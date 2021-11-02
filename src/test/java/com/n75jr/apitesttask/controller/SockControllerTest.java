package com.n75jr.apitesttask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.n75jr.apitesttask.dao.SockRepository;
import com.n75jr.apitesttask.model.Sock;
import com.n75jr.apitesttask.model.SockID;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class SockControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private SockRepository repository;

    // POSTs:
    //
    @Test
    public void postIncomeWhenSockAbsentsOrExistsByValidCheck() throws Exception {
//         cottonPart is bad because < 0
        Sock sock = new Sock("green", -1, 10);

        mvc.perform(MockMvcRequestBuilders.
                post("/api/socks/income")
                .content(objectMapper.writeValueAsString(sock))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(sock)));

//         cottonPart is bad because == 0
        sock.setCottonPart(0);

        mvc.perform(MockMvcRequestBuilders.
                post("/api/socks/income")
                .content(objectMapper.writeValueAsString(sock))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(sock)));

//         quantity is bad because < 0
        sock.setCottonPart(1);
        sock.setQuantity(-1);

        mvc.perform(MockMvcRequestBuilders.
                post("/api/socks/income")
                .content(objectMapper.writeValueAsString(sock))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(sock)));

//         quantity is bad because == 0
        sock.setQuantity(0);

        mvc.perform(MockMvcRequestBuilders.
                post("/api/socks/income")
                .content(objectMapper.writeValueAsString(sock))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(sock)));
    }

    @Test
    public void postIncomeWhenSockExists() throws Exception {
        int addQuantity = 100;
        Sock sock = new Sock("green", 75, 10);
        Sock addedSock = new Sock(sock.getColor(), sock.getCottonPart(), addQuantity);
        Sock expectedSock = new Sock(sock.getColor(), sock.getCottonPart(), sock.getQuantity() + addQuantity);
        SockID id = new SockID(sock.getColor(), sock.getCottonPart());

        Mockito.when(repository.existsById(id)).thenReturn(true);
        Mockito.when(repository.getById(id)).thenReturn(sock);
        Mockito.when(repository.save(sock)).thenReturn(expectedSock);

        mvc.perform(MockMvcRequestBuilders.
                post("/api/socks/income")
                .content(objectMapper.writeValueAsString(addedSock))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedSock)));
    }

    // /API/SOCKS/OUTCOME
    @Test
    public void postOutcomeWhenSockAbsents() throws Exception {
        Sock sock = new Sock("green", 75, 10);
        SockID id = new SockID(sock.getColor(), sock.getCottonPart());

        Mockito.when(repository.existsById(id)).thenReturn(false);

        mvc.perform(MockMvcRequestBuilders.
                post("/api/socks/outcome")
                .content(objectMapper.writeValueAsString(sock))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNoContent())
                .andExpect(content().json(objectMapper.writeValueAsString(sock)));
    }

    @Test
    public void postOutcomeWhenSockExists() throws Exception {
        int subQuantity = 100;
        Sock sock = new Sock("green", 75, 10);
        Sock subtractedSock = new Sock(sock.getColor(), sock.getCottonPart(), subQuantity);
        Sock expectedSock = new Sock(sock.getColor(), sock.getCottonPart(), sock.getQuantity() - subQuantity);
        SockID id = new SockID(sock.getColor(), sock.getCottonPart());

        Mockito.when(repository.existsById(id)).thenReturn(true);
        Mockito.when(repository.getById(id)).thenReturn(sock);
        Mockito.when(repository.save(sock)).thenReturn(expectedSock);

        mvc.perform(MockMvcRequestBuilders.
                post("/api/socks/outcome")
                .content(objectMapper.writeValueAsString(subtractedSock))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedSock)));
    }

    // GETs:
    //
    @Test
    public void getSocksOperationsBadParams() throws Exception {
//         cottonPart is bad because < 0
        mvc.perform(get("/api/socks")
                .param("color", "green")
                .param("cottonPart", "-1")
                .param("operation", "moreThan"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("-1"));

//         operation is bad because it's unknown
        mvc.perform(get("/api/socks")
                .param("color", "green")
                .param("cottonPart", "-1")
                .param("operation", "iDontKnowYou"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("-1"));

//         color is bad because it's empty
        mvc.perform(get("/api/socks")
                .param("cottonPart", "1")
                .param("operation", "moreThan"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("-1"));

//         cottonPart is bad because it's empty
        mvc.perform(get("/api/socks")
                .param("color", "green")
                .param("operation", "moreThan"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("-1"));

//         operation is bad because it's empty
        mvc.perform(get("/api/socks")
                .param("color", "green")
                .param("cottonPart", "1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("-1"));
    }

    // /API/SOCKS?OPERATION=moreThan&...
    @Test
    public void getSocksOperationMoreThan() throws Exception {
        int findCottonPart = 35;
        String findColor = "green";
        List<Sock> socks = new ArrayList<>();
        socks.addAll(Arrays.asList(
                new Sock(findColor, 38, 10),
                new Sock(findColor, 75, 35),
                new Sock("brown", 50, 100),
                new Sock("brown", 30, 100))
        );

        long sum = 0;
        for (Sock sock : socks) {
            if (sock.getColor().equalsIgnoreCase(findColor)
                    && sock.getCottonPart() > findCottonPart) {
                sum += sock.getQuantity();
            }
        }

        Mockito.when(repository.findAll()).thenReturn(socks);

        mvc.perform(
                get("/api/socks")
                        .param("color", "green")
                        .param("cottonPart", Integer.toString(findCottonPart))
                        .param("operation", "moreThan"))
                .andExpect(status().isOk())
                .andExpect(content().string(Long.toString(sum)));

        // when sock absents
        mvc.perform(
                get("/api/socks")
                        .param("color", "pink")
                        .param("cottonPart", Integer.toString(99))
                        .param("operation", "moreThan"))
                .andExpect(status().isOk())
                .andExpect(content().string(Long.toString(0)));
    }
}
