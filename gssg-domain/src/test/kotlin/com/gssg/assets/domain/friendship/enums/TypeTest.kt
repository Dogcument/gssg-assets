package com.gssg.assets.domain.friendship.enums

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

/**
 * @Author Heli
 */
internal class TypeTest {
    @Test
    fun `찾을 수 있는 타입인 경우 정상 수행된다`() {
        val actual = Type.of("FolLoW")

        Assertions.assertThat(actual).isEqualTo(Type.FOLLOW)
    }

    @Test
    fun `찾을 수 없는 타입인 경우 Exception 이 발생한다`() {
        org.junit.jupiter.api.assertThrows<IllegalArgumentException> {
            Type.of("1q2w33e45432r43rewrwerew")
        }
    }
}
