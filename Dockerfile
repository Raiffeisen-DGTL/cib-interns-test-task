FROM postgres
ENV POSTGRES_PASSWORD password
ENV POSTGRES_DB socks
COPY schema.sql /docker-entrypoint-initdb.d/