package service;

import model.InputData;
import model.Rate;

import java.util.List;

public class MortgageCalculationService implements MortgageCalculationServiceInterface {

    private final PrintingServiceInterface printingServiceInterface;
    private final RateCalculationServiceInterface rateCalculationServiceInterface;
    private final SummaryServiceInterface summaryServiceInterface;

    public MortgageCalculationService(
            PrintingServiceInterface printingServiceInterface,
            RateCalculationServiceInterface rateCalculationServiceInterface,
            SummaryServiceInterface summaryServiceInterface) {
        this.printingServiceInterface = printingServiceInterface;
        this.rateCalculationServiceInterface = rateCalculationServiceInterface;
        this.summaryServiceInterface = summaryServiceInterface;

    }

    @Override
    public void calculate(InputData inputData) {
        printingServiceInterface.printInputDataInfo(inputData);
        List<Rate> rates = rateCalculationServiceInterface.calculate(inputData);

        printingServiceInterface.printSummary(summaryServiceInterface.calculate(rates));
        printingServiceInterface.printRates(rates);

    }
}
