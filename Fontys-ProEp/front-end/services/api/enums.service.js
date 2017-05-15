'use strict';

/**
 *this service provides all the http requests to back-end related to person.
 */
angular.module('appServiceAPI').service('enumsService', function ($http) {

    var self = this;
    var http = 'http://';
    var localhost = "localhost";
    var baseUrl = http + localhost + ':8090/enums';

    self.getCourseTypesPossibleValues = function () {
        return $http.get(baseUrl + '/courseTypes');
    };

    self.getMajorsPossibleValues = function () {
        return $http.get(baseUrl + '/majors');
    };
});