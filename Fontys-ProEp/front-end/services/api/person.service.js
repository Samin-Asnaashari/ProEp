'use strict';

/**
 * this service provides all the http requests to back-end related to person Student/Teacher.
 */
angular.module('appServiceAPI').service('personService', function ($http) {

    var self = this;
    var http = 'http://';
    var localhost = "localhost";
    var baseUrl = http + localhost + ':8090/students';

    self.addPerson = function (newPerson) {
        return $http.post(baseUrl + newPerson);
    };

    self.getPerson = function (pcn) {
        return $http.get(baseUrl + '/' + pcn);
    };

    self.updatePerson = function (person) {
        return $http.put(baseUrl, person);
    };

    self.deletePerson = function (pcn) {
        return $http.delete(baseUrl + '/' + pcn);
    };

    //TODO remove testing purposes
    self.GetAllStudents = function () {
        return $http.get(baseUrl);
    };
});