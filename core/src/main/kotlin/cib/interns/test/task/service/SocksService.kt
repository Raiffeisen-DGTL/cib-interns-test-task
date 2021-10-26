package cib.interns.test.task.service

interface SocksService {

    fun getSocks(): List<Socks>

    fun addSocks(request: Socks): Socks

    fun removeSocks(request: Socks): Socks
}