'use strict';

/**
 * this service provides all the http requests to back-end related to Teacher
 */
angular.module('appServiceAPI').service('teacherService', function ($http) {

    var self = this;
    var http = 'http://';
    var localhost = "localhost";
    var baseUrl = http + localhost + ':8090/teachers';

    self.addTeacher = function (teacher) {
        return $http.post(baseUrl, teacher);
    };

    self.getTeacher = function (pcn) {
        return $http.get(baseUrl + '/' + pcn);
    };

    self.getAllTeachers = function () {
        return $http.get(baseUrl);
    };

    self.updateTeacher = function (teacher) {
        return $http.put(baseUrl, teacher);
    };

    self.deleteTeacher = function (pcn) {
        return $http.delete(baseUrl + '/' + pcn);
    };
});