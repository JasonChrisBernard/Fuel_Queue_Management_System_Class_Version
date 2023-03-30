package com.example.gui_cw;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
public class HelloController {
    @FXML
    private TextField s_bar;
    @FXML
    private TextArea v_box;

    @FXML
    private TextArea s_box;



    @FXML
    protected void customer_v(){
        try{
            FileReader reader = new FileReader("CW.txt");
            int data = reader.read();
            while(data != -1){
                v_box.appendText(String.valueOf((char)data));
                data=reader.read();

            }
            reader.close();
        }catch(Exception e){
            System.out.println("can't find file");
        }

    }

    @FXML
    protected void customer_s(){
        String[][][] array = new String[5][6][4];
        try{
            FileReader reader = new FileReader("CW.txt");
            Scanner cat = new Scanner(reader);
            for(int i=0;i<5;i++){
                for (int j=0;j<6;j++){
                    array[i][j][0]=cat.nextLine();
                    array[i][j][1]=cat.nextLine();
                    array[i][j][2]=cat.nextLine();
                    array[i][j][3]=cat.nextLine();

                }
            }
            reader.close();
           //System.out.println(Arrays.deepToString(array));
            for(int k =0;k< array.length;k++){
                for(int s=0;s<6;s++) {
                    if (array[k][s][0].equals(s_bar.getText())) {
                        s_box.appendText(array[k][s][0]+"\n");//firstname
                        s_box.appendText(array[k][s][1]+"\n");//lastname
                        s_box.appendText(array[k][s][2]+"\n");//vehicle
                        s_box.appendText(array[k][s][3]+"\n");//litter
                        //System.out.println(array[k][s][0]);
                    }
                }

            }

        }catch(Exception e){
            System.out.println("can't find file");
        }
    }
}
