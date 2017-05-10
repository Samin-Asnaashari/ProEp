'use strict';

angular.module('appComponent.student').controller('studentCtrl', function (studentsResolve) {

    var vm = this;
    vm.sudents = studentsResolve.students;
    vm.RowNumber = function (student) {
        return vm.students.indexOf(student) + 1;
    };
});