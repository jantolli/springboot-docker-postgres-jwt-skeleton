How to run:

# Pull and run PosgreSQL from Docker Hub: 
docker run --name postgres-0 -d -e POSTGRES_PASSWORD=postgres -p 5432:5432 postgres:alpine

#copy and run initdb.sql inside the container
docker cp /Users/joseantollini/work/Skeleton/skeleton/src/main/resources/initdb.sql postgres-0:/

# Run db container:
docker exec -it postgres-0 bash

# From another terminal:
# Connect to mydb DB:
psql -h localhost -p 5432 -U postgres -f initdb.sql 

# Make sure the table users exists:
select * from users;

# Using docker-compose:
First we need to have the binary:
```
mvn clean package
cd /Users/joseantollini/work/Skeleton/skeleton/
docker-compose up
```


# If image needs to be rebuilt:
/Users/joseantollini/work/Devel/apache-maven-3.6.3/bin/mvn clean package
# If pre-existingin container run:
docker rm skeleton
# If pre-existing image run:
docker image rm skeleton 
# Rebuild image
docker build -t skeleton .
