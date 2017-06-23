package com.five.globalSearch.dao;

import com.five.cinema.model.Cinema;
import com.five.film.model.Film;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by haoye on 17-6-21.
 */
@Repository
@Transactional
public class GlobalSearchDaoImpl implements GlobalSearchDao {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    public List cinemaSearch(String text) {
        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);

        QueryBuilder qb = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Cinema.class).get();

        System.out.print(text);
        org.apache.lucene.search.Query query = qb
                .keyword()
                .fuzzy()
                .withThreshold(0.7f)
                .onFields("name", "address")
                .matching(text)
                .createQuery();

        javax.persistence.Query persistenceQuery =
                fullTextEntityManager.createFullTextQuery(query, Cinema.class);

        List result = persistenceQuery.getResultList();

        return result;
    }

    public List filmSearch(String text) {
        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);

        QueryBuilder qb = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Film.class).get();

        org.apache.lucene.search.Query query = qb
                .keyword()
                .fuzzy()
                .withThreshold(0.7f)
                .onFields("name")
                .matching(text)
                .createQuery();

        javax.persistence.Query persistenceQuery =
                fullTextEntityManager.createFullTextQuery(query, Film.class);

        List result = persistenceQuery.getResultList();

        return result;
    }
}
