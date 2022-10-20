package com.amjed.TextEditor.database;


import com.amjed.TextEditor.entities.DictionaryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoDictionaryRepo extends MongoRepository<DictionaryEntity, String> {
}
