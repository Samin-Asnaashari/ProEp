'use strict';

/**
 * this service provides all the http requests to back-end related to Student
 */
angular.module('appServiceAPI').service('studentService', function ($http) {

    var self = this;
    var http = 'http://';
    var localhost = "localhost";
    var baseUrl = http + localhost + ':8090/students';

    self.addStudent = function (student) {
        return $http.post(baseUrl, student);
    };

    self.addStudents = function (students) {
        return $http.post(baseUrl + '/addToGORCA', students);
    };

    self.getStudent = function (pcn) {
        return $http.get(baseUrl + '/' + pcn);
    };

    self.getAllStudents = function () {
        return $http.get(baseUrl);
    };

    self.getAllFontysStudents = function () {
        return $http.get(baseUrl + '/fontysStudents');
    };

    self.updateStudent = function (student) {
        return $http.put(baseUrl, student);
    };

    self.deleteStudent = function (pcn) {
        return $http.delete(baseUrl + '/' + pcn);
    };

    self.deleteStudents = function (students) {
        return $http.put(baseUrl + '/deleteStudents', students);
    };
});