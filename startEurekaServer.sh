mvn clean install
java -jar config-server/target/config-server-0.0.1-SNAPSHOT.jar & sleep 6 && java -jar eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar & sleep 15 && java -jar microservice-demo/target/microservice-demo-0.0.1-SNAPSHOT.jar & sleep 20 && java -jar zuul-service/target/zuul-service-0.0.1-SNAPSHOT.jar
