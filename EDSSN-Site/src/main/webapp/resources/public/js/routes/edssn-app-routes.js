angular.module('edssnApp')
    .config(['$routeProvider', function ($routeProvider) {
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
                controller: 'SignupController',
                controllerAs: 'ctrl'
            })
            .when('/account', {
                templateUrl: '/resources/public/partials/account.html',
                controller: 'AccountController',
                controllerAs: 'ctrl'
            })
            .when('/users', {
                templateUrl: '/resources/partials/users.html',
                controller: 'CustomerController',
                controllerAs: 'ctrl'
            })
            .otherwise({
                redirectTo: '/'
            });
    }]);
