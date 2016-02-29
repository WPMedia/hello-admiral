'use strict';

(function (root, angular) {

    angular.module('hello.config', [])
        .constant('helloConfig', {
            helloApiPath: '${HELLO_API}'
        });

}(window, angular));