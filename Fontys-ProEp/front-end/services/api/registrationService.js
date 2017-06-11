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
        return $http.get(baseUrl + '/status/' + status);
    };

    self.getAllAcceptedRegistrations = function () {
        $http.defaults.headers.common.Authorization = "Basic MzEwMzIzOjEyMw==";
        return $http.get(baseUrl + '/status/ACCEPTED');
    };

    self.getAllPendingRegistrations = function () {
        $http.defaults.headers.common.Authorization = "Basic MzEwMzIzOjEyMw==";
        return $http.get(baseUrl + '/status/PENDING');
    };

    self.getAllDeclinedRegistrations = function () {
        $http.defaults.headers.common.Authorization = "Basic MzEwMzIzOjEyMw==";
        return $http.get(baseUrl + '/status/DECLINE');
    };

});
