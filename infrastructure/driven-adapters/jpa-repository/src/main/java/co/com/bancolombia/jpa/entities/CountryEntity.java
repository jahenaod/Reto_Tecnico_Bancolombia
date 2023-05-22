package co.com.bancolombia.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@Table(name="logger2")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
public class CountryEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "logger2_seq")
    @SequenceGenerator(name = "logger2_seq", sequenceName = "logger2_seq", allocationSize = 1)
    private BigInteger id;


    @Column(name = "population")
    private long population;

    @Column(name = "area")
    private double area;

    @Column(name = "name")
    private String name;
}