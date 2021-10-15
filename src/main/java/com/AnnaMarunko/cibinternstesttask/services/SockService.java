package com.AnnaMarunko.cibinternstesttask.services;

import com.AnnaMarunko.cibinternstesttask.entities.Sock;
import com.AnnaMarunko.cibinternstesttask.repos.SockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Sock service.
 */
@Service
public class SockService {

    /**
     * The Sock repo.
     */
    @Autowired
    SockRepo sockRepo;


    /**
     * Create sock.
     *
     * @param sock the sock
     * @return the sock
     */
    public Sock create(Sock sock) {return sockRepo.save(sock);}


    /**
     * Update sock.
     *
     * @param sock the sock
     * @return the sock
     */
    public Sock update(Sock sock) {return sockRepo.save(sock);}


    /**
     * Delete boolean.
     *
     * @param sock the sock
     * @return the boolean
     */
    public Boolean delete(Sock sock) {sockRepo.delete(sock);
    return true;}


    /**
     * Find all list.
     *
     * @return the list
     */
    public List findAll() {return sockRepo.findAll();}


    /**
     * Find optional.
     *
     * @param id the id
     * @return the optional
     */
    public Optional<Sock> find(Long id) {return sockRepo.findById(id);}


    /**
     * Find by color and equals list.
     *
     * @param color      the color
     * @param cottonPart the cotton part
     * @return the list
     */
    public List<Sock> findByColorAndEquals(String color, Integer cottonPart){
        return sockRepo.findByColorAndCottonPartEquals(color, cottonPart);
    }


    /**
     * Find by color and more than list.
     *
     * @param color      the color
     * @param cottonPart the cotton part
     * @return the list
     */
    public List findByColorAndMoreThan(String color, Integer cottonPart){
        return sockRepo.findByColorAndCottonPartGreaterThan(color, cottonPart);
    }


    /**
     * Find by color and less than list.
     *
     * @param color      the color
     * @param cottonPart the cotton part
     * @return the list
     */
    public List findByColorAndLessThan(String color, Integer cottonPart){
        return sockRepo.findByColorAndCottonPartLessThan(color, cottonPart);
    }
}
