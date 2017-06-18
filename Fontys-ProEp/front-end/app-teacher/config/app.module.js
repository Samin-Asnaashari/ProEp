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
        'ngCookies',
        'appComponent.login',
        'angularMoment',
        'ui-notification',
        'appComponent.notification'
    ]
)

.run(function ($transitions) {

        $transitions.onStart( {}, function(transition) {
            var from = transition.from().name;
            var loggedIn = transition.injector().get('loginService').SetHeaderAuthentication("Teacher");

            if((from === 'login' || from === 'logout') && loggedIn) {
                transition.injector().get('teacherService').getBadgeCount()
                    .then(function (response) {
                        transition.injector().get('notificationService').setAmountOfBadges(response.data);
                    }, function (error) {
                        console.log(angular.toJson(error));
                    });
            }
            if(!loggedIn && transition.to().name !== 'login') {
                return transition.router.stateService.target("login", undefined, { location: false });
            }
            else if(loggedIn && transition.to().name === 'login') {
                return false;
            }
        });
});