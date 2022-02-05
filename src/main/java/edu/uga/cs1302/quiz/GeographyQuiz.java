package edu.uga.cs1302.quiz;

//importing javafx classes
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;
import javafx.stage.Modality;
import javafx.scene.text.Text;

//importing java classes
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Random;


public class GeographyQuiz extends Application { //Creating Geography Quiz class

    private Stage primaryStage, quizStage; //initializing stage variables
    private Country1 c = new Country1(); //initializing Country variable from the Country1 class
    private Results result = new Results(); //initializing result variable from the Result class
    public Scene scene1, scene2, scene3, scene4, scene5, scene6, scene7; //initializing Scene variables for the questions
    public int Maxscore = 0; //initalizing int to keep a hold of the score
    private String timeStamp, timeStampPlus; //initializing String variables to create and format the timeStamp
    private boolean startQuiz = false; // boolean stating if the quiz has started
    private boolean isFinished = false; // boolean stating if the quiz has finished


    @Override
    public void start(Stage primaryStage) {

        //setting title for the primary stage
        primaryStage.setTitle("Geography Quiz \uD83C\uDF0D");

//Scene 1
        //initalizing label to greet users
        Label label1= new Label("Greetings User");
        // creating play button
        Button button1= new Button("Play");
        button1.setOnAction(e -> primaryStage.setScene(scene2));
        Button button2= new Button("Previous Results"); //creating previous quiz button
        button2.setOnAction(e -> primaryStage.setScene(scene1));
        Button button3= new Button("Help"); //creating help button
        button3.setOnAction(e -> primaryStage.setScene(scene1));
        Button button4= new Button("Quit"); //creating quit button
        button4.setOnAction(e -> primaryStage.setScene(scene1));
        VBox layout1 = new VBox(20); //creating the layout for scene1
        layout1.getChildren().addAll(label1, button1, button2, button3, button4); //initializing layout
        scene1= new Scene(layout1, 300, 250); //initializing Scene1

        button1.setOnAction( this::playButtonHandler ); //giving action to button1
        button2.setOnAction( this::prevButtonHandler ); //giving action to button2
        button4.setOnAction( this::exitButtonOnAction ); //giving action to button3
        button3.setOnAction( this::helpButtonHandler ); //giving action to button4

    this.primaryStage = primaryStage; //initializing primary stage

        primaryStage.setScene(scene1); //setting primaryStage to scene1
        layout1.setAlignment(Pos.CENTER); //aligning layout to center
        primaryStage.show(); //showing primaryStage window
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }

    public void playButtonHandler( ActionEvent event ) {

        Random rn11 = new Random(); //creating variable from random class
        int random1 = rn11.nextInt(c.getCountry().size()); //creating random int from 0 to the # of countries in the csv file
        int randomAnswer11 = (c.getContinentAnswer().indexOf(c.getCountry().get(random1))) + 1; //creating integer to find index of the random country's answer

        ArrayList<String> list1 = new ArrayList<String>(); //creating an array list
        list1.add(c.getContinentAnswer().get(randomAnswer11)); //adding the country's answer to the list
        while (list1.size() < 4) { //while loop to add random continents
            int randomnum1 = rn11.nextInt(c.getContinent().size()); //random int to grab random continents
            if (!(list1.contains(c.getContinent().get(randomnum1)))){ //checking if the list contains the continent already
                list1.add(c.getContinent().get(randomnum1)); //adding the continent to the list
            }
        }

        int randomnum1 = rn11.nextInt(3);
        list1.remove(0); //removing the answer becasuse if not it will be the first choice everytime
        if (randomnum1 > list1.size()) { //if the random number is the 3 then we append the answer to the end
            list1.add(c.getContinentAnswer().get(randomAnswer11)); //add to the end of the list
        }
        else { //else add the index of the random number
            list1.add(randomnum1 , (c.getContinentAnswer().get(randomAnswer11)));
        }

        String correctAnswer1 = c.getContinentAnswer().get(randomAnswer11); //creating a string with the correct answer

        RadioButton radio11, radio21, radio31; //initializing radio buttons
        Label label11 = new Label("On which continent is " + c.getCountry().get(random1) + " located?"); //initializing variable for the question
        String option1 = list1.remove(0); //option1 will be the 1st element in the random list created
        radio11 = new RadioButton("" + option1);
        String option2 = list1.remove(0); //option2 will be the 2nd element in the random list created
        radio21 = new RadioButton("" + option2);
        String option3 = list1.remove(0);//option3 will be the 3rd element in the random list created
        radio31 = new RadioButton("" + option3);
        Button button11 = new Button("Submit"); //creating submit button
        Label labelresponse11= new Label(); //creating another label
        Button button21 = new Button("Next Question"); //creating next question label

        button11.setDisable(true); //cant be clicked while true
        button21.setDisable(true); //cant be clicked while true

        ToggleGroup question1 = new ToggleGroup(); //allowing only one radio button to be selected

        radio11.setToggleGroup(question1);
        radio21.setToggleGroup(question1);
        radio31.setToggleGroup(question1);

    //allows submit button to be clicked
        radio11.setOnAction(e -> button11.setDisable(false) );
        radio21.setOnAction(e -> button11.setDisable(false) );
        radio31.setOnAction(e -> button11.setDisable(false) );

        button11.setOnAction(e -> //setting action to submit button
                {
                    //checking if the button selected is correct
                    if (((radio11.isSelected()) && (radio11.getText().equalsIgnoreCase(correctAnswer1))) ||
                            ((radio21.isSelected()) && (radio21.getText().equalsIgnoreCase(correctAnswer1))) ||
                            ((radio31.isSelected()) && (radio31.getText().equalsIgnoreCase(correctAnswer1))))
                    {
                        labelresponse11.setText("Correct answer: +1 Point");
                        //buttons and radio buttons lose functionality
                        button11.setDisable(true);
                        radio11.setDisable(true);
                        radio21.setDisable(true);
                        radio31.setDisable(true);
                        button21.setDisable(false);
                        Maxscore++; //concat the score of the game
                    }
                    else //the selected button is incorrect
                    {
                        labelresponse11.setText("Incorrect! The correct answer is: " + correctAnswer1);
                        button11.setDisable(true);
                        radio11.setDisable(true);
                        radio21.setDisable(true);
                        radio31.setDisable(true);
                        button21.setDisable(false);
                    }
                }
        );

        //removing elements from list so they are not repeated
    c.getCountry().remove(random1);
    c.getContinentAnswer().remove(random1 * 2);
    c.getContinentAnswer().remove(random1 * 2 + 1);



// the main layout in the new window is an VBox
        VBox layout21 = new VBox(20);
        layout21.setAlignment(Pos.CENTER);
        //initializing layout
        layout21.getChildren().addAll(label11, radio11, radio21, radio31, button11, labelresponse11, button21);
        // Alligning layout
       layout21.setAlignment( Pos.CENTER );

        // create a new Scene with the new Layout
        Scene newScene = new Scene( layout21, 300, 450 );

        // New window (Stage)
         quizStage = new Stage();
        quizStage.setTitle( "Question 1" );
        quizStage.setScene( newScene );

        // set the modality for the new window;  the main window will be blocked
        quizStage.initModality( Modality.APPLICATION_MODAL );
        // Set position of the song window vs the main window.
        quizStage.setX( primaryStage.getX() + 200);
        quizStage.setY( primaryStage.getY() + 100);

        //assigning action to Next Question Button
        button21.setOnAction(e ->
        {
            setScene2(scene2);
        quizStage.setScene(scene2);
        quizStage.setTitle( "Question 2" );
        }
        );

        quizStage.show();//showing quizStage window
    }

    public void setScene2(Scene scene2) {

    Random rn2 = new Random();//creating variable from random class
    int random2 = rn2.nextInt(c.getCountry().size());//creating random int from 0 to the # of countries in the csv file
    int randomAnswer2 = (c.getContinentAnswer().indexOf(c.getCountry().get(random2))) + 1; //creating integer to find index of the random country's answer

    ArrayList<String> list12 = new ArrayList<String>();//creating an array list
    list12.add(c.getContinentAnswer().get(randomAnswer2));//adding the country's answer to the list
    while (list12.size() < 4) {//while loop to add random continents
        int randomnum12 = rn2.nextInt(c.getContinent().size());//random int to grab random continents
        if (!(list12.contains(c.getContinent().get(randomnum12)))){//checking if the list contains the continent already
            list12.add(c.getContinent().get(randomnum12));//adding the continent to the list
        }
    }

    int randomnum12 = rn2.nextInt(3);
    list12.remove(0);//removing the answer becasuse if not it will be the first choice everytime
    if (randomnum12 > list12.size()) {//if the random number is the 3 then we append the answer to the end
        list12.add(c.getContinentAnswer().get(randomAnswer2));//add to the end of the list
    }
    else {//else add the index of the random number
        list12.add(randomnum12 , (c.getContinentAnswer().get(randomAnswer2)));
    }

    String correctAnswer2 = c.getContinentAnswer().get(randomAnswer2);//creating a string with the correct answer

    RadioButton radio522, radio622, radio722;//initializing radio buttons
    Label label32 = new Label("On which continent is " + c.getCountry().get(random2) + " located?");//initializing variable for the question
    String option5 = list12.remove(0);//option1 will be the 1st element in the random list created
    radio522 = new RadioButton("" + option5.substring(option5.indexOf(",") + 1));
    String option6 = list12.remove(0);//option2 will be the 2nd element in the random list created
    radio622 = new RadioButton("" + option6.substring(option6.indexOf(",") + 1));
    String option7 = list12.remove(0);//option3 will be the 3rd element in the random list created
    radio722 = new RadioButton("" + option7.substring(option7.indexOf(",") + 1));
    Button button722 = new Button("Submit");//creating submit button
    Label labelresponse122= new Label();//creating another label
    Button button822 = new Button("Next Question");//creating next question label

    ToggleGroup question2 = new ToggleGroup();//allowing only one radio button to be selected

    radio522.setToggleGroup(question2);
    radio622.setToggleGroup(question2);
    radio722.setToggleGroup(question2);

//cant be clicked while true
    button722.setDisable(true);
    button822.setDisable(true);

//allows submit button to be clicked
    radio522.setOnAction(e -> button722.setDisable(false) );
    radio622.setOnAction(e -> button722.setDisable(false) );
    radio722.setOnAction(e -> button722.setDisable(false) );



    button722.setOnAction(e ->//setting action to submit button
            {
//checking if the button selected is correct
                if (((radio522.isSelected()) && (radio522.getText().equalsIgnoreCase(correctAnswer2))) ||
                        ((radio622.isSelected()) && (radio622.getText().equalsIgnoreCase(correctAnswer2))) ||
                        ((radio722.isSelected()) && (radio722.getText().equalsIgnoreCase(correctAnswer2))))
                {
                    labelresponse122.setText("Correct answer: +1 Point");
                    //buttons and radio buttons lose functionality
                    button722.setDisable(true);
                    radio522.setDisable(true);
                    radio622.setDisable(true);
                    radio722.setDisable(true);
                    button822.setDisable(false);
                    Maxscore++;//concat the score of the game
                }
                else//the selected button is incorrect
                {
                    labelresponse122.setText("Incorrect! The correct answer is: " + correctAnswer2);
                    button722.setDisable(true);
                    radio522.setDisable(true);
                    radio622.setDisable(true);
                    radio722.setDisable(true);
                    button822.setDisable(false);
                }
            }
    );

//removing elements from list so they are not repeated
    c.getCountry().remove(random2);
        c.getContinentAnswer().remove(random2 * 2);
        c.getContinentAnswer().remove(random2 * 2 + 1);

        // the main layout in the new window is an VBox
        VBox layout322= new VBox(20);
        // Alligning layout
    layout322.setAlignment(Pos.CENTER);
        //initializing layout
    layout322.getChildren().addAll(label32, radio522, radio622, radio722, button722, labelresponse122, button822);

        // create a new Scene with the new Layout
    scene2 = new Scene(layout322,300,450);

this.scene2 = scene2;//initializing Scene2

//assigning action to Next Question Button
    button822.setOnAction(e ->
            {
                setScene3(scene3);
                quizStage.setScene(scene3);
                quizStage.setTitle( "Question 3" );
            }
    );
    quizStage.show();//showing quizStage window
}

