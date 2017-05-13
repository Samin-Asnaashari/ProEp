'use strict';

angular.module('appComponent.textEditor').controller('textEditorCtrl', function ($scope, EventCourseEdit) {

    var vm = this;
    vm.placeholder = "NO Description Yet...";
    vm.editable = false;
    vm.hover = false;

    vm.element = angular.copy($scope.element);

    /* Functions - Methods */

    vm.edit = function () {
        vm.editable = true;
    };

    vm.close = function () {
        vm.hover = false;
        vm.editable = false;
        EventCourseEdit.notifyOnDescriptionChange(vm.element);
    };

    /**
     * Check if the content is empty/undefined/null
     * @returns {boolean}
     */
    vm.isNullOrEmptyOrUndefined = function () {
        return !vm.element;
    };
});