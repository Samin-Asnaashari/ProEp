'use strict';

/**
 *this service provides all the http requests to back-end related to person.
 */
angular.module('appServiceAPI').service('courseService', function ($http) {

    var self = this;
    var http = 'http://';
    var localhost = "localhost";
    var baseUrl = http + localhost + ':8090/courses';

    self.addCourse = function (newCourse) {
        return $http.post(baseUrl + newCourse);
    };

    self.getCourse = function (code) {
        return $http.get(baseUrl + '/' + code);
    };

    self.getAllCourses = function () {
        return $http.get(baseUrl);
    };

    self.updateCourse = function (course) {
        return $http.put(baseUrl, course);
    };

    self.deleteCourse = function (code) {
        return $http.delete(baseUrl, '/' + code);
    };
});