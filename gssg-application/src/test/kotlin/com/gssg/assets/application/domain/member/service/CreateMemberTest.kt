package com.gssg.assets.application.domain.member.service

import com.gssg.assets.application.domain.member.port.`in`.CreateMemberUseCase
import com.gssg.assets.application.domain.member.port.out.MockMemberPersistencePortAdapter
import com.gssg.assets.application.domain.member.service.exception.MemberDisplayNameIntegrityConstraintViolationException
import com.gssg.assets.application.domain.member.service.exception.MemberEmailIntegrityConstraintViolationException
import com.gssg.assets.application.domain.member.service.mock.mockMember
import com.gssg.assets.application.domain.member.service.validation.CommandMemberValidator
import com.gssg.assets.domain.member.Member
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*

/**
 * @Author Heli
 */
internal class CreateMemberTest {

    private val memberPersistencePort = MockMemberPersistencePortAdapter()

    private val queryMemberUseCase = QueryMember(
        memberPersistencePort = memberPersistencePort
    )

    private val memberValidator = CommandMemberValidator(
        queryMemberUseCase = queryMemberUseCase
    )

    private val createMember = CreateMember(
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
    fun `멤버를 만들 수 있다`() {
        assertDoesNotThrow {
            createMember()
        }
        assertThat(true)
    }

    @Test
    fun `이미 등록된 DisplayName 인 경우 Exception 를 발생시킨다`() {
        assertThrows<MemberDisplayNameIntegrityConstraintViolationException> {
            createMember(
                member = mockMember(
                    newDisplayName = "first",
                    newEmail = "heli@example.com"
                )
            )
        }
    }

    @Test
    fun `이미 등록된 Email 인 경우 Exception 를 발생시킨다`() {
        assertThrows<MemberEmailIntegrityConstraintViolationException> {
            createMember(
                member = mockMember(
                    newDisplayName = "heli",
                    newEmail = "first@example.com"
                )
            )
        }
    }

    private fun createMember(member: Member? = null) {
        val command = CreateMemberUseCase.Command(
            member = member ?: mockMember()
        )
        createMember.command(command = command)
    }
}
