angular.module('edssnApp')
    .controller('PromotionCodeListController', ['$q', '$scope', 'uiGridConstants', 'PromotionCode', 'SelectedPromotionCode',
        function ($q, $scope, uiGridConstants, PromotionCode, SelectedPromotionCode) {
            var columnDefs = [{
                name: 'Código',
                field: 'code'
            }, {
                name: '¿usado?',
                field: 'used',
                cellTemplate: '<div>{{COL_FIELD ? " Sí " : " No "}}</div>'
            }, {
                name: 'Puntos',
                field: 'points'
            }, {
                name: 'Campaña',
                field: 'marketingCampaign.name'
            }
            ];

            var paginationOptions = {
                pageNumber: 1,
                pageSize: 25,
                sort: null,
                specification: null
            };

            $scope.promotionCodes;

            var getPage = function (currentPage, pageSize, sort, specification) {
                var deferred = $q.defer();
                $scope.promotionCodes = PromotionCode.queryPagination({
                    page: currentPage - 1,
                    size: pageSize,
                    sort: sort
                }, specification, function (data) {
                    $scope.resetVM();
                    $scope.gridOpts.totalItems = data.totalItems;
                    $scope.gridOpts.data = data.content;
                    deferred.resolve(data.content);
                });
                return deferred.promise;
            };

            $scope.selectedItem = null;
            $scope.gridOpts.columnDefs = columnDefs;
            $scope.gridOpts.useExternalPagination = true;
            $scope.gridOpts.useExternalSorting = true;
            $scope.gridOpts.useExternalFiltering = true;
            $scope.gridOpts.enableGridMenu = true;
            $scope.gridOpts.onRegisterApi = function (gridApi) {
                $scope.gridApi = gridApi;

                gridApi.core.on.sortChanged($scope, function (grid, sortColumns) {
                    if (sortColumns.length == 0) {
                        paginationOptions.sort = null;
                    } else {
                        paginationOptions.sort = $scope.getSort(sortColumns);
                    }
                    if (getPage) {
                        getPage(paginationOptions.pageNumber, paginationOptions.pageSize, paginationOptions.sort, paginationOptions.specification);
                    }
                });

                gridApi.core.on.filterChanged($scope, function () {
                    var grid = this.grid;
                    var gridColumns = grid.columns;
                    var specification = {};
                    var modified = false;
                    angular.forEach(gridColumns, function (column) {
                        var term;
                        if (column.filters && column.filters.length > 0 && column.filters[0].term) {
                            term = column.filters[0].term;

                            if (column.field === 'code') {
                                specification.code = term;
                                modified = true;
                            }
                            if (column.field === 'used' && (term === 'Sí' || term === 'No')) {
                                specification.used = (term === 'Sí' ? true : false);
                                modified = true;
                            }
                            if (column.field === 'points' && $scope.isNumber(term)) {
                                specification.points = term;
                                modified = true;
                            }
                            if (column.field === 'marketingCampaign.name') {
                                specification.marketingCampaign = {};
                                specification.marketingCampaign.name = term;
                                modified = true;
                            }
                        }
                    });
                    if (modified) {
                        paginationOptions.specification = specification;
                    } else {
                        paginationOptions.specification = null;
                    }
                    if (getPage) {
                        getPage(paginationOptions.pageNumber, paginationOptions.pageSize, paginationOptions.sort, paginationOptions.specification);
                    }
                });

                gridApi.selection.on.rowSelectionChanged($scope, function (row) {
                    SelectedPromotionCode.item = row.entity;
                    $scope.selectedItem = SelectedPromotionCode.item;
                });

                gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
                    if (!isNaN(newPage)) {
                        paginationOptions.pageNumber = newPage;
                    } else {
                        paginationOptions.pageNumber = 1;
                    }
                    paginationOptions.pageSize = pageSize;
                    if (getPage) {
                        getPage(paginationOptions.pageNumber, paginationOptions.pageSize, paginationOptions.sort, paginationOptions.specification);
                    }
                });
            };
            $scope.gridOpts.exporterAllDataFn = function () {
                return getPage(1, $scope.gridOpts.totalItems, paginationOptions.sort)
                    .then(function () {
                        $scope.gridOpts.useExternalPagination = false;
                        $scope.gridOpts.useExternalSorting = false;
                        $scope.gridOpts.useExternalFiltering = false;
                        getPage = null;
                    });
            };

            $scope.deletePromotionCode = function () {
                var promotionCodeToDelete = new PromotionCode(SelectedPromotionCode.item);
                promotionCodeToDelete.$delete(function () {
                    $scope.promotionCodes = PromotionCode.query();
                    $scope.gridOpts.data = $scope.promotionCodes;
                    $scope.addSuccessMessage('El código promocional ha sido eliminado exitosamente');
                });
            };
            if (getPage) {
                getPage(paginationOptions.pageNumber, paginationOptions.pageSize, paginationOptions.sort);
            }
        }])
    .controller('PromotionCodeCreateController', ['$scope', '$location', 'PromotionCode',
        function ($scope, $location, PromotionCode) {
            $scope.newPromotionCode = {};

            $scope.save = function () {
                var award = new PromotionCode($scope.newPromotionCode);
                award.$save(function () {
                    $location.url('/promotion-code-list');
                });
            };
        }])
    .controller('AwardEditController', ['$scope', '$location', 'PromotionCode', 'SelectedPromotionCode',
        function ($scope, $location, PromotionCode, SelectedPromotionCode) {
            $scope.selectedPromotionCode = SelectedPromotionCode.item;
            $scope.save = function () {
                var promotionCode = new Award($scope.selectedPromotionCode);
                promotionCode.$update(function () {
                    $location.url('/promotion-code-list');
                });
            };
        }]);