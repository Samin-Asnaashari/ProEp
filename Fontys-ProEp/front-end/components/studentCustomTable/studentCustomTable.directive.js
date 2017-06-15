'use strict';

angular.module('appComponent.studentTable').directive('studentCustomTable', function () {
        return {
            restrict: "E",
            scope: {
                studentList: '=',
                action: '='  //0 determines removing 1 determines adding
            },
            templateUrl: "./components/studentCustomTable/studentCustomTable.html",
            controller: 'studentCustomTableCtrl',
            controllerAs: 'vmStudentCustomTable'
        };
    }
);