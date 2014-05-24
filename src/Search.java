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
    class DateAndTime // class to store the date of connection
    {
        private String day;
        private String year;
        private String month;
        private String time;
        
        DateAndTime()
        {
            day="1";
            year="2014";
            month = "Styczeń";
            time ="0000";
        }
        DateAndTime(String day,String month,  String year,String time)
        {
            this.day = day;
            this.month=month;
            this.time=time;
            this.year=year;
        }
        
        public String getDay(){return day;}
        public String getYear(){return year;}
        public String getMonth(){return month;}
        public String getTime(){return time;}
    }
    
    
    public class Search {
        ChooseTheDate chooseDate = new ChooseTheDate();
        void searchConnection(WebDriver wd, String from, String to, DateAndTime date ) 
                throws org.openqa.selenium.NoSuchElementException, IOException
        {
        try{
        // clear the arrival station
        wd.findElement(By.xpath("//input[@value='']")).clear();
        // choose arrival city
        wd.findElement(By.xpath("//input[@value='']")).sendKeys(from);
        // clear the destinatinon city
        wd.findElement(By.xpath("//div[2]/div[3]/div/div/form/div[6]/div[2]/div/input[2]")).clear();
        // choose the destination city
        wd.findElement(By.xpath("//div[2]/div[3]/div/div/form/div[6]/div[2]/div/input[2]")).sendKeys(to);
        // click to choose date 
        wd.findElement(By.cssSelector("span.upper.pointer")).click();
        
        chooseDate.choose(date,wd);
       // update 
        wd.findElement(By.xpath("(//button[@type='button'])[15]")).click();
       // search the connection
        wd.findElement(By.id("singlebutton")).click();
        }catch(org.openqa.selenium.NoSuchElementException e )
        {
            System.out.println("Wystąpił błąd przy wyszukiwaniu połączenia");
            File scrFile = ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("./res/screenSearchConnection.png"));
            
        }
        catch(IOException e)
        {
            System.out.println("Zła scieżka dostępu do zapisu zrzutów ekranu");
        }
       
 } 
}
