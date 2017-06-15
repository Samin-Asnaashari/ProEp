/**
 * Created by Merv on 6/14/2017.
 */

angular.module('appComponent.login').directive('loginPage', function () {
        return {
            restrict: "E",
            scope: {
                loginApp: '@'
            },
            templateUrl: "./components/login/login.html",
            controller: 'loginCtrl',
            controllerAs: 'vmLogin'
        };
    }
);