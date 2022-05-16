package gt.com.model.entity;

/**
 * 
 */
public class RoomEntity {

    public RoomEntity() {
    }

    private int idRoom;
    private int numberRoom;
    private int capacityRoom;
    private boolean statusRoom;

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(int numberRoom) {
        this.numberRoom = numberRoom;
    }

    public int getCapacityRoom() {
        return capacityRoom;
    }

    public void setCapacityRoom(int capacityRoom) {
        this.capacityRoom = capacityRoom;
    }

    public boolean isStatusRoom() {
        return statusRoom;
    }

    public void setStatusRoom(boolean statusRoom) {
        this.statusRoom = statusRoom;
    }
}