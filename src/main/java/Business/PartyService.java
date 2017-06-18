package Business;

import Business.Interfaces.IPartyService;
import DAL.Dao.Interfaces.IPartyDao;
import DAL.Dao.PartyDao;
import DAL.EntityDataBase.PartyDto;
import Model.Atack;
import Model.Eetakemon;
import Model.Party;
import Model.User;
import com.mysql.jdbc.StringUtils;
import org.joda.time.DateTime;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by Miguel Angel on 18/06/2017.
 */
public class PartyService implements IPartyService {

    private List<User> usersPendingToFight;
    private List<Party> partiesInCurse;
    private static IPartyDao _servicePartyDao;

    public PartyService() {
        usersPendingToFight = new ArrayList<>();
        partiesInCurse = new ArrayList<>();
        _servicePartyDao = new PartyDao();
    }

    @Override
    public Party registerCandidate(User candidate) {
        usersPendingToFight.add(candidate);
        if (usersPendingToFight.size() > 1) {
            prepareParty(candidate);
        }

        return findMyParty(candidate);
    }

    @Override
    public Party getParty(User candidate) {

        return findMyParty(candidate);
    }

    @Override
    public Party doAtack(Party party) {
        if (party.getAtack() == null || party.getAtack().isEmpty()) {
            return null;
        }

        if (party.getTurnIndication().get(party.getCandidate1().getUsername())) {
            Eetakemon eetakemon = party.getCandidate1().getEetakemons().get(0);
            Atack atack = getAtack(eetakemon, party.getAtack());
            int finalDamage = calculateAtack(eetakemon.getLevel(), atack.getDamageBase());

            eetakemon.setPs(eetakemon.getPs() - finalDamage);

        }
        if (party.getTurnIndication().get(party.getCandidate2().getUsername())) {
            Eetakemon eetakemon = party.getCandidate2().getEetakemons().get(0);
            Atack atack = getAtack(eetakemon, party.getAtack());
            int finalDamage = calculateAtack(eetakemon.getLevel(), atack.getDamageBase());

            eetakemon.setPs(eetakemon.getPs() - finalDamage);
        }

        boolean isCandidate1Turn = party.getTurnIndication().get(party.getCandidate1().getUsername());
        party.getTurnIndication().put(party.getCandidate1().getUsername(), !isCandidate1Turn);
        party.getTurnIndication().put(party.getCandidate2().getUsername(), isCandidate1Turn);

        party = checkWiner(party);
        updateParty(party);

        if(!StringUtils.isNullOrEmpty(party.getCandidateWiner()))
        {
            addRegisterGame(party);
        }
        return party;
    }

    @Override
    public List<PartyDto> getAllRegisters() {
        return _servicePartyDao.getAll();
    }

    @Override
    public List<PartyDto> getAllRegistersByUser(String name) {
        return _servicePartyDao.getAllByConditions(name);
    }

    private boolean addRegisterGame(Party party)
    {
        return _servicePartyDao.add(party);
    }

    private Party checkWiner(Party party) {
        if (party.getCandidate1().getEetakemons().get(0).getPs() <= 0) {
            party.setCandidateWiner(party.getCandidate2().getUsername());
            party.setDateEnd(new DateTime());
        }
        if (party.getCandidate2().getEetakemons().get(0).getPs() <= 0) {

            party.setCandidateWiner(party.getCandidate1().getUsername());
            party.setDateEnd(new DateTime());
        }
        return party;
    }

    private void updateParty(Party party) {
        int index = partiesInCurse.indexOf(findMyParty(party.getCandidate1()));
        partiesInCurse.set(index, party);
    }

    private Atack getAtack(Eetakemon eetakemon, String atackSelected) {
        for (Atack atack : eetakemon.getAtacks()) {

            if (atack.getName().equals(atackSelected)) {
                return atack;
            }
        }
        return eetakemon.getAtacks().get(0);
    }

    private int calculateAtack(int level, int damageBase) {
        Random rand = new Random();
        return (int) (damageBase * (level * rand.nextDouble()));
    }

    private void createParty(User candidate1, User candidate2) {
        Party party = new Party();
        party.setCandidate1(candidate1);
        party.setCandidate2(candidate2);

        Map<String, Boolean> turnIndication = new HashMap<>();
        turnIndication.put(candidate1.getUsername(), true);
        turnIndication.put(candidate2.getUsername(), false);

        party.setTurnIndication((HashMap<String, Boolean>) turnIndication);
        party.setDateStart(new DateTime());
        partiesInCurse.add(party);
    }

    private List<User> getCandidatesToFight(User candidate) {
        List<User> usersCandidate = new ArrayList<>();
        for (User user : usersPendingToFight) {
            if (!user.getUsername().equals(candidate.getUsername())) {
                usersCandidate.add(user);
            }
        }
        return usersCandidate;
    }

    private void prepareParty(User candidate) {
        List<User> usersCandidate = getCandidatesToFight(candidate);
        if (usersCandidate.size() > 0) {
            createParty(candidate, usersCandidate.get(0));
            usersPendingToFight.remove(usersCandidate.get(0));
            usersPendingToFight.remove(candidate);
        }
    }

    private Party findMyParty(User candidate) {
        for (Party party : partiesInCurse) {
            if (party.getCandidate1().getUsername().equals(candidate.getUsername()) ||
                party.getCandidate2().getUsername().equals(candidate.getUsername())) {
                return party;
            }
        }
        return null;
    }
}
