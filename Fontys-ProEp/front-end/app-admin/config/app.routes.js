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
                            // $state.go('Error');
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
                            $state.go('home');
                        });
                }
            }
        })
        .state('login', {
            url: '/login',
            templateUrl: './components/login/login.html',
            controller: 'loginCtrl as vmLogin',
            resolve: {
                loginResolve: function () {
                    return {loginApp: "Admin"};
                }
            }
        })
        .state('logout', {
            url: '/logout',
            templateUrl: './components/login/login.html',
            controller: 'loginCtrl as vmLogin',
            resolve: {
                logOutResolve: function ($state, $stateParams, loginService) {
                    return loginService.logout()
                        .then(function (response) {
                            loginService.DeleteAuthenticationCookie();
                        }, function (error) {
                            console.log("Error");
                            console.log(error);
                        });
                },
                loginResolve: function () {
                    return {loginApp: "Admin"};
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
                                console.log(error);
                                //$state.go('home');
                            });
                }
            }
        });
});





