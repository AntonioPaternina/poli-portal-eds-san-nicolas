angular.module('edssnApp')
        .controller('LoginController', ['$http', '$scope', '$controller', function ($http, $scope, $controller) {

                angular.extend(this, $controller('MainController', {$scope: $scope}));

                this.onLogin = function () {
                    $scope.vm.isFormSent = true;
                    if ($scope.form.$invalid) {
                        return;
                    }
                    $scope.login($scope.vm.username, $scope.vm.password);
                };
            }]);
        