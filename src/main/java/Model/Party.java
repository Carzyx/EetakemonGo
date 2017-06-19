package Model;

import org.joda.time.DateTime;

import java.sql.Date;
import java.util.HashMap;

/**
 * Created by Miguel Angel on 18/06/2017.
 */
public class Party {

    private User candidate1;
    private User candidate2;
    private HashMap<String, Boolean> turnIndication;
    private String atack;
    private Date dateStart;
    private Date dateEnd;
    private String candidateWiner;

    public User getCandidate1() {
        return candidate1;
    }

    public void setCandidate1(User candidate1) {
        this.candidate1 = candidate1;
    }


    public User getCandidate2() {
        return candidate2;
    }

    public void setCandidate2(User candidate2) {
        this.candidate2 = candidate2;
    }

    public HashMap<String, Boolean> getTurnIndication() {
        return turnIndication;
    }

    public void setTurnIndication(HashMap<String, Boolean> turnIndication) {
        this.turnIndication = turnIndication;
    }

    public String getAtack() {
        return atack;
    }

    public void setAtack(String atack) {
        this.atack = atack;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getCandidateWiner() {
        return candidateWiner;
    }

    public void setCandidateWiner(String candidateWiner) {
        this.candidateWiner = candidateWiner;
    }
}
