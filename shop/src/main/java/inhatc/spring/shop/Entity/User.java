package inhatc.spring.shop.Entity;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto_Increment를 해줌
    @Column(name = "user_id")
    private Long id;

    @Column(length = 30)
    private String name;

}
