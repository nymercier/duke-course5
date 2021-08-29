
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
import java.io.*;



public class MovieRunnerWithFilters {
    
    public void printAverageRatings () {       
        //ici c'est des ratings +++
        ThirdRatings tr = new ThirdRatings ("data/ratings.csv");
        
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmoviesfull.csv");
        System.out.println("md.size() (read data for) : "+md.size()+ " movies ");
        
        
        //int nbfilm = sr.getMovieSize(myMovies);
        //System.out.println(sr.test());
        System.out.println("---------------------------------------------");
        int mraters = 35;
        //System.out.println("getAverageRatings() is : " +getAverageRatings(mraters)+ " minraters : "+mraters);
        
        ArrayList<Rating> getAverage= tr.getAverageRatings(mraters);
        tr.sortByAVerage(getAverage);
        for (Rating r : getAverage) {
            String id = r.getItem();
            //String nomassocié = tr.getTitre(id);
            System.out.println(r);
            
        }
        System.out.println("getAverage.size() "+getAverage.size());
    }
    
    public void printAverageRatingsByYear () {
        ThirdRatings tr = new ThirdRatings ("data/ratings.csv");
        
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmoviesfull.csv");
        System.out.println("md.size() (read data for) : "+md.size()+ " movies ");
        
        System.out.println("---------------------------------------------");
        int mraters = 1;
        int annéedemandée = 2000;
        Filter yr = new YearAfterFilter(annéedemandée);
        ArrayList<Rating> getAverage= tr.getAverageRatingsByFilter(yr, mraters);
        tr.sortByAVerage(getAverage);
        for (Rating r : getAverage) {
            String id = r.getItem();
            String name = md.getTitle(id);
            System.out.println(r + "\t" +name);
        }
        System.out.println("Results of printAverageRatingsByYear "+annéedemandée+ " : "+getAverage.size());
        System.out.println("---------------------------------------------");
    }
    
    public void printAverageRatingsByGenre  () {
        ThirdRatings tr = new ThirdRatings ("data/ratings_short.csv");
        
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmovies_short.csv");
        System.out.println("md.size() (read data for) : "+md.size()+ " movies ");
        
        System.out.println("---------------------------------------------");
        int mraters = 1;
        String genredemandé = "Crime";
        Filter gr = new GenreFilter(genredemandé);
        ArrayList<Rating> getAverage= tr.getAverageRatingsByFilter(gr, mraters);
        tr.sortByAVerage(getAverage);
        for (Rating r : getAverage) {
            String id = r.getItem();
            String name = md.getTitle(id);
            String genre = md.getGenres(id);
            System.out.println(r + "\t" +name);
            System.out.println("\t" +genre);
        }
        System.out.println("Results of printAverageRatingsByGenre "+genredemandé+ " : "+getAverage.size());
        System.out.println("---------------------------------------------");
    }
    
    public void printAverageRatingsByDirectors () {
        ThirdRatings tr = new ThirdRatings ("data/ratings_short.csv");
        
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmovies_short.csv");
        System.out.println("md.size() (read data for) : "+md.size()+ " movies ");
        
        //ThirdRatings tr = new ThirdRatings ("data/ratings.csv");
        
        //MovieDatabase md = new MovieDatabase();
        //md.initialize("ratedmoviesfull.csv");
        //System.out.println("md.size() (read data for) : "+md.size()+ " movies ");
        
        System.out.println("---------------------------------------------");
        int mraters = 1;
        String directeurdemandé = "Charles Chaplin,Michael Mann,Spike Jonze";
        
        Filter dr = new DirectorsFilter(directeurdemandé);
        ArrayList<Rating> getAverage= tr.getAverageRatingsByFilter(dr, mraters);
        tr.sortByAVerage(getAverage);
        for (Rating r : getAverage) {
            String id = r.getItem();
            String name = md.getTitle(id);
            String genre = md.getGenres(id);
            int tempsdufilm = md.getMinutes(id);
            String dirdem = md.getDirector(id);
            System.out.println(r + "\t" +name);
            System.out.println("\t" +dirdem);
        }
        System.out.println("Results of ByDirectors contenant : "+directeurdemandé+" : "+getAverage.size());
        System.out.println("---------------------------------------------");
    }
    
