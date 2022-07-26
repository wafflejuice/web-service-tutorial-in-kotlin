package org.wafflejuice.springboot.web.dto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class HelloResponseDtoTest {

    @Test
    fun `dto 동등성 테스트`() {
        //given
        val name = "test"
        val amount = 1000

        //when
        val dto = HelloResponseDto(name, amount)

        //then
        assertThat(dto.name).isEqualTo(name)
        assertThat(dto.amount).isEqualTo(amount)
    }
}