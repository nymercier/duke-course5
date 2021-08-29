
public class MinutesFilter implements Filter {
    private int min;
    private int max;
    
    public MinutesFilter (int minminutes, int maxminutes) {
        min = minminutes;
        max = maxminutes;
    }
    
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getMinutes(id) >= min && MovieDatabase.getMinutes(id) <= max ;
    }

}
