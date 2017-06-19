'use strict';

angular.module('GORCA.serviceAPIS').service('courseService', function ($http) {

  var self = this;
  var http = 'http://';
  var ipAddress = "localhost";
  var baseUrl = http + ipAddress + ':8090/courses';

  // self.acceptedElectedCourses = null;
  // self.exceptAcceptedCourses = null;

  // self.getAcceptedElectedCourses = function () {
  //   return self.acceptedElectedCourses;
  // };
  //
  // self.setAcceptedElectedCourses = function (courses) {
  //   self.acceptedElectedCourses = courses;
  // };
  //
  // self.setExceptAcceptedCourses = function (courses) {
  //   self.exceptAcceptedCourses = courses;
  // };
  //
  // self.getExceptAcceptedCourses = function () {
  //   return self.exceptAcceptedCourses;
  // };

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

  self.GetAllNotAppliedElectiveCoursesForStudent = function () {
    return $http.get(baseUrl + "/elective/forApply");
  };
});
