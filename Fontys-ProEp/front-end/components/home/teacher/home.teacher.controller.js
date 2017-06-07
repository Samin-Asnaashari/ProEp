
'use strict';

angular.module('appComponent.homeTeacher').controller('teacherHomeCtrl', function ($scope, teachersResolve, teacherService) {

    var vm = this;
    vm.teacher = teachersResolve.teacher;
    vm.courses = vm.teacher.courses;

});