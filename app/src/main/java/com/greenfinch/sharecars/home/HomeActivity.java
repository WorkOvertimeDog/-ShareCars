package com.greenfinch.sharecars.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.google.zxing.activity.CaptureActivity;
import com.greenfinch.sharecars.R;
import com.greenfinch.sharecars.base.activity.BaseActivity;
import com.greenfinch.sharecars.utils.CommonUtil;
import com.greenfinch.sharecars.me.MeActivity;

/**
 * Created by Admin on 2017/9/9.
 */
public class HomeActivity extends BaseActivity {

    private MapView mMapView = null;
    private BaiduMap mBaiduMap = null;//BaiduMap对象
    private LocationClient locationClient;//百度地图的定位对象
    private RelativeLayout mapView;//MapView的载体布局
    private View infoView;//地图上点击标记物，显示更多信息的View布局
    private boolean isShow = false;//是够显示显示更多信息 默认不显示
    private RelativeLayout scanImage, meImage;

    //默认设置为北京天安门的位置
    private double eLon1 = 116.400244;//目标位置的经纬度
    private double eLat1 = 39.963175;

    //打开扫描界面请求码
    private int REQUEST_CODE = 0x01;
    //扫描成功返回码
    private int RESULT_OK = 0xA1;

    @Override
    public int setUpContentView() {
        return R.layout.home_activity_layout;
    }

    @Override
    public void setUpView() {
        mapView = (RelativeLayout) findViewById(R.id.baidu_mapview);
        scanImage = (RelativeLayout) findViewById(R.id.scanning);
        meImage = (RelativeLayout) findViewById(R.id.me);
        //扫描二维码
        scanImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CommonUtil.isCameraCanUse()){
                    Intent intent = new Intent(HomeActivity.this, CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                }else{
                    Toast.makeText(HomeActivity.this,"请打开此应用的摄像头权限！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        meImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        initBaiDuMap();
        locate();

        LatLng point1 = new LatLng(eLat1, eLon1);
        LatLng point2 = new LatLng(39.86923, 116.397428);
        addMark(point1);
        addMark(point2);
    }

    //初始化百度地图
    private void initBaiDuMap() {
        BaiduMapOptions options = new BaiduMapOptions();
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.zoom(10);//设置初始显示的地图比例

        MapStatus status = builder.build();
        options.mapStatus(status);
        options.scaleControlEnabled(false);//隐藏地图上比例尺
        options.zoomControlsEnabled(false);// 隐藏地图缩放控件

        mMapView = new MapView(this, options);

        //隐藏百度地图的百度logo
        View child = mMapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)){
            child.setVisibility(View.INVISIBLE);
        }

        mMapView.setMapCustomEnable(true);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mapView.addView(mMapView, params);

        mBaiduMap = mMapView.getMap();//获得BaiduMap控件
    }

    //获取当前位置
    private void locate() {
        locationClient = new LocationClient(getApplicationContext());
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式
        option.setCoorType("gcj02");// 返回的定位结果是百度经纬度，默认值
        option.setScanSpan(5000);// 设置发起定位请求的间隔时间为5000ms
        option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
        option.setNeedDeviceDirect(true);// 返回的定位结果包含手机机头的方向
        locationClient.setLocOption(option);
        locationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation result) {
                if (result != null) {
                    MyLocationData data = new MyLocationData.Builder().latitude(result.getLatitude()).longitude(result.getLongitude()).build();
                    mBaiduMap.setMyLocationData(data);
                }else{
                    Toast.makeText(HomeActivity.this, "网络不好，请退出后重试！", Toast.LENGTH_LONG).show();
                }
            }
        });
//        BitmapDescriptor geo = BitmapDescriptorFactory.fromResource(R.mipmap.station_position);
//        MyLocationConfiguration configuration = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, geo);
//        mBaiduMap.setMyLocationConfigeration(configuration);// 设置定位模式
//        MapStatusUpdate u = MapStatusUpdateFactory.zoomTo(7);
//        mBaiduMap.animateMapStatus(u);
        mBaiduMap.setMyLocationEnabled(true);// 打开定位图层

    }

    //添加标记物
    private void addMark(LatLng point){
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
//        LatLng point = new LatLng(eLat, eLon);
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.station_position);
        OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
        mBaiduMap.addOverlay(option);
        final InfoWindow currentInfoWindow = new InfoWindow(getInfoWindoView(),point, -77);
//        mBaiduMap.showInfoWindow(currentInfoWindow);
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
//                if (isShow){
////                    LatLng latLng =marker.getPosition();
////                    InfoWindow currentInfoWindow = new InfoWindow(getInfoWindoView(),latLng, -77);
//                    mBaiduMap.hideInfoWindow();
//                    isShow = false;
//                }else{
//                    mBaiduMap.showInfoWindow(currentInfoWindow);
//                    isShow = true;
//                }
                LatLng markPoint = marker.getPosition();
                InfoWindow markPointInfo = new InfoWindow(getInfoWindoView(), markPoint, -77);
                mBaiduMap.showInfoWindow(markPointInfo);
                return false;
            }
        });
    }

    //获取标记物的布局
    private View getInfoWindoView(){
        if (null == infoView) {
            infoView = (ViewGroup) LayoutInflater.from(HomeActivity.this).inflate(R.layout.more_info_view, null);
        }
        return  infoView;
    }

    @Override
    protected void onStart() {
        super.onStart();
        locationClient.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
        locationClient.stop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { //RESULT_OK = -1
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("qr_scan_result");
            //将扫描出的信息显示出来
            Toast.makeText(HomeActivity.this, scanResult, Toast.LENGTH_LONG).show();
            Log.i("luke", scanResult);
        }
    }

    @Override
    protected void doAdd() {

    }
}
