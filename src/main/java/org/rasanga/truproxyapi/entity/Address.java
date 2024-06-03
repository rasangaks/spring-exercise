package org.rasanga.truproxyapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Addresss {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    private String premises;
    @Column(name = "address_line_1")
    private String addressLine1;
    private String locality;
    private String country;
    @Column(name = "postal_code")
    private String postalCode;
}
