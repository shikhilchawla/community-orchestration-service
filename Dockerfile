FROM openjdk:17
COPY target/CommunityOrchestrationService-0.0.1-SNAPSHOT.jar community-orch-svc.jar
CMD ["sh", "-c", "java -jar /community-orch-svc.jar"]