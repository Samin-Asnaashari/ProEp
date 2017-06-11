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
                                acceptedStudents.push(r.student);
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
                                pendingStudents.push(r.student);
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
                                declinedStudents.push(r.student);
                            });
                            return {declinedStudents: declinedStudents};
                        }, function (error) {
                            $state.go('home');
                        });
                }
            }
        });
        // .state('login', {
        //     url: '/login'
        // })
        // .state('notifications', {
        //     url: '',
        //     templateUrl: '',
        //     controller: '',
        //     resolve: {
        //     }
        // });
});





