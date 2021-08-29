
/**
 * Décrivez votre classe MovieRunnerAverage ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */

import java.util.*;

public class MovieRunnerAverage {
    
    public void printAverageRatings () {
        //SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");

        //int nbfilm = sr.getMovieSize(myMovies);
        //System.out.println(sr.test());
        System.out.println("---------------------------------------------");
        int mraters = 50;
        //System.out.println("getAverageRatings() is : " +getAverageRatings(mraters)+ " minraters : "+mraters);
        
        ArrayList<Rating> getAverage= sr.getAverageRatings(mraters);
        for (Rating r : getAverage) {
            String id = r.getItem();
            String nomassocié = sr.getTitre(id);
            System.out.println(r);
        }
        System.out.println("---------------------------------------------");
        System.out.println("Combien de film avec min de : "+mraters + " est de : "+getAverage.size());
        System.out.println("---------------------------------------------");
        System.out.println("Du plus grand au plus petit");
        sr.sortByAVerage(getAverage);
        for (Rating r : getAverage) {
            String id = r.getItem();
            String nomassocié = sr.getTitre(id);
            System.out.println(nomassocié+"\t"+"rating "+r);
        }
    }
    
    public void getAverageRatingOneMovie () {
        //SecondRatings sedratings = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        //SecondRatings sedratings = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        //SecondRatings sedratings = new SecondRatings("data/ratedmovies_short - Copy.csv", "data/ratings_short - Copy.csv");
        
        SecondRatings sedratings = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings_short - Copy.csv");
        //ICI POUR CHANGER LES RATINGS
        
        String nameofmovie = "Inside Llewyn Davis";
        //String idofthemovie = sedratings.getID(nameofmovie);
        //sedratings.getAverageByID(idofthemovie,2);
        
        //min ratings
        ArrayList<Rating> average = sedratings.getAverageRatings(2);
        System.out.println("---------------------------------------------");
        for (Rating r : average) {
            //System.out.println("rating "+r.toString());
            //System.out.println("---------------------------------------------");
            if (sedratings.getTitre(r.getItem()).equals(nameofmovie)) {
                System.out.println("Moyenne du film = "+nameofmovie+ " est "+r.getValue());
            }
        }
    }
}
