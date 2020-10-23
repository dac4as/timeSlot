package it.unicam.cs.asdl2021.es4;

import java.util.Objects;

/**
 * Una prenotazione riguarda una certa aula per un certo time slot.
 * 
 * @author Template: Luca Tesei, Implementation: Collective
 *
 */
public class Prenotazione implements Comparable<Prenotazione> {

    private final Aula aula;

    private final TimeSlot timeSlot;

    private final String docente;

    private final String motivo;

    /**
     * Costruisce una prenotazione.
     * 
     * @param aula
     *                     l'aula a cui la prenotazione si riferisce
     * @param timeSlot
     *                     il time slot della prenotazione
     * @param docente
     *                     il nome del docente che ha prenotato l'aula
     * @param motivo
     *                     il motivo della prenotazione
     * @throws NullPointerException
     *                                  se uno qualsiasi degli oggetti passati è
     *                                  null
     */
    public Prenotazione(Aula aula, TimeSlot timeSlot, String docente,
            String motivo) {
        if(aula==null||timeSlot==null||docente==null||motivo==null)
            throw new NullPointerException();
        else {
            this.aula = aula;
            this.timeSlot = timeSlot;
            this.docente = docente;
            this.motivo = motivo;
        }
    }

    /**
     * @return the aula
     */
    public Aula getAula() {
        return aula;
    }

    /**
     * @return the timeSlot
     */
    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    /**
     * @return the docente
     */
    public String getDocente() {
        return docente;
    }

    /**
     * @return the motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /*
     * Ridefinire in accordo con equals
     */
    @Override
    public int hashCode() {
        return Objects.hash(aula, timeSlot);
    }

    /*
     * L'uguaglianza è data solo da stessa aula e stesso time slot. Non sono
     * ammesse prenotazioni diverse con stessa aula e stesso time slot.
     */
    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj==null|| getClass() != obj.getClass()) return false;
        Prenotazione prenotazione = (Prenotazione) obj;
        return aula.equals(prenotazione.aula) && timeSlot.equals(prenotazione.timeSlot);
    }

    /*
     * Una prenotazione precede un altra in base all'ordine dei time slot. Se
     * due prenotazioni hanno lo stesso time slot allora una precede l'altra in
     * base all'ordine tra le aule.
     */
    @Override
    public int compareTo(Prenotazione o) {
        if(this.getAula().equals(o.getAula()))//se l'aula è diversa, non c'è rischio di eventuali overlap. D'altro canto se è uguale, bisogna differenziare i timeSlot
        {
            if (this.getTimeSlot().getStop().before(o.getTimeSlot().getStart()))//se this prenotazione precede (<) o, allora restituisco -1
                return -1;
            else if (o.getTimeSlot().getStop().before(this.getTimeSlot().getStart()))
                return 1;
        }
        else if (this.getAula()!=o.getAula())
            return -1;
        return 0;
    }

    @Override
    public String toString() {
        return "Prenotazione [aula = " + aula + ", time slot =" + timeSlot
                + ", docente=" + docente + ", motivo=" + motivo + "]";
    }

}
