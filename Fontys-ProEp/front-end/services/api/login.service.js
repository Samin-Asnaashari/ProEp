angular.module('appServiceAPI').service('loginService', function ($http, $cookies) {

    var self = this;
    var http = 'http://';
    var localhost = "localhost";
    var baseUrl = http + localhost + ':8090/login';

    self.logout = function () {
        return $http.post(http + localhost + ":8090/logout");
    };
    
    self.login = function (newLogin, loginApp) {
        return $http({
            method: 'GET',
            url: baseUrl + '/do' + loginApp + 'Login',
            headers: {
                'Authorization': 'Basic ' + btoa(newLogin.pcn + ':' + newLogin.password)
            }
        });
    };

    self.SetHeaderAuthentication = function (loginApp) {
        var cookieDetails = self.getAuthentication(loginApp);
        if(cookieDetails){
            $http.defaults.headers.common.Authorization = cookieDetails;
            return true;
        }
        else {
            return false;
        }
    };

    self.setAuthentication = function (token, loginApp) {
        $http.defaults.headers.common.Authorization = token;
        $cookies.putObject(loginApp + 'Token', token);
    };

    self.getAuthentication = function (loginApp) {
        return $cookies.getObject(loginApp + 'Token');
    };

    self.DeleteAuthenticationCookie = function (loginApp) {
        $http.defaults.headers.common.Authorization = "";
        $cookies.remove(loginApp + 'Token');
    };
});