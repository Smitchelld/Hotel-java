public class Guest {
    // The name of the guest.
    private String name;

    // The room that the guest is currently staying in.
    private Room room;

    /**
     * Constructor to create a guest with a specified name.
     *
     * @param name The name of the guest.
     */
    public Guest(String name) {
        this.name = name;
    }

    /**
     * Get the name of the guest.
     *
     * @return The guest's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the room for the guest, linking the guest to a specific room.
     *
     * @param room The room the guest is staying in.
     */
    void setRoom(Room room) {
        this.room = room;
    }
}