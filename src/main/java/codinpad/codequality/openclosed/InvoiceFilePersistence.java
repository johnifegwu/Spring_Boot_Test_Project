package codinpad.codequality.openclosed;

/***
 * Persist the provided invoice to File.
 */
public class InvoiceFilePersistence implements IInvoicePersistence{

    IInvoice invoice;
    String fileName;

    public InvoiceFilePersistence (IInvoice invoice, String fileName) {
        this.invoice = invoice;
        this.fileName = fileName;
    }

    @Override
    public void saveToMemory() {
        // Creates a file with the given name and writes the invoice data to this file
    }
}
