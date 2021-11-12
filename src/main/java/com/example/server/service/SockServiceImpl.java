package com.example.server.service;

import com.example.server.model.Sock;
import com.example.server.repository.SockRepository;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class SockServiceImpl implements SockService
{
	private final SockRepository sockRepository;

	public SockServiceImpl(SockRepository sockRepository) {
		this.sockRepository = sockRepository;
	}
	@Override
	public void create(Sock sock)
	{
		sockRepository.save(sock);
	}

	@Override
	public List<Sock> readAll()
	{
		return sockRepository.findAll();
	}

//	@Override
//	public Sock read(int id)
//	{
//		return sockRepository.getById(id);
//	}
//
//	@Override
//	public boolean update(Sock sock, int id)
//	{
//		if (sockRepository.existsById(id))
//		{
//			sock.setId(id);
//			sockRepository.save(sock);
//			return true;
//		}
//		return false;
//	}

	@Override
	public boolean delete(int id)
	{
		if (sockRepository.existsById(id))
		{
			sockRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
