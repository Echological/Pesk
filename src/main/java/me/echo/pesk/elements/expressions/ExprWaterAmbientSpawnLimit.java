package me.echo.pesk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprWaterAmbientSpawnLimit extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprWaterAmbientSpawnLimit.class, Number.class, ExpressionType.SIMPLE, "[the] water ambient spawn[ ]limit");
    }

    @Nullable
    @Override
    protected Number[] get(Event event) {
        return new Number[] {Bukkit.getWaterAmbientSpawnLimit()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "the water ambient spawn limit";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        return true;
    }

}
