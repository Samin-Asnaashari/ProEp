
'use strict';

angular.module('appComponent.homeTeacher').controller('teacherHomeCtrl', function (coursesResolve, $state, courseService) {
    var vm = this;
    vm.courses = coursesResolve.courses;
    vm.goToCourseView = function (code) {
        console.log(course, "logged");
        vm.goToCourseView(code);
    };

    vm.goToCourseView = function (courseCode) {
        $state.go('courseView', {code: courseCode})
    };


});
