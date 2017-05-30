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
        .state('courseView', {
            url: '/courseView?code',
            templateUrl: './components/course/view/course.view.html',
            controller: 'courseViewCtrl as vmCourseView',
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
            controller: 'loginCtrl as vmLogin'
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





