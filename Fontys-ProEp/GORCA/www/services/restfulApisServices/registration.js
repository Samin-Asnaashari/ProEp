'use strict';

angular.module('GORCA.serviceAPIS').service('registrationService', function ($http) {

  var self = this;
  var http = 'http://';
  //192.168.178.24 (your own ip) for testing on emulator or android device
  var ipAddress = "192.168.178.24"; /*145.93.136.163*/
  var baseUrl = http + ipAddress + ':8090/registrations';
  var accepted = '/accepted/pcn/';
  var acceptedAsCourses = '/ascourses' + accepted;

  self.createRegistration = function (registration) {
    return $http.post(baseUrl, registration);
  };

  self.getAcceptedRegistrations = function (pcn) {
    return $http.get(baseUrl + accepted + pcn);
  };

  self.getAcceptedRegistrationsAsCourses = function (pcn) {
    return $http.get(baseUrl + acceptedAsCourses + pcn);
  };

  self.GetAllRegistrationsExceptAcceptedOnes = function () {
    return $http.get(baseUrl + "/exceptAcceptedOnes");
  };

  self.GetAllRegistrationsByStudent = function () {
    return $http.get(baseUrl + "/forStudent/{pcn}");
  };
});
