import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
public class freeway1968 {
	public static void main(String args[]) throws Exception{
		String[][] arr1 = {{"快速公路 64 號", "觀音山", "中和一"},
                {"國道 4 號", "清水端", "豐原端"},
                {"國道 1 號", "基隆端", "高雄端"}};
		 for (int i = 0; i < arr1.length; i++) {
			 get1968(arr1[i][0].toString(),arr1[i][1].toString(),arr1[i][2].toString());
	     }
	}
	
	public static void get1968(String freeway,String from_location,String end_location) throws SQLException {
//		DesiredCapabilities dcaps = new DesiredCapabilities();
//		dcaps.setCapability("phantomjs.page.settings.resourceTimeout", 5000);
//		dcaps.setCapability("acceptSslCerts", true);
//		dcaps.setCapability("takesScreenshot", false);
//		dcaps.setCapability("cssSelectorsEnabled", true);
//		dcaps.setJavascriptEnabled(true);
//		dcaps.setCapability("phantomjs.binary.path", "/var/www/html/phantomjs");
//		PhantomJSDriver driver = new PhantomJSDriver(dcaps);
		Connection conn=getDBConnect();

		//local test
		System.setProperty("webdriver.chrome.driver", "c:\\driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
      try {
          driver.get("https://1968.freeway.gov.tw/n_speed");

          WebElement fieldSortBy = driver.findElement(By.xpath("//*[@id=\"freeway\"]"));
          Select selectSortBy = new Select(fieldSortBy);
          fieldSortBy.click();
          //selectSortBy.selectByIndex(8);
          selectSortBy.selectByVisibleText(freeway);
          
          fieldSortBy = driver.findElement(By.xpath("//*[@id=\"from_location\"]"));
          selectSortBy = new Select(fieldSortBy);
          fieldSortBy.click();
          selectSortBy.selectByVisibleText(from_location);
          
          fieldSortBy = driver.findElement(By.xpath("//*[@id=\"end_location\"]"));
          selectSortBy = new Select(fieldSortBy);
          fieldSortBy.click();
          selectSortBy.selectByVisibleText(end_location);
          WebElement addButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/div[2]/div[4]/button"));
          addButton.click();
          Thread.sleep(5000);          
          
          WebElement mytable = driver.findElement(By.xpath("//*[@id=\"section_table\"]/table/tbody"));
          List < WebElement > rows_table = mytable.findElements(By.tagName("tr"));
          int rows_count = rows_table.size();
          for (int row = 0; row < rows_count; row++) {
        	  //To locate columns(cells) of that specific row.
          	  List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));

          	  //To calculate no of columns(cells) In that specific row.
          	  int columns_count = Columns_row.size();
//          	  System.out.println("Number of cells In Row " + row + " are " + columns_count);
          	  String BeginMileage=null,EndMileage=null,BeginLocation=null,EndLocation=null;
    		  String speed_left=null,speed_right=null;

          	  //Loop will execute till the last cell of that specific row.
          	  for (int column = 0; column < columns_count; column++) {          		  
          		  //To retrieve text from the cells.
          		  String celltext = Columns_row.get(column).getText();
          		  
//          		  System.out.println("Cell Value Of row number " + row + " and column number " + column + " Is " + celltext);
          		  if(column==0) {
          			  speed_left=celltext.trim();
          		  }
          		  if(column==1) {
          			BeginMileage=getBeginMileage(celltext);
          			BeginLocation=getBeginLocation(celltext);
          			EndLocation=getEndLocation(celltext);
          			EndMileage=getEndMileage(celltext);
          		  }  
          		  if(column==2) {
          			speed_right=celltext.trim();          			  
          		  }          		  
          	   }
          	  String sql = "INSERT INTO most_1968 (freeway_name,start_section,start_km,speed_left,speed_right,end_section,end_km) VALUES (?,?,?,?,?,?,?) ";
    		  PreparedStatement ptmt = conn.prepareStatement(sql); 
    		  ptmt.setString(1, freeway);
    		  ptmt.setString(2, BeginLocation);
    		  ptmt.setString(3, BeginMileage);
    		  ptmt.setString(4, speed_left);
    		  ptmt.setString(5, speed_right);
    		  ptmt.setString(6, EndLocation);
    		  ptmt.setString(7, EndMileage);
    		  ptmt.execute(); 
          }
      }catch(Exception e) {
      	System.out.println(e.toString());
      }finally {
    	  if(conn!=null) {
    		  conn.close();
    	  }
      	driver.quit();
      }
	}
	
	public static String getBeginLocation(String celltext) {
		int beginFromIndex=celltext.indexOf("(");
		String beginLocation=null;
		if(beginFromIndex!=-1) {
			beginLocation=celltext.substring(0, beginFromIndex);
		}
		return beginLocation.trim();
	}
	
	public static String getEndLocation(String celltext) {
		int beginFromIndex=celltext.indexOf("-");
		int beginEndIndex=celltext.lastIndexOf("(");
		String endLocation=null;
		if(beginFromIndex!=-1) {
			endLocation=celltext.substring(beginFromIndex+1, beginEndIndex);
		}
		return endLocation.trim();
	}
	
	public static String getBeginMileage(String celltext) {
		int beginFromIndex=celltext.indexOf("(");
		int beginEndIndex=celltext.indexOf(")");
		String beginMileage=null;
		if(beginFromIndex!=-1) {
			beginMileage=celltext.substring(beginFromIndex+1, beginEndIndex);
		}
		return beginMileage.trim();
	}
	
	public static String getEndMileage(String celltext) {
		int beginFromIndex=celltext.lastIndexOf("(");
		int beginEndIndex=celltext.lastIndexOf(")");
		String endMileage=null;
		if(beginFromIndex!=-1) {
			endMileage=celltext.substring(beginFromIndex+1, beginEndIndex);
		}
		return endMileage.trim();
	}
	
	public static Connection getDBConnect(){
	    Connection conn=null;
	    try {	   
	        Class.forName("com.mysql.jdbc.Driver");
	        conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/most?useUnicode=true&characterEncoding=UTF-8","root","!CID1234");	            
	        }catch(Exception ex){
	            System.out.println("Error: "+ex);
	        }
	    return conn;
	}

}
