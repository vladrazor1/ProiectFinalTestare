import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class MyThread1 extends Thread {

    WebDriver driverChrome;
    Actions actions;
    JavascriptExecutor js;

    @Override
    public void run() {
        before1();
        test1();
        afterClass1();
    }


    @BeforeTest
    public void before1(){
        WebDriverManager.chromedriver().setup();
        driverChrome = new ChromeDriver();
        actions = new Actions(driverChrome);
    }

    @Test
    public void test1() {
        WebDriverManager.chromedriver().setup();
        driverChrome = new ChromeDriver();
        actions = new Actions(driverChrome);


        driverChrome.get(Variabile.site5);
        js = (JavascriptExecutor) driverChrome;

        WebElement element = driverChrome.findElement(By.id("hot-spot"));
        actions.contextClick(element).build().perform();
        driverChrome.switchTo().alert().accept();


        js.executeScript("window.open('https://www.mercedes-benz.ro/?group=all&subgroup=all.BODYTYPE.hatchback&view=BODYTYPE')");
        js.executeScript("window.open('https://www.youtube.com/')");
        js.executeScript("window.open('https://altex.ro/')");


        ArrayList<String> handles = new ArrayList<>();
        handles.addAll(driverChrome.getWindowHandles());

        try {
            Thread.sleep(3000);
            driverChrome.switchTo().window(handles.get(1));


            Thread.sleep(3000);
            driverChrome.switchTo().window(handles.get(2));


            Thread.sleep(3000);
            driverChrome.switchTo().window(handles.get(3));


            Thread.sleep(3000);
            driverChrome.switchTo().window(handles.get(0));

            assertEquals(true, element.isDisplayed());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("A rulat threadul:" +Thread.currentThread().getId());
    }

    @AfterTest
    public void afterClass1() {
        driverChrome.quit();
    }

}
