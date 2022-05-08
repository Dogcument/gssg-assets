package com.gssg.assets.domain


/**
 * @Author Heli
 */

abstract class BaseId {
    abstract val id: Long
}

abstract class BaseDomain {

    abstract val id: BaseId

    val longId: Long
        get() = id.id
}
