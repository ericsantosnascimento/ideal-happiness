FROM anapsix/alpine-java:jre8
ADD target/acme-api-*.jar app.jar
RUN bash -c 'touch app.jar'

RUN echo "if [ "\$DEBUG_PORT" ]; then DEBUG_OPTS=\"-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=\$DEBUG_PORT\"; fi " >> start.sh
RUN echo -n "java \$DEBUG_OPTS " >> start.sh
RUN echo -n "-Djava.security.egd=file:/dev/./urandom -jar app.jar" >> start.sh

RUN chmod +x start.sh

CMD ["start.sh"]