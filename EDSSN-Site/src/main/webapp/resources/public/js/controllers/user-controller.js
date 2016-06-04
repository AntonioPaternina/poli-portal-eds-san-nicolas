angular.module('edssnApp')
    .controller('UserController', ['$scope', 'UsersService', function ($scope, UsersService) {
        var columnDefs = [{
            name: 'Usuario',
            field: 'username',
            enableHiding: false
        }, {
            name: 'No. Identificación',
            field: 'nationalId',
            enableHiding: false
        }, {
            name: 'Nombre Completo',
            field: 'fullName',
            enableHiding: false
        }, {
            name: 'e-mail',
            field: 'email',
            enableHiding: false
        }
        ];

        $scope.vm.users = UsersService.query();

        $scope.gridOpts = {
            columnDefs: columnDefs,
            data: $scope.vm.users,
            enableFiltering: true,
            enableRowSelection: true,
            showSelectionCheckbox: true,
            multiSelect: false,
            enableSelectAll: false,
            noUnselect: true
        };

        $scope.gridOpts.onRegisterApi = function(gridApi){
            $scope.gridApi = gridApi;
            gridApi.selection.on.rowSelectionChanged($scope,function(row){
                $scope.selectedItem = row.entity;
            });
        };

        $scope.redimirPuntos = function() {
            window.alert('Redimir puntos para usuario: ' + $scope.selectedItem.username);
        }

    }]);
