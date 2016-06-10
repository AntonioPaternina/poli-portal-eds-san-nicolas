angular.module('edssnApp')
    .controller('LoginController', ['$http', '$scope', '$rootScope', '$controller', '$location',
        'AUTH_EVENTS', 'AuthService',
        function ($http, $scope, $rootScope, $controller, $location, AUTH_EVENTS, AuthService) {
            $scope.onLogin = function () {
                $scope.vm.isFormSent = true;
                if ($scope.form.$invalid) {
                    return;
                }
                AuthService.login($scope.vm.username, $scope.vm.password, $scope.vm.rememberMe).then(function (user) {
                    $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
                    $scope.setCurrentUser(user);
                    $location.url('/');
                }, function () {
                    $rootScope.$broadcast(AUTH_EVENTS.loginFailed);
                    $scope.vm.errorMessages = [];
                    $scope.vm.errorMessages
                        .push({description: 'Usuario y/o contraseña inválidos'});
                });
            };
        }]);
        