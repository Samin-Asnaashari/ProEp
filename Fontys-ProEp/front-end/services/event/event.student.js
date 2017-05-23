'use strict';

/**
 * this module provides all the events related to student OBJ.
 * for each events there is a subscription method and notifyOn method.
 */
angular.module('appServiceEvent').factory('EventStudent', function ($rootScope) {
    return {

        /*************************************************************
         * Event: ADD / Remove Student From Student List
         *************************************************************/

        subscribeOnAStudentAdded: function (scope, callback) {
            var handler = $rootScope.$on('EventStudent::onAddOne', callback);
            scope.$on('$destroy', handler);
        },

        notifyOnAStudentAdded: function (student) {
            $rootScope.$emit('EventStudent::onAddOne', {
                student: student
            });
        },

        subscribeOnStudentsAdded: function (scope, callback) {
            var handler = $rootScope.$on('EventStudent::onAddMany', callback);
            scope.$on('$destroy', handler);
        },

        notifyOnStudentsAdded: function (students) {
            $rootScope.$emit('EventStudent::onAddMany', {
                students: students
            });
        },

        subscribeOnAStudentRemoved: function (scope, callback) {
            var handler = $rootScope.$on('EventStudent::onRemoveOne', callback);
            scope.$on('$destroy', handler);
        },

        notifyOnAStudentRemoved: function (pcn) {
            $rootScope.$emit('EventStudent::onRemoveOne', {
                pcn: pcn
            });
        },

        subscribeOnStudentsRemoved: function (scope, callback) {
            var handler = $rootScope.$on('EventStudent::onRemoveMany', callback);
            scope.$on('$destroy', handler);
        },

        notifyOnStudentsRemoved: function (students) {
            $rootScope.$emit('EventStudent::onRemoveMany', {
                students: students
            });
        }
    };
});