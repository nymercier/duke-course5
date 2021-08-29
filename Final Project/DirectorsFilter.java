import java.util.*;
import org.apache.commons.csv.*;
import edu.duke.FileResource;

public class DirectorsFilter  implements Filter {
    private String mydirector;
    private String [] output;
    
    public DirectorsFilter (String directors) {
        mydirector = directors;
        output = (mydirector.split(","));
    }
    
    @Override
    public boolean satisfies(String id) {
        //System.out.println (Arrays.toString(output));
        for (int i=0; i< output.length; i++) {
            if (output[i].contains(MovieDatabase.getDirector(id))) {
                return true;
            }
        }
        return false;
    }
}
