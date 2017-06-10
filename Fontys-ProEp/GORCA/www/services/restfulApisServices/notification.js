/**
 * Created by Merv on 6/2/2017.
 */
'use strict';

angular.module('GORCA.serviceAPIS').service('notificationService', function ($http) {

  var self = this;
  var http = 'http://';
  //192.168.178.24 (your own ip) for testing on emulator or android device
  var ipAddress = "192.168.178.24";
  var baseUrl = http + ipAddress + ':8090/notifications';

  self.getAllNotifications = function () {
    $http.defaults.headers.common.Authorization = "Basic MzEwMzIzOjEyMw==";
    return $http.get(baseUrl);
  };

  self.getAllNotificationsBefore = function (lastNotificationID) {
    return $http.get(baseUrl + "/before/" + lastNotificationID);
  };

  self.getAllNotificationsAfter = function (firstNotificationID) {
    return $http.get(baseUrl + "/after/" + firstNotificationID);
  };

  self.setNotificationStatus = function (notificationID) {
    return $http.put(baseUrl + "/changeStatus/" + notificationID);
  };
});
