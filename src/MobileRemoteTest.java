import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.*;

public class MobileRemoteTest {
	
	public static void main(String[] args) throws MalformedURLException, UnsupportedEncodingException {

		System.out.println("Run started");
		String browserName = "mobileChrome";
		DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
		String host = args[0];
		String user = URLEncoder.encode(args[1], "UTF-8");
		String password = URLEncoder.encode(args[2], "UTF-8");
		capabilities.setCapability("deviceName", args[3]);

		RemoteWebDriver driver = new RemoteWebDriver(new URL("https://" + user + ':' + password + '@' + host + "/nexperience/wd/hub"), capabilities);
		
		try {
         // write your code here
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
		
		System.out.println("Run ended");
	}
	
	private static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}
	
	private void switchToContext(RemoteWebDriver driver, String context) {
		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
		Map<String,String> params = new HashMap<String,String>();
		params.put("name", context);
		executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
	}
	
	private String getCurrentContextHandle(RemoteWebDriver driver) {		  
		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
		String context =  (String) executeMethod.execute(DriverCommand.GET_CURRENT_CONTEXT_HANDLE, null);
		return context;
	}
	
	private List<String> getContextHandles(RemoteWebDriver driver) {		  
		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
		List<String> contexts =  (List<String>) executeMethod.execute(DriverCommand.GET_CONTEXT_HANDLES, null);
		return contexts;
	}

}
