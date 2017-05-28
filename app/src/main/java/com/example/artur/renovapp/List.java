package com.example.artur.renovapp;

import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by artur on 19/05/2017.
 * UNUFOR - Universidade de Fortaleza   CCT - Centro de Ciências Tecnológicas
 * Engenharia Eletrônica                Per:    2017.1
 * T923    Estrutura de Dados           Prof:   Gilson Pereira do Carmo Filho
 * Aluno:  Artur Alves Torres de Araujo Mat:    1610674
 */

class NodeList {
    ////////////////////////////////////////
    //ATRIBUTOS
    ////////////////////////////////////////
    NodeList previousNode;
    NodeList nextNode;
    PlaceClass mPlace;
    MaterialClass mMaterial;

    ////////////////////////////////////////
    //CONSTRUTORES
    ////////////////////////////////////////
    NodeList(MaterialClass mMaterial) {//construtor de nós de lista de materiais
        this.previousNode = null;
        this.nextNode = null;
        this.mMaterial = mMaterial;
    }

    NodeList(PlaceClass mPlace) {//construtor de nós de lista de localidades
        this.previousNode = null;
        this.nextNode = null;
        this.mPlace = mPlace;
    }
}

class MyList {
    ////////////////////////////////////////
    //CONSTANTES
    ////////////////////////////////////////
    private static final String TAG = "MyList";
    ////////////////////////////////////////
    //ATRIBUTOS
    ////////////////////////////////////////
    String category;
    NodeList firstNode;
    NodeList lastNode;

    ////////////////////////////////////////
    //CONSTRUTORES
    ////////////////////////////////////////
    MyList() {
        this.firstNode = null;
        this.lastNode = null;
    }

    MyList(String category) {
        this.firstNode = null;
        this.lastNode = null;
        this.category = category;
    }

    ////////////////////////////////////////
    //MÉTODOS DE INSERÇÃO
    ////////////////////////////////////////
    void insertPlace(PlaceClass place){
        NodeList newNode = new NodeList(place);
        insert(newNode);
    }
    void insertMaterial(MaterialClass material){
        NodeList newNode = new NodeList(material);
        insert(newNode);
    }
    void insert(NodeList newNode){
        if(firstNode == null){
            firstNode = newNode;
            lastNode = newNode;
        }else {
            NodeList aux1 = getLastPosition();
            if (aux1 != null) {
                aux1.nextNode = newNode;
                newNode.previousNode = aux1;
                Log.e(TAG,"----Added a node to mPlacesList---------");
                Log.e(TAG,"Category:\t" + newNode.mPlace.category);
                Log.e(TAG,"Name:\t" + newNode.mPlace.placeName);
                Log.e(TAG,"Address:\t" + newNode.mPlace.placeAddress);
                Log.e(TAG,"----------------------------------------");
            }
        }
    }

    ////////////////////////////////////////
    //MÉTODOS DE BUSCA
    ////////////////////////////////////////
    void searchCategory(String category){
        //TODO: Retorna todos os itens de uma mesma categoria
    }
    PlaceClass searchPlace(String name){
        //TODO: Retorna todos os itens de uma mesma categoria
        return null;
    }
    MaterialClass searchMaterial(String name){
        //TODO: Retorna todos os itens de uma mesma categoria
        return null;
    }

    @Nullable
    private NodeList getLastPosition(){
        NodeList aux1 = firstNode;
        NodeList aux2 = null;
        if (aux1 != null) {
            while (aux1 != null) {
                aux2 = aux1;
                aux1 = aux1.nextNode;
            }
            return aux2;
        } else {
            Log.e(TAG,"Lista vazia.");
            return null;
        }
    }

    NodeList searchNode(String name){
        NodeList aux1 = firstNode;
        if (aux1 != null) {
            while ( ((!name.equals(aux1.mMaterial.materialName)) || (!name.equals(aux1.mPlace.placeName))) ){
                aux1 = aux1.nextNode;
                if(aux1 == null){
                    Log.e(TAG,"O item (" + name + ") não pertence à lista.");
                    return null;
                }
            }
            return aux1;
        } else {
            Log.e(TAG,"FAIL: Lista vazia");
            return null;
        }
    }

    ////////////////////////////////////////
    //MÉTODOS DE REMOÇÃO
    ////////////////////////////////////////
    void remove(String name){
        if (searchNode(name) != null) {
            NodeList aux1 = searchNode(name);
            NodeList aux2 = aux1.previousNode;
            NodeList aux3 = aux1.nextNode;
            aux2.nextNode = aux3;
            aux3.previousNode = aux2;
            Log.e(TAG,"SUCCESS: Item removido");
        } else {
            Log.e(TAG,"FAIL: Remoção não realizada.");
        }
    }
}