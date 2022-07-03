package com.gssg.assets.application.domain.topic.pick.port.out

import com.gssg.assets.domain.topic.base.*
import com.gssg.assets.domain.topic.pick.*
import java.time.LocalDateTime
import java.util.concurrent.atomic.AtomicLong

/**
 * @Author Heli
 */
internal class MockPickPersistencePortAdapter : PickPersistencePort {

    private val db = mutableMapOf<PickId, Pick>()
    private val distributedId = AtomicLong(1L)

    override fun insert(pick: Pick) {
        val pickId = PickId(id = distributedId.getAndIncrement())
        db[pickId] = pick
    }

    override fun update(pick: Pick) {
        val pickId = PickId(id = pick.longId)
        db[pickId] = pick
    }

    override fun findById(pickId: PickId): Pick? {
        return db[pickId]
    }

    override fun findByTargetDate(pickTargetDate: PickTargetDate): List<Pick> {
        return db.values.filter {
            it.targetDate == pickTargetDate
        }
    }

    fun initData() {
        val now = LocalDateTime.now()
        (1L..3L).forEach { longId ->
            db[PickId(id = longId)] = Pick(
                id = PickId(id = longId),
                createdAt = PickCreatedAt(createdAt = now),
                modifiedAt = PickModifiedAt(modifiedAt = now),
                topic = PickTopic(
                    topic = Topic(
                        id = TopicId(id = 1L),
                        createdAt = TopicCreatedAt(createdAt = now),
                        modifiedAt = TopicModifiedAt(modifiedAt = now),
                        text = TopicText(text = "text $longId"),
                        description = TopicDescription(description = "description $longId")
                    )
                ),
                targetDate = PickTargetDate(targetDate = now.plusDays(longId - 1L).toLocalDate())
            )
        }
    }

    fun clear() {
        db.clear()
    }
}
