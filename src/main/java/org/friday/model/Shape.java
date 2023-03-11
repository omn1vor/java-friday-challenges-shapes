package org.friday.model;

import org.friday.view.Drawable;

public abstract class Shape implements Drawable {
    Point origin;

    public Shape(Point origin) {
        this.origin = origin;
    }

    abstract public double top();

    abstract public double bottom();

    abstract public double left();

    abstract public double right();

    abstract boolean intersects(Shape shape);

    abstract boolean intersectsWithRectangle(Shape shape);

    abstract boolean intersectsWithCircle(Shape shape);

    public static Shape parse(String input) {
        String signature = input.toLowerCase().replaceAll("\\s", "");

        if (signature.startsWith("square(")) {
            return Square.parse(signature);
        } else if (signature.startsWith("rectangle(")) {
            return Rectangle.parse(signature);
        } else if (signature.startsWith("circle(")) {
            return Circle.parse(signature);
        } else {
            throw new IllegalArgumentException("Unknown shape");
        }
    }

    public static String inputFormat() {
        return """
                Possible options:
                - square(x, y, width)
                - rectangle(x, y, width, height)
                - circle(x, y, radius),
                where x and y are the coordinates of the top-left corner for squares and rectangles,
                and the center for circles.
                """;
    }

    public static boolean rectangleIntersectsWithRectangle(Shape rect1, Shape rect2) {
        return !(rect1.left() > rect2.right()
                || rect1.right() < rect2.left()
                || rect1.top() > rect2.bottom()
                || rect1.bottom() < rect2.top());
    }

    public static boolean rectangleIntersectsWithCircle(Shape rect, Shape circle) {
        double closestX = Math.min(Math.max(circle.origin.x(), rect.left()), rect.right());
        double closestY = Math.min(Math.max(circle.origin.y(), rect.top()), rect.bottom());

        double deltaX = circle.origin.x() - closestX;
        double deltaY = circle.origin.y() - closestY;

        double radius = circle.origin.x() - circle.left();

        return Math.pow(radius, 2) >= Math.pow(deltaX, 2) + Math.pow(deltaY, 2);
    }

    public static boolean CircleIntersectsWithCircle(Shape circle1, Shape circle2) {
        double deltaX = circle1.origin.x() - circle2.origin.x();
        double deltaY = circle1.origin.y() - circle2.origin.y();

        double radius1 = circle1.origin.x() - circle1.left();
        double radius2 = circle2.origin.x() - circle2.left();

        double distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

        return radius1 + radius2 >= distance;
    }
}
