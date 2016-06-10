angular.module('edssnApp')
    .controller('MainController', ['$scope', '$http', '$rootScope', '$location', 'UserService', 'AuthService', 'USER_ROLES', 'AUTH_EVENTS', 'Session',
        function ($scope, $http, $rootScope, $location, UserService, AuthService, USER_ROLES, AUTH_EVENTS, Session) {
            $scope.currentUser = null;
            $scope.userRoles = USER_ROLES;
            $scope.isAuthorized = AuthService.isAuthorized;
            $scope.setCurrentUser = function (user) {
                $scope.currentUser = user;
            };

            $scope.getUser = function getUser() {
                UserService.getUser().then(function (user) {
                    $scope.currentUser = user;
                    Session.create(user);
                }, function (errorMessage) {
                    // TODO handle this error
                });
            };
            if (!$scope.currentUser) {
                $scope.getUser();
            }
            $scope.logout = function () {
                UserService.logout();
            };
            var redirectToLoginPage = function () {
                Session.destroy();
                $scope.currentUser = null;
                $location.url('/login');
            };
            $scope.$on(AUTH_EVENTS.notAuthenticated, redirectToLoginPage);
            $scope.$on(AUTH_EVENTS.sessionTimeout, redirectToLoginPage);

            $scope.vm = {
                isFormSent: false,
                errorMessages: [],
                infoMessages: [],
                successMessages: []
            };
            var focusedField;
            $scope.resetVM = function () {
                $scope.vm = {
                    isFormSent: false,
                    errorMessages: [],
                    infoMessages: [],
                    successMessages: []
                };
                focusedField = undefined;
            };
            $scope.$on('$routeChangeStart', $scope.resetVM);


            $scope.onFocus = function (fieldName) {
                focusedField = fieldName;
            };
            $scope.onBlur = function () {
                focusedField = undefined;
            };
            $scope.isErrorMessageListVisible = function (fieldName) {
                return focusedField === fieldName || this.vm.isFormSent;
            };
            $scope.dateFormat = 'dd/MM/yyyy';
            $scope.altInputFormats = ['d!/M!/yyyy'];
            $scope.datePickerCloseText = "Cerrar";
            $scope.datePickerCurrentText = "Hoy";
            $scope.datePickerClearText = "Limpiar";
            $scope.dateOptions = {
                formatYear: 'yyyy',
                startingDay: 1,
                showWeeks: false
            };

            $scope.gridOpts = {
                enableFiltering: true,
                enableRowSelection: true,
                showSelectionCheckbox: true,
                multiSelect: false,
                enableSelectAll: false,
                noUnselect: true,
                gridMenuShowHideColumns: false
            };

            $rootScope.addError = function (errorMessage) {
                $scope.resetVM();
                $scope.vm.errorMessages
                    .push({description: errorMessage});

            };
            $rootScope.addInfoMessage = function (infoMessage) {
                $scope.resetVM();
                $scope.vm.infoMessages.push({description: infoMessage});
            };
            $rootScope.addSuccessMessage = function (successMessage) {
                $scope.resetVM();
                $scope.vm.successMessages.push({description: successMessage});
            };
        }]);
