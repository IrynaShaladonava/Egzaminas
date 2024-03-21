import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestCalculatorTestNG {

    @BeforeClass
    public void setUp() {
        try {
            TestCalculator.setup();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public void tearDown() {
        TestCalculator.driver.quit();
    }

    @Test(priority = 2)
    public void testSendIncorrectKeys() {
        TestCalculator.createAccountButton();
        TestCalculator.sendIncorrectKeys();
        TestCalculator.clickSukurtiButton();
        TestCalculator.checkErrors();
    }

    @Test(priority = 3)
    public void testSendCorrectKeys() {
        TestCalculator.clearForm();
        TestCalculator.sendCorrectKeys();
        TestCalculator.clickSukurtiCorrectButton();
        TestCalculator.checkRegistrationSuccess();
    }

    @Test(priority = 5)
    public void testLoginNegative() {
        TestCalculator.logOut();
        TestCalculator.logInNegative();
        Assert.assertFalse(TestCalculator.driver.getPageSource().contains("Error"), "Login negative test failed.");
    }

    @Test(priority = 6)
    public void testLoginPositive() {
        TestCalculator.logInPositive();
        Assert.assertTrue(TestCalculator.driver.getTitle().equals("Skaičiuotuvas"), "Login positive test failed.");
    }

    @Test(priority = 7)
    public void testSendOperationsPositive() {
        TestCalculator.clearFields();
        TestCalculator.sendOperationsPositive();
        Assert.assertFalse(TestCalculator.driver.findElement(By.tagName("h4")).getText().contains("Result"), "Send operations positive test failed.");
    }

    @Test(priority = 8)
    public void testSendOperationsNegative() {
        TestCalculator.clearFields();
        TestCalculator.sendOperationsNegative();
        Assert.assertFalse(TestCalculator.driver.getPageSource().contains("Error"), "Send operations negative test failed.");
    }

    @Test(priority = 9)
    public void testFindDivisionResult() {
        TestCalculator.findDivisionResult();
        String pageSource = TestCalculator.driver.getPageSource();

        boolean found = pageSource.contains("Rastas įrašas: 999");
        Assert.assertFalse(found, "Find division result test failed.");
    }

    @Test(priority = 10)
    public void testFindDivisionResultNegative() {
        TestCalculator.findDivisionResultNegative();
        String pageSource = TestCalculator.driver.getPageSource();
        boolean found = pageSource.contains("Įrašas su tekstu '9999' nerastas."); // Здесь укажите ожидаемое сообщение об ошибке, которое должно быть выведено методом findDivisionResultNegative()
        Assert.assertFalse(found, "Find division result negative test failed.");
    }




//    @Test(priority = 11)
//    public void testDeleteOperationNegative() {
//        TestCalculator.goBack();
//        TestCalculator.deleteOperationNegative();
//        Assert.assertFalse(TestCalculator.driver.getPageSource().contains("Error"), "Delete operation negative test failed.");
//    }
//
//    @Test(priority = 12)
//    public void testDeleteOperation() {
//        TestCalculator.deleteOperation();
//        Assert.assertFalse(TestCalculator.driver.findElements(By.partialLinkText("Trinti")).isEmpty(), "Delete operation test failed.");
//    }
}


