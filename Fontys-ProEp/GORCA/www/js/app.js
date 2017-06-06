// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.controllers' is found in controllers.js
angular.module('GORCA', ['ionic', 'ionic.cloud', 'GORCA.controllers', 'GORCA.serviceAPIS', 'GORCA.dataServices',
  'angularMoment', 'GORCA.events'])

  .run(function($ionicPlatform, $ionicPush, studentService, notificationDataService, notificationService, EventNotification) {
    $ionicPlatform.ready(function() {
      // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
      // for form inputs)
      if (window.cordova && window.cordova.plugins.Keyboard) {
        cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
        cordova.plugins.Keyboard.disableScroll(true);

      }
      if (window.StatusBar) {
        // org.apache.cordova.statusbar required
        StatusBar.styleDefault();
      }

      $ionicPlatform.on("resume", function(event) {
        var lastID = notificationDataService.lastNotificationID;
        if (lastID == -1) {
          lastID = 0;
        }
        notificationService.getAllNotificationsBefore(lastID)
          .then(function (response) {
            if (response.data != "") {
              notificationDataService.lastNotificationID = response.data[0].id;
              EventNotification.notifyOnNewNotifications(response.data);
            }
          }, function (error) {
            alert(angular.toJson(error));
          });
      });

      //registering for push notifications
      //for now here, must be after user logs in
      $ionicPush.register()
        .then(function(t) {
          return $ionicPush.saveToken(t);
        }).then(function(t) {
          studentService.addPushNotificationToken(t.token);
      });
    });
  })

  .config(function ($ionicCloudProvider) {
    $ionicCloudProvider.init({
      "core": {
        "app_id": "09e5cf3c"
      },
      "push": {
        "sender_id": "122739110109",
        "pluginConfig": {
          "ios": {
            "badge": true,
            "sound": true
          },
          "android": {
            "iconColor": "#343434"
          }
        }
      }
    });
  })

  .config(function ($ionicConfigProvider) {
    $ionicConfigProvider.navBar.alignTitle('center');
  })

  .config(function($stateProvider, $urlRouterProvider) {
    $stateProvider

      .state('app', {
        url: '/app',
        abstract: true,
        views: {
          'mainMenu': {
            templateUrl: 'templates/menu.html',
            controller: 'MenuController',
            controllerAs: 'menuCtrl'
          }
        },
        resolve: {
          notificationsResolve: function (notificationService, $ionicLoading) {

            //show loading icon
            $ionicLoading.show({
              template: 'Loading...'
            });

            return notificationService.getAllNotifications()
                    .then(function (response) {
                      return {notifications: response.data};
                    }, function (error) {
                      console.log('error');
                      //disable loading icon
                      $ionicLoading.hide();
                      alert(angular.toJson(error));
                    })
          },
          notificationsBadgeCountResolve: function (studentService) {
            return studentService.getBadgeCount()
                    .then(function (response) {
                      return {badgeCount: response.data};
                    }, function (error) {
                      //disable loading icon
                      $ionicLoading.hide();
                      alert(angular.toJson(error));
                    })
          }
        }
    })

      .state('login', {
        url: '/login',
        views: {
          'mainMenu': {
            templateUrl: 'templates/login.html'
          }
        }
      })

      .state('app.home', {
        url: '/home',
        views: {
          'menuContent': {
            templateUrl: 'templates/home.html',
            controller: 'HomeController',
            controllerAs: 'homeCtrl'
          }
        }
      })

    .state('app.search', {
      url: '/search',
      views: {
        'menuContent': {
          templateUrl: 'templates/search.html'
        }
      }
    })

    .state('app.browse', {
        url: '/browse',
        views: {
          'menuContent': {
            templateUrl: 'templates/browse.html'
          }
        }
      })
      .state('app.playlists', {
        url: '/playlists',
        views: {
          'menuContent': {
            templateUrl: 'templates/playlists.html',
            controller: 'PlaylistsCtrl'
          }
        }
      })

    .state('app.single', {
      url: '/playlists/:playlistId',
      views: {
        'menuContent': {
          templateUrl: 'templates/playlist.html',
          controller: 'PlaylistCtrl'
        }
      }
    });
    // if none of the above states are matched, use this as the fallback
    $urlRouterProvider.otherwise('/login');
});
