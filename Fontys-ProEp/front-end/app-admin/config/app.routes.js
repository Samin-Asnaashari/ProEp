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
        });
});





