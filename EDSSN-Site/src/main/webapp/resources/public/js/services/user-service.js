angular.module('edssnApp')
        .service('UserService', ['$http', '$q', function ($http, $q) {
                return {
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
                            if (response.status === 200) {
                                window.location.reload();
                            } else {
                                console.log("Ha fallado el cierre de sesión");
                            }
                        });
                    }
                };
            }]);