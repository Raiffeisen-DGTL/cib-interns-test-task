package cib.interns.test.task.database.repository

import cib.interns.test.task.database.entity.Sock
import org.springframework.data.jpa.repository.JpaRepository

interface SockRepository : JpaRepository<Sock, Long> {
}