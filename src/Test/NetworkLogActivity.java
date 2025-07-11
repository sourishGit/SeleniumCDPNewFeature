package Test;

import java.time.Duration;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v138.network.Network;
import org.openqa.selenium.devtools.v138.network.model.Request;
import org.openqa.selenium.devtools.v138.network.model.Response;

public class NetworkLogActivity {	
	
	
	public static void main(String[] args) throws InterruptedException {
	// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:/Users/SOURISH/eclipse-workspace/selenium-java/files/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
		
		devTools.addListener(Network.requestWillBeSent(), request ->
		
		{
			Request req = request.getRequest();
			System.out.println(req.getUrl());
			
		});
		
		devTools.addListener(Network.responseReceived(), response ->
		{
			Response res = response.getResponse();
			System.out.println(res.getUrl());
			//System.out.println(res.getStatus());
			if(res.getStatus().toString().startsWith("4"))
			{
				System.out.println("Test is failing with status code : "+res.getStatus());
			}
		});
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector(".btn-primary")).click();
		Thread.sleep(3000);
	}

}
