package me.echo.pesk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.PufferFish;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprPufferfishPuffState extends SimplePropertyExpression<LivingEntity, Number> {

    static {
        if (Skript.classExists("org.bukkit.entity.PufferFish")) {
            register(ExprPufferfishPuffState.class, Number.class, "puff[ ]state", "livingentities");
        }
    }

    @Override
    @Nullable
    public Number convert(final LivingEntity e) {
        if (!(e instanceof PufferFish)) return null;
        return ((PufferFish)e).getPuffState();
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
                ((PufferFish)entity).setPuffState(0);
            }
        } else {
            final Number num = (Number) delta[0];
            for (final LivingEntity entity : getExpr().getArray(e)) {
                ((PufferFish)entity).setPuffState(num.intValue());
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "puffstate";
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }
}
