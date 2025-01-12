public abstract class Command {
    protected Hotel hotel;

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    public abstract void execute();
}
