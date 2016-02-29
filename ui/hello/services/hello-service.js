'use strict';

(function (angular) {

    angular.module('helloApp')

        .factory('helloService', ['httpWithAuth', '$q', 'helloConfig', '$rootScope',
            function (httpWithAuth, $q, helloConfig, $rootScope) {
                var helloUrl = helloConfig.helloApiPath;
                var helloService = {

                    setUserInfo : function (userInfo) {
                        localStorage.setItem('admiral.auth.userinfo', angular.toJson(userInfo));

                        userInfo.isEnabled = function () {
                            return userInfo.enabled;
                        }

                        userInfo.hasAuthority = function (authority) {
                            return userInfo.roles.indexOf(authority) != -1;
                        };

                        return userInfo;
                    },

                    isTimedOut : function (date) {
                        if (date === undefined || date === null || date === "''") {
                            return false;
                        }
                        var dateArray = date.split(/[^0-9]/);
                        var date = new Date(dateArray[0],dateArray[1]-1,dateArray[2],dateArray[3],dateArray[4],dateArray[5]);
                        return date > new Date();
                    },

                    getHelloUser : function (){
                        return httpWithAuth({
                            method: "GET",
                            url: helloUrl + '/api/hello-admiral/userinfo'
                        });
                    }

                };

                return helloService;

            }])

        .factory('httpWithAuth', ['$rootScope', '$http', '$q', function ($rootScope, $http, $q) {
            return function(config, onSuccess, onReject) {
                // if token is not passed in, set it on the request
                if (!config.headers) {
                    config.headers = {};
                }

                if (!config.headers["X-Auth-Token"]) {
                    var token = localStorage.getItem('admiral.auth.token');
                    config.headers["X-Auth-Token"] = token;
                }

                onSuccess = onSuccess || function (response) {
                    return response.data;
                };

                onReject = onReject || function (response) {
                    var errorData = response.data;
                    var message = (errorData && errorData.message) ? errorData.message : "Unknown error.";
                    $rootScope.$broadcast("admiral.messenger.error", message);
                    return $q.reject(response);
                };

                return $http(config).then(onSuccess, function (response) {
                    if (response.status == 401) {
                        localStorage.removeItem('admiral.auth.token');
                        localStorage.removeItem('admiral.auth.token.expired');
                        localStorage.removeItem('admiral.auth.userinfo');
                        localStorage.setItem('admiral.auth.redirectToUrl', window.location.href);
                        window.location.href = '/signin';
                        return $q.reject(response);
                    } else {
                        return onReject(response)
                    }
                });
            }
        }]);

}(angular));