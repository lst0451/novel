package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "publisher")
@Data
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @OneToMany(mappedBy = "publisher")
    @JsonManagedReference
    private List<Novel> novelList;

    private String name;
    private String type;
    private String location;
    private String remark;
}
