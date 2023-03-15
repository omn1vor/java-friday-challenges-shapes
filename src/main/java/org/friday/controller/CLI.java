package org.friday.controller;

import org.friday.model.Shape;
import org.friday.model.ShapeResolver;
import org.friday.view.ShapeView;
import org.friday.view.SwingShapeView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLI {

    public CLI() {
        List<Shape> shapes = acceptShapes();
        if (shapes.isEmpty()) {
            return;
        }

        ShapeResolver shapeResolver = new ShapeResolver(shapes);
        String result = shapeResolver.isSingleConnectedShape() ? "a single shape" : "not a single shape";
        System.out.println("The resulting shape is " + result);

        ShapeView shapeView = new SwingShapeView(result);
        shapeView.show(shapes);
    }

    private List<Shape> acceptShapes() {
        List<Shape> shapes = new ArrayList<>();

        System.out.println("Please enter shapes to consider.");
        System.out.println(Shape.inputFormat());
        System.out.println("You can enter several shapes, one by one. Enter an empty string when done.");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!"".equals(input)) {
            try {
                shapes.add(Shape.parse(input));
            } catch (NumberFormatException e) {
                System.out.println("Expected only numbers as parameters for shapes. " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            input = scanner.nextLine();
        }
        return shapes;
    }


}
