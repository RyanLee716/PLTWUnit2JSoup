import java.io.IOException;

import org.jsoup.*;
import org.jsoup.nodes.*;

class soup {
    public static void main(String args[]) {
        Document doc;
        String url = "";
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            System.out.println("Website could not be accessed: " + e);
            return;
        }

    }
}