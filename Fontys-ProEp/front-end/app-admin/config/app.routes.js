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
                coursesResolve: function ($state, $stateParams, courseService) {
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
                            return {course: response.data};
                        }, function (error) {
                            $state.go('home');
                        });
                }
            }
        })
        .state('students', {
            url: '/students',
            templateUrl: './components/student/student.html',
            controller: 'studentCtrl as vmStudent',
            resolve: {
                studentsResolve: function ($state, $stateParams, studentService) {
                    return studentService.getAllStudents()
                        .then(function (response) {
                            return {students: response.data};
                        }, function (error) {
                            $state.go('home');
                        });
                }
            }
        });
});





