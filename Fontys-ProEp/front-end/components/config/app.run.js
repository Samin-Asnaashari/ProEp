angular.module('appComponent').run(function ($state, $rootScope) {
    $rootScope.$state = $state;
});