angular.module('edssnApp', ['commonDirectives', 'services', 'spring-security-csrf-token-interceptor'])
        .controller('MainController', ['$scope', 'UserService', function ($scope, UserService) {
                $scope.logout = function () {
                    UserService.logout();
                };
            }]);
