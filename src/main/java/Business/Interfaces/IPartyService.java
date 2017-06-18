package Business.Interfaces;

import DAL.EntityDataBase.PartyDto;
import Model.Party;
import Model.User;

import java.util.List;

/**
 * Created by Miguel Angel on 18/06/2017.
 */
public interface IPartyService {

    Party registerCandidate(User candidate);

    Party getParty(User candidate);

    Party doAtack(Party party);

    List<PartyDto> getAllRegisters();
    List<PartyDto> getAllRegistersByUser(String name);
}
