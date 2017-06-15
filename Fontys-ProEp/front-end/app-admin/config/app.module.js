/**
 * Angular application with dependencies
 */
angular.module('appAdmin',
    [
        'ui.router',
        'appServiceAPI',
        'appServiceEvent',
        'appComponent',
        'ngCookies'
    ]
)

.run(function ($transitions) {

    $transitions.onStart( {}, function(transition) {
        if(!transition.injector().get('loginService').SetHeaderAuthentication("Admin") && transition.to().name !== 'login') {
            return transition.router.stateService.target("login", undefined, { location: false });
        }
        else if(transition.injector().get('loginService').getAuthentication("Admin") && transition.to().name === 'login') {
            return false;
        }
    });

});