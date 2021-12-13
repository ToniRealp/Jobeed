package platform.shared.domain.command;

public interface CommandBus {
    void register(Class<? extends Command> command, CommandHandler handler);
    void dispatch(Command command) throws Exception;
}
