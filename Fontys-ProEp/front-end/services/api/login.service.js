/**
 * Created by Phoenix on 17-May-17.
 */
angular.module('appServiceAPI').service('loginService', function ($http) {

    var self = this;
    var http = 'http://';
    var localhost = "localhost";
    var baseUrl = http + localhost + ':8090/login';

    self.login = function (pcn, pass) {
        var data = {pcn: pcn, password: pass};
        return $http.post(baseUrl, data);
    };
});