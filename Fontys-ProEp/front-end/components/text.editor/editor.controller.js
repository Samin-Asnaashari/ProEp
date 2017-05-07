'use strict';

angular.module('appComponent.textEditor').controller('textEditorCtrl', function ($scope, elementService, Notification) {

    var vm = this;
    vm.placeholder = "Enter text here";
    vm.editable = false;
    vm.hover = false;

    vm.element = angular.copy($scope.element);
    var elementRestore = angular.copy(vm.element);

    /* Functions - Methods */

    vm.edit = function () {
        vm.editable = true;
        elementRestore.content = vm.element.content;
    };

    vm.save = function () {
        vm.hover = false;
        vm.editable = false;

        // Call API
        elementService.updateElement(vm.element)
            .then(function (response) {
                // Success
                $scope.element.content = vm.element.content;
                elementRestore.content = vm.element.content;
                //Notification.success('Saved');
            }, function (error) {
                //Notification.error('Something went wrong!');
            });
    };

    vm.discard = function () {
        vm.hover = false;
        vm.editable = false;
        // Restore content
        vm.element.content = elementRestore.content;
    };

    /**
     * Check if the content is empty/undefined/null
     * @returns {boolean}
     */
    vm.isNullOrEmptyOrUndefined = function () {
        return !vm.element.content;
    };
});