package com.mbe.reactive.entities;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="students")
public class Student {

    @Id
    private Long id;
    @Column("firstname")
    private String firstName;
    @Column("lastname")
    private String lastName;
    private int age;
}
