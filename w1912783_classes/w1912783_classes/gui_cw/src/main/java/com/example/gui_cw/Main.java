package com.example.gui_cw;//libraries used here
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    //Variables
    public static ArrayList<String> waitingListFirstName = new ArrayList<String>();
    public static ArrayList<String> waitingListSecondName = new ArrayList<String>();
    public static ArrayList<String> waitingListVehicleNo = new ArrayList<String>();
    public static ArrayList<Integer> waitingListNumOfLiters = new ArrayList<Integer>();
    public static String[][] p1 = new String[6][4];
    public static String[][] p2 = new String[6][4];
    public static String[][] p3 = new String[6][4];
    public static String[][] p4 = new String[6][4];
    public static String[][] p5 = new String[6][4];
    static FuelQueue pump1 = new FuelQueue();
    static FuelQueue pump2 = new FuelQueue();
    static FuelQueue pump3 = new FuelQueue();
    static FuelQueue pump4 = new FuelQueue();
    static FuelQueue pump5 = new FuelQueue();

    public static int q1;
    public static int q2;
    public static int q3;
    public static int q4;
    public static int q5;
    static FuelQueue[] pump = {pump1, pump2, pump3, pump4, pump5};

    public static ArrayList<String[]> wait_list = new ArrayList<>();
    public static int fuel_stock = 6660;
    public static int i1 = 0;
    public static int i2 = 0;
    public static int i3 = 0;
    public static int i4 = 0;
    public static int i5 = 0;
    public static int low[] = new int[5];
    public static int tot;

    public static void main(String[] args) {
        //main function
        //do display the menu
        while (true) {//if the user enters wrong the menu will display over again until its true
            System.out.println();
            menu();
            System.out.print("Enter the option : ");
            Scanner input = new Scanner(System.in);
            String opt = input.nextLine();
            switch (opt) {
                case "100", "VFQ"://view queues
                    view(pump);
                    break;

                case "101", "VEQ"://view empty queues
                    empty(pump);
                    break;

                case "102", "ACQ"://to add cuastomer
                    add_c(pump);
                    sort_low(pump);
                    break;

                case "103", "RCQ"://to remove customers
                    remove_c(pump);
                    break;

                case "104", "PCQ":
                    remove_rc(pump);//to remove reserved customers
                    break;

                case "105", "VSC":
                    alpha_order(pump);//display in alphabetical order
                    break;

                case "106", "SPD":
                    save_data(pump);//to save the data to a text file
                    break;

                case "107", "LPD":
                    load_data(pump);//to load the data form the text file
                    break;
                case "108", "STK":
                    remaining_fuel(pump);//remanining fuel stock
                    break;

                case "109", "AFS"://adding fuel
                    add_fuel(pump);
                    break;

                case "110", "IFQ":
                    income(pump);//income per one fuel
                    break;

                default:
                    System.out.println("Wrong option");//if the user enters the wrong option ten it iterates back to the menu and tell us to add the option again
            }
            if(opt.equals("999") || opt.equals("EXT")) {
                System.out.println("Congrats U have successfully ");
                break;
            }

            continue;


        }


    }
    //menu
    public static void menu() {
        System.out.println("THE ClASS VERSION".toUpperCase());
        System.out.println();
        System.out.println("Fuel Queue Management System".toUpperCase());
        System.out.println();
        System.out.println("Enter the following options u would like to see :");
        System.out.println("100 or VFQ: View all Fuel Queues.");
        System.out.println("101 or VEQ: View all Empty Queues");
        System.out.println("102 or ACQ: Add customer to a Queue.");
        System.out.println("103 or RCQ: Remove a customer from a Queue.");
        System.out.println("104 or PCQ: Remove a served customer.");
        System.out.println("105 or VCS: View Customers Sorted in alphabetical order");
        System.out.println("106 or SPD: Store Program Data into file.");
        System.out.println("107 or LPD: Load Program Data from file.");
        System.out.println("108 or STK: View Remaining Fuel Stock.");
        System.out.println("109 or AFS: Add Fuel Stock.");
        System.out.println("110 or IFQ: Income of each fuel.");
        System.out.println("999 or EXT: Exit the Program.");
        System.out.println();

    }
    //to view the customers in the queues
    public static void view(FuelQueue[] pump) {
        for (int i = 0; i < 5; i++) {
            System.out.println("<------------------------PUMP "+(i+1)+"-------------------------->");
            System.out.println();
            for (int j = 0; j < 6; j++) {
                System.out.println();
                System.out.println("<-------CUSTOMER "+(j+1)+"------>");
                System.out.println("First Name : " + pump[i].passangers[j].get_FirstName()+'\n'+ "Second Name : " + pump[i].passangers[j].get_SecondName() + '\n' + "Vehicle number : " + pump[i].passangers[j].get_VehNum() + '\n' + "Number of liters : " + pump[i].passangers[j].get_liters());
                System.out.println();

            }

        }
    }
    //to view empty queue
    public static void empty(FuelQueue[] pump) {
        for (int i = 0; i < 5; i++) {
            System.out.println("<------------------------PUMP "+(i+1)+"-------------------------->");
            for (int j = 0; j < 6; j++) {
                if (pump[i].passangers[j].get_FirstName() == "~") {
                    System.out.println();
                    System.out.println("<-------CUSTOMER "+j+"------>");
                    System.out.println("First Name : " + pump[i].passangers[j].get_FirstName()+'\n'+ "Second Name : " + pump[i].passangers[j].get_SecondName() + '\n' + "Vehicle number : " + pump[i].passangers[j].get_VehNum() + '\n' + "Number of liters : " + pump[i].passangers[j].get_liters());
                    System.out.println();
                }
            }
        }

        return;

    }

    //it is used to add the customers
    public static void add_c(FuelQueue[] pump) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("First name : ");
        String FirstName = scanner.nextLine();
        System.out.println("Second name :");
        String SecondName = scanner.nextLine();
        System.out.println("Vehicle number :");
        String Vehicle = scanner.nextLine();
        System.out.println("Enter the Number of liters :");
        int liters = scanner.nextInt();

        if (pump[4].passangers[5].get_FirstName() != "~") {
            System.out.println("pump is full, adding to waiting list");
            waitingListFirstName.add(FirstName);
            waitingListSecondName.add(SecondName);
            waitingListVehicleNo.add(Vehicle);
            waitingListNumOfLiters.add(liters);
            ;

        } else if (i1 == low[0]) {
            for (int i = 0; i < 6; i++) {
                if (pump[0].passangers[i].get_FirstName() == "~") {
                    pump[0].passangers[i].set_FirstName(FirstName);
                    pump[0].passangers[i].set_SecondName(SecondName);
                    pump[0].passangers[i].set_VehNum(Vehicle);
                    pump[0].passangers[i].set_liters(liters);
                    i1++;
                    System.out.println("The customer has been added Successfully");
                    break;
                }
            }
        } else if (i2 == low[0]) {
            for (int i = 0; i < 6; i++) {
                if (pump[1].passangers[i].get_FirstName() == "~") {
                    assert (!pump[1].passangers[i].equals("~")) ;
                    pump[1].passangers[i].set_FirstName(FirstName);
                    pump[1].passangers[i].set_SecondName(SecondName);
                    pump[1].passangers[i].set_VehNum(Vehicle);
                    pump[1].passangers[i].set_liters(liters);
                    i2++;
                    System.out.println("The customer has been added Successfully");
                    break;
                }
            }

        } else if (i3 == low[0]) {
            for (int i = 0; i < 6; i++) {
                if (pump[2].passangers[i].get_FirstName() == "~") {
                    assert (!pump[2].passangers[i].equals("~"));
                    pump[2].passangers[i].set_FirstName(FirstName);
                    pump[2].passangers[i].set_FirstName(SecondName);
                    pump[2].passangers[i].set_VehNum(Vehicle);
                    pump[2].passangers[i].set_liters(liters);
                    i3++;
                    System.out.println("The customer has been added Successfully");
                    break;
                }
            }

        } else if (i4 == low[0]) {
            for (int i = 0; i < 6; i++) {
                if (pump[3].passangers[i].get_FirstName() == "~") {
                    assert (!pump[3].passangers[i].equals("~"));
                    pump[3].passangers[i].set_FirstName(FirstName);
                    pump[3].passangers[i].set_SecondName(SecondName);
                    pump[3].passangers[i].set_VehNum(Vehicle);
                    pump[3].passangers[i].set_liters(liters);
                    i4++;
                    System.out.println("The customer has been added Successfully");
                    break;
                }
            }

        } else if (i5 == low[0]) {
            for (int i = 0; i < 6; i++) {
                if (pump[4].passangers[i].get_FirstName() == "~") {
                    assert !pump[4].passangers[i].equals("~") ;
                    pump[4].passangers[i].set_FirstName(FirstName);
                    pump[4].passangers[i].set_SecondName(SecondName);
                    pump[4].passangers[i].set_VehNum(Vehicle);
                    pump[4].passangers[i].set_liters(liters);
                    i5++;
                    System.out.println("The customer has been added Successfully");
                    break;
                }
            }

        }


    }
    //this is used when the customers are added to the lesser crowded queues
    public static void sort_low(FuelQueue[] pump) {
        int count = 0;
        int temp = 0;
        low[0] = i1;
        low[1] = i2;
        low[2] = i3;
        low[3] = i4;
        low[4] = i5;
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i3);
        System.out.println(i4);
        System.out.println(i5);
        boolean fo = true;
