package com.gssg.assets.domain.member.enums

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * @Author Heli
 */
internal class RoleTest {

    @Test
    fun `찾을 수 있는 타입인 경우 정상 수행된다`() {
        val actual = Role.of("USER")

        Assertions.assertThat(actual).isEqualTo(Role.USER)
    }

    @Test
    fun `찾을 수 없는 타입인 경우 Exception 이 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Role.of("Heli12313sdf1e12e21")
        }
    }
}
