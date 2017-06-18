package Business;

import Business.Interfaces.ILocation;
import DAL.Dao.Interfaces.IMarkersDao;
import DAL.Dao.MarkersDao;
import Model.Markers;
import com.google.maps.model.LatLng;

import java.util.List;

/**
 * Created by Ignacio on 03/06/2017.
 */
public class LocationService implements ILocation {
    private static IMarkersDao _serviceMarkers;

    public LocationService() {
        _serviceMarkers = new MarkersDao();
    }

    @Override
    public List<Markers> getNearMarkers(Markers markers) {
        return _serviceMarkers.getNearMarkers(new LatLng(markers.getLat(), markers.getLng()));
    }

    @Override
    public List<Markers> getMarkers(Markers markers) {
        return _serviceMarkers.getActiveMarkers(new LatLng(markers.getLat(), markers.getLng()));
    }
}
