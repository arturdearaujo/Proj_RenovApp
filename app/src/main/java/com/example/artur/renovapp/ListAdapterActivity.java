package com.example.artur.renovapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by artur on 19/05/2017.
 * UNUFOR - Universidade de Fortaleza   CCT - Centro de Ciências Tecnológicas
 * Engenharia Eletrônica                Per:    2017.1
 * T923    Estrutura de Dados           Prof:   Gilson Pereira do Carmo Filho
 * Aluno:  Artur Alves Torres de Araujo Mat:    1610674
 */

public class ListAdapterActivity extends ArrayAdapter<PlaceClass> {

    ////////////////////////////////////////
    //ATRIBUTOS
    ////////////////////////////////////////
    private LayoutInflater mLayoutInflater;
    private ArrayList<PlaceClass> mPlaces;
    private int  mViewResourceId;
    String category;

    ////////////////////////////////////////
    //CONSTRUTORES
    ////////////////////////////////////////
    public ListAdapterActivity(Context context, int tvResourceId, ArrayList<PlaceClass> devices, String category){
        super(context, tvResourceId,devices);
        this.mPlaces = devices;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = tvResourceId;
        this.category = category;
    }

    public ListAdapterActivity(Context context, int tvResourceId, ArrayList<PlaceClass> devices){
        super(context, tvResourceId,devices);
        this.mPlaces = devices;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = tvResourceId;
    }

    ////////////////////////////////////////
    //MÉTODOS
    ////////////////////////////////////////
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mLayoutInflater.inflate(mViewResourceId, null);

        PlaceClass mList = mPlaces.get(position);

        if (mList != null) {
            TextView placeName = (TextView) convertView.findViewById(R.id.item_name);
            TextView placeAddress = (TextView) convertView.findViewById(R.id.item_address);

            if (placeName != null) {
                placeName.setText(mList.placeName);
            }
            if (placeAddress != null) {
                placeAddress.setText(mList.placeAddress);
            }
        }

        return convertView;
    }

}
