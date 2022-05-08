package com.gssg.assets.application.domain.member.service

import com.gssg.assets.application.domain.member.port.`in`.CreateMemberUseCase
import com.gssg.assets.application.domain.member.port.out.MockMemberPersistencePortAdapter
import com.gssg.assets.domain.member.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

/**
 * @Author Heli
 */
internal class CreateMemberTest {

    private val memberPersistencePort = MockMemberPersistencePortAdapter()

    private val createMember = CreateMember(
        memberPersistencePort = memberPersistencePort
    )

    @AfterEach
    fun reset() {
        memberPersistencePort.clear()
    }

    @Test
    fun `멤버를 만들 수 있다`() {
        createMember()
        assertThat(true)
    }

    private fun createMember(
        newEmail: String? = null,
        newPassword: String? = null,
        newDisplayName: String? = null,
        newIntroduce: String? = null,
    ) {
        val member = Member.create(
            newEmail = MemberEmail(email = newEmail ?: DEFAULT_EMAIL),
            newPassword = MemberPassword(password = newPassword ?: DEFAULT_PASSWORD),
            newDisplayName = MemberDisplayName(
                displayName = newDisplayName ?: DEFAULT_DISPLAY_NAME
            ),
            newIntroduce = MemberIntroduce(introduce = newIntroduce ?: DEFAULT_INTRODUCE)
        )
        val command = CreateMemberUseCase.Command(
            member = member
        )
        createMember.command(command = command)
    }

    companion object {
        private const val DEFAULT_EMAIL = "heli@example.com"
        private const val DEFAULT_PASSWORD = "examplePassword1!"
        private const val DEFAULT_DISPLAY_NAME = "Heli"
        private const val DEFAULT_INTRODUCE = "Hello, I am Heli"
    }
}
