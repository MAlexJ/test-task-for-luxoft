'use strict';

/**
 * URL path to controllers
 */
var url_signup = '/rest/signup';

/**
 * SignUp Page
 */
myApp.controller('signUpController', function ($rootScope, $scope, $http, $log) {

    // log
    $log.debug(">>> SignUp Controller");

    $scope.user = {};
    $scope.progressBar = false;

    // POST:
    $scope.signUp = function (user) {

        $scope.progressBar = true;

        $http({
            method: 'POST',
            url: url_signup,
            headers: {'Content-Type': 'application/json'},
            data: user
        }).then(function (success) {

            // success
            if (success.data.status) {
                $rootScope.isLogin = true;
                console.log(success.data.user.id);
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