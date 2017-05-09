'use strict';

/**
 * this service provides all the http requests to back-end related to person Student/Teacher.
 */
angular.module('appServiceAPI').service('personService', function ($http) {

    var self = this;
    var http = 'http://';
    var localhost = "localhost";
    var baseUrl = http + localhost + ':8090/';

    //testing purposes
    self.GetAllStudents = function () {
        return self.students =
            [
                {
                    "StudentNr" : "2658974",
                    "Name" : "Peter"
                },

                {
                    "StudentNr" : "7896542",
                    "Name" : "Jan"
                },
                {
                    "StudentNr" : "1234567",
                    "Name" : "Becky"
                }
            ];
        //return $http.get(baseUrl + "students");//guessing its students//backend student service needed
    };
});