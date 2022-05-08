package com.gssg.assets.application.domain.member.service.validation

import com.gssg.assets.application.domain.member.port.out.MockMemberPersistencePortAdapter
import com.gssg.assets.application.domain.member.service.QueryMember
import com.gssg.assets.application.domain.member.service.exception.MemberDisplayNameIntegrityConstraintViolationException
import com.gssg.assets.application.domain.member.service.exception.MemberEmailIntegrityConstraintViolationException
import com.gssg.assets.application.domain.member.service.mock.mockMember
import org.junit.jupiter.api.*

/**
 * @Author Heli
 */
internal class CommandMemberValidatorTest {

    private val memberPersistencePort = MockMemberPersistencePortAdapter()

    private val queryMember = QueryMember(
        memberPersistencePort = memberPersistencePort
    )

    private val memberValidator = CommandMemberValidator(
        queryMemberUseCase = queryMember
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
    fun `이미 등록된 DisplayName 인 경우 Exception 를 발생시킨다`() {
        assertThrows<MemberDisplayNameIntegrityConstraintViolationException> {
            memberValidator.requireValid(
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
            memberValidator.requireValid(
                member = mockMember(
                    newDisplayName = "heli",
                    newEmail = "first@example.com",
                )
            )
        }
    }

    @Test
    fun `등록되지 않은 DisplayName 인 경우 정상 등록된다`() {
        assertDoesNotThrow {
            memberValidator.requireValid(
                member = mockMember(
                    newDisplayName = "heli",
                    newEmail = "heli@example.com",
                )
            )
        }
    }

    @Test
    fun `등록되지 않은 Email 인 경우 정상 등록된다`() {
        assertDoesNotThrow {
            memberValidator.requireValid(
                member = mockMember(
                    newDisplayName = "heli",
                    newEmail = "heli@example.com",
                )
            )
        }
    }
}
