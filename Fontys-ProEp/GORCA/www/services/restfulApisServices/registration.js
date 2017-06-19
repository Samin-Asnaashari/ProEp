'use strict';

angular.module('GORCA.serviceAPIS').service('registrationService', function ($http) {

  var self = this;
  var http = 'http://';
  var ipAddress = "localhost";
  var baseUrl = http + ipAddress + ':8090/registrations';

  self.createRegistration = function (courseCode) {
    return $http.post(baseUrl + "/" +courseCode);
  };

  self.GetAllRegistrationsExceptAcceptedOnes = function () {
    return $http.get(baseUrl + "/exceptAcceptedOnes");
  };

  self.dropRegistration = function(courseCode) {
    return $http.get(baseUrl + '/drop/' + courseCode);
  };

  self.cancelRegistration = function(courseCode) {
    return $http.delete(baseUrl + '/cancelRegistration/' + courseCode);
  }
});
