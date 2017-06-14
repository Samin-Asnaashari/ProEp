'use strict';

angular.module('GORCA.serviceAPIS').service('courseService', function ($http) {

  var self = this;
  var http = 'http://';
  //192.168.178.24 (your own ip) for testing on emulator or android device
  var ipAddress = "localhost";
  var baseUrl = http + ipAddress + ':8090/courses';

  self.getCourse = function (code) {
    return $http.get(baseUrl + "/" + code);
  };

  self.getAllElectiveCourses = function () {
    return $http.get(baseUrl + "/elective");
  };

  self.getAllMandatoryCourses = function () {
    return $http.get(baseUrl + "/mandatory");
  };

  self.getAllAcceptedElectiveCourses = function () {
    return $http.get(baseUrl + "/accepted");
  };

  self.GetAllElectiveCoursesExceptAcceptedOnes = function () {
    return $http.get(baseUrl + "/elective/exceptAcceptedOnes");
  };
});
