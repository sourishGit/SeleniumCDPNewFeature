package Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v136.emulation.Emulation;
import org.openqa.selenium.devtools.v136.network.Network;

public class SetGeoLocation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:/Users/SOURISH/eclipse-workspace/selenium-java/files/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		
//		Map<String,Object> geoLoc = new HashMap<String,Object>();
//		geoLoc.put("latitude", 40);
//		geoLoc.put("longitude", 3);
//		geoLoc.put("accuracy", 1);
		
		devTools.send(Emulation.setGeolocationOverride(Optional.of(40), Optional.of(3), Optional.of(1)));
		//devTools.send(Network.enable(Optional.of(1000000), Optional.empty(), Optional.empty()));
		devTools.send(Emulation.setTimezoneOverride("Europe/Madrid"));
		//driver.executeCdpCommand("Emulation.setGeolocationOverride", geoLoc);
		//driver.get("http://google.com");
		driver.get("http://about.netflix.com");
		//driver.findElement(By.name("q")).sendKeys("netflix", Keys.ENTER);
		//driver.findElements(By.cssSelector(".td-hu")).get(0).click();
		//String title = driver.findElement(By.cssSelector(".default-ltr-cache-1ay1asr")).getText();
	}

}
