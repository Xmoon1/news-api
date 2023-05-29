package com.news.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Data
@Entity
@Table(name = "news")
@AllArgsConstructor
@NoArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(Views.List.class)
    private Long id;

    @Column(name = "name")
    @JsonView(Views.List.class)
    private String name;

    @Column(name = "short_description")
    @JsonView(Views.List.class)
    @NotBlank(message = "Field short description cannot be empty")
    private String shortDescription;
    @Column(name = "full_description")
    @NotBlank(message = "Field full description cannot be empty")
    @JsonView(Views.List.class)
    private String fullDescription;


    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "news_type_id")
    @JsonView(Views.List.class)
    private NewsType newsType;
}
