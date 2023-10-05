package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ViewBuisnessPages {

	public ViewBuisnessPages(WebDriver driver) {
	}
	
	public By productSelector = By.xpath("//*[@id='productSelector_categorySwitch']");
	public By categorySwitch = By.xpath("//*[@id='productSelector_categorySwitch_link']");
	public By termsConditions = By.xpath("//*[@data-app-group='Business Credit Cards'][3]//*[text()='+Terms & Conditions']");
	public By termsConditionsTable = By.xpath("//*[@id='divTermsContent']");
	public By valueAPRTable = By.xpath("//*[@id='divTermsContent']/table[1]/tbody/tr[4]/td[2]");

}
