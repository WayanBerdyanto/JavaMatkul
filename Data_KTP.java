package praktikum_asd;

public class Data_KTP {

    private String nik;
    private String nama;
    private String tmpLahir;
    private String jenisKel;
    private String alamat;
    private String status;

    public Data_KTP(String nik, String nama, String tmpLahir, String jenisKel, String alamat, String status) {
        this.nik = nik;
        this.nama = nama;
        this.tmpLahir = tmpLahir;
        this.jenisKel = jenisKel;
        this.alamat = alamat;
        this.status = status;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTmpLahir() {
        return tmpLahir;
    }

    public void setTmpLahir(String tmpLahir) {
        this.tmpLahir = tmpLahir;
    }

    public String getJenisKel() {
        return jenisKel;
    }

    public void setJenisKel(String jenisKel) {
        this.jenisKel = jenisKel;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAll() {
        return getNik() + "," + getNama().trim() + "," + getTmpLahir().trim()+ "," + getJenisKel().trim() + "," + getAlamat().trim() +","+ getStatus() + "\n";

    }
}
