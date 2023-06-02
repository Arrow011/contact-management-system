package com.project.contactmanagement.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ContactDetailsRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
