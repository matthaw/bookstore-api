# Bookstore API

This API is responsible for controlling books in a library, there API provides the features needed to manage books in a bookstore.

### requirements:

1. Java 11
2. PostgreSQL

#### If you want the magic way, you just need docker is docker compose

1. Docker and Docker compose

## Settings

```shell
    cp .env.example .env
    
    # Open the .env file and configure your settings
    DB_NAME=database_name
    DB_USER=username
    DB_PASSWORD=password
    TIMEZONE=America/Sao_Paulo # Or different according to your region or country
```

## Install

#### If you use the magic path, just run

```shell
  docker-compose up --build -d
```

#### manually
You need to configure postgreSQL according to your system.

```shell
export $(cat .env | xargs) # if you are using linux, list the environment variables for the application.properties file
mvn spring-boot:run
```
