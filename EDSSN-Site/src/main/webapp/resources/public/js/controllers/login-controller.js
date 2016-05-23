angular.module('edssnApp')
        .controller('LoginController', ['$http', '$httpParamSerializerJQLike',
            '$location', '$scope', '$controller', function ($http,
                    $httpParamSerializerJQLike, $location, $scope, $controller) {
                angular.extend(this, $controller('MainController', {$scope: $scope}));
                this.onLogin = function () {
                    console.log('Inicio de sesión con usuario: '
                            + $scope.vm.username + ' y contraseña '
                            + $scope.vm.password);

                    $scope.vm.isFormSent = true;

                    if ($scope.form.$invalid) {
                        return;
                    }

                    $scope.login($scope.vm.username, $scope.vm.password);

                };

            }]);
        