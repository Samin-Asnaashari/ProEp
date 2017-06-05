/**
 * Created by Agnes on 5-6-2017.
 */
'use strict';

angular.module('appComponent.courseView').controller('courseViewCtrl', function ($stateParams, $state, $scope,
                                                                                 courseResolve, courseService) {
    var vm = this;
    vm.course = courseResolve.course;
    vm.majors = [];
    vm.courseTypes = [];

    vm.courseState = {};
    vm.courseStates = vm.course.states;

    vm.teachers = vm.course.teachers;

    vm.goTop = function () {
        $location.hash('top');
        // call $anchorScroll()
        $anchorScroll();
    };

})
    .config(function ($mdThemingProvider) {
        // Configure a dark theme with primary foreground yellow
        $mdThemingProvider.theme('docs-dark', 'default')
            .primaryPalette('purple')
            .dark();
    });