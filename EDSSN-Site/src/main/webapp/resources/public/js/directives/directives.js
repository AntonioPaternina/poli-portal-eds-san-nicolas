angular.module('edssnApp')
    .directive('mainHeader', function () {
        return {
            restrict: 'E',
            templateUrl: '/resources/public/partials/site-header.html'
        };
    })
    .directive('mainFooter', function () {
        return {
            restrict: 'E',
            templateUrl: '/resources/public/partials/site-footer.html'
        };
    })
    .directive('splashHeader', function () {
        return {
            restrict: 'E',
            templateUrl: '/resources/public/partials/splash-header.html'
        };
    })
    .directive('commonMessages', function () {
        return {
            restrict: 'E',
            templateUrl: '/resources/public/partials/common-messages.html'
        };
    })
    .directive('checkPasswordsMatch', function () {
        return {
            require: 'ngModel',
            link: function (scope, elm, attrs, ngModel) {
                ngModel.$validators.checkPasswordsMatch = function (modelValue, viewValue) {
                    if (scope.vm && scope.vm.password && viewValue) {
                        return scope.vm.password === viewValue;
                    }
                    return true;
                };
            }
        };
    });