package utils;

import com.google.common.io.Files;
import dto.Car;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;
import pages.BasePage;
import pages.LetTheCarWorkPage;

import java.io.*;
import java.nio.Buffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestUtil extends BasePage {

    public static void takeScreenShot(){
        LocalDateTime ldt = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        DateTimeFormatter timeStamp = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH-mm-ss");
        String s = timeStamp.format(ldt);
        System.out.println(s);
        File screenShot = new File(String.format("failed_tests/screen_%s.png", s));
        TakesScreenshot screenshot2 = (TakesScreenshot) driver;
        File file = screenshot2.getScreenshotAs(OutputType.FILE);
        try{
            Files.copy(file, screenShot);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @DataProvider()
    public Iterator<Object[]> getTestingData(){
        BufferedReader br;
        List<Object[]> list = new ArrayList<>();

        try{
            br = new BufferedReader(new FileReader("C:\\Qa29\\CarRental_POM\\src\\test\\java\\testdata\\carData.txt"));
            String line = br.readLine();
            while (line != null){
                String[] str = line.split(",");
                LetTheCarWorkPage.errorMessage = str[0];
                Car car = Car.builder()
                        .address(str[1])
                        .make(str[2])
                        .model(str[3])
                        .year(str[4])
                        .engine(str[5])
                        .fuel(str[6])
                        .gear(str[7])
                        .wheelsDrive(str[8])
                        .doors(str[9])
                        .seats(str[10])
                        .carClass(str[11])
                        .fuelConsumption(str[12])
                        .carRegistrationNumber(str[13])
                        .price(str[14])
                        .distanceIncluded(str[15])
                        .photoPath(str[16])
                        .build();
                System.out.println(car);
                System.out.println(str.length);
                list.add(new Object[]{car});
                line = br.readLine();
            }

        }catch (IOException e){
            e.getMessage();
        }

        return list.iterator();
    }
}
