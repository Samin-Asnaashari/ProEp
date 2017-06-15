/**
 * Created by Phoenix on 17-May-17.
 */

angular.module('appComponent.login').controller('loginCtrl', function ($state, $scope, loginService) {

    var vm = this;

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
        loginService.login(vm.newLogin, $scope.loginApp)
            .then(function (response) {
                loginService.setAuthentication(response.data.message, $scope.loginApp);
                $state.go('home');
            }, function (error) {
                if(error.status === 401){
                    vm.errorMsg = "Bad credentials";
                }
                else if(error.status === 403){
                    vm.errorMsg = error.data.message;
                }
            });
    };
});