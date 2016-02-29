# Hello Admiral

## Overview
This is a simple application with an API and a UI that demonstrates how to implement Arc-Auth into an application.

### Running locally
Make sure a Docker machine is running, then go to the root of this project and run:
$ docker-compose build
$ docker-compose up

Log into Admiral in Production and in the local storage, copy the values for 'admiral.auth.token' and 'admiral.auth.token.expired' and paste them into the code below:
localStorage.setItem('admiral.auth.token', [value from Admiral Production]);
localStorage.setItem('admiral.auth.token.expired', [value from Admiral Production]);

Enter that in your browser console window and hit return and the values will be set.

Go to http://localhost.nile.works:9999/hello/ in your browser and you will see a welcome message for the user you logged into Admiral as. Additionally you will see the JSON that will be returned from all Arc Auth calls. The example is meant to show you exactly what values to expect when developing your APIs.

If you don't have valid local storage values in your browser (token and non-expired date), the browser will redirect to /signin (which won't be a valid page). This is meant to demonstrate that if you were coming to this application from the Admiral Dashboard you would already have local storage values set and valid.

### Modifying for your application
The following environment variables must be set in your application for each environment:
	"ARC_AUTH_URI": "https://arc-auth.internal.arc.nile.works" / "https://arc-auth-beta.internal.arc.nile.works" / "https://arc-auth-alpha.internal.arc.nile.works"
    "ARC_AUTH_USER": [YOUR APP USERNAME HERE],
    "ARC_AUTH_PASS": [YOUR APP PASSWORD HERE]

