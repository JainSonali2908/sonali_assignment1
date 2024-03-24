import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.*;
import java.util.Map.Entry;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;

        public class Assignment3Resolution {
            static HashMap<String, String> mapFromExcel;
            static HashMap<String, String> mapFromUI;
            static XSSFWorkbook wb;
            static FileInputStream fis;
            static XSSFSheet sh;
            static List<WebElement> companyNameList;
            static List<WebElement> currentPriceList;
            static WebDriver driver;
            public static void main(String[] args) {
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\10738115\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
                driver=null;
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.get("https://money.rediff.com/gainers/bse/daily/groupall");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //Loading excel
                try {
                    loadExcel();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                Map<String,String> expMap= storeDataInMapFromExcel();
                Map<String,String> actualMap=storeDataInMapFromUI();

                boolean areRecordsMatching=  areEqualNorm(actualMap,expMap);
                if(areRecordsMatching==true)
                {
                    System.out.println("Records are matching with UI and Excel-Pass");
                }
                else {
                    System.out.println("Records are not matching with UI and Excel-Fail");
                }
            }
            private static boolean areEqualNorm(Map<String, String> actual, Map<String, String> expMap) {
                if (actual.size() != expMap.size()) {
                    return false;
                }

                return actual.entrySet().stream()
                        .allMatch(e1 -> e1.getValue().equals(expMap.get(e1.getKey())));
            }
            public static void loadExcel() throws Exception {
                File f = new File("C:\\Users\\10738115\\Downloads\\Doc1.xlsx");
                fis = new FileInputStream(f);
                wb = new XSSFWorkbook(fis);
                sh = wb.getSheet("Sheet3");
                fis.close();

            }

            // Storing data Into HashMap from excel
            public static Map storeDataInMapFromExcel() {

                mapFromExcel = new HashMap<String,String>();
                String value="";
                for (int r = 0; r <= sh.getLastRowNum(); r++) {

                    String key = sh.getRow(r)
                            .getCell(0)
                            .getStringCellValue();

                    switch (sh.getRow(r).getCell(1).getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            // System.out.println(sh.getRow(r).getCell(1).getStringCellValue()+" r "+r);
                            value=sh.getRow(r).getCell(1).getStringCellValue();
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            //System.out.println(sh.getRow(r).getCell(1).getNumericCellValue()+" r "+r);
                            //System.out.println(sh.getRow(r).getCell(1).getNumericCellValue()+" r "+r);
                            value=String.valueOf(sh.getRow(r).getCell(1).getNumericCellValue());
                            break;
                        default:
                            System.out.println("not matching type");
                    }
                    mapFromExcel.put(key, value);
                }
                return mapFromExcel;
            }

            // Storing data Into HashMap from UI
            public static Map storeDataInMapFromUI() {

                companyNameList = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr/td/a"));
                currentPriceList = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr/td[4]"));
                mapFromUI = new HashMap<String, String>();
                if (companyNameList.size() == currentPriceList.size()) {
                    for (int r = 0; r < companyNameList.size(); r++) {
                        String key = companyNameList.get(r).getText().toString().trim();

                        String value = currentPriceList.get(r).getText().toString().trim();
                        //System.out.println(Double.valueOf(value));

                        mapFromUI.put(key, value);

                    }


                } else {
                    System.out.println("Records Mismatch");
                }

                return mapFromUI;

            }
        }
