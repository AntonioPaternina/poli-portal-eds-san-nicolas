angular.module('edssnApp')
    .factory('PromotionCode', ['$resource', function ($resource) {
        return $resource('/promotion-codes/:id', {id: '@id'}, {
            queryPagination: {
                method: 'POST',
                params: {
                    page: 0,
                    size: 25,
                    sort: null,
                    content: null
                },
                isArray: false
            },
            update: {
                method: 'PUT'
            }
        });
    }])
    .factory('SelectedPromotionCode', function () {
        return {item: null}
    });