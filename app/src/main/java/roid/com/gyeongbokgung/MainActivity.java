package roid.com.gyeongbokgung;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import net.daum.mf.map.api.CameraUpdateFactory;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapPointBounds;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    public static String API_KEY = "";
    private MapView mapView = null;
    private MapPoint currentLocation = null;

    @BindView(R.id.fab_current_point)
    FloatingActionButton mCurrentPoint;
    @BindView(R.id.fab_gps)
    FloatingActionButton mGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // MapView 띄우기
        mapView = new MapView(this);
        API_KEY = getString(R.string.kakao_app_key);
//        mapView.setDaumMapApiKey(API_KEY);

        RelativeLayout mapViewContainer = (RelativeLayout) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
        mapView.setMapViewEventListener((MapView.MapViewEventListener) this);
        mapView.setCurrentLocationEventListener((MapView.CurrentLocationEventListener) this);
    }

    // 기기의 현재 위치 정보를 받아 맵을 갱신
    @OnClick(R.id.fab_gps)
    void setCurrentLocation() {
        Toast.makeText(getBaseContext(), "현재 위치로 맵을 갱신합니다.", Toast.LENGTH_LONG).show();
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading);
        mapView.moveCamera(CameraUpdateFactory.newMapPoint(currentLocation));
    }

    // 마커 추가
    @OnClick(R.id.fab_current_point)
    void onShowMarker() {
        Toast.makeText(getBaseContext(), "마커를 표시합니다.", Toast.LENGTH_LONG).show();
//        int radius = 2500; // 반경 거리
//        // TODO:일정 범위 안에 있으면 마커를 표시
//        if(radius){
//
//        }
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
        List<MapPoint> mapPoints = new ArrayList<>();
        mapPoints.add(MapPoint.mapPointWithGeoCoord(37.580283, 126.97808179999993)); // JAGYEONGJEON
        mapPoints.add(MapPoint.mapPointWithGeoCoord(37.5678617, 126.98029859999997)); // JAGYEONGJEON
        mapPoints.add(MapPoint.mapPointWithGeoCoord(37.5790885, 126.97704399999998)); // SAJEONGJEON


        // 마커 좌표 지정
        for (int i = 0; i < mapPoints.size(); i++) {
            MapPOIItem maker = new MapPOIItem();
            MapPoint p = mapPoints.get(i);
            maker.setMapPoint(p);
            maker.setTag(i);
            mapView.addPOIItem(maker); // 마커 생성
        }


        // 지도뷰의 중심좌표와 줌레벨을 마커가 나오도록 조정
        MapPointBounds mapPointBounds = new MapPointBounds(mapPoints.get(0), mapPoints.get(1));
        int padding = 100; // px
        mapView.moveCamera(CameraUpdateFactory.newMapPointBounds(mapPointBounds, padding));
    }


}
