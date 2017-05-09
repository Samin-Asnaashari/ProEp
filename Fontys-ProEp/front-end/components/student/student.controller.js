'use strict';

angular.module('appComponent.student').controller('studentCtrl', $state, function () {

    var vm = this;
    vm.courses = coursResolve;
    vm.RowNumber = function (student) {
        return vm.students.indexOf(student) + 1;
     };
});