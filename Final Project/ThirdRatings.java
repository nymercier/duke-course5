
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
    private HashMap<String, ArrayList <Rating>> myRaters;
    
    public ThirdRatings() {
        this("ratings.csv");
    }
    
    public ThirdRatings (String ratingsfile) {
        FirstRatings fr = new FirstRatings ();
        this.myRaters = fr.loadRaters(ratingsfile);

        //System.out.println(myRaters);
        System.out.println("getRaterSize() (raters) is : "+ getRaterSize(myRaters));
    }
    
       
    public int getRaterSize (HashMap<String, ArrayList <Rating>> myRaters) {
        return myRaters.size();
    }
    
    private double getAverageByID (String id, int minimalRaters) {
        double average  = 0.0;
        int count =0;
        ArrayList <Double> set = new ArrayList<Double>();
        for (String s : myRaters.keySet()) {
            ArrayList <Rating> ratings = myRaters.get(s);
            //System.out.println("ratings.size() "+ratings.size());
            //System.out.println("id "+id+" minimalRaters "+minimalRaters);
            for (Rating r : ratings) {
                double rt = r.getValue();
                String idmovie = r.getItem();
                //System.out.println("id "+idmovie+" rt "+rt);
                if(idmovie.contains(id)) {
                    count = count+1;
                    //System.out.println("id "+idmovie+" rt "+rt+ " count "+count);
                    set.add(rt);
                }
            }
        }
        //System.out.println("size set "+set.size());
        if (set.size() >= minimalRaters) {
            for (Double v : set) {
                //System.out.println("v "+v);
                average = average+v;
            }
            //System.out.println("average "+average);
            average = average/count;
        }
        else {
            average = average;
        }
        return average;
    }
    
    
    public ArrayList<Rating> getAverageRatings (int minimalRaters) {
        ArrayList<Rating> averageratings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        //HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        
        //private HashMap<String, ArrayList <Rating>> myRaters;
        for (String movieID : movies) {
            //System.out.println("movieID "+movieID);
            double average = getAverageByID(movieID, minimalRaters);
            //System.out.println("average "+average);
            if (average != 0.0) {
                Rating rating = new Rating (movieID, average);
                averageratings.add(rating);
            }
        }
        return averageratings;
    }
    
    
     public ArrayList <Rating> getAverageRatingsByFilter (Filter filterCriteria, int minimalRaters) {
        ArrayList <Rating> list = new ArrayList <Rating>();
        ArrayList <String> movies = MovieDatabase.filterBy(filterCriteria);
        
        for (String id : movies) {
            double average = getAverageByID(id, minimalRaters);
            //System.out.println("average "+average);
            if (average != 0.0) {
                Rating rating = new Rating (id, average);
                list.add(rating);
            }
        }
        return list;
    }
     
    
    public int getLargest (ArrayList<Rating> ratingData, int from) {
        int maxIdx = from;
        for (int i=from+1; i< ratingData.size(); i++) {
            if (ratingData.get(i).getValue() > ratingData.get(maxIdx).getValue()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    public void sortByAVerage (ArrayList<Rating> in) {
        int nombrecontenudansin = in.size();
        for (int i=0; i<nombrecontenudansin; i++) {
            //System.out.println("i"+i);
            int maxIdx = getLargest (in,i);
            Rating qi = in.get(i);
            Rating qmax = in.get(maxIdx);
            in.set(i,qmax);
            in.set(maxIdx,qi);
        }
        
    }
    
}
