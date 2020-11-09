package me.paulf.wings.util.function;

@FunctionalInterface
public interface FloatUnaryOperator
{
    static FloatUnaryOperator identity()
    {
        return t -> t;
    }

    float applyAsFloat(float operand);
}
