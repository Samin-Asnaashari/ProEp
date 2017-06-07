'use strict';

/**
 * this service provides all the http requests to back-end related to Registration
 */
angular.module('appServiceAPI').service('registrationService', function ($http) {

    var self = this;
    var http = 'http://';
    var localhost = "localhost";
    var baseUrl = http + localhost + ':8090/registrations';

    self.getAllRegistrationsByCourse = function (courseCode) {
        return $http.get(baseUrl + '/' + courseCode);
    };

    self.getAllRegistrationsByStatus = function (status) {
        return $http.get(baseUrl + '/' + status);
    };

    self.getAllAcceptedRegistrations = function () {
        return $http.get(baseUrl + '/ACCEPTED');
    };

    self.getAllPendingRegistrations = function () {
        return $http.get(baseUrl + '/PENDING');
    };

    self.getAllDeclineRegistrations = function () {
        return $http.get(baseUrl + '/DECLINE');
    };

});
