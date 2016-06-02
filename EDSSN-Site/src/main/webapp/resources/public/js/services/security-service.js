angular.module('edssnApp').service('SecurityService', ['$rootScope', '$q', 'UserService', function ($rootScope, $q, UserService) {
        return {
            allowed: function (permission) {
                var service = this;
                var deferred = $q.defer();
                var loggedInUser = $rootScope.loggedInUser;
                if (!loggedInUser) {
                    UserService.getUser().then(function (user) {
                        $rootScope.loggedInUser = user;
                        deferred.resolve(service.queryPermission(permission));
                    }, function () {
                        deferred.reject("Error retrieving user info");
                    });
                } else {
                    deferred.resolve(service.queryPermission(permission));
                }
                return deferred.promise;
            },
            queryPermission: function (permission) {
                var loggedInUser = $rootScope.loggedInUser;
                if (!loggedInUser) {
                    return false;
                }
                var userRoles = loggedInUser.userRoles;
                for (var i = 0; i < userRoles.length; i++) {
                    if (userRoles[i].type === permission) {
                        return true;
                    }
                }
                return false;
            }
        };
    }]);
