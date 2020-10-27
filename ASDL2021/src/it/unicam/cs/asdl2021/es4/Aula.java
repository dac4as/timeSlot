package it.unicam.cs.asdl2021.es4;


import java.util.Arrays;
import java.util.Objects;

/**
 * Un oggetto della classe aula rappresenta una certa aula con le sue facilities
 * e le sue prenotazioni.
 * 
 * @author Template: Luca Tesei, Implementation: Collective
 *
 */
public class Aula implements Comparable<Aula> {

    // numero iniziale delle posizioni dell'array facilities. Se viene richiesto
    // di inserire una facility e l'array è pieno questo viene raddoppiato. La
    // costante è protected solo per consentirne l'accesso ai test JUnit
    protected static final int INIT_NUM_FACILITIES = 5;

    // numero iniziale delle posizioni dell'array prenotazioni. Se viene
    // richiesto di inserire una prenotazione e l'array è pieno questo viene
    // raddoppiato. La costante è protected solo per consentirne l'accesso ai
    // test JUnit.
    protected static final int INIT_NUM_PRENOTAZIONI = 100;

    // Identificativo unico di un'aula
    private final String nome;

    // Location dell'aula
    private final String location;

    // Insieme delle facilities di quest'aula. L'array viene creato all'inizio
    // della dimensione specificata nella costante INIT_NUM_FACILITIES. Il
    // metodo addFacility(Facility) raddoppia l'array qualora non ci sia più
    // spazio per inserire la facility.
    private Facility[] facilities;

    // numero corrente di facilities inserite
    private int numFacilities;

    // Insieme delle prenotazioni per quest'aula. L'array viene creato
    // all'inizio
    // della dimensione specificata nella costante INIT_NUM_PRENOTAZIONI. Il
    // metodo addPrenotazione(TimeSlot, String, String) raddoppia l'array
    // qualora non ci sia più
    // spazio per inserire la prenotazione.
    private Prenotazione[] prenotazioni;

    // numero corrente di prenotazioni inserite
    private int numPrenotazioni;

    /**
     * Costruisce una certa aula con nome e location. Il set delle facilities è
     * vuoto. L'aula non ha inizialmente nessuna prenotazione.
     * 
     * @param nome
     *                     il nome dell'aula
     * @param location
     *                     la location dell'aula
     * 
     * @throws NullPointerException
     *                                  se una qualsiasi delle informazioni
     *                                  richieste è nulla
     */
    public Aula(String nome, String location) {
        if(nome==null||location==null)
            throw new NullPointerException();
        // Nota: i due assegnamenti che seguono servono solo
        // per non creare un errore in questo template del codice. Vanno
        // modificati.
        this.nome = nome;
        this.location = location;
        this.prenotazioni= new Prenotazione[]{};
        this.facilities= new Facility[]{};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aula aula = (Aula) o;
        return nome.equals(aula.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    /* L'ordinamento naturale si basa sul nome dell'aula */
    @Override
    public int compareTo(Aula o) {
        if(this.getNome().equals(o.getNome())) return 0; //se i nomi delle aule corrispondono ritorno 0
        String[] array1={getNome().toLowerCase(),o.getNome().toLowerCase()};
        String[] array2={getNome().toLowerCase(),o.getNome().toLowerCase()};
        Arrays.sort(array2);
        if(Arrays.equals(array1,array2))
        {
            return 1;
        }
        else return -1;
    }

    /**
     * @return the facilities
     */
    public Facility[] getFacilities() {
        return facilities;
    }

    /**
     * @return il numero corrente di facilities
     */
    public int getNumeroFacilities() {
        return numFacilities;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return the prenotazioni
     */
    public Prenotazione[] getPrenotazioni() {
        return prenotazioni;
    }

    /**
     * @return il numero corrente di prenotazioni
     */
    public int getNumeroPrenotazioni() {
        return numPrenotazioni;
    }

    /**
     * Aggiunge una faciltity a questa aula. Controlla se la facility è già
     * presente, nel qual caso non la inserisce.
     * 
     * @param f
     *              la facility da aggiungere
     * @return true se la facility non era già presente e quindi è stata
     *         aggiunta, false altrimenti
     * @throws NullPointerException
     *                                  se la facility passata è nulla
     */
    public boolean addFacility(Facility f) {
        if(f==null)
            throw new NullPointerException();
        // Nota: attenzione! Per controllare se una facility è già presente
        // bisogna usare il metodo equals della classe Facility.
        if(this.equals(f))//se è vero vuol dire che Facility è già presente, e non deve quindi essere aggiunto (return false)
            return false;
        // Nota: attenzione bis! Si noti che per le sottoclassi di Facility non
        // è richiesto di ridefinire ulteriormente il metodo equals...
        facilities[numFacilities]=f;//aggiungo f all'indice numFacilities
        numFacilities++;
        return true;
    }

    /**
     * Determina se l'aula è libera in un certo time slot.
     * 
     * 
     * @param ts
     *               il time slot da controllare
     * 
     * @return true se l'aula risulta libera per tutto il periodo del time slot
     *         specificato
     * @throws NullPointerException
     *                                  se il time slot passato è nullo
     */
    public boolean isFree(TimeSlot ts) {
        if(ts==null)
            throw new NullPointerException();
        if (getNumeroPrenotazioni()==0)//se la lista è vuota, evito di comparare, ergo è libera
            return true;
        for(int i=0; i<getNumeroPrenotazioni();i++)
        {
            return ts.overlapsWith(prenotazioni[i].getTimeSlot());
        }
        return true;//non ci sono prenotazioni, ergo è libera
    }

    /**
     * Determina se questa aula soddisfa tutte le facilities richieste
     * rappresentate da un certo insieme dato.
     * 
     * @param requestedFacilities
     *                                l'insieme di facilities richieste da
     *                                soddisfare, sono da considerare solo le
     *                                posizioni diverse da null
     * @return true se e solo se tutte le facilities di
     *         {@code requestedFacilities} sono soddisfatte da questa aula.
     * @throws NullPointerException
     *                                  se il set di facility richieste è nullo
     */
    public boolean satisfiesFacilities(Facility[] requestedFacilities) {
        if(requestedFacilities==null)
            throw new NullPointerException();
        for(Facility f:requestedFacilities)
        {
            if((f instanceof PresenceFacility && this.getFacilities().equals(requestedFacilities)) || (f instanceof QuantitativeFacility && this.getFacilities().equals(f)))
                //non ho idea di cosa abbia fatto, ma nel dubbio
                return true;
        }

        return false;
    }

    /**
     * Prenota l'aula controllando eventuali sovrapposizioni.
     * 
     * @param ts
     * @param docente
     * @param motivo
     * @throws IllegalArgumentException
     *                                      se la prenotazione comporta una
     *                                      sovrapposizione con un'altra
     *                                      prenotazione nella stessa aula.
     * @throws NullPointerException
     *                                      se una qualsiasi delle informazioni
     *                                      richieste è nulla.
     */
    public void addPrenotazione(TimeSlot ts, String docente, String motivo) {
        if(ts==null || docente==null || motivo==null)
            throw new NullPointerException();
        if(!isFree(ts))
            throw new IllegalArgumentException();
        Aula a =this;
        Prenotazione p = new Prenotazione(a,ts,docente,motivo);
        //System.out.println(p);
        //System.out.println(this);//dice che è null ma non dovrebbe
        prenotazioni[numPrenotazioni]=p;
        numPrenotazioni++;
    }
}
