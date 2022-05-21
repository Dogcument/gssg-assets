package com.gssg.assets.application.domain.comment.service

import com.gssg.assets.application.domain.comment.port.`in`.WriteCommentUseCase
import com.gssg.assets.application.domain.comment.port.out.MockCommentPersistencePortAdapter
import com.gssg.assets.domain.article.*
import com.gssg.assets.domain.comment.Comment
import com.gssg.assets.domain.comment.CommentArticle
import com.gssg.assets.domain.comment.CommentAuthor
import com.gssg.assets.domain.comment.CommentContent
import com.gssg.assets.domain.member.*
import com.gssg.assets.domain.topic.base.Topic
import com.gssg.assets.domain.topic.base.TopicDescription
import com.gssg.assets.domain.topic.base.TopicText
import com.gssg.assets.domain.topic.pick.Pick
import com.gssg.assets.domain.topic.pick.PickTargetDate
import com.gssg.assets.domain.topic.pick.PickTopic
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.time.LocalDate

/**
 * @Author Heli
 */
internal class WriteCommentTest {

    private val commentPersistencePort = MockCommentPersistencePortAdapter()

    private val writeComment = WriteComment(
        commentPersistencePort = commentPersistencePort
    )

    @BeforeEach
    fun init() {
        commentPersistencePort.initData()
    }

    @AfterEach
    fun reset() {
        commentPersistencePort.clear()
    }

    @Test
    fun `댓글을 작성할 수 있다`() {
        assertDoesNotThrow {
            writeComment()
        }
        Assertions.assertThat(true)
    }

    private fun writeComment() {
        val command = WriteCommentUseCase.Command(
            comment = Comment.create(
                newContent = CommentContent(content = "Hello, I am Heli"),
                author = CommentAuthor(
                    author = Member.create(
                        newEmail = MemberEmail(email = "heli@example.com"),
                        newPassword = MemberPassword(password = "password"),
                        newDisplayName = MemberDisplayName(displayName = "Heli"),
                        newIntroduce = MemberIntroduce(introduce = "Hello, I am Heli")
                    )
                ),
                article = CommentArticle(
                    article = Article.create(
                        newTitle = ArticleTitle(title = "Article Title"),
                        newContent = ArticleContent(content = "Article Content"),
                        author = ArticleAuthor(
                            author = Member.create(
                                newEmail = MemberEmail(email = "heli@example.com"),
                                newPassword = MemberPassword(password = "password"),
                                newDisplayName = MemberDisplayName(displayName = "Heli"),
                                newIntroduce = MemberIntroduce(introduce = "Hello, I am Heli")
                            )
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
                        )
                    )
                )
            )
        )
        writeComment.command(command = command)
    }
}
