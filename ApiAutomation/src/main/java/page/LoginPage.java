package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class LoginPage {
public static WebDriver driver;
@FindBy(name="email")
private WebElement email;
@FindBy(name="password")
private WebElement password;
@FindBy(xpath="//button[text()='Sign In']")
private WebElement signin;
public LoginPage(WebDriver driver)
{
	PageFactory.initElements(driver, this);
}
public void email(String fn)
{
	email.clear();
	email.sendKeys(fn);
}
public void password(String ln)
{
	password.clear();
	password.sendKeys(ln);
}
public void signin()
{
	signin.click(); 
}
}
