/**
 * Route configuration
 */
angular.module('appTeacher').config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/home');
    $stateProvider
        .state('home', {
            url: '/home',
            templateUrl: './components/home/teacher/home.teacher.html',
            controller: 'teacherHomeCtrl as vmTeacherHome',
            resolve: {
                coursesResolve: function (teacherService, Notification) {
                    return teacherService.getMyCourses()
                        .then(function (response) {
                            return {courses: response.data};
                        }, function (error) {
                            console.log(error.data);
                            Notification.error("Error getting your courses");
                        });
                }
            }
        })
        .state('courseView', {
            url: '/courseView?code',
            templateUrl: './components/course/view/course.view.html',
            controller: 'courseViewCtrl as vmCourseView',
            params: {
                course: null
            },
            resolve: {
            //     courseResolve: function ($state, $stateParams, courseService /*, loginService*/) {
            //         //if(loginService.SetHeaderAuthentication()) {
            //             return courseService.getCourse($stateParams.code)
            //                 .then(function (response) {
            //                    // response.data.regStartDate = moment(response.data.regStartDate).format("LL LT");
            //                    // response.data.regEndDate = moment(response.data.regEndDate).format("LL LT");
            //                     return {course: response.data};
            //                 }, function (error) {
            //                     $state.go('home');
            //                 });
            //       //  }
            //        // else {
            //        //     return $state.go('login');
            //        // }
            //     },
                acceptedRegistrationsResolve: function ($state, registrationService, $stateParams, Notification) {
                    return registrationService.getAllAcceptedRegistrations($stateParams.code)
                        .then(function (response) {
                            var acceptedStudents = [];
                            angular.forEach(response.data, function (r) {
                                r.id.student.registrationDate = moment(r.date).format("LL LT");
                                acceptedStudents.push(r.id.student);
                                //acceptedStudents.push(r);
                            });
                            //If other info is needed other than student from registration object you can pass it here as commented example below
                            return {acceptedStudents:  acceptedStudents/*, otherinfo: "whatever"*/};
                        }, function (error) {
                            Notification.error("Error loading accepted students");
                        });
                },
                pendingRegistrationsResolve: function ($state, registrationService, $stateParams, Notification) {
                    return registrationService.getAllPendingRegistrations($stateParams.code)
                        .then(function (response) {
                            var pendingStudents = [];
                            angular.forEach(response.data, function (r) {
                                r.id.student.registrationDate = moment(r.date).format("LL LT");
                                pendingStudents.push(r.id.student);
                            });
                            return {pendingStudents: pendingStudents};
                        }, function (error) {
                            Notification.error("Error loading pending students");
                        });
                },
                declinedRegistrationsResolve: function ($state, registrationService, $stateParams, Notification) {
                    return registrationService.getAllDeclinedRegistrations($stateParams.code)
                        .then(function (response) {
                            var declinedStudents = [];
                            angular.forEach(response.data, function (r) {
                                r.id.student.registrationDate = moment(r.date).format("LL LT");
                                declinedStudents.push(r.id.student);
                            });
                            return {declinedStudents: declinedStudents};
                        }, function (error) {
                            Notification.error("Error loading declined students");
                        });
                }
            }
        })
        .state('login', {
            url: '/login',
            template: '<login-page login-app="Teacher"></login-page>'
        })
        .state('logout', {
            url: '/logout',
            template: '<login-page login-app="Teacher"></login-page>',
            resolve: {
                logOutResolve: function (loginService, Notification, $rootScope, $interval) {
                    return loginService.logout()
                        .then(function (response) {
                            loginService.DeleteAuthenticationCookie("Teacher");
                            $interval.cancel($rootScope.notificationLoop);
                        }, function (error) {
                            Notification.error("Error logging out");
                            console.log("Error");
                            console.log(error);
                        });
                }
            }
        })
        .state('notifications', {
            url: '/notifications',
            templateUrl: './components/notifications/notifications.html',
            controller: 'notificationCtrl as vmNotifications',
            resolve: {
                notificationsResolve: function(notificationService, Notification) {
                    return notificationService.getAllNotifications()
                        .then(function (response) {
                            return {notifications: response.data};
                        }, function (error) {
                            Notification.error("Error loading notifications");
                            console.log("Error");
                            console.log(error);
                        });
                }
            }
        });
});





