package com.amjed.TextEditor.database;

import com.amjed.TextEditor.entities.RequestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoRequestsRepo extends MongoRepository<RequestEntity, String> {
}
