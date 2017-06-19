angular.module('GORCA.controllers', ['GORCA.Directives'])

  .controller('MenuController', function (notificationsResolve, notificationsBadgeCountResolve, notificationService, $ionicPopup,
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
          $ionicPopup.alert({
            title: 'Error',
            template: 'Error Logging out!'
          });
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
      if(notification.type === "ACCEPTED") {
        $state.go('app.myCourses');
      }
      else if(notification.type === "DECLINED"){
        $state.go('app.registration');
      }
      else {
        // var acceptedCourses = courseService.getAcceptedElectedCourses();
        // var exceptAcceptedCourses = courseService.getExceptAcceptedCourses();
        // if(acceptedCourses !== null) {
        //   if($filter("filter")(acceptedCourses, {code:notification.courseCode}).length !== 0) {
        //     $state.go('app.myCourses');
        //     return;
        //   }
        // }
        // if()
        //
        // $ionicPopup.alert({
        //   title: 'Error',
        //   template: 'Could not find acceptedCourses'
        // })
      }
    };

    //event for receiving push notifications
    $scope.$on('cloud:push:notification', function (event, data) {
      if (data.message.raw.additionalData.foreground) {
        notificationService.getAllNotificationsBefore(notificationDataService.lastNotificationID)
          .then(function (response) {
            if (response.data !== "") {
              notificationDataService.lastNotificationID = response.data[0].id;
              vm.notifications = response.data.concat(vm.notifications);
              vm.updateNotificationGui();
            }
          }, function (error) {
            $ionicPopup.alert({
              title: 'Error',
              template: 'Error receiving push Notifications!'
            });
          });
      }
    });

    vm.updateNotificationGui = function () {
      studentService.getBadgeCount()
        .then(function (response) {
          if (response.data !== "") {
            if (response.data !== 0) {
              vm.amountOfBadges = response.data;
              vm.badgeAvailable = true;
            }
          }
        }, function (error) {
          $ionicPopup.alert({
            title: 'Error',
            template: 'Error getting Notifications count!'
          });
        });
    };

    EventNotification.subscribeOnNewNotifications($scope, function (event, data) {
      vm.notifications = data.notifications.concat(vm.notifications);
      vm.updateNotificationGui();
    });

    vm.loadMoreNotifications = function () {
      notificationService.getAllNotificationsAfter(vm.notifications[vm.notifications.length - 1].id)
        .then(function (response) {
          if (response.data.length !== 0) {
            vm.notifications = vm.notifications.concat(response.data);
            $scope.$broadcast('scroll.infiniteScrollComplete');
          }
          else {
            vm.moreDataCanBeLoaded = false;
            $scope.$broadcast('scroll.infiniteScrollComplete');
          }
        }, function (error) {
          $scope.$broadcast('scroll.infiniteScrollComplete');
          $ionicPopup.alert({
            title: 'Error',
            template: alert(angular.toJson(error))
          });
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

    vm.course = $stateParams.course;

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
      reviewService.addReview(vm.course.code, vm.newReview)
        .then(function (response) {
          vm.newReview = {};
          $state.go('app.courseDetailsView', {courseView: vm.course});
        }, function (error) {
          $ionicPopup.alert({
            title: 'Error',
            template: 'Error adding Review!'
          });
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

  .controller('HomeController', function ($state, $ionicHistory) {
    var vm = this;
    vm.currentDate = new Date();

    vm.goToRegistration = function () {
      $ionicHistory.nextViewOptions({
        disableBack: true
      });

      $state.go('app.registration');
    };
  })
  
  .controller('RegistrationController', function (electiveCoursesToApplyResolve, registeredCoursesResolve, registrationService, $state, $ionicLoading, $ionicPopup,
                                                  courseService) {
    var vm = this;
    vm.coursesToApply = electiveCoursesToApplyResolve.coursesToApply;
    vm.registeredCoursesExceptAcceptedOnes = registeredCoursesResolve.registeredCoursesExceptAcceptedOnes;
    // courseService.setExceptAcceptedCourses(vm.registeredCoursesExceptAcceptedOnes);

    vm.register = function (course) {
      if (course.teachers.length != 0) {
        return registrationService.createRegistration(course.code)
          .then(function (response) {
            if(response.data == true){
              var index = vm.coursesToApply.indexOf(course);
              vm.coursesToApply.splice(index, 1);
              course.status = "PENDING";
              vm.registeredCoursesExceptAcceptedOnes.push(course);
            }else{
              $ionicPopup.alert({
                title: 'Error',
                template: 'Registration date is not set or is expired. For more info see the course details page.'
              });
            }
          }, function (error) {
            alert(angular.toJson(error));
          });
      } else {
        $ionicPopup.alert({
          title: 'Error',
          template: 'No Teacher has been assigned yet! Please Come Back Later.'
        });
      }
    };

    vm.goToCourseView = function (course) {
      $state.go('app.courseDetailsView', {courseView: course})
    };

    vm.cancelRegistration = function (course) {
      var confirmPopup = $ionicPopup.confirm({
        title: 'Cancel Registration for Course ' + course.code,
        template: 'Are you sure you want to Cancel your request?'
      });

      confirmPopup.then(function (res) {
        if (res) {
          registrationService.cancelRegistration(course.code).then(function (response) {
            var index = vm.registeredCoursesExceptAcceptedOnes.indexOf(course);
            vm.registeredCoursesExceptAcceptedOnes.splice(index, 1);
          }, function (error) {
            $ionicPopup.alert({
              title: 'Error',
              template: 'Error cancelling registration request!'
            });
          });
        }
      });
    };

    //disable loading icon
    $ionicLoading.hide();
  })

  .controller('CourseDetailsController', function ($stateParams, reviewsResolve, $ionicLoading) {
    var vm = this;
    vm.course = $stateParams.courseView;
    vm.reviews = reviewsResolve.reviews;

    // disable loading icon
    $ionicLoading.hide();
  })

  .controller('MyCoursesController', function ($state, $ionicPopup, myMandatoryCoursesResolve, myAcceptedCoursesResolve, registrationService, $ionicLoading) {

    var vm = this;
    vm.acceptedCourses = myAcceptedCoursesResolve.acceptedCourses;
    // courseService.setAcceptedElectedCourses(vm.acceptedCourses);
    vm.mandatoryCourses = myMandatoryCoursesResolve.mandatoryCourses;

    vm.mandatoryEC = myMandatoryCoursesResolve.mandatoryEC;
    vm.acceptedEC = myAcceptedCoursesResolve.acceptedEC;

    vm.dropRegistration = function (course) {
      var confirmPopup = $ionicPopup.confirm({
        title: 'Drop course ' + course.code,
        template: 'Are you sure you want to drop this course?'
      });

      confirmPopup.then(function (res) {
        if (res) {
          registrationService.dropRegistration(course.code).then(function (response) {
            var index = vm.acceptedCourses.indexOf(course);
            vm.acceptedEC = vm.acceptedEC - course.ec;
            vm.acceptedCourses.splice(index, 1);
          }, function (error) {
            $ionicPopup.alert({
              title: 'Error',
              template: 'Error dropping course!'
            });
          });
        }
      });
    };

    vm.reloadElective = function () {
      vm.acceptedCourses = myAcceptedCoursesResolve.acceptedCourses;
      vm.acceptedEC = myAcceptedCoursesResolve.acceptedEC;
    };

    //disable loading icon
    $ionicLoading.hide();
  });
