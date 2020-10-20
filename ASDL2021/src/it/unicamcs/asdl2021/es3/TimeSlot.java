/**
 * 
 */
package it.unicamcs.asdl2021.es3;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Un time slot è un intervallo di tempo continuo che può essere associato ad
 * una prenotazione o a una facility. Gli oggetti della classe sono immutabili.
 * Non sono ammessi time slot che iniziano e finiscono nello stesso istante.
 * 
 * @author Template: Luca Tesei, Implementazione: collettiva
 *
 */
public class TimeSlot implements Comparable<TimeSlot> {

    /**
     * Rappresenta la soglia di tolleranza da considerare nella sovrapposizione
     * di due Time Slot. Se si sovrappongono per un numero di minuti minore o
     * uguale a questa soglia allora NON vengono considerati sovrapposti.
     */
    public static final int MINUTES_OF_TOLERANCE_FOR_OVERLAPPING = 5;

    private final GregorianCalendar start;

    private final GregorianCalendar stop;

    /**
     * Crea un time slot tra due istanti di inizio e fine
     * 
     * @param start
     *                  inizio del time slot
     * @param stop
     *                  fine del time slot
     * @throws NullPointerException
     *                                      se uno dei due istanti, start o
     *                                      stop, è null
     * @throws IllegalArgumentException
     *                                      se start è uguale o successivo a
     *                                      stop
     */
    public TimeSlot(GregorianCalendar start, GregorianCalendar stop) {
        // TODO implementare
        this.start = null;
        this.stop = null;
    }

    /**
     * @return the start
     */
    public GregorianCalendar getStart() {
        // TODO implementare
        return null;
    }

    /**
     * @return the stop
     */
    public GregorianCalendar getStop() {
        // TODO implementare
        return null;
    }

    /*
     * Ridefinire in accordo con equals
     */
    @Override
    public int hashCode() {
        // TODO implementare
        return -1;
    }

    /*
     * Un time slot è uguale a un altro se rappresenta esattamente lo stesso
     * intervallo di tempo, cioè se inizia nello stesso istante e termina nello
     * stesso istante.
     */
    @Override
    public boolean equals(Object obj) {
        // TODO implementare
        return false;
    }

    /*
     * Un time slot precede un altro se inizia prima. Se due time slot iniziano
     * nello stesso momento quello che finisce prima precede l'altro. Se hanno
     * stesso inizio e stessa fine sono uguali, in compatibilità con equals.
     */
    @Override
    public int compareTo(TimeSlot o) {
        // TODO implementare
        return 0;
    }

    /**
     * Determina se questo time slot si sovrappone a un altro time slot dato,
     * considerando la soglia di tolleranza.
     * 
     * @param o
     *              il time slot che viene passato per il controllo di
     *              sovrapposizione
     * @return true se questo time slot si sovrappone per più di
     *         MINUTES_OF_TOLERANCE_FOR_OVERLAPPING minuti a quello passato
     * @throws NullPointerException
     *                                  se il time slot passato è nullo
     */
    public boolean overlapsWith(TimeSlot o) {
        // TODO implementare
        return false;
    }

    /*
     * Esempio di stringa: [4/11/2019 11.0 - 4/11/2019 13.0] Esempio di stringa:
     * [10/11/2019 11.15 - 10/11/2019 23.45]
     */
    @Override
    public String toString() {
        // TODO implementare
        return null;
    }

}
