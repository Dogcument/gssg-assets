package com.gssg.assets.domain.member.enums

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * @Author Heli
 */
internal class ProfileDogTypeTest {

    @Test
    fun `찾을 수 있는 타입인 경우 정상 수행된다`() {
        val actual = ProfileDogType.of("BAEKGU")

        Assertions.assertThat(actual).isEqualTo(ProfileDogType.BAEKGU)
    }

    @Test
    fun `찾을 수 없는 타입인 경우 Exception 이 발생한다`() {
        assertThrows<IllegalArgumentException> {
            ProfileDogType.of("Heli12313sdf1e12e21")
        }
    }
}
