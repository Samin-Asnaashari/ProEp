/**
 * Angular application with dependencies
 */
angular.module('appTeacher',
    [
        'ui.router',
        'appServiceAPI',
        'appServiceEvent',
        'appComponent.homeTeacher',
        'appComponent.courseView',
        'appComponent.studentTable'
    ]
);