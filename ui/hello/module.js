'use strict';

(function (angular) {
    angular.module('helloApp', ['ui.router', 'hello.config'])
        .config(["$stateProvider", "$locationProvider", function ($stateProvider, $locationProvider) {
            $locationProvider.html5Mode({
                enabled: true
            });

            $stateProvider
                .state('hello', {
                    url: '/',
                    templateUrl: 'views/hello.html',
                    controller: 'helloCtrl'
                })
        }]);
}(angular));