angular.module('edssnApp')
    .service('UsersService', ['$resource', function ($resource) {
        return $resource('/users/:id');
    }]);
