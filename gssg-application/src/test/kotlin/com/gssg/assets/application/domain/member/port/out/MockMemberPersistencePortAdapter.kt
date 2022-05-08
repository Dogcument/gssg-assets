package com.gssg.assets.application.domain.member.port.out

import com.gssg.assets.domain.member.Member
import com.gssg.assets.domain.member.MemberDisplayName
import com.gssg.assets.domain.member.MemberId
import java.util.concurrent.atomic.AtomicLong

/**
 * @Author Heli
 */
internal class MockMemberPersistencePortAdapter : MemberPersistencePort {

    private val db = mutableMapOf<MemberId, Member>()
    private val distributedId = AtomicLong(1L)

    override fun insert(member: Member) {
        val memberId = MemberId(id = distributedId.getAndIncrement())
        db[memberId] = member
    }

    override fun update(member: Member) {
        val memberId = MemberId(id = member.requiredId)
        db[memberId] = member
    }

    override fun findById(memberId: MemberId): Member? {
        return db[memberId]
    }

    override fun findByDisplayName(memberDisplayName: MemberDisplayName): Member? {
        return db.values.find {
            it.displayName == memberDisplayName
        }
    }

    override fun existDisplayName(memberDisplayName: MemberDisplayName): Boolean {
        return db.values.find {
            it.displayName == memberDisplayName
        } != null
    }

    fun clear() {
        db.clear()
    }
}
