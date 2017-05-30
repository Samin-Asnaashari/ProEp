/**
 * Created by Agnes on 30-5-2017.
 */
'use strict';

angular.module('appComponent.homeTeacher').controller('teacherHomeCtrl', function ($scope, coursesResolve, courseService, $mdDialog, EventCourse) {

    var vm = this;
    vm.courses = coursesResolve.courses;

});