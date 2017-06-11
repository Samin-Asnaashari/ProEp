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
            resolve: {
                courseResolve: function ($state, $stateParams, courseService /*, loginService*/) {
                    //if(loginService.SetHeaderAuthentication()) {
                        return courseService.getCourse($stateParams.code)
                            .then(function (response) {
                               // response.data.regStartDate = moment(response.data.regStartDate).format("LL LT");
                               // response.data.regEndDate = moment(response.data.regEndDate).format("LL LT");
                                return {course: response.data};
                            }, function (error) {
                                $state.go('home');
                            });
                  //  }
                   // else {
                   //     return $state.go('login');
                   // }
                },
                acceptedRegistrationsResolve: function ($state, registrationService) {
                    return registrationService.getAllAcceptedRegistrations(status)
                        .then(function (response) {
                            return {acceptedRegistrations: response.data};
                        }, function (error) {
                            $state.go('home');
                        });
                },
                pendingRegistrationsResolve: function ($state, registrationService) {
                    return registrationService.getAllPendingRegistrations(status)
                        .then(function (response) {
                            return {pendingRegistrations: response.data};
                        }, function (error) {
                            $state.go('home');
                        });
                },
                declineRegistrationsResolve: function ($state, registrationService) {
                    return registrationService.getAllDeclineRegistrations(status)
                        .then(function (response) {
                            return {declinedRegistrations: response.data};
                        }, function (error) {
                            $state.go('home');
                        });
                }
            }
        })
        .state('login', {
            url: '/login'
        })
        .state('notifications', {
            url: '',
            templateUrl: '',
            controller: '',
            resolve: {
            }
        });
});





