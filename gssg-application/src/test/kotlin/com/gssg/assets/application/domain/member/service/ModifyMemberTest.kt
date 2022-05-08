package com.gssg.assets.application.domain.member.service

import com.gssg.assets.application.domain.member.port.`in`.ModifyMemberUseCase
import com.gssg.assets.application.domain.member.port.out.MockMemberPersistencePortAdapter
import com.gssg.assets.application.domain.member.service.exception.MemberDisplayNameIntegrityConstraintViolationException
import com.gssg.assets.application.domain.member.service.validation.CommandMemberValidator
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
            modifyMember()
        }
        assertThat(true)
    }

    @Test
    fun `이미 등록된 DisplayName 인 경우 Exception 를 발생시킨다`() {
        assertThrows<MemberDisplayNameIntegrityConstraintViolationException> {
            modifyMember(
                memberDisplayName = MemberDisplayName("second")
            )
        }
    }

    private fun modifyMember(
        memberDisplayName: MemberDisplayName? = null
    ) {
        val command = ModifyMemberUseCase.Command(
            memberId = MemberId(1L),
            memberDisplayName = memberDisplayName ?: MemberDisplayName("Heli"),
            memberIntroduce = MemberIntroduce("Hello, I am Heli"),
            memberProfileDog = MemberProfileDog(ProfileDogType.SILVER)
        )
        modifyMember.command(command = command)
    }
}
