'use strict';

angular.module('appComponent.student').controller('studentCtrl', function ($state, $scope, studentsResolve, personService) {

    var vm = this;
    vm.students = studentsResolve.students;
    vm.RowNumber = function (student) {
        return vm.students.indexOf(student) + 1;
    };
    vm.DeleteStudent = function (student) {
        personService.deletePerson(student.pcn)
            .then(function (response) {
                console.log("success");
                vm.students.splice(vm.students.indexOf(student), 1);
            }, function (error) {
                console.log("error");
            });
    };
});