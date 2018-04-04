package ru.gerasimchuk.homework6repoforcountry.domain;


import javax.persistence.*;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country-id-gen")
    @SequenceGenerator(name = "country-id-gen", initialValue = 10000, allocationSize = 20)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String capital;

    @Column(nullable = false, unique = true)
    private double population;

    public Country() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public double getPopulation() {
        return population;
    }

    public void setPopulation(double population) {
        this.population = population;
    }
}
