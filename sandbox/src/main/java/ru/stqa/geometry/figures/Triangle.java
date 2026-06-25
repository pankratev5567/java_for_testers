package ru.stqa.geometry.figures;

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

    //площадь по формуле Герона
    public double getArea() {
        double p = getPerimeter() / 2;
        return Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
    }

}

