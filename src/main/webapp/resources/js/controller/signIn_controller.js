'use strict';

/**
 * URL path to controllers
 */
var url_signin = '/rest/signin';

/**
 * SignIn Page
 */
myApp.controller('signInController', function ($rootScope, $scope, $http, $log) {

    $log.debug(">>> SignIn Controller");

    $scope.user = {};
    $scope.progressBar = false;

    $scope.signIn = function (user) {

        $scope.progressBar = true;

        $http({

            method: 'POST',
            url: url_signin,
            headers: {'Content-Type': 'application/json'},
            data: {'email': user.email, 'password': user.password}

        }).then(function (success) {

            // success
            if (success.data.status) {
                $rootScope.isLogin = true;
                $rootScope.userId = success.data.user.id;
            }

            Materialize.toast(success.data.message, 3000, 'rounded')
            $scope.progressBar = false;

        }, function (error) {
            Materialize.toast(error, 3000, 'rounded')
            $scope.progressBar = false;
        });
    }

});