angular.module('loginApp', ['common', 'spring-security-csrf-token-interceptor', 'commonDirectives'], function ($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
}).controller('LoginCtrl', ['$scope', function ($scope) {
        $scope.onLogin = function () {
            console.log('Inicio de sesión con usuario: ' + $scope.vm.username + ' y contraseña ' + $scope.vm.password);

            $scope.vm.isFormSent = true;

            if ($scope.form.$invalid) {
                return;
            }

            $scope.login($scope.vm.username, $scope.vm.password);

        };

    }]);