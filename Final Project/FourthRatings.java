
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class FourthRatings {
    
    public double getAverageByID(String id, int minimalRaters) {
        int number = 0;
        double valrt = 0.0;
        
        for(Rater r : RaterDatabase.getRaters()) {
            
            if(r.getItemsRated().contains(id)) {
            number ++;
            valrt += r.getRating(id);
            }   
        }
        if(number >= minimalRaters) {
            return (double)valrt/number;
        }
        return 0.0;
        //pas trouvé
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        
        ArrayList<Rating> rating = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        for(String id : movies) {
            double average =  getAverageByID(id,minimalRaters);
            if(average != 0.0) {
                Rating r = new Rating(id,average);
                rating.add(r);
            }
        }
        return rating;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,Filter filterCriteria) {
        
        ArrayList<Rating> rating = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        
        for(String id : movies) {
            double average =  getAverageByID(id,minimalRaters);
            if(average != 0.0) {
                Rating r = new Rating(id,average);
                rating.add(r);
            }
        }
        return rating;
    }
    
    private double dotProduct(Rater me, Rater r) {
        double dotProduct = 0.0;
        
        ArrayList<String> rIDs = r.getItemsRated();
        ArrayList<String> raterme = me.getItemsRated();
        
        for(String id : raterme) {
            if(rIDs.contains(id)) {
                dotProduct += (me.getRating(id) - 5) * (r.getRating(id) - 5);
                //[0,5]*[2,2]
                //0to10, -5
            }
        }
        return dotProduct;
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
        
        ArrayList<Rating> listsim = new ArrayList<Rating>();
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        
        Rater me = RaterDatabase.getRater(id);
        
        for(Rater r : raters) {
            String raterID = r.getID();
            
            if(!raterID.equals(id)) {
                double product = dotProduct(me, r);
                if(product >= 0) {
                    Rating rating = new Rating(raterID, product);
                    listsim.add(rating);
                }
            }
        }
        Collections.sort(listsim, Collections.reverseOrder());
        
        return listsim;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<Rating> list = getSimilarities(id);
        
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String mID : movies) {
            double valrt = 0.0;
            int number = 0;
            
            for(int k=0; k < numSimilarRaters; k++){
                Rating r = list.get(k);
                String rID = r.getItem();
                
                double rating1 = r.getValue();
                double rating2 = 0;
                
                try {
                    rating2 = RaterDatabase.getRater(rID).getRating(mID);
                }
                catch(NullPointerException e) {
                    continue;
                    //null
                }
                valrt += rating1 * rating2;
                number++;
            }
            if(number >= minimalRaters){
                ratings.add(new Rating(mID, (valrt/number)));
            }
        }
        Collections.sort(ratings, Collections.reverseOrder());
        return ratings;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter  (String id, int numSimilarRaters, int minimalRaters,Filter filterCriteria) {
        
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<Rating> list = getSimilarities(id);
        
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String mID : movies) {
            double valrt = 0.0;
            int number = 0;
            
            for(int k=0; k < numSimilarRaters; k++){
                Rating r = list.get(k);
                String rID = r.getItem();
                
                double rating1 = r.getValue();
                double rating2 = 0;
                try {
                    rating2 = RaterDatabase.getRater(rID).getRating(mID);
                }
                catch(NullPointerException e) {
                    continue;
                }
                valrt += rating1 * rating2;
                number++;
            }
            
            if(number >= minimalRaters) {
                ratings.add(new Rating(mID, (valrt/number)));
            }
        }
        Collections.sort(ratings, Collections.reverseOrder());
        return ratings;
    }
}