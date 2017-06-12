/**
 * Angular application with dependencies
 */
angular.module('appAdmin',
    [
        'ui.router',
        'appServiceAPI',
        'appServiceEvent',
        'appComponent',
        'ngCookies',
        'base64'
    ]
)

.run(function ($transitions) {

    $transitions.onStart( {}, function(transition) {
        if(!transition.injector().get('loginService').SetHeaderAuthentication() && transition.to().name !== 'login') {
            return transition.router.stateService.target("login", undefined, { location: false });
        }
        else if(transition.injector().get('loginService').getAuthentication() && transition.to().name === 'login') {
            return false;
        }
    });

});