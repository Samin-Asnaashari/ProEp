'use strict';

angular.module('appComponent.courseView').controller('courseViewCtrl', function ($stateParams, $scope,registrationService, acceptedRegistrationsResolve,
                                                                                 pendingRegistrationsResolve, declinedRegistrationsResolve, EventStudent) {
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

    EventStudent.subscribeOnAStudentAdded($scope, function (event, data) {
        console.log(event, "event");
        console.log(data.student.pcn, "data");
        console.log(vm.course.code, "code");
        registrationService.updateRegistration(vm.course.code,data.student.pcn,"ACCEPTED")
            .then(function () {
                if(vm.pendingStudents.indexOf(data.student) !== -1) {
                    vm.pendingStudents.splice(vm.pendingStudents.indexOf(data.student), 1);
                }
                if(vm.declinedStudents.indexOf(data.student) !== -1) {
                    vm.declinedStudents.splice(vm.declinedStudents.indexOf(data.student), 1);
                }
                vm.acceptedStudents.push(data.student);
            }, function (error) {

            });
    });

    EventStudent.subscribeOnAStudentRemoved($scope, function (event, data) {
        console.log(data.pcn, "data");
        registrationService.updateRegistration(vm.course.code,data.pcn,"DECLINE")
            .then(function () {
                vm.acceptedStudents.splice(vm.acceptedStudents.indexOf(data), 1);
                vm.declinedStudents.push(data);
            }, function (error) {

            });
    });
});
