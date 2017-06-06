/**
 * Created by Merv on 6/3/2017.
 */
'use strict';

angular.module('GORCA.events').factory('EventNotification', function ($rootScope) {
  return {

    subscribeOnNewNotifications: function (scope, callback) {
      var handler = $rootScope.$on('EventNotification::OnNewNotifications', callback);
      scope.$on('$destroy', handler);
    },
    notifyOnNewNotifications: function (notifications) {
      $rootScope.$emit('EventNotification::OnNewNotifications', {
        notifications: notifications
      });
    }

  };
});
