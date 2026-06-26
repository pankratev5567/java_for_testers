package ru.stqa.geometry.figures;

import java.util.Objects;

public class Triangle {
    private final double sideA;
    private final double sideB;
    private final double sideC;

    public Triangle(double sideA, double sideB, double sideC) {
        //проверка на отрицательные длины сторон
        if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
            throw new IllegalArgumentException("Triangle side should not be negative");
        }

        //проверка неравенства треугольника
        if (sideA + sideB < sideC || sideA + sideC < sideB || sideB + sideC < sideA) {
            throw new IllegalArgumentException(
                    "The sum of any two sides must be greater than or equal to the third side"
            );
        }
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    //периметр
    public double getPerimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(triangle.sideA, this.sideA) == 0 && Double.compare(triangle.sideB, this.sideB) == 0 && Double.compare(triangle.sideC, this.sideC) == 0)
                || (Double.compare(triangle.sideA, this.sideA) == 0 && Double.compare(triangle.sideB, this.sideC) == 0 && Double.compare(triangle.sideC, this.sideB) == 0)
                || (Double.compare(triangle.sideA, this.sideB) == 0 && Double.compare(triangle.sideB, this.sideA) == 0 && Double.compare(triangle.sideC, this.sideC) == 0)
                || (Double.compare(triangle.sideA, this.sideB) == 0 && Double.compare(triangle.sideB, this.sideC) == 0 && Double.compare(triangle.sideC, this.sideA) == 0)
                || (Double.compare(triangle.sideA, this.sideC) == 0 && Double.compare(triangle.sideB, this.sideA) == 0 && Double.compare(triangle.sideC, this.sideB) == 0)
                || (Double.compare(triangle.sideA, this.sideC) == 0 && Double.compare(triangle.sideB, this.sideB) == 0 && Double.compare(triangle.sideC, this.sideA) == 0);
    }

    @Override
    public int hashCode() {
        return 1;
    }

    //площадь по формуле Герона
    public double getArea() {
        double p = getPerimeter() / 2;
        return Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
    }

}

