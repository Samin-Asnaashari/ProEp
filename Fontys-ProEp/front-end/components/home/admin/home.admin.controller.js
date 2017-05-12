'use strict';

angular.module('appComponent.homeAdmin').controller('homeCtrl', function ($state, $scope, coursesResolve, courseService) {

    var vm = this;
    vm.courses = coursesResolve.courses;
    vm.RowNumber = function (course) {
        return vm.courses.indexOf(course) + 1;
    };
    vm.RequestCourseDeletion = function (courseCode) {
        courseService.RequestCourseDeletion(courseCode)
            .then(function (response) {
                console.log("success request");
                /*show confirmation model with possible warning message 'response' if empty then there is no warning to how to admin
                if yes is pressed then call deletion function
                courseService.deleteCourse(courseCode)
                    .then(function (response) {
                        console.log("success deletion");
                    }, function (error) {
                        console.log("error deletion");
                    });*/
            }, function (error) {
                console.log("error request");
                console.log(error.message);

            });
    };
});