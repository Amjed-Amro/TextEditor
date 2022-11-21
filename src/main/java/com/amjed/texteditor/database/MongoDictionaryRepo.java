package com.amjed.texteditor.database;


import com.amjed.texteditor.entities.DictionaryEntity;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoDictionaryRepo extends MongoRepository<DictionaryEntity, String> {
}
