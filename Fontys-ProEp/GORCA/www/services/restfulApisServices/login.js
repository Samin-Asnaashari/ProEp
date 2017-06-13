/**
 * Created by Merv on 6/11/2017.
 */
'use strict';

angular.module('GORCA.serviceAPIS').service('loginService', function ($http, $cookies) {

  var self = this;
  var http = 'http://';
  var localhost = "145.93.43.252";
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
    $cookies.putObject('token', token);
  };

  self.getAuthentication = function () {
    return $cookies.getObject('token');
  };

  self.DeleteAuthenticationCookie = function () {
    $http.defaults.headers.common.Authorization = "";
    $cookies.remove('token');
  };
});
