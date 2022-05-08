package com.gssg.assets.application.domain.member.port.out

import com.gssg.assets.domain.member.*
import com.gssg.assets.domain.member.enums.Role
import com.gssg.assets.domain.member.enums.Status
import java.time.LocalDateTime
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
        val memberId = MemberId(id = member.longId)
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

    override fun findByEmail(memberEmail: MemberEmail): Member? {
        return db.values.find {
            it.email == memberEmail
        }
    }

    fun initData() {
        val now = LocalDateTime.now()
        db[MemberId(id = 1L)] = Member(
            id = MemberId(id = 1L),
            createdAt = MemberCreatedAt(createdAt = now),
            modifiedAt = MemberModifiedAt(modifiedAt = now),
            email = MemberEmail(email = "first@example.com"),
            password = MemberPassword(password = "password"),
            displayName = MemberDisplayName(displayName = "first"),
            introduce = MemberIntroduce(introduce = "Hello, I am First"),
            profileDog = MemberProfileDog.default(),
            role = MemberRole(role = Role.USER),
            status = MemberStatus(status = Status.ACTIVE)
        )
        db[MemberId(id = 2L)] = Member(
            id = MemberId(id = 2L),
            createdAt = MemberCreatedAt(createdAt = now),
            modifiedAt = MemberModifiedAt(modifiedAt = now),
            email = MemberEmail(email = "second@example.com"),
            password = MemberPassword(password = "password"),
            displayName = MemberDisplayName(displayName = "second"),
            introduce = MemberIntroduce(introduce = "Hello, I am Second"),
            profileDog = MemberProfileDog.default(),
            role = MemberRole(role = Role.USER),
            status = MemberStatus(status = Status.INACTIVE)
        )
        db[MemberId(id = 3L)] = Member(
            id = MemberId(id = 3L),
            createdAt = MemberCreatedAt(createdAt = now),
            modifiedAt = MemberModifiedAt(modifiedAt = now),
            email = MemberEmail(email = "third@example.com"),
            password = MemberPassword(password = "password"),
            displayName = MemberDisplayName(displayName = "second"),
            introduce = MemberIntroduce(introduce = "Hello, I am Third"),
            profileDog = MemberProfileDog.default(),
            role = MemberRole(role = Role.ADMIN),
            status = MemberStatus(status = Status.ACTIVE)
        )
    }

    fun clear() {
        db.clear()
    }
}
