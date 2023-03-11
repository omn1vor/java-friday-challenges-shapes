package org.friday.view;

import org.friday.model.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class SwingShapeView extends JFrame implements ShapeView {

    final int SIZE = 400;
    final int BORDER = 5;

    Collection<Shape> shapes;

    public SwingShapeView(String title) {
        super("Shapes: " + title);
    }

    @Override
    public void show(Collection<Shape> shapes) {
        this.shapes = shapes;
        SwingUtilities.invokeLater(this::createGUI);
        repaint();
    }

    private void createGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(SIZE, SIZE);
        Panel panel = new Panel();
        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class Panel extends JPanel {

        int scalingFactor = 1;

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            computeScalingFactor();
            DrawVisitor drawVisitor = new SwingDrawVisitor(g, scalingFactor, BORDER);

            for (Shape shape : shapes) {
                drawVisitor.visitToDraw(shape);
            }
        }

        void computeScalingFactor() {
            double max = 0;
            for (Shape shape: shapes) {
                double right = shape.right();
                double bottom = shape.bottom();
                double maxCoordinate = Math.max(right, bottom);
                if (maxCoordinate > max) {
                    max = maxCoordinate;
                }
            }

            scalingFactor = (int) ((Math.min(getWidth(), getHeight()) - BORDER * 2) / max);
        }
    }


}

