import java.time.LocalDate;


public class GarageReports {

    private String date; // the day of the chosen report
    private String id; // this is the 
    private String paySummary; // this calculates how much the garage profited for the day (should be calcuated in the method )
    private int carTracker; // track how many cars were entered in the garage for the day
    
    //This is an approach that makes the user input the values instead of the program doing it all on its own
    public GarageReports(){
        this.date = fetchCurrentDate(); 
        this.paySummary = paysummary;
        this.carTracker = carTracker; 
        this.id = generateRandomID(); // creating a unique ID
    }
    // create a method that prints the report

    public String getDate(){

        return date;
    }
    
    public String getID() {

        return id;
    } 

    public String getSummary(){

        return paySummary;
            /*  the payment amount needs to be saved or stored so we can get the summary of the day
            maybe when someone pays their bill, the amount that is showed at payment not only shows the customer but is saved within a variable,
            file, etc, so we can have access and use the information later. (create list, array, file?) */
    }

    public String getCarTracker(){

        return carTracker;
            // discuss with the others on how each car is being tracked. (One idea is that for every person that pays, the car counter rises also)
    }

    public String toString(){


        return "Details of the report: Date - " + date + " ID - " + id + " Payment Summary - " + paySummary + "How many cars entered for the day - " + carTracker;
    }

    private String generateRandomID(){
        
        return "GR-" + (int)(Math.random() * 10000);
    }

    private String fetchCurrentDate(){
        
        return LocalDate.now().toString();
    }
}