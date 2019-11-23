package com.mazvile;

import com.mazvile.domain.Cell;
import com.mazvile.domain.Coordinates;
import com.mazvile.domain.Field;

import javax.swing.*;
import java.awt.*;

public class Application extends Canvas {
    private final static Field FIELD = new Field();

    public static void main(String[] args) throws InterruptedException {
        final Application app = new Application();
        prepareField();

        JFrame frame = new JFrame();
        frame.add(app);
        frame.setSize(600, 600);
        frame.setVisible(true);

        do {
            Thread.sleep(700);
            FIELD.nextGeneration();
            app.update(app.getGraphics());
        } while (true);
    }

    public void paint(final Graphics g) {
        setBackground(Color.WHITE);
        for (final Cell cell : FIELD.getCells()) {
            drawCell(g, cell);
        }
    }

    private void drawCell(final Graphics g, final Cell cell) {
        g.setColor(pickColor(cell.getNumberOfNeighbours()));
        g.fillRect(cell.getCoordinates().getX() * 10, cell.getCoordinates().getY() * 10, 10, 10);
    }

    private Color pickColor(final int number) {
        switch (number) {
            case 0:
                return Color.WHITE.darker();
            case 1:
                return Color.lightGray;
            case 2:
                return Color.GRAY.brighter();
            case 3:
                return Color.GRAY.brighter();
            case 4:
                return Color.lightGray.darker();
            case 5:
                return Color.GRAY;
            case 6:
                return Color.GRAY.darker();
            case 7:
                return Color.BLACK;
            default:
                return Color.BLACK;
        }
    }

    private static void prepareField() {
        addToField(35, 40);
        addToField(36, 40);
        addToField(37, 40);
        addToField(38, 40);
        addToField(20, 60);
        addToField(21, 60);
        addToField(20, 61);
        addToField(21, 61);
        addToField(33, 40);
        addToField(34, 40);
        addToField(35, 40);
        addToField(36, 40);
        addToField(28, 10);
        addToField(28, 11);
        addToField(28, 12);
        addToField(28, 13);
        addToField(28, 14);
    }

    private static void addToField(final int x, final int y) {
        FIELD.spawnCell(new Cell(new Coordinates(x, y)));
    }

}
