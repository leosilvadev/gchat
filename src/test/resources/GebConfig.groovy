
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver

waiting {
	timeout = 2
}

environments {
	
	chrome {
		driver = { new ChromeDriver() }
	}
	
	phantomJs {
		driver = { new PhantomJSDriver() }
	}
	
}

baseUrl = "http://localhost:9000"