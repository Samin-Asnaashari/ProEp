/**
 * Route configuration
 */
angular.module('appTeacher').config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/home');
    $stateProvider
        .state('home', {
            url: '/home',
            views: {
                'content': {
                    templateUrl: './components/home/teacher/home.teacher.html',
                    controller: 'teacherHomeCtrl as vmTeacherHome',
                    resolve: {
                        coursesResolve: function (teacherService) {
                            return teacherService.getMyCourses()
                                .then(function (response) {
                                    return {courses: response.data};
                                }, function (error) {
                                    // $state.go('Error');
                                });
                        }
                    }
                }
            }
        })
        .state('courseView', {
            url: '/courseView?code',
            templateUrl: './components/course/view/course.view.html',
            controller: 'courseViewCtrl as vmCourseView',
            resolve: {
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





