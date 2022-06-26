package com.gssg.assets.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

/**
 * @Author Heli
 */
internal class LoggerKtTest {

    companion object {
        val companionLogger = logger()
    }

    @Test
    fun `변수에 할당하면 현재 클래스의 이름으로 로거를 생성한다`() {
        val logger = logger()

        Assertions.assertThat(logger.name).isEqualTo(this.javaClass.name)
    }

    @Test
    fun `Companion class 에 할당하면 Companion Class 의 이름으로 로거를 생성한다`() {
        Assertions.assertThat(companionLogger.name).isEqualTo(this.javaClass.name)
    }
}
