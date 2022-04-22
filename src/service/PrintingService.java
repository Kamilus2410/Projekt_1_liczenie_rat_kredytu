package service;

import model.InputData;
import model.Overpayment;
import model.Rate;
import model.Summary;

import java.util.List;
import java.util.Optional;

public class PrintingService implements PrintingServiceInterface {

    @Override
    public void printInputDataInfo(InputData inputData) {
        StringBuilder msg = new StringBuilder(NEW_LINE);
        msg.append(MORTGAGE_AMOUNT).append(inputData.getAmount()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(MORTGAGE_PERIOD).append(inputData.getMonthsDuration()).append(MONTHS);
        msg.append(NEW_LINE);
        msg.append(INTEREST).append(inputData.getInterestDisplay()).append(PERCENT);
        msg.append(NEW_LINE);

        Optional.of(inputData.getOverpaymentScheme())
                .filter(scheme -> scheme.size() > 0)
                .ifPresent(scheme -> logOverpayment(msg, inputData));

        printMessage(msg);
    }

    private void logOverpayment(StringBuilder msg, InputData inputData) {
        switch (inputData.getOverpaymentReduceWay()) {
            case Overpayment.REDUCE_PERIOD:
                msg.append(OVERPAYMENT_REDUCE_PERIOD);
                break;
            case Overpayment.REDUCE_RATE:
                msg.append(OVERPAYMENT_REDUCE_RATE);
                break;
            default:
                throw new MortgageException();
        }
        msg.append(NEW_LINE);
        msg.append(OVERPAYMENT_FREQUENCY).append(inputData.getOverpaymentScheme());
        msg.append(NEW_LINE);
    }

    @Override
    public void printRates(List<Rate> rates) {
        String format = "%1s %4s | " +
                "%1s %1s | " +
                "%1s %2s | " +
                "%1s %2s | " +
                "%1s %7s | " +
                "%1s %7s | " +
                "%1s %7s | " +
                "%1s %8s | " +
                "%1s %10s | " +
                "%1s %4s | ";
        for (Rate rate : rates) {
            StringBuilder message = new StringBuilder(String.format(format,
                    RATE_NUMBER, rate.getRateNumber(),
                    DATE, rate.getTimePoint().getDate(),
                    YEAR, rate.getTimePoint().getYear(),
                    MONTH, rate.getTimePoint().getMonth(),
                    RATE, rate.getRateAmounts().getRateAmount(),
                    INTEREST_RATE, rate.getRateAmounts().getInterestAmount(),
                    CAPITAL, rate.getRateAmounts().getCapitalAmount(),
                    OVERPAYMENT, rate.getRateAmounts().getOverpayment().getAmount(),
                    LEFT, rate.getMortgageResidual().getAmount(),
                    LEFT_MONTHS, rate.getMortgageResidual().getDuration()
            ));
            printMessage(message);

            if (rate.getRateNumber().intValue() % 12 == 0) {
                System.out.println("--------------------------------------------------------------------------------" +
                        "-------------------------------------------------------------------------------------------" +
                        "--------------");
            }
        }
        System.out.println("----------------------------------------------------------------------------------------" +
                "-------------------------------------------------------------------------------------------------");
    }

    @Override
    public void printSummary(Summary summary) {
        StringBuilder msg = new StringBuilder();
        msg.append(INTEREST_SUM).append(summary.getInterestSum()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(OVERPAYMENT_PROVISION).append(summary.getOverpaymentProvisions()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(LOSS_SUM).append(summary.getTotalLoss()).append(CURRENCY);
        printMessage(msg);
        System.out.println("----------------------------------------------------------------------------------------" +
                "-------------------------------------------------------------------------------------------------");
    }

    private void printMessage(StringBuilder sb) {
        System.out.println(sb);
    }

}
