# Use Tomcat 9 (matches javax.servlet 4.0)
FROM tomcat:9.0-jdk8-temurin

# Remove default apps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy WAR built by Maven
COPY target/ROOT.war /usr/local/tomcat/webapps/ROOT.war

# Expose Railway port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
