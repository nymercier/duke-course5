
public class YearAfterFilter implements Filter {
    private int myYear;
    
    public YearAfterFilter(int year) {
        myYear = year;
    }
    
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getYear(id) >= myYear;
    }
    //If the year is 2000, then all movies created in the year 2000 and the years after (2001, 2002, 2003, etc) 
}
