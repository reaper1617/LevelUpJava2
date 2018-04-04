
package ru.gerasimchuk.homework6repoforcountry.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.gerasimchuk.homework6repoforcountry.domain.Country;

import java.util.Collection;
import java.util.List;

public class CountryRepositoryImpl implements CountryRepository {

    private final SessionFactory factory;

    public CountryRepositoryImpl(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public Country create(String name, String capital, double population) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Country country = new Country();
        country.setName(name);
        country.setCapital(capital);
        country.setPopulation(population);
        session.persist(country);
        transaction.commit();
        session.close();
        return country;
    }

    @Override
    public Country update(int id, String name, String capital, double population) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Country country = session.get(Country.class, id);
        country.setName(name);
        country.setCapital(capital);
        country.setPopulation(population);
        transaction.commit();
        session.close();
        return country;
    }

    @Override
    public void delete(int id) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Country country = session.get(Country.class, id);
        session.delete(country);
        transaction.commit();
        session.close();
    }

    @Override
    public Collection<Country> findAll() {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        List list = session.createQuery("FROM Country").list();
        transaction.commit();
        session.close();
        return list;
    }


}
