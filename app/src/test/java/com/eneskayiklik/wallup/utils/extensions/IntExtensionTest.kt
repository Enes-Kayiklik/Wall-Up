package com.eneskayiklik.wallup.utils.extensions

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class IntExtensionTest {

    @Test
    fun `validate parse count`() {
        val counts = listOf(100, 18_340, 372_992, 823_498_239, 9_384, 2_048)
        val results = listOf("100", "18.3K", "372.9K", "823.4M", "9.3K", "2K")
        val result = counts.map { it.parseCount() }
        result.forEachIndexed { index, s ->
            assertThat(s).isEqualTo(results[index])
        }
    }
}