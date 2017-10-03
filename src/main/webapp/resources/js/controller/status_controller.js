'use strict';

/**
 * URL path to controllers
 */
var url_get_status = '/rest/getStatusById';
var url_change_status = '/rest/changeStatus';

/**
 * Status Controller
 */
myApp.controller('statusController', function ($rootScope, $scope, $http, $log) {

    $log.debug(">>> status Controller");
    $scope.progressBar = true;
    $scope.history = {};

    $http({
        method: 'GET',
        url: url_get_status + "?id=" + $rootScope.userId
    }).then(function (success) {

        // success
        if (success.data.status) {
            $scope.history = success.data.history;
        }

        $scope.progressBar = false;
    }, function (error) {
        // Materialize.toast(error, 3000, 'rounded')
        $scope.progressBar = false;
    });

    $scope.changeStatus = function () {

        $http({
            method: 'POST',
            url: url_change_status,
            headers: {'Content-Type': 'application/json'},
            data: $scope.history
        }).then(function (success) {

            $scope.history = success.data.history;

            Materialize.toast(success.data.message, 3000, 'rounded')

        }, function (error) {
            Materialize.toast(error, 3000, 'rounded')
        });

    }


});