package ku.piii2018.gui1;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.DirectoryChooser;
import ku.piii2018.businesslogic1.FileService;
import ku.piii2018.businesslogic1.FileServiceImpl;
import ku.piii2018.businesslogic1.MediaInfoSource;
import ku.piii2018.businesslogic1.MediaInfoSourceFromID3;
import ku.piii2018.businesslogic1.MediaItem;

public class FXMLController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TableView<MediaItem> tableView1;
    @FXML
    private TableView<MediaItem> tableView2;

    String collectionRootAB = "test_folders" + File.separator + 
                              "original_filenames";
    String collectionRootA = collectionRootAB + File.separator + 
                              "collection-A" ;
    String collectionRootB = collectionRootAB + File.separator + 
                              "collection-B" ;
    
    
    @FXML
    private void handleButton1Action(ActionEvent event) {
        System.out.println("You clicked me!");
      //  label.setText("Hello World!");
    }
    @FXML
    private void handleButton2Action(ActionEvent event) {
        System.out.println("You clicked me!");
       // label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<MediaItemColumnInfo> columns = MediaItemTableViewFactory.makeColumnInfoList();
        MediaItemTableViewFactory.makeTable(tableView1, columns);
        MediaItemTableViewFactory.makeTable(tableView2, columns);
    }    
    @FXML
    private void openABIn2(ActionEvent event) {
        open(2, collectionRootAB);
    }
    @FXML
    private void swap(ActionEvent event) {
        
        ObservableList<MediaItem> table1Data =
                tableView1.getItems();
        ObservableList<MediaItem> table2Data =
                tableView2.getItems();
        tableView1.setItems(table2Data);
        tableView2.setItems(table1Data);
        
        
    }
    @FXML
    private void openIn2(ActionEvent event) {
        open(2, null);
    }
    @FXML
    private void openAIn2(ActionEvent event) {
        open(2, collectionRootA);
    }
    @FXML
    private void openBIn2(ActionEvent event) {
        open(2, collectionRootB);
    }
    @FXML
    private void openABIn1(ActionEvent event) {
        open(1, collectionRootAB);
    }
    @FXML
    private void openIn1(ActionEvent event) {
        open(1, null);
    }
    @FXML
    private void openAIn1(ActionEvent event) {
        open(1, collectionRootA);
    }
    @FXML
    private void openBIn1(ActionEvent event) {
        open(1, collectionRootB);
    }

    private void open(int tableNumber, String collectionRoot) {
        if(collectionRoot==null){
            DirectoryChooser dirChooser = new DirectoryChooser();
            dirChooser.setTitle("Open Media Folder for Table " + tableNumber);
            File path = dirChooser.showDialog(null).getAbsoluteFile();
            collectionRoot = path.toString();
        }
        else {
            String cwd = System.getProperty("user.dir");
            System.out.println(cwd);
            collectionRoot = Paths.get(cwd, 
                                       "..",
                                       collectionRoot).toString();
        }
        TableView<MediaItem> referenceToEitherTable = null;
        if(tableNumber==1){
            referenceToEitherTable = tableView1;
        }
        else if(tableNumber==2) {
            referenceToEitherTable = tableView2;            
        }
        addContents(referenceToEitherTable, collectionRoot);
    }

    private void addContents(TableView<MediaItem> referenceToEitherTable, String collectionRoot) {
        FileService fileService = new FileServiceImpl();
        Set<MediaItem> collectionA = fileService.getAllMediaItems(collectionRoot.toString());
        
        MediaInfoSource myInfoSource = new MediaInfoSourceFromID3();
        for (MediaItem item : collectionA ) {
            try {
                myInfoSource.addMediaInfo(item);
            }
            catch (Exception e)            {
                
            }
        }
        List<MediaItem> currentItems = referenceToEitherTable.getItems();
        collectionA.addAll(currentItems);
        ObservableList<MediaItem> dataForTableViewAndModel =
                FXCollections.observableArrayList(collectionA);
        referenceToEitherTable.setItems(dataForTableViewAndModel);
    }
}
