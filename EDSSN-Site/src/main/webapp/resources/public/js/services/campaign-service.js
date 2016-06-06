angular.module('edssnApp')
    .factory('Campaign', ['$resource', function ($resource) {
        return $resource('/marketing-campaigns/:id');
    }]);