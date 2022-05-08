package com.gssg.assets.application.domain.member.service

import com.gssg.assets.application.domain.member.port.`in`.QueryMemberUseCase
import com.gssg.assets.application.domain.member.port.out.MockMemberPersistencePortAdapter
import com.gssg.assets.domain.member.MemberDisplayName
import com.gssg.assets.domain.member.MemberEmail
import com.gssg.assets.domain.member.MemberId
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @Author Heli
 */
internal class QueryMemberTest {

    private val memberPersistencePort = MockMemberPersistencePortAdapter()

    private val queryMember = QueryMember(
        memberPersistencePort = memberPersistencePort
    )

    @BeforeEach
    fun init() {
        memberPersistencePort.initData()
    }

    @AfterEach
    fun reset() {
        memberPersistencePort.clear()
    }

    @Test
    fun `Id 로 멤버를 찾을 수 있다`() {
        val query = QueryMemberUseCase.Query.ById(
            memberId = MemberId(id = 1L)
        )
        val result = queryMember.queryById(query = query)
        Assertions.assertNotNull(result)
    }

    @Test
    fun `등록되지 않은 Id 로 검색 시 null 을 반환한다`() {
        val query = QueryMemberUseCase.Query.ById(
            memberId = MemberId(id = 4L)
        )
        val result = queryMember.queryById(query = query)
        Assertions.assertNull(result)
    }

    @Test
    fun `DisplayName 으로 멤버를 찾을 수 있다`() {
        val query = QueryMemberUseCase.Query.ByDisplayName(
            memberDisplayName = MemberDisplayName(displayName = "first")
        )
        val result = queryMember.queryByDisplayName(query = query)
        Assertions.assertNotNull(result)
    }

    @Test
    fun `등록되지 않은 DisplayName 으로 검색 시 null 을 반환한다`() {
        val query = QueryMemberUseCase.Query.ByDisplayName(
            memberDisplayName = MemberDisplayName(displayName = "heli")
        )
        val result = queryMember.queryByDisplayName(query = query)
        Assertions.assertNull(result)
    }

    @Test
    fun `존재하는 DisplayName 이면 True 를 반환한다`() {
        val query = QueryMemberUseCase.Query.ByDisplayName(
            memberDisplayName = MemberDisplayName(displayName = "first")
        )
        val result = queryMember.existBy(query = query)
        Assertions.assertTrue(result)
    }

    @Test
    fun `존재하지 않는 DisplayName 이면 False 를 반환한다`() {
        val query = QueryMemberUseCase.Query.ByDisplayName(
            memberDisplayName = MemberDisplayName(displayName = "heli")
        )
        val result = queryMember.existBy(query = query)
        Assertions.assertFalse(result)
    }

    @Test
    fun `존재하는 Email 이면 True 를 반환한다`() {
        val query = QueryMemberUseCase.Query.ByEmail(
            memberEmail = MemberEmail(email = "first@example.com")
        )
        val result = queryMember.existBy(query = query)
        Assertions.assertTrue(result)
    }

    @Test
    fun `존재하지 않는 Email 이면 False 를 반환한다`() {
        val query = QueryMemberUseCase.Query.ByEmail(
            memberEmail = MemberEmail(email = "heli@example.com")
        )
        val result = queryMember.existBy(query = query)
        Assertions.assertFalse(result)
    }
}
