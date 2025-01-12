import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class Hotel {

    Scanner scanner = new Scanner(System.in);

    MyMap<Integer, Room> rooms = new MyMap<>();
    MyMap<Integer, MyMap<Integer, Room>> floors = new MyMap<>();
    MyMap<String, Guest> guests = new MyMap<>();

    /**
     * Constructs an empty hotel with no rooms or guests.
     */
    public Hotel() {
    }

    /**
     * Generates rooms for the hotel across multiple floors.
     *
     * @param floors The number of floors in the hotel.
     * @param roomsPerFloor The number of rooms per floor.
     * @param capacity The capacity of each room.
     * @param price The price of each room.
     * @return The current Hotel instance with the generated rooms.
     * @throws IllegalArgumentException If floors or roomsPerFloor is less than or equal to zero.
     */
    Hotel generateRooms(Integer floors, Integer roomsPerFloor, int capacity, int price) {
        if (floors == null || floors <= 0 || roomsPerFloor == null || roomsPerFloor <= 0) {
            throw new IllegalArgumentException("Floors and roomsPerFloor must be positive integers.");
        }
        int startFloor = this.floors.isEmpty() ? 0 : this.floors.size();
        for (int floor = startFloor; floor < floors + startFloor; floor++) {
            MyMap<Integer, Room> currentFloor = this.floors.get(floor);
            if (currentFloor == null) {
                currentFloor = new MyMap<>();
                this.floors.put(floor, currentFloor);
            }
            for (int room = 0; room < roomsPerFloor; room++) {
                Integer roomId = 100 * floor + room + 1;
                Room roomToAdd = new Room(roomId, capacity, price);
                this.rooms.put(roomId, roomToAdd);
                currentFloor.put(roomId, roomToAdd);
            }
        }
        return this;
    }

    /**
     * Adds a single room to a specific floor.
     *
     * @param floor The floor number where the room will be added.
     * @param capacity The capacity of the new room.
     * @param price The price of the new room.
     * @return The current Hotel instance with the added room.
     * @throws IllegalArgumentException If floor is null.
     */
    Hotel addRoom(Integer floor, int capacity, int price) {
        if (floor == null) {
            throw new IllegalArgumentException("Floor must be positive integers.");
        }
        MyMap<Integer, Room> currentFloor = this.floors.get(floor);
        if (currentFloor == null) {
            currentFloor = new MyMap<>();
            this.floors.put(floor, currentFloor);
        }
        int roomId = 100 * floor + currentFloor.size() + 1;
        Room roomToAdd = new Room(roomId, capacity, price);
        currentFloor.put(roomId, roomToAdd);
        return this;
    }

    /**
     * Prints all room IDs across all floors.
     */
    public void print() {
        for (Integer floor : this.floors.keys()) {
            MyMap<Integer, Room> currentFloor = this.floors.get(floor);
            for (Room room : currentFloor.values()) {
                int roomId = room.getRoomId();
                if (roomId < 100) {
                    System.out.print("00" + roomId + " |");
                } else {
                    System.out.print(room.getRoomId() + " |");
                }
            }
            System.out.println();
        }
    }

    /**
     * Rent a room for a guest.
     *
     * @param guest The guest who will rent a room.
     * @return The current Hotel instance after renting a room.
     */
    public Hotel rentRoom(Guest guest) {
        for (Room room : this.rooms.values()) {
            if (!room.isOccupied()) {
                room.rent(guest);
                System.out.println("Room rented");
                return this;
            }
        }
        System.err.println("No free room found");
        return this;
    }

    /**
     * Rent a specific room to a guest.
     *
     * @param guest The guest who will rent the room.
     * @param room The room to be rented.
     * @return The current Hotel instance after renting the room.
     * @throws IllegalArgumentException If the room is already occupied.
     */
    public Hotel rentRoom(Guest guest, Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room must be positive integers.");
        }
        if (room.isOccupied()) {
            throw new IllegalArgumentException("Room already occupied.");
        }
        room.rent(guest);
        return this;
    }

    /**
     * Rent a room by its ID for a guest.
     *
     * @param guest The guest who will rent the room.
     * @param roomId The ID of the room to be rented.
     * @return The current Hotel instance after renting the room.
     * @throws IllegalArgumentException If the room is not found.
     */
    public Hotel rentRoom(Guest guest, int roomId) {
        if (!rooms.containsKey(roomId)) {
            throw new IllegalArgumentException("Room not found.");
        }
        rentRoom(guest, rooms.get(roomId));
        return this;
    }

    /**
     * View the details of a room by its ID.
     *
     * @param roomId The ID of the room to be viewed.
     * @return The current Hotel instance after viewing the room.
     */
    public Hotel view(int roomId) {
        Room room = rooms.get(roomId);
        if (room != null) {
            room.print();
            return this;
        }
        System.err.println("No room found");
        return this;
    }

    /**
     * View the details of a room by inputting its ID.
     *
     * @return The current Hotel instance after viewing the room.
     */
    public Hotel view() {
        System.out.println("Pass room number: ...");
        while (true) {
            int roomId;
            try {
                roomId = Integer.parseInt(scanner.nextLine().strip());
                if (rooms.containsKey(roomId)) {
                    view(roomId);
                    return this;
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid room number");
                System.err.println("Try again ...");
            }
        }
    }

    /**
     * Lists all rooms in the hotel.
     *
     * @return The current Hotel instance after listing the rooms.
     */
    public Hotel list() {
        for (Room room : this.rooms.values()) {
            room.print();
        }
        return this;
    }

    /**
     * Displays the prices for all rooms in the hotel.
     *
     * @return The current Hotel instance after displaying the prices.
     */
    public Hotel prices() {
        for (Room room : this.rooms.values()) {
            room.toStringPrices();
        }
        return this;
    }

    /**
     * Check-in a guest into a room.
     *
     * @return The current Hotel instance after checking in the guest.
     */
    public Hotel checkIn() {
        System.out.println("Checking in");
        System.out.println("Pass room number: ...");
        Integer roomId = 0;
        while (true) {
            String tmp = scanner.nextLine().strip();
            if (tmp.equals("exit")) return this;
            try {
                roomId = Integer.parseInt(tmp);
                if (rooms.containsKey(roomId)) {
                    break;
                }
                System.out.println("Room not found.");
                System.out.println("Try again ...");
            } catch (NumberFormatException e) {
                System.out.println("Invalid room number");
                System.out.println("Try again ...");
            }
        }
        System.out.println("Room number: " + roomId);
        System.out.println("Pass guest name: ...");
        String guestName = scanner.nextLine().strip();
        Guest guest;
        if (guests.containsKey(guestName)) {
            guest = guests.get(guestName);
        } else {
            guest = new Guest(guestName);
            guests.put(guestName, guest);
        }
        try {
            rentRoom(guest, roomId);
            System.out.println("Checking in: " + guestName + " in room number: " + roomId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("How many extra guests? Room capacity: " + (rooms.get(roomId).getCapacity() - 1) + "...");
        while (true) {
            try {
                int tmp = Integer.parseInt(scanner.nextLine().strip());
                if (tmp == 0) return this;
                if (tmp > 0) {
                    for (int i = 0; i < tmp; i++) {
                        System.out.println("Pass guest name: ...");
                        guestName = scanner.nextLine().strip();
                        if (guests.containsKey(guestName)) {
                            guest = guests.get(guestName);
                        } else {
                            guest = new Guest(guestName);
                            guests.put(guestName, guest);
                        }
                        rooms.get(roomId).addGuest(guest);
                        System.out.println("Checking in: " + guestName + " in room number: " + roomId);
                    }
                    return this;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Check-out a guest from a room.
     *
     * @return The current Hotel instance after checking out the guest.
     */
    public Hotel checkOut() {
        System.out.println("Checking out");
        System.out.println("Pass room number: ...");
        int roomId = 0;
        Room room;
        while (true) {
            try {
                String tmp = scanner.nextLine().strip();
                if (tmp.equals("cancel")) return this;
                roomId = Integer.parseInt(tmp);
                room = rooms.get(roomId);
                break;
            } catch (Exception e) {
                System.out.println("Invalid room number");
            }
        }
        long price = room.checkOut();
        System.out.println("Checking out from room number: " + roomId);
        System.out.println("Charge: " + price);
        return this;
    }

    /**
     * Change the check-in date for a room.
     *
     * @param date The new check-in date.
     * @param roomId The ID of the room to update.
     */
    public void changeTime(LocalDate date, int roomId) {
        try {
            rooms.get(roomId).setCheckInDate(date);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Exits the current hotel system.
     *
     * @return The current Hotel instance after exiting.
     */
    public Hotel exit() {
        System.out.println("Exit");
        return this;
    }
}
