/**
 * Created by Phoenix on 24-May-17.
 */
angular.module("appAdmin").controller("navCtrl", function ($scope, $rootScope, $location) {
    $scope.isActive = function (viewLocation) {
        return viewLocation === $location.path();
    };
});