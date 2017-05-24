'use strict';

angular.module('appComponent.homeAdmin').controller('homeCtrl', function ($state, $scope, coursesResolve, courseService, $mdDialog, EventCourse) {

    var vm = this;
    vm.courses = coursesResolve.courses;
    vm.RowNumber = function (course) {
        return vm.courses.indexOf(course) + 1;
    };

    EventCourse.subscribeOnCourseDeleted($scope, function(event, data){
        vm.courses.splice(vm.courses.indexOf(data.course), 1);
    });

    vm.requestCourseDeletion = function (course) {
        courseService.requestCourseDeletion(course.code)
            .then(function (response) {
                vm.showDialog(response.data.message, course);
            }, function (error) {
                console.log("error request");
                console.log(error.data.message);
            });
    };
    vm.showDialog = function (serverMessage, course) {
        $mdDialog.show({
            templateUrl: './components/ConfirmationDialog/delete.confirmation.template.html',
            clickOutsideToClose: true,
            parent: angular.element(document.body),
            locals: {serverResponse: serverMessage},
            controller: function (courseService, EventCourse) {
                var vm = this;
                vm.serverResponse = serverMessage;
                vm.cancel = function () {
                    $mdDialog.cancel();
                };
                vm.ok = function () {
                    courseService.deleteCourse(course.code)
                        .then(function (response) {
                            console.log("success deletion2s");
                            EventCourse.notifyOnCourseDeleted(course);//todo for loop
                        }, function (error) {
                            console.log("error deletion");
                        });
                    $mdDialog.cancel();
                };
            },
            controllerAs: 'vmDeleteCourseConfirmationDialog'
        });
    };

    vm.goToFontysCourseDialog = function () {
        return courseService.getAllFontysCourses()
            .then(function (response) {
                var allCourses = [];
                allCourses = response.data;
                vm.showCourseDialog(allCourses);
            }, function (error) {

            });
    };

    vm.showCourseDialog = function (fontysCoursesList) {
        $mdDialog.show({
            templateUrl: './components/home/admin/fontysCourseDialog/fontys.course.dialog.html',
            clickOutsideToClose: true,
            parent: angular.element(document.body),
            locals: {fontysCoursesList: fontysCoursesList},
            controller: function () {
                var vm = this;
                vm.allFontysCourses = fontysCoursesList; //TODO Check

                vm.close = function () {
                    $mdDialog.cancel();
                };
            }
            ,
            controllerAs: 'vmFontysCourseDialog'

        })
        ;
    };
});