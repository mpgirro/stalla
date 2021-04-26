package dev.stalla.util

import java.math.BigInteger
import kotlin.contracts.contract

@InternalAPI
internal fun Int?.asBigIntegerOrNull(): BigInteger? {
    contract {
        returnsNotNull() implies (this@asBigIntegerOrNull != null)
    }

    if (this == null) return null
    return BigInteger.valueOf(this.toLong())
}

@InternalAPI
internal fun Long?.asBigIntegerOrNull(): BigInteger? {
    contract {
        returnsNotNull() implies (this@asBigIntegerOrNull != null)
    }

    if (this == null) return null
    return BigInteger.valueOf(this)
}
