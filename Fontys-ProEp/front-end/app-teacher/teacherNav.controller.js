'use strict';

angular.module("appTeacher").controller("navCtrl", function (Notification, $filter, $location, loginService, $scope, $window, notificationService, teacherService, $state, courseService) {

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
                Notification.error("Error getting notification count");
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

    $scope.goToAllNotifications = function () {
      $scope.show = false;
      $state.go('notifications');
    };

    $scope.removeBadge = function () {
        //if ($scope.amountOfBadges > 0) {
            $scope.amountOfBadges = 0;
            teacherService.clearBadgeCount();
        //}
    };

    $scope.setNotificationStatus = function (notification) {
        if (notification.status === "UNREAD") {
            notification.status = "READ";
            notificationService.setNotificationStatus(notification.id);
            $scope.removeBadge();
        }
        $scope.show = false;
        if(notification.type === "DELETED") {
            if($state.current.name === 'home'){
                $state.go($state.current, {}, {reload: true}); //second parameter is for $stateParams
            }
            else {
                $state.go('home');
            }
        }
        else {
            var courses = courseService.getCourses();
            if(courses === null) {
                courseService.getAllCourses()
                    .then(function (response) {
                        var course = $filter("filter")(response.data, {code:notification.courseCode});
                        if(course.length === 1) {
                            if($state.current.name === 'courseView'){
                                $state.go($state.current, {course: course[0], code: course[0].code}, {reload: true}); //second parameter is for $stateParams
                            }
                            else {
                                $state.go('courseView', {course: course[0], code: course[0].code});
                            }
                        }
                        else {
                            console.log("course not found");
                            Notification.error("Error going to course details");
                        }
                    }, function (error) {
                        Notification.error("Error going to course details");
                        console.log(angular.toJson(error));
                    });
                return;
            }
            var course = $filter("filter")(courseService.getCourses(), {code:notification.courseCode});
            if(course.length === 1) {
                console.log(course[0]);
                if($state.current.name === 'courseView'){
                    $state.go($state.current, {course: course[0], code: course[0].code}, {reload: true}); //second parameter is for $stateParams
                }
                else {
                    $state.go('courseView', {course: course[0], code: course[0].code});
                }
            }
            else {
                console.log("course not found");
                Notification.error("Error going to course details");
            }
        }
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
                    Notification.error("Error getting notifications");
                    console.log(angular.toJson(error));
                    $scope.loadingDone = true;
                });
        }
    };
});