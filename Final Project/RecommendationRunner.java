
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
import java.util.Random.*;

public class RecommendationRunner implements Recommender {
    private Random myRandom;
    
    public RecommendationRunner(){
        myRandom = new Random();
    }
    
    public ArrayList<String> getItemsToRate() {
        
        ArrayList<String> nameofmovies = new ArrayList<String>();
        
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
                
        for (int i=0; i < 10; i++) {
            int choix = myRandom.nextInt(MovieDatabase.size());
            String currMovie = movies.get(choix);
            nameofmovies.add(currMovie);
        }
        return nameofmovies;
        
        //10-20 movies should be fine to get a good profile of the user’s opinions, but 50 may be too many.
    }
        
    public void test() {
        ArrayList<String> movieList = getItemsToRate();
        System.out.println(movieList);  
    }
        
    public void printRecommendationsFor(String webRaterID) {
        //a String that is the ID of the user, who has been added by our code to the RaterDatabase with the ratings they entered
        
        FourthRatings fr = new FourthRatings();
        
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
                
        //AllFilters f = new AllFilters();
        
        //f.addFilter(new YearAfterFilter(1975));
        //f.addFilter(new MinutesFilter(70,200));
        
        ArrayList<Rating> ratings = fr.getSimilarRatings(webRaterID,10,3);
        
        int num = ratings.size();
        if (num == 0){
            System.out.println("Not Found");
        }
        
        System.out.println("<style>td,th{border:1px solid #0066ff;text-align:left;padding:8px}</style>");
        
        System.out.println("<table>");
        
        System.out.println("<tr><th>Rank</th><th>Movie Title</th></tr>");
        
        for(int i = 0; i < 15; i ++) {
            int number = i+1;
            System.out.println("<tr><td>"+(number)+"</td><td>" + MovieDatabase.getTitle(ratings.get(i).getItem()) + "</td></tr>");
        }
        
        System.out.println("</table>");
        
    }
    
}
