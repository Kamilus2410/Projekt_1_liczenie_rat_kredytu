package model;

import java.math.BigDecimal;

public class Summary {

    private final BigDecimal interestSum;
    private final BigDecimal overpaymentProvisions;
    private final BigDecimal totalLoss;

    public Summary(BigDecimal interestSum, BigDecimal overpaymentProvisions, BigDecimal totalLoss) {
        this.interestSum = interestSum;
        this.overpaymentProvisions = overpaymentProvisions;
        this.totalLoss = totalLoss;
    }

    public BigDecimal getInterestSum() {
        return interestSum;
    }

    public BigDecimal getOverpaymentProvisions() {
        return overpaymentProvisions;
    }

    public BigDecimal getTotalLoss() {
        return totalLoss;
    }
}
