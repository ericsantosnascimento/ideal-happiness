FROM anapsix/alpine-java:jre8

RUN wget http://repo1.maven.org/maven2/org/flywaydb/flyway-commandline/3.2.1/flyway-commandline-3.2.1.tar.gz -q
RUN tar -xvzf flyway-commandline-3.2.1.tar.gz
ENV FLYWAY_HOME flyway-3.2.1

RUN echo "\$FLYWAY_HOME/flyway migrate -schemas=\$DATABASE_SCHEMAS -url=\"jdbc:postgresql://\$DATABASE_HOST:5432/postgres\" -user=\$DATABASE_USER -password=\$DATABASE_PASSWORD" > start.sh && chmod +x start.sh

CMD start.sh
