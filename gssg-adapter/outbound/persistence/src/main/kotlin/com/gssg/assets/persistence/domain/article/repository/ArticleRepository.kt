package com.gssg.assets.persistence.domain.article.repository

import com.gssg.assets.domain.article.enums.Status
import com.gssg.assets.persistence.common.CommonDefinition
import com.gssg.assets.persistence.domain.article.entity.ArticleEntity
import java.time.LocalDate

/**
 * @Author Heli
 */
interface ArticleRepository {

    fun insert(definition: ArticleDefinition)

    fun update(id: Long, definition: ArticleDefinition)

    fun findById(id: Long): ArticleEntity?

    fun findByPickTargetDate(pickTargetDate: LocalDate): List<ArticleEntity>

    data class ArticleDefinition(
        val title: String,
        val content: String,
        val authorId: Long,
        val pickId: Long,
        val status: Status
    ) : CommonDefinition
}
