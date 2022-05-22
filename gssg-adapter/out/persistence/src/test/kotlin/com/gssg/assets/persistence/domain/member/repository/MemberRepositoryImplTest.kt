package com.gssg.assets.persistence.domain.member.repository

import com.gssg.assets.config.utils.notNull
import com.gssg.assets.domain.member.enums.ProfileDogType
import com.gssg.assets.domain.member.enums.Role
import com.gssg.assets.domain.member.enums.Status
import com.gssg.assets.persistence.ExposedRepositoryTestManager
import com.gssg.assets.persistence.domain.member.entity.MemberEntities
import org.assertj.core.api.Assertions
import org.jetbrains.exposed.sql.Database
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @Author Heli
 */
// FIXME: 2022/05/09
// TODO Repository 테스트 방식 변경
internal class MemberRepositoryImplTest : ExposedRepositoryTestManager(
    tables = arrayOf(MemberEntities)
) {
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

        runTestTransaction {
            memberRepository.insert(definition)
            val actual = memberRepository.findById(id = 1L)
            Assertions.assertThat(actual).notNull()
            Assertions.assertThat(actual!!.id.value).isEqualTo(1L)
            Assertions.assertThat(actual.displayName).isEqualTo("Heli")
            Assertions.assertThat(actual.password).isEqualTo("password")
            Assertions.assertThat(actual.email).isEqualTo("heli@xample.com")
            Assertions.assertThat(actual.introduce).isEqualTo("Hi I am heli")
            Assertions.assertThat(actual.profileDog).isEqualTo(ProfileDogType.ALEX.name)
            Assertions.assertThat(actual.role).isEqualTo(Role.ADMIN.name)
            Assertions.assertThat(actual.status).isEqualTo(Status.ACTIVE.name)
        }
    }
}
