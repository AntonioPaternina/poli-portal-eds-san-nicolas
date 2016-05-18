angular.module('signupApp', ['common', 'spring-security-csrf-token-interceptor', 'commonDirectives'])
        .controller('SignupCtrl', ['$scope', '$http', function ($scope, $http) {
                $scope.signup = function () {
                    console.log('Registrando al usuario con username ' + $scope.vm.username + ' y contraseña ' + $scope.vm.password);

                    $scope.vm.isFormSent = true;

                    if ($scope.form.$invalid) {
                        return;
                    }

                    var postData = {
                        username: $scope.vm.username,
                        password: $scope.vm.password,
                        email: $scope.vm.email,
                        nationalId: $scope.vm.nationalId,
                        fullName: $scope.vm.fullName,
                        address: $scope.vm.address
                    };

                    $http({
                        method: 'POST',
                        url: '/user',
                        data: postData,
                        headers: {
                            "Content-Type": "application/json",
                            "Accept": "text/plain"
                        }
                    }).then(function (response) {
                        if (response.status === 200) {
                            $scope.login($scope.vm.username, $scope.vm.password);
                        } else {
                            $scope.vm.errorMessages = [];
                            $scope.vm.errorMessages.push({description: response.data});
                            console.log("Falló la creación del usuario: " + response.data);
                        }
                    });
                };
            }]);