package com.gssg.assets.application.domain.member.service.mock

import com.gssg.assets.domain.member.*

/**
 * @Author Heli
 */
fun mockMember(
    newEmail: String? = null,
    newPassword: String? = null,
    newDisplayName: String? = null,
    newIntroduce: String? = null,
) = Member.create(
    newEmail = MemberEmail(email = newEmail ?: DEFAULT_EMAIL),
    newPassword = MemberPassword(password = newPassword ?: DEFAULT_PASSWORD),
    newDisplayName = MemberDisplayName(
        displayName = newDisplayName ?: DEFAULT_DISPLAY_NAME
    ),
    newIntroduce = MemberIntroduce(introduce = newIntroduce ?: DEFAULT_INTRODUCE)
)

private const val DEFAULT_EMAIL = "heli@example.com"
private const val DEFAULT_PASSWORD = "examplePassword1!"
private const val DEFAULT_DISPLAY_NAME = "Heli"
private const val DEFAULT_INTRODUCE = "Hello, I am Heli"
