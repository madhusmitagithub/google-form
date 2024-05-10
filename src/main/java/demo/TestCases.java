package demo;

import java.util.List;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void navigateToGooleForms(){
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
    }

    public static void sendKeys(WebElement e, String inputText) throws InterruptedException
    {
        System.out.println("Enter the text");
        e.clear();
        e.sendKeys(inputText);
        Thread.sleep(2000);
    }

    public String getCurrentEpochTime() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    public static void selectTestingExperience(WebDriver driver) {
        WebElement radioButton = driver.findElement(By.xpath("(//div[@class='AB7Lab Id5V1'])[1]"));
        radioButton.click();
    }

    public void selectSkills(WebDriver driver, String text) {
        WebElement checkBox = driver.findElement(By.xpath("//div[@aria-label='" + text + "']"));
        checkBox.click();
    }

    public void selectAddressedOption(WebDriver driver) throws InterruptedException {
        WebElement dropdownElement = driver.findElement(By.xpath("(//div[@role='listbox'])[1]"));
        dropdownElement.click();
        Thread.sleep(2000);
        
        //WebElement option = driver.findElement(By.xpath("(//div[@data-value='Mrs'])[2]"));
        List<WebElement> options=driver.findElements(By.xpath("//div[@jsname='wQNmvb']"));

        String option="Mrs";
        for(int i=0; i<options.size(); i++) {
            if(options.get(i).getText().contains(option)) {
                options.get(i).click();
                    System.out.println(" option clicked");
                    break;
            }
        }
        
    }
    
    public void enterDate(WebDriver driver){
        WebElement dateField = driver.findElement(By.xpath("//input[@type='date']"));
        LocalDate currentDate = LocalDate.now();
        LocalDate sevenDaysAgo = currentDate.minusDays(7);
        String actualDate = sevenDaysAgo.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        dateField.sendKeys(actualDate);
    }

    public void enterCurrentTime(WebDriver driver) throws InterruptedException{
        WebElement timeFieldHour = driver.findElement(By.xpath("//input[@aria-label='Hour']"));
        WebElement timeFieldMinute = driver.findElement(By.xpath("//input[@aria-label='Minute']"));

        LocalTime currentTime = LocalTime.now();
        int hour = currentTime.getHour();
        int minute = currentTime.getMinute();
        timeFieldHour.sendKeys(String.valueOf(hour));
        timeFieldMinute.sendKeys(String.valueOf(minute));

        // WebElement timeDropDown = driver.findElement(By.xpath("(//div[@role='presentation'])[8]"));
        // Thread.sleep(5000);
        // timeDropDown.click();
        // Thread.sleep(4000);

        List<WebElement> amOrPmElements = driver.findElements(By.xpath("//div[@jsname='V68bde']"));
        Thread.sleep(4000);
        String amOrPm = (hour < 12) ? "AM" : "PM";
        for(WebElement element : amOrPmElements){
            String amOrPmText = element.getText();
            
            if(amOrPmText.equalsIgnoreCase(amOrPm)){
                element.click();
                System.out.println("Clicked on " + amOrPm);
                WebElement am_Pm = driver.findElement(By.xpath("//div[@data-value='AM']"));
                String am_pmText = am_Pm.getText();
                System.out.println("The time is : "+am_pmText);
                if(am_Pm.isDisplayed()){
                    System.out.println("AM/PM is displayed");
                }else{
                    System.out.println("AM/PM is not displayed");
                }
                break;
            }
        }
    }
    public void submit(){
        WebElement submitBtn = driver.findElement(By.xpath("(//div[@role='button'])[1]"));
        submitBtn.click();
    }

    public void successMessage(){
        WebElement successMessage = driver.findElement(By.className("idZHHb"));
        String message = successMessage.getText();
        if(successMessage.isDisplayed()){
            System.out.println("The message is : "+message);
        }
    }

    
    public  void testCase01() throws InterruptedException{
        System.out.println("Start Test case: testCase01");

        navigateToGooleForms();

        WebElement nameField = driver.findElement(By.xpath("(//input[@jsname='YPqjbf'])[1]"));
        Thread.sleep(2000);
        nameField.sendKeys("Madhusmita");
        
        WebElement practiceField = driver.findElement(By.xpath("//textarea[@class='KHxj8b tL9Q4c']"));
        Thread.sleep(2000);
        practiceField.sendKeys("I want to be the best QA Engineer! " + getCurrentEpochTime());
        Thread.sleep(3000);

        selectTestingExperience(driver);
        //Thread.sleep(3000);

        selectSkills(driver, "Java");
        selectSkills(driver, "Selenium");
        selectSkills(driver, "TestNG");
        Thread.sleep(3000);

        selectAddressedOption(driver);
        Thread.sleep(3000);
        
        enterDate(driver);
        //Thread.sleep(2000);
        enterCurrentTime(driver);
        //Thread.sleep(3000);
        submit();
        successMessage();

        System.out.println("end Test case: testCase01");
    }


}
