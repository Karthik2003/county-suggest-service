package com.fstudio.countysearch.dto;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
@Data
@Entity
public class County implements Serializable {
    @Id
    private Long fips;
    private String state;
    private String name;
}