FROM openjdk
ADD target/trai-quotations.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]