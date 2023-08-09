package com.amjed.texteditor.models.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String responseCode;
    private String responseMessage;
    private Object response;
}
