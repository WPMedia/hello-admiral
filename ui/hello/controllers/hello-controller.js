'use strict';

(function (angular) {
    angular.module('helloApp')
        .controller('helloCtrl', ['$rootScope', '$scope', '$location', 'helloService',
            function ($rootScope, $scope, $location, helloService) {

                $scope.backYet = false;
                $scope.name = "...?";
                $scope.userInfo = "";

                var token = localStorage.getItem('admiral.auth.token');
                var expires = localStorage.getItem('admiral.auth.token.expired');

                if ((token != undefined) && (expires != undefined)) {

                    var ok = helloService.isTimedOut(expires);
                    if (!ok ){
                        console.log("User session timed out, redirecting to sign in page.");
                        window.location.href = '/signin';
                    } else {
                        helloService.getHelloUser().then(function(userInfo) {
                            helloService.setUserInfo(userInfo);
                            $scope.userInfo = userInfo;
                            $scope.name = userInfo.user.firstName;
                            $scope.backYet = true;
                        });
                    }
                } else {
                    window.location.href = '/signin';
                }

            }]);
}(angular));