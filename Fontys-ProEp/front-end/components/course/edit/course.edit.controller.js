'use strict';

angular.module('appComponent.courseEdit').controller('courseEditCtrl', function ($stateParams, $state, $scope, courseResolve, courseService, EventCourseEdit, $location, $anchorScroll) {

    var vm = this;
    vm.course = courseResolve.course;
    vm.majors = [];
    vm.courseTypes = [];
    vm.major = null;
    vm.courseType = null;
    vm.teachers = [];

    EventCourseEdit.subscribeOnDescriptionChange($scope, function (event, data) {
        vm.course.description = data.description;
    });

    vm.goTop = function () {
        $location.hash('top');
        // call $anchorScroll()
        $anchorScroll();
    };

    vm.loadMajor = function () {

    };

    vm.loadCourseType = function () {

    };

    /**
     * Adding new combination of courseType + Major as state to course
     */
    vm.addNewStateToCourse = function () {

    };

    vm.save = function () {
        return courseService.updateCourse(vm.course)
            .then(function (response) {
                $state.go('home');
            }, function (error) {
            });
    };
})
    .config(function ($mdThemingProvider) {

        // Configure a dark theme with primary foreground yellow
        $mdThemingProvider.theme('docs-dark', 'default')
            .primaryPalette('purple')
            .dark();

    });