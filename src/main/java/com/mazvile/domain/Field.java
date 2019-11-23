package com.mazvile.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Field {
    private Set<Cell> cells = new HashSet<>();

    public Set<Cell> getCells() {
        return this.cells;
    }

    public void spawnCell(final Cell cell) {
        cells.add(cell);
    }

    public void spawnCells(final Set<Cell> cells) {
        this.cells.addAll(cells);
    }

    public void nextGeneration() {
        final Set<Cell> babyCells = spawningCells();
        final Set<Cell> survivingCells = survivingCells();

        cells.clear();
        cells.addAll(babyCells);
        cells.addAll(survivingCells);

        cells.forEach(this::countNeighbours);
    }

    private Set<Cell> survivingCells() {
        cells.forEach(this::countNeighbours);
        return cells.stream()
                .filter(this::isSurviving)
                .collect(Collectors.toSet());
    }

    private boolean isSurviving(final Cell cell) {
        return cell.getNumberOfNeighbours() > 1 && cell.getNumberOfNeighbours() < 4;
    }

    private Set<Cell> spawningCells() {
        return possibleSpawnPlaces().stream()
                .map(coordinates -> {
                    final Cell cell = new Cell(coordinates);
                    countNeighbours(cell);
                    return cell;
                })
                .filter(cell -> cell.getNumberOfNeighbours() == 3)
                .collect(Collectors.toSet());
    }

    private void countNeighbours(final Cell cell) {
        int count = 0;
        for (final Cell other : cells) {
            if (cell.isNeighbour(other)) {
                count++;
            }
        }
        cell.setNumberOfNeighbours(count);
    }

    private Set<Coordinates> possibleSpawnPlaces() {
        final Set<Coordinates> possibleBirthPlaces = cells
                .stream()
                .flatMap(cell -> coordinatesAround(cell).stream())
                .collect(Collectors.toSet());

        removeOccupied(possibleBirthPlaces);
        return possibleBirthPlaces;
    }

    private Set<Coordinates> coordinatesAround(final Cell cell) {
        final Set<Coordinates> coordinatesAround = new HashSet<>();
        final int x = cell.getCoordinates().getX();
        final int y = cell.getCoordinates().getY();

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                coordinatesAround.add(new Coordinates(i, j));
            }
        }
        return coordinatesAround;
    }

    private void removeOccupied(final Set<Coordinates> coordinates) {
        for (final Cell cell : cells) {
            coordinates.remove(cell.getCoordinates());
        }
    }

}
