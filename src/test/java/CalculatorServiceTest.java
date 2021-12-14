import com.damico958.cloud_native.tema06.CalculatorConfig;
import com.damico958.cloud_native.tema06.CalculatorService;
import com.damico958.cloud_native.tema06.Operation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CalculatorConfig.class)
public class CalculatorServiceTest {
    @Autowired
    private Operation addition, subtraction, multiplication, division, exponentiation;

    @Autowired
    private CalculatorService calculatorService;

    private final double firstArgumentTest = 10;
    private final double secondArgumentTest = 5;

    @Test
    public void shouldCalculateAdditionCorrectly() {
        double addedResultTest = calculatorService.execute(addition, firstArgumentTest, secondArgumentTest);
        assertEquals(15, addedResultTest);
    }

    @Test
    public void shouldCalculateAnyOperationAsManyTimesAsRequested() {
        double addedResultTest = calculatorService.execute(addition, firstArgumentTest, secondArgumentTest);
        assertEquals(15, addedResultTest);
        double thirdArgumentTest = 18, fourthArgumentTest = 7;
        double secondAddedResultTest = calculatorService.execute(addition, thirdArgumentTest, fourthArgumentTest);
        assertEquals(25, secondAddedResultTest);
    }

    @Test
    public void shouldCalculateSubtractionCorrectly() {
        double subtractedResultTest = calculatorService.execute(subtraction, firstArgumentTest, secondArgumentTest);
        assertEquals(5, subtractedResultTest);
    }

    @Test
    public void shouldCalculateMultiplicationCorrectly() {
        double multipliedResultTest = calculatorService.execute(multiplication, firstArgumentTest, secondArgumentTest);
        assertEquals(50, multipliedResultTest);
    }

    @Test
    public void shouldCalculateDivisionCorrectly() {
        double dividedResultTest = calculatorService.execute(division, firstArgumentTest, secondArgumentTest);
        assertEquals(2, dividedResultTest);
    }

    @Test
    public void shouldNotCalculateDivisionWhenSecondArgumentIsZero() {
        String divisionByZeroExceptionMessage = "Fail! Can't divide a number by zero!";
        IllegalArgumentException exceptionTest = assertThrows(IllegalArgumentException.class, () -> calculatorService.execute(division, firstArgumentTest, 0));
        assertEquals(divisionByZeroExceptionMessage, exceptionTest.getMessage());
    }

    @Test
    public void shouldCalculateExponentiationCorrectly() {
        double poweredResultTest = calculatorService.execute(exponentiation, firstArgumentTest, secondArgumentTest);
        assertEquals(100000, poweredResultTest);
    }

    @Test
    public void shouldIncreaseHistorySizeByOneAfterOneOperation() {
        int currentHistorySize = calculatorService.getCalculatorHistory().size();
        int afterHistorySize = currentHistorySize + 1;
        double addedResultTest = calculatorService.execute(addition, firstArgumentTest, secondArgumentTest);
        assertEquals(15, addedResultTest);
        assertEquals(afterHistorySize, calculatorService.getCalculatorHistory().size());
    }

    @Test
    public void shouldShowCorrectHistoryAfterMoreThanOneOperation() {
        int currentHistorySize = calculatorService.getCalculatorHistory().size();
        int afterHistorySize = currentHistorySize + 5;
        calculatorService.execute(addition, firstArgumentTest, secondArgumentTest);
        calculatorService.execute(subtraction, firstArgumentTest, secondArgumentTest);
        calculatorService.execute(multiplication, firstArgumentTest, secondArgumentTest);
        calculatorService.execute(division, firstArgumentTest, secondArgumentTest);
        calculatorService.execute(exponentiation, firstArgumentTest, secondArgumentTest);
        assertEquals(afterHistorySize, calculatorService.getCalculatorHistory().size());
    }
}