package com.gssg.assets.application.domain.topic.pick.port.out

import com.gssg.assets.domain.topic.pick.Pick
import com.gssg.assets.domain.topic.pick.PickId
import com.gssg.assets.domain.topic.pick.PickTargetDate

/**
 * @Author Heli
 */
interface PickPersistencePort {

    fun insert(pick: Pick)

    fun update(pick: Pick)

    fun findById(pickId: PickId): Pick?

    fun findByTargetDate(pickTargetDate: PickTargetDate): Pick?
}
