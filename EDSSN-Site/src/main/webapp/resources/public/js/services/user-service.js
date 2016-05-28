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
                    assignCoupon: function (coupon) {
                        var deferred = $q.defer();
                        $http({
                            method: 'POST',
                            url: '/account/assign-promotion-code',
                            data: "code=" + coupon,
                            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                        }).then(function (response) {
                            if (response.status === 200) {
                                deferred.resolve(response.data);
                            } else {
                                deferred.reject('Error assigning promotion code');
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