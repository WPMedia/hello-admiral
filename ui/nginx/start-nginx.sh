#!/bin/bash
cat <<- EOF > /hello-admiral/config/properties.js
	'use strict';

	(function (root, angular) {

	    angular.module('hello.config', [])
	        .constant('helloConfig', {
	            helloApiPath: '${HELLO_API}'
	        });

	}(window, angular));

EOF

python /hello-admiral/nginx/generate-config.py > /etc/nginx/nginx.conf
exec nginx -g "daemon off;"
