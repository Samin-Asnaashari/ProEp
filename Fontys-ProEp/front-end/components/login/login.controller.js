/**
 * Created by Phoenix on 17-May-17.
 */

angular.module('appComponent.login').controller('loginCtrl', function ($state, $scope, loginService, loginResolve) {

    var vm = this;

    console.log("WAWAWAWAWA");
    sessionStorage.loginApp = angular.toJson(loginResolve.loginApp);
    console.log("qqqqqqqqqqqq: " + angular.fromJson(sessionStorage.loginApp));
    vm.errorMsg = "";

    vm.SetErrorEmpty = function () {
        vm.errorMsg = "";
    };

    vm.GetError = function () {
        if (vm.errorMsg === "") {
            return false;
        }
        else {
            return true;
        }
    };

    vm.login = function () {
        loginService.login(vm.newLogin)
            .then(function (response) {
                loginService.setAuthentication(response.data.message);
                $state.go('home');
            }, function (error) {
                console.log(error);
                if(error.status === 401){
                    vm.errorMsg = "Bad credentials";
                }
                else if(error.status === 403){
                    vm.errorMsg = error.data.message;
                }
            });
    };
});