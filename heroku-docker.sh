#!/bin/bash

heroku container:login
heroku create
heroku container:push web
