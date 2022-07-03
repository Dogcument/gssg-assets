package com.gssg.assets.application.domain.article.service

import com.gssg.assets.application.domain.article.port.`in`.QueryArticleByPickTargetDateUseCase
import com.gssg.assets.application.domain.article.port.out.MockArticlePersistencePortAdapter
import com.gssg.assets.application.domain.topic.pick.port.out.MockPickPersistencePortAdapter
import com.gssg.assets.domain.topic.pick.PickTargetDate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

/**
 * @Author Heli
 */
internal class QueryArticleByPickTargetDateTest {


    private val pickPersistencePort = MockPickPersistencePortAdapter()

    private val articlePersistencePort = MockArticlePersistencePortAdapter(
        pickPersistencePort = pickPersistencePort
    )

    private val queryArticleByPickTargetDate = QueryArticleByPickTargetDate(
        articlePersistencePort = articlePersistencePort
    )

    @BeforeEach
    fun init() {
        pickPersistencePort.initData()
        articlePersistencePort.initData()
    }

    @AfterEach
    fun reset() {
        pickPersistencePort.clear()
        articlePersistencePort.clear()
    }

    @Test
    fun `피커의 타겟 날짜로 게시글을 찾을 수 있다`() {
        val nowDate = LocalDate.now()
        val query = QueryArticleByPickTargetDateUseCase.Query(
            pickTargetDate = PickTargetDate(targetDate = nowDate)
        )
        val result = queryArticleByPickTargetDate.query(
            query = query
        )
        val (articles) = result
        assertThat(articles).hasSize(1)
        assertThat(articles[0].id.id).isEqualTo(1L)
    }
}
