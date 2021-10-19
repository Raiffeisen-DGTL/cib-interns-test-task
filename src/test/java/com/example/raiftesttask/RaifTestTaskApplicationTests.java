package com.example.raiftesttask;

import com.example.raiftesttask.domain.Color;
import com.example.raiftesttask.domain.Sock;
import com.example.raiftesttask.repo.SockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;


@SpringBootTest
@WebAppConfiguration
class RaifTestTaskApplicationTests {

    @Autowired
    private SockRepository repository;
    @Test
    void contextLoads() throws Exception {
         Sock sock1 = new Sock(Color.BLACK,66,4L);
         Sock sock2 = new Sock(Color.RED,11,2L);
         Sock sock3 = new Sock(Color.BLUE,13,2L);
         Sock sock4 = new Sock(Color.BLACK,2,2L);
         Sock sock5 = new Sock(Color.WHITE,1,2L);
         Sock sock6 = new Sock(Color.GREEN,73,2L);
         Sock sock7 = new Sock(Color.GREEN,53,2L);
         Sock sock8 = new Sock(Color.GREEN,13,2L);
         Sock sock9 = new Sock(Color.GREEN,1,2L);
         Sock sock10 = new Sock(Color.GREEN,99,2L);

         repository.save(sock1);
         repository.save(sock2);
         repository.save(sock3);
         repository.save(sock4);
         repository.save(sock5);
         repository.save(sock6);
         repository.save(sock7);
         repository.save(sock8);
         repository.save(sock9);
         repository.save(sock10);

         assert repository.findByColorAndCottonPartAfter(Color.GREEN,60).size()==2;
         assert repository.findByColorAndCottonPartBefore(Color.BLACK,50).size()==1;
         assert repository.findAll().size()==10;

         repository.delete(sock1);
         repository.delete(sock2);
         repository.delete(sock3);
         repository.delete(sock4);

         assert repository.findAll().size()==6;

    }


    @BeforeEach
    void deleteAll(){
        repository.deleteAll();
}


}
