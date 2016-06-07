angular.module('edssnApp', ['ngRoute',
    'spring-security-csrf-token-interceptor',
    'ngMessages',
    'ngResource',
    'ui.bootstrap',
    'ui.grid',
    'ui.grid.selection'])
    .run(['$rootScope', 'AUTH_EVENTS', 'AuthService', '$location', function ($rootScope, AUTH_EVENTS, AuthService, $location) {
        $rootScope.$on('$routeChangeStart', function (event, next) {
            if (!next.data) {
                return;
            }
            var authorizedRoles = next.data.authorizedRoles;
            if (!AuthService.isAuthorized(authorizedRoles)) {
                event.preventDefault();
                if (AuthService.isAuthenticated()) {
                    // user is not allowed
                    $rootScope.$broadcast(AUTH_EVENTS.notAuthorized);
                    $location.url('/');
                } else {
                    // user is not logged in
                    $rootScope.$broadcast(AUTH_EVENTS.notAuthenticated);
                    $location.url('/');
                }
            }
        });
    }])
    .config(function ($httpProvider) {
        $httpProvider.interceptors.push([
            '$injector',
            function ($injector) {
                return $injector.get('AuthInterceptor');
            }
        ]);
    });