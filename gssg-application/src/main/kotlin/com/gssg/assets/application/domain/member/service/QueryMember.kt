package com.gssg.assets.application.domain.member.service

import com.gssg.assets.application.domain.member.port.`in`.QueryMemberUseCase
import com.gssg.assets.application.domain.member.port.out.MemberPersistencePort
import com.gssg.assets.domain.logger
import org.springframework.stereotype.Service

/**
 * @Author Heli
 */
@Service
class QueryMember(
    private val memberPersistencePort: MemberPersistencePort
) : QueryMemberUseCase {

    private val logger = logger()

    override fun queryById(query: QueryMemberUseCase.Query.ById): QueryMemberUseCase.Result? {
        val memberId = query.memberId

        logger.info("멤버를 데이터베이스에서 조회하기 위해 영속성 포트를 호출")
        val member = memberPersistencePort.findById(memberId = memberId) ?: return null

        return QueryMemberUseCase.Result(
            member = member
        )
    }

    override fun queryByDisplayName(query: QueryMemberUseCase.Query.ByDisplayName): QueryMemberUseCase.Result? {
        val memberDisplayName = query.memberDisplayName

        logger.info("멤버를 데이터베이스에서 조회하기 위해 영속성 포트를 호출")
        val member = memberPersistencePort.findByDisplayName(memberDisplayName = memberDisplayName)
            ?: return null

        return QueryMemberUseCase.Result(
            member = member
        )
    }

    override fun existBy(query: QueryMemberUseCase.Query.ByDisplayName): Boolean {
        val memberDisplayName = query.memberDisplayName

        logger.info("멤버를 데이터베이스에서 조회하기 위해 영속성 포트를 호출")
        return memberPersistencePort.existBy(memberDisplayName = memberDisplayName)
    }
}
