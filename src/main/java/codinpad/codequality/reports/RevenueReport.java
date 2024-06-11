package codinpad.codequality.reports;

import java.util.Arrays;

public class RevenueReport {
    public double calculateTotal(double[] revenueFigures) {
        double total = 0;
        for (double figure : revenueFigures) {
            total += figure;
        }
        return total;
    }
    // other methods related to revenue report

    //The above method could be implemented as bellow to improve performance
    // Also the method name should be changed to the one bellow to follow naming convention.
    public double getTotalRevenue(double[] revenueFigures) {
        return Arrays.stream(revenueFigures).summaryStatistics().getSum();
    }

}
