angular.module('edssnApp')
        .controller('MainController', ['$scope', '$http',
            '$httpParamSerializerJQLike', '$location', '$rootScope', 'UserService',
            function ($scope, $http, $httpParamSerializerJQLike, $location,
                    $rootScope, UserService) {
                $scope.vm = {
                    isFormSent: false,
                    errorMessages: []
                };
                var focusedField;

                $scope.getUser = function getUser() {
                    UserService.getUser().then(function (user) {
                        $rootScope.loggedInUser = user;
                    }, function (errorMessage) {
                        // TODO handle this error
                    });
                };
                if (!$rootScope.loggedInUser) {
                    $scope.getUser();
                }

                $scope.logout = function () {
                    UserService.logout();
                };

                $scope.vm.appReady = true;


                $scope.onFocus = function (fieldName) {
                    focusedField = fieldName;
                };
                $scope.onBlur = function () {
                    focusedField = undefined;
                };
                $scope.isErrorMessageListVisible = function (fieldName) {
                    return focusedField === fieldName || this.vm.isFormSent;
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
                            $location.url('/#');
                            $scope.getUser();
                        } else {
                            $scope.vm.errorMessages = [];
                            $scope.vm.errorMessages
                                    .push({description: 'Usuario y/o contraseña inválidos'});
                        }
                    });
                };
                $scope.dateFormat = 'dd/MM/yyyy';
                $scope.altInputFormats = ['d!/M!/yyyy'];
                $scope.datePickerCloseText = "Cerrar";
                $scope.datePickerCurrentText = "Hoy";
                $scope.datePickerClearText = "Limpiar";
                $scope.dateOptions = {
                    formatYear: 'yyyy',
                    startingDay: 1,
                    showWeeks: false
                };
            }]);
