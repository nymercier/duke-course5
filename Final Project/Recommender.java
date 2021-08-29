
/**
 * Write a description of Recommender here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public interface Recommender {
    
    public ArrayList<String> getItemsToRate();
    
    public void printRecommendationsFor(String webRaterID);

}
