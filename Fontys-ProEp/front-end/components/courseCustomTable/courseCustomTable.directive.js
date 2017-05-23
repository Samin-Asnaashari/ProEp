'use strict';

angular.module('appComponent.courseTable').directive('courseCustomTable', function () {
        return {
            restrict: "E",
            scope: {
                courseList: '=',
                action: '='  //0 determines removing 1 determines adding
            },
            templateUrl: "./components/courseCustomTable/courseCustomTable.html",
            controller: 'courseCustomTableCtrl',
            controllerAs: 'vmCourseCustomTable'
        }
    }
);