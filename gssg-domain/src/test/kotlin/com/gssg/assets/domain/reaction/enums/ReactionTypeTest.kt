package com.gssg.assets.domain.reaction.enums

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * @Author Heli
 */
internal class ReactionTypeTest {

    @Test
    fun `찾을 수 있는 타입인 경우 정상 수행된다`() {
        val actual = ReactionType.of("liKe")

        Assertions.assertThat(actual).isEqualTo(ReactionType.LIKE)
    }

    @Test
    fun `찾을 수 없는 타입인 경우 Exception 이 발생한다`() {
        assertThrows<IllegalArgumentException> {
            ReactionType.of("12312312312asda")
        }
    }
}
