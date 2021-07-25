/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewController;

import dao.UserDao;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.User;

/**
 * FXML Controller class
 *
 * @author Bilal Sayed
 */
public class LoginScreenController implements Initializable {

    @FXML
    private Insets x1;

    /**
     *
     */
    @FXML
    public TextField usernameTextField;
    @FXML
    public PasswordField passwordTextField;
    @FXML
    private Button loginButton;
    @FXML
    private Label errorLabel;
    @FXML
    private Label loginMainLabel;
    //variable for error text
    String errorLabelText = "Username or Password not found.";
    String Title = "";

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //code to get user locale
        ResourceBundle rbLanguage = ResourceBundle.getBundle("jdbcapp/Nat_es", Locale.getDefault());
        //check user loacale and translate accordingly
        if (Locale.getDefault().getLanguage().equals("es")) {

            errorLabelText = rbLanguage.getString("Username") + " " + rbLanguage.getString("or") + " " + rbLanguage.getString("Password") + " " + rbLanguage.getString("not") + " " + rbLanguage.getString("found");
            loginMainLabel.setText(rbLanguage.getString("Login"));
            usernameTextField.setPromptText(rbLanguage.getString("Username"));
            passwordTextField.setPromptText(rbLanguage.getString("Password"));
            loginButton.setText(rbLanguage.getString("Login"));
        }

    }

    @FXML
    private void loginButtonHandler(ActionEvent event) {
        try {
            //create new user and try to login
            User loggedInUser = new User();
            loggedInUser = UserDao.login(usernameTextField.getText(), passwordTextField.getText());

            if (loggedInUser != null) {
                //set logged in user
                UserDao.setLoggedInUser(loggedInUser);
                //log user log in in log file
                File file = new File("user_log.txt");
                FileWriter fr = new FileWriter(file, true);
                fr.write("USER " + loggedInUser.getUserId() + " " + loggedInUser.getUserName() + " has logged in at " + LocalDateTime.now() + "\n");
                fr.close();

                Parent root = FXMLLoader.load(getClass().
                        getResource("/viewController/mainScreen.fxml"));
                //create a new stage and scene
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                //set the title and modality and set the scene and show it 
                Stage previousStage = (Stage) loginButton.getScene().getWindow();
                previousStage.close();

                stage.setScene(scene);
                stage.setResizable(false);
                stage.getIcons().add(new Image("/img/appIcon.png"));
                stage.setTitle("Scheduling App - Main");;
                stage.show();
            } else {
                //set error label text
                errorLabel.setText(errorLabelText);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
