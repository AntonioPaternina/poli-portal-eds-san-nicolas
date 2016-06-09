angular.module('edssnApp')
    .controller('RedeemAwardPointsController', ['$scope', '$http', 'RedeemPointsCustomer', 'Award',
        'AwardRedeemRequest', function ($scope, $http, RedeemPointsCustomer, Award, AwardRedeemRequest) {
            $scope.resetVM();
            
            var ctrl = this;
            $scope.customer = RedeemPointsCustomer.customer;
            $scope.awards = [];
            ctrl.selectedCampaignId = null;

            $scope.onCampaignSelected = function () {
                $scope.awards = Award.findByMarketingCampaign({marketingCampaignId: ctrl.selectedCampaignId});
            };

            $scope.submitRedeemRequest = function (awardId) {
                var awardRedeemRequest = new AwardRedeemRequest({
                    award: {id: awardId},
                    user: {id: $scope.customer.id},
                    marketingCampaign: {id: Number(ctrl.selectedCampaignId)}
                });
                awardRedeemRequest.$save();
            };
        }]);