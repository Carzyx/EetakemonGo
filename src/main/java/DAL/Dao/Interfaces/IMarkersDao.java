package DAL.Dao.Interfaces;

import Model.Markers;
import com.google.maps.model.LatLng;

import java.util.List;

/**
 * Created by Ignacio on 11/06/2017.
 */
public interface IMarkersDao {
    List<Markers> getActiveMarkers(LatLng latLng);

}
