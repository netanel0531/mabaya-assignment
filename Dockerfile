FROM eclipse-temurin:17.0.7_7-jre-jammy
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
EXPOSE 5005
ARG JAR_FILE=*.jar
COPY /target/${JAR_FILE} application.jar
