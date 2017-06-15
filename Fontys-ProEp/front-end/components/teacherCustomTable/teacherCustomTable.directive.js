'use strict';

angular.module('appComponent.teacherTable').directive('teacherCustomTable', function () {
        return {
            restrict: "E",
            scope: {
                teacherList: '='
            },
            templateUrl: "./components/teacherCustomTable/teacherCustomTable.html",
            controller: 'teacherCustomTableCtrl',
            controllerAs: 'vmTeacherCustomTable'
        };
    }
);