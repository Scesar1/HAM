package com.example.ham;

import static com.example.ham.Utils.readEncodedPolyLinePointsFromCSV;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class MapFragment extends Fragment implements OnMapReadyCallback, OnMapAndViewReadyListener.OnGlobalLayoutAndMapReadyListener {
    FusedLocationProviderClient client;
    private MainActivity myAct;
    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;
    LatLng userLatLng;
    int food;
    int dorm;
    int hall;
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            food = getArguments().getInt("bundleKey1", 0);
            dorm = getArguments().getInt("bundleKey2", 0);
            hall = getArguments().getInt("bundleKey3", 0);


        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Initialize view
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        myAct = (MainActivity) getActivity();
        setHasOptionsMenu(true);


        // Initialize map fragment
        SupportMapFragment mapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map);

        // Async map
        mapFragment.getMapAsync(this);

        // Return view
        return view;
    }

    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.bar_menu, menu);
        MenuItem item = menu.findItem(R.id.search);

        SearchView sv = new SearchView(((myAct.getSupportActionBar().getThemedContext())));
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        item.setActionView(sv);
        /*sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                .getFilter().filter(newText);
                return false;
            }
        });*/
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(myAct, R.raw.style_json));

        if (food == 1)
            drawHopkinsCafePolyLine();
        if (dorm == 1)
            drawAMRPolyLine();
        if (hall == 1)
            drawGilmanPolyLine();

        if (ContextCompat.checkSelfPermission(myAct, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
        } else {
            myAct.requestLocationPermission();
        }
        // When map is loaded
        LatLng mark1 = new LatLng(39.329071, -76.619175);
        googleMap.addMarker(new MarkerOptions().position(mark1).title("MSE Front Entrance").icon(BitmapDescriptorFactory.fromResource(R.drawable.accessible)));

        LatLng mark2 = new LatLng(39.329048, -76.619754);
        googleMap.addMarker(new MarkerOptions().position(mark2).title("MSE Back Entrance").icon(BitmapDescriptorFactory.fromResource(R.drawable.accessible)));

        LatLng mark3 = new LatLng(39.328644328919815, -76.61962244790644);
        googleMap.addMarker(new MarkerOptions().position(mark3).title("Brody Cafe Entrance").icon(BitmapDescriptorFactory.fromResource(R.drawable.accessible)));

        LatLng mark4 = new LatLng(39.32869661492257, -76.62000848829113);
        googleMap.addMarker(new MarkerOptions().position(mark4).title("Krieger Hall Entrance").icon(BitmapDescriptorFactory.fromResource(R.drawable.accessible)));

        LatLng mark5 = new LatLng(39.32963836357293, -76.62035402342596);
        googleMap.addMarker(new MarkerOptions().position(mark5).title("Remsen Hall Entrance").icon(BitmapDescriptorFactory.fromResource(R.drawable.accessible)));

        LatLng mark6 = new LatLng(39.328991990969776, -76.62119339984896);
        googleMap.addMarker(new MarkerOptions().position(mark6).title("Gilman Hall Entrance").icon(BitmapDescriptorFactory.fromResource(R.drawable.accessible)));

        LatLng mark7 = new LatLng(39.32824700125401, -76.61937276900144);
        googleMap.addMarker(new MarkerOptions().position(mark7).title("Brody Commons Side Entrance").icon(BitmapDescriptorFactory.fromResource(R.drawable.accessible)));

        locationManager = (LocationManager) myAct.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                userLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(userLatLng).title("Your Location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(userLatLng));
            }
        };
    }

    private void drawAMRPolyLine() {
        // Add a blue Polyline.
        mMap.addPolyline(new PolylineOptions()
                .color(getResources().getColor(R.color.colorPolyLineBlue)) // Line color.
                .width(20) // Line width.
                .clickable(false) // Able to click or not.
                .addAll(readEncodedPolyLinePointsFromCSV(myAct, "lineBlue"))); // all the whole list of lat lng value pairs which is retrieved by calling helper method readEncodedPolyLinePointsFromCSV.

    }

    private void drawHopkinsCafePolyLine() {
        mMap.addPolyline(new PolylineOptions()
                .color(getResources().getColor(R.color.colorPolyLineRed))
                .width(20) // Line width.
                .clickable(false) // Able to click or not.
                .addAll(readEncodedPolyLinePointsFromCSV(myAct, "lineRed")));
    }

    private void drawGilmanPolyLine() {
        mMap.addPolyline(new PolylineOptions()
                .color(getResources().getColor(R.color.colorPolyLineGreen))
                .width(20) // Line width.
                .clickable(false) // Able to click or not.
                .addAll(readEncodedPolyLinePointsFromCSV(myAct, "lineGreen")));
    }
}
