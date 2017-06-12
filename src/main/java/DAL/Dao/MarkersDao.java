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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Ignacio on 11/06/2017.
 */
public class MarkersDao implements IMarkersDao{
    private static int radio=500000000;
    private static IGenericDao<MarkersDto> _service;
    private static IEetakemonDao _seriveceEeteckemon;
    private ModelMapper modelMapper = new ModelMapper();
    private List<Markers>activeMarkers;
    public MarkersDao(){
        _service = new GenericDaoImpl<>();
        _seriveceEeteckemon=new EetakemonDao();
        actualizarMarkers();
    }
    private void actualizarMarkers(){
        activeMarkers=new ArrayList<Markers>();
        MarkersDto markersDto=new MarkersDto();
        EetakemonDto eetakemonDto=new EetakemonDto();
        List<MarkersDto>markersDtos=_service.getAll(markersDto);
        List<Eetakemon> eetakemonList=_seriveceEeteckemon.getAll();
        Markers markers=new Markers();
        Random r=new Random();
        int i=0;
        System.out.println("Markers Activos:");
        while (i<10){
            markers=new Markers();
            modelMapper.map(markersDtos.get(r.nextInt(10)),markers);
            markers.setEetakemon(eetakemonList.get(r.nextInt(eetakemonList.size())));
            if(verificarMarker(markers)){
                activeMarkers.add(markers);
                System.out.println("Lat:"+markers.getLat()+" "+"Lng:"+markers.getLng());
                i++;
            }
        }
    }
    private boolean verificarMarker(Markers markers) {
        for (int i=0;i<activeMarkers.size();i++){
            if ((activeMarkers.get(i).getLat()==markers.getLat())&&(activeMarkers.get(i).getLng()==markers.getLng()))
                return false;
        }
        return true;
    }

    @Override
    public List<Markers> getActiveMarkers(LatLng latLng) {
        return activeMarkers;
    }

    @Override
    public List<Markers> getNearMarkers(LatLng latLng) {
        List<Markers>markersList=new ArrayList<Markers>();
        for (Markers marker:activeMarkers) {
            int i=(int)distFrom(latLng.lat,latLng.lng,marker.getLat(),marker.getLng());
            if (i<=radio) {
                markersList.add(marker);
            }
        }
        return markersList;
    }
    public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
            Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = (double) (earthRadius * c);
        return dist;
    }


}
