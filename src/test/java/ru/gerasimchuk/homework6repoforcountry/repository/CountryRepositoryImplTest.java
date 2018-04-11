package ru.gerasimchuk.homework6repoforcountry.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Matchers;

import ru.gerasimchuk.homework6repoforcountry.domain.Country;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.*;

public class CountryRepositoryImplTest {

    private CountryRepository repository;
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    @Before
    public void setup(){
        sessionFactory = mock(SessionFactory.class);
        session = mock(Session.class);
        transaction = mock(Transaction.class);
        repository = new CountryRepositoryImpl((sessionFactory));
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);

    }


    @Test
    public void testCreate_validData_returnSavedObject(){
        String name = "Russia";
        String capital = "Moscow";
        double population = 147_000_000d;

        Country country = repository.create(name,capital,population);
        assertEquals(name,country.getName());
        assertEquals(capital,country.getCapital());
        assertEquals(population, country.getPopulation(), 0.2d);
        verify(session, times(1)).persist(Matchers.any(Country.class));
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    public void testUpdate_validData_returnUpdatedObject(){
        int id = 10001;
        String name = "USA";
        String capital = "Washingtone";
        double population = 123_341_030d;
//        Country country = new Country();
//        country.setId(id);
//        country.setName(name);
//        country.setCapital(capital);
//        country.setPopulation(population);
        Country country = repository.update(id,name,capital,population);
//        assertEquals(name,country.getName());
//        assertEquals(capital,country.getCapital());
//        assertEquals(population, country.getPopulation(), 0.2d);
        verify(session, times(1)).get(Country.class,id);
        verify(transaction).commit();
        verify(session).close();
    }


    @Test
    public void testDelete_validId_returnVoid(){
        int id = 10001;
        repository.delete(id);
        verify(session, times(1)).get(Country.class,id);
        verify(session, times(1)).delete(Matchers.any(Country.class));
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    public void testFindAll_void_returnCollection(){
        Object object = repository.findAll();
        verify(session, times(1)).createQuery(Matchers.anyString());
        verify(transaction).commit();
        verify(session).close();
    }
}
