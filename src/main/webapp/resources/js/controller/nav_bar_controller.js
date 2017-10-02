'use strict';

/**
 *  Nav Bar Controller
 */
myApp.controller('navBarController', function ($window, $rootScope, $scope, $http, $log) {

    $log.debug(">>> Nav Bar Controller");

    // signout
    $rootScope.isLogin = false;
    $scope.signout = function () {
        $rootScope.isLogin = false;
        $window.location.href = '#/analytics';
    };

    $rootScope.userId = '';

});