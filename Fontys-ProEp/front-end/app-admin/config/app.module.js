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
);