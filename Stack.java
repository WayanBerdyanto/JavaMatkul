
package praktikum_asd;

public class Stack {

    private Batas[] data;
    private int top;

    public Stack(int ukuran) {
        this.data = new Batas[ukuran];
        top = -1; 
    }

    public void push(int kiri, int kanan) {
        if (this.top < this.data.length - 1) {
            this.data[++this.top] = new Batas(kiri, kanan);
        } else {
            System.out.println("Stack Sudah Penuh");
        }
    }

    public Boolean habis() {
        return this.top < 0;
    }

    public Batas pop() {
        if (!this.habis()) {
            return this.data[this.top--];
        } else {
            System.out.println("Stack Masih Kosong");
            return new Batas(0, 0);
        }
    }

    public class Batas {

        public int kiri, kanan;

        public Batas(int kiri, int kanan) {
            this.kiri = kiri;
            this.kanan = kanan;
        }
    }

}
