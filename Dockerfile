# =========================
# Stage 1: Build WAR using Maven
# =========================
FROM maven:3.9.6-eclipse-temurin-8 AS builder

WORKDIR /app

# Copy pom and source
COPY pom.xml .
COPY src ./src

# Build WAR
RUN mvn clean package -DskipTests


# =========================
# Stage 2: Run WAR on Tomcat
# =========================
FROM tomcat:9.0-jdk8-temurin

# Remove default apps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy WAR from builder stage
COPY --from=builder /app/target/ROOT.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
