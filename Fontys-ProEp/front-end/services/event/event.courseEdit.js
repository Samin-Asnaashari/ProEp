'use strict';

/**
 * this module provides all the events related to courseEdit page.
 * for each events there is a subscription method and notifyOn method.
 */
angular.module('appServiceEvent').factory('EventCourseEdit', function ($rootScope) {
    return {

        /*************************************************************
         * Event: Changing Description
         *************************************************************/

        subscribeOnDescriptionChange: function (scope, callback) {
            var handler = $rootScope.$on('EventCourseEdit::onEdit', callback);
            scope.$on('$destroy', handler);
        },

        notifyOnDescriptionChange: function (description) {
            $rootScope.$emit('EventCourseEdit::onEdit', {
                description: description
            });
        }
    };
});