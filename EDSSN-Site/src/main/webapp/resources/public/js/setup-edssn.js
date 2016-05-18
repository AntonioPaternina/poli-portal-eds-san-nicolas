///////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Defines the javascript files that need to be loaded and their dependencies.
//
///////////////////////////////////////////////////////////////////////////////////////////////////////////

require.config({
    paths: {
        angular: '../../bower_components/angular/angular',
        angularMessages: '../../bower_components/angular-messages/angular-messages',
        csrfInterceptor: '../../bower_components/spring-security-csrf-token-interceptor/dist/spring-security-csrf-token-interceptor.min',
        lodash: "../../bower_components/lodash/dist/lodash",
        commonDirectives: '../../public/js/common-directives',
        services: 'services',
        edssnApp: "edssn-app"
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
        commonDirectives: {
            deps: ['angular', 'lodash']
        },
        services: {
            deps: ['angular', 'lodash', 'csrfInterceptor']
        },
        edssnApp: {
            deps: ['lodash', 'angular', 'angularMessages', 'commonDirectives', 'services']
        }
    }
});

require(['edssnApp'], function () {

    angular.bootstrap(document.getElementById('edssnApp'), ['edssnApp']);

});