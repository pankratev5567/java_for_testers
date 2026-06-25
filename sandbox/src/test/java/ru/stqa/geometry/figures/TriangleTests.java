package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculateArea() {
        Triangle t = new Triangle(3, 4, 5);
        double area = t.getArea();
        Assertions.assertEquals(6.0, area);

    }

    @Test
    void canCalculatePerimeter() {
        Triangle t = new Triangle(4, 5, 6);
        double perimeter = t.getPerimeter();
        Assertions.assertEquals(15.0, perimeter);

    }
    // при sideA = 2, sideB = 8, sideC = 12 такого треугольника не существует

    @Test
    void canCalculateAreaForSameSides() {
        Triangle t = new Triangle(6, 6, 6);
        double expected = Math.sqrt(3) / 4 * 36;
        Assertions.assertEquals(expected, t.getArea(), 0.0001);

    }

    @Test
    void cannotCreateTriangleWithNegativeSide() {
        try {
            new Triangle(-1.0, 2.0, 3.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {

        }
    }

    @Test
    void cannotCreateTriangleWithViolatedInequality() {
        try {
            new Triangle(2.0, 3.0, 6.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
        }
    }
}



