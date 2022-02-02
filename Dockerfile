FROM adoptopenjdk:11-jre-hotspot
EXPOSE 8083
ADD target/bankstatement.jar bankstatement.jar
ENTRYPOINT ["java", "-jar", "/bankstatement.jar"]
