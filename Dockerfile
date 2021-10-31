FROM postgres
ENV POSTGRES_PASSWORD socks-password
ENV POSTGRES_DB socks-db
ENV POSTGRES_USER socks-user
COPY src/main/resources/socks_schema.sql /docker-entrypoint-initdb.d/socks_schema.sql

