package com.mazvile.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class CellTest {
    private val cell = Cell(Coordinates(0, 0))

    companion object {
        @JvmStatic
        fun coordinates() = listOf(
                Arguments.of(Coordinates(-1, 0)),
                Arguments.of(Coordinates(-1, 1)),
                Arguments.of(Coordinates(0, 1)),
                Arguments.of(Coordinates(0, -1)),
                Arguments.of(Coordinates(1, -1)),
                Arguments.of(Coordinates(1, 1)),
                Arguments.of(Coordinates(1, 0)),
                Arguments.of(Coordinates(1, -1))
        )
    }

    @ParameterizedTest
    @MethodSource("coordinates")
    fun `coordinates that differ by one should return cells are neighbours`(coordinates: Coordinates) {
        val neighbourCell = Cell(coordinates)

        assertThat(cell.isNeighbour(neighbourCell)).isTrue()
    }

    @Test
    fun `cell with distant coordinates is not a neighbour`() {
        val distantCell = Cell(Coordinates(99, 99))

        assertThat(cell.isNeighbour(distantCell)).isFalse()
    }

    @Test
    fun `cell with the same coordinates is not a neighbour`() {
        val sameSpotCell = Cell(Coordinates(0,0))

        assertThat(cell.isNeighbour(sameSpotCell)).isFalse()
    }

}