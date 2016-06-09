angular.module('edssnApp')
    .factory('Customer', ['$resource', function ($resource) {
        return $resource('/customers/:id');
    }])
    .factory('RedeemPointsCustomer', function () {
        return {customer: null};
    });
