package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

//Product details
//        Paperback: 288 pages
//        Publisher: Harper (May 14 2019)
//        Language: English
//        ISBN-10: 0062888463
//        ISBN-13: 978-0062888464
//        Product Dimensions: 14 x 1.8 x 21 cm
//        Shipping Weight: 200 g
//        Average Customer Review: 4.2 out of 5 stars   41 customer reviews
//        Amazon Bestsellers Rank: #107 in Books (See Top 100 in Books)
@Entity
@Table(name = "novel")
public class Novel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String name;

    @ManyToOne(cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name = "author_id")
    @JsonBackReference
    private Author author;

    @ManyToOne(cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name = "publisher_id")
    @JsonBackReference
    private Publisher publisher;

    private Date publishDate;

    private BigDecimal price;

    private Integer pages;
    private String isbn;
    private String language;
    private Integer weight;
    private String summary;
}
