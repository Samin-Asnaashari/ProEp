/**
 * Route configuration
 */
angular.module('appAdmin').config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/home');
    $stateProvider
        .state('home', {
            url: '/home',
            templateUrl: './components/home/admin/home.admin.html',
            controller: 'homeCtrl as vmHome'
            // resolve: {
            //     coursesResolve: function (courseService) {
            //         var courses = [];
            //         //TODO Resolve list of courses
            //     }
            // }
        })
        .state('courseEdit', {
            url: '/admin/course?code',
            templateUrl: './components/course/edit/course.edit.html',
            controller: 'courseEditCtrl as vmCourseEdit',
            resolve: {
                courseResolve: function ($state, $stateParams, courseService) {
                    return courseService.findOneCourse($stateParams.code)
                        .then(function (response) {
                            return {course: response.data};
                        }, function (error) {
                            $state.go('home');
                        });
                }
            }
        })
        .state('courses', {
            url: '/courses',
            templateUrl: './components/course/view/course.view.html',
            controller: 'courseViewCtrl as vmCourseView',
            resolve: {
                courseResolve: function ($state, $stateParams, courseService) {
                    return courseService.findAllCourses()
                        .then(function (response) {
                            return {courses: response.data};
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
                 studentsResolve: function ($state, $stateParams, personService) {
                    return personService.GetAllStudents()
                        .then(function (response) {
                            return {student: response.data};
                    }, function (error) {
                        $state.go('home');
                 });
             }
             }
         });
});





