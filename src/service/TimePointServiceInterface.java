package service;

import model.InputData;
import model.TimePoint;

import java.math.BigDecimal;

public interface TimePointServiceInterface {
    TimePoint calculate(BigDecimal rateNumber, InputData inputData);
}
