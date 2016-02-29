# This is a Dockerfile to create an image with the Hello Admiral app.

# VERSION       1.0

FROM quay.io/washpost/docker-maven-dropwizard:java8-mvn3-dw_0_8_1-v2

WORKDIR /hello-admiral/api

ADD api/pom.xml /hello-admiral/api/pom.xml
ADD ./maven/settings.xml /hello-admiral/maven/
# Download dependencies without any source files
RUN mvn -s /hello-admiral/maven/settings.xml -Dcheckstyle.skip=true -Dmaven.test.skip=true -Dsource.skip=true -Dmaven.javadoc.skip=true install

ADD api /hello-admiral/api
ADD ./maven/settings.xml /hello-admiral/maven/
RUN mvn -s /hello-admiral/maven/settings.xml -Dcheckstyle.skip=true -Dmaven.test.skip=true -Dsource.skip=true -Dmaven.javadoc.skip=true package

CMD ["java", "-Xmx1024m", "-Xms512m", "-jar", "/hello-admiral/api/target/hello-admiral.jar", "server", "/hello-admiral/api/hello-admiral.yml"]
