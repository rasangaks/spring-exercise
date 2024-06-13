package org.rasanga.truproxyapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String premises;
    @JsonProperty("address_line_1")
    private String addressLine1;
    private String locality;
    private String country;
    @JsonProperty("postal_code")
    private String postalCode;
}
