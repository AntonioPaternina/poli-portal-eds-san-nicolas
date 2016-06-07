angular.module('edssnApp')
    .factory('AuthService', ['$http', '$httpParamSerializerJQLike', 'Session', 'UserService',
        function ($http, $httpParamSerializerJQLike, Session, UserService) {
            var authService = {};

            authService.login = function (username, password) {
                return $http({
                    method: 'POST',
                    url: '/login',
                    data: $httpParamSerializerJQLike({
                        username: username,
                        password: password
                    }),
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded",
                        "X-Login-Ajax-call": 'true'
                    }
                }).then(function () {
                    return UserService.getUser().then(function (user) {
                        Session.create(user);
                        return user;
                    });
                });
            };

            authService.isAuthenticated = function () {
                return !!Session.userId;
            };

            authService.isAuthorized = function (authorizedRoles) {
                if (!angular.isArray(authorizedRoles)) {
                    authorizedRoles = [authorizedRoles];
                }
                if (!authService.isAuthenticated()) {
                    return false;
                }
                var isAuthorized = false;
                angular.forEach(Session.userRoles, function (value) {
                    if (authorizedRoles.indexOf(value.type) !== -1) {
                        isAuthorized = true;
                    }
                });
                return isAuthorized;
            };

            return authService;
        }])
    .service('Session', function () {
        this.create = function (user) {
            this.user = user;
            this.userId = user.username;
            this.userRoles = user.userRoles;
        };
        this.destroy = function () {
            this.user = null;
            this.userId = null;
            this.userRoles = null;
        };
    })
    .factory('AuthInterceptor', function ($rootScope, $q,
                                          AUTH_EVENTS) {
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
    });