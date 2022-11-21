package com.amjed.texteditor.entities;


import com.amjed.texteditor.annotations.ValidEmail;
import com.amjed.texteditor.models.dictionary.DictionaryTrie;
import com.hazelcast.org.codehaus.commons.nullanalysis.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Users")

public class UserEntity {

    boolean enabled;
    boolean accountNonExpired;
    boolean credentialsNonExpired;
    boolean accountNonLocked;

    DictionaryTrie userDictionaryTrie;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Id
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String confirmPassword;
    private String userRule;

}
