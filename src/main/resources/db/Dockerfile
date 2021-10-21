FROM mdillon/postgis:11-alpine
LABEL maintainer="MR"

ENV PGTBLSPACES /var/lib/postgresql/tablespaces && RUN mkdir -p "$PGTBLSPACES" && chown -R postgres:postgres "$PGTBLSPACES" && chmod 700 "$PGTBLSPACES"
