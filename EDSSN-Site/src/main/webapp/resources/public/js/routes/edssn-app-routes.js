angular.module('edssnApp')
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: '/resources/public/partials/landing-page-content.html'
            })
            /* available to all visitors */
            .when('/products', {
                templateUrl: '/resources/public/partials/products.html'
            })
            .when('/awards', {
                templateUrl: '/resources/public/partials/awards.html',
                controller: 'AwardController',
                controllerAs: 'ctrl'
            })
            .when('/about-us', {
                templateUrl: '/resources/public/partials/about-us.html'
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

            /* available to logged in users only */
            .when('/account', {
                templateUrl: '/resources/public/partials/account.html',
                controller: 'AccountController',
                controllerAs: 'ctrl'
            })

            /* avaiblable to admin users only */
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
            .when('/award-list', {
                templateUrl: '/resources/partials/award-list.html',
                controller: 'AwardController',
                controllerAs: 'ctrl'
            })
            .when('/award-detail', {
                templateUrl: '/resources/partials/award-detail.html',
                controller: 'AwardController',
                controllerAs: 'ctrl'
            })
            .when('/award-create', {
                templateUrl: '/resources/partials/award-create.html',
                controller: 'AwardCreateController',
                controllerAs: 'ctrl'
            })
            .when('/award-edit', {
                templateUrl: '/resources/partials/award-edit.html',
                controller: 'AwardEditController',
                controllerAs: 'ctrl'
            })


            .otherwise({
                redirectTo: '/'
            });
    }]);
