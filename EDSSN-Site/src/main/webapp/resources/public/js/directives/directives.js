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
    .directive('commonErrorMessages', function () {
        return {
            restrict: 'E',
            link: function (scope, element, attrs) {
                scope.extraStyles = attrs.extraStyles;
            },
            scope: {
                errorMessages: '=errorMessages'
            },
            templateUrl: '/resources/public/partials/error-messages.html'
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