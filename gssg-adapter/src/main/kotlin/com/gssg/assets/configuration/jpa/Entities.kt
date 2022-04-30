package com.gssg.assets.configuration.jpa

import com.gssg.assets.configuration.jpa.BaseEntity.Companion.COMPARATOR_PK_ASC
import com.gssg.assets.configuration.utils.lateInit
import com.gssg.assets.configuration.utils.notNull
import java.time.LocalDateTime
import javax.persistence.*

/**
 * @Author Heli
 */
@MappedSuperclass
abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = lateInit()

    @Column(updatable = false)
    var createdAt: LocalDateTime = lateInit()

    var modifiedAt: LocalDateTime = lateInit()

    @PrePersist
    fun prePersist() {
        val now = LocalDateTime.now()
        createdAt = now
        modifiedAt = now
    }

    @PreUpdate
    fun preUpdate() {
        modifiedAt = LocalDateTime.now()
    }

    companion object {
        val COMPARATOR_PK_ASC: Comparator<BaseEntity> = compareBy(BaseEntity::requiredId)
    }
}

fun <T : BaseEntity> Sequence<T>.sortedById(): Sequence<T> = sortedWith(COMPARATOR_PK_ASC)

val BaseEntity.requiredId: Long get() = id.notNull { "Entity(${javaClass.simpleName}) id is null" }
