package Business.Interfaces;

import Model.Party;
import Model.User;

/**
 * Created by Miguel Angel on 18/06/2017.
 */
public interface IPartyService {

    Party registerCandidate(User candidate);

    Party getParty(User candidate);

    Party doAtack(Party party);
}
