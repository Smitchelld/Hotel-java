public class ExitCommand extends Command {
    @Override
    public void execute() {
        System.out.println("Exiting...");
        System.exit(0);
    }
}
