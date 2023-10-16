# Use the official PostgreSQL image as the base image
FROM postgres:13

# Set environment variables for the PostgreSQL database
ENV POSTGRES_DB=users
ENV POSTGRES_USER=usersubr
ENV POSTGRES_PASSWORD=userspassword

# Expose a local port for your Spring Boot application to connect to
EXPOSE 5432

# Optionally, copy a custom initialization script to the container
COPY src/main/resources/sql/init.sql /docker-entrypoint-initdb.d/