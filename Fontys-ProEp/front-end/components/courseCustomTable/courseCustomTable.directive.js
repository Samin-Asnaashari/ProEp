'use strict';

angular.module('appComponent.courseTable').directive('courseCustomTable', function () {
        return {
            restrict: "E",
            scope: {
                courseList: '=',
                editAction: '=', /*0 mean is not supports this action, 1 mean is supports this action*/
                deleteAction: '=',
                addAction: '='
            },
            templateUrl: "./components/courseCustomTable/courseCustomTable.html",
            controller: 'courseCustomTableCtrl',
            controllerAs: 'vmCourseCustomTable'
        };
    }
);