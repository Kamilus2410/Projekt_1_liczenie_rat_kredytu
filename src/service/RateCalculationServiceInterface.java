package service;

import model.InputData;
import model.Rate;

import java.util.List;


public interface RateCalculationServiceInterface {

    List<Rate> calculate(final InputData inputData);
}
