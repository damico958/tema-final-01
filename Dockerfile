ARG GRADLE_VERSION=7.1.0-jdk
ARG JETTY_VERSION=9.4.44-jdk11
FROM gradle:${GRADLE_VERSION} as gradle
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build

FROM jetty:${JETTY_VERSION} as jetty
COPY --from=gradle home/gradle/src/build/libs/calculator-app.war /var/lib/jetty/webapps/
EXPOSE 8080