'use strict';

angular.module('appComponent.list').controller('customListCtrl', function ($scope) {

    var vm = this;
    vm.showFilter = false;
    vm.action = 1; //0 determines removing 1 determines adding

    vm.selected = [];

    vm.ShowOrHideFilter = function () {
        vm.showFilter = !vm.showFilter;
    };

    vm.addStudents = function () {
        //TODO
    };

    vm.removeStudents = function () {
        //TODO
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
        vm.selected.length !== $scope.list.length); //TODO check if list is ok to be $scope
    };

    vm.isChecked = function () {
        return vm.selected.length === $scope.list.length; //TODO check if list is ok to be $scope
    };

    vm.toggleAll = function (list) {
        if (vm.selected.length === list.length) {
            vm.selected = [];
        } else if (vm.selected.length === 0 || vm.selected.length > 0) {
            vm.selected = list.slice(0);
        }
    };
});