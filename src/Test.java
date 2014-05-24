package mediustest1;
import java.io.IOException;
import java.util.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
/**
 *
 * @author Magda Nowak-Trzos
 */
public class Test {
        
      public static void main(String[] args) 
            throws InterruptedException, NoSuchElementException, IOException {
        // TODO code application logic here
        ShortOrLongConnection shortorlong = new ShortOrLongConnection();
        Search search = new Search();
        GetTheDistance getTheDistance = new GetTheDistance();

        WebDriver wd = new FirefoxDriver();
        wd.get("http://beta.rozklad-pkp.pl/");
       //SEARCHING WĄCHOCK-WARZĄCHEWKA CONNECTION //
        String from;
        String to;
        from = "Wąchock";
        to= "Warząchewka";
        DateAndTime data = new DateAndTime("12","Maj","2014","1300");
        
        System.out.println("Wyszukuje najkrótszego połączenia "+ from +"-"+ to+" Data: "+ data.getDay() +  "-" 
                           + data.getMonth() + "-" + data.getYear()+" o godzinie "+ data.getTime());
        
        Thread.sleep(2000);    
        search.searchConnection(wd, from, to,data);
        Thread.sleep(4000);    
        shortorlong.find(wd, "Short");
        Thread.sleep(2000);    
        getTheDistance.get(wd);
        
        from = "Zakopane";
        to = "Kraków Główny";
        DateAndTime data1 = new DateAndTime("22","Lipiec","2014","1845");
         System.out.println("Wyszukuje najdłuższego połączenia "+ from +"-"+ to+" Data: "+ data1.getDay() +  "-"
                            + data1.getMonth() + "-" + data1.getYear()+" o godzinie "+ data1.getTime());
        
        //SEARCHING ZAKOPANE - KRAKÓW GŁOWNY//
        wd.get("http://beta.rozklad-pkp.pl/");
        Thread.sleep(2000);    
        search.searchConnection(wd, from, to, data1);
        Thread.sleep(4000);    
        shortorlong.find(wd, "Long");
        Thread.sleep(2000);    
        getTheDistance.get(wd);
        
    }
    
}
