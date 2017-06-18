'use strict';

angular.module('GORCA.serviceAPIS').service('studentService', function ($http) {

  var self = this;
  var http = 'http://';
  var ipAddress = "localhost";
  var baseUrl = http + ipAddress + ':8090/students';

  self.addPushNotificationToken = function (pushNotificationToken) {
    return $http.put(baseUrl + "/addPushNotificationToken/" + pushNotificationToken);
  };

  self.getBadgeCount = function () {
    return $http.get(baseUrl + "/getBadgeCount");
  };

  self.clearBadgeCount = function () {
    return $http.put(baseUrl + "/clearBadgeCount");
  };
});
