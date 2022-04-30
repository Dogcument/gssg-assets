package com.gssg.assets.domain.member

/**
 * @Author Heli
 */
data class MemberEmail(val email: String) {
    companion object {
        private const val EMAIL_REGEX = "test" // TODO Validation 추가
    }

    init {
        validate(email = email)
    }

    private fun validate(email: String) {
        // validate email
    }
}
