'use strict';

angular.module('appComponent.course').controller('courseEditCtrl', function ($stateParams, $state, $scope, courseResolve) {

    var vm = this;
    vm.course = courseResolve.course;
});