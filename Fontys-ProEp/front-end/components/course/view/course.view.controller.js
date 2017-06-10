'use strict';

angular.module('appComponent.courseView').controller('courseViewCtrl', function ($stateParams, $state, $scope,
                                                                                 acceptedRegistrationsResolve,
                                                                                 pendingRegistrationsResolve, declineRegistrationsResolve,courseResolve) {
    var vm = this;
    vm.course = courseResolve.course;
    vm.acceptedRegistrations = acceptedRegistrationsResolve.acceptedRegistrations;
    vm.PendingRegistrations = pendingRegistrationsResolve.PendingRegistrations;
    vm.declineRegistrations = declineRegistrationsResolve.declineRegistrations;

    vm.provideCourse = function (course) {
        console.log('course provided', course);

    }
});
