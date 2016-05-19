/* MODULE DECLARATION */
angular.module('edssnApp', ['ngRoute', 'commonDirectives', 'services', 'spring-security-csrf-token-interceptor', 'ngMessages']);

/* CONTROLLERS */
angular.module('edssnApp')
        .controller('MainController', ['$scope', '$http', '$httpParamSerializerJQLike', '$location', 'UserService', function ($scope, $http, $httpParamSerializerJQLike, $location, UserService) {
                $scope.vm = {
                    isFormSent: false,
                    errorMessages: []
                };
                var focusedField;

                function getUser() {
                    UserService.getUser().then(function (user) {
                        $scope.vm.username = user.username;
                    }, function (errorMessage) {
                        // TODO
                    });
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
                            $scope.loggedInUsername = username;
                        } else {
                            $scope.vm.errorMessages = [];
                            $scope.vm.errorMessages.push({description: 'Usuario y/o contraseña inválidos'});
                        }
                    });
                };
            }])
        .controller('LoginController', ['$http', '$httpParamSerializerJQLike', '$location', '$scope', function ($http, $httpParamSerializerJQLike, $location, $scope) {
                this.onLogin = function () {
                    console.log('Inicio de sesión con usuario: ' + $scope.vm.username + ' y contraseña ' + $scope.vm.password);

                    $scope.vm.isFormSent = true;

                    if ($scope.form.$invalid) {
                        return;
                    }

                    $scope.login($scope.vm.username, $scope.vm.password);

                };

            }])
        .controller('SignupCtrl', ['$http', '$scope', function ($http, $scope) {
                var controller = this;
                controller.signup = function () {
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


/* ROUTING CONFIGURATION */
angular.module('edssnApp')
        .config(function ($routeProvider) {
            $routeProvider
                    .when('/', {
                        templateUrl: '/resources/public/partials/landing-page-content.html'
                    })
                    .when('/login', {
                        templateUrl: '/resources/public/partials/login.html',
                        controller: 'LoginController',
                        controllerAs: 'ctrl'
                    })
                    .when('/signup', {
                        templateUrl: '/resources/public/partials/signup.html',
                        controller: 'SignupCtrl',
                        controllerAs: 'ctrl'
                    })
                    .otherwise({
                        redirectTo: '/'
                    });
        });

