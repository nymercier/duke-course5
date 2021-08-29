
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    
    public ArrayList <Movie> shortloadMovies (String filename) {
        ArrayList <Movie> readmovies = new ArrayList <Movie> ();
        FileResource fr = new FileResource (filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            //System.out.println(record);
            Movie nw = new Movie (record.get("id"),record.get("title"), record.get("year"),record.get("genre"));
            readmovies.add(nw);
        }
        return readmovies;
    }
    
    
    public ArrayList <Movie> loadMovies (String filename) {
        ArrayList <Movie> readmovies = new ArrayList <Movie> ();
        
        FileResource fr = new FileResource (filename);
        CSVParser parser = fr.getCSVParser();
        
        for (CSVRecord record : parser) {
            //System.out.println(record);
            Movie nw = new Movie (record.get("id"),record.get("title"), record.get("year"), 
                                  record.get("genre"), record.get("director"), record.get("country"), record.get("poster"),
                                  Integer.parseInt(record.get("minutes")));
            readmovies.add(nw);
        }
        return readmovies;
    }
    
    public void testLoadMovies () {
        //ArrayList <Movie> nw = loadMovies ("data/"+"ratedmovies_short.csv");
        ArrayList <Movie> nw = loadMovies ("data/"+"ratedmoviesfull.csv"); 
        HashMap<String, Integer> directors = new HashMap<String, Integer>();
        
        String genres = "Comedy";
        int countgenres = 0;
        
        int minutes = 150;
        int countminutes = 0;
        
        String nomdudir = "Alfred Hitchcock";
                
        for (Movie s : nw) {
            //System.out.println("ID du film "+s.getID());
            //System.out.println("Genre du film "+s.getGenres());
            
            //Add code to determine how many movies include the Comedy genre. In the file ratedmovies_short.csv, there is only one.
            if (s.getGenres().contains(genres)) {
                countgenres++;
            }           
            if (s.getMinutes() > minutes) {
                countminutes++;
            }
            
            String directeur = s.getDirector();
            //maximum number of movies by any director, and who the directors are that directed that many movies
            if (!directors.containsKey(directeur)) {
                if (!directeur.contains(",")) {
                    directors.put(directeur,1); 
                }
                else {
                    //System.out.println(directeur);
                    String [] output = (directeur.split(","));
                    for (int i=0; i< output.length; i++) {
                        directors.put(output[i].trim(),1);
                    }
                }
            }
            else if (directors.containsKey(directeur)) {
                int nb = directors.get(directeur);
                System.out.println("test nb "+nb);
                directors.put(directeur,directors.get(directeur)+1); 
            }
        }
        
        System.out.println("---------------------------------------------");
        
        System.out.println("Nombre de films "+nw.size());
        System.out.println("Films du genre "+ genres+"\t"+" nb "+countgenres);
        System.out.println("Films durant plus de "+ minutes + " nb "+countminutes);  
        
        System.out.println("---------------------------------------------");
        System.out.println("directeur avec le plus de films "+ biggestvalues(directors));
       
        //HashMap<String, Integer> directors = new HashMap<String, Integer>();
        
        for (Map.Entry <String, Integer> entry : directors.entrySet()) {
            if (entry.getValue().equals(biggestvalues(directors))) {
                System.out.println("nom de celui-ci "+entry.getKey()+entry.getValue());
            }
        }
        
        
        System.out.println("---------------------------------------------");
        for (String m : directors.keySet()) {
            if(directors.containsKey(nomdudir)==true){
                System.out.println("Nom demandé " + nomdudir +" nb de films "+ directors.get(nomdudir));
                break;
            }
            //System.out.println("nom du directeur "+m +"\t"+ "nb de fois "+ directors.get(m));
            //System.out.println(directors.get(biggestvalues(directors)));
        } 

    }
    
    public void testshortLoadMovies () {
        //ArrayList <Movie> nw = loadMovies ("data/"+"ratedmovies_short.csv");
        ArrayList <Movie> nw = shortloadMovies ("data/"+"ratedmovies_short - Copy.csv"); 
        
    }
        
    public int biggestvalues (HashMap<String, Integer> directors) {
        int big = 0;
        for (Integer v : directors.values()) {
            int curr = v;
            if (curr > big) {
                big = curr;
            }
        }
        return big;
    }
    
    public  HashMap<String, ArrayList <Rating>> loadRaters (String filename) {
        HashMap<String, ArrayList <Rating>> readraters = new HashMap<String, ArrayList <Rating>>();
        
        FileResource fr = new FileResource (filename);
        CSVParser parser = fr.getCSVParser();
        
        for (CSVRecord record : parser) {
            Rater rater = new PlainRater (record.get("rater_id"));
            String id = rater.getID();
            //System.out.println("test getID "+ id.toString());
            Rating rt = new Rating (record.get("movie_id"), Double.parseDouble(record.get("rating")));
           
            if (!readraters.containsKey(id)) {
                ArrayList <Rating> myRatings = new ArrayList <Rating> ();
                myRatings.add(rt);
                readraters.put(id,myRatings);
            }
            else {
                readraters.get(id).add(rt);
            }
        }
        return readraters;
    } 
    
    public void testLoadRaters  () {
        //HashMap <String, ArrayList <Rating>> nr = loadRaters ("data/"+"ratings_short.csv");
        HashMap<String, ArrayList <Rating>> nr = loadRaters ("data/"+"ratings.csv"); 

        System.out.println("---------------------------------------------");
        System.out.println("Nombre de raters "+nr.size());
        
        System.out.println("---------------------------------------------");
        
        for (String m : nr.keySet()) {
            System.out.println("Rater "+m +"\t"+ "nb de rating "+ nr.get(m));
            //System.out.println(directors.get(biggestvalues(directors)));
        }
        
        System.out.println("---------------------------------------------");
        String idrech = "193";
        for (String m : nr.keySet()) {
            System.out.println("Rater demandé est "+idrech +"\t"+ "Ratings "+ nr.get(idrech));
            System.out.println("Ratings" + (nr.get(idrech)).size());
            //System.out.println(directors.get(biggestvalues(directors)));
            break;
        }
        System.out.println("---------------------------------------------");
        HashMap<String, Integer> raters = idwithmost(nr);
        for (Map.Entry <String, Integer> entry : raters.entrySet()) {
            if (entry.getValue().equals(biggestvalues(raters))) {
                System.out.println("nb " +biggestvalues(raters));
                System.out.println("id du plus de ratings "+entry.getKey()+ " combien de ratings "+entry.getValue());
            }
        }
        
        System.out.println("---------------------------------------------");
        String movieasked = "1798709";
        ArrayList <String> diffmovies = new ArrayList <String>();
        for (String m : nr.keySet()) {
            ArrayList <Rating> réponse = nr.get(m);
            //ArrayList <String> diffmovies = new ArrayList <String>();
            for (Rating s : réponse) {
                String item = s.getItem();
                //System.out.println(item);
                if (item.contains(movieasked)) {
                    System.out.println("m"+m);
                }
                //ArrayList <String> diffmovies = new ArrayList <String>();
                if (!diffmovies.contains(item)) {
                    diffmovies.add(item);
                }
            }

        }
        System.out.println("nb de films diff "+diffmovies.size());
    }
    
    public HashMap<String, Integer> idwithmost (HashMap <String, ArrayList <Rating>> nr) {
        HashMap<String, Integer> idwithmost = new HashMap<String, Integer> ();
        for (String m : nr.keySet()) {
            int nbpararray = nr.get(m).size();
            idwithmost.put(m,nbpararray);
        }
        return idwithmost;
    }
   
    
}

