package org.spring.childrenservice.persistance.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
@Table(name = "children")
public class Children {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    private String name;

//    private int training; // засетать на ребенка
    //посмотреть в трейнинги
    //и по рест темплейту запросить из их табилцы
    //в чилдренах должен быть ендпоинт берет айди ребенка и передает в методе который запрашивает у трайнинга айди.

//    @OneToOne
//    @JoinTable(name = "training",
//            schema = "trainingdb",
//            joinColumns = @JoinColumn(name = "id"))
//    private Training traineeId;
//
//    public Children(String name, Long traineeID) {
//    }
}
