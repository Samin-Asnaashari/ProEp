'use strict';

angular.module('appComponent.homeAdmin').controller('homeCtrl', function ($state, $scope, coursesResolve, courseService) {

    var vm = this;
    vm.courses = coursesResolve.courses;
    vm.RowNumber = function (course) {
        return vm.courses.indexOf(course) + 1;
    };
    vm.DeleteRequest = function (courseCode) {
        courseService.deleteCourse(courseCode)
            .then(function (response) {
                console.log("success");
            }, function (error) {
                console.log("error");
            });
    };
});