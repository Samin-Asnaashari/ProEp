'use strict';

angular.module('appComponent.courseEdit').controller('courseEditCtrl', function ($stateParams, $state, $scope,
                                                                                 courseResolve, courseService, EventCourseEdit, $location, $anchorScroll, enumsService) {

    var vm = this;
    vm.course = courseResolve.course;
    vm.majors = [];
    vm.courseTypes = [];

    vm.courseState = {};
    vm.courseStates = vm.course.states;

    vm.addedcourseStates = [];
    vm.removedcourseStates = [];

    vm.teachers = [];
    
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
        if(add == true){
            vm.courseStates.push(vm.courseState);
            vm.addedcourseStates.push(vm.courseState);
            vm.courseState = {};
        }//TODO else show error you cant add two different state for a major
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
        return courseService.updateCourse(vm.course)
            .then(function (response) {
                vm.saveNewCourseStates();
                vm.removeStatesFromCourse();
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
})
    .config(function ($mdThemingProvider) {

        // Configure a dark theme with primary foreground yellow
        $mdThemingProvider.theme('docs-dark', 'default')
            .primaryPalette('purple')
            .dark();
    });