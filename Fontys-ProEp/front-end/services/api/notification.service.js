/**
 * Created by Merv on 6/16/2017.
 */
'use strict';

/**
 *this service provides all the http requests to back-end related to person.
 */
angular.module('appServiceAPI').service('notificationService', function ($http) {

    var self = this;
    var http = 'http://';
    var localhost = "localhost";
    var baseUrl = http + localhost + ':8090/notifications';

    self.amountOfBadges = 0;

    self.getAmountOfBadges = function () {
      return self.amountOfBadges;
    };

    self.setAmountOfBadges = function (newAmountOfBadges) {
        self.amountOfBadges = newAmountOfBadges;
    };

    self.getAllNotifications = function () {
        return $http.get(baseUrl + "/");
    };

    self.setNotificationStatus = function (notificationID) {
        return $http.put(baseUrl + "/changeStatus/" + notificationID);
    };
});