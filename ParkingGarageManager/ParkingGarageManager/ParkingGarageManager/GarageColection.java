package ParkingGarageManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GarageColection {
	    private static final List<Garage> sharedArray = Collections.synchronizedList(new ArrayList<>(5));

	    public static List<Garage> getSharedArray() {
	        return sharedArray;
	    }
}
