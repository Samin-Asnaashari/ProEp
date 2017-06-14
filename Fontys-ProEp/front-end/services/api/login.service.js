angular.module('appServiceAPI').service('loginService', function ($http, $cookies) {

    var self = this;
    var http = 'http://';
    var localhost = "localhost";
    var baseUrl = http + localhost + ':8090/login';

    self.logout = function () {
        return $http.post(http + localhost + ":8090/logout");
    };
    
    self.login = function (newLogin) {
        return $http({
            method: 'GET',
            url: baseUrl + '/do' + angular.fromJson(sessionStorage.loginApp) + 'Login',
            headers: {
                'Authorization': 'Basic ' + btoa(newLogin.pcn + ':' + newLogin.password)
            }
        });
    };

    self.SetHeaderAuthentication = function () {
        var cookieDetails = self.getAuthentication();
        if(cookieDetails){
            $http.defaults.headers.common.Authorization = cookieDetails;
            return true;
        }
        else {
            return false;
        }
    };

    self.setAuthentication = function (token) {
        $http.defaults.headers.common.Authorization = token;
        $cookies.putObject(angular.fromJson(sessionStorage.loginApp) + 'token', token);
    };

    self.getAuthentication = function () {
        return $cookies.getObject(angular.fromJson(sessionStorage.loginApp) + 'token');
    };

    self.DeleteAuthenticationCookie = function () {
        $http.defaults.headers.common.Authorization = "";
        $cookies.remove(angular.fromJson(sessionStorage.loginApp) + 'token');
    };
});