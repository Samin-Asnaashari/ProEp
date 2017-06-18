
'use strict';

angular.module('GORCA.serviceAPIS').service('loginService', function ($http, $window) {

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
      url: baseUrl + '/doStudentLogin',
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
    $window.localStorage['token'] = token;
  };

  self.getAuthentication = function () {
    return $window.localStorage['token'] || false;
  };

  self.DeleteAuthenticationCookie = function () {
    $http.defaults.headers.common.Authorization = "";
    $window.localStorage.removeItem('token');
  };
});
