package service;

import model.InputData;
import model.Overpayment;
import model.Rate;
import model.RateAmounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AmountsCalculationService implements  AmountsCalculationServiceInterface{

private final  ConstantAmountsCalculationServiceInterface constantAmountsCalculationServiceInterface;
private final  DecreasingAmountsCalculationServiceInterface decreasingAmountsCalculationServiceInterface;

    public AmountsCalculationService(
            ConstantAmountsCalculationServiceInterface constantAmountsCalculationServiceInterface,
            DecreasingAmountsCalculationServiceInterface decreasingAmountsCalculationServiceInterface)
    {
        this.constantAmountsCalculationServiceInterface = constantAmountsCalculationServiceInterface;
        this.decreasingAmountsCalculationServiceInterface = decreasingAmountsCalculationServiceInterface;
    }

    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment) {
        switch (inputData.getRateType()) {
            case CONSTANT:
                return constantAmountsCalculationServiceInterface.calculate(inputData, overpayment);
            case DECREASING:
                return decreasingAmountsCalculationServiceInterface.calculate(inputData, overpayment);
            default:
                throw new MortgageException();
        }
    }

    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate) {
        switch (inputData.getRateType()) {
            case CONSTANT:
                return constantAmountsCalculationServiceInterface.calculate(inputData, overpayment, previousRate);
            case DECREASING:
                return decreasingAmountsCalculationServiceInterface.calculate(inputData, overpayment, previousRate);
            default:
                throw new MortgageException();
        }
    }

}
