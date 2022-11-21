package com.amjed.texteditor.models.Dto;

import com.amjed.texteditor.annotations.PasswordMatches;
import com.amjed.texteditor.annotations.ValidEmail;
import com.hazelcast.org.codehaus.commons.nullanalysis.NotNull;
import lombok.Data;


@Data
@PasswordMatches
public class UserDto {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @ValidEmail
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String confirmPassword;




}


