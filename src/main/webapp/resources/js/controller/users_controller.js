'use strict';

/**
 * URL path to controllers
 */
var url_users = '/rest/getAllUsers';

/**
 * Users Controller
 */
myApp.controller('usersController', function ($rootScope, $scope, $http, $log) {

    $log.debug(">>> Users Controller");

    $scope.users = [];
    $scope.progressBar = true;

    $http({
        method: 'GET',
        url: url_users
    }).then(function (success) {
        // success
        if (success.data.status) {
            $scope.users = success.data.users;
        }
        $scope.progressBar = false;
    });

});