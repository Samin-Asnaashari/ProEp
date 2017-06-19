/**
 * Created by Phoenix on 18-Jun-17.
 */
angular.module('appComponent.notifications')
    .controller('notificationCtrl', function (teacherService, $filter, $state, $scope, notificationsResolve, notificationService, courseService) {

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
            $scope.removeBadge();
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
                $state.go('courseView', {course: course[0], code: course[0].code});
            }
            else {
                console.log("course not found");
                Notification.error("Error going to course details");
            }
        }
    };

});