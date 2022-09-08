package com.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailFields {
    private String fromEmail;

    private String toEmail;

    private String body;

    private String subject;
}
