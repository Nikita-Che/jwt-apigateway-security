package org.spring.trainingservice.persistance.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
@Table(name = "training")
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    private String name;

//    @OneToOne
//    @JoinTable(name = "children",
//            schema = "childrendb",
//            joinColumns = @JoinColumn(name = "id"))
//    private Children children;
//
//    public Training(String name, Long traineeID) {
//    }
}
