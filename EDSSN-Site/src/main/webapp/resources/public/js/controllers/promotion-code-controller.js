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
            }, {
                name: 'Fecha de Creación',
                field: 'creationDate',
                cellTemplate: '<div>{{COL_FIELD | date:"yyyy-MM-dd HH:mm:ss Z"}}</div>',
                type: 'date',
                enableFiltering: false
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
            var getPageDefault = function () {
                if (getPage) {
                    getPage(paginationOptions.pageNumber, paginationOptions.pageSize, paginationOptions.sort, paginationOptions.specification);
                }
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
                    getPageDefault();
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
                    getPageDefault();
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
                    getPageDefault();
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
                    getPageDefault();
                    $scope.addSuccessMessage('El cupón ha sido eliminado correctamente');
                });
            };
            if (getPage) {
                getPage(paginationOptions.pageNumber, paginationOptions.pageSize, paginationOptions.sort);
            }
        }])
    .controller('PromotionCodeCreateController', ['$scope', '$location', 'PromotionCode', 'Campaign',
        function ($scope, $location, PromotionCode, Campaign) {
            $scope.promotionCodeBatchRequest = {};

            $scope.campaigns = Campaign.query();
            $scope.selection = [];

            $scope.save = function () {
                PromotionCode.batchCreate({
                    numberOfCodesToCreate: $scope.promotionCodeBatchRequest.numberOfCodesToCreate,
                    awardPointsPercode: $scope.promotionCodeBatchRequest.awardPointsPercode,
                    codeLength: $scope.promotionCodeBatchRequest.codeLength,
                    marketingCampaign: {
                        id: $scope.promotionCodeBatchRequest.selectedCampaignId
                    }
                }, function () {
                    $scope.addSuccessMessage('Los códigos se han generado correctamente');
                });
            };
        }]);