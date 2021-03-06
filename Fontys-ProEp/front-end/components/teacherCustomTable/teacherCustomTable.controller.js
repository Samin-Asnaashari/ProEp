'use strict';

angular.module('appComponent.teacherTable').controller('teacherCustomTableCtrl', function ($scope, EventTeacher) {

    var vm = this;
    vm.showFilter = false;
    vm.selected = [];

    vm.ShowOrHideFilter = function () {
        vm.showFilter = !vm.showFilter;
    };

    vm.addTeacher = function (teacher) {
        EventTeacher.notifyOnAddATeacherToCourse(teacher);
    };

    vm.addTeachers = function () {
        EventTeacher.notifyOnAddTeachersToCourse(vm.selected);
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
        vm.selected.length !== $scope.teacherList.length); //TODO check if list is ok to be $scope
    };

    vm.isChecked = function () {
        return vm.selected.length === $scope.teacherList.length; //TODO check if list is ok to be $scope
    };

    vm.toggleAll = function (list) {
        if (vm.selected.length === list.length) {
            vm.selected = [];
        } else if (vm.selected.length === 0 || vm.selected.length > 0) {
            vm.selected = list.slice(0);
        }
    };
});