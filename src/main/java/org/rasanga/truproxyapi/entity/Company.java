package org.rasanga.truproxyapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Filter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "company_number")
    private Integer id;
    @Column(name = "title")
    private String name;
    @Column(name = "company_type")
    private String type;
    @Enumerated(EnumType.STRING)
    @Column(name = "company_status")
    private CompanyStatus status;
    @CreationTimestamp
    @Column(name = "date_of_creation")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdOn;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="company_id")
    private List<Officer> officers;
}
