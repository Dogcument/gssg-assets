package com.gssg.assets.domain

import com.gssg.assets.config.utils.notNull


/**
 * @Author Heli
 */

abstract class BaseId {
    abstract val id: Long
}

abstract class BaseDomain {

    abstract val id: BaseId?

    val requiredId: Long
        get() = id.notNull {
            "Domain(${javaClass.simpleName}) id is null"
        }.id
}
