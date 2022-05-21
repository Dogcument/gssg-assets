package com.gssg.assets.application.domain.comment.port.out

import com.gssg.assets.domain.article.*
import com.gssg.assets.domain.comment.*
import com.gssg.assets.domain.comment.enums.Status
import com.gssg.assets.domain.member.*
import com.gssg.assets.domain.member.enums.Role
import com.gssg.assets.domain.topic.base.Topic
import com.gssg.assets.domain.topic.base.TopicDescription
import com.gssg.assets.domain.topic.base.TopicText
import com.gssg.assets.domain.topic.pick.Pick
import com.gssg.assets.domain.topic.pick.PickTargetDate
import com.gssg.assets.domain.topic.pick.PickTopic
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.concurrent.atomic.AtomicLong

/**
 * @Author Heli
 */
class MockCommentPersistencePortAdapter : CommentPersistencePort {

    private val db = mutableMapOf<CommentId, Comment>()
    private val distributedId = AtomicLong(1L)

    override fun insert(comment: Comment) {
        val commentId = CommentId(id = distributedId.getAndIncrement())
        db[commentId] = comment
    }

    override fun update(comment: Comment) {
        val commentId = CommentId(id = comment.longId)
        db[commentId] = comment
    }

    override fun findByArticleId(articleId: ArticleId): List<Comment> {
        return db.values.filter {
            it.article.article.longId == articleId.id
        }
    }

    fun initData() {
        val now = LocalDateTime.now()
        val member = Member(
            id = MemberId(id = 1L),
            email = MemberEmail(email = "heli@example.com"),
            password = MemberPassword(password = "password"),
            displayName = MemberDisplayName(displayName = "Heli"),
            introduce = MemberIntroduce(introduce = "Hello, I am Heli"),
            profileDog = MemberProfileDog.default(),
            role = MemberRole(role = Role.USER),
            status = MemberStatus(status = com.gssg.assets.domain.member.enums.Status.ACTIVE)
        )
        (1L..3L).forEach { longId ->
            db[CommentId(id = longId)] = Comment(
                id = CommentId(id = longId),
                createdAt = CommentCreatedAt(createdAt = now),
                modifiedAt = CommentModifiedAt(modifiedAt = now),
                content = CommentContent(content = "Hi i am Heli"),
                author = CommentAuthor(
                    author = member
                ),
                article = CommentArticle(
                    article = Article(
                        id = ArticleId(id = 1L),
                        author = ArticleAuthor(
                            author = member
                        ),
                        pick = ArticlePick(
                            pick = Pick.create(
                                existTopic = PickTopic(
                                    topic = Topic.create(
                                        newText = TopicText(text = "First"),
                                        newDescription = TopicDescription(description = "First Description")
                                    )
                                ),
                                newTargetDate = PickTargetDate(targetDate = LocalDate.now())
                            )
                        ),
                        title = ArticleTitle(title = "Article Title"),
                        content = ArticleContent(content = "Article Content"),
                        status = ArticleStatus(status = com.gssg.assets.domain.article.enums.Status.ACTIVE)
                    )
                ),
                status = CommentStatus(status = Status.ACTIVE)
            )
        }
    }

    fun clear() {
        db.clear()
    }
}
