/**
 * Created by Phoenix on 24-May-17.
 */
'use strict';

angular.module("appTeacher").controller("navCtrl", function ($location, loginService, $scope, $window, notificationService, teacherService) {

    //var vm = this;

    $scope.amountOfBadges = 0;
    $scope.notificationService = notificationService;
    $scope.$watch('notificationService.getAmountOfBadges()', function(newValue) {
        if(newValue > 0) {
            $scope.amountOfBadges = newValue;
        }
    });

    $scope.isActive = function (viewLocation) {
        return viewLocation === $location.path();
    };

    if(loginService.SetHeaderAuthentication("Teacher")) {
        teacherService.getBadgeCount()
            .then(function (response) {
                $scope.amountOfBadges = response.data;
            }, function (error) {
                console.log(angular.toJson(error));
            });
    }

    $scope.show = false;
    $scope.loadingDone = false;
    $scope.notifications = [];

    $scope.isLoggedIn = function () {
        if(loginService.getAuthentication("Teacher")){
            return true;
        }
        else{
            return false;
        }
    };

    $scope.removeBadge = function () {
        if ($scope.amountOfBadges > 0) {
            $scope.amountOfBadges = 0;
            teacherService.clearBadgeCount();
        }
    };

    $scope.setNotificationStatus = function (notification) {
        if (notification.status === "UNREAD") {
            notification.status = "READ";
            notificationService.setNotificationStatus(notification.id);
            $scope.removeBadge();
        }
        $scope.show = false;
    };

    $scope.getNotifications = function () {
        $window.onclick = null;
        $scope.show = !$scope.show;
        if($scope.show) {
            $scope.loadingDone = false;
            $scope.removeBadge();
            $window.onclick = function (event) {
                $scope.show = false;
                $scope.$apply();
            };
            notificationService.getAllNotifications()
                .then(function (response) {
                    $scope.notifications = response.data;
                    $scope.loadingDone = true;
                }, function (error) {
                   console.log(angular.toJson(error));
                    $scope.loadingDone = true;
                });
        }
    };
});