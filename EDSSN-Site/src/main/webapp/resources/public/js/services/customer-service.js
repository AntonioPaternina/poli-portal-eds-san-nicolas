angular.module('edssnApp')
    .service('CustomerService', ['$http', function ($http) {
        
    }])
    .factory('Customer', ['$resource', function ($resource) {
        return $resource('/customers/:id');
    }])
    .factory('RedeemPointsCustomer', function () {
        return {customer: null};
    });
