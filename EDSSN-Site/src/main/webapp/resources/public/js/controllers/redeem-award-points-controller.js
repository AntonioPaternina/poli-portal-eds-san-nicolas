angular.module('edssnApp')
    .controller('RedeemAwardPointsController', ['$scope', '$http', 'RedeemPointsCustomer', 'Award',
        function ($scope, $http, RedeemPointsCustomer, Award) {
            var ctrl = this;
            $scope.customer = RedeemPointsCustomer.customer;
            $scope.awards = [];
            ctrl.selectedCampaignId = null;
            $scope.availablePointsForSelectedCampaign = 0;

            $scope.onCampaignSelected = function () {
                $scope.awards = Award.findByMarketingCampaign({marketingCampaignId: ctrl.selectedCampaignId});
                $scope.calculateAvailablePointsForSelectedCampaign();
            };

            $scope.submitRedeemRequest = function (awardId) {
                console.log('awardId' + awardId);

            };

            $scope.calculateAvailablePointsForSelectedCampaign = function () {
                var awardPoints = $scope.customer.awardPoints;
                var selectedCampaign = ctrl.selectedCampaignId;
                for (var i = 0; i < awardPoints.length; i++) {
                    if (awardPoints[i].marketingCampaign.id == selectedCampaign) {
                        $scope.availablePointsForSelectedCampaign = awardPoints[i].numberOfPoints;
                    }
                }
            };
        }]);