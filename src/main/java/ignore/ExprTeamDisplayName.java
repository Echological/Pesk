package ignore;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

public class ExprTeamDisplayName extends SimplePropertyExpression<Team, String> {

    static {
        register(ExprTeamDisplayName.class, String.class, "[team] display[(-| )]name", "teams");
    }

    @Override
    protected String getPropertyName() {
        return "team display name";
    }

    @Nullable
    @Override
    public String convert(Team team) {
        return ((TextComponent)team.displayName()).content();
    }

    @Override
    @Nullable
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.DELETE || mode == Changer.ChangeMode.RESET)
            return new Class[] {String.class};
        return null;
    }

    @Override
    public void change(final Event e, @Nullable final Object[] delta, final Changer.ChangeMode mode) {
        if (delta == null) {
            for (final Team t : getExpr().getArray(e)) {
                t.displayName(null);
            }
        } else {
            final String name = (String) delta[0];
            for (final Team t : getExpr().getArray(e)) {
                t.displayName(Component.text(name));
            }
        }
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
