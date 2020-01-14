import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestCitizenship {

    WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod

    @Parameters({"username", "password"})
    public void setup(String username, String password) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\dilek\\IdeaProjects\\chrome\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://test-basqar.mersys.io");
        driver.manage().window().maximize();
        // login info
        driver.findElement(By.cssSelector("[formcontrolname=\"username\"]")).sendKeys(username);
        driver.findElement(By.cssSelector("[formcontrolname=\"password\"]")).sendKeys(password);
        driver.findElement(By.cssSelector("button[aria-label=\"LOGIN\"]")).click();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@class='cc-btn cc-dismiss']")).click();

    }

    @Test
    @Parameters({"citizenName"})
    public void login(String citizenName) throws InterruptedException {
        // Clicking on Setup
        WebElement setUpButton=driver.findElement(By.xpath("(//span[contains(text(),'Setup')])[1]"));
        setUpButton.click();
        wait.until(ExpectedConditions.visibilityOf(setUpButton));

        // Clicking on Parameter
        WebElement parameterButton=driver.findElement(By.xpath("(//span[contains(text(),'Parameters')])[1]"));
        wait.until(ExpectedConditions.visibilityOf(parameterButton));
        parameterButton.click();

//        Clicking on Citizenship
        WebElement citizenship=driver.findElement(By.xpath("//span[@class='nav-link-title ng-star-inserted'][contains(text(),'Citizenships')]"));
        citizenship.click();

        //      Clicking on plus icon

        WebElement plusIcon=driver.findElement(By.cssSelector("ms-add-button[tooltip='CITIZENSHIP.TITLE.ADD']"));
        plusIcon.click();


//     Creating the Name
        WebElement name=driver.findElement(By.cssSelector("ms-text-field[formcontrolname='name']>input"));
        name.sendKeys(citizenName);


        //       Click on Save Button
        WebElement saveButton = driver.findElement(By.cssSelector("ms-save-button"));
        saveButton.click();

        wait.until(ExpectedConditions.invisibilityOf(saveButton));
    }

    @Test
    public void craetingPosition() throws InterruptedException {
//      Click on Human Resource

        WebElement humanResource = driver.findElement(By.xpath("//span[text()='Human Resources']"));

        humanResource.click();


        WebElement setUpHuman = driver.findElement(By.xpath("(//span[contains(text(),'Setup')])[4]"));

        setUpHuman.click();

    }

    @AfterMethod

    public void closing(){
        driver.close();
    }
    }



