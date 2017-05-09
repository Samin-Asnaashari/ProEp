'use strict';

angular.module('appComponent.course').controller('courseEditCtrl', function (courseResolve) {

    var vm = this;
    vm.course = courseResolve;
});