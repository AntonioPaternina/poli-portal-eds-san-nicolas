angular.module('edssnApp')
    .controller('MainController', ['$scope', '$http', '$rootScope', '$location', 'UserService', 'AuthService', 'USER_ROLES', 'AUTH_EVENTS', 'Session', 'uiGridConstants', 'i18nService',
        function ($scope, $http, $rootScope, $location, UserService, AuthService, USER_ROLES, AUTH_EVENTS, Session, uiGridConstants, i18nService) {
            i18nService.setCurrentLang('es');

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
                gridMenuShowHideColumns: false,
                paginationPageSizes: [25, 50, 75],
                paginationPageSize: 25,
                exporterMenuPdf: false
            };
            $scope.getSort = function (uiGridSortColumns) {
                var sortColumn = uiGridSortColumns[0];

                var sortDirection;
                switch (sortColumn.sort.direction) {
                    case uiGridConstants.ASC:
                        sortDirection = 'asc';
                        break;
                    case uiGridConstants.DESC:
                        sortDirection = 'desc';
                        break;
                    default:
                        break;
                }

                return sortColumn.field + ',' + sortDirection;
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

            $scope.isNumber = function (n) {
                return !isNaN(parseFloat(n)) && isFinite(n);
            };

            $scope.parseMoment = function (dateString) {
                var moment = require('moment');
                return moment(dateString);
            };
            $scope.parseDate = function (dateString) {
                return $scope.parseMoment(dateString).toDate();
            };
            $scope.isDate = function (dateString) {
                var parsedMoment = $scope.parseMoment(dateString);
                return parsedMoment.isValid();
            };
        }]);
