/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class GetTheDistance {
      
    static void get(WebDriver wd) 
            throws org.openqa.selenium.NoSuchElementException, NumberFormatException, IOException
    {
        //click to get the distance [km]
        try{
        wd.findElement(By.xpath("(//a[contains(text(),'Odległość (km)')])[2]")).click();
        Thread.sleep(2000);
        String kmtemp = wd.findElement(By.xpath("(//div/div[2]/div[2]/div/table/tbody/tr/td)[2]")).getText();
        int km = Integer.parseInt(kmtemp);
        System.out.println("Odległosc wynosi: " +km);
      
        
        }catch(org.openqa.selenium.NoSuchElementException e)
        {
            System.out.println("Wystąpił bład w pobieraniu odległosci w km");
            File scrFile = ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("./res/screengetTheDistance.png"));
        }catch(InterruptedException e)
        {
            System.out.println("Wystąpił bład");
        }catch(NumberFormatException e)
        {
            System.out.println("Nie ma informacji o odległoci");
        }
    
    }
    
}
