/**
 * Created by Merv on 5/13/2017.
 */
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
        return $http.post(baseUrl + student);
    };

    self.getStudent = function (pcn) {
        return $http.get(baseUrl + '/' + pcn);
    };

    self.updateStudent = function (student) {
        return $http.put(baseUrl, student);
    };

    self.deleteStudent = function (pcn) {
        return $http.delete(baseUrl + '/' + pcn);
    };

    self.GetStudents = function () {
        return $http.get(baseUrl);
    };
});