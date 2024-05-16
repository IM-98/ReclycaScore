package com.recycla.score.domain.entity;

import com.recycla.score.domain.enums.Materiel;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Objet {
    @SequenceGenerator(
            name = "confirmation_sequence",
            sequenceName = "confirmation_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmation_sequence"
    )
    private Long id;

    @Column(name = "materiel", nullable = false)
    @Enumerated(EnumType.STRING)
    private Materiel materiel;

    @Column(name = "score", nullable = false)
    private int score;

    @Column(name = "description", nullable = false)
    private String description;
}
