package cib.interns.test.task.core.service

interface SocksService {

    fun getSocks(request: SocksFind): Int

    fun addSocks(request: Socks): Socks

    fun removeSocks(request: Socks): Socks
}