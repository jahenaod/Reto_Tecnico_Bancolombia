package co.com.bancolombia.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CountryEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Id
    @Column(name = "population")
    private long population;

    @Column(name = "area")
    private double area;
}
