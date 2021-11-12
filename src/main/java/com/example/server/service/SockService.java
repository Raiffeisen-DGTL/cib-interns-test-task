package com.example.server.service;

import com.example.server.model.Sock;

import java.util.List;


public interface SockService
{
	void        create(Sock client);

	List<Sock>  readAll();

//	Sock        read(int id);
//
//	boolean     update(Sock sock, int id);

	boolean     delete(int id);
}
