package Model;

/**
 * Supplied class InHouse.java
 */

public class InHouse extends Part {

     int machineId;



    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * @return the machineID
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * @param machineId the machine Id to set.
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }


}