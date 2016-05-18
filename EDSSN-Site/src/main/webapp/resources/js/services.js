angular.module('services', [])
        .service('UserService', ['$http', '$q', function ($http, $q) {
                return {
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