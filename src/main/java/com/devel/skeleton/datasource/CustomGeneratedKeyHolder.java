package com.devel.skeleton.datasource;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.util.UUID;

public class CustomGeneratedKeyHolder extends GeneratedKeyHolder {

    public UUID getUUIDKey() throws InvalidDataAccessApiUsageException, DataRetrievalFailureException {
        return (UUID) this.getKeyAs(UUID.class);
    }
}
