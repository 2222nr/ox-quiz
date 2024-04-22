package com.example.oxquiz.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="Quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String question;

    @Column(length = 1, nullable = false)
    private int answer;

    @Column(nullable = false)
    private String writer;
}
