import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


    public class Assigment1Resolution {
        public static void main(String[] args) {

            System.setProperty("webdriver.chrome.driver", "C:\\Users\\10738115\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://www.flipkart.com/");
            List<WebElement> allLinks = driver.findElements(By.tagName("a"));

            System.out.println("---Retreiving links using for each loop---");

            for (WebElement a : allLinks) {
                System.out.println("Link : " + a.getAttribute("href"));
                System.out.println("Link Name : " + a.getText());
            }

            System.out.println("---Retreiving links using Stream---");

            System.out.println(allLinks);
            List<String> list = allLinks.stream().filter(link -> !link.getText().isEmpty()).map(ele -> ele.getAttribute("href")).collect(Collectors.toList());
            list.forEach(ele -> System.out.println("Link Name : " + ele));

            System.out.println("---Retreiving links using Parallel Stream---");

            List<WebElement> links = driver.findElements(By.tagName("a"));
            links.parallelStream()
                    .filter(link -> !link.getText().isEmpty())
                    .forEach(link -> System.out.println(link.getText() + " : " + link.getAttribute("href")));

            driver.close();
        }


    }



