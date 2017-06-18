package DAL.Dao;

import DAL.Dao.Interfaces.IGenericDao;
import DAL.Dao.Interfaces.IPartyDao;
import DAL.EntityDataBase.PartyDto;
import Model.Party;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Miguel Angel on 18/06/2017.
 */
public class PartyDao implements IPartyDao{

    private static IGenericDao<PartyDto> _service;

    public PartyDao()
    {
        _service = new GenericDaoImpl<>();
    }


    public boolean add(Party item) {
        PartyDto party = new PartyDto(item.getCandidate1().getUsername(), item.getCandidate2().getUsername(), item.getDateStart(), item.getDateEnd(), item.getCandidateWiner());
        return _service.add(party);
    }


    public List<PartyDto> getAll() {

        return _service.getAll(new PartyDto());
    }

    public List<PartyDto> getAllByConditions(String name)
    {
        List<PartyDto> result = new ArrayList<>();
        PartyDto party1 = new PartyDto();
        party1.setCandidate1(name);
        Hashtable<String, String> conditions1 = new Hashtable<>();
        conditions1.put("candidate1", name);
        result.addAll((Collection<? extends PartyDto>) _service.getByParameters(party1, conditions1));

        PartyDto party2 = new PartyDto();
        party2.setCandidate2(name);
        Hashtable<String, String> conditions2 = new Hashtable<>();
        conditions2.put("candidate2", name);
        result.addAll((Collection<? extends PartyDto>) _service.getByParameters(party1, conditions2));

        return result;
    }
}
