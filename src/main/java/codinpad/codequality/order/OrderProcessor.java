package codinpad.codequality.order;

// =====================================
// The Java class OrderProcessor below violates one of the SOLID Principles. Which one?
//======================================================================================
// It violates the Dependency Inversion Principle for the following reasons:
// 1. We may have different kinds of Orders that may have different ways of validating their validity.
// 2. Each of these orders may also have different ways of generating their Ids as well.
// So, we will create a base order abstract class for our code bellow, we will then implement this base order abstract class
// in each of our order classes.

// Refactor the code accordingly.
public class OrderProcessor {
    public void processOrder(IOrder order) {
        if (order.isValid() && saveOrder(order)) {
            System.out.println("Order processed successfully");
        } else {
            System.err.println("Error processing order");
        }
    }

    private boolean saveOrder(IOrder order) {
        // Saving the order in the database
        return true;
    }
    
    public void logOrderProcess(IOrder order, boolean isSuccess) {
        if (isSuccess) {
            System.out.println("Order " + order.getId() + " processed.");
        } else {
            System.err.println("Failed to process order " + order.getId());
        }
    }
}
