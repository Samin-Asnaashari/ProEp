'use strict';

angular.module('appComponent.studentTable').directive('studentCustomTable', function () {
        return {
            restrict: "E",
            scope: {
                studentList: '=',
                removeAction: '=',
                addAction: '=',
                registration: '='
            },
            templateUrl: "./components/studentCustomTable/studentCustomTable.html",
            controller: 'studentCustomTableCtrl',
            controllerAs: 'vmStudentCustomTable'
        };
    }
);