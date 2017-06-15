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

    self.getAllAcceptedRegistrations = function (courseCode) {
        return $http.get(baseUrl + '/status/ACCEPTED/' + courseCode);
    };

    self.getAllPendingRegistrations = function (courseCode) {
        return $http.get(baseUrl + '/status/PENDING/' + courseCode);
    };

    self.getAllDeclinedRegistrations = function (courseCode) {
        return $http.get(baseUrl + '/status/DECLINE/' + courseCode);
    };

    self.updateRegistration = function (courseCode, studentPcn, status) {
        return $http.put(baseUrl+ '/updateRegistrationStatus/'+ courseCode+ '/' + studentPcn+'/'+status);
    };

});
