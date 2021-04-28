package PFA.GestionIntervention.GUIs.ajouterIntervention;

import PFA.GestionIntervention.Modules.Intervention;
import PFA.GestionIntervention.Modules.PersonnelMin;
import PFA.GestionIntervention.Services.InterventionServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Box;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ajouterPersonnelIntervention implements Initializable {
    public Intervention getIntervention() {
        return intervention;
    }
    
    public void setIntervention(Intervention intervention) {
        this.intervention = intervention;
    }
    
    private Intervention intervention;
    
    @FXML
    private TableView<PersonnelMin> PersonnelTV;
    
    @FXML
    private TableColumn<PersonnelMin, String> nomColumn;
    
    @FXML
    private TableColumn<PersonnelMin, String> prenomColumn;
    
    @FXML
    private TableColumn<PersonnelMin, String> posteColumn;
    
    @FXML
    private TableColumn<PersonnelMin, CheckBox> selectColumn;
    
    
    
    public void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ajouterGenerale.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToOutil(ActionEvent event) throws IOException {
        ArrayList<PersonnelMin> liste = new ArrayList<>(PersonnelTV.getItems());
        ArrayList<PersonnelMin> toAdd = new ArrayList<>();
        for (PersonnelMin p: liste){
            if (p.getCheck().isSelected()){
                toAdd.add(p);
            }
        }
        
        if (!toAdd.isEmpty()){
            intervention.setEquipe(toAdd);
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Aucun personnel selectione");
            alert.showAndWait();
            return ;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ajouterVehiculeIntervention.fxml"));
        Parent root = loader.load();
        ajouterVehiculeIntervention controller = loader.getController();
        controller.intervention = getIntervention();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        posteColumn.setCellValueFactory(new PropertyValueFactory<>("poste"));
        selectColumn.setCellValueFactory(new PropertyValueFactory<>("check"));
        ArrayList<PersonnelMin> liste = (ArrayList<PersonnelMin>) InterventionServices.ParsePersonnelListe();
        for (PersonnelMin p:liste){
            p.setCheck(new CheckBox());
        }
        PersonnelTV.getItems().setAll(liste);
    }
    
    
    
}