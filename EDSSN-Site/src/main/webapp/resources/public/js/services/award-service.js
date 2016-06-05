angular.module('edssnApp')
    .factory('Award', ['$resource', function ($resource) {
        return $resource('/awards/:id', {id: '@id'}, {
            findByMarketingCampaign: {
                url: '/awards',
                method: 'GET',
                params: {
                    marketingCampaignId: null
                },
                isArray: true
            }
        });
    }])
    .factory('AwardRedeemRequest', ['$resource', function ($resource) {
        return $resource('/award-redeem-request/:id', {id: '@id'});
    }]);