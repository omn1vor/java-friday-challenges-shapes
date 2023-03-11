package org.friday.model;

import junit.framework.TestCase;

import java.util.List;

public class ShapeResolverTest extends TestCase {

    public void testSimpleConnectedSquareAndRectangle() {
        List<Shape> shapes = List.of(
                new Square(0.5, 1, 1),
                new Rectangle(1, 1, 0.5, 1)
        );

        ShapeResolver resolver = new ShapeResolver(shapes);
        assertTrue(resolver.isSingleConnectedShape());
    }

    public void testSimpleDisconnectedSquareAndRectangle() {
        List<Shape> shapes = List.of(
                new Square(0.5, 1, 1),
                new Rectangle(2, 1, 0.5, 1)
        );

        ShapeResolver resolver = new ShapeResolver(shapes);
        assertFalse(resolver.isSingleConnectedShape());
    }

    public void testSimpleConnectedSquareAndCircle() {
        List<Shape> shapes = List.of(
                new Circle(0.5, 0.5, 0.5),
                new Square(0.5, 1, 1)
        );

        ShapeResolver resolver = new ShapeResolver(shapes);
        assertTrue(resolver.isSingleConnectedShape());
    }

    public void testSimpleDisconnectedSquareAndCircle() {
        List<Shape> shapes = List.of(
                new Circle(0.5, 0.5, 0.5),
                new Square(0.6, 1, 1)
        );

        ShapeResolver resolver = new ShapeResolver(shapes);
        assertFalse(resolver.isSingleConnectedShape());
    }

    public void testComplexConnectedShape() {
        List<Shape> shapes = List.of(
                new Square(1, 1, 1),
                new Circle(0.5, 0.5, 0.5),
                new Square(0.6, 1, 1),
                new Rectangle(0.7, 0.7, 0.2, 1)
        );

        ShapeResolver resolver = new ShapeResolver(shapes);
        assertTrue(resolver.isSingleConnectedShape());
    }

    public void testComplexDisconnectedShape() {
        List<Shape> shapes = List.of(
                new Square(1, 1, 1),
                new Circle(0.5, 0.5, 0.5),
                new Square(0.6, 1, 1)
        );

        ShapeResolver resolver = new ShapeResolver(shapes);
        assertFalse(resolver.isSingleConnectedShape());
    }

    public void testTwoDisconnectedGroups() {
        List<Shape> shapes = List.of(
                new Square(1, 1, 1),
                new Circle(3, 1, 0.5),
                new Square(3, 1, 1),
                new Rectangle(3, 1, 2, 0.5)
        );

        ShapeResolver resolver = new ShapeResolver(shapes);
        assertFalse(resolver.isSingleConnectedShape());
    }

}