/**
 * Created by Agnes on 30-5-2017.
 */
'use strict';

angular.module('appComponent.homeTeacher').controller('teacherHomeCtrl', function ($scope, teachersResolve, teacherService) {

    var vm = this;
    vm.teacher = teachersResolve.teacher;
    vm.courses = vm.teacher.courses;

});