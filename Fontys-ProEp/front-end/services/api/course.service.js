'use strict';

/**
 *this service provides all the http requests to back-end related to person.
 */
angular.module('appServiceAPI').service('courseService', function ($http) {

    var self = this;
    var http = 'http://';
    var localhost = "localhost";
    // var secure = '/secure';
    var baseUrl = http + localhost + ':8090/course';

    self.findOneCourse = function (code) {
        return $http.get(baseUrl + '/' + code);
    };

    self.findAllCourses = function () {
        return $http.get(baseUrl);
    };

    self.updateCourse = function (course) {
        return $http.put(baseUrl , course);
    };

});