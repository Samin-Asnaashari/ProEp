'use strict';

angular.module('GORCA.serviceAPIS').service('notificationService', function ($http) {

  var self = this;
  var http = 'http://';
  var ipAddress = "localhost";
  var baseUrl = http + ipAddress + ':8090/notifications';

  self.getAllNotifications = function () {
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
