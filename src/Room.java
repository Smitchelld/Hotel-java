import java.time.LocalDate;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Room {
    static List<Integer> roomIds = new ArrayList<>();
    private List<Guest> extraGuests = new ArrayList<>();
    private int roomId;
    private int capacity;
    private int price;
    private boolean occupied;
    private Guest guest;
    private LocalDate checkInDate;



    public Room(int roomId, int capacity, int price) {
        if (roomIds.contains(roomId)) {
            throw new IllegalArgumentException("Room id already exists");
        }
        this.roomId = roomId;
        this.capacity = capacity;
        this.price = price;
    }

    public void rent(Guest guest) {
        if(occupied) {
            throw new IllegalArgumentException("Room is occupied");
        }
        this.guest = guest;
        this.guest.setRoom(this);
        this.checkInDate = LocalDate.now();
        this.occupied = true;
    }

    public long checkOut(){
        guest.setRoom(null);
        guest = null;
        for (Guest g : extraGuests) {
            g.setRoom(null);
        }
        extraGuests.clear();
        this.occupied = false;
        return ChronoUnit.DAYS.between(checkInDate, LocalDate.now()) * this.price;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    void print(){
        System.out.println();
        String id = roomId > 100? roomId + "" : "00" + roomId;
        System.out.print(   "RoomID: " + id + "\n" +
                "Price: " + price + "\n" +
                "Occupied: " + occupied + "\n");
        if (occupied && extraGuests.isEmpty()) System.out.println("Guest : " + guest.getName());
        else if (occupied){
            System.out.print("Guests : " + guest.getName());
            for (Guest g : extraGuests) {
                System.out.print(", " + g.getName());
            }
            System.out.print("\n ");
        }
    }

    void toStringPrices(){
        String id = roomId > 100? roomId + "" : "00" + roomId;
        System.out.println("RoomID: " + id + " Price: " + price);
    }

    void addGuest(Guest guest) {
        if((capacity - 1) == extraGuests.size()) throw new IllegalArgumentException("Room full");
        extraGuests.add(guest);
        guest.setRoom(this);
    }

}
