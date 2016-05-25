angular.module('edssnApp')
        .controller('SignupController', ['$http', '$scope', '$controller', function ($http, $scope, $controller) {
                angular.extend(this, $controller('MainController', {$scope: $scope}));

                $scope.birthdatePopup = {
                    opened: false
                };

                $scope.dateOptions.maxDate = new Date();
                $scope.dateOptions.minDate = new Date(1800, 0, 0);
                $scope.openBirthdatePopup = function () {
                    $scope.birthdatePopup.opened = true;
                };

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
                        address: $scope.vm.address,
                        birthdate: $scope.vm.birthdate
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
