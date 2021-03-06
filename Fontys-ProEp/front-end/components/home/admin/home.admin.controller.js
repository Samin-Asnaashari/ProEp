'use strict';

angular.module('appComponent.homeAdmin').controller('homeCtrl', function (Notification, $scope, coursesResolve, courseService, $mdDialog, EventCourse) {

    var vm = this;
    vm.courses = coursesResolve.courses;

    EventCourse.subscribeOnCourseDeleted($scope, function (event, data) {
        vm.courses.splice(vm.courses.indexOf(data.course), 1);
    });

    EventCourse.subscribeOnCourseAdded($scope, function (event, data) {
        var add = true;
        angular.forEach(vm.courses, function (c) {
            if (c.code === data.course.code) {
                add = false;
            }
        });
        if (add === true) {
            return courseService.addCourse(data.course)
                .then(function (response) {
                    vm.courses.push(data.course);
                }, function (error) {
                    Notification.error('Error adding course!');
                });
        }else{
            Notification.error(data.course.code + " is already in the list!");
        }
    });

    vm.goToFontysCourseDialog = function () {
        return courseService.getAllFontysCourses()
            .then(function (response) {
                vm.showCourseDialog(response.data);
            }, function (error) {
                Notification.error("Error showing fontys courses!");
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
                vm.allFontysCourses = fontysCoursesList;

                vm.close = function () {
                    $mdDialog.cancel();
                };
            },
            controllerAs: 'vmFontysCourseDialog'
        });
    };
});