angular.module('edssnApp')
    .factory('AuthService', ['$http', '$q', '$httpParamSerializerJQLike', 'Session', 'UserService',
        function ($http, $q, $httpParamSerializerJQLike, Session, UserService) {
            var authService = {};

            authService.login = function (username, password, rememberMe) {
                return $http({
                    method: 'POST',
                    url: '/login',
                    data: $httpParamSerializerJQLike({
                        username: username,
                        password: password,
                        "remember-me": rememberMe
                    }),
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded",
                        "X-Login-Ajax-call": 'true'
                    }
                }).then(function (response) {
                    var deferred = $q.defer();
                    if (response && response.data === "ok") {
                        UserService.getUser().then(function (user) {
                            Session.create(user);
                            deferred.resolve(user);
                        });
                    } else {
                        deferred.reject('authentication failed');
                    }
                    return deferred.promise;
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
    });