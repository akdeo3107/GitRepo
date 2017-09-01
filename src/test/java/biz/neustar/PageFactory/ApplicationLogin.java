package biz.neustar.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.*;

public class ApplicationLogin {
	@FindBy(name="NF_CustomerId")
	WebElement domain;
	
	@FindBy(name="NF_UserName")
	WebElement username;
	
	@FindBy(name="NF_Password")
	WebElement password;
	
   @FindBy(xpath="//*[@src='/gateway/images/authentication/LogonButton.gif']")
   WebElement login;
   
   public void strusername(String strusename){
	   username.sendKeys("");
   }
   
   public void strpassword(String strpassword){
	   password.sendKeys("");
   }
   
   public void clicklogin(){
	   login.click();
   }
	public void login(String strusername,String strpassword){
		
		this.strusername(strusername);
		this.strpassword(strusername);
		this.clicklogin();
		
		
		
	}
}
