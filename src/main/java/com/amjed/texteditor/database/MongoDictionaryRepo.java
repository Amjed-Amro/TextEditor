package com.amjed.texteditor.database;


import com.amjed.texteditor.entities.DictionaryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoDictionaryRepo extends MongoRepository<DictionaryEntity, String> {
    public Optional<DictionaryEntity> findAllByCreatedBy(String createdBy);
}
