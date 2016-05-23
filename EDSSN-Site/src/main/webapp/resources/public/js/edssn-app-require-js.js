require.config({
    paths: {
        angular: '/resources/bower_components/angular/angular',
        angularMessages: '/resources/bower_components/angular-messages/angular-messages',
        angularRoute: '/resources/bower_components/angular-route/angular-route',
        csrfInterceptor: '/resources/bower_components/spring-security-csrf-token-interceptor/dist/spring-security-csrf-token-interceptor.min',
        lodash: "/resources/bower_components/lodash/dist/lodash",
        edssnApp: '/resources/public/js/edssn-app',
        directives: '/resources/public/js/directives/directives',
        userService: '/resources/public/js/services/user-service',
        edssnAppRoutes: '/resources/public/js/routes/edssn-app-routes',
        mainController: '/resources/public/js/controllers/main-controller',
        signupController: '/resources/public/js/controllers/signup-controller',
        loginController: '/resources/public/js/controllers/login-controller',
        userController: '/resources/public/js/controllers/user-controller'
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
        edssnApp: {
            deps: ['lodash', 'angular', 'angularMessages', 'angularRoute', 'csrfInterceptor']
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
        }
    }
});

require(['edssnApp', 'edssnAppRoutes', 'directives', 'mainController', 'loginController', 'signupController', 'userController', 'userService'], function () {

    angular.bootstrap(document.getElementById('edssnApp'), ['edssnApp'], {
        // TODO this is set to false because of an unknown error, must be fixed
        strictDi: false
    });

});