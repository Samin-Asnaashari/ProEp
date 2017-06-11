'use strict';

angular.module('GORCA.serviceAPIS').service('studentService', function ($http) {

  var self = this;
  var http = 'http://';
  //192.168.178.24 (your own ip) for testing on emulator or android device
  var ipAddress = "145.116.43.195"; /*145.93.136.163*/
  var baseUrl = http + ipAddress + ':8090/students';

  self.addPushNotificationToken = function (pushNotificationToken) {
    //for now until log in is working
    $http.defaults.headers.common.Authorization = "Basic MzEwMzIzOjEyMw==";
    return $http.put(baseUrl + "/addPushNotificationToken/" + pushNotificationToken);
  };

  self.getBadgeCount = function () {
    return $http.get(baseUrl + "/getBadgeCount");
  };

  self.clearBadgeCount = function () {
    return $http.put(baseUrl + "/clearBadgeCount");
  };
});
