FROM openjdk:8
WORKDIR /init-serv
COPY entrypoint.sh .
RUN apt-get update && apt-get install curl \
    && apt-get install maven -y \
    && rm -rf /var/lib/apt/lists/*
RUN wget https://github.com/niki75jr/cib-interns-test-task/archive/refs/tags/v.1.0.0-rc0.tar.gz -O - | tar -xz \
    && cd cib-interns-test-task-v.1.0.0-rc0
ENTRYPOINT ["/bin/bash", "/init-serv/entrypoint.sh"]