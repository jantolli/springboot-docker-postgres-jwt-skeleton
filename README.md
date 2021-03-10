# Description
This is a simple Base SpringBoot application with JWT support and PostgreSQL DB.
This CRUD application stores users and stocks purchased by them

<br/>

# How to Configure, Run and Test

## Pull and run PosgreSQL from Docker Hub: 
```
docker run --name postgres-0 -d -e POSTGRES_PASSWORD=postgres -p 5432:5432 postgres:alpine
```

## copy and run initdb.sql inside the container
```
docker cp [BASE_DIR]/src/main/resources/initdb.sql postgres-0:/
```

## Run container:
```
docker exec -it postgres-0
```
<br/>

## Test DB is up and running
### Connect to mydb DB:
```
psql -h localhost -p 5432 -U postgres -f initdb.sql 
```

### Make sure the table users exists:
```
select * from users;
```
