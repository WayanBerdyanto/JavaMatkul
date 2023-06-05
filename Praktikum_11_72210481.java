package praktikum_asd;
//@author asus

import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import praktikum_asd.Stack.Batas;

public class Praktikum_11_72210481 extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    TableView<Data_KTP> ktp;
    private FileTeks file;
    boolean baru = true;

    void setKolom() {

        TableColumn<Data_KTP, String> kolNik = new TableColumn("NIK");
        kolNik.setCellValueFactory(new PropertyValueFactory<>("nik"));
        kolNik.setMaxWidth(160);
        kolNik.setStyle("-fx-alignment:center");

        TableColumn<Data_KTP, String> kolNama = new TableColumn("Nama");
        kolNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        kolNama.setMinWidth(150);

        TableColumn<Data_KTP, String> kolTmpLahir = new TableColumn("Tempat & Tgl Lahir");
        kolTmpLahir.setCellValueFactory(new PropertyValueFactory<>("tmpLahir"));
        kolTmpLahir.setMinWidth(155);
        kolTmpLahir.setStyle("-fx-alignment:center");

        TableColumn<Data_KTP, String> kolJenisKel = new TableColumn("Jenis Kelamin");
        kolJenisKel.setCellValueFactory(new PropertyValueFactory<>("jenisKel"));
        kolJenisKel.setMinWidth(150);
        kolJenisKel.setStyle("-fx-alignment:center");

        TableColumn<Data_KTP, String> kolAlamat = new TableColumn("Alamat");
        kolAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        kolAlamat.setMinWidth(160);
        kolAlamat.setStyle("-fx-alignment:center");

        TableColumn<Data_KTP, String> kolStatus = new TableColumn("Status");
        kolStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        kolStatus.setMinWidth(160);
        kolStatus.setStyle("-fx-alignment:center");
        ktp = new TableView<>();
        ktp.setItems(getRow());
        ktp.getColumns().addAll(kolNik, kolNama, kolTmpLahir, kolJenisKel, kolAlamat, kolStatus);
    }

    TextField txtNik = new TextField();
    TextField txtNama = new TextField();
    TextField txtTmpLahir = new TextField();
    ComboBox cbJenisKel = new ComboBox();
    TextField txtAlamat = new TextField();
    ComboBox cbStatus = new ComboBox();
    Label lblQuickSort = new Label("Order by");
    ComboBox cbQuickSort = new ComboBox();
    VBox vb = new VBox(10);
    HBox hbqs = new HBox(5);
    HBox hb = new HBox(5);
    HBox hb1 = new HBox(10);
    HBox hb2 = new HBox(10);
    Button btnAdd = new Button("_Add");
    Button btnUpdate = new Button("_Update");
    Button btnQuickSort = new Button("_QuickSort");
    Button btnDelete = new Button("_Delete");
    Button btnSave = new Button("_Save");
    Button btnFind = new Button("_Find");
    Button btnFilter = new Button("_Filter");
    Button btnUndo = new Button("_Undo");
    Button btnClose = new Button("_Close");
    Region reg = new Region();

    private void inisialisasiKontrol() {
        txtNik.setPromptText("NIK");
        txtNik.setMaxWidth(150);
        txtNama.setPromptText("Nama");
        txtNama.setMaxWidth(150);
        txtTmpLahir.setPromptText("Tempat & Tgl Lahir");
        txtTmpLahir.setMaxWidth(155);
        cbJenisKel.getItems().addAll("Laki-Laki",
                "Perempuan");
        cbJenisKel.setValue("Laki-Laki");
        txtAlamat.setPromptText("Alamat");
        txtAlamat.setMaxWidth(160);
        cbStatus.getItems().addAll("Kawin",
                "Belum Kawin");
        cbStatus.setValue("Kawin");
        cbQuickSort.getItems().addAll("Nik", "Nama", "TTL");
        cbQuickSort.setValue("Nik");
        hbqs.getChildren().addAll(lblQuickSort, cbQuickSort,btnQuickSort);
        hb.getChildren().addAll(txtNik, txtNama, txtTmpLahir, cbJenisKel, txtAlamat, cbStatus);
        HBox.setHgrow(reg, Priority.ALWAYS);
        hb1.getChildren().addAll(btnAdd, btnUpdate, btnDelete, btnFind, btnFilter, reg, btnClose);
        hb2.getChildren().addAll(btnSave, btnUndo);
        hb.setPadding(new Insets(0, 5, 2, 5));
        vb.getChildren().addAll(ktp, hb,hbqs, hb1, hb2);
    }

    private void add() {
        String nik, nama, tempatLahir, jenisKelamin, alamat, status;
        nik = txtNik.getText();
        nama = txtNama.getText();
        tempatLahir = txtTmpLahir.getText();
        jenisKelamin = String.valueOf(cbJenisKel.getValue());
        alamat = txtAlamat.getText();
        status = String.valueOf(cbStatus.getValue());

        Data_KTP dataKtp = new Data_KTP(nik, nama, tempatLahir, jenisKelamin, alamat, status);
        ktp.getItems().add(dataKtp);
        txtNik.clear();
        txtNama.clear();
        txtTmpLahir.clear();
        txtAlamat.clear();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informasi");
        alert.setHeaderText("Menambah Data Berhasil");
        alert.setContentText(
                "Terimakasih");
        alert.showAndWait();

    }

    private void delete() {
        ObservableList<Data_KTP> pilih, semua;
        semua = ktp.getItems();
        pilih = ktp.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfimrasi");
        alert.setHeaderText("Mohon Konfirmasi");
        alert.setContentText("Apakah Anda yakin untuk Menghapus ?");

        Optional<ButtonType> jwb = alert.showAndWait();
        if (jwb.get() == ButtonType.OK) {
            pilih.forEach(semua::remove);
            file.tulis(getMhs());
        } else if (jwb.get() == ButtonType.OK) {
            System.out.println("Pengguna setuju");
        } else {
            System.out.println("Pengguna menolak");
        }

    }

    private void update() {
        String nik, nama, tempatLahir, jenisKelamin, alamat, status;
        ObservableList<Data_KTP> semua;
        semua = ktp.getItems();
        ObservableList<Data_KTP> pilih = ktp.getSelectionModel().getSelectedItems();
        nik = pilih.get(0).getNik();
        nama = pilih.get(0).getNama();
        tempatLahir = String.valueOf(pilih.get(0).getTmpLahir());
        jenisKelamin = String.valueOf(pilih.get(0).getJenisKel());
        alamat = String.valueOf(pilih.get(0).getAlamat());
        status = String.valueOf(pilih.get(0).getStatus());
        txtNik.setText(nik);
        txtNama.setText(nama);
        txtTmpLahir.setText(tempatLahir);
        cbJenisKel.setValue(jenisKelamin);
        txtAlamat.setText(alamat);
        cbStatus.setValue(status);
        txtNik.requestFocus();
        Alert alertUpdate = new Alert(Alert.AlertType.CONFIRMATION);
        alertUpdate.setTitle("Konfimrasi");
        alertUpdate.setHeaderText("Mohon Konfirmasi");
        alertUpdate.setContentText("Apakah Anda yakin untuk Mengupdate Data ?");
        Optional<ButtonType> jwb = alertUpdate.showAndWait();
        if (jwb.get() == ButtonType.OK) {
            pilih.forEach(semua::remove);
        } else if (jwb.get() == ButtonType.OK) {
            System.out.println("Pengguna setuju");
        } else {
            System.out.println("Pengguna menolak");
        }
    }

    public ObservableList<Data_KTP> getRow() {
        ObservableList<Data_KTP> data = FXCollections.observableArrayList();
        this.file = new FileTeks("src/praktikum_asd/DataKtp.csv");
        String[] arrCvv = this.file.bacaBaris();
        String[] row;
        String nik, nama, tempatLahir, jenisKelamin, alamat, status;
        for (int i = 0; i < arrCvv.length; i++) {
            row = arrCvv[i].split(",");
            nik = row[0];
            nama = row[1];
            tempatLahir = row[2];
            jenisKelamin = row[3];
            alamat = row[4];
            status = row[5];
            data.add(new Data_KTP(nik, nama, tempatLahir, jenisKelamin, alamat, status));
        }
        return data;
    }

    public String getMhs() {
        String semua = "";
        for (int i = 0; i < this.ktp.getItems().size(); i++) {
            String nik = String.valueOf(ktp.getItems().get(i).getNik());
            String nama = String.valueOf(ktp.getItems().get(i).getNama());
            String tempatLahir = String.valueOf(ktp.getItems().get(i).getTmpLahir());
            String jenisKelamin = String.valueOf(ktp.getItems().get(i).getJenisKel());
            String alamat = String.valueOf(ktp.getItems().get(i).getAlamat());
            String status = String.valueOf(ktp.getItems().get(i).getStatus());
            semua += nik + "," + nama + "," + tempatLahir + "," + jenisKelamin + "," + alamat + "," + status + "\n";

        }
        return semua;
    }

    public void getSave() {
        add();
        this.file.tulis(getMhs());

    }

    private void close() {

        Alert alertClose = new Alert(Alert.AlertType.CONFIRMATION);
        alertClose.setTitle("Konfimrasi");
        alertClose.setHeaderText("Mohon Konfirmasi");
        alertClose.setContentText("Apakah Anda yakin untuk Menutup ?");
        Optional<ButtonType> jwb = alertClose.showAndWait();
        if (jwb.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setKolom();
        inisialisasiKontrol();
        Scene sc = new Scene(vb, 900, 500);
        primaryStage.setTitle("DATA KTP");
        primaryStage.setScene(sc);
        primaryStage.show();
        btnAdd.setOnAction(e -> add());
        btnDelete.setOnAction(e -> delete());
        btnUpdate.setOnAction(e -> update());
        btnClose.setOnAction(e -> close());
        btnFind.setOnAction(e -> binarySearch());
        btnUndo.setOnAction(e -> undo());
        btnFilter.setOnAction(e -> dialogBoxFilter());
        btnSave.setOnAction(e -> getSave());
        btnQuickSort.setOnAction(e-> quickSort());
    }

    void undo() {
        if (baru == true) {
            txtAlamat.clear();
            txtNama.clear();
            txtNik.clear();
            txtTmpLahir.clear();
        } else {
            String nik, nama, tempatLahir, jenisKelamin, alamat, status;
            nik = txtNik.getText();
            nama = txtNama.getText();
            tempatLahir = txtTmpLahir.getText();
            jenisKelamin = String.valueOf(cbJenisKel.getValue());
            alamat = txtAlamat.getText();
            status = String.valueOf(cbStatus.getValue());
            Data_KTP dataKtp = new Data_KTP(nik, nama, tempatLahir, jenisKelamin, alamat, status);
            ktp.getItems().add(dataKtp);
            txtAlamat.clear();
            txtNama.clear();
            txtNama.clear();
            txtTmpLahir.clear();
        }

    }

    void ascendingNik() {
        ktp.getItems().sort(Comparator.comparing(Data_KTP::getNik));
    }

    void ascendingNama() {
        ktp.getItems().sort(Comparator.comparing(Data_KTP::getNama));
    }

    void binarySearch() {
        ComboBox cbNik = new ComboBox();
        cbNik.getItems().addAll("Nik",
                "Nama", "TTL");
        cbNik.setValue("Nik");
        TextField txt = new TextField();
        txt.setPromptText("Masukan NIK dan Nama");
        txt.setMaxWidth(150);

        Button btnCari = new Button("_Find");
        HBox hb = new HBox(5, cbNik, txt, btnCari);
        hb.setPadding(new Insets(15, 20, 15, 20));
        Stage window = new Stage();
        window.setScene(new Scene(hb));
        window.initModality(Modality.APPLICATION_MODAL);
        btnCari.setOnAction(e -> window.close());
        window.showAndWait();
        String baca = "";
        String cariNim = String.valueOf(txt.getText());
        String cariNama = String.valueOf(txt.getText());
        ObservableList<Data_KTP> dataKtp = ktp.getItems();

        int cacah = dataKtp.size();
        int min, max, mid = 0, x = 0;
        min = 0;
        max = cacah - 1;
        if (cbNik.getValue() == "Nik") {
            while (min <= max) {
                mid = (min + max) / 2;
                baca = dataKtp.get(mid).getNik();
                x++;
                if (baca.equals(cariNim)) {
                    break;
                }
                if (baca.compareTo(cariNim) < 0) {
                    min = mid + 1;
                } else {
                    max = mid - 1;
                }

            }
            if (baca.equals(cariNim)) {
                ktp.getSelectionModel().select(mid);
                ktp.scrollTo(mid);
                ktp.requestFocus();
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Sequential  Search : " + mid + " kali iterasi \n" + "Binary Search : " + x + " kali iterasi");
                a.show();
            } else {
                Alert a = new Alert(Alert.AlertType.WARNING, "Nim " + cariNim + " tidak ditemukan!");
                a.show();
            }
        } else if (cbNik.getValue() == "Nama") {
            while (min <= max) {
                mid = (min + max) / 2;
                baca = dataKtp.get(mid).getNama();
                x++;
                if (baca.equals(cariNama)) {
                    break;
                }
                if (baca.compareTo(cariNama) < 0) {
                    min = mid + 1;
                } else {
                    max = mid - 1;
                }

            }
            if (baca.equals(cariNama)) {
                ktp.getSelectionModel().select(mid);
                ktp.scrollTo(mid);
                ktp.requestFocus();
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Sequential  Search : " + mid + " kali iterasi \n" + "Binary Search : " + x + " kali iterasi");
                a.show();
            } else {
                Alert a = new Alert(Alert.AlertType.WARNING, "Nama " + cariNama + " tidak ditemukan!");
                a.show();
            }
        }

    }
    public void quickSort() {
        ObservableList<Data_KTP> dataKtp = ktp.getItems();
        Stack stck = new Stack(20);
        int cacah = dataKtp.size();
        stck.push(0, cacah - 1);
        String sementara;
        int x, y, pivot;

        while (!stck.habis()) {
            Batas bt = stck.pop();
            x = bt.kiri;
            y = bt.kanan;
            pivot = x;
            while (x < y) {
                if (cbQuickSort.getValue().equals("Nik")) {
                    while (dataKtp.get(y).getNik().compareTo(dataKtp.get(pivot).getNik()) >= 0 && y > pivot) {
                        y--;
                    }
                    sementara = dataKtp.get(pivot).getNik();
                    dataKtp.get(pivot).getNik().compareTo(dataKtp.get(y).getNik());
                    dataKtp.get(y).getNik().compareTo(sementara);
                    pivot = y;
                    while (dataKtp.get(x).getNik().compareTo(dataKtp.get(pivot).getNik()) < 0 && x < pivot) {
                        x++;
                    }
                    sementara = dataKtp.get(pivot).getNik();
                    dataKtp.get(pivot).getNik().compareTo(dataKtp.get(x).getNik());
                    dataKtp.get(x).getNik().compareTo(sementara);
                    pivot = x;
                    Collections.swap(dataKtp, x, y);
                } else if (cbQuickSort.getValue().equals("Nama")) {
                    while (dataKtp.get(y).getNama().compareTo(dataKtp.get(pivot).getNama()) >= 0 && y > pivot) {
                        y--;
                    }
                    sementara = dataKtp.get(pivot).getNama();
                    dataKtp.get(pivot).getNama().compareTo(dataKtp.get(y).getNama());
                    dataKtp.get(y).getNama().compareTo(sementara);
                    pivot = y;
                    while (x==pivot&& x<pivot) {
                        x++;
                    }
                    sementara = dataKtp.get(pivot).getNama();
                    dataKtp.get(pivot).getNama().compareTo(dataKtp.get(x).getNama());
                    dataKtp.get(x).getNama().compareTo(sementara);
                    pivot = x;
                    Collections.swap(dataKtp, x, y);
                } else if (cbQuickSort.getValue().equals("TTL")) {
                    while (dataKtp.get(y).getTmpLahir().compareTo(dataKtp.get(pivot).getTmpLahir()) >= 0 && y > pivot) {
                        y--;
                    }
                    sementara = dataKtp.get(pivot).getTmpLahir();
                    dataKtp.get(pivot).getTmpLahir().compareTo(dataKtp.get(y).getTmpLahir());
                    dataKtp.get(y).getTmpLahir().compareTo(sementara);
                    pivot = y;
                    while (dataKtp.get(x).getTmpLahir().compareTo(dataKtp.get(pivot).getTmpLahir()) < 0 && x < pivot) {
                        x++;
                    }
                    sementara = dataKtp.get(pivot).getTmpLahir();
                    dataKtp.get(pivot).getTmpLahir().compareTo(dataKtp.get(x).getTmpLahir());
                    dataKtp.get(x).getTmpLahir().compareTo(sementara);
                    pivot = x;
                    Collections.swap(dataKtp, x, y);
                }
            }
            if (bt.kiri < pivot - 1) {
                stck.push(bt.kiri, pivot - 1);
            }
            if (bt.kanan > pivot + 1) {
                stck.push(pivot + 1, bt.kanan);
            }
        }
    }

    void dialogBoxFilter() {
        TextField txtFilter = new TextField();
        txtFilter.setMaxWidth(150);
        txtFilter.setPromptText("masukan_nama");
        Button btnClose = new Button("Close");
        HBox hb3 = new HBox(5, txtFilter, btnClose);
        hb3.setPadding(new Insets(30, 50, 30, 50));
        Stage window = new Stage();
        window.setScene(new Scene(hb3));
        window.initModality(Modality.APPLICATION_MODAL);
        btnClose.setOnAction(e -> window.close());

        FilteredList<Data_KTP> dataTersaring = new FilteredList<>(ktp.getItems(), b -> true);
        txtFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            dataTersaring.setPredicate(mk -> {
                if (mk.getNama().toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                } else {
                    return false;
                }
            });
            ktp.setItems(dataTersaring);

        });
        window.setTitle("Tulis Nama");
        window.setX(500);
        window.setY(200);
        window.showAndWait();
        dataTersaring.setPredicate(t -> true);
    }

}
