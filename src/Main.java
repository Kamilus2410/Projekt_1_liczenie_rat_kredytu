import model.InputData;
import model.Overpayment;
import model.RateType;
import service.*;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        InputData inputData = new InputData()
                .withAmount(new BigDecimal("498000"))
                .withMonthsDuration(BigDecimal.valueOf(160))
                .withRateType(RateType.DECREASING)
                .withRateType(RateType.CONSTANT)
                .withOverpaymentReduceWay(Overpayment.REDUCE_RATE);


        PrintingServiceInterface printingServiceInterface = new PrintingService();

        RateCalculationServiceInterface rateCalculationServiceInterface = new RateCalculationService(
                new TimePointService(),
                new AmountsCalculationService(new ConstantAmountsCalculationService(),
                        new DecreasingAmountsCalculationService()),
                new ResidualCalculationService(),
                new OverpaymentCalculationService(),
                new ReferenceCalculationService()
        );

        MortgageCalculationServiceInterface mortgageCalculationServiceInterface = new MortgageCalculationService(
                printingServiceInterface, rateCalculationServiceInterface, SummaryService.create());

        mortgageCalculationServiceInterface.calculate(inputData);


    }

}
