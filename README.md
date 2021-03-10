How to run:

# Pull and run PosgreSQL from Docker Hub: 
docker run --name postgres-0 -d -e POSTGRES_PASSWORD=postgres -p 5432:5432 postgres:alpine

#copy and run initdb.sql inside the container
```
docker cp [BASE_DIR]/src/main/resources/initdb.sql postgres-0:/
```

# Run container:
```
docker exec -it postgres-0
```

# From another terminal:
# Connect to mydb DB:
```
psql -h localhost -p 5432 -U postgres -f initdb.sql 
```

# Make sure the table users exists:
```
select * from users;
```
