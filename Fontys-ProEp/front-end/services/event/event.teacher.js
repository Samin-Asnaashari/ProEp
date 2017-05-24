'use strict';

/**
 * this module provides all the events related to teacher OBJ.
 * for each events there is a subscription method and notifyOn method.
 */
angular.module('appServiceEvent').factory('EventTeacher', function ($rootScope) {
    return {

        /*************************************************************
         * Event: Assign teacher to a course
         *************************************************************/

        subscribeOnAddATeacherToCourse: function (scope, callback) {
            var handler = $rootScope.$on('EventTeacher::onAddOne', callback);
            scope.$on('$destroy', handler);
        },

        notifyOnAddATeacherToCourse: function (teacher) {
            $rootScope.$emit('EventTeacher::onAddOne', {
                teacher: teacher
            });
        },

        subscribeOnAddTeachersToCourse: function (scope, callback) {
            var handler = $rootScope.$on('EventTeacher::onAddMany', callback);
            scope.$on('$destroy', handler);
        },

        notifyOnAddTeachersToCourse: function (teachers) {
            $rootScope.$emit('EventTeacher::onAddMany', {
                teachers: teachers
            });
        }
    };
});