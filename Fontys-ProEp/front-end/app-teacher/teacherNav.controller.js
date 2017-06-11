/**
 * Created by Phoenix on 24-May-17.
 */
angular.module("appTeacher").controller("navCtrl", function ($location /**, loginService**/) {

    var vm = this;

    vm.isActive = function (viewLocation) {
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