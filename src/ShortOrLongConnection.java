package mediustest1;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author Magda Nowak-Trzos
 */
class TimeCompare implements Comparable<TimeCompare>
{
          private int number; // position number of each connection
          private String time;
        
          TimeCompare()
          {
              number = 0; 
              time = "";
          }
          TimeCompare(int number, String time)
          {
              this.number = number;
              this.time = time;
          }
          public int getNumber(){ return number; }
          public String getTime() { return time; }
               
          @Override
            // if the time is the same, the earlier connection is chosen.
           public int compareTo(TimeCompare a) 
           {
              int TimeCompare = this.getTime().compareTo(a.getTime());
              Date time2;
              Date thisTime;
              SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
           try{
              time2 = parser.parse(a.getTime());
              thisTime = parser.parse(this.getTime());
              if(time2.before(thisTime))
                  TimeCompare = 1;
              else if(time2.after(thisTime))
                  TimeCompare=-1;
              else
                  TimeCompare = 0 ;
            }catch(java.text.ParseException ex)
            {
                  System.out.println("Wystapił ParseException");
            }
            if(TimeCompare == 0) 
            {
                   if(this.getNumber()<a.getNumber())
                        return this.getNumber();
                   else 
                        return a.getNumber();
            }
            else 
                    return TimeCompare;
                            
      }
}

public class ShortOrLongConnection {
    
   static void find (WebDriver wd, String LongOrShort)
            throws org.openqa.selenium.NoSuchElementException, IOException
    {
        //class storing the number and time of connection
         
        //create an array to store connection times
        List<TimeCompare> TimeArray = new ArrayList();
        try{
        for ( int i = 1 ;i < 100; i++)
        {
            // get connection time
            String a = wd.findElement(By.xpath("//tr[@id='focus_guiVCtrl_connection_detailsOut_select_C0-"+
                    (i-1)+"']/td[5]")).getText();
            TimeCompare time = new TimeCompare(i,a);
            TimeArray.add(time);
        }
        }catch(org.openqa.selenium.NoSuchElementException e)
        {
            System.out.println("Koniec przeszukiwania połączen, Już wybrano najlepsze");
        }
        // sort the array
        Collections.sort(TimeArray);
        
        // click the shortest connection 
        try{
            if("Short".equals(LongOrShort))
            {
                System.out.println("Czas podróży to:" + TimeArray.get(0).getTime());
                wd.findElement(By.xpath(
                        "//tr[@id='focus_guiVCtrl_connection_detailsOut_select_C0-"+
                        TimeArray.get(0).getNumber() +"']/td[5]")).click();
            }
            else if("Long".equals(LongOrShort))
            {
                int i = TimeArray.size()-1;
                System.out.println("Czas podróży to:" + TimeArray.get(i).getTime());
                wd.findElement(By.xpath(
                        "//tr[@id='focus_guiVCtrl_connection_detailsOut_select_C0-"+
                        TimeArray.get(i).getNumber()+"']/td[5]")).click();
            }
            else
            {
                System.out.println("Wpisano zły parametr, należy wpisac: Short lub Long");
            }
        }catch(org.openqa.selenium.NoSuchElementException e)
        {
            System.out.println("Wystapil bład w wyszukaniu polaczenia z odpowiednim czasem");
            File scrFile = ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("./res/screenFindTheShortestLongestConnection.png"));
        }
    }
    
        
        // different implementation (unused method)
       static void find2 (WebDriver wd, String LongOrShort)
            throws org.openqa.selenium.NoSuchElementException, IOException
    {
        try{
            String sort = "//div[@id='content']/div/div/div[2]/form[2]/div/table/thead/tr/th[5]";
            if("Short".equals(LongOrShort)) //sorting /shortest
            {        
                            
                wd.findElement(By.xpath(sort)).click();
            }
            else if("Long".equals(LongOrShort)) // sorting /longest
            {
                wd.findElement(By.xpath(sort)).click(); 
                wd.findElement(By.xpath(sort)).click();
            }
            else
            {
                System.out.println("Wpisano zły parametr, należy wpisac: Short lub Long");
            }
            
            String time = wd.findElement(By.xpath("//td[5]")).getText(); //wybiera pierwszy z gory
            System.out.println("Czas połączenia wynosi: " +time);
            
            wd.findElement(By.xpath("//td")).click(); // choose the proper one
            
            }catch(Exception e)
        {
            System.out.println("Wystapil bład w wyszukaniu polaczenia z odpowiednim czasem ");
            File scrFile = ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("./res/screenFindTheShortestLongestConnection1.png"));
            
        }
    }
    
    
    
}
