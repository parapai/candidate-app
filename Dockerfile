# ==========================================
# STAGE 1: Build Aplikasi dengan Maven & Java 25
# ==========================================
FROM maven:3.9.11-eclipse-temurin-25 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn dependency:go-offline

RUN mvn clean package -DskipTests

# ==========================================
# STAGE 2: Jalankan .war di Tomcat Server dengan Java 25
# ==========================================
FROM tomcat:10.1-jdk25-temurin-jammy

RUN rm -rf /usr/local/tomcat/webapps/ROOT \
           /usr/local/tomcat/webapps/ROOT.war

COPY --from=builder /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 9021

CMD ["catalina.sh", "run"]