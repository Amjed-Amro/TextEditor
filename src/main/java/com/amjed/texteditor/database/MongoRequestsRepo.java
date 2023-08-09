package com.amjed.texteditor.database;

import com.amjed.texteditor.entities.RequestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoRequestsRepo extends MongoRepository<RequestEntity, String> {
}
