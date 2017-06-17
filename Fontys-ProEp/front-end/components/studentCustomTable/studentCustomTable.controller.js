'use strict';

angular.module('appComponent.studentTable').controller('studentCustomTableCtrl', function ($scope, EventStudent) {

    var vm = this;
    vm.showFilter = false;
    vm.selected = [];

    vm.getMaxColspan = function () {
      if($scope.registration === 1) {
          return 22;
      }
      else
          return 20;
    };

    vm.ShowOrHideFilter = function () {
        vm.showFilter = !vm.showFilter;
    };

    vm.addStudent = function (student) {
        EventStudent.notifyOnAStudentAdded(student);
    };

    vm.removeStudent = function (pcn) {
        EventStudent.notifyOnAStudentRemoved(pcn);
    };

    vm.addStudents = function (students) {
        if(students.length > 0)
            EventStudent.notifyOnStudentsAdded(students);
    };

    vm.removeStudents = function (students) {
        if(students.length > 0)
            EventStudent.notifyOnStudentsRemoved(students);
    };

    vm.toggle = function (item) {
        var idx = vm.selected.indexOf(item);
        if (idx > -1) {
            vm.selected.splice(idx, 1);
        }
        else {
            vm.selected.push(item);
        }
    };

    vm.exists = function (item) {
        return vm.selected.indexOf(item) > -1;
    };

    vm.isIndeterminate = function () {
        return (vm.selected.length !== 0 &&
        vm.selected.length !== $scope.studentList.length); //TODO check if list is ok to be $scope
    };

    vm.isChecked = function () {
        return vm.selected.length === $scope.studentList.length; //TODO check if list is ok to be $scope
    };

    vm.toggleAll = function (list) {
        if (vm.selected.length === list.length) {
            vm.selected = [];
        } else if (vm.selected.length === 0 || vm.selected.length > 0) {
            vm.selected = list.slice(0);
        }
    };
});