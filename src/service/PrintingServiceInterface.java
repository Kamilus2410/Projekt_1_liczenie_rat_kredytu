package service;

import model.InputData;
import model.Rate;
import model.Summary;

import java.util.List;

public interface PrintingServiceInterface {

    String INTEREST_SUM = "SUMA ODSETEK: ";
    String OVERPAYMENT_PROVISION = "PROWIZJA ZA NADPLATY: ";
    String LOSS_SUM = "SUMA STRAT: ";

    String OVERPAYMENT_REDUCE_RATE = "NADPLATA, ZMNIEJSZENIE RATY";
    String OVERPAYMENT_REDUCE_PERIOD = "NADPLATA, SKROCENIE OKRESU";
    String OVERPAYMENT_FREQUENCY = "SCHEMAT DOKONYWANIA NADPLAT: ";

    String RATE_NUMBER = "| NR RATY: ";
    String YEAR = "ROK: ";
    String MONTH = "MIESIAC: ";
    String DATE = "DATA: ";
    String MONTHS = " MIESIECY ";
    String LEFT_MONTHS = "MIESIECY ";
    String RATE = "RATA: ";
    String INTEREST = "OPROCENTOWANIE: ";
    String INTEREST_RATE = "ODSETKI: ";
    String CAPITAL = "KAPITAL: ";
    String OVERPAYMENT = "NADPLATA: ";
    String LEFT = "POZOSTALO: ";
    String MORTGAGE_AMOUNT = "KWOTA KREDYTU: ";
    String MORTGAGE_PERIOD = "OKRES KREDYTOWANIA: ";

    String CURRENCY = " zl";
    String NEW_LINE = "\n";
    String PERCENT = "% ";

    void printInputDataInfo (final InputData inputData);

    void printRates(List<Rate> rates);

    void printSummary(Summary summary);
}
