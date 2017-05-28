package com.example.artur.renovapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by artur on 14/05/2017.
 * UNUFOR - Universidade de Fortaleza   CCT - Centro de Ciências Tecnológicas
 * Engenharia Eletrônica                Per:    2017.1
 * T923    Estrutura de Dados           Prof:   Gilson Pereira do Carmo Filho
 * Aluno:  Artur Alves Torres de Araujo Mat:    1610674
 */

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.artur.renovapp.MESSAGE";
    public final String TAG = "Main Activity";
    DecisionTree tree = new DecisionTree();
    MyList mPlaceList = new MyList();
    static String categorySelector = "";
    ListView mListItems;
    ListAdapterActivity mListAdapterActivity;
    public ArrayList<PlaceClass> mPlacesArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG,"OnCreate executado");
        fillTreeFromAsset();
        mListItems = (ListView) findViewById(R.id.list_of_places);
    }

    ////////////////////////////////////////
    //MÉTODOS DA ÁRVORE
    ////////////////////////////////////////
    void fillTreeFromAsset(){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("questions.txt")));
            String line;
            do{
                line = reader.readLine();
                if(line != null){
                    //(int,int,String,int)
                    String[] value = line.split(",");
                    if(tree.rootNodeTree==null){
                        tree.createRoot(Integer.parseInt(value[1]),value[2]);
                    }else {
                        tree.addNode(Integer.parseInt(value[0]),Integer.parseInt(value[1]),value[2],Integer.parseInt(value[3]));
                    }
                }
            }while (line != null);
            Log.e(TAG,"Árvore criada com sucesso");

        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
    }

    ////////////////////////////////////////
    //MÉTODOS DAS LISTAS
    ////////////////////////////////////////
    String getListName(String categorySelector){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("categories.txt")));
            String line;
            do{
                line = reader.readLine();
                if(line != null){
                    //(int,int,String,int)
                    String[] value = line.split(",");
                    if(value[0].equals(categorySelector)){
                        return value[1];
                    }

                }
            }while (line != null);
            Log.e(TAG,"");

        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        return null;
    }

    void fillListFromAsset(String categorySelector){
        BufferedReader reader = null;
        try {
            TextView disclaimerTextView = (TextView) findViewById(R.id.view_questions);
            reader = new BufferedReader(new InputStreamReader(getAssets().open(getListName(categorySelector))));
            String line;
            do{
                line = reader.readLine();
                if(line != null && !categorySelector.equals("Sobre Rejeitos:")){
                String[] value = line.split(",");
                mPlacesArrayList.add(new PlaceClass(value[0],value[1],value[2]));
                }
            }while (line != null);
            if (categorySelector.equals("Sobre Rejeitos:")) {
                callSubActivity();
            }
            Log.e(TAG,"");

        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
    }

    ////////////////////////////////////////
    //SUB-ACTIVITIES
    ////////////////////////////////////////
    void callSubActivity(){
        Intent intent = new Intent(this, SubActivity.class);

        intent.putExtra(EXTRA_MESSAGE, "teste ddddd");
        startActivity(intent);
    }

    ////////////////////////////////////////
    //BOTÕES
    ////////////////////////////////////////
    public void onClickYes(View view) {
        if (tree.savedNodeTree !=null) {
            tree.resolver("Yes");
            TextView quantityTextView = (TextView) findViewById(R.id.view_questions);
            quantityTextView.setText(tree.toDisplayInTextView);

            if(tree.savedNodeTree.yesBranch == null && tree.savedNodeTree.noBranch == null){
                categorySelector = tree.savedNodeTree.questOrAns;
                //IntentFilter placeListIntent = new IntentFilter();
                //registerReceiver(mBroadcastReceiver, placeListIntent);
                mPlacesArrayList.clear();
                fillListFromAsset(categorySelector);
                //fillArrayList(categorySelector);
                mListAdapterActivity = new ListAdapterActivity(this, R.layout.activity_list_adapter, mPlacesArrayList);
                mListItems.setAdapter(mListAdapterActivity);
            }
        }
    }

    public void onClickNo(View view) {
        if (tree.savedNodeTree !=null) {
            tree.resolver("No");
            TextView quantityTextView = (TextView) findViewById(R.id.view_questions);
            quantityTextView.setText(tree.toDisplayInTextView);

            if(tree.savedNodeTree.yesBranch == null && tree.savedNodeTree.noBranch == null){
                categorySelector = tree.savedNodeTree.questOrAns;
                //IntentFilter placeListIntent = new IntentFilter();
                //registerReceiver(mBroadcastReceiver, placeListIntent);
                mPlacesArrayList.clear();
                fillListFromAsset(categorySelector);
                //fillArrayList(categorySelector);
                mListAdapterActivity = new ListAdapterActivity(this, R.layout.activity_list_adapter, mPlacesArrayList);
                mListItems.setAdapter(mListAdapterActivity);
            }
        }
    }

    public void onClickStart(View view) {
        tree.queryBinTree();
        TextView quantityTextView = (TextView) findViewById(R.id.view_questions);
        quantityTextView.setText(tree.toDisplayInTextView);
        mListItems.setAdapter(null);
    }

}
