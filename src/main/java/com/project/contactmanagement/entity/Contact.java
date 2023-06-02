package com.project.contactmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Contact")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long contactId;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "Email", unique = true)
    private String email;
    @Column(name = "PhoneNumber")
    private String phoneNumber;
}
