package co.com.bancolombia.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "country_logger")
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long population;
    private double area;
}
