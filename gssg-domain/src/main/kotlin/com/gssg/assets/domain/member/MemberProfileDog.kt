package com.gssg.assets.domain.member

import com.gssg.assets.domain.member.enums.ProfileDogType

/**
 * @Author Heli
 */
data class MemberProfileDog(val profileDog: ProfileDogType) {
    companion object {
        fun default(): MemberProfileDog = MemberProfileDog(ProfileDogType.BAEKGU)
    }
}
