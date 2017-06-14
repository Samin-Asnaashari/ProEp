angular.module('GORCA.controllers', ['GORCA.Directives'])

  .controller('MenuController', function (notificationsResolve, notificationsBadgeCountResolve, notificationService,
                                          $ionicLoading, $scope, EventNotification, notificationDataService, studentService, loginService, $ionicHistory, $state) {

    // With the new view caching in Ionic, Controllers are only called
    // when they are recreated or on app start, instead of every page change.
    // To listen for when this page is active (for example, to refresh data),
    // listen for the $ionicView.enter event:
    // $scope.$on('$ionicView.enter', function(e) {
    //   alert("MenuController entered");
    // });

    var vm = this;

    vm.notifications = notificationsResolve.notifications;

    vm.moreDataCanBeLoaded = true;

    vm.logout = function () {
      loginService.logout()
        .then(function (response) {
          loginService.DeleteAuthenticationCookie();
          $ionicHistory.nextViewOptions({
            historyRoot: true
          });
          $state.go('login');
        }, function (error) {
          alert(angular.toJson(error));
        })
    };

    if (vm.notifications.length > 0) {
      notificationDataService.lastNotificationID = vm.notifications[0].id;
    }
    else {
      vm.moreDataCanBeLoaded = false;
      $scope.$broadcast('scroll.infiniteScrollComplete');
    }

    vm.badgeAvailable = false;

    vm.amountOfBadges = notificationsBadgeCountResolve.badgeCount;

    if (vm.amountOfBadges > 0) {
      vm.badgeAvailable = true;
    }

    vm.removeBadge = function () {
      if (vm.badgeAvailable) {
        vm.badgeAvailable = false;
        studentService.clearBadgeCount();
      }
    };

    vm.typeOfNotificationClass = function () {
      if (vm.badgeAvailable)
        return "ion-android-notifications";
      else
        return "ion-android-notifications-none";
    };

    vm.setNotificationStatus = function (notification) {
      if (notification.status === "UNREAD") {
        notification.status = "READ";
        notificationService.setNotificationStatus(notification.id);
        vm.removeBadge();
      }
    };

    //event for receiving push notifications
    $scope.$on('cloud:push:notification', function (event, data) {
      if (data.message.raw.additionalData.foreground) {
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
          if (response.data != "") {
            if (response.data != 0) {
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
    //   //   console.log("test");
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

  .controller('ReviewController', function ($ionicLoading, $scope) {
    var vm = this;

    vm.overallScore = null;

    vm.scoreDefinition = null;

    vm.processVariables = function () {
      var totalScore = 0;
      angular.forEach($scope.reviewList, function (obj) {

        //overallscore calculation
        totalScore = totalScore + obj.score;

        //injecting rating object into review object
        obj.ratingObject = {
          iconOn: 'ion-ios-star',
          iconOff: 'ion-ios-star-outline',
          iconOnColor: 'rgb(107, 70, 229)',
          iconOffColor: 'rgb(107, 70, 229)',
          rating: obj.score,
          minRating: 1,
          readOnly: true
        };
      });

      //overallscore calculation
      vm.overallScore = (totalScore / $scope.reviewList.length);
    };

    vm.processVariables();

    vm.getScoreDefinition = function () {
      if (vm.overallScore < 2) {
        vm.scoreDefinition = "Poor";
      }
      else if (vm.overallScore < 3) {
        vm.scoreDefinition = "Bad";
      }
      else if (vm.overallScore < 4) {
        vm.scoreDefinition = "Good";
      }
      else if (vm.overallScore >= 4 && vm.overallScore <= 4.5) {
        vm.scoreDefinition = "Very good";
      }
      else
        vm.scoreDefinition = "Excellent";
    };

    vm.getScoreDefinition();
    //disable loading icon
    $ionicLoading.hide();
  })

  .controller('AddReviewController', function ($stateParams, reviewService, $state, $ionicHistory, $ionicPopup) {
    var vm = this;

    vm.courseCode = $stateParams.courseCode;

    vm.newReview = {};

    vm.newReview.score = 3;

    vm.ratingObject = {
      iconOn: 'ion-ios-star',    //Optional
      iconOff: 'ion-ios-star-outline',   //Optional
      iconOnColor: 'rgb(107, 70, 229)',  //Optional
      iconOffColor: 'rgb(107, 70, 229)',    //Optional
      rating: 3, //Optional
      minRating: 1,    //Optional
      readOnly: false, //Optional
      callback: function (rating, index) {    //Mandatory
        vm.ratingsCallback(rating, index);
      }
    };

    vm.ratingsCallback = function (rating, index) {
      vm.newReview.score = rating;
    };

    vm.save = function () {
      reviewService.addReview(vm.courseCode, vm.newReview)
        .then(function (response) {
          $ionicHistory.currentView($ionicHistory.backView());
          vm.newReview = {};
          $state.go('reviews', {courseCode: 'SAI'});
        }, function (error) {
          $ionicPopup.alert({
            title: 'Error',
            template: 'Saving new review failed'
          })
        });
    };
  })

  .controller('LoginController', function (loginService, $ionicHistory, $state, $ionicPush, studentService, $ionicPopup) {
    var vm = this;

    vm.loginData = {};

    vm.trylogin = function () {
      loginService.login(vm.loginData)
        .then(function (response) {
          loginService.setAuthentication(response.data.message);
          //registering for push notifications
          $ionicPush.register()
            .then(function (t) {
              return $ionicPush.saveToken(t);
            }).then(function (t) {
            studentService.addPushNotificationToken(t.token);
          });
          $ionicHistory.nextViewOptions({
            historyRoot: true
          });
          $ionicHistory.clearCache()
            .then(function () {
              $state.go('app.home');
            });
        }, function (error) {
          $ionicPopup.alert({
            title: 'Error',
            template: error.data.error
          });
        });
    };
  })

  .controller('HomeController', function () {
    var vm = this;
    vm.currentDate = new Date();
  })

  .controller('RegistrationController', function (electiveCoursesResolve, registrationService, $state) {
    var vm = this;
    vm.electiveCourses = electiveCoursesResolve.courses;

    vm.register = function (courseCode) {
      return registrationService.createRegistration(courseCode)
        .then(function (response) {
          $state.go('app.registration');
        }, function (error) {
          console.log(error);
        });

    };
  })

  .controller('CourseDetailsController', function ($stateParams, reviewsResolve) {
    var vm = this;
    vm.course = $stateParams.courseView;
    vm.reviews = reviewsResolve.reviews;
  })

  .controller('MyCoursesController', function ($state, myMandatoryCoursesResolve, myAcceptedCoursesResolve) {
    var vm = this;
    vm.acceptedCourses = myAcceptedCoursesResolve.acceptedCourses;
    vm.mandatoryCourses = myMandatoryCoursesResolve.mandatoryCourses;

    vm.mandatoryEC = myMandatoryCoursesResolve.mandatoryEC;
    vm.acceptedEC = myAcceptedCoursesResolve.acceptedEC;
  });
