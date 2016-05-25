angular.module('edssnApp')
        .service('UserService', ['$http', '$q', '$location', '$window', function ($http, $q, $location, $window) {
                return {
                    getAwardPointsForCurrentUser: function () {
                        var deferred = $q.defer();
                        $http.get('/account/award-points').then(function (response) {
                            if (response.status === 200) {
                                deferred.resolve(response.data);
                            } else {
                                deferred.reject("Error obteniendo los puntos de recompensa del usuario");
                            }
                        });
                        return deferred.promise;
                    },
                    getUser: function () {
                        var deferred = $q.defer();
                        $http.get('/user').then(function (response) {
                            if (response.status === 200) {
                                deferred.resolve(response.data);
                            } else {
                                deferred.reject('Error obteniendo información del usuario');
                            }
                        });
                        return deferred.promise;
                    },
                    logout: function () {
                        $http({
                            method: 'POST',
                            url: '/logout'
                        }).then(function (response) {
                            $window.location.replace('/');
                        });
                    }
                };
            }]);