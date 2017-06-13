'use strict';

angular.module('GORCA.serviceAPIS').service('courseService', function ($http) {

  var self = this;
  var http = 'http://';
  //192.168.178.24 (your own ip) for testing on emulator or android device
  var ipAddress = "145.93.43.252"; /*145.93.136.163*/
  var baseUrl = http + ipAddress + ':8090/courses';
  var mandatory = '/mandatory/';
  var accepted = '/accepted'

  self.getCourse = function (code) {
    return $http.get(baseUrl + "/" + code);
  };

  self.getAllElectiveCourses = function () {
    return $http.get(baseUrl + "/electives");
  };

  self.getAllMandatoryCourses = function () {
    return $http.get(baseUrl + "/mandatory");
  };

  self.getAllMandatoryCoursesByMajor = function(major) {
    return $http.get(baseUrl + mandatory + major);
  };

  self.getAllAcceptedElectiveCourses = function() {
    return $http.get(baseUrl + accepted);
  };

});
