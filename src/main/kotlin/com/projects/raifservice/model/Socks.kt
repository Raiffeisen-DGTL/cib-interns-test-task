package com.projects.raifservice.model

import org.hibernate.Hibernate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.*


@Entity
data class Socks(
    @field:NotEmpty
    val color: String,
    @field:Positive
    @field:Max(100)
    @field:NotNull
    val cottonPart: Int,
    @field:Positive
    @field:NotNull
    val quantity: Int,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long?
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Socks

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 0

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}