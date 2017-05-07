'use strict';

angular.module('appComponent.resize').directive('resizeWindow', function ($window, EventResizeWindow) {
    return {
        link: function (scope) {
            function onResize(event) {
                EventResizeWindow.notifyOnResize();
            }

            // Trigger function on resize
            // TODO: Maybe don't have a event service, but directly use the on 'resize' event
            angular.element($window).on('resize', onResize);
        }
    }
});