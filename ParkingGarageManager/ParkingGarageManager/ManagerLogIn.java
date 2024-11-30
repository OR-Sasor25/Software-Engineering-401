package ParkingGarageManager;
public class ManagerLogIn {
    private final String id; 
    private final String password; 
    private String status; 
    private final int garageID; 

   
    public ManagerLogIn(String id, String password, int garageID) {
        this.id = id;
        this.password = password;
        this.status = "inactive"; // <---THIS IS THE DEFAULT SET 
        this.garageID = garageID;
    }

    // getters/setters
    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    public int getGarageID() {
        return garageID;
    }

    // only setter for status
    public void setStatus(String status) {
        this.status = status;
    }

    // method to validate login (sets the status to "active" if valid)
    public void validateLogin(boolean isValid) {
        if (isValid) {
            this.status = "active";
        } else {
            this.status = "inactive";
        }
    }

    // method to select garage
    public void selectGarage(int garageID) {
        if ("active".equals(this.status)) { //this is to make sure manager is logged in
            System.out.println("Garage ID " + garageID + " is selected.");
        } else {
            System.out.println("Log in first!");
        }
    }
}
