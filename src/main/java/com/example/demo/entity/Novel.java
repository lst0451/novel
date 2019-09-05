package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "novel")
@Getter
@Setter
public class Novel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "author_id")
    @JsonBackReference
    private Author author;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "publisher_id")
    @JsonBackReference
    private Publisher publisher;

    private LocalDate publishDate;

    private BigDecimal price;

    private Integer pages;
    private String genre;
    private String isbn;
    private String language;
    private Integer weight;

    @Column(length = 1024)
    private String remark;
    private String productDimensions;
}
