/**
 * textAngular configuration
 */
angular.module('appComponent.textEditor').config(function ($provide) {
    $provide.decorator('taOptions', ['taRegisterTool', '$delegate', function (taRegisterTool, taOptions) {

        taOptions.classes = {
            focussed: 'focussed',
            toolbar: 'btn-toolbar',
            toolbarGroup: 'btn-group',
            toolbarButton: 'btn btn-primary',
            toolbarButtonActive: 'active',
            disabled: 'disabled',
            textEditor: 'form-control',
            htmlEditor: 'form-control'
        };

        taOptions.toolbar = [
            ['h1', 'h2', 'h3', 'h4', 'h5', 'h6', 'p'],
            ['bold', 'italics', 'underline', 'strikeThrough'],
            ['ul', 'ol'],
            ['insertLink'],
            ['justifyLeft', 'justifyCenter', 'justifyRight', 'justifyFull']
        ];

        return taOptions;
    }]);
});