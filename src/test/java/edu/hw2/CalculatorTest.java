package edu.hw2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static edu.hw2.Calculator.*;
class CalculatorTest {

    @Test
    void ExprTest() {
        Calculator.Constant two = new Calculator.Constant(2);
        Calculator.Constant four = new Calculator.Constant(4);
        Calculator.Negate negOne = new Calculator.Negate(new Calculator.Constant(1));
        Calculator.Addition sumTwoFour = new Calculator.Addition(two, four);
        Calculator.Multiplication mult = new Calculator.Multiplication(sumTwoFour, negOne);
        Calculator.Exponent exp = new Calculator.Exponent(mult, two);
        Calculator.Addition res = new Calculator.Addition(exp, new Calculator.Constant(1));

        System.out.println(res + " = " + res.evaluate());
        Assertions.assertEquals(res.evaluate(),37.0);

    }

}
