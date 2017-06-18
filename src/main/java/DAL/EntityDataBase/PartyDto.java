package DAL.EntityDataBase;

import Model.Party;
import Model.User;
import org.joda.time.DateTime;

import java.util.HashMap;

/**
 * Created by Miguel Angel on 18/06/2017.
 */
public class PartyDto {

    private String candidate1;
    private String candidate2;
    private DateTime dateStart;
    private DateTime dateEnd;
    private String candidateWiner;

    public PartyDto(){}

    public PartyDto(String candidate1, String candidate2, DateTime dateStart, DateTime dateEnd, String candidateWiner)
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

    public DateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(DateTime dateStart) {
        this.dateStart = dateStart;
    }

    public DateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(DateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getCandidateWiner() {
        return candidateWiner;
    }

    public void setCandidateWiner(String candidateWiner) {
        this.candidateWiner = candidateWiner;
    }
}
