import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * Use this template to create Apps with Graphical User Interfaces.
 *
 * @author Jainam Patel
 */
public class TicketScanningApp extends Application {

    // TODO: Instance Variables for View Components and Model

    //instance variable for reddem button
    private Button redeemButton;

    //Label for title of the prograam
    private Label appTitle;

    //private tetfield to take the input

    private TextField inputBox;

    private Table t = new Table("codes.txt");

    // Label to announce whether the ticket is valid or not
    private Label validator;

    //button to reset all tickets to unredeemed
    private Button resetButton;


    private ArrayList <String> ticketList = new ArrayList<>(150);





    // TODO: Private Event Handlers and Helper Methods


    /*methods to indicate the status of a ticket code whether it is valid or not*/
    private void invalidSound () {
        MediaPlayer player = new MediaPlayer
                (new Media("file:///"+System.getProperty("user.dir").replace('\\','/').replaceAll(" ",
                        "%20")+"/" + "invalid.wav") );
        player.play();

    }

    private void validSound() {
        MediaPlayer player = new MediaPlayer
                (new Media("file:///"+System.getProperty("user.dir").replace('\\','/').replaceAll(" ",
                        "%20")+"/" + "valid.wav") );
        player.play();

    }



    /*the private method to check the validity of the ticket*/
    private void codeValidator (ActionEvent e) {
        String str;
        str = inputBox.getText().toUpperCase();

        if (!str.isEmpty()){
            String [] newCode = t.getMatches(str.toUpperCase());

            if (newCode != null ) {
                if(ticketList.contains(str)) {
                    validator.setStyle("-fx-text-fill:red;");
                    validator.setFont(new Font(20));
                    validator.setText("The code is already redeemed");
                    invalidSound();

                }
                else {
                    ticketList.add(str);
                    validator.setStyle("-fx-text-fill:green;");
                    validator.setFont(new Font(25));
                    validator.setText("The code " + str.toUpperCase() + " is valid");
                    t.change(str , "Y");
                    t.save();
                    validSound();
                }


            }
            else {
                validator.setStyle("-fx-text-fill:red;");
                validator.setFont(new Font(20));
                validator.setText("The given code is invalid");
                invalidSound();
            }
        }
        else {
            validator.setText("Please enter the code");
            validSound();
        }
    }


    /*private method to */
    private void resetButton (ActionEvent e) {
            for(int i =0 ; i<t.getNumRows();i++) {
                t.change("Y",2,"N");


            }
            validator.setStyle("-fx-text-fill:green;");
            validator.setFont(new Font(26));
            validator.setText("All the tickets have been reset");
             t.save();
    }



    /**
     * This is where you create your components and the model and add event
     * handlers.
     *
     * @param stage The main stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, 400, 225); // set the size here
        stage.setTitle("Ticket Scanning App"); // set the window title here
        stage.setScene(scene);
        // TODO: Add your GUI-building code here

        // 1. Create the model
        // 2. Create the GUI components
            appTitle = new Label("Ticket Scanning App");
            redeemButton = new Button("Redeem");
            inputBox = new TextField();
            validator = new Label();
            resetButton = new Button("Reset");




        // 3. Add components to the root
        root.getChildren().addAll(appTitle , redeemButton , inputBox , validator , resetButton);
        // 4. Configure the components (colors, fonts, size, location)
        appTitle.relocate(70,50);
        appTitle.setFont(new Font("Helvetica" , 25));
        appTitle.setStyle("-fx-text-fill:blue;");



        redeemButton.relocate(200,100);
        inputBox.relocate(80,100);
        inputBox.setPrefWidth(100);
        resetButton.relocate(270,100);


        validator.relocate(80,150);
        // 5. Add Event Handlers and do final setup
        redeemButton.setOnAction(this::codeValidator);
        resetButton.setOnAction(this::resetButton);
        // 6. Show the stage
        stage.show();
    }

    /**
     * Make no changes here.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}