'use strict';

angular.module('appComponent.courseView').controller('courseViewCtrl', function ($anchorScroll, $location, $filter, $stateParams, $scope,registrationService, acceptedRegistrationsResolve,
                                                                                 pendingRegistrationsResolve, declinedRegistrationsResolve, EventStudent) {
    var vm = this;

    //storing data for when browser refreshes
    if(angular.fromJson(sessionStorage.course) === null || $stateParams.course !== null) {
        sessionStorage.course = angular.toJson($stateParams.course);
    }

    //getting stored data for when browser refreshes
    vm.course = angular.fromJson(sessionStorage.course);

    //Coming from route resolve only all accepted, pending and declined students
    //If you need information from the registration objects then see route acceptedRegistrationsResolve for example
    vm.acceptedStudents = acceptedRegistrationsResolve.acceptedStudents;
    vm.pendingStudents = pendingRegistrationsResolve.pendingStudents;
    vm.declinedStudents = declinedRegistrationsResolve.declinedStudents;

    vm.goTop = function () {
        $location.hash('top');
        // call $anchorScroll()
        $anchorScroll();
    };

    vm.getStudentPCNs = function (list) {
        var studentPCNs = [];
        angular.forEach(list, function (student) {
            studentPCNs.push(student.pcn);
        });
        return studentPCNs;
    };

    vm.handleRemoveOrAdd = function (spliceList, pushList, pushSliceObject, status) {
        vm.callAddOrRemoveBackend(pushSliceObject.pcn, status)
            .then(function () {
                vm.handleSpliceAndPush(spliceList, pushList, pushSliceObject);
            }, function (error) {
                console.log(angular.toJson(error));
            });
    };

    vm.handleRemoveOrAddList = function (spliceList, pushList, pushSliceListObjects, status) {
        vm.callAddOrRemoveBackend(vm.getStudentPCNs(pushSliceListObjects), status)
            .then(function (response) {
                angular.forEach(pushSliceListObjects, function (student) {
                    vm.handleSpliceAndPush(spliceList, pushList, student);
                });
            }, function (error) {
                console.log(angular.toJson(error));
            });
    };

    vm.callAddOrRemoveBackend = function (listOfPCNs, status) {
        return registrationService.updateRegistration(vm.course.code, listOfPCNs, status);
    };

    vm.handleSpliceAndPush = function (spliceList, pushList, pushSliceObject) {
        spliceList.splice(spliceList.indexOf(pushSliceObject), 1);
        pushList.push(pushSliceObject);
    };

    EventStudent.subscribeOnAStudentAdded($scope, function (event, data) {
        if(vm.pendingStudents.indexOf(data.student) !== -1) {
            vm.handleRemoveOrAdd(vm.pendingStudents, vm.acceptedStudents, data.student, "ACCEPTED");
            return;
        }
        vm.handleRemoveOrAdd(vm.declinedStudents, vm.acceptedStudents, data.student, "ACCEPTED");
    });

    EventStudent.subscribeOnAStudentRemoved($scope, function (event, data) {

        var studentFoundList = $filter("filter")(vm.acceptedStudents, {pcn:data.pcn});
        if(studentFoundList.length !== 0) {
            vm.handleRemoveOrAdd(vm.acceptedStudents, vm.declinedStudents, studentFoundList[0], "DECLINED");
            return;
        }
        vm.handleRemoveOrAdd(vm.pendingStudents, vm.declinedStudents, $filter("filter")(vm.pendingStudents, {pcn:data.pcn})[0], "DECLINED");
    });

    EventStudent.subscribeOnStudentsAdded($scope, function (event, data) {
        if(vm.pendingStudents.indexOf(data.students[0]) !== -1) {
            vm.handleRemoveOrAddList(vm.pendingStudents, vm.acceptedStudents, data.students, "ACCEPTED");
            return;
        }
        vm.handleRemoveOrAddList(vm.declinedStudents, vm.acceptedStudents, data.students, "ACCEPTED");
    });

    EventStudent.subscribeOnStudentsRemoved($scope, function (event, data){
        if(vm.pendingStudents.indexOf(data.students[0]) !== -1) {
            vm.handleRemoveOrAddList(vm.pendingStudents, vm.declinedStudents, data.students, "DECLINED");
            return;
        }
        vm.handleRemoveOrAddList(vm.acceptedStudents, vm.declinedStudents, data.students, "DECLINED");
    });
});
