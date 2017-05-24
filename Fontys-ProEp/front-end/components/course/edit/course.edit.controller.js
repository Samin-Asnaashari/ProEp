'use strict';

angular.module('appComponent.courseEdit').controller('courseEditCtrl', function ($stateParams, $state, $scope,
                                                                                 courseResolve, courseService, EventCourseEdit,
                                                                                 $location, $anchorScroll, enumsService,
                                                                                 teacherService, $mdDialog, EventTeacher) {
    var vm = this;
    vm.course = courseResolve.course;
    vm.majors = [];
    vm.courseTypes = [];

    vm.courseState = {};
    vm.courseStates = vm.course.states;
    vm.addedcourseStates = [];
    vm.removedcourseStates = [];

    vm.teachers = vm.course.teachers;
    vm.addedTeacheres = [];
    vm.removedTeachers = [];

    EventCourseEdit.subscribeOnDescriptionChange($scope, function (event, data) {
        vm.course.description = data.description;
    });

    vm.goTop = function () {
        $location.hash('top');
        // call $anchorScroll()
        $anchorScroll();
    };

    vm.loadMajors = function () {
        return enumsService.getMajorsPossibleValues()
            .then(function (response) {
                vm.majors = response.data;
            }, function (error) {
            });
    };

    vm.loadCourseTypes = function () {
        return enumsService.getCourseTypesPossibleValues()
            .then(function (response) {
                vm.courseTypes = response.data;
            }, function (error) {
            });
    };

    /**
     * Adding new combination of courseType + Major as state to course (in frontEnd)
     */
    vm.addNewState = function () {
        var add = true;
        angular.forEach(vm.courseStates, function (s) {
            if (s.major === vm.courseState.major) {
                add = false;
                //TODO break;
            }
        });
        if (add == true) {
            vm.courseStates.push(vm.courseState);
            vm.addedcourseStates.push(vm.courseState);
            vm.courseState = {};
        }//TODO else show error you can't add two different state for one major
    };

    /**
     * Removing a combination of courseType + Major as state from course (in frontEnd)
     */
    vm.removeState = function (courseState) {
        var index = vm.courseStates.indexOf(courseState);
        vm.courseStates.splice(index, 1);
        var findIndex = vm.addedcourseStates.indexOf(courseState);
        if (findIndex != -1) {
            vm.addedcourseStates.splice(findIndex, 1);
        } else {
            vm.removedcourseStates.push(courseState);
        }
    };

    vm.save = function () {
        var tempStartMoment = moment(vm.course.regStartDate, "LL LT");
        vm.course.regStartDate = tempStartMoment.toISOString();
        var tempEndMoment = moment(vm.course.regEndDate, "LL LT");
        vm.course.regEndDate = tempEndMoment.toISOString();
        return courseService.updateCourse(vm.course)
            .then(function (response) {
                vm.saveNewCourseStates();
                vm.removeStatesFromCourse();
                vm.submitDeletedTeachersFromCourse();
                vm.submitAddedTeacherToCourse();
                $state.go('home');
            }, function (error) {
            });
    };

    vm.saveNewCourseStates = function () {
        if (vm.addedcourseStates.length != 0) {
            return courseService.addNewCourseStateToCourse(vm.course.code, vm.addedcourseStates)
                .then(function (response) {
                    vm.addedcourseStates = [];
                }, function (error) {
                });
        }
    };

    vm.removeStatesFromCourse = function () {
        if (vm.removedcourseStates.length != 0) {
            return courseService.removeCourseStateFromCourse(vm.removedcourseStates)
                .then(function (response) {
                    vm.removedcourseStates = [];
                }, function (error) {
                });
        }
    };

    vm.goToFontysTeacherDialog = function () {
        return teacherService.getAllTeachers()
            .then(function (response) {
                var allTeachers = [];
                allTeachers = response.data;
                vm.showDialog(allTeachers);
            }, function (error) {

            });
    };

    vm.showDialog = function (fontysTeacherList) {
        $mdDialog.show({
            templateUrl: './components/course/edit/fontysTeachersDialog/fontys.teacher.dialog.html',
            clickOutsideToClose: true,
            parent: angular.element(document.body),
            locals: {fontysTeacherList: fontysTeacherList},
            controller: function () {
                var vm = this;
                vm.allFontysTeachers = fontysTeacherList; //TODO Check

                vm.close = function () {
                    $mdDialog.cancel();
                };
            }
            ,
            controllerAs: 'vmFontysTeachersDialog'

        });
    };

    vm.removeTeacherFromCourse = function (teacher) { /*TODO check passing teacher or pcn*/
        var index = vm.teachers.indexOf(teacher);
        vm.teachers.splice(index, 1);
        var findIndex = vm.addedTeacheres.indexOf(teacher);
        if (findIndex != -1) {
            vm.addedTeacheres.splice(findIndex, 1);
        } else {
            vm.removedTeachers.push(teacher);
        }
    };

    EventTeacher.subscribeOnAddATeacherToCourse($scope, function (event, data) {
        var add = true;
        angular.forEach(vm.teachers, function (t1) {
            if (data.teacher.pcn == t1.pcn) {
                add = false;
            }
        });
        if (add == true) {
            vm.teachers.push(data.teacher);
            vm.addedTeacheres.push(data.teacher);
        }
        /*TODO else show that teacher already exist*/
    });

    EventTeacher.subscribeOnAddTeachersToCourse($scope, function (event, data) {
        angular.forEach(data.teachers, function (t1) {
            var add = true;
            angular.forEach(vm.teachers, function (t2) {
                if (t1.pcn == t2.pcn) {
                    add = false;
                }
            });
            if (add == true) {
                vm.teachers.push(t1);
                vm.addedTeacheres.push(t1);
            }
            /*TODO else show that teacher already exist*/
        });
    });

    vm.submitAddedTeacherToCourse = function () {
        if (vm.addedTeacheres.length != 0) {
            return courseService.AddTeachersToCourse(vm.addedTeacheres, vm.course.code)
                .then(function (response) {
                    vm.addedTeacheres = [];
                }, function (error) {
                });
        }
    };

    vm.submitDeletedTeachersFromCourse = function () {
        if (vm.removedTeachers.length != 0) {
            return courseService.DeleteTeachersFromCourse(vm.removedTeachers, vm.course.code)
                .then(function (response) {
                    vm.removedTeachers = [];
                }, function (error) {
                });
        }
    };
})
    .config(function ($mdThemingProvider) {
        // Configure a dark theme with primary foreground yellow
        $mdThemingProvider.theme('docs-dark', 'default')
            .primaryPalette('purple')
            .dark();
    });