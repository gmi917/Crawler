import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeTest {
	public static void main(String args[]) throws Exception{
		//phantomjs無UI介面瀏覽器
		//設置必要參數
        DesiredCapabilities dcaps = new DesiredCapabilities();
        dcaps.setCapability("phantomjs.page.settings.resourceTimeout", 5000);
        dcaps.setCapability("acceptSslCerts", true);
        dcaps.setCapability("takesScreenshot", false);
        dcaps.setCapability("cssSelectorsEnabled", true);
        dcaps.setJavascriptEnabled(true);
        dcaps.setCapability("phantomjs.binary.path", "c:\\driver\\phantomjs.exe");

        PhantomJSDriver driver = new PhantomJSDriver(dcaps);

        //chrome有UI介面的瀏覽器
//		System.setProperty("webdriver.chrome.driver", "c:\\driver\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver(); 
        try {
        	driver.get("https://www.google.com");
//    		Thread.sleep(5000);  // Let the user actually see something!
    		  WebElement searchBox = driver.findElement(By.name("q"));
    		  searchBox.sendKeys("新聞");
    		  searchBox.submit();
    		  String url = driver.getCurrentUrl();
    		  System.out.println("----------"+url+"------------");
    		  Document doc = Jsoup.connect(url).get();
    			String title = doc.title();
    			System.out.println("title: "+ title);

    			System.out.println("\nget all link and text.............\n");
    			Elements links = doc.select("[class=g]");
    			for(Element link : links){
    			    System.out.println(link.select("a").attr("href"));
    				System.out.println("text: "+link.select("h3[class=LC20lb]").text());
    				System.out.println(link.select("span[class=st]").text());
    				System.out.println();
    			}
    			
//    			Elements img = doc.getElementsByTag("img");
//    			for(Element src : img){
//    				System.out.println("img src: "+src.attr("abs:src"));
//    			}
    			Elements pages = doc.select("[class=fl]");
    			for(Element page : pages){
    				if(page.attr("href").indexOf("ei") != -1) {
    					getArtitcleByPage(page.attr("href"));
    				}
    			}
//    		  Thread.sleep(5000);  // Let the user actually see something!
        }catch(Exception e) {
        	System.out.println(e.toString());
        }finally {
        	driver.quit();
        }
		
	}
	
	public static void getArtitcleByPage(String url) throws IOException {
		System.out.println("url="+url);
		System.out.println();
		Connection conn = Jsoup.connect("https://www.google.com"+url)
				.userAgent("Mozilla/5.0 (Windows NT 6.1; rv:47.0) Gecko/20100101 Firefox/47.")
				.timeout(5000)
				.method(Connection.Method.GET);
				Document doc = conn.get();
				Elements links = doc.select("[class=g]");
				for(Element link : links){
					System.out.println(link.select("a").attr("href"));
    				System.out.println("text: "+link.select("h3[class=LC20lb]").text());
    				System.out.println(link.select("span[class=st]").text());
    				System.out.println();
				}
	}
	
	public static String readHtml(String myurl) { 
		StringBuffer sb = new StringBuffer(""); 
		URL url; 
		try { 
			url = new URL(myurl); 
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8")); 
			String s = ""; 
			while ((s = br.readLine()) != null) { 
				sb.append(s + "/r/n"); 
			} 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		return sb.toString();
	}
	
}
