package service;

import model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

public class RateCalculationService implements RateCalculationServiceInterface {

    private final TimePointServiceInterface timePointServiceInterface;
    private final AmountsCalculationServiceInterface amountsCalculationServiceInterface;
    private final ResidualCalculationServiceInterface residualCalculationServiceInterface;
    private final OverpaymentCalculationServiceInterface overpaymentCalculationServiceInterface;
    private final ReferenceCalculationServiceInterface referenceCalculationServiceInterface;

    public RateCalculationService(
            TimePointServiceInterface timePointServiceInterface,
            AmountsCalculationServiceInterface amountsCalculationServiceInterface,
            ResidualCalculationServiceInterface residualCalculationServiceInterface,
            OverpaymentCalculationServiceInterface overpaymentCalculationServiceInterface,
            ReferenceCalculationServiceInterface referenceCalculationServiceInterface)
    {
        this.timePointServiceInterface = timePointServiceInterface;
        this.amountsCalculationServiceInterface = amountsCalculationServiceInterface;
        this.residualCalculationServiceInterface = residualCalculationServiceInterface;
        this.overpaymentCalculationServiceInterface = overpaymentCalculationServiceInterface;
        this.referenceCalculationServiceInterface = referenceCalculationServiceInterface;
    }

    @Override
    public List<Rate> calculate(InputData inputData) {
        List<Rate> rates = new LinkedList<>();

        BigDecimal rateNumber = BigDecimal.ONE;
        Rate firstRate = calculateRate(rateNumber, inputData);

        rates.add(firstRate);

        Rate previousRate = firstRate;

        for (BigDecimal index = rateNumber.add(BigDecimal.ONE);
             index.compareTo(inputData.getMonthsDuration()) <= 0;
             index = index.add(BigDecimal.ONE)) {
            Rate nextRate = calculateRate(index, inputData, previousRate);
            rates.add(nextRate);
            previousRate = nextRate;

            if (BigDecimal.ZERO.equals(nextRate.getMortgageResidual().getAmount().setScale(0, RoundingMode.HALF_UP))) {
            break;
            }
        }
        return rates;
    }

    private Rate calculateRate(BigDecimal rateNumber, InputData inputData) {
        TimePoint timePoint = timePointServiceInterface.calculate(rateNumber, inputData);
        Overpayment overpayment = overpaymentCalculationServiceInterface.calculate(rateNumber, inputData);
        RateAmounts rateAmounts = amountsCalculationServiceInterface.calculate(inputData, overpayment);
        MortgageResidual mortgageResidual = residualCalculationServiceInterface.calculate(rateAmounts, inputData);
        MortgageReference mortgageReference = referenceCalculationServiceInterface.calculate(inputData);

        return new Rate(rateNumber, timePoint, rateAmounts, mortgageResidual, mortgageReference);
    }

    private Rate calculateRate(BigDecimal rateNumber, InputData inputData, Rate previousRate) {
        TimePoint timePoint = timePointServiceInterface.calculate(rateNumber, inputData);
        Overpayment overpayment = overpaymentCalculationServiceInterface.calculate(rateNumber, inputData);
        RateAmounts rateAmounts = amountsCalculationServiceInterface.calculate(inputData, overpayment, previousRate);
        MortgageResidual mortgageResidual = residualCalculationServiceInterface.calculate(rateAmounts, previousRate);
        MortgageReference mortgageReference =
                referenceCalculationServiceInterface.calculate(inputData, rateAmounts, previousRate);

        return new Rate(rateNumber, timePoint, rateAmounts, mortgageResidual, mortgageReference);
    }
}
