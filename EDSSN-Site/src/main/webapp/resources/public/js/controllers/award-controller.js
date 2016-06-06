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

            $scope.addNewCampaign = function () {
                $location.url('/award-create');
            };

        }])
    .controller('AwardCreateController', ['$scope', 'Campaign', 'Award', function ($scope, Campaign, Award) {
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
            award.$save();
        };
    }]);