package org.rasanga.truproxyapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @JsonProperty("company_number")
    private Integer id;
    @JsonProperty("title")
    private String name;
    @JsonProperty("company_type")
    private String type;
    @JsonProperty("company_status")
    private CompanyStatus status;
    @JsonProperty("date_of_creation")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date createdOn;
    private Address address;
    private List<Officer> officers;
}
