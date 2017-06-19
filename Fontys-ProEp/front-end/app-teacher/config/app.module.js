/**
 * Angular application with dependencies
 */
'use strict';

angular.module('appTeacher',
    [
        'ui.router',
        'appServiceAPI',
        'appServiceEvent',
        'appComponent.homeTeacher',
        'appComponent.courseView',
        'appComponent.studentTable',
        'appComponent.notifications',
        'ngCookies',
        'appComponent.login',
        'angularMoment',
        'ui-notification',
        'appComponent.popupNotification'
    ]
)

.run(function ($transitions) {

        $transitions.onStart( {}, function(transition, $interval) {
            var from = transition.from().name;
            var loggedIn = transition.injector().get('loginService').SetHeaderAuthentication("Teacher");

            // if((from === 'login' || from === 'logout') && loggedIn) {
            var rootscope = transition.injector().get('$rootScope');
            console.log(transition.injector().get('$interval').cancel(rootscope.notificationLoop));
            if(loggedIn) {
                console.log("scscs");
                rootscope.notificationLoop = transition.injector().get('$interval')(function () {
                    console.log("run");
                    transition.injector().get('teacherService').getBadgeCount()
                        .then(function (response) {
                            transition.injector().get('notificationService').setAmountOfBadges(response.data);
                        }, function (error) {
                            console.log(angular.toJson(error));
                        });
                }, 1000);
            }
                // transition.injector().get('teacherService').getBadgeCount()
                //     .then(function (response) {
                //         transition.injector().get('notificationService').setAmountOfBadges(response.data);
                //     }, function (error) {
                //         console.log(angular.toJson(error));
                //     });
            // }
            if(!loggedIn && transition.to().name !== 'login') {
                return transition.router.stateService.target("login", undefined, { location: false });
            }
            else if(loggedIn && transition.to().name === 'login') {
                return false;
            }
        });
});