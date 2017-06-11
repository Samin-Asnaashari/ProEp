'use strict';

angular.module('appComponent.courseView').controller('courseViewCtrl', function ($stateParams, acceptedRegistrationsResolve,
                                                                                 pendingRegistrationsResolve, declinedRegistrationsResolve) {
    var vm = this;
    vm.course = $stateParams.course;
    vm.acceptedRegistrations = acceptedRegistrationsResolve.acceptedRegistrations;
    vm.pendingRegistrations = pendingRegistrationsResolve.pendingRegistrations;
    vm.declinedRegistrations = declinedRegistrationsResolve.declinedRegistrations;

    vm.acceptedStudents = acceptedRegistrationsResolve.acceptedRegistrations;
    vm.pendingStudents = [];
    vm.declinedStudents = [];

    //vm.acceptedStudents = [{"class":"com.example.Student","pcn":310323,"password":"123","email":"example@student.fontys.nl","firstName":"Samin","lastName":"Asnaashari","studentNumber":271372,"studentType":"REGULARSEMESTER","major":"SOFTWARE","avgScore":8.0,"pushNotificationToken":null,"notificationBadgeCount":0,"notifications":[]},{"class":"com.example.Student","pcn":236478,"password":"789","email":"blabla@student.fontys.nl","firstName":"Beer","lastName":"LaLa","studentNumber":278383,"studentType":"SECONDSEMESTER","major":"TECHNOLOGY","avgScore":6.0,"pushNotificationToken":null,"notificationBadgeCount":0,"notifications":[]},{"class":"com.example.Student","pcn":37272,"password":"738","email":"agnes@student.fontys.nl","firstName":"Agnes","lastName":"Wasee","studentNumber":637367,"studentType":"REGULARSEMESTER","major":"SOFTWARE","avgScore":8.0,"pushNotificationToken":null,"notificationBadgeCount":0,"notifications":[]},{"class":"com.example.Student","pcn":18583,"password":"74839","email":"tech@student.fontys.nl","firstName":"Dex","lastName":"Heijden","studentNumber":382938,"studentType":"SECONDSEMESTER","major":"BUSINESS","avgScore":7.0,"pushNotificationToken":null,"notificationBadgeCount":0,"notifications":[]}];

    vm.getAcceptedStudents = function () {
        for (var i=0; i<vm.acceptedRegistrations.length; i++) {
            vm.acceptedStudents.push(vm.acceptedRegistrations[i].student);
        }
        return vm.acceptedStudents;
    };

    vm.getPendingStudents = function () {
        angular.forEach(vm.pendingRegistrations, function (r) {
            vm.pendingStudents.push(r.student);
        });
        return vm.pendingStudents;
    };

    vm.getDeclinedStudents = function () {
        angular.forEach(vm.declinedRegistrations, function (r) {
            vm.declinedStudents.push(r.student);
        });
        return vm.declinedStudents;
    };

});
