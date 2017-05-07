'use strict';

angular.module('appComponent.textEditor').directive('textEditor', function () {
    return {
        restrict: 'E',
        templateUrl: './components/text.editor/editor.view.html',
        controller: 'textEditorCtrl',
        controllerAs: 'vmTextEditor',
        scope: {
            element: '='
        }
    };
});