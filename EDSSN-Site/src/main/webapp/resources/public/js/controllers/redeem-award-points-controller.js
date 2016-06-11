angular.module('edssnApp')
    .controller('RedeemAwardPointsController', ['$scope', '$http', 'RedeemPointsCustomer', 'Customer', 'Award',
        'AwardRedeemRequest', function ($scope, $http, RedeemPointsCustomer, Customer, Award, AwardRedeemRequest) {
            var ctrl = this;
            $scope.customer;
            var loadCustomer = function () {
                $scope.customer = Customer.get({id: RedeemPointsCustomer.customer.id});
            };
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
                awardRedeemRequest.$save(function () {
                    $scope.addSuccessMessage('Se ha redimido el premio correctamente');
                    loadCustomer();
                });
            };
            loadCustomer();
        }]);