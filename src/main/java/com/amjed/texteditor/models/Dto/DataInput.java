package com.amjed.texteditor.models.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DataInput {
    private String firstWord;
    private String secondWord;
    private String text;
    private Integer number;
    private Integer number2;
    private String fromDate;
    private String toDate;

}
