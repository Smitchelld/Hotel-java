import java.net.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Hotel hotel = new Hotel();

    public static Hotel getHotel() {
        return hotel;
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
        hotel.generateRooms(10, 10, 4, 100);
        CommandRegistry commandFactory = new CommandRegistry();
        commandFactory.registerCommand("checkin", CheckInCommand.class);
        commandFactory.registerCommand("checkout", CheckOutCommand.class);
        commandFactory.registerCommand("view", ViewCommand.class);
        commandFactory.registerCommand("list", ListCommand.class);
        commandFactory.registerCommand("exit", ExitCommand.class);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please enter the command - valid commands are: view, list, checkin, checkout, save, exit: ");
            System.out.println();
            String cmd = scanner.nextLine();
            try {
                Command command = commandFactory.createCommand(cmd);
                command.setHotel(hotel);
                if (command == null) {
                    System.err.println("No such command, please try again...");
                    continue;
                }
                command.execute();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.out.println("Please try again...");
            }
        }




    }

}