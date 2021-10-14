if [ -z "$JDBC_DATABASE_URL" ]; then
    echo "[INFO] Extracting JDBC_DATABASE_URL from Heroku DATABASE_URL"

    # extract the protocol
    proto="`echo $DATABASE_URL | grep '://' | sed -e's,^\(.*://\).*,\1,g'`"
    # remove the protocol
    url=`echo $DATABASE_URL | sed -e s,$proto,,g`

    echo "[INFO] protocol: "$proto

    # extract the user and password (if any)
    userpass="`echo $url | grep @ | cut -d@ -f1`"
    pass=`echo $userpass | grep : | cut -d: -f2`
    if [ -n "$pass" ]; then
        user=`echo $userpass | grep : | cut -d: -f1`
    else
        user=$userpass
    fi

    echo "[INFO] password= "$pass
    echo "[INFO] user= "$user

    # extract the host -- updated
    hostport=`echo $url | sed -e s,$userpass@,,g | cut -d/ -f1`
    port=`echo $hostport | grep : | cut -d: -f2`
    if [ -n "$port" ]; then
        host=`echo $hostport | grep : | cut -d: -f1`
    else
        host=$hostport
    fi

    echo "[INFO] host= "$host
    echo "[INFO] port= "$port

    # extract the path (if any)
    db_name="`echo $url | grep / | cut -d/ -f2-`"

    echo "[INFO] path= "$path

    export JDBC_DATABASE_URL="jdbc:postgresql://${host}:${port}/${db_name}"
    export JDBC_DATABASE_USERNAME=$user
    export JDBC_DATABASE_PASSWORD=$pass
else
   echo "[INFO] Using predefined JDBC_DATABASE_URL"
fi

/usr/bin/java -XX:+UnlockExperimentalVMOptions \
              -XX:+UseCGroupMemoryLimitForHeap \
              -Xmx256m -Xss512k -XX:MetaspaceSize=100m \
              -jar /apps/app.jar