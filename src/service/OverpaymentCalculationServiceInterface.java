package service;

import model.InputData;
import model.Overpayment;

import java.math.BigDecimal;

public interface OverpaymentCalculationServiceInterface {

    Overpayment calculate(BigDecimal rateNumber, InputData inputData);
}
