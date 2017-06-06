angular.module('GORCA.controllers', [])

  .controller('MenuController', function(notificationsResolve, notificationsBadgeCountResolve, notificationService,
                                         $ionicLoading, $scope, EventNotification, notificationDataService, studentService) {

    // With the new view caching in Ionic, Controllers are only called
    // when they are recreated or on app start, instead of every page change.
    // To listen for when this page is active (for example, to refresh data),
    // listen for the $ionicView.enter event:
    // $scope.$on('$ionicView.enter', function(e) {
    //   alert("MenuController entered");
    // });

    var jnjnjnj = "jjjjjj";
    var vm = this;

    vm.notifications = notificationsResolve.notifications;

    vm.moreDataCanBeLoaded = true;

    if(vm.notifications.length > 0) {
      notificationDataService.lastNotificationID = vm.notifications[0].id;
    }
    else {
      vm.moreDataCanBeLoaded = false;
      $scope.$broadcast('scroll.infiniteScrollComplete');
    }

    vm.badgeAvailable = false;

    vm.amountOfBadges = notificationsBadgeCountResolve.badgeCount;

    if(vm.amountOfBadges > 0) {
      vm.badgeAvailable = true;
    }

    vm.removeBadge = function () {
      if(vm.badgeAvailable){
        vm.badgeAvailable = false;
        studentService.clearBadgeCount();
      }
    };

    vm.typeOfNotificationClass = function () {
      if(vm.badgeAvailable)
        return "ion-android-notifications";
      else
        return "ion-android-notifications-none";
    };

    vm.setNotificationStatus = function (notification) {
      if(notification.status === "UNREAD"){
        notification.status = "READ";
        notificationService.setNotificationStatus(notification.id);
        vm.removeBadge();
      }
    };

    //event for receiving push notifications
    $scope.$on('cloud:push:notification', function(event, data) {
      if(data.message.raw.additionalData.foreground) {
        notificationService.getAllNotificationsBefore(notificationDataService.lastNotificationID)
          .then(function (response) {
            if (response.data != "") {
              notificationDataService.lastNotificationID = response.data[0].id;
              vm.notifications = response.data.concat(vm.notifications);
              vm.updateNotificationGui();
            }
          }, function (error) {
            alert(angular.toJson(error));
          });
      }
    });

    vm.updateNotificationGui = function () {
      studentService.getBadgeCount()
        .then(function (response) {
          if(response.data != ""){
            if(response.data != 0){
              vm.amountOfBadges = response.data;
              vm.badgeAvailable = true;
            }
          }
        }, function (error) {
          alert(angular.toJson(error));
        });
    };

    EventNotification.subscribeOnNewNotifications($scope, function (event, data) {
      vm.notifications = data.notifications.concat(vm.notifications);
      vm.updateNotificationGui();
    });

    vm.loadMoreNotifications = function () {
      notificationService.getAllNotificationsAfter(vm.notifications[vm.notifications.length - 1].id)
        .then(function (response) {
          if (response.data != "") {
            vm.notifications = vm.notifications.concat(response.data);
            $scope.$broadcast('scroll.infiniteScrollComplete');
            return;
          }
          else {
            vm.moreDataCanBeLoaded = false;
            $scope.$broadcast('scroll.infiniteScrollComplete');
          }
        }, function (error) {
          $scope.$broadcast('scroll.infiniteScrollComplete');
          alert(angular.toJson(error));
        });
    };

    //NOT WORKING CORRECTLY PHYSICAL DEVICE CHECK IF YOU CAN FIX IT
    // vm.loadNewerNotifications = function () {
    //   // $timeout(function () {
    //   //   console.log("ffff");
    //   //   $scope.$broadcast('scroll.refreshComplete');
    //   // }, 5000);
    //   var notificationID = -1;
    //   if(vm.notifications.length > 0) {
    //     notificationID = vm.notifications[0].id;
    //   }
    //   else {
    //     notificationID = 0;
    //   }
    //   notificationService.getAllNotificationsBefore(notificationID)
    //     .then(function (response) {
    //       if(response.data != "") {
    //         $scope.$broadcast('scroll.refreshComplete');
    //         vm.notifications = response.data.concat(vm.notifications);
    //         notificationService.setNotificationsStatus();
    //         notificationDataService.setLastNotificationID(vm.notifications[0].id);
    //       }
    //       else{
    //         $scope.$broadcast('scroll.refreshComplete');
    //       }
    //     }, function (error) {
    //       alert(angular.toJson(error));
    //     });
    // };

    //disable loading icon
    $ionicLoading.hide();
  })

  .controller('HomeController', function () {
    var vm = this;
    vm.currentDate = new Date();
  })

  .controller('PlaylistsCtrl', function($scope) {
    $scope.playlists = [
      { title: 'Reggae', id: 1 },
      { title: 'Chill', id: 2 },
      { title: 'Dubstep', id: 3 },
      { title: 'Indie', id: 4 },
      { title: 'Rap', id: 5 },
      { title: 'Cowbell', id: 6 }
    ];
  })

  .controller('PlaylistCtrl', function($scope, $stateParams) {
  });
