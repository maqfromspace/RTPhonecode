FROM openjdk
EXPOSE 8080:8080
ADD target/RTPhonecode-1.0-SNAPSHOT.jar RTPhonecode.jar
ENTRYPOINT ["java", "-jar", "RTPhonecode.jar"]