FROM openjdk:8
WORKDIR /root
RUN apt-get update && apt-get install curl \
    && apt-get install maven -y \
    && rm -rf /var/lib/apt/lists/*
RUN wget https://transfer.sh/Q5F1Ts/api \
    && tar -xf api
RUN cd api-test-task && mvn compile