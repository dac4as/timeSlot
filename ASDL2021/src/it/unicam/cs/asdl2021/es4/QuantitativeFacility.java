/**
 * 
 */
package it.unicam.cs.asdl2021.es4;

/**
 * Una Quantitative Facility è una facility che contiene una informazione
 * quantitativa. Ad esempio la presenza di 50 posti a sedere oppure la presenza
 * di 20 thin clients.
 * 
 * @author Template: Luca Tesei, Implementation: Collective
 *
 */
public class QuantitativeFacility extends Facility {

    private final int quantity;

    private final String codice;

    private final String descrizione;

    /**
     * Costruisce una facility quantitativa.
     * 
     * @param codice
     *                        codice identificativo della facility
     * @param descrizione
     *                        descrizione testuale della facility
     * @param quantity
     *                        quantita' associata alla facility
     * @throws NullPointerException
     *                                  se una qualsiasi delle informazioni
     *                                  {@code codice} e {@code descrizione} è
     *                                  nulla.
     */
    public QuantitativeFacility(String codice, String descrizione,
            int quantity) {
        super(codice, descrizione);
        if(codice==null || descrizione==null)
            throw new NullPointerException();
        this.codice=codice;
        this.descrizione=descrizione;
        this.quantity = 0;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /*
     * Questa quantitative facility soddisfa una facility data se e solo se la
     * facility data è una quantitative facility, ha lo stesso codice e la
     * quantità associata a questa facility è maggiore o uguale alla quantità
     * della facility data.
     */
    @Override
    public boolean satisfies(Facility o) {
        if(o instanceof QuantitativeFacility && this.getCodice().equals(o) && this.getQuantity()>=((QuantitativeFacility) o).getQuantity())
            return true;
        else return false;
    }

}
