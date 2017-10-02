'use strict';

/**
 * URL path to controllers
 */
var url_Profile = '/rest/getUserById';
var url_update_Profile = '/rest/update';

/**
 * Profile Controller
 */
myApp.controller('profileController', function ($rootScope, $scope, $http, $log) {

    $log.debug(">>> Profile Controller");

    $scope.user = {};
    $scope.progressBar = true;
    $scope.progressBarButton = false;

    $http({
        method: 'GET',
        url: url_Profile + "?id=" + $rootScope.userId
    }).then(function (success) {
        // success
        if (success.data.status) {
            $scope.user = success.data.user;
        }

        console.log($scope.user);
        console.log(success.data.user);

        $scope.progressBar = false;
    }, function (error) {
        // Materialize.toast(error, 3000, 'rounded')
        $scope.progressBar = false;
    });


    $scope.updateProfile = function (user) {

        $scope.progressBarButton = true;

        $http({
            method: 'POST',
            url: url_update_Profile,
            headers: {'Content-Type': 'application/json'},
            data: user
        }).then(function (success) {


            Materialize.toast(success.data.message, 3000, 'rounded')
            $scope.progressBarButton = false;
        }, function (error) {
            Materialize.toast(error, 3000, 'rounded')
            $scope.progressBarButton = false;
        });

    }

});