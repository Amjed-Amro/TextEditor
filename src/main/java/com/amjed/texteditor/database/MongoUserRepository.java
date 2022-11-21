package com.amjed.texteditor.database;

import com.amjed.texteditor.entities.RequestEntity;
import com.amjed.texteditor.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoUserRepository extends MongoRepository<UserEntity, String> {
}
