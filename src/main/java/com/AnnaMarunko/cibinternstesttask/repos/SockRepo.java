package com.AnnaMarunko.cibinternstesttask.repos;

import com.AnnaMarunko.cibinternstesttask.entities.Sock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Sock repo.
 */
@Repository
public interface SockRepo extends JpaRepository<Sock, Long> {

    /**
     * Find by color and cotton part equals list.
     *
     * @param color      the color
     * @param cottonPart the cotton part
     * @return the list
     */
    public List<Sock> findByColorAndCottonPartEquals(String color, Integer cottonPart);


    /**
     * Find by color and cotton part greater than list.
     *
     * @param color      the color
     * @param cottonPart the cotton part
     * @return the list
     */
    public List<Sock> findByColorAndCottonPartGreaterThan(String color, Integer cottonPart);


    /**
     * Find by color and cotton part less than list.
     *
     * @param color      the color
     * @param cottonPart the cotton part
     * @return the list
     */
    public List<Sock> findByColorAndCottonPartLessThan(String color, Integer cottonPart);

}
