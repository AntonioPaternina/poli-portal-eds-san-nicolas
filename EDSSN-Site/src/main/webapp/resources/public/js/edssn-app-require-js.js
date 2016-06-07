require.config({
    paths: {
        angular: '/resources/bower_components/angular/angular',
        angularMessages: '/resources/bower_components/angular-messages/angular-messages',
        angularRoute: '/resources/bower_components/angular-route/angular-route',
        angularAnimate: '/resources/bower_components/angular-animate/angular-animate',
        angularTouch: '/resources/bower_components/angular-touch/angular-touch',
        angularResource: '/resources/bower_components/angular-resource/angular-resource',
        angularBootstrap: '/resources/bower_components/angular-bootstrap/ui-bootstrap-tpls',
        angulari18n: '/resources/bower_components/angular-i18n/angular-locale_es-co',
        angularGrid: '/resources/bower_components/angular-ui-grid/ui-grid',
        csrfInterceptor: '/resources/bower_components/spring-security-csrf-token-interceptor/dist/spring-security-csrf-token-interceptor.min',
        edssnApp: '/resources/public/js/edssn-app',
        directives: '/resources/public/js/directives/directives',
        authService: '/resources/public/js/services/auth-service',
        userService: '/resources/public/js/services/user-service',
        customerService: '/resources/public/js/services/customer-service',
        awardService: '/resources/public/js/services/award-service',
        campaignService: '/resources/public/js/services/campaign-service',
        edssnAppRoutes: '/resources/public/js/routes/edssn-app-routes',
        mainController: '/resources/public/js/controllers/main-controller',
        signupController: '/resources/public/js/controllers/signup-controller',
        loginController: '/resources/public/js/controllers/login-controller',
        customerController: '/resources/public/js/controllers/customer-controller',
        accountController: '/resources/public/js/controllers/account-controller',
        awardController: '/resources/public/js/controllers/award-controller',
        redeemAwardPointsController: '/resources/public/js/controllers/redeem-award-points-controller',
        constants: '/resources/public/js/constants/constants'
    },
    shim: {
        angular: {
            exports: "angular"
        },
        csrfInterceptor: {
            deps: ['angular']
        },
        angularMessages: {
            deps: ['angular']
        },
        angularRoute: {
            deps: ['angular']
        },
        angularAnimate: {
            deps: ['angular']
        },
        angularTouch: {
            deps: ['angular']
        },
        angularResource: {
            deps: ['angular']
        },
        angularBootstrap: {
            deps: ['angular', 'angularTouch', 'angularAnimate']
        },
        angularGrid: {
            deps: ['angular']
        },
        angulari18n: {
            deps: ['angular']
        },
        edssnApp: {
            deps: ['angular', 'angularMessages', 'angularRoute', 'csrfInterceptor', 'angularBootstrap', 'angulari18n', 'angularGrid', 'angularResource']
        },
        directives: {
            deps: ['angular', 'edssnApp']
        },
        authService: {
            deps: ['angular', 'edssnApp']
        },
        userService: {
            deps: ['angular', 'edssnApp']
        },
        customerService: {
            deps: ['angular', 'edssnApp']
        },
        campaignService: {
            deps: ['angular', 'edssnApp']
        },
        awardService: {
            deps: ['angular', 'edssnApp']
        },
        edssnAppRoutes: {
            deps: ['angular', 'edssnApp']
        },
        mainController: {
            deps: ['angular', 'edssnApp']
        },
        loginController: {
            deps: ['angular', 'edssnApp']
        },
        signupController: {
            deps: ['angular', 'edssnApp']
        },
        customerController: {
            deps: ['angular', 'edssnApp']
        },
        accountController: {
            deps: ['angular', 'edssnApp']
        },
        awardController: {
            deps: ['angular', 'edssnApp']
        },
        redeemAwardPointsController: {
            deps: ['angular', 'edssnApp']
        },
        constants: {
            deps: ['angular', 'edssnApp']
        }
    }
});

require(['edssnApp',
    'edssnAppRoutes',
    'directives',
    'mainController',
    'loginController',
    'signupController',
    'customerController',
    'accountController',
    'redeemAwardPointsController',
    'awardController',
    'authService',
    'userService',
    'customerService',
    'awardService',
    'campaignService',
    'constants'], function () {

    angular.bootstrap(document.getElementById('edssnApp'), ['edssnApp'], {
        // TODO this is set to false because of an unknown error, must be fixed
        strictDi: false
    });

});