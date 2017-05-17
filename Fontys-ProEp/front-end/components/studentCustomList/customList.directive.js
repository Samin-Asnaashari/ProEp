'use strict';

angular.module('appComponent.list').directive('customList', function () {
        return {
            restrict: "E",
            scope: {
                list: '=',
                action: '='
            },
            templateUrl: "./components/studentCustomList/customList.html",
            controller: 'customListCtrl',
            controllerAs: 'vmCustomList'
        }
    }
);