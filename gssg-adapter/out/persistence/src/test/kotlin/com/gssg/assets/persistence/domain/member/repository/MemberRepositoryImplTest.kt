package com.gssg.assets.persistence.domain.member.repository

import com.gssg.assets.domain.member.enums.ProfileDogType
import com.gssg.assets.domain.member.enums.Role
import com.gssg.assets.domain.member.enums.Status
import com.gssg.assets.persistence.domain.member.entity.MemberEntities
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @Author Heli
 */
// FIXME: 2022/05/09
// TODO Repository 테스트 방식 변경
internal class MemberRepositoryImplTest {
    private val memberRepository = MemberRepositoryImpl()

    @BeforeEach
    fun setup() {
        Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver", user = "root", password = "")
    }

    @Test
    fun `멤버를 데이터베이스에 INSERT 할 수 있다`() {
        val definition = MemberRepository.MemberDefinition(
            email = "heli@xample.com",
            password = "password",
            displayName = "Heli",
            introduce = "Hi I am heli",
            profileDog = ProfileDogType.ALEX,
            role = Role.ADMIN,
            status = Status.ACTIVE
        )

        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(MemberEntities)

            memberRepository.insert(definition)

            SchemaUtils.drop(MemberEntities)
        }
    }
}
