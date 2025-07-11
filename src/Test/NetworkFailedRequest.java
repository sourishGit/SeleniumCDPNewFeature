package Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v138.fetch.Fetch;
import org.openqa.selenium.devtools.v138.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v138.network.model.ErrorReason;

public class NetworkFailedRequest {	
	
	
	public static void main(String[] args) throws InterruptedException {
	// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:/Users/SOURISH/eclipse-workspace/selenium-java/files/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		
		Optional<List<RequestPattern>> patterns = Optional.of(Arrays.asList(new RequestPattern(Optional.of("*GetBook*"),Optional.empty(),Optional.empty())));
		
		devTools.send(Fetch.enable(patterns, Optional.empty()));
		devTools.addListener(Fetch.requestPaused(), request ->
		{
			devTools.send(Fetch.failRequest(request.getRequestId(), ErrorReason.FAILED));
		});
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector(".btn-primary")).click();
		//Thread.sleep(3000);
		
	}	
		
}
