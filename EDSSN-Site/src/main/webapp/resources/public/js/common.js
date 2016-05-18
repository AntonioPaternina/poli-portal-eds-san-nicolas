angular.module('common', ['ngMessages'])
        .controller('FormCtrl', ['$scope', '$http', '$httpParamSerializerJQLike', function ($scope, $http, $httpParamSerializerJQLike) {
                var focusedField;
                $scope.vm = {
                    isFormSent: false,
                    errorMessages: []
                };
                $scope.onFocus = function (fieldName) {
                    focusedField = fieldName;
                };
                $scope.onBlur = function () {
                    focusedField = undefined;
                };
                $scope.isErrorMessageListVisible = function (fieldName) {
                    return focusedField === fieldName || $scope.vm.isFormSent;
                };
                $scope.login = function (username, password) {
                    $http({
                        method: 'POST',
                        url: '/login',
                        data: $httpParamSerializerJQLike({
                            username: username,
                            password: password
                        }),
                        headers: {
                            "Content-Type": "application/x-www-form-urlencoded",
                            "X-Login-Ajax-call": 'true'
                        }
                    }).then(function (response) {
                        if (response.data === 'ok') {
                            window.location.replace('/resources/index.html');
                        } else {
                            $scope.vm.errorMessages = [];
                            $scope.vm.errorMessages.push({description: 'Usuario y/o contraseña inválidos'});
                        }
                    });
                }
            }]);