package platform.shared.infrastructure;

import platform.shared.domain.command.Command;
import platform.shared.domain.command.CommandBus;
import platform.shared.domain.command.CommandHandler;

import java.util.HashMap;
import java.util.Map;

public class MemoryCommandBus implements CommandBus {

    private static final Map<Class<? extends Command>, CommandHandler> handlers = new HashMap<>();

    @Override
    public void register(Class<? extends Command> command, CommandHandler handler) {
        handlers.put(command, handler);
    }

    @Override
    public void dispatch(Command command) throws Exception {
        handlers.get(command.getClass()).handle(command);
    }

}
