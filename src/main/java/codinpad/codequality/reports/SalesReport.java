package codinpad.codequality.reports;

import java.util.Arrays;

public class SalesReport {
    public double calculateTotal(double[] salesFigures) {
        double total = 0;
        for (double figure : salesFigures) {
            total += figure;
        }
        return total;
    }
    // other methods related to sales report

    //The above method could be implemented as bellow to improve performance
    // Also the method name should be changed to the one bellow to follow naming convention.
    public double getTotalSales(double[] salesFigures) {
        return Arrays.stream(salesFigures).summaryStatistics().getSum();
    }
}