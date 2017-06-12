'use strict';

angular.module('GORCA.serviceAPIS', []).service('registrationService', function ($http) {

  var self = this;
  var http = 'http://';
  //192.168.178.24 (your own ip) for testing on emulator or android device
  var ipAddress = "192.168.0.115";
  var baseUrl = http + ipAddress + ':8090/registrations';

  self.createRegistration = function (registration) {
    return $http.post(baseUrl, registration);
  };
});
