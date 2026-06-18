package ru.stqa.geometry.figures;

public class Triangle {

    private final double sideA;

    private final double sideB;

    private final double sideC;

    // Конструктор – сохраняет стороны

    public Triangle(double sideA, double sideB, double sideC) {

        this.sideA = sideA;

        this.sideB = sideB;

        this.sideC = sideC;

    }

    // Периметр

    public double getPerimeter() {

        return sideA + sideB + sideC;

    }

    // Площадь по формуле Герона

    public double getArea() {

        double p = getPerimeter() / 2;

        return Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));

    }

}

