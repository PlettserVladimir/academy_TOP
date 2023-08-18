package com.top.calc;

public class CosineTheorem {
    //Нахождение третьей стороны треугольника по двум сторонам и углу между ними.
    public double foundingASide(double c, double b,double alfa){
        double res;
        res=Math.sqrt(Math.pow(c,2)+Math.pow(b,2)-(2*b*c*Math.cos(alfa)));// Формула теоремы косинусов
        return res;
    }
}
