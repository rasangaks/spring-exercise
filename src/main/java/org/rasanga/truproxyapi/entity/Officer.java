package org.rasanga.truproxyapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Officer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    private String name;
    @Column(name = "officer_role")
    private String role;
    @Column(name = "appointed_on")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date appointedOn;
    @Column(name = "resigned_on")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private  Date resignedOn;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
}
