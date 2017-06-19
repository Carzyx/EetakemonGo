package Business;

import Business.Interfaces.ILocation;
import DAL.Dao.EetakemonDao;
import DAL.Dao.GenericDaoImpl;
import DAL.Dao.Interfaces.IEetakemonDao;
import DAL.Dao.Interfaces.IMarkersDao;
import DAL.Dao.MarkersDao;
import DAL.EntityDataBase.MarkersDto;
import Model.Eetakemon;
import Model.Markers;
import com.google.maps.model.LatLng;
import org.modelmapper.ModelMapper;

import java.util.*;

/**
 * Created by Ignacio on 03/06/2017.
 */
public class LocationService implements ILocation {
    private static IMarkersDao _serviceMarkers;
    private static int radio=500000000;//Distancia en m de radio
    private static int time=300000;//Se actualiza cada 10min
    private static IEetakemonDao _seriveceEeteckemon;
    private ModelMapper modelMapper = new ModelMapper();
    private List<Markers>activeMarkers;
    private static LocationService instance = null;
    private TimerTask timerTask;
    private  static Timer timer;

    private void LocationService(){
        timer=new Timer();
        _serviceMarkers=new MarkersDao();
        _seriveceEeteckemon=new EetakemonDao();
        timerTask=new TimerTask() {
            @Override
            public void run() {
                actualizarMarkers();
            }
        };
        timer.schedule(timerTask,0,time);
    }
    public static LocationService getInstance() {
        if(instance == null) {
            instance = new LocationService();
        }
        return instance;
    }
    public LocationService(){
        _serviceMarkers=new MarkersDao();
    }
    @Override
    public List<Markers> getNearMarkers(Markers markers) {
        List<Markers>markersList=new ArrayList<Markers>();
        for (Markers marker:activeMarkers) {
            int i=(int)distFrom(markers.getLat(),markers.getLng(),marker.getLat(),marker.getLng());
            if (i<=radio) {
                markersList.add(marker);
            }
        }
        return markersList;}

    @Override
    public List<Markers> getMarkers(Markers markers) {
        return activeMarkers;
    }
    private void actualizarMarkers(){
        activeMarkers=new ArrayList<Markers>();
        MarkersDto markersDto=new MarkersDto();
        List<MarkersDto>markersDtos=_serviceMarkers.getAll();
        List<Eetakemon>eetakemons=_seriveceEeteckemon.getAllEetackemonsComplete();
        List<Eetakemon> eetakemonList=new ArrayList<>();
        for (Eetakemon eetakemon:eetakemons) {
            eetakemonList.add(_seriveceEeteckemon.getCompleteEetakemonByName(eetakemon.getName()));
        }

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
        System.out.println(" ");
    }
    private boolean verificarMarker(Markers markers) {
        for (int i=0;i<activeMarkers.size();i++){
            if ((activeMarkers.get(i).getLat()==markers.getLat())&&(activeMarkers.get(i).getLng()==markers.getLng()))
                return false;
        }
        return true;
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
