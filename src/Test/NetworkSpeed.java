package Test;

import java.time.Duration;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v138.network.Network;
import org.openqa.selenium.devtools.v138.network.model.ConnectionType;

public class NetworkSpeed {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:/Users/SOURISH/eclipse-workspace/selenium-java/files/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		DevTools devTools = driver.getDevTools();
		
		devTools.createSession();
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
		devTools.send(Network.emulateNetworkConditions(true, 3000, 20000, 100000, Optional.of(ConnectionType.ETHERNET), Optional.empty(), Optional.empty(), Optional.empty()));
		devTools.addListener(Network.loadingFailed(), loadingFailed ->
				{
					System.out.println(loadingFailed.getErrorText());
					System.out.println(loadingFailed.getTimestamp());
				});
		
		long startTime = System.currentTimeMillis();
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector(".btn-primary")).click();
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
		//15318  //1456

	}

}
