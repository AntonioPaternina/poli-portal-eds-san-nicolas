angular.module('edssnApp')
    .controller('AwardController', ['$scope', '$rootScope', '$controller', '$location', 'Award',
        function ($scope, $rootScope, $controller, $location, Award) {
            angular.extend(this, $controller('MainController', {$scope: $scope}));

            var columnDefs = [{
                name: 'Referencia',
                field: 'reference'
            }, {
                name: 'Nombre',
                field: 'name'
            }, {
                name: 'Descripción',
                field: 'description'
            }, {
                name: 'Costo en Puntos',
                field: 'costInPoints'
            }
            ];

            $scope.awards = Award.query();


            $scope.gridOpts.columnDefs = columnDefs;
            $scope.gridOpts.data = $scope.awards;
            $scope.gridOpts.onRegisterApi = function (gridApi) {
                $scope.gridApi = gridApi;
                gridApi.selection.on.rowSelectionChanged($scope, function (row) {
                    $rootScope.crudAwardSelectedRecord = row.entity;
                });
            };

            $scope.showDetails = function () {
                $location.url('/award-detail');
            };

            $scope.addNewAward = function () {
                $location.url('/award-create');
            };

            $scope.editAward = function () {
                $location.url('/award-edit');
            };

            $scope.deleteAward = function () {
                var awardToDelete = new Award($rootScope.crudAwardSelectedRecord);
                awardToDelete.$delete(function () {
                    $scope.awards = Award.query();
                    $scope.gridOpts.data = $scope.awards;
                });
            };

        }])
    .controller('AwardCreateController', ['$scope', '$location', 'Campaign', 'Award', function ($scope, $location, Campaign, Award) {
        var ctrl = this;
        $scope.newAward = {};
        $scope.campaigns = Campaign.query();


        $scope.selection = [];

        $scope.$watch('campaigns | filter:{selected:true}', function (nv) {
            $scope.selection = nv.map(function (campaign) {
                return {id: campaign.id};
            });
        }, true);

        $scope.save = function () {
            $scope.newAward.marketingCampaigns = $scope.selection;

            var award = new Award($scope.newAward);
            award.$save(function () {
                $location.url('/award-list');
            });
        };
    }])
    .controller('AwardEditController', ['$scope', '$rootScope', '$location', 'Campaign', 'Award', function ($scope, $rootScope, $location, Campaign, Award) {
        var ctrl = this;
        $scope.selectedAward = $rootScope.crudAwardSelectedRecord;

        $scope.campaigns = Campaign.query(function () {
            ctrl.preLoadCampaigns();
        });
        $scope.selection = [];
        ctrl.preLoadCampaigns = function () {
            var currentCampaigns = $scope.selectedAward.marketingCampaigns;
            for (var i = 0; i < currentCampaigns.length; i++) {
                for (var j = 0; j < $scope.campaigns.length; j++) {
                    if ($scope.campaigns[j].id === currentCampaigns[i].id) {
                        $scope.campaigns[j].selected = true;
                    }
                }
            }
        };
        $scope.$watch('campaigns | filter:{selected:true}', function (nv) {
            $scope.selection = nv.map(function (campaign) {
                return {id: campaign.id};
            });
        }, true);

        $scope.save = function () {
            $scope.selectedAward.marketingCampaigns = $scope.selection;

            var award = new Award($scope.selectedAward);
            award.$update(function () {
                $location.url('/award-list');
            });
        };

    }]);