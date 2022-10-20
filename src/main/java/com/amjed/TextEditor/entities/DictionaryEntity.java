package com.amjed.TextEditor.entities;

import com.amjed.TextEditor.models.dictionary.DictionaryTrie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private Double port;
    private String URL;
    private DictionaryTrie dictionaryTrie;
}
