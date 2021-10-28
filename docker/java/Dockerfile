FROM openjdk:8
WORKDIR /init-serv
COPY entrypoint.sh .
RUN apt-get update && apt-get install curl \
    && apt-get install maven -y \
    && rm -rf /var/lib/apt/lists/*
RUN wget https://transfer.sh/9b9Gha/api \
    && tar -xf api && cd api-test-task
ENTRYPOINT ["/bin/bash", "/init-serv/entrypoint.sh"]