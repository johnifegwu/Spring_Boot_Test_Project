package codinpad.codequality.openclosed;

/***
 * Persist the provided invoice to REDIS Cache.
 */
public class InvoiceREDISPersistence implements IInvoicePersistence{
    IInvoice invoice;

    public InvoiceREDISPersistence (IInvoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public void saveToMemory() {
        // Saves the invoice data to a REDIS cache.
    }
}
