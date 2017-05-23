'use strict';

angular.module('appComponent.homeAdmin').controller('homeCtrl', function ($state, $scope, coursesResolve, courseService) {

    var vm = this;
    vm.courses = coursesResolve.courses;
    vm.RowNumber = function (course) {
        return vm.courses.indexOf(course) + 1;
    };
    vm.requestCourseDeletion = function (course) {
        courseService.requestCourseDeletion(course.code)
            .then(function (response) {
                //console.log(response.data.message);
                if (response.data.message != "") {
                    /*show confirmation modal with possible warning message as 'response.message' if empty then there is no warning for admin
                     if yes is pressed then call deletion function*/
                    courseService.deleteCourse(course.code)
                        .then(function (response) {
                            console.log("success deletion2s");
                            vm.courses.splice(vm.courses.indexOf(course), 1);
                        }, function (error) {
                            console.log("error deletion");
                        });
                }
                else
                    vm.courses.splice(vm.courses.indexOf(course), 1);
            }, function (error) {
                console.log("error request");
                console.log(error.message);

            });
    };
    /*vm.AddCourse = function (course) {
     coursesResolve.addCourse(course)
     .then(function (response) {
     console.log("success");
     }, function (error) {
     console.log("error");
     });
     };*/
});