package com.example.newspapermanagment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "newspapers")
@Builder
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class Newspaper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String title;
    private String editorName;
    private String coverImage;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "newspaper")
    private List<Page> pages;
}

