'use strict';

angular.module('appComponent.homeAdmin').controller('homeCtrl', function ($state, $scope, coursesResolve) {

    var vm = this;
    vm.courses = coursesResolve.courses;
    vm.RowNumber = function (course) {
        return vm.courses.indexOf(course) + 1;
    };
});