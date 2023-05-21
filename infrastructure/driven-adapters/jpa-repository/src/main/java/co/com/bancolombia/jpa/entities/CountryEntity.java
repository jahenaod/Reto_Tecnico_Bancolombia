package co.com.bancolombia.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_sequence")
    @SequenceGenerator(name = "country_sequence", sequenceName = "custom_sequence_name")
    private long population;

    @Column(name = "area")
    private double area;
}
