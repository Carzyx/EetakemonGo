package Business.Interfaces;

import Model.Markers;

import java.util.List;

/**
 * Created by Ignacio on 03/06/2017.
 */
public interface ILocation {
    List<Markers> getMarkers(Markers markers);

    List<Markers> getNearMarkers(Markers markers);
}
