/**
 * Created by Merv on 6/8/2017.
 */
'use strict';

angular.module('GORCA.serviceAPIS').service('reviewService', function ($http) {

  var self = this;
  var http = 'http://';
  var ipAddress = "localhost";
  var baseUrl = http + ipAddress + ':8090/reviews';

  self.getAllReviews = function (courseCode) {
    return $http.get(baseUrl + "/" + courseCode);
  };

  self.addReview = function (courseCode, review) {
    return $http.post(baseUrl + "/" + courseCode, review);
  };
});
