angular.module('edssnApp')
    .config(['$routeProvider', 'USER_ROLES', function ($routeProvider, USER_ROLES) {
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

            /* available to customers only */
            .when('/account', {
                templateUrl: '/resources/public/partials/account.html',
                controller: 'AccountController',
                controllerAs: 'ctrl',
                data: {
                    authorizedRoles: [USER_ROLES.customer]
                }
            })

            /* avaiblable to admin users only */
            .when('/customers', {
                templateUrl: '/resources/partials/customer/list.html',
                controller: 'CustomerController',
                controllerAs: 'ctrl',
                data: {
                    authorizedRoles: [USER_ROLES.admin]
                }
            })
            .when('/customer-detail', {
                templateUrl: '/resources/partials/customer/detail.html',
                controller: 'CustomerDetailController',
                controllerAs: 'ctrl',
                data: {
                    authorizedRoles: [USER_ROLES.admin]
                }
            })
            .when('/redeem-points', {
                templateUrl: '/resources/partials/redeem-points.html',
                controller: 'RedeemAwardPointsController',
                controllerAs: 'ctrl',
                data: {
                    authorizedRoles: [USER_ROLES.admin]
                }
            })
            .when('/award-list', {
                templateUrl: '/resources/partials/award/list.html',
                controller: 'AwardController',
                controllerAs: 'ctrl',
                data: {
                    authorizedRoles: [USER_ROLES.admin]
                }
            })
            .when('/award-detail', {
                templateUrl: '/resources/partials/award/detail.html',
                controller: 'AwardController',
                controllerAs: 'ctrl',
                data: {
                    authorizedRoles: [USER_ROLES.admin]
                }
            })
            .when('/award-create', {
                templateUrl: '/resources/partials/award/create.html',
                controller: 'AwardCreateController',
                controllerAs: 'ctrl',
                data: {
                    authorizedRoles: [USER_ROLES.admin]
                }
            })
            .when('/award-edit', {
                templateUrl: '/resources/partials/award/edit.html',
                controller: 'AwardEditController',
                controllerAs: 'ctrl',
                data: {
                    authorizedRoles: [USER_ROLES.admin]
                }
            })
            .when('/promotion-code-list', {
                templateUrl: '/resources/partials/promotion-code/list.html',
                controller: 'PromotionCodeListController',
                controllerAs: 'ctrl',
                data: {
                    authorizedRoles: [USER_ROLES.admin]
                }
            })
            .when('/promotion-code-detail', {
                templateUrl: '/resources/partials/promotion-code/detail.html',
                controller: 'PromotionCodeDetailController',
                controllerAs: 'ctrl',
                data: {
                    authorizedRoles: [USER_ROLES.admin]
                }
            })
            .when('/promotion-code-create', {
                templateUrl: '/resources/partials/promotion-code/create.html',
                controller: 'PromotionCodeCreateController',
                controllerAs: 'ctrl',
                data: {
                    authorizedRoles: [USER_ROLES.admin]
                }
            })
            .when('/promotion-code-edit', {
                templateUrl: '/resources/partials/promotion-code/edit.html',
                controller: 'PromotionCodeEditController',
                controllerAs: 'ctrl',
                data: {
                    authorizedRoles: [USER_ROLES.admin]
                }
            })


            .otherwise({
                redirectTo: '/'
            });
    }]);
