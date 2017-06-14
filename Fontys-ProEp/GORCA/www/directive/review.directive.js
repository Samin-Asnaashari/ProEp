'use strict';

angular.module('GORCA.Directives', []).directive('reviews', function () {
    return {
      restrict: "E",
      scope: {
        reviewList: '='
      },
      templateUrl: 'templates/review.html',
      controller: 'ReviewController',
      controllerAs: 'reviewCtrl'
    }
  }
);