    public void printAverageRatingsByMinutes () {
        ThirdRatings tr = new ThirdRatings ("data/ratings_short.csv");
        
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmovies_short.csv");
        System.out.println("md.size() (read data for) : "+md.size()+ " movies ");
        
        System.out.println("---------------------------------------------");
        int mraters = 1;
        int minminutes =110;
        int maxminutes =170;
        
        Filter gr = new MinutesFilter(minminutes,maxminutes);
        ArrayList<Rating> getAverage= tr.getAverageRatingsByFilter(gr, mraters);
        tr.sortByAVerage(getAverage);
        for (Rating r : getAverage) {
            String id = r.getItem();
            String name = md.getTitle(id);
            String genre = md.getGenres(id);
            int tempsdufilm = md.getMinutes(id);
            System.out.println(r + "\t" +name + "\t"+tempsdufilm+"minutes");
            //System.out.println("\t" +genre);
        }
        System.out.println("Results of ByMinutes min : "+minminutes+ " et max "+maxminutes+ " : "+getAverage.size());
        System.out.println("---------------------------------------------");
    }
    
    public void printAverageRatingsByYearAfterAndGenre () {
        ThirdRatings tr = new ThirdRatings ("data/ratings.csv");
        
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmoviesfull.csv");
        System.out.println("md.size() (read data for) : "+md.size()+ " movies ");
        
        System.out.println("---------------------------------------------");
        
        int mraters = 1;
        String gr = "Romance";
        int yr = 1980;
        
        AllFilters all = new AllFilters();
        all.addFilter(new GenreFilter(gr));
        all.addFilter(new YearAfterFilter(yr));
        
        ArrayList<Rating> getAverage= tr.getAverageRatingsByFilter(all, mraters);
        tr.sortByAVerage(getAverage);
        for (Rating r : getAverage) {
            String id = r.getItem();
            String name = md.getTitle(id);
            String genre = md.getGenres(id);
            int tempsdufilm = md.getMinutes(id);
            System.out.println(r + "\t" +name + "\t"+tempsdufilm+"minutes");
            System.out.println("\t" +genre);
        }
        System.out.println("Results of ByYearAfterAndGenre : "+getAverage.size());
        System.out.println("---------------------------------------------");
    }
    
    
    public void printAverageRatingsByDirectorsAndMinutes () {
        ThirdRatings tr = new ThirdRatings ("data/ratings_short.csv");
        
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmovies_short.csv");
        System.out.println("md.size() (read data for) : "+md.size()+ " movies ");
        
        System.out.println("---------------------------------------------");
        
        int mraters = 1;
        int minminutes =30;
        int maxminutes =170;
        String directeurdemandé = "Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola";
        
        AllFilters all = new AllFilters();
        all.addFilter(new MinutesFilter(minminutes, maxminutes));
        all.addFilter(new DirectorsFilter(directeurdemandé));
        
        ArrayList<Rating> getAverage= tr.getAverageRatingsByFilter(all, mraters);
        tr.sortByAVerage(getAverage);
        for (Rating r : getAverage) {
            String id = r.getItem();
            String name = md.getTitle(id);
            String genre = md.getGenres(id);
            int tempsdufilm = md.getMinutes(id);
            String direc = md.getDirector(id);
            System.out.println(r + "\t" +name + "\t"+tempsdufilm+"minutes");
            System.out.println("\t" +direc);
        }
        System.out.println("Results of ByDirectorsAndMinutes : "+getAverage.size());
        System.out.println("---------------------------------------------");
    }
}
