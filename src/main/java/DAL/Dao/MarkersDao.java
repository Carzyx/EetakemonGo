package DAL.Dao;

import DAL.Dao.Interfaces.IGenericDao;
import DAL.Dao.Interfaces.IMarkersDao;
import DAL.EntityDataBase.EetakemonDto;
import DAL.EntityDataBase.MarkersDto;
import DAL.Dao.Interfaces.IEetakemonDao;
import Model.Eetakemon;
import Model.EetakemonType;
import Model.Markers;
import com.google.maps.model.Distance;
import com.google.maps.model.LatLng;
import com.google.maps.model.LocationType;
import org.modelmapper.ModelMapper;

import javax.xml.stream.Location;
import java.util.*;

/**
 * Created by Ignacio on 11/06/2017.
 */
public class MarkersDao implements IMarkersDao {


    private static IGenericDao<MarkersDto> _service;

    public MarkersDao(){
        _service = new GenericDaoImpl<>();
    }

    @Override
    public boolean add(MarkersDto item) {
        return false;
    }

    @Override
    public boolean updateByName(MarkersDto newItem) {
        return false;
    }

    @Override
    public boolean removeByName(MarkersDto item) {
        return false;
    }

    @Override
    public MarkersDto getByName(String name) {
        return null;
    }

    @Override
    public List<MarkersDto> getAll() {
        return _service.getAll(new MarkersDto());
    }
}
