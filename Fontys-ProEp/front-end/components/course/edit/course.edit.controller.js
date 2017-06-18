'use strict';

angular.module('appComponent.courseEdit').controller('courseEditCtrl', function ($stateParams, $state, $scope,
                                                                                 courseResolve, courseService, EventCourseEdit,
                                                                                 $location, $anchorScroll, enumsService,
                                                                                 teacherService, $mdDialog, EventTeacher, Notification) {
    var vm = this;
    vm.course = courseResolve.course;
    vm.majors = [];
    vm.courseTypes = [];

    vm.courseState = {};
    vm.removedcourseStates = [];

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
                Notification.error("Error getting course state");
            });
    };

    vm.loadCourseTypes = function () {
        return enumsService.getCourseTypesPossibleValues()
            .then(function (response) {
                vm.courseTypes = response.data;
            }, function (error) {
                Notification.error("Error getting course state");
            });
    };

    /**
     * Adding new combination of courseType + Major as state to course (in frontEnd)
     */
    vm.addNewState = function () {
        var add = true;
        angular.forEach(vm.course.states, function (s) {
            if (s.major === vm.courseState.major) {
                add = false;
                //TODO break;
            }
        });
        if (add === true) {
            vm.course.states.push(vm.courseState);
            vm.courseState = {};
        }//TODO else show error you can't add two different state for one major
    };

    /**
     * Removing a combination of courseType + Major as state from course (in frontEnd)
     */
    vm.removeState = function (courseState) {
        var index = vm.course.states.indexOf(courseState);
        vm.course.states.splice(index, 1);
        if (courseState.id != undefined) {
            vm.removedcourseStates.push(courseState);
        }
    };

    vm.save = function () {
        vm.course.regStartDate = vm.selectedStartDate.toISOString();
        vm.course.regEndDate = vm.selectedEndDate.toISOString();
        return courseService.updateCourse(vm.course)
            .then(function (response) {
                //vm.removeStatesFromCourse();
                $state.go('home');
            }, function (error) {
                Notification.error("Error saving");
            });
    };

    vm.saveNewCourseStates = function () {
        if (vm.addedcourseStates.length !== 0) {
            return courseService.addNewCourseStateToCourse(vm.course.code, vm.addedcourseStates)
                .then(function (response) {
                    vm.addedcourseStates = [];
                }, function (error) {
                    Notification.error("Error adding course state");
                });
        }
    };

    vm.removeStatesFromCourse = function () {
        if (vm.removedcourseStates.length !== 0) {
            return courseService.removeCourseStateFromCourse(vm.removedcourseStates)
                .then(function (response) {
                    vm.removedcourseStates = [];
                }, function (error) {
                    Notification.error("Error removing course state");
                });
        }
    };

    vm.goToFontysTeacherDialog = function () {
        return teacherService.getAllFontysCourses()
            .then(function (response) {
                var allTeachers = [];
                allTeachers = response.data;
                vm.showDialog(allTeachers);
            }, function (error) {
                Notification.error("Error opening fontys teachers");
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
        var index = vm.course.teachers.indexOf(teacher);
        vm.course.teachers.splice(index, 1);
    };

    EventTeacher.subscribeOnAddATeacherToCourse($scope, function (event, data) {
        var add = true;
        angular.forEach(vm.course.teachers, function (t1) {
            if (data.teacher.pcn === t1.pcn) {
                add = false;
            }
        });
        if (add === true) {
            vm.course.teachers.push(data.teacher);
        }
        /*TODO else show that teacher already exist*/
    });

    EventTeacher.subscribeOnAddTeachersToCourse($scope, function (event, data) {
        angular.forEach(data.teachers, function (t1) {
            var add = true;
            angular.forEach(vm.course.teachers, function (t2) {
                if (t1.pcn === t2.pcn) {
                    add = false;
                }
            });
            if (add === true) {
                vm.course.teachers.push(t1);
            }
            /*TODO else show that teacher already exist*/
        });
    });
})
    .config(function ($mdThemingProvider) {
        // Configure a dark theme with primary foreground yellow
        $mdThemingProvider.theme('docs-dark', 'default')
            .primaryPalette('purple')
            .dark();
    });