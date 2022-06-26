package com.gssg.assets.application.domain.member.service

import com.gssg.assets.application.domain.member.port.`in`.ModifyMemberUseCase
import com.gssg.assets.application.domain.member.port.`in`.QueryMemberUseCase
import com.gssg.assets.application.domain.member.port.out.MockMemberPersistencePortAdapter
import com.gssg.assets.application.domain.member.service.exception.MemberDisplayNameIntegrityConstraintViolationException
import com.gssg.assets.application.domain.member.service.exception.MemberNotFoundException
import com.gssg.assets.application.domain.member.service.validation.CommandMemberValidator
import com.gssg.assets.config.utils.notNull
import com.gssg.assets.domain.member.MemberDisplayName
import com.gssg.assets.domain.member.MemberId
import com.gssg.assets.domain.member.MemberIntroduce
import com.gssg.assets.domain.member.MemberProfileDog
import com.gssg.assets.domain.member.enums.ProfileDogType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*

/**
 * @Author Heli
 */
internal class ModifyMemberTest {

    private val memberPersistencePort = MockMemberPersistencePortAdapter()

    private val queryMemberUseCase = QueryMember(
        memberPersistencePort = memberPersistencePort
    )

    private val memberValidator = CommandMemberValidator(
        queryMemberUseCase = queryMemberUseCase
    )

    private val modifyMember = ModifyMember(
        memberPersistencePort = memberPersistencePort,
        memberValidator = memberValidator
    )

    private val queryMember = QueryMember(
        memberPersistencePort = memberPersistencePort
    )

    @BeforeEach
    fun init() {
        memberPersistencePort.initData()
    }

    @AfterEach
    fun reset() {
        memberPersistencePort.clear()
    }

    @Test
    fun `멤버를 수정할 수 있다`() {
        assertDoesNotThrow {
            modifyMember(
                memberDisplayName = MemberDisplayName("Heli 2"),
                memberIntroduce = MemberIntroduce("Heli Introduce 2"),
                memberProfileDog = MemberProfileDog(ProfileDogType.BAEKGU)
            )
        }

        val result = queryMember.query(QueryMemberUseCase.Query.ById(MemberId(1L)))
        assertThat(result).notNull()

        val actual = result!!.member
        assertThat(actual.displayName.displayName).isEqualTo("Heli 2")
        assertThat(actual.introduce.introduce).isEqualTo("Heli Introduce 2")
        assertThat(actual.profileDog.profileDog.name).isEqualTo(ProfileDogType.BAEKGU.name)
    }

    @Test
    fun `Command 에 값을 전달하지 않으면 기본 값과 동일하다`() {
        assertDoesNotThrow {
            val command = ModifyMemberUseCase.Command(
                memberId = MemberId(id = 3L),
            )
            modifyMember.command(command = command)
        }

        val result = queryMember.query(QueryMemberUseCase.Query.ById(MemberId(3L)))
        assertThat(result).notNull()

        val actual = result!!.member
        assertThat(actual.displayName.displayName).isEqualTo("third")
        assertThat(actual.introduce.introduce).isEqualTo("Hello, I am Third")
        assertThat(actual.profileDog.profileDog.name).isEqualTo(ProfileDogType.BAEKGU.name)
    }

    @Test
    fun `이미 등록된 DisplayName 인 경우 Exception 이 발생한다`() {
        assertThrows<MemberDisplayNameIntegrityConstraintViolationException> {
            modifyMember(
                memberDisplayName = MemberDisplayName("second")
            )
        }
    }

    @Test
    fun `존재하지 않는 MemberId 를 수정하려고 하면 Exception 이 발생한다`() {
        assertThrows<MemberNotFoundException> {
            modifyMember(memberId = MemberId(99L))
        }
    }

    private fun modifyMember(
        memberId: MemberId = MemberId(1L),
        memberDisplayName: MemberDisplayName? = null,
        memberIntroduce: MemberIntroduce? = null,
        memberProfileDog: MemberProfileDog? = null
    ) {
        val command = ModifyMemberUseCase.Command(
            memberId = memberId,
            memberDisplayName = memberDisplayName,
            memberIntroduce = memberIntroduce,
            memberProfileDog = memberProfileDog,
        )
        modifyMember.command(command = command)
    }
}
