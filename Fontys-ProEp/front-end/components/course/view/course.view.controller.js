'use strict';

angular.module('appComponent.courseView').controller('courseViewCtrl', function ($stateParams, $state, $scope, $anchorScroll,
                                                                                 acceptedRegistrationsResolve, pendingRegistrationsResolve, declineRegistrationsResolve) {
    var vm = this;
    vm.course = {};
    vm.acceptedRegistrations = acceptedRegistrationsResolve.acceptedRegistrations;
    vm.PendingRegistrations = pendingRegistrationsResolve.PendingRegistrations;
    vm.declineRegistrations = declineRegistrationsResolve.declineRegistrations;

    vm.goTop = function () {
        $location.hash('top');
        // call $anchorScroll()
        $anchorScroll();
    };
});
