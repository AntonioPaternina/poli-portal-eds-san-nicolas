/* ROUTING CONFIGURATION */
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
                    .when('/my-account', {
                        templateUrl: '/resources/public/partials/my-account.html',
                        controller: 'UserController',
                        controllerAs: 'userCtrl'
                    })
                    .otherwise({
                        redirectTo: '/'
                    });
        }]);
