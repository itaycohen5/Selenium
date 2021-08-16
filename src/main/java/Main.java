import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\itayc\\Desktop\\chromedriver_win32\\chromedriver.exe");



        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.aac.ac.il");
        Thread.sleep(1000);


        WebElement personalInfo = driver.findElement(By.cssSelector("a[href='https://portal.aac.ac.il']"));
        personalInfo.click();
        Thread.sleep(1000);

        WebElement userName = driver.findElement(By.id("Ecom_User_ID"));
        userName.sendKeys("itaycohen");

        WebElement userPassword = driver.findElement(By.id("Ecom_Password"));
        userPassword.sendKeys("itay1998");

        WebElement submitButton = driver.findElement(By.id("wp-submit"));
        submitButton.click();

        WebElement moodleSystem = driver.findElement(By.cssSelector("a[href='https://moodle.aac.ac.il/login/index.php']"));
        moodleSystem.click();



        List<WebElement> coursesList = driver.findElements(By.xpath("//div[contains(@class,'card dashboard-card') and contains(@role,'listitem')]"));
        System.out.println("Your courses list:");
        for (int i = 0; i < coursesList.size(); i++) {
            String courseName = null;
            try {
                courseName = coursesList.get(i).findElement(By.className("multiline")).getText();
            } catch (Exception ignored) {

            }
            if (courseName != null)
                System.out.println(i + 1 + "." + (courseName));
        }

        Scanner scanner = new Scanner(System.in);
        int courseOption=0;
        boolean error = false;
        do {
            error = false;
            try {
                System.out.println("Which course would you like to choose :");
                courseOption = scanner.nextInt();


            }catch (Exception e){
                System.out.println("Wrong option,try again.");
                error = true;
                scanner.nextLine();
            }
        }while (error);
        coursesList.get(courseOption - 1).click();

        Thread.sleep(1500);

        WebElement accountHandling= driver.findElements(By.id("action-menu-toggle-1")).get(0);
        accountHandling.click();
        driver.findElement(By.xpath("//a[@data-title='logout,moodle']")).click();


        driver.findElement(By.xpath("//a[@href='https://portal.aac.ac.il/AGLogout']")).click();
        System.out.println("Bye:) ");


    }
}