//then bubble sort is done to  arrange the array from the lowest count value to the largest
        while (fo) {
            if (low[0] > low[1]) {
                temp = low[0];
                low[0] = low[1];
                low[1] = temp;
            }
            if (low[1] > low[2]) {
                temp = low[2];
                low[2] = low[1];
                low[1] = temp;
            }
            if (low[2] > low[3]) {
                temp = low[2];
                low[2] = low[3];
                low[3] = temp;
            }
            if (low[3] > low[4]) {
                temp = low[3];
                low[3] = low[4];
                low[4] = temp;
            }
            count++;
            if (25 == count) {
                fo = false;
            }

        }
    }
    // it is used to remove the customers
    public static void remove_c(FuelQueue[] pump) {
        System.out.println("------------------------TO REMOVE CUSTOMERS------------------------");
        Scanner scanner = new Scanner(System.in);
        Scanner num = new Scanner(System.in);
        System.out.println("Enter the pump no : ");
        int que_r = scanner.nextInt();
        System.out.println("Enter the customer position: ");
        int k = num.nextInt();
        String find = "";
        if (que_r == 1 || que_r == 2 || que_r == 3 || que_r == 4 || que_r == 5){
            pump[que_r - 1].passangers.equals("~");
            for (k = 0; k<(6-1) ; k++){
                pump[que_r - 1].passangers[k] = pump[que_r - 1].passangers[k + 1];
            }
            System.out.println("Customer Removed Sucessfully");
        }

        else {
            System.out.println("Wrong Number");
        }
        for(int i=0;i<6;i++){
            if(pump[que_r- 1].passangers[i].get_FirstName()==null && waitingListFirstName.size() != 0){
                pump[que_r - 1].passangers[i].set_FirstName(waitingListFirstName.get(0));
                pump[que_r - 1].passangers[i].set_SecondName(waitingListSecondName.get(0));
                pump[que_r - 1].passangers[i].set_VehNum(waitingListVehicleNo.get(0));
                pump[que_r- 1].passangers[i].set_liters(waitingListNumOfLiters.get(0));
                waitingListFirstName.remove(0);
                waitingListSecondName.remove(0);
                waitingListVehicleNo.remove(0);
                waitingListNumOfLiters.remove(0);
                break;
            }
        }
    }


    //used to remove the 1st serve customers

    public static void remove_rc(FuelQueue[] pump) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the pump no : ");
        int que_rc = scanner.nextInt();
        if (que_rc == 1 || que_rc == 2 || que_rc == 3 || que_rc == 4 || que_rc == 5) {
            pump[que_rc - 1].passangers.equals("~");
            for (int k = 0; k<(6-1) ; k++){
                pump[que_rc - 1].passangers[k] = pump[que_rc - 1].passangers[k + 1];
                System.out.println("Reserved Customer Removed Sucessfully");
            }
        }
        else {
            System.out.println("Wrong Input");
        }


    }
    //siaplays the queue in alpahbetical order by using
    public static void alpha_order(FuelQueue[] pump) {
        int pum = 6;
        String[] temp;
        System.out.println("---------------------PUMP 1------------------------\n");
        for (int i = 0; i < pum; i++) {
            for (int j = i + 1; j < pum; j++) {
                if (p1[i][0] == "~" || p1[j][0] == "~") {
                    continue;
                } else {
                    if (Arrays.toString(p1[i]).compareTo(Arrays.toString(p1[j])) > 0) {
                        temp = p1[i];
                        p1[i] = p1[j];
                        p1[j] = temp;
                    }
                }
            }

        }
        System.out.println("After sorted: ");
        System.out.println("---------------------PUMP 2------------------------\n");
        for (int i = 0; i < pum; i++) {
            System.out.println(Arrays.toString(p1[i]));
        }

        for (int i = 0; i < pum; i++) {
            for (int j = i + 1; j < pum; j++) {
                if (p2[i][0] == "~" || p2[j][0] == "~") {
                    continue;
                } else {
                    if (Arrays.toString(p2[i]).compareTo(Arrays.toString(p2[j])) > 0) {
                        temp = p2[i];
                        p2[i] = p2[j];
                        p2[j] = temp;
                    }
                }
            }
        }
        System.out.println("After sorted: ");
        for (int i = 0; i < pum; i++) {
            System.out.println(Arrays.toString(p2[i]));
        }
        System.out.println("---------------------PUMP 3------------------------\n");
        for (int i = 0; i < pum; i++) {
            for (int j = i + 1; j < pum; j++) {
                if (p3[i][0] == "~" || p3[j][0] == "~") {
                    continue;
                } else {
                    if (Arrays.toString(p3[i]).compareTo(Arrays.toString(p3[j])) > 0) {
                        temp = p3[i];
                        p3[i] = p3[j];
                        p3[j] = temp;
                    }
                }
            }

        }
        System.out.println("After sorted: ");
        for (int i = 0; i < pum; i++) {
            System.out.println(Arrays.toString(p3[i]));
        }
        System.out.println("---------------------PUMP 4------------------------\n");
        for (int i = 0; i < pum; i++) {
            for (int j = i + 1; j < pum; j++) {
                if (p4[i][0] == "~" || p4[j][0] == "~") {
                    continue;
                } else {
                    if (Arrays.toString(p4[i]).compareTo(Arrays.toString(p4[j])) > 0) {
                        temp = p4[i];
                        p4[i] = p4[j];
                        p4[j] = temp;
                    }
                }
            }
        }

        System.out.println("---------------------PUMP 5------------------------\n");
        for (int i = 0; i < pum; i++) {
            for (int j = i + 1; j < pum; j++) {
                if (p5[i][0] == "~" || p5[j][0] == "~") {
                    continue;
                } else {
                    if (Arrays.toString(p5[i]).compareTo(Arrays.toString(p5[j])) > 0) {
                        temp = p5[i];
                        p5[i] = p5[j];
                        p5[j] = temp;
                    }
                }
            }
        }


    }

   //used to save the data to the text file
    public static void save_data(FuelQueue[] pump) {
        try {

            File txt = new File("cw.txt");
            PrintWriter writeFile=new PrintWriter(txt);
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 6; j++) {
                    writeFile.println(pump[i].passangers[j].get_FirstName());
                    writeFile.println(pump[i].passangers[j].get_SecondName());
                    writeFile.println(pump[i].passangers[j].get_VehNum());
                    writeFile.println(pump[i].passangers[j].get_liters());

                }

            }

            writeFile.close();
            System.out.println("Data is Saved Sucessfully");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
    //used to load the data form text file
    public static void load_data(FuelQueue[] pump) {
        //reading the saved text file.
        try {
            System.out.println("Data is loaded form the text file Sucessfully");
            File txt = new File("cw.txt");
            Scanner ld = new Scanner(txt);
            for(int i=0; i<215; i++) {
                System.out.println(ld.nextLine());


            }

        } catch (IOException FileNotFoundException) {
            System.out.println("An error occurred.");
            FileNotFoundException.printStackTrace();
        }

    }

    //view the remaining fuel
    public static void remaining_fuel(FuelQueue[] pump){
        System.out.println("<-------------View Remaining Fuel Stock------------->\n");
        System.out.println("Remaining fuel :"+fuel_stock+"l");
    }
    //use to add the petrol
    public static void add_fuel(FuelQueue[] pump){
        Scanner add_f = new Scanner(System.in);
        System.out.print("\nHow much fuel stock do you want to add(add as liters)??");
        int add = add_f.nextInt();
        fuel_stock+=add;
    }
    public static void income(FuelQueue[] pump){//in the que each perosn is charged for 430 for each liters.
        for(int i=0; i<4;i++ ){
            q1=q1+(pump[0].passangers[i].get_liters());
            q2=q2+(pump[1].passangers[i].get_liters());
            q3=q3+(pump[2].passangers[i].get_liters());
            q4=q4+(pump[3].passangers[i].get_liters());
            q5=q5+(pump[4].passangers[i].get_liters());

        }
        tot=((q1*430)+(q2*430)+(q3*430)+(q4*430)+(q5*430));
        System.out.println("<-------------Income of each Fuel queue.------------->");
        System.out.println("Queue 01 : RS."+(q1*430));
        System.out.println("Queue 02 : RS."+(q2*430));
        System.out.println("Queue 03 : RS."+(q3*430));
        System.out.println("Queue 04 : RS."+(q4*430));
        System.out.println("Queue 04 : RS."+(q5*430)+"\n");

    }






}