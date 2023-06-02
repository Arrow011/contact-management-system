package com.project.contactmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactDetailsResponse {
    private Long contactId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
