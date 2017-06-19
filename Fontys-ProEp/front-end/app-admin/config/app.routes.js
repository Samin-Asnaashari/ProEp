/**
 * Route configuration
 */
angular.module('appAdmin').config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/home');
    $stateProvider
        .state('home', {
            url: '/home',
            templateUrl: './components/home/admin/home.admin.html',
            controller: 'homeCtrl as vmHome',
            resolve: {
                coursesResolve: function ($state, courseService) {
                    return courseService.getAllCourses()
                        .then(function (response) {
                            return {courses: response.data};
                        }, function (error) {
                            Notification.error("Error getting courses");
                        });
                }
            }
        })
        .state('courseEdit', {
            url: '/courseEdit?code',
            templateUrl: './components/course/edit/course.edit.html',
            controller: 'courseEditCtrl as vmCourseEdit',
            resolve: {
                courseResolve: function ($state, $stateParams, courseService) {
                    return courseService.getCourse($stateParams.code)
                        .then(function (response) {
                            response.data.regStartDate = moment(response.data.regStartDate).format("LL LT");
                            response.data.regEndDate = moment(response.data.regEndDate).format("LL LT");
                            return {course: response.data};
                        }, function (error) {
                            Notification.error("Error getting course details");
                        });
                }
            }
        })
        .state('login', {
            url: '/login',
            template: '<login-page login-app="Admin"></login-page>'
        })
        .state('logout', {
            url: '/logout',
            template: '<login-page login-app="Admin"></login-page>',
            resolve: {
                logOutResolve: function (loginService) {
                    return loginService.logout()
                        .then(function (response) {
                            loginService.DeleteAuthenticationCookie("Admin");
                        }, function (error) {
                            Notification.error("Error logging out");
                            console.log("Error");
                            console.log(error);
                        });
                }
            }
        })
        .state('students', {
            url: '/students',
            templateUrl: './components/student/student.html',
            controller: 'studentCtrl as vmStudent',
            resolve: {
                studentsResolve: function (studentService) {
                        return studentService.getAllStudents()
                            .then(function (response) {
                                return {students: response.data};
                            }, function (error) {
                                Notification.error("Error getting students");
                                console.log(error);
                            });
                }
            }
        });
});





