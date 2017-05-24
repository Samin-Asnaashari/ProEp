'use strict';

angular.module('appComponent.courseTable').controller('courseCustomTableCtrl', function ($state, $scope, courseService, $mdDialog, EventCourse) {

    var vm = this;

    vm.RowNumber = function (course) {
        return $scope.courseList.indexOf(course) + 1;
    };

    vm.goToCourseEdit = function (courseCode) {
        $state.go('courseEdit', {code: courseCode})
    };

    vm.addCourse = function (course) {
        EventCourse.notifyOnCourseAdded(course);
    };

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
                            EventCourse.notifyOnCourseDeleted(course);
                        }, function (error) {
                            console.log("error deletion");
                        });
                    $mdDialog.cancel();
                };
            },
            controllerAs: 'vmDeleteCourseConfirmationDialog'
        });
    };
});