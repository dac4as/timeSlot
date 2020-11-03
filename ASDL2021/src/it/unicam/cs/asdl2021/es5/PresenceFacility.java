/**
 * 
 */
package it.unicam.cs.asdl2021.es5;

/**
 * Una Presence Facility è una facility che può essere presente oppure no. Ad
 * esempio la presenza di un proiettore HDMI oppure la presenza dell'aria
 * condizionata.
 * 
 * @author Template: Luca Tesei, Implementation: Collective
 *
 */
public class PresenceFacility extends Facility {

    private final String codice;

    private final String descrizione;
    /**
     * Costruisce una presence facility.
     *
     * @param codice
     * @param descrizione
     * @throws NullPointerException
     *                                  se una qualsiasi delle informazioni
     *                                  richieste è nulla.
     */
    public PresenceFacility(String codice, String descrizione) {
        super(codice, descrizione);//costruttore della superclasse
        if(codice==null || descrizione==null)
            throw new NullPointerException();
        this.codice=codice;
        this.descrizione=descrizione;
    }

    /*
     * Una Presence Facility soddisfa una facility solo *SOLO* se la facility passata è
     * una Presence Facility ed ha lo stesso codice.
     *
     * Non è stato specificato che va un NullPointerEx qui
     */
    @Override
    public boolean satisfies(Facility o) {
        if(o==null)
            throw new NullPointerException();
        if(o instanceof PresenceFacility && (this.getCodice().equals(o.getCodice()) || this.getCodice()==o.getCodice()))
            return true;
        else
            return false;

    }

}
