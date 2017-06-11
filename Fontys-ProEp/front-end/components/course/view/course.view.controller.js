'use strict';

angular.module('appComponent.courseView').controller('courseViewCtrl', function ($stateParams, acceptedRegistrationsResolve,
                                                                                 pendingRegistrationsResolve, declinedRegistrationsResolve) {
    var vm = this;

    //storing data for when browser refreshes
    if(angular.fromJson(sessionStorage.course) === null || ($stateParams.course !== null &&
        $stateParams.course.code !== angular.fromJson(sessionStorage.course))) {
        sessionStorage.course = angular.toJson($stateParams.course);
    }

    //getting stored data for when browser refreshes
    vm.course = angular.fromJson(sessionStorage.course);

    //Coming from route resolve only all accepted, pending and declined students
    //If you need information from the registration objects then see route acceptedRegistrationsResolve for example
    vm.acceptedStudents = acceptedRegistrationsResolve.acceptedStudents;
    vm.pendingStudents = pendingRegistrationsResolve.pendingStudents;
    vm.declinedStudents = declinedRegistrationsResolve.declinedStudents;
});
