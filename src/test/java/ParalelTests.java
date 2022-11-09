import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
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
import static org.junit.Assert.assertTrue;


public class ParalelTests extends Thread{

        WebDriver driverChrome;
        Actions actions;
        JavascriptExecutor js;

    @Test
    public void test1() {
        System.out.println("Ruleaza thredul:" +Thread.currentThread().getId());

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

    }

    @AfterTest
    public void afterClass1() {
        driverChrome.quit();
    }

    @Test
    public void test2() {

        System.out.println("Ruleaza thredul:" + Thread.currentThread().getId());

        WebDriverManager.chromedriver().setup();
        driverChrome = new ChromeDriver();
        actions = new Actions(driverChrome);

        driverChrome.get(Variabile.site5);
        js = (JavascriptExecutor) driverChrome;

        WebElement element = driverChrome.findElement(By.id("hot-spot"));
        actions.contextClick(element).build().perform();
        driverChrome.switchTo().alert().accept();

        js.executeScript("window.open('https://altex.ro/')");
        js.executeScript("window.open('https://www.youtube.com/')");
        js.executeScript("window.open('https://www.mercedes-benz.ro/?group=all&subgroup=all.BODYTYPE.hatchback&view=BODYTYPE')");

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
            //Accept All Cookies
            js.executeScript("document.querySelector(\"body > cmm-cookie-banner\").shadowRoot.querySelector(\"button[data-test='handle-accept-all-button']\").click()");

            //Open carHeaderDropdowne
            js.executeScript("document.querySelector(\"owc-header\").shadowRoot.querySelector(\"button[aria-label='menu']\").click()");

            //Select CompactModel
            js.executeScript("document.querySelector(\"vmos-flyout\").shadowRoot.querySelector(\"li:nth-child(2) > ul > li:nth-child(1) > div\").click()");

            //Select Clasa A Limuzină compacta
            js.executeScript("document.querySelector(\"vmos-flyout\").shadowRoot.querySelector(\"#app-vue > div > owc-header-flyout > ul >li > ul > li:nth-child(1) > a > p\").click()");


            //Valideaza Aparitia titlului conform selectiei anterioare
            String titluPagina = (String) js.executeScript("return document.querySelector(\"owc-stage\").shadowRoot.querySelector(\"h1\").textContent");
            assertTrue("Titlul nu este conform", titluPagina.equals("Modelul Clasa A Limuzină compactă"));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void afterClass2() {
        driverChrome.quit();
    }
}
