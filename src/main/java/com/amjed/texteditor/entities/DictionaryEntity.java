package com.amjed.texteditor.entities;

import com.amjed.texteditor.models.dictionary.DictionaryTrie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "dictionary_entities")

public class DictionaryEntity {
    private Date created;
    private Date updated;
    private String createdBy;
    private String updatedBy;
    private String ipAddress;
    private Integer port;
    private String URL;
    private DictionaryTrie dictionaryTrie;


}
