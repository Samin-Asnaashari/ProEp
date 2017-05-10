'use strict';

angular.module('appComponent.homeAdmin').controller('homeCtrl', function ($state, $scope, coursesResolve) {

    vm.courses = coursesResolve;
    vm.RowNumber = function (course) {
        return vm.courses.indexOf(course) + 1;
    };
});