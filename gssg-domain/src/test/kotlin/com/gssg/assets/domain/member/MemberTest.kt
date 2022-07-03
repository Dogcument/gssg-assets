package com.gssg.assets.domain.member

import com.gssg.assets.domain.member.enums.ProfileDogType
import com.gssg.assets.domain.member.enums.Role
import com.gssg.assets.domain.member.enums.Status
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

/**
 * @Author Heli
 */
internal class MemberTest {

    companion object {
        private const val DEFAULT_MEMBER_EMAIL = "heli@example.com"
        private const val DEFAULT_MEMBER_PASSWORD = "password"
        private const val DEFAULT_MEMBER_DISPLAY_NAME = "Heli"
        private const val DEFAULT_MEMBER_INTRODUCE = "Hi I am Heli"
    }

    @Test
    fun `기본 값이 의도대로 세팅되어 있다`() {
        val member = generateMember()

        Assertions.assertThat(member.longId).isEqualTo(-1L)
        Assertions.assertThat(member.createdAt.createdAt).isEqualTo(LocalDateTime.MIN)
        Assertions.assertThat(member.modifiedAt.modifiedAt).isEqualTo(LocalDateTime.MIN)
    }

    @Test
    fun `Create 시 값이 의도대로 세팅되어 있다`() {
        val member = generateMember()

        Assertions.assertThat(member.email.email).isEqualTo(DEFAULT_MEMBER_EMAIL)
        Assertions.assertThat(member.password.password).isEqualTo(DEFAULT_MEMBER_PASSWORD)
        Assertions.assertThat(member.displayName.displayName).isEqualTo(DEFAULT_MEMBER_DISPLAY_NAME)
        Assertions.assertThat(member.introduce.introduce).isEqualTo(DEFAULT_MEMBER_INTRODUCE)
        Assertions.assertThat(member.profileDog.profileDog.name).isEqualTo(ProfileDogType.BAEKGU.name)
        Assertions.assertThat(member.role.role.name).isEqualTo(Role.USER.name)
        Assertions.assertThat(member.status.status.name).isEqualTo(Status.ACTIVE.name)
    }

    @Test
    fun `Update 시 값이 의도대로 세팅되어 있다 - 1`() {
        val member = generateMember()

        val actual = member.update(null, null, null)

        Assertions.assertThat(actual.email.email).isEqualTo(DEFAULT_MEMBER_EMAIL)
        Assertions.assertThat(actual.password.password).isEqualTo(DEFAULT_MEMBER_PASSWORD)
        Assertions.assertThat(actual.displayName.displayName).isEqualTo(DEFAULT_MEMBER_DISPLAY_NAME)
        Assertions.assertThat(actual.introduce.introduce).isEqualTo(DEFAULT_MEMBER_INTRODUCE)
        Assertions.assertThat(actual.profileDog.profileDog.name).isEqualTo(ProfileDogType.BAEKGU.name)
        Assertions.assertThat(actual.role.role.name).isEqualTo(Role.USER.name)
        Assertions.assertThat(actual.status.status.name).isEqualTo(Status.ACTIVE.name)
    }

    @Test
    fun `Update 시 값이 의도대로 세팅되어 있다 - 2`() {
        val member = generateMember()

        val actual = member.update(
            newDisplayName = MemberDisplayName("Heli2"),
            newIntroduce = MemberIntroduce("Hi I am Heli 2"),
            newProfileDog = MemberProfileDog(ProfileDogType.JANGGUN)
        )

        Assertions.assertThat(actual.email.email).isEqualTo(DEFAULT_MEMBER_EMAIL)
        Assertions.assertThat(actual.password.password).isEqualTo(DEFAULT_MEMBER_PASSWORD)
        Assertions.assertThat(actual.displayName.displayName).isEqualTo("Heli2")
        Assertions.assertThat(actual.introduce.introduce).isEqualTo("Hi I am Heli 2")
        Assertions.assertThat(actual.profileDog.profileDog.name).isEqualTo(ProfileDogType.JANGGUN.name)
        Assertions.assertThat(actual.role.role.name).isEqualTo(Role.USER.name)
        Assertions.assertThat(actual.status.status.name).isEqualTo(Status.ACTIVE.name)
    }

    @Test
    fun `Delete 시 다른 값은 그대로며 Status 가 INACTIVE 로 변경된다`() {
        val member = generateMember()

        val actual = member.delete()

        Assertions.assertThat(actual.email.email).isEqualTo(DEFAULT_MEMBER_EMAIL)
        Assertions.assertThat(actual.password.password).isEqualTo(DEFAULT_MEMBER_PASSWORD)
        Assertions.assertThat(actual.displayName.displayName).isEqualTo(DEFAULT_MEMBER_DISPLAY_NAME)
        Assertions.assertThat(actual.introduce.introduce).isEqualTo(DEFAULT_MEMBER_INTRODUCE)
        Assertions.assertThat(actual.profileDog.profileDog.name).isEqualTo(ProfileDogType.BAEKGU.name)
        Assertions.assertThat(actual.role.role.name).isEqualTo(Role.USER.name)
        Assertions.assertThat(actual.status.status.name).isEqualTo(Status.INACTIVE.name)
    }

    private fun generateMember() = Member.create(
        newEmail = MemberEmail(DEFAULT_MEMBER_EMAIL),
        newDisplayName = MemberDisplayName(DEFAULT_MEMBER_DISPLAY_NAME),
        newIntroduce = MemberIntroduce(DEFAULT_MEMBER_INTRODUCE),
        newPassword = MemberPassword(DEFAULT_MEMBER_PASSWORD)
    )
}
