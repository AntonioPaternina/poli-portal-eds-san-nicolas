angular.module('edssnApp', ['commonDirectives', 'services', 'spring-security-csrf-token-interceptor'])
        .controller('MainController', ['$scope', 'UserService', function ($scope, UserService) {
                $scope.vm = {};

                getUser();

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
            }]);
