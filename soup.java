import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.*;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

class soup {
    private static String[] positiveWordList = new String[] {"good", "love", "great"};

    private static boolean inReview(String[] keywords, String review) {
        for (String word : keywords) {
            if (review.contains(word)) {
                return true;
            }
        }
        return false;
    }
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

        ArrayList<String[]> dataArr = new ArrayList<String[]>();

        for (Element review : parent_reviews) {
            String reviewerName = review.getElementsByClass("user-summary__name").text();
            String ratings = String.valueOf(review.getElementsByClass("icon-star-solid active").size());
            Element reviewTextDiv = review.getElementsByClass("reveal__content").first();
            String reviewText = "No Review";
            if (reviewTextDiv != null) {
                reviewText = reviewTextDiv.text();
            }

            dataArr.add(new String[] {reviewerName, ratings, reviewText});
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

        dataArr.removeIf(review -> (Integer.parseInt(review[1]) < 4));
        dataArr.removeIf(review -> ("No Review".equals(review[2])));
        dataArr.removeIf(review -> !(inReview(positiveWordList, review[2])));

        try {
            FileWriter outputfile = new FileWriter("PotentialCustomers.csv"); 
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
    }
}