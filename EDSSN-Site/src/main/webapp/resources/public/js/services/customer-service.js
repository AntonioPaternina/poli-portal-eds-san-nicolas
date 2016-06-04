angular.module('edssnApp')
    .factory('Customer', ['$resource', function ($resource) {
        return $resource('/customers/:id');
    }]);
