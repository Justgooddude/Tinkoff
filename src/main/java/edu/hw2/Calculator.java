package edu.hw2;

public class Calculator {
    public interface Expr {
        double evaluate();
    }

    public static class Constant implements Expr {
        private double constant;

        public Constant(double con) {
            this.constant = con;
        }

        public Constant() {
            this.constant = 0;
        }

        public double evaluate() {
            return this.constant;
        }
    }

    public static class Negate extends Constant implements Expr {
        Constant input;

        public Negate(Constant constan) {
            this.input = constan;
        }

        public double evaluate() {
            return -this.input.evaluate();
        }
    }

    public static class Exponent extends Constant implements Expr {
        Constant input1;
        Constant input2;

        public Exponent(Constant constan1, Constant constan2) {
            this.input1 = constan1;
            this.input2 = constan2;
        }

        public double evaluate() {
            return Math.pow(this.input1.evaluate(), this.input2.evaluate());
        }
    }

    public static class Addition extends Constant implements Expr {
        Constant input1;
        Constant input2;

        public Addition(Constant constan1, Constant constan2) {
            this.input1 = constan1;
            this.input2 = constan2;
        }

        public double evaluate() {
            return this.input1.evaluate() + this.input2.evaluate();
        }
    }

    public static class Multiplication extends Constant implements Expr {
        Constant input1;
        Constant input2;

        public Multiplication(Constant constan1, Constant constan2) {
            this.input1 = constan1;
            this.input2 = constan2;
        }

        public double evaluate() {
            return this.input1.evaluate() * this.input2.evaluate();
        }
    }

}
