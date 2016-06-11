require.config({
    paths: {
        angular: '/resources/bower_components/angular/angular.min',
        angularMessages: '/resources/bower_components/angular-messages/angular-messages.min',
        angularRoute: '/resources/bower_components/angular-route/angular-route.min',
        angularAnimate: '/resources/bower_components/angular-animate/angular-animate.min',
        angularTouch: '/resources/bower_components/angular-touch/angular-touch.min',
        angularResource: '/resources/bower_components/angular-resource/angular-resource.min',
        angularBootstrap: '/resources/bower_components/angular-bootstrap/ui-bootstrap-tpls.min',
        angulari18n: '/resources/bower_components/angular-i18n/angular-locale_es-co',
        angularGrid: '/resources/bower_components/angular-ui-grid/ui-grid.min',
        angularValidate: '/resources/bower_components/angular-ui-validate/dist/validate.min',
        moment: '/resources/bower_components/moment/min/moment.min',
        edssnApp: '/resources/public/js/edssn-app',
        directives: '/resources/public/js/directives/directives',
        authService: '/resources/public/js/services/auth-service',
        userService: '/resources/public/js/services/user-service',
        customerService: '/resources/public/js/services/customer-service',
        awardService: '/resources/public/js/services/award-service',
        campaignService: '/resources/public/js/services/campaign-service',
        promotionCodeService: '/resources/public/js/services/promotion-code-service',
        edssnAppRoutes: '/resources/public/js/routes/edssn-app-routes',
        mainController: '/resources/public/js/controllers/main-controller',
        signupController: '/resources/public/js/controllers/signup-controller',
        loginController: '/resources/public/js/controllers/login-controller',
        customerController: '/resources/public/js/controllers/customer-controller',
        accountController: '/resources/public/js/controllers/account-controller',
        awardController: '/resources/public/js/controllers/award-controller',
        promotionCodeController: '/resources/public/js/controllers/promotion-code-controller',
        redeemAwardPointsController: '/resources/public/js/controllers/redeem-award-points-controller',
        constants: '/resources/public/js/constants/constants'
    },
    shim: {
        angular: {
            exports: "angular"
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
        angularValidate: {
            deps: ['angular']
        },
        moment: {
            deps: []
        },
        angulari18n: {
            deps: ['angular']
        },
        edssnApp: {
            deps: ['angular', 'angularMessages', 'angularRoute', 'angularBootstrap', 'angulari18n',
                'angularGrid', 'angularValidate', 'angularResource', 'moment']
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
        promotionCodeService: {
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
        promotionCodeController: {
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
    'promotionCodeController',
    'awardController',
    'authService',
    'userService',
    'customerService',
    'awardService',
    'campaignService',
    'promotionCodeService',
    'constants'], function () {

    angular.bootstrap(document.getElementById('edssnApp'), ['edssnApp'], {
        // TODO this is set to false because of an unknown error, must be fixed
        strictDi: false
    });

});