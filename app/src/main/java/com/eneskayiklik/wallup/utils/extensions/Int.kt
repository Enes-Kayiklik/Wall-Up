package com.eneskayiklik.wallup.utils.extensions

fun Int.parseCount(): String {
    return when (this) {
        in 1_000..999_999 -> {
            val result = StringBuilder("")
            val concat = this % 1000
            result.append("${this / 1_000}")
            if (concat >= 100)
                result.append(".${concat.toString().first()}")
            result.append("K")
            result.toString()
        }
        in 1_000_000..999_999_999 -> {
            val result = StringBuilder("")
            val concat = this % 1_000_000
            result.append("${this / 1_000_000}")
            if (concat >= 100)
                result.append(".${concat.toString().first()}")
            result.append("M")
            result.toString()
        }
        else -> "$this"
    }
}