package mediustest1;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author Magda Nowak-Trzos
 */
public class ChooseTheDate {
        int monthComparator(String currentMonth, String wantedMonth) // compare two months 
        {
            String[] allMonths = new String[] {"Styczeń", "Luty", "Marzec", 
                                                "Kwiecień", "Maj","Czerwiec","Lipiec",
                                                "Sierpień","Wrzesień", 
                                                "Październik","Listopad","Grudzień" };
            int i = 0;
            while(!(currentMonth.equals(allMonths[i])))   i++;
            
            int k = 0;
            while(!(wantedMonth.equals(allMonths[k])))    k++;
            
            
            if(k>i) return -1; // if the wantedMonth is the later month method returns -1
            if(k<i) return 1; // if the wantedMonth is the earlier month method returns 1
            else return 0; // otherwise return 0
        
        }
              
        void choose(DateAndTime data, WebDriver wd) throws IOException
        {
            
            try{
            // current month
            String month=wd.findElement(By.cssSelector("span.ui-datepicker-month")).getText();
            // current year
            String year =wd.findElement(By.cssSelector("span.ui-datepicker-year")).getText();
            String next = "//div/div/div/a[2]";
            String prev = "//div[7]/div/div/div/div/a";
            
            
        // if current year is < then year of searched connection.    
        if(year.compareTo(data.getYear())<0){ 
            while(!(year.equals(data.getYear())))
            {
                 wd.findElement(By.xpath(next)).click(); // click next
                 year =wd.findElement(By.cssSelector("span.ui-datepicker-year")).getText();
            }
        }
        // if current year is > then year of searched connection.
        else if(year.compareTo(data.getYear())>0) 
        {
            while(!(year.equals(data.getYear())))
            {
                 wd.findElement(By.xpath(prev)).click(); // click next
                 year =wd.findElement(By.cssSelector("span.ui-datepicker-year")).getText();
            }
            
        }
        
        else if(year.equals(data.getYear()))
        {
            // if current month is < then month of searched connection
            if(monthComparator(month,data.getMonth())<0) 
            {
                while(!(month.equals(data.getMonth())))
                {
                    wd.findElement(By.xpath(next)).click(); // click next 
                    month=wd.findElement(By.cssSelector("span.ui-datepicker-month")).getText(); 
                }
            }
            // if current month is < then month of searched connection
            else if(monthComparator(month,data.getMonth())>0) 
            {
               while(!(month.equals(data.getMonth())))
                {
                    wd.findElement(By.xpath(prev)).click(); // click next
                    month=wd.findElement(By.cssSelector("span.ui-datepicker-month")).getText(); 
                } 
            }
        }
        wd.findElement(By.linkText(data.getDay())).click();
        //update
        wd.findElement(By.xpath("(//button[@type='button'])[15]")).click();
        // click to choose time
        wd.findElement(By.cssSelector("span.upper.pointer")).click();
        // choose time
        wd.findElement(By.cssSelector("input.day-hour.form-control")).click();
        // put time
        wd.findElement(By.cssSelector("input.day-hour.form-control")).sendKeys(data.getTime());
        }catch(org.openqa.selenium.NoSuchElementException e)
        {
            System.out.println("Wstąpił bład przy wyborze daty");
            File scrFile = ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("./res/screen.png"));    
        }
            
        }
    
}
