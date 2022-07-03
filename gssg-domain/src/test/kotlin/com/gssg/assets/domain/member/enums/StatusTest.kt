package com.gssg.assets.domain.member.enums

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * @Author Heli
 */
internal class StatusTest {

    @Test
    fun `찾을 수 있는 타입인 경우 정상 수행된다`() {
        val actual = Status.of("ACTIVE")

        Assertions.assertThat(actual).isEqualTo(Status.ACTIVE)
    }

    @Test
    fun `찾을 수 없는 타입인 경우 Exception 이 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Status.of("Heli12313sdf1e12e21")
        }
    }
}
