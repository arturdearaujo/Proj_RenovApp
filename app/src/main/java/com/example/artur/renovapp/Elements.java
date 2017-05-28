package com.example.artur.renovapp;

/**
 * Created by artur on 19/05/2017.
 * UNUFOR - Universidade de Fortaleza   CCT - Centro de Ciências Tecnológicas
 * Engenharia Eletrônica                Per:    2017.1
 * T923    Estrutura de Dados           Prof:   Gilson Pereira do Carmo Filho
 * Aluno:  Artur Alves Torres de Araujo Mat:    1610674
 */

class PlaceClass{
    ////////////////////////////////////////
    //ATRIBUTOS
    ////////////////////////////////////////
    String category;
    String placeName;
    String placeAddress;
    int phoneNumber;
    MyList materials;

    ////////////////////////////////////////
    //CONSTRUTORES
    ////////////////////////////////////////
    public PlaceClass(String category, String placeName) {
        this.category = category;
        this.placeName = placeName;
    }

    public PlaceClass(String category, String placeName, String placeAddress) {
        this.category = category;
        this.placeName = placeName;
        this.placeAddress = placeAddress;
    }

    public PlaceClass(String category, String placeName, String placeAddress, int phoneNumber) {
        this.category = category;
        this.placeName = placeName;
        this.placeAddress = placeAddress;
        this.phoneNumber = phoneNumber;
    }
}

class MaterialClass {
    ////////////////////////////////////////
    //ATRIBUTOS
    ////////////////////////////////////////
    String materialName;
    String description;
    double price;

    ////////////////////////////////////////
    //CONSTRUTORES
    ////////////////////////////////////////
    public MaterialClass(String materialName) {//CONSTRUTOR MÍNIMO
        this.materialName = materialName;
    }

    public MaterialClass(String materialName, String description, int price) {//CONSTRUTOR COMPLETO
        this.materialName = materialName;
        this.description = description;
        this.price = price;
    }
}