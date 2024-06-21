package com.example.newspapermanagment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Data
@Entity
@Table(name = "pages")
@Builder
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String editorName;
    private String wordDocument;

    @ManyToOne
    @JoinColumn(name = "newspaper_id")
    private Newspaper newspaper;
}