import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.opencsv.*;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

class soup {
    public static void main(String args[]) {
        File input = new File("webpage.html");
        Document doc;
        try {
            doc = Jsoup.parse(input, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Elements parent_reviews = doc.getElementsByClass("user-generated-content");

        
        for (Element review : parent_reviews) {
            
            int ratings = review.getElementsByClass("icon-star-solid active").size();
            Element reviewTextDiv = review.getElementsByClass("reveal__content").first();
            String reviewText = "No Review";
            if (reviewTextDiv != null) {
                reviewText = reviewTextDiv.text();
            }
        }
        File file = new File("AllReviews.csv"); 

        try {
            FileWriter outputfile = new FileWriter(file); 
            CSVWriter writer = new CSVWriter(outputfile); 
            String[] header = {"Name", "Rating", "Review"}; 
            writer.writeNext(header); 
    
            for (String[] dataRow : dataArr) {
                writer.writeNext(dataRow); 
            }
            // closing writer connection 
            writer.close(); 
        } 
        catch (IOException e) { 
            e.printStackTrace(); 
        } 

        // rating: check inside "rating__score" classes and look for "icon-star-active" class.
        // "reveal__content" classes contain review.
    }
}