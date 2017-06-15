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
                coursesResolve: function (teacherService) {
                    return teacherService.getMyCourses()
                        .then(function (response) {
                            return {courses: response.data};
                        }, function (error) {
                            console.log(error.data);
                            // $state.go('Error');
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
                acceptedRegistrationsResolve: function ($state, registrationService, $stateParams) {
                    return registrationService.getAllAcceptedRegistrations($stateParams.code)
                        .then(function (response) {
                            var acceptedStudents = [];
                            angular.forEach(response.data, function (r) {
                                acceptedStudents.push(r.id.student);
                                //acceptedStudents.push(r);
                            });
                            //If other info is needed other than student from registration object you can pass it here as commented example below
                            return {acceptedStudents:  acceptedStudents/*, otherinfo: "whatever"*/};
                        }, function (error) {
                            $state.go('home');
                        });
                },
                pendingRegistrationsResolve: function ($state, registrationService, $stateParams) {
                    return registrationService.getAllPendingRegistrations($stateParams.code)
                        .then(function (response) {
                            var pendingStudents = [];
                            angular.forEach(response.data, function (r) {
                                pendingStudents.push(r.id.student);
                            });
                            return {pendingStudents: pendingStudents};
                        }, function (error) {
                            $state.go('home');
                        });
                },
                declinedRegistrationsResolve: function ($state, registrationService, $stateParams) {
                    return registrationService.getAllDeclinedRegistrations($stateParams.code)
                        .then(function (response) {
                            var declinedStudents = [];
                            angular.forEach(response.data, function (r) {
                                declinedStudents.push(r.id.student);
                            });
                            return {declinedStudents: declinedStudents};
                        }, function (error) {
                            $state.go('home');
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
                logOutResolve: function (loginService) {
                    return loginService.logout()
                        .then(function (response) {
                            loginService.DeleteAuthenticationCookie("Teacher");
                        }, function (error) {
                            console.log("Error");
                            console.log(error);
                        });
                }
            }
        });
        // .state('notifications', {
        //     url: '',
        //     templateUrl: '',
        //     controller: '',
        //     resolve: {
        //     }
        // });
});





