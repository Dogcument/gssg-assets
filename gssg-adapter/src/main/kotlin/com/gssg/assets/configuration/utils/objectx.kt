package com.gssg.assets.configuration.utils

/**
 * @Author Heli
 */
@Suppress("UNCHECKED_CAST")
fun <T> lateInit(): T = null as T

val Any?.safe get() = Unit

fun <T : Any> T?.notNull(): T = requireNotNull(this)
inline fun <T : Any> T?.notNull(lazyMessage: () -> Any): T = requireNotNull(this, lazyMessage)

inline fun <reified T> Array<*>.find(): T? {
    for (a in this) {
        if (a is T) {
            return a
        }
    }
    return null
}
