package com.amjed.texteditor.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "request_response_log")

public class RequestEntity {
    private Object request;
    private Date requestDate;
    private String user;
    private String ipAddress;
    private String URL;
    private Integer port;
    private Object response;
    private Date responseDate;


}
