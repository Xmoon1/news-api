package com.news.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "news_type")
@AllArgsConstructor
@NoArgsConstructor
public class NewsType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
//    @JsonView(Views.List.class)
    private Long id;

    @Column(name = "name")
    @Size(max = 70, message = "Field name cannot be lager than 70 symbols")
    @NotBlank(message = "Field name cannot be empty")
    @JsonView(Views.List.class)
    private String typeName;

    @Column(name = "color")
    @NotBlank(message = "Field color cannot be empty")
    @JsonView(Views.List.class)
    private String typeColor;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "newsType")
    private List<News> newsList;

}