    public void setScene3(Scene scene3) {

        Random rn3 = new Random(); //creating variable from random class
        int random3 = rn3.nextInt(c.getCountry().size()); //creating random int from 0 to the # of countries in the csv file
        int randomAnswer3 = (c.getContinentAnswer().indexOf(c.getCountry().get(random3))) + 1; //creating integer to find index of the random country's answer

        ArrayList<String> list1113 = new ArrayList<String>(); //creating an array list
        list1113.add(c.getContinentAnswer().get(randomAnswer3)); //adding the country's answer to the list
        while (list1113.size() < 4) { //while loop to add random continents
            int randomnum3 = rn3.nextInt(c.getContinent().size()); //random int to grab random continents
            if (!(list1113.contains(c.getContinent().get(randomnum3)))){ //checking if the list contains the continent already
                list1113.add(c.getContinent().get(randomnum3)); //adding the continent to the list
            }
        }

        int randomnum123 = rn3.nextInt(3);
        list1113.remove(0); //removing the answer becasuse if not it will be the first choice everytime
        if (randomnum123 > list1113.size()) { //if the random number is the 3 then we append the answer to the end
            list1113.add(c.getContinentAnswer().get(randomAnswer3)); //add to the end of the list
        }
        else { //else add the index of the random number
            list1113.add(randomnum123 , (c.getContinentAnswer().get(randomAnswer3)));
        }


        String correctAnswer3 = c.getContinentAnswer().get(randomAnswer3); //creating a string with the correct answer

        RadioButton radio52, radio62, radio72; //initializing radio buttons
        Label label323 = new Label("On which continent is " + c.getCountry().get(random3) + " located?"); //initializing variable for the question
        String option5 = list1113.remove(0); //option1 will be the 1st element in the random list created
        radio52 = new RadioButton("" + option5.substring(option5.indexOf(",") + 1));
        String option6 = list1113.remove(0); //option2 will be the 2nd element in the random list created
        radio62 = new RadioButton("" + option6.substring(option6.indexOf(",") + 1));
        String option7 = list1113.remove(0); //option3 will be the 3rd element in the random list created
        radio72 = new RadioButton("" + option7.substring(option7.indexOf(",") + 1));
        Button button723 = new Button("Submit"); //creating submit button
        Label labelresponse123= new Label(); //creating another label
        Button button823 = new Button("Next Question"); //creating next question label

        ToggleGroup question3 = new ToggleGroup(); //allowing only one radio button to be selected

        radio52.setToggleGroup(question3);
        radio62.setToggleGroup(question3);
        radio72.setToggleGroup(question3);

        //These buttons cant be clicked while set to true
        button723.setDisable(true);
        button823.setDisable(true);

        //allows submit button to be clicked
        radio52.setOnAction(e -> button723.setDisable(false) );
        radio62.setOnAction(e -> button723.setDisable(false) );
        radio72.setOnAction(e -> button723.setDisable(false) );

        button723.setOnAction(e -> //setting action to submit button
                {
//checking if the button selected is correct
                    if (((radio52.isSelected()) && (radio52.getText().equalsIgnoreCase(correctAnswer3))) ||
                            ((radio62.isSelected()) && (radio62.getText().equalsIgnoreCase(correctAnswer3))) ||
                            ((radio72.isSelected()) && (radio72.getText().equalsIgnoreCase(correctAnswer3))))
                    {
                        labelresponse123.setText("Correct answer: +1 Point");
                        //buttons and radio buttons lose functionality
                        button723.setDisable(true);
                        radio52.setDisable(true);
                        radio62.setDisable(true);
                        radio72.setDisable(true);
                        button823.setDisable(false);
                        Maxscore++; //concat the score of the game
                    }

                    else //the selected button is incorrect
                    {
                        labelresponse123.setText("Incorrect! The correct answer is: " + correctAnswer3);
                        button723.setDisable(true);
                        radio52.setDisable(true);
                        radio62.setDisable(true);
                        radio72.setDisable(true);
                        button823.setDisable(false);
                    }
                }
        );

        //removing elements from list so they are not repeated
        c.getCountry().remove(random3);
        c.getContinentAnswer().remove(random3 * 2);
        c.getContinentAnswer().remove(random3 * 2 + 1);

        // the main layout in the new window is an VBox
        VBox layout33= new VBox(20);
        // Alligning layout
        layout33.setAlignment(Pos.CENTER);
        //initializing layout
        layout33.getChildren().addAll(label323, radio52, radio62, radio72, button723, labelresponse123, button823);
        // create a new Scene with the new Layout
        scene3 = new Scene(layout33,300,450);

        this.scene3 = scene3; //initializing Scene3

        //assigning action to Next Question Button
        button823.setOnAction(e ->
                {
                    setScene4(scene4);
                    quizStage.setScene(scene4);
                    quizStage.setTitle( "Question 4" );
                }
        );

        quizStage.show(); //showing quizStage window
    }

    public void setScene4(Scene scene4) {

        Random rn4 = new Random();//creating variable from random class
        int random4 = rn4.nextInt(c.getCountry().size());//creating random int from 0 to the # of countries in the csv file
        int randomAnswer4 = (c.getContinentAnswer().indexOf(c.getCountry().get(random4))) + 1; //creating integer to find index of the random country's answer

        ArrayList<String> list1114 = new ArrayList<String>();//creating an array list
        list1114.add(c.getContinentAnswer().get(randomAnswer4));//adding the country's answer to the list
        int holder = 0;
        while (list1114.size() < 4) {//while loop to add random continents
            int randomnum4 = rn4.nextInt(c.getContinent().size());//random int to grab random continents
            if (!(list1114.contains(c.getContinent().get(randomnum4)))){//checking if the list contains the continent already
                list1114.add(c.getContinent().get(randomnum4));//adding the continent to the list
            }
        }

        ArrayList<String> randomList14 = new ArrayList<String>();
        int randomnum4 = rn4.nextInt(3);
        list1114.remove(0);//removing the answer becasuse if not it will be the first choice everytime
        if (randomnum4 > list1114.size()) {//if the random number is the 3 then we append the answer to the end
            list1114.add(c.getContinentAnswer().get(randomAnswer4));//add to the end of the list
        }
        else {//else add the index of the random number
            list1114.add(randomnum4 , (c.getContinentAnswer().get(randomAnswer4)));
        }

        String correctAnswer4 = c.getContinentAnswer().get(randomAnswer4);//creating a string with the correct answer

        RadioButton radio52, radio62, radio72, radio82;//initializing radio buttons
        Label label324 = new Label("On which continent is " + c.getCountry().get(random4) + " located?");//initializing variable for the question
        String option5 = list1114.remove(0);//option1 will be the 1st element in the random list created
        radio52 = new RadioButton("" + option5.substring(option5.indexOf(",") + 1));
        // radio5.setAlignment(Pos.CENTER);
        String option6 = list1114.remove(0);//option2 will be the 2nd element in the random list created
        radio62 = new RadioButton("" + option6.substring(option6.indexOf(",") + 1));
        //radio6.setAlignment(Pos.CENTER);
        String option7 = list1114.remove(0);//option3 will be the 3rd element in the random list created
        radio72 = new RadioButton("" + option7.substring(option7.indexOf(",") + 1));
        Button button72 = new Button("Submit");//creating submit button
        Label labelresponse124= new Label();//creating another label
        Button button82 = new Button("Next Question");//creating next question label
        // button8.setAlignment(Pos.BASELINE_RIGHT);

        ToggleGroup question4 = new ToggleGroup();//allowing only one radio button to be selected

//allows submit button to be clicked
        radio52.setToggleGroup(question4);
        radio62.setToggleGroup(question4);
        radio72.setToggleGroup(question4);

//These buttons cant be clicked while set to true
        button72.setDisable(true);
        button82.setDisable(true);

        radio52.setOnAction(e -> button72.setDisable(false) );
        radio62.setOnAction(e -> button72.setDisable(false) );
        radio72.setOnAction(e -> button72.setDisable(false) );


        button72.setOnAction(e ->//setting action to submit button
                {
//checking if the button selected is correct
                    if (((radio52.isSelected()) && (radio52.getText().equalsIgnoreCase(correctAnswer4))) ||
                            ((radio62.isSelected()) && (radio62.getText().equalsIgnoreCase(correctAnswer4))) ||
                            ((radio72.isSelected()) && (radio72.getText().equalsIgnoreCase(correctAnswer4))))
                    {
                        labelresponse124.setText("Correct answer: +1 Point");
                        //buttons and radio buttons lose functionality
                        button72.setDisable(true);
                        radio52.setDisable(true);
                        radio62.setDisable(true);
                        radio72.setDisable(true);
                        button82.setDisable(false);
                        Maxscore++;//concat the score of the game
                    }

                    else//the selected button is incorrect
                    {
                        labelresponse124.setText("Incorrect! The correct answer is: " + correctAnswer4 );
                        button72.setDisable(true);
                        radio52.setDisable(true);
                        radio62.setDisable(true);
                        radio72.setDisable(true);
                        button82.setDisable(false);
                    }
                }
        );

//removing elements from list so they are not repeated
        c.getCountry().remove(random4);
        c.getContinentAnswer().remove(random4 * 2);
        c.getContinentAnswer().remove(random4 * 2 + 1);

// the main layout in the new window is an VBox
        VBox layout34= new VBox(20);
        layout34.setAlignment(Pos.CENTER);// Alligning layout
        layout34.getChildren().addAll(label324, radio52, radio62, radio72, button72, labelresponse124, button82);//initializing layout
        scene4= new Scene(layout34,300,450);// create a new Scene with the new Layout

        this.scene4 = scene4;//initializing Scene4

        button82.setOnAction(e ->//assigning action to Next Question Button
                {
                    setScene5(scene5);
                    quizStage.setScene(scene5);
                    quizStage.setTitle( "Question 5" );
                }
        );
        quizStage.show();//showing quizStage window
    }

    public void setScene5(Scene scene5) {

        Random rn15 = new Random();//creating variable from random class
        int random15 = rn15.nextInt(c.getCountry().size());//creating random int from 0 to the # of countries in the csv file
        int randomAnswer5 = (c.getContinentAnswer().indexOf(c.getCountry().get(random15))) + 1;//creating integer to find index of the random country's answer

        ArrayList<String> list1115 = new ArrayList<String>();//creating an array list
        list1115.add(c.getContinentAnswer().get(randomAnswer5));//adding the country's answer to the list
        int holder = 0;
        while (list1115.size() < 4) {//while loop to add random continents
            int randomnum = rn15.nextInt(c.getContinent().size());//random int to grab random continents
            if (!(list1115.contains(c.getContinent().get(randomnum)))){//checking if the list contains the continent already
                list1115.add(c.getContinent().get(randomnum));//adding the continent to the list
            }
        }

        int randomnum5 = rn15.nextInt(3);
        list1115.remove(0);//removing the answer becasuse if not it will be the first choice everytime
        if (randomnum5 > list1115.size()) {//if the random number is the 3 then we append the answer to the end
            list1115.add(c.getContinentAnswer().get(randomAnswer5));//add to the end of the list
        }
        else {//else add the index of the random number
            list1115.add(randomnum5 , (c.getContinentAnswer().get(randomAnswer5)));
        }

        String correctAnswer5 = c.getContinentAnswer().get(randomAnswer5);//creating a string with the correct answer

        RadioButton radio52, radio62, radio72, radio82;//initializing radio buttons
        Label label325 = new Label("On which continent is " + c.getCountry().get(random15) + " located?");//initializing variable for the question
        String option5 = list1115.remove(0);//option1 will be the 1st element in the random list created
        radio52 = new RadioButton("" + option5.substring(option5.indexOf(",") + 1));
        // radio5.setAlignment(Pos.CENTER);
        String option6 = list1115.remove(0);//option2 will be the 2nd element in the random list created
        radio62 = new RadioButton("" + option6.substring(option6.indexOf(",") + 1));
        //radio6.setAlignment(Pos.CENTER);
        String option7 = list1115.remove(0);//option3 will be the 3rd element in the random list created
        radio72 = new RadioButton("" + option7.substring(option7.indexOf(",") + 1));
        Button button72 = new Button("Submit");//creating submit button
        Label labelresponse125 = new Label();//creating another label
        Button button82 = new Button("Next Question");//creating next question label
        // button8.setAlignment(Pos.BASELINE_RIGHT);

        ToggleGroup question5= new ToggleGroup();//allowing only one radio button to be selected

        radio52.setToggleGroup(question5);
        radio62.setToggleGroup(question5);
        radio72.setToggleGroup(question5);

//cant be clicked while true
        button72.setDisable(true);
        button82.setDisable(true);

//allows submit button to be clicked
        radio52.setOnAction(e -> button72.setDisable(false) );
        radio62.setOnAction(e -> button72.setDisable(false) );
        radio72.setOnAction(e -> button72.setDisable(false) );

        button72.setOnAction(e ->//setting action to submit button
                {
//checking if the button selected is correct
                    if (((radio52.isSelected()) && (radio52.getText().equalsIgnoreCase(correctAnswer5))) ||
                                          ((radio62.isSelected()) && (radio62.getText().equalsIgnoreCase(correctAnswer5))) ||
                            ((radio72.isSelected()) && (radio72.getText().equalsIgnoreCase(correctAnswer5))))
                    {
                        labelresponse125.setText("Correct answer: +1 Point");
                        //buttons and radio buttons lose functionality
                        button72.setDisable(true);
                        radio52.setDisable(true);
                        radio62.setDisable(true);
                        radio72.setDisable(true);
                        button82.setDisable(false);
                        Maxscore++;//concat the score of the game
                    }
                    else//the selected button is incorrect
                    {
                        labelresponse125.setText("Incorrect! The correct answer is: " + correctAnswer5);
                        button72.setDisable(true);
                        radio52.setDisable(true);
                        radio62.setDisable(true);
                        radio72.setDisable(true);
                        button82.setDisable(false);
                    }
                }
        );

//removing elements from list so they are not repeated
        c.getCountry().remove(random15);
        c.getContinentAnswer().remove(random15 * 2);
        c.getContinentAnswer().remove(random15 * 2 + 1);

// the main layout in the new window is an VBox
        VBox layout35= new VBox(20);
        layout35.setAlignment(Pos.CENTER);// Alligning layout
        layout35.getChildren().addAll(label325, radio52, radio62, radio72, button72, labelresponse125, button82);//initializing layout
        scene5= new Scene(layout35,300,450);// create a new Scene with the new Layout

        this.scene5 = scene5;//initializing Scene5

        button82.setOnAction(e ->//assigning action to Next Question Button
                {
                    setScene6(scene6);
                    quizStage.setScene(scene6);
                    quizStage.setTitle( "Question 6" );
                }
        );
        quizStage.show();//showing quizStage window
    }

    public void setScene6(Scene scene6) {

        Random rn16 = new Random();//creating variable from random class
        int random16 = rn16.nextInt(c.getCountry().size());//creating random int from 0 to the # of countries in the csv file
        int randomAnswer6 = (c.getContinentAnswer().indexOf(c.getCountry().get(random16))) + 1;//creating integer to find index of the random country's answer

        ArrayList<String> list1116 = new ArrayList<String>();//creating an array list
        list1116.add(c.getContinentAnswer().get(randomAnswer6));//adding the country's answer to the list
        int holder = 0;
        while (list1116.size() < 4) {//while loop to add random continents
            int randomnum6 = rn16.nextInt(c.getContinent().size());//random int to grab random continents
            if (!(list1116.contains(c.getContinent().get(randomnum6)))){//checking if the list contains the continent already
                list1116.add(c.getContinent().get(randomnum6));//adding the continent to the list
            }
        }

        int randomnum = rn16.nextInt(3);
        list1116.remove(0);//removing the answer becasuse if not it will be the first choice everytime
        if (randomnum > list1116.size()) {//if the random number is the 3 then we append the answer to the end
            list1116.add(c.getContinentAnswer().get(randomAnswer6));//add to the end of the list
        }
        else {//else add the index of the random number
            list1116.add(randomnum , (c.getContinentAnswer().get(randomAnswer6)));
        }

        String correctAnswer7 = c.getContinentAnswer().get(randomAnswer6);//creating a string with the correct answer

        RadioButton radio52, radio62, radio72, radio82;//initializing radio buttons
        Label label326 = new Label("On which continent is " + c.getCountry().get(random16) + " located?");//initializing variable for the question
        String option5 = list1116.remove(0);//option1 will be the 1st element in the random list created
        radio52 = new RadioButton("" + option5.substring(option5.indexOf(",") + 1));
        // radio5.setAlignment(Pos.CENTER);
        String option6 = list1116.remove(0);//option2 will be the 2nd element in the random list created
        radio62 = new RadioButton("" + option6.substring(option6.indexOf(",") + 1));
        //radio6.setAlignment(Pos.CENTER);
        String option7 = list1116.remove(0);//option3 will be the 3rd element in the random list created
        radio72 = new RadioButton("" + option7.substring(option7.indexOf(",") + 1));
        Button button72 = new Button("Submit");//creating submit button
        Label labelresponse126 = new Label();//creating another label
        Button button82 = new Button("Next Question");//creating next question label
        // button8.setAlignment(Pos.BASELINE_RIGHT);

        ToggleGroup question6 = new ToggleGroup();//allowing only one radio button to be selected

        radio52.setToggleGroup(question6);
        radio62.setToggleGroup(question6);
        radio72.setToggleGroup(question6);

//These buttons cant be clicked while set to true
        button72.setDisable(true);
        button82.setDisable(true);

//allows submit button to be clicked
        radio52.setOnAction(e -> button72.setDisable(false) );
        radio62.setOnAction(e -> button72.setDisable(false) );
        radio72.setOnAction(e -> button72.setDisable(false) );


        button72.setOnAction(e ->//setting action to submit button
                {
//checking if the button selected is correct
                    if (((radio52.isSelected()) && (radio52.getText().equalsIgnoreCase(correctAnswer7))) ||
                            ((radio62.isSelected()) && (radio62.getText().equalsIgnoreCase(correctAnswer7))) ||
                            ((radio72.isSelected()) && (radio72.getText().equalsIgnoreCase(correctAnswer7))))
                    {
                        labelresponse126.setText("Correct answer: +1 Point");
                        //buttons and radio buttons lose functionality
                        button72.setDisable(true);
                        radio52.setDisable(true);
                        radio62.setDisable(true);
                        radio72.setDisable(true);
                        button82.setDisable(false);
                        Maxscore++;//concat the score of the game
                    }

                    else//the selected button is incorrect
                    {
                        labelresponse126.setText("Incorrect! The correct answer is: " + correctAnswer7);
                        button72.setDisable(true);
                        radio52.setDisable(true);
                        radio62.setDisable(true);
                        radio72.setDisable(true);
                        button82.setDisable(false);
                    }
                }
        );

//removing elements from list so they are not repeated
        c.getCountry().remove(random16);
        c.getContinentAnswer().remove(random16 * 2);
        c.getContinentAnswer().remove(random16 * 2 + 1);

// the main layout in the new window is an VBox
        VBox layout37= new VBox(20);
        layout37.setAlignment(Pos.CENTER);// Alligning layout
        layout37.getChildren().addAll(label326, radio52, radio62, radio72, button72, labelresponse126, button82);//initializing layout
        scene6= new Scene(layout37,300,450);// create a new Scene with the new Layout

        this.scene6 = scene6;//initializing Scene6

        button82.setOnAction(e ->//assigning action to Next Question Button
                {
                    isFinished = true;
                    setScene7(scene7);
                    quizStage.setScene(scene7);
                    quizStage.setTitle( "Quiz Results" );
                }
        );
        quizStage.show();//showing quizStage window
    }

    public void setScene7(Scene scene7) {
        String results = ""; //creating an empty String
        if ((Maxscore == 6) && (isFinished = true)) { //If call question were answered correctly
            results = "Congratulations! You scored 6/6!";
            timeStamp = new SimpleDateFormat("MM/dd/yyyy     HH:mm:ss").format(Calendar.getInstance().getTime()); //initializing time and date String

            result.resultsWriteToFile(timeStamp , Maxscore); //adding time, date, and score to the csv file
            Maxscore = 0; //setting score back to zero after adding to the csv file
            isFinished = false;
        }
        else if (isFinished = true){ // else the user didnt get all answers correct
            results = "You scored: " + this.Maxscore + "/6";

            timeStamp = new SimpleDateFormat("MM/dd/yyyy     HH:mm:ss").format(Calendar.getInstance().getTime()); //initalizing time and date String
            result.resultsWriteToFile(timeStamp , Maxscore); //adding time, date, and score to the csv file
            Maxscore = 0; //setting score back to zero after adding to the csv file
            isFinished = false;
        }

        Label label327 = new Label("" + results); //initializing string with results

        Button buttonpg = new Button("Play Again"); //initializing play again button
        Button buttonExit = new Button("Exit"); //initializing Exit button
        Button buttonqr = new Button("View Quiz Results"); //initializing view quiz results button

        buttonExit.setOnAction(this::exitButtonOnAction); //giving action to exit button
        buttonqr.setOnAction(this::prevButtonHandler);//giving action to view quiz results button
        buttonpg.setOnAction(this::playButtonHandler);  //giving action to play again button


        VBox layout327= new VBox(20);// the main layout in the new window is an VBox
        layout327.setAlignment(Pos.CENTER);// Alligning layout
        layout327.getChildren().addAll(label327, buttonpg, buttonqr, buttonExit);//initializing layout

        scene7= new Scene(layout327,300,450);// create a new Scene with the new Layout

        this.scene7 = scene7;//initializing Scene7

        startQuiz = true; //boolean allowing quiz to start again

        quizStage.show();//showing quizStage window
    }

    public void prevButtonHandler( ActionEvent event) {

        result.resultsReadFromFile(); // allow reading from the csv file
        String formattedString = result.resultList.toString() //formatting the result list toString
                .replace(",", "")  //remove the commas
                .replace("!", "\n") //replace ! with new line
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .trim();
        Label label32 = new Label("m"); //Creatign a label
        if (result.resultList.isEmpty() == false) { //if the list is not empty
            label32 = new Label("" + formattedString); //add the list formatted toString to the label
        }
        else { //else if it is empty
            label32 = new Label("You Currently have no Quizzes. Exit and Play to get results."); //empty statement
        }
       result.resultList.clear(); //clearing the list

        Button buttonExit = new Button("Exit"); //Creating exit button
        buttonExit.setAlignment(Pos.CENTER); //allignign button

        Text fs = new Text(formattedString); //creating text for the scrollPane

        ScrollPane scrollPane = new ScrollPane(); //creating new scrollPane
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportWidth(280);
        scrollPane.setContent( fs ); //seting the text to the scrollPane

        buttonExit.setOnAction(this::exitButtonOnAction); //assigning action to the exit button

        VBox layout21 = new VBox(20);// the main layout in the new window is an VBox
        layout21.getChildren().addAll(scrollPane, buttonExit);//initializing layout
       VBox.setMargin( scrollPane, new Insets( 10, 10, 10, 10 ) );
        // provide margins around the ScrollPane inside the HBox

        // create a new Scene with the newLayout
        Scene newScene = new Scene( layout21, 400, 450 );

        // New window (Stage)
        quizStage = new Stage();
        quizStage.setTitle( "Previous Results" );
        quizStage.setScene( newScene );

        // set the modality for the new window;  the main window will be blocked
        quizStage.initModality( Modality.APPLICATION_MODAL );
        // Set position of the song window vs the main window.
        quizStage.setX( primaryStage.getX() + 200);
        quizStage.setY( primaryStage.getY() + 100);

        quizStage.show(); //showing quizStage window
    }

    private void exitButtonOnAction(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    private void helpButtonHandler(ActionEvent event) {
        Label label32 = new Label("Play Button – Starts a game of 6 questions. Each question " +
                "\nconsist of 3 answer choices. At the end of the quiz, you \nwill have the option to play again" +
                ", view your past quiz \nresults, or exit the window."); //creating help label for play button
        Label label33 = new Label("Previous Results Button – List the results and date and " +
                "time \nof the previous quizzes taken. " + "The user will also be \nable to view them once the quiz is finished."); //creating help label for previous results button
        Label label34 = new Label("Quit Button – allows the user to quit and exit the application."); //creating help label for quit button
        Button buttonExit = new Button("Exit"); //creating an exit button
        buttonExit.setAlignment(Pos.BASELINE_CENTER); //alligning Exit button to the center

        buttonExit.setOnAction(this::exitButtonOnAction); //assignign action to the exit button

        VBox layout21 = new VBox(20);// the main layout in the new window is an VBox
        layout21.getChildren().addAll(label32, label33, label34, buttonExit);//initializing layout
        // provide margins around the ScrollPane inside the HBox

        // create a new Scene with the newLayout
        Scene newScene = new Scene( layout21, 415, 450 );

        // New window (Stage)
        quizStage = new Stage();
        quizStage.setTitle( "Help" );
        quizStage.setScene( newScene );

        // set the modality for the new window;  the main window will be blocked
        quizStage.initModality( Modality.APPLICATION_MODAL );
        // Set position of the song window vs the main window.
        quizStage.setX( primaryStage.getX() + 200);
        quizStage.setY( primaryStage.getY() + 100);

        quizStage.show();//showing quizStage window
    }

    public static void main(String[] args) { //main method
        launch(args); //launch
    }

}

