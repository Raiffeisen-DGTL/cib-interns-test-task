package com.vitekkor.socksShop.data

enum class Operation {
    MORE_THAN,
    LESS_THAN,
    EQUAL;

    companion object {
        fun getFromStringOrNull(string: String): Operation? {
            return when (string) {
                "moreThan" -> MORE_THAN
                "lessThan" -> LESS_THAN
                "equal" -> EQUAL
                else -> null
            }
        }
    }
}
