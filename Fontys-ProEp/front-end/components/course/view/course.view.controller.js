'use strict';

angular.module('appComponent.courseView').controller('courseViewCtrl', $state, function () {

    var vm = this;
    vm.courses = courseResolve;
    vm.RowNumber = function (course) {
        return vm.courses.indexOf(course) + 1;
    };
});