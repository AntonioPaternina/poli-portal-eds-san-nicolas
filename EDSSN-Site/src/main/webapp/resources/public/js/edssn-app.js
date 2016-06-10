angular.module('edssnApp', ['ngRoute',
    'ngMessages',
    'ngResource',
    'ui.bootstrap',
    'ui.grid',
    'ui.grid.selection',
    'ui.validate'])
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
    .config(['$provide', '$httpProvider', function ($provide, $httpProvider) {
        $provide.factory('CsrfTokenInterceptor', [function () {
            var factory = this;
            this.updateCsrfToken = function (response) {
                if (response.headers('X-CSRF-TOKEN')) {
                    $httpProvider.defaults.headers.common['X-CSRF-TOKEN'] = response.headers('X-CSRF-TOKEN');
                }
                return response;
            };
            return {
                response: function (response) {
                    return factory.updateCsrfToken(response);
                },
                responseError: function (response) {
                    return factory.updateCsrfToken(response);
                }
            }
        }]);
        $provide.factory('AuthInterceptor', ['$rootScope', '$q', 'AUTH_EVENTS', 'Session'
            , function ($rootScope, $q, AUTH_EVENTS, Session) {
                return {
                    responseError: function (response) {
                        $rootScope.$broadcast({
                            401: AUTH_EVENTS.notAuthenticated,
                            403: AUTH_EVENTS.notAuthorized,
                            419: AUTH_EVENTS.sessionTimeout,
                            440: AUTH_EVENTS.sessionTimeout
                        }[response.status], response);

                        return $q.reject(response);
                    }
                };
            }]);
        $provide.factory('ErrorInterceptor', ['$q', '$rootScope', function ($q, $rootScope) {
            return {
                responseError: function (rejection) {
                    if (rejection && rejection.data && rejection.data.message) {
                        $rootScope.addError(rejection.data.message);
                    }
                    return $q.reject(rejection);
                }
            };
        }]);

        $httpProvider.defaults.xsrfHeaderName = 'X-CSRF-TOKEN';

        $httpProvider.interceptors.push('CsrfTokenInterceptor');
        $httpProvider.interceptors.push('AuthInterceptor');
        $httpProvider.interceptors.push('ErrorInterceptor');
    }]);