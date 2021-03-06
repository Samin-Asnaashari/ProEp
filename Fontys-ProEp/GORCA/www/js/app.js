// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.controllers' is found in controllers.js
angular.module('GORCA', ['ionic', 'ionic.cloud', 'ionic-ratings', 'GORCA.controllers', 'GORCA.serviceAPIS', 'GORCA.dataServices',
  'angularMoment', 'GORCA.events', 'ngCookies'])

  .run(function ($ionicPlatform, notificationDataService, notificationService, EventNotification, $rootScope, loginService, $state, $ionicPopup) {
    $ionicPlatform.ready(function () {
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

      $ionicPlatform.on("resume", function (event) {
        if (loginService.getAuthentication()) {
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
              $ionicPopup.alert({
                title: 'Error',
                template: 'Error getting the latest Notifications!'
              });
            });
        }
      });
      // $rootScope.$on('$stateChangeStart', function(event, toState){
      //   if(!loginService.SetHeaderAuthentication() && toState.name !== 'login') {
      //     event.preventDefault();
      //     $state.go('login');
      //   }
      //   else if(loginService.getAuthentication() && toState.name === 'login') {
      //     event.preventDefault();
      //   }
      // });
    });
    $rootScope.$on('$stateChangeStart', function (event, toState) {
      if (!loginService.SetHeaderAuthentication() && toState.name !== 'login') {
        event.preventDefault();
        $state.go('login');
      }
      else if (loginService.getAuthentication() && toState.name === 'login') {
        event.preventDefault();
        $state.go('app.home');
      }
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
    $ionicConfigProvider.views.maxCache(0);
  })

  .config(function ($stateProvider, $urlRouterProvider) {
    $stateProvider

      .state('app', {
        url: '/app',
        abstract: true,
        params: {
          courseToView: null
        },
        views: {
          'mainMenu': {
            templateUrl: 'templates/menu.html',
            controller: 'MenuController',
            controllerAs: 'menuCtrl'
          }
        },
        resolve: {
          notificationsResolve: function (notificationService, $ionicLoading, $ionicPopup) {

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
                $ionicPopup.alert({
                  title: 'Error',
                  template: 'Error getting Notifications!'
                });
              })
          },
          notificationsBadgeCountResolve: function (studentService, $ionicLoading, $ionicPopup) {
            return studentService.getBadgeCount()
              .then(function (response) {
                return {badgeCount: response.data};
              }, function (error) {
                //disable loading icon
                $ionicLoading.hide();
                $ionicPopup.alert({
                  title: 'Error',
                  template: 'Error getting Notifications count!'
                });
              })
          }
        }
      })

      .state('login', {
        url: '/login',
        views: {
          'mainMenu': {
            templateUrl: 'templates/login.html',
            controller: 'LoginController',
            controllerAs: 'loginCtrl'
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

      .state('app.myCourses', {
        url: '/myCourses',
        views: {
          'menuContent': {
            templateUrl: 'templates/myCourses.html',
            controller: 'MyCoursesController',
            controllerAs: 'MyCoursesCtrl'
          }
        },
        resolve: {
          myAcceptedCoursesResolve: function (courseService, $ionicPopup, $ionicLoading) {

            //show loading icon
            $ionicLoading.show({
              template: 'Loading...'
            });

            return courseService.getAllAcceptedElectiveCourses()
              .then(function (response) {
                var totalEC = 0;
                angular.forEach(response.data, function (course) {
                  totalEC = totalEC + course.ec;
                });

                return {acceptedCourses: response.data, acceptedEC: totalEC};
              }, function (error) {
                //disable loading icon
                $ionicLoading.hide();
                $ionicPopup.alert({
                  title: 'Error',
                  template: 'Error retreiving accepted courses!'
                })
              });
          },
          myMandatoryCoursesResolve: function (courseService, $ionicPopup, $ionicLoading) {

            //show loading icon
            $ionicLoading.show({
              template: 'Loading...'
            });

            return courseService.getAllMandatoryCourses()
              .then(function (response) {
                var totalEC = 0;
                angular.forEach(response.data, function (course) {
                  totalEC = totalEC + course.ec;
                });

                return {mandatoryCourses: response.data, mandatoryEC: totalEC};
              }, function (error) {
                //disable loading icon
                $ionicLoading.hide();
                $ionicPopup.alert({
                  title: 'Error',
                  template: 'Error retreiving mandatory courses!'
                })
              });
          }
        }
      })

      .state('app.registration', {
        url: '/registration',
        views: {
          'menuContent': {
            templateUrl: 'templates/registration.html',
            controller: 'RegistrationController',
            controllerAs: 'registrationCtrl'
          }
        },
        resolve: {
          registeredCoursesResolve: function (registrationService, $ionicLoading, $ionicPopup) {

            //show loading icon
            $ionicLoading.show({
              template: 'Loading...'
            });

            return registrationService.GetAllRegistrationsExceptAcceptedOnes()
              .then(function (response) {
                var myRegisteredCourses = [];
                angular.forEach(response.data, function (r) {
                  r.id.course.status = r.registrationStatus;
                  myRegisteredCourses.push(r.id.course);
                });
                return {registeredCoursesExceptAcceptedOnes: myRegisteredCourses};
              }, function (error) {
                //disable loading icon
                $ionicLoading.hide();
                $ionicPopup.alert({
                  title: 'Error',
                  template: 'Error getting Registration record!'
                });
              });
          },
          electiveCoursesToApplyResolve: function (courseService, $ionicLoading, $ionicPopup) {

            //show loading icon
            $ionicLoading.show({
              template: 'Loading...'
            });

            return courseService.GetAllNotAppliedElectiveCoursesForStudent()
              .then(function (response) {
                return {coursesToApply: response.data};
              }, function (error) {
                //disable loading icon
                $ionicLoading.hide();
                $ionicPopup.alert({
                  title: 'Error',
                  template: 'Error getting elective courses to apply!'
                });
              });
          }
        }
      })

      .state('app.addReview', {
        url: '/courseDetails/newReview',
        params: {
          course: null
        },
        views: {
          'menuContent': {
            templateUrl: 'templates/addReview.html',
            controller: 'AddReviewController',
            controllerAs: 'addReviewCtrl'
          }
        }
      })

      // .state('reviews', {
      //   url: '/courseDetails/reviews/:courseCode',
      //   views: {
      //     'menuContent': {
      //       templateUrl: 'templates/review.html',
      //       controller: 'ReviewController',
      //       controllerAs: 'reviewCtrl'
      //     }
      //   },
      //   resolve: {
      //     reviewsResolve: function (reviewService, $ionicLoading, $stateParams) {
      //
      //       //show loading icon
      //       $ionicLoading.show({
      //         template: 'Loading...'
      //       });
      //
      //       return reviewService.getAllReviews($stateParams.courseCode)
      //         .then(function (response) {
      //           return {reviews: response.data};
      //         }, function (error) {
      //           console.log('error');
      //           //disable loading icon
      //           $ionicLoading.hide();
      //           alert(angular.toJson(error));
      //         })
      //     }
      //   }
      // })

      .state('app.courseDetailsView', {
        url: '/myCourses/courseDetailsView',
        params: {
          courseView: null
        },
        views: {
          'menuContent': {
            templateUrl: 'templates/courseDetailsView.html',
            controller: 'CourseDetailsController',
            controllerAs: 'CourseDetailsCtrl'
          }
        },
        resolve: {
          reviewsResolve: function (reviewService, $ionicLoading, $stateParams, $ionicPopup) {

            // show loading icon
            $ionicLoading.show({
              template: 'Loading...'
            });

            var courseDetailStorage = window.localStorage['courseDetail'];
            if (!courseDetailStorage || $stateParams.courseView !== null) {
              window.localStorage['courseDetail'] = JSON.stringify($stateParams.courseView);
            }
            else {
              $stateParams.courseView = JSON.parse(courseDetailStorage);
            }
            return reviewService.getAllReviews($stateParams.courseView.code)
              .then(function (response) {
                return {reviews: response.data};
              }, function (error) {
                console.log('error');
                // disable loading icon
                $ionicLoading.hide();
                $ionicPopup.alert({
                  title: 'Error',
                  template: 'Error getting Reviews!'
                });
              })
          }
        }
      })
    ;

    $urlRouterProvider.otherwise(function ($injector) {
      var $state = $injector.get('$state');
      return $state.go('login');
    });
  });
