'use strict';

/**
 * this module provides all the events related to window resize.
 * it been used in resize-window component.
 * it is used for managing the size of the components when an image being uploaded.
 * for each events there is a subscription method and notifyOn method.
 */
angular.module('appServiceEvent').factory('EventResizeWindow', function ($rootScope) {
    return {

        /*************************************************************
         * Event: Window resize
         *************************************************************/

        subscribeOnResize: function (scope, callback) {
            var handler = $rootScope.$on('EventResizeWindow::onResize', callback);
            scope.$on('$destroy', handler);
        },

        notifyOnResize: function () {
            $rootScope.$emit('EventResizeWindow::onResize');
        }
    };
});