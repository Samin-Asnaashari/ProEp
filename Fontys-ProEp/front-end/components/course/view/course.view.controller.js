'use strict';

angular.module('appComponent.courseView').controller('courseViewCtrl', function ($stateParams, $state, $scope,
                                                                                 acceptedRegistrationsResolve,
                                                                                 pendingRegistrationsResolve, declineRegistrationsResolve,courseResolve) {
    var vm = this;
    vm.course = courseResolve.course;
    vm.acceptedRegistrations = acceptedRegistrationsResolve.acceptedRegistrations;
    console.log(vm.acceptedRegistrations, "accepted");
    vm.PendingRegistrations = pendingRegistrationsResolve.PendingRegistrations;
    vm.declineRegistrations = declineRegistrationsResolve.declineRegistrations;
    vm.acceptedRegistrationStudents = [];

    vm.acceptedStudents = function () {
        angular.forEach(vm.acceptedRegistrations, function (s) {
            var temp;
            temp = studentService.getStudent(s.studentPcn);
            vm.acceptedRegistrationStudents.push(temp);
        });
        return vm.acceptedRegistrationStudents;
        };
});
