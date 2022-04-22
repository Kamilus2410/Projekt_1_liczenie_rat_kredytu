package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

public class InputData {

    private static final BigDecimal PERCENT = new BigDecimal("100");

    private LocalDate repaymentStartDate = LocalDate.of(2022,04,12);
    private BigDecimal wiborPercent = new BigDecimal("5.5");
    private BigDecimal amount = new BigDecimal("600000");
    private BigDecimal monthsDuration = BigDecimal.valueOf(180);
    private RateType rateType = RateType.DECREASING;
    private BigDecimal bankMarginPercent = new BigDecimal("1.9");

    private Map <Integer, BigDecimal> overpaymentScheme = Map.of(
            5, BigDecimal.valueOf(10000),
            6, BigDecimal.valueOf(10000),
            7, BigDecimal.valueOf(10000),
            8, BigDecimal.valueOf(10000)
    );
    private String overpaymentReduceWay = Overpayment.REDUCE_PERIOD;
    private BigDecimal overpaymentProvisionPercent = BigDecimal.valueOf(3);
    private BigDecimal getOverpaymentProvisionMonths = BigDecimal.valueOf(36);

    public InputData withOverpeymentScheme(Map <Integer, BigDecimal> overpaymentScheme) {
        this.overpaymentScheme = overpaymentScheme;
        return this;
    }

    public InputData withOverpaymentReduceWay (String overpaymentReduceWay) {
        this.overpaymentReduceWay = overpaymentReduceWay;
        return this;
    }

    public InputData withOverpaymentProvisionPercent (BigDecimal overpaymentProvisionPercent) {
        this.overpaymentProvisionPercent = overpaymentProvisionPercent;
        return this;
    }

    public InputData withGetOverpaymentProvisionMonths (BigDecimal getOverpaymentProvisionMonths) {
        this.getOverpaymentProvisionMonths = getOverpaymentProvisionMonths;
        return this;
    }
    public InputData withRepaymentStartDate (LocalDate repaymentStartDate) { //to jest wither

        this.repaymentStartDate = repaymentStartDate;
        return this;
    }

    public InputData withWiborPercent (BigDecimal wiborPercent) {
        this.wiborPercent = wiborPercent;
        return this;
    }

    public InputData withAmount (BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public InputData withMonthsDuration (BigDecimal monthsDuration) {
        this.monthsDuration = monthsDuration;
        return this;
    }

    public InputData withRateType (RateType rateType) {
        this.rateType = rateType;
        return this;
    }

    public InputData withBankMarginPercent (BigDecimal bankMarginPercent) {
        this.bankMarginPercent = bankMarginPercent;
        return this;
    }

    public LocalDate getRepaymentStartDate() {
        return repaymentStartDate;
    }

    public BigDecimal getWiborPercent() {
        return wiborPercent;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getMonthsDuration() {
        return monthsDuration;
    }

    public RateType getRateType() {
        return rateType;
    }

    public BigDecimal getBankMarginPercent() {
        return bankMarginPercent;
    }

    public BigDecimal getInterestPercent() {
        return wiborPercent.add(bankMarginPercent).divide(PERCENT, 4, RoundingMode.HALF_UP);
    }

    public BigDecimal getInterestDisplay() {
        return wiborPercent.add(bankMarginPercent).setScale(2, RoundingMode.HALF_UP);
    }

    public Map<Integer, BigDecimal> getOverpaymentScheme() {
        return overpaymentScheme;
    }

    public String getOverpaymentReduceWay() {
        return overpaymentReduceWay;
    }

    public BigDecimal getOverpaymentProvisionPercent() {
        return overpaymentProvisionPercent.divide(PERCENT, 4, RoundingMode.HALF_UP);
    }

    public BigDecimal getGetOverpaymentProvisionMonths() {
        return getOverpaymentProvisionMonths;
    }
}
