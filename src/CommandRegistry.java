import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private final Map<String, Class<? extends Command>> commandMap = new HashMap<>();

    public void registerCommand(String name, Class<? extends Command> commandClass) {
        commandMap.put(name, commandClass);
    }

    public Command createCommand(String commandName){
        Class<? extends Command> commandClass = commandMap.get(commandName);


        if(commandClass == null){
            throw new IllegalArgumentException("Unknown command: " + commandName);
        }
        try{
            return commandClass.getDeclaredConstructor().newInstance();
        }catch (Exception e){
            throw new RuntimeException("Failed to create command instance", e);
        }
    }
}
