import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class windy {
	public static void main(String args[]) throws Exception{
		System.setProperty("webdriver.chrome.driver", "c:\\driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
        try {
        	driver.get("https://www.windy.com");
   	
        	WebElement search = driver.findElement(By.xpath("//*[@id=\"q\"]"));
        	Actions action = new Actions(driver);
        	action.click(search).sendKeys("新竹科學園區").perform();
        	action.sendKeys(Keys.NULL);
        	
        	Thread.sleep(1000); 
        	action.sendKeys(Keys.ENTER).perform();

        	Thread.sleep(2000);
        	WebElement mytable = driver.findElement(By.xpath("//*[@id=\"detail-data-table\"]/tbody"));
            List < WebElement > rows_table = mytable.findElements(By.tagName("tr"));
            int rows_count = rows_table.size();
            for (int row = 0; row < rows_count; row++) {

            	   //To locate columns(cells) of that specific row.
            	   List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));

            	   //To calculate no of columns(cells) In that specific row.
            	   int columns_count = Columns_row.size();
            	   System.out.println("Number of cells In Row " + row + " are " + columns_count);

            	   //Loop will execute till the last cell of that specific row.
            	   for (int column = 0; column < columns_count; column++) {
            	    //To retrieve text from the cells.
            	    String celltext = Columns_row.get(column).getText();
            	    System.out.println("Cell Value Of row number " + row + " and column number " + column + " Is " + celltext);
            	   }
            	  }
        }catch(Exception e) {
        	System.out.println(e.toString());
        }finally {
        	driver.quit();
        }
	}

}
