'use strict';

angular.module('appComponent.homeTeacher').controller('teacherHomeCtrl', function (coursesResolve, $state, courseService) {

    var vm = this;
    vm.courses = coursesResolve.courses;
    courseService.setCourses(vm.courses);

    vm.goToCourseView = function (course) {
        $state.go('courseView', {course: course, code: course.code});
    };


});
