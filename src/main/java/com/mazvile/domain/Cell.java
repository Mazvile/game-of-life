package com.mazvile.domain;

import java.util.Objects;

public class Cell {
    private Coordinates coordinates;
    private int numberOfNeighbours;

    public Cell(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isNeighbour(final Cell cell) {
        return !cell.equals(this)
                && Math.abs(this.coordinates.getX() - cell.coordinates.getX()) < 2
                && Math.abs((this.coordinates.getY() - cell.coordinates.getY())) < 2;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getNumberOfNeighbours() {
        return numberOfNeighbours;
    }

    void setNumberOfNeighbours(int numberOfNeighbours) {
        this.numberOfNeighbours = numberOfNeighbours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return coordinates.equals(cell.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "coordinates=" + coordinates +
                ", numberOfNeighbours=" + numberOfNeighbours +
                '}';
    }

}
