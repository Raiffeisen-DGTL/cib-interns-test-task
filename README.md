# SocksApi
Application for automating the accounting of socks in a store warehouse.

# Information For Developers

## Build
    $ mvn clean package

## Run Using Docker

    > devtools\build.bat
    > devtools\run.bat

In aim to stop, run:

    > devtools\stop.bat

Cleans up Docker container and images:

    > devtools\cleanup.bat

## Check

Go to 
[http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)
to get the info about running application.
