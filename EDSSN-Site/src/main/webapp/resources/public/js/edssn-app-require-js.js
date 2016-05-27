require.config({
    paths: {
        angular: '/resources/bower_components/angular/angular',
        angularMessages: '/resources/bower_components/angular-messages/angular-messages',
        angularRoute: '/resources/bower_components/angular-route/angular-route',
        angularAnimate: '/resources/bower_components/angular-animate/angular-animate',
        angularTouch: '/resources/bower_components/angular-touch/angular-touch',
        angularBootstrap: '/resources/bower_components/angular-bootstrap/ui-bootstrap-tpls',
        angulari18n: '/resources/bower_components/angular-i18n/angular-locale_es-co',
        angularGrid: '/resources/bower_components/angular-ui-grid/ui-grid',
        csrfInterceptor: '/resources/bower_components/spring-security-csrf-token-interceptor/dist/spring-security-csrf-token-interceptor.min',
        lodash: "/resources/bower_components/lodash/dist/lodash",
        edssnApp: '/resources/public/js/edssn-app',
        directives: '/resources/public/js/directives/directives',
        userService: '/resources/public/js/services/user-service',
        edssnAppRoutes: '/resources/public/js/routes/edssn-app-routes',
        mainController: '/resources/public/js/controllers/main-controller',
        signupController: '/resources/public/js/controllers/signup-controller',
        loginController: '/resources/public/js/controllers/login-controller',
        userController: '/resources/public/js/controllers/user-controller',
        accountController: '/resources/public/js/controllers/account-controller'
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
            deps: ['lodash', 'angular', 'angularMessages', 'angularRoute', 'csrfInterceptor', 'angularBootstrap', 'angulari18n', 'angularGrid']
        },
        directives: {
            deps: ['angular', 'lodash', 'edssnApp']
        },
        userService: {
            deps: ['angular', 'lodash', 'edssnApp']
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
        userController: {
            deps: ['angular', 'edssnApp']
        },
        accountController: {
            deps: ['angular', 'edssnApp']
        }
    }
});

require(['edssnApp', 'edssnAppRoutes', 'directives', 'mainController', 'loginController', 'signupController', 'userController', 'accountController', 'userService'], function () {

    angular.bootstrap(document.getElementById('edssnApp'), ['edssnApp'], {
        // TODO this is set to false because of an unknown error, must be fixed
        strictDi: false
    });

});