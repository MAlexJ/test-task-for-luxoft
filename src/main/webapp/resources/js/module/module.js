'use strict';

var myApp = angular.module('myApp', ["ngRoute"]).config(function ($routeProvider, $locationProvider, $logProvider) {

    // debug level
    $logProvider.debugEnabled(true);

    $locationProvider.hashPrefix('');

    $('.datepicker').pickadate({
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 15, // Creates a dropdown of 15 years to control year,
        today: 'Today',
        clear: 'Clear',
        close: 'Ok',
        closeOnSelect: false // Close upon selecting a date,
    });

    // Mobile nav bar
    $('.button-collapse').sideNav({
            menuWidth: 250,
            edge: 'left',
            closeOnClick: true, // Closes side-nav on <a> clicks, useful for Angular/Meteor
            draggable: true,
            onOpen: function (el) {
            },
            onClose: function (el) {
            }
        }
    );

    // ************  Analytics Page  ******************
    $routeProvider.when('/analytics',
        {
            templateUrl: './analytics.html',
            controller: 'analyticsController'
        });

    // ************  Users Page  ******************
    $routeProvider.when('/users',
        {
            templateUrl: './users.html',
            controller: 'usersController'
        });

    // ************  Profile Page  ******************
    $routeProvider.when('/profile',
        {
            templateUrl: './profile.html',
            controller: 'profileController'
        });


    // ************  Status Page  ******************
    $routeProvider.when('/status',
        {
            templateUrl: './status.html',
            controller: 'statusController'
        });

    // ************  Info Page  ******************
    $routeProvider.when('/info',
        {
            templateUrl: './info.html',
            controller: 'infoController'
        });

    // ************  SignIn Page  ******************
    $routeProvider.when('/signin',
        {
            templateUrl: './signin.html',
            controller: 'signInController'
        });

    // ************  SignUp Page  ******************
    $routeProvider.when('/signup',
        {
            templateUrl: './signup.html',
            controller: 'signUpController'
        });


    // ************  Default Page  ******************
    $routeProvider.otherwise({redirectTo: '/analytics'});
});

var myAdmin = angular.module('myAdmin', ["ngRoute"]).config(function ($routeProvider, $locationProvider) {

    $locationProvider.hashPrefix('');

    // ************  Home Page  ******************
    $routeProvider.when('/home',
        {
            templateUrl: './admin/adminHome.html',
            controller: 'adminHomeController'
        });

    // ************  Create Page  ******************
    $routeProvider.when('/create',
        {
            templateUrl: './admin/adminCreate.html',
            controller: 'adminCreateController'
        });

    // ************  Update Page  ******************
    $routeProvider.when('/update',
        {
            templateUrl: './admin/adminUpdate.html',
            controller: 'adminUpdateController'
        });

    // ************  Delete Page  ******************
    $routeProvider.when('/delete',
        {
            templateUrl: './admin/adminDelete.html',
            controller: 'adminDeleteController'
        });

    // ************  View Page  ******************
    $routeProvider.when('/view',
        {
            templateUrl: './admin/adminView.html',
            controller: 'adminViewController'
        });

    // ************  Default Page  ******************
    $routeProvider.otherwise({redirectTo: '/home'});
});