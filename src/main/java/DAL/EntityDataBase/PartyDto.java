package DAL.EntityDataBase;

import Model.Party;
import Model.User;
import org.joda.time.DateTime;

import java.sql.Date;
import java.util.HashMap;

/**
 * Created by Miguel Angel on 18/06/2017.
 */
public class PartyDto {

    private String candidate1;
    private String candidate2;
    private Date dateStart;
    private Date dateEnd;
    private String candidateWiner;

    public PartyDto(){}

    public PartyDto(String candidate1, String candidate2, Date dateStart, Date dateEnd, String candidateWiner)
    {
        this.candidate1 = candidate1;
        this.candidate2 = candidate2;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.candidateWiner = candidateWiner;
    }

    public String getCandidate1() {
        return candidate1;
    }

    public void setCandidate1(String candidate1) {
        this.candidate1 = candidate1;
    }

    public String getCandidate2() {
        return candidate2;
    }

    public void setCandidate2(String candidate2) {
        this.candidate2 = candidate2;
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
