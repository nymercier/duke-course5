
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private HashMap<String, ArrayList <Rating>> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
        
    }
    
    public SecondRatings (String moviefile, String ratingsfile) {
        FirstRatings fr = new FirstRatings ();
        //ArrayList<Movie> myMovies = new ArrayList<Movie> ();
        this.myMovies = fr.loadMovies(moviefile);
        
        //System.out.println(myMovies);
        System.out.println("getMovieSize() : "+ getMovieSize(myMovies));
        //ArrayList<Rater> myRaters = new ArrayList<Rater> ();
        //HashMap <String, ArrayList <Rating>> loadRaters = new HashMap<String, ArrayList <Rating>> ();
        this.myRaters = fr.loadRaters(ratingsfile);

        //System.out.println(myRaters);
        System.out.println("getRaterSize() is : "+ getRaterSize(myRaters));
        
        /*
        System.out.println("---------------------------------------------");
        String id = "0068646";
        int minraters = 2;
        System.out.println("getAverageByID() for id : "+id+ " is : " +getAverageByID(id,minraters)+ " minraters : "+minraters);
        */
       
       
        /*
        System.out.println("---------------------------------------------");
        int mraters = 50;
        //System.out.println("getAverageRatings() is : " +getAverageRatings(mraters)+ " minraters : "+mraters);
        
        ArrayList<Rating> getAverage= getAverageRatings(mraters);
        for (Rating r : getAverage) {
            String id = r.getItem();
            String nomassocié = getTitre(id);
            System.out.println(r + " "+nomassocié);
        }
        System.out.println("---------------------------------------------");
        System.out.println("Combien de film avec min de : "+mraters + " est de : "+getAverage.size());
        */         
        
        /*
        System.out.println("---------------------------------------------");
        String idmovie = "0113277";
        //System.out.println(myMovies);
        //System.out.println(test());
        System.out.println("getTitle is : " +getTitre(idmovie)+ " for " + idmovie);
        
        System.out.println("---------------------------------------------");
        String namemovie = "The Godfather";
        System.out.println("getID is : " +getID(namemovie)+ " for " + namemovie);
        */
        
    }
    

    public String getTitre (String id) {
        String title = "";
        for (Movie m: myMovies) {
            String item = m.getID();
            if (item.equals(id)) {
                title = m.getTitle();
                return title;
            }
            else {
                title = "nothing found";
            }
        }
        return title;
    }
    
    public String getID (String title) {
        //This method returns the movie ID of this movie
        String getID = "";
        for (Movie m: myMovies) {
            //System.out.println(myMovies);            
            String itemtitle = m.getTitle();
            //System.out.println("itemtitle "+itemtitle);
            if (itemtitle.equals(title)) {
                getID = m.getID();
                return getID;
            }
            else {
                getID = "NO SUCH TITLE.";
            }
        }
        return getID;
    }
    
    
    public int getMovieSize (ArrayList<Movie> myMovies) {
        return myMovies.size();
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
        if (set.size() > minimalRaters) {
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
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        
        //private HashMap<String, ArrayList <Rating>> myRaters;
        for (String s : myRaters.keySet()) {
            ArrayList <Rating> ratings = myRaters.get(s);
            for (Rating r : ratings) {
                double rt = r.getValue();
                String idmovie = r.getItem();
                //System.out.println("rater "+s+" id "+idmovie+" rt "+rt);
                if (!map.containsKey(idmovie)) {
                    ArrayList<String> nameofraters = new ArrayList<String>();
                    nameofraters.add(s);
                    map.put(idmovie,nameofraters);
                }
                
                else {
                    map.get(idmovie).add(s);
                }
            }
        }
        
        for (String rfinal : map.keySet()) {
            //explications visuelles
            //System.out.println("id final "+rfinal+" raters "+map.get(rfinal));
            String idtrouvé = null;
            if(map.get(rfinal).size() > minimalRaters) {
                idtrouvé = rfinal;
                //System.out.println("idtrouvé "+idtrouvé);
                double résultatduidtrouvé = getAverageByID(idtrouvé, minimalRaters);
                Rating trouvé = new Rating(idtrouvé,résultatduidtrouvé);
                averageratings.add(trouvé);
            }
        }
        return averageratings;
    }
    
    /*
     * public ArrayList <Rating> getAverageRatings(Filter f, int minimalRaters)
     * ArrayList <Rating> list = new ArrayList <Rating>();
     * ArrayList <String> movies = MovieDatabase.filterBy(f);
     * for (String id : movies) {
     *  Calculate average
     * }
     */
    
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
