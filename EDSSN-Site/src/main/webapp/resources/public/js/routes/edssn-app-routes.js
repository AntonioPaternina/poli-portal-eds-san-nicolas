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
            .when('/customers', {
                templateUrl: '/resources/partials/customers.html',
                controller: 'CustomerController',
                controllerAs: 'ctrl'
            })
            .when('/redeem-points', {
                templateUrl: '/resources/partials/redeem-points.html',
                controller: 'RedeemAwardPointsController',
                controllerAs: 'ctrl'
            })
            .otherwise({
                redirectTo: '/'
            });
    }]);
