angular.module('edssnApp')
    .controller('SignupController', ['$http', '$scope', '$rootScope', '$location', 'Customer', 'AuthService', 'AUTH_EVENTS',
        function ($http, $scope, $rootScope, $location, Customer, AuthService, AUTH_EVENTS) {
            $scope.resetVM();

            $scope.birthdatePopup = {
                opened: false
            };

            $scope.dateOptions.maxDate = new Date();
            $scope.dateOptions.minDate = new Date(1800, 0, 0);
            $scope.openBirthdatePopup = function () {
                $scope.birthdatePopup.opened = true;
            };

            var columnDefs = [{
                name: 'placa',
                field: 'licensePlate'
            }, {
                name: 'marca',
                field: 'brand'
            }, {
                name: 'tipo',
                field: 'vehicleType'
            }
            ];

            $scope.vm.vehicles = [];
            $scope.tipos = [{
                label: 'Automovil',
                value: 'AUTOMOVIL'
            }, {
                label: 'Bus',
                value: 'BUS'
            }, {
                label: 'Buseta',
                value: 'BUSETA'
            }, {
                label: 'Camión',
                value: 'CAMION'
            }, {
                label: 'Campero',
                value: 'CAMPERO'
            }, {
                label: 'Carrotanque',
                value: 'CARROTANQUE'
            }, {
                label: 'Moto',
                value: 'MOTO'
            }];

            $scope.gridOpts = {
                columnDefs: columnDefs,
                data: $scope.vm.vehicles
            };

            $scope.addRow = function () {
                $scope.gridOpts.data.push({
                    licensePlate: $scope.tmp_plate,
                    brand: $scope.tmp_brand,
                    model: $scope.tmp_model,
                    vehicleType: $scope.tmp_type
                });
            };

            $scope.genre = [{
                label: 'Femenino',
                value: 'FEMALE'
            }, {
                label: 'Masculino',
                value: 'MALE'
            }];

            $scope.signup = function () {
                console.log('Registrando al usuario con username ' + $scope.vm.username + ' y contraseña ' + $scope.vm.password);

                $scope.vm.isFormSent = true;

                if ($scope.form.$invalid) {
                    return;
                }

                $scope.customer = new Customer();

                $scope.customer.username = $scope.vm.username;
                $scope.customer.password = $scope.vm.password;
                $scope.customer.email = $scope.vm.email;
                $scope.customer.nationalId = $scope.vm.nationalId;
                $scope.customer.fullName = $scope.vm.fullName;
                $scope.customer.address = $scope.vm.address;
                $scope.customer.birthdate = $scope.vm.birthdate;
                $scope.customer.vehicles = $scope.vm.vehicles;
                $scope.customer.gender = $scope.vm.gender;


                $scope.customer.$save(function () {
                    AuthService.login($scope.vm.username, $scope.vm.password, $scope.vm.rememberMe).then(function (user) {
                        $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
                        $scope.setCurrentUser(user);
                        $location.url('/');
                    }, function () {
                        $rootScope.$broadcast(AUTH_EVENTS.loginFailed);
                        $location.url('/login');
                    });
                });
            };
        }
    ]);
