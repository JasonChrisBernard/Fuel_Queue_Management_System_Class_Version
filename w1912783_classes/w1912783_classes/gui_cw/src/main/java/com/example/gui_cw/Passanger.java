package com.example.gui_cw;

public class Passanger {

    private String FirstName;
    private String SecondName;
    private String VehcleNum;
    private int NumofLiters;



    public Passanger(){
        this.FirstName="~";
        this.SecondName="~";
        this.VehcleNum="~";
        this.NumofLiters=0;

    }
    //assigning the variables
    public void setName(String FirstName,String SecondName,String VehcleNum,int NumofLiters){
        this.FirstName=FirstName;
        this.SecondName=SecondName;
        this.VehcleNum=VehcleNum;
        this.NumofLiters=NumofLiters;
    }

    public void set_FirstName(String first_name){

        this.FirstName = first_name;
    }

    public void set_SecondName(String sec_name){

        this.SecondName = sec_name;
    }

    public void set_VehNum(String vehicle_num){

        this.VehcleNum = vehicle_num ;
    }

    public void set_liters(int no_of_liters){

        this.NumofLiters = no_of_liters;
    }


    public String get_FirstName() {

        return FirstName;
    }
    public String get_SecondName(){

        return  SecondName;
    }
    public  String get_VehNum(){

        return VehcleNum;
    }
    public  int get_liters(){

        return NumofLiters;
    }


}
