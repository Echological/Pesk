package me.echo.pesk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.entity.Bee;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprBeeAnger extends SimplePropertyExpression<LivingEntity, Number> {

    static {
        if (Skript.classExists("org.bukkit.entity.Bee")) {
           register(ExprBeeAnger.class, Number.class, "anger", "livingentities");
        }
    }

    @Override
    @Nullable
    public Number convert(final LivingEntity e) {
        if (!(e instanceof Bee)) return null;
        return ((Bee)e).getAnger();
    }

    @Override
    @Nullable
    public Class<?>[] acceptChange(final ChangeMode mode) {
        if (mode == ChangeMode.SET || mode == ChangeMode.DELETE || mode == ChangeMode.RESET)
            return new Class[] {Number.class};
        return null;
    }

    @Override
    public void change(final Event e, @Nullable final Object[] delta, final ChangeMode mode) {
        if (delta == null) {
            for (final LivingEntity entity : getExpr().getArray(e)) {
                ((Bee)entity).setAnger(0);
            }
        } else {
            final Number level = (Number) delta[0];
            for (final LivingEntity entity : getExpr().getArray(e)) {
                ((Bee)entity).setAnger(level.intValue());
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "anger";
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }
}
