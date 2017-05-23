'use strict';

/**
 * this module provides all the events related to course OBJ.
 * for each events there is a subscription method and notifyOn method.
 */
angular.module('appServiceEvent').factory('EventCourse', function ($rootScope) {
    return {

        /*************************************************************
         * Event: Remove Course From Course List
         *************************************************************/

        subscribeOnCourseDeleted: function (scope, callback) {
            var handler = $rootScope.$on('EventCourse::onDelete', callback);
            scope.$on('$destroy', handler);
        },

        notifyOnCourseDeleted: function (course) {
            $rootScope.$emit('EventCourse::onDelete', {
                course: course
            });
        }
    };
});