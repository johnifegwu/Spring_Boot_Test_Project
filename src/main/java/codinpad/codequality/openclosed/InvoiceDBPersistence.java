package codinpad.codequality.openclosed;

// =====================================
// Review the InvoicePersistence class:
// 1. Identify any issues related to the Open/Closed Principle.
// 2. Propose a refactor of the class design to adhere to the Open/Closed Principle, allowing for future extensions like saving to different types of storage without modifying existing code.
// 3. Describe or implement your solution.

//=========================================================================
// 1. The InvoicePersistence should be made an abstract class such that it will become extendable.
// 2. We will create IInvoicePersistence interface class with only method (saveToMemory) and implement it in the InvoiceDBPersistence
//    class for Database, InvoiceREDISPersistence for REDIS and InvoiceFilePersistence for File.
//
//=========================================================================

// Given the following Java class:
// 3. Renaming the bellow class from InvoicePersistence to InvoiceDBPersistence

/***
 * Persist the provided invoice to Database.
 */
public class InvoiceDBPersistence implements IInvoicePersistence {
    IInvoice invoice;

    public InvoiceDBPersistence (IInvoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public void saveToMemory() {
        // Saves the invoice data to a database
    }

}