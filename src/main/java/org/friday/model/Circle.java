package org.friday.model;

import org.friday.view.DrawVisitor;
import org.friday.view.Drawable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Circle extends Shape implements Drawable {
    private final double radius;

    public Circle(double x, double y, double radius) {
        super(new Point(x, y));
        this.radius = radius;
    }

    @Override
    public double top() {
        return origin.y() - radius;
    }

    @Override
    public double bottom() {
        return origin.y() + radius;
    }

    @Override
    public double left() {
        return origin.x() - radius;
    }

    @Override
    public double right() {
        return origin.x() + radius;
    }

    @Override
    boolean intersects(Shape shape) {
        return shape.intersectsWithCircle(this);
    }

    @Override
    boolean intersectsWithRectangle(Shape shape) {
        return rectangleIntersectsWithCircle(shape, this);
    }

    @Override
    boolean intersectsWithCircle(Shape shape) {
        return CircleIntersectsWithCircle(this, shape);
    }

    @Override
    public void acceptDrawing(DrawVisitor drawVisitor) {
        drawVisitor.draw(this);
    }

    public static Circle parse(String signature) {
        Pattern pattern = Pattern.compile("circle\\((.+)\\)");
        Matcher matcher = pattern.matcher(signature);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("wrong format for circle");
        }

        String[] args = matcher.group(1).split(",");

        if (args.length != 3) {
            throw new IllegalArgumentException("expected 3 parameters for circle");
        }

        return new Circle(
                Double.parseDouble(args[0]),
                Double.parseDouble(args[1]),
                Double.parseDouble(args[2])
        );
    }
}
