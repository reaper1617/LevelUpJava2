package ru.gerasimchuk.homework6repoforcountry.repository;

import ru.gerasimchuk.homework6repoforcountry.domain.Country;

import java.util.Collection;

public interface CountryRepository {
    Country create(String name, String capital, double population);

    Country update(int id, String name, String capital, double population);

    void delete(int id);

   Collection<Country> findAll();

}
