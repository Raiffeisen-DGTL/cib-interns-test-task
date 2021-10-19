#!/bin/bash
docker build -f docker/Dockerfile . -t registry.heroku.com/$1/web
docker push registry.heroku.com/$1/web

heroku container:release web -a $1
heroku logs -a $1 --tail
