import java.io.IOException;

import org.jsoup.*;
import org.jsoup.nodes.*;

class soup {
    public static void main(String args[]) {
        Document doc;
        String url = "https://www.commonsensemedia.org/book-reviews/harry-potter-and-the-sorcerers-stone-harry-potter-book-1";
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            System.out.println("Website could not be accessed: " + e);
            return;
        }

        Elements parent_reviews = doc.getElementsByClass("user-reviews-reviews-list-full-adult");
        System.out.println(parent_reviews);
    }
}