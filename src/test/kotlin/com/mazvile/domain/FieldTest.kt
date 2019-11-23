package com.mazvile.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FieldTest {
    private val field = Field()

    @Test
    fun `newly created field should be empty`() {
        assertThat(field.cells).isEmpty()
    }

    @Test
    fun `lonely cell should die after one generation`() {
        field.spawnCell(Cell(Coordinates(1, 1)))
        field.nextGeneration()

        assertThat(field.cells).isEmpty()
    }

    @Test
    fun `cube of cells stays stable after one generation`() {
        val cube = setOf(
                Cell(Coordinates(0, 1)), Cell(Coordinates(1, 1)),
                Cell(Coordinates(0, 0)), Cell(Coordinates(1, 0)))

        field.spawnCells(cube)
        field.nextGeneration()

        assertThat(field.cells).containsAll(cube)
    }

    @Test
    fun `cells with less than two neighbours die after one generation`() {
        val row = setOf(Cell(Coordinates(0, 0)), Cell(Coordinates(1, 0)), Cell(Coordinates(2, 0)))

        field.spawnCells(row)
        field.nextGeneration()

        assertThat(field.cells).doesNotContain(Cell(Coordinates(0, 0)), Cell(Coordinates(2, 0)))
    }

    @Test
    fun `cell with more than three neighbours dies after one generation`() {
        val rectangle = setOf(
                Cell(Coordinates(0, 2)), Cell(Coordinates(1, 2)),
                Cell(Coordinates(0, 1)), Cell(Coordinates(1, 1)),
                Cell(Coordinates(0, 0)), Cell(Coordinates(0, 1)))

        field.spawnCells(rectangle)
        field.nextGeneration()

        assertThat(field.cells).doesNotContain(Cell(Coordinates(0, 1)), Cell(Coordinates(1, 1)))
    }

    @Test
    fun `cell should spawn in empty space with three neighbours`() {
        val corner = setOf(
                Cell(Coordinates(0, 1)), Cell(Coordinates(1, 1)),
                Cell(Coordinates(0, 0)))

        field.spawnCells(corner)
        field.nextGeneration()

        assertThat(field.cells).contains(Cell(Coordinates(1, 0)))
    }

}