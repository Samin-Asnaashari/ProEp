/**
 * Created by Phoenix on 18-Jun-17.
 */
angular.module('appComponent.notifications')
    .controller('notificationCtrl', function ($filter, $state, $scope, notificationsResolve, notificationService, courseService) {

    var vm = this;
    vm.notifications = notificationsResolve.notifications;

    vm.removeBadge = function () {
        if ($scope.amountOfBadges > 0) {
            $scope.amountOfBadges = 0;
            teacherService.clearBadgeCount();
        }
    };

    vm.setNotificationStatus = function (notification) {
        if (notification.status === "UNREAD") {
            notification.status = "READ";
            notificationService.setNotificationStatus(notification.id);
            vm.removeBadge();
        }
        $scope.show = false;
        if(notification.type === "DELETION") {
            $state.go('home');
        }
        else {
            var courses = courseService.getCourses();
            if(courses === null) {
                courseService.getAllCourses()
                    .then(function (response) {
                        var course = $filter("filter")(response.data, {code:notification.courseCode});
                        if(course.length === 1) {
                            $state.go('courseView', {course: course[0], code: course[0].code});
                        }
                        else {
                            console.log("course not found");
                            //notification
                        }
                    }, function (error) {
                        console.log(angular.toJson(error));
                    });
                return;
            }
            var course = $filter("filter")(courseService.getCourses(), {code:notification.courseCode});
            if(course.length === 1) {
                console.log(course[0]);
                $state.go('courseView', {course: course[0], code: course[0].code});
            }
            else {
                console.log("course not found");
            }
        }
    };

});