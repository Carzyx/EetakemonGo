package Controller;

import Controller.Interfaces.ILocation;
import Dao.Interfaces.IMarkersDao;
import Dao.MarkersDao;
import Model.Eetakemon;
import Model.EetakemonType;
import Model.Markers;
import com.google.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ignacio on 03/06/2017.
 */
public class LocationService implements ILocation {
    private static IMarkersDao _serviceMarkers;

    public LocationService(){
        _serviceMarkers=new MarkersDao();
    }
    @Override
    public List<Markers> getMarkers(Markers markers) {
        return _serviceMarkers.getActiveMarkers(new LatLng(markers.getLat(),markers.getLng()));}
}
