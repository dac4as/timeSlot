/**
 * 
 */
package it.unicam.cs.asdl2021.es5;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * Un time slot è un intervallo di tempo continuo che può essere associato ad
 * una prenotazione o a una facility. Gli oggetti della classe sono immutabili.
 * Non sono ammessi time slot che iniziano e finiscono nello stesso istante.
 * 
 * @author Template: Luca Tesei, Implementation: Collective
 *
 */
public class TimeSlot implements Comparable<TimeSlot> {//"questa classe definisce l'ordinamento naturale di timeslot"

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
        if(start==null||stop==null)
            throw new NullPointerException();
        else if(start.before(stop)) {//se l'else if è vero costruisci, altrimenti lancia eccezione. Manca l'equals per vedere se corrispondono start.equals(stop)
            this.start = start;//andava anche bene il start.compareTo(stop)
            this.stop = stop;
        }
        else
            throw new IllegalArgumentException();
    }

    /**
     * @return the start
     */
    public GregorianCalendar getStart() {
        return start;
    }

    /**
     * @return the stop
     */
    public GregorianCalendar getStop() {
        return stop;
    }

    /*
     * Ridefinire in accordo con equals
     */
    @Override
    public int hashCode() {
        return Objects.hash(start,stop);
    }

    /*
    public int hashCode()
    {
        int result = numero primo;
        result = 31 * result + this.start.hashCode();
        result = 31 * result + this.stop.hashCode();
        return result;
    }
*/
    /*
     * Un time slot è uguale a un altro se rappresenta esattamente lo stesso
     * intervallo di tempo, cioè se inizia nello stesso istante e termina nello
     * stesso istante.
     */
    @Override
    public boolean equals(Object obj) {
        //if(this==obj) return true;//non credo sia necessario
        if(obj==null|| this.getClass() != obj.getClass())
            return false;
        TimeSlot timeSlot = (TimeSlot) obj;
        if (this.getStart().getTimeInMillis()==timeSlot.getStart().getTimeInMillis() && this.getStop().getTimeInMillis()==timeSlot.getStop().getTimeInMillis())
            return true;
        else return false;//standard return
    }

    /*
     * Un time slot precede un altro se inizia prima. Se due time slot iniziano
     * nello stesso momento quello che finisce prima precede l'altro. Se hanno
     * stesso inizio e stessa fine sono uguali, in compatibilità con equals.
     */
    @Override
    public int compareTo(TimeSlot o) {
        if(this.getStart().before(o.getStart()))
            return -1;
        else if (this.getStart().after(o.getStart()))//se non vanno entrambi gli if, teoricamente i getStart() coincidono
            return 1;
        else if(this.getStop().before(o.getStop()))//quindi si provvede a verificare quale dei due finsice prima
            return -1;
        else if(this.getStop().after(o.getStop()))
            return 1;
        else return 0;//standard return(vuol dire che sia i getStart() che i getStop() coincidono)
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
        long millis; //se inizializzo la variabile l'ide me la segnala ridondante
        if(o==null)
            throw new NullPointerException();
        if(o.getStart().after(this.getStart()) && this.getStop().before(o.getStop()) && this.getStop().after(o.getStart())) //cond 1 degli appunti cartacei
        {
            millis = (this.getStop().getTimeInMillis()-o.getStart().getTimeInMillis())/60000;//prendo i millis ma li converto subito in minuti
            if(millis>MINUTES_OF_TOLERANCE_FOR_OVERLAPPING)
                return true;
            else return false; //l'overlap inferiore ai 5 min non viene considerato
        }

        else if(this.getStart().after(o.getStart()) && o.getStop().before(this.getStop()) && this.getStart().before(o.getStop())) //cond 2 degli appunti cartacei (ho fatto un disegno)
        {
            millis = (o.getStop().getTimeInMillis()-this.getStart().getTimeInMillis())/60000;//prendo i millis ma li converto subito in minuti
            if(millis>MINUTES_OF_TOLERANCE_FOR_OVERLAPPING)
                return true;
            else return false; //l'overlap inferiore ai 5 min non viene considerato
        }

        else if (this.getStart().after(o.getStart()) && this.getStop().before(o.getStop()))
        {
            millis=(getStop().getTimeInMillis()-getStart().getTimeInMillis())/60000;
            if(millis>MINUTES_OF_TOLERANCE_FOR_OVERLAPPING)
                return true;
            else return false;
        }

        else if(this.getStart().before(o.getStart()) && this.getStop().after(o.getStop()))
        {
            millis=(o.getStop().getTimeInMillis()-o.getStart().getTimeInMillis())/60000;
            if(millis>MINUTES_OF_TOLERANCE_FOR_OVERLAPPING)
                return true;
            else return false;
        }
        return false;//standard return (non c'è overlap)
    }

    /*
     * Esempio di stringa: [4/11/2019 11.0 - 4/11/2019 13.0] Esempio di stringa:
     * [10/11/2019 11.15 - 10/11/2019 23.45]
     */
    @Override
    public String toString() {//è altamente consigliato STRINGBUFFER (java.lang) e metodo append()
        int yearStart, yearStop;
        String inizio, fine, range;

        Date start = this.start.getTime();
        Date stop = this.stop.getTime();

        yearStart = start.getYear() + 1900;
        yearStop = start.getYear() + 1900;

        inizio = start.getDate() + "/" + (start.getMonth()+1) + "/" + yearStart + " " + start.getHours() + "." + start.getMinutes();
        fine = stop.getDate() + "/" + (stop.getMonth()+1) + "/" + yearStop + " " + stop.getHours() + "." + stop.getMinutes();

        range = "[" + inizio + " - " + fine + "]";

        return range;
    }

}
