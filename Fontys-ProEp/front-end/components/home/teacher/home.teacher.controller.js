'use strict';

angular.module('appComponent.homeTeacher').controller('teacherHomeCtrl', function (coursesResolve, $state) {

    var vm = this;
    vm.courses = coursesResolve.courses;

    vm.goToCourseView = function (course) {
        $state.go('courseView', {course: course})
    };


});
