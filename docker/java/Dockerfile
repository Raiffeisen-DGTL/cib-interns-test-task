FROM openjdk:8
RUN apt-get update && apt-get install curl \
    && apt-get install maven -y \
    && apt-get install tree -y \
    && rm -rf /var/lib/apt/lists/*
WORKDIR /init-serv
COPY entrypoint.sh /init-serv
ENTRYPOINT ["/bin/bash", "/init-serv/entrypoint.sh"]