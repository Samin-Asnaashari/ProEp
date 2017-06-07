/**
 * Created by Phoenix on 24-May-17.
 */
angular.module("appTeacher").controller("navCtrl", function ($scope, $rootScope, $location /**, loginService**/) {
    $scope.isActive = function (viewLocation) {
        return viewLocation === $location.path();
    };

    /**$scope.isLoggedIn = function () {
        if(loginService.getAuthentication()){
            return true;
        }
        else{
            return false;
        }
    };**/
});