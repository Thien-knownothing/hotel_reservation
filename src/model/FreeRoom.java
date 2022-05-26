package model;

public class FreeRoom extends Room{
    public FreeRoom(String roomNumber, Double price, RoomType enumeration) {
        super(roomNumber, (double) 0, enumeration);

    }
    @Override
    public String toString(){
        return "Free room:" + super.toString();
    }
}
