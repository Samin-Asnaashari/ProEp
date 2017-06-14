'use strict';

angular.module('GORCA.serviceAPIS').service('registrationService', function ($http) {

  var self = this;
  var http = 'http://';
  //192.168.178.24 (your own ip) for testing on emulator or android device
  var ipAddress = "localhost"; /*145.93.136.163*/
  var baseUrl = http + ipAddress + ':8090/registrations';

  self.createRegistration = function (courseCode) {
    return $http.post(baseUrl + "/" +courseCode);
  };

  self.GetAllRegistrationsExceptAcceptedOnes = function () {
    return $http.get(baseUrl + "/exceptAcceptedOnes");
  };
  
  self.GetAllRegistrationsByStudent = function () {
    return $http.get(baseUrl + "/forStudent/{pcn}");
  };

  self.dropRegistration = function(course) {
    return $http.get(baseUrl + '/drop/' + course);
  }
});
