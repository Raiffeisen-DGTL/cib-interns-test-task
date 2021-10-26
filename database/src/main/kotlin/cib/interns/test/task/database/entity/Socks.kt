package cib.interns.test.task.database.entity

import javax.persistence.*

@Entity
class Socks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var color: String? = null

    var cottonPart: Byte? = null

    var quantity: Long? = null
}