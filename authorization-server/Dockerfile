FROM openjdk:8
ADD target/authorization-server-0.0.1-SNAPSHOT.jar authorization-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","authorization-server-0.0.1-SNAPSHOT.jar"]
#ENTRYPOINT ["./wait-for-it.sh", "auth-db:3306", "--", "java", "-jar", "authorization-server-0.0.1-SNAPSHOT.jar"]
