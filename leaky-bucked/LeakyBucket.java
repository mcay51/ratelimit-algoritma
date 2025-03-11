/**
 * LeakyBucket (Sizdiran Kova) algoritmasi
 * 
 * Bu algoritma, bir sisteme gelen istekleri sinirlamak icin kullanilir.
 * Kova benzetmesi ile calisir: Istekler kovaya damla damla dolar, 
 * kova ise sabit bir hizda sizdirarak (isleyerek) bosalir.
 */
public class LeakyBucket {
    private final int kapasite;       // Kovanin maksimum kapasitesi
    private final int sizdirmaHizi;   // Saniyede islenecek istek sayisi
    private int suMiktari;            // Kovadaki mevcut istek sayisi
    private long sonSizdirmaZamani;   // Son sizinti zamani

    /**
     * LeakyBucket sinifinin yapici metodu
     * 
     * @param kapasite Kovanin maksimum alabilecegi istek sayisi
     * @param sizdirmaHizi Saniyede islenecek istek sayisi
     */
    public LeakyBucket(int kapasite, int sizdirmaHizi) {
        this.kapasite = kapasite;
        this.sizdirmaHizi = sizdirmaHizi;
        this.suMiktari = 0;
        this.sonSizdirmaZamani = System.currentTimeMillis();
    }

    /**
     * Yeni bir istegin kabul edilip edilmeyecegini kontrol eder
     * 
     * @return Istek kabul edildiyse true, reddedildiyse false
     */
    public synchronized boolean istekKabulEt() {
        sizdir(); // Once sizintiyi hesapla

        // Kova kapasitesi doluysa istegi reddet
        if (suMiktari >= kapasite) {
            return false;
        }

        // Istegi kovaya ekle
        suMiktari++;
        return true;
    }

    /**
     * Gecen sureye gore kovadan sizan (islenen) istek miktarini hesaplar
     */
    private void sizdir() {
        long simdi = System.currentTimeMillis();
        long gecenSure = simdi - sonSizdirmaZamani;
        
        // Gecen surede bosaltilmasi gereken istek sayisini hesapla
        int sizanIstekSayisi = (int) ((gecenSure / 1000.0) * sizdirmaHizi);
        
        if (sizanIstekSayisi > 0) {
            // Kovadan sizan suyu guncelle
            suMiktari = Math.max(0, suMiktari - sizanIstekSayisi);
            sonSizdirmaZamani = simdi;
        }
    }

    /**
     * Kovadaki mevcut istek sayisini dondurur
     */
    public int getSuMiktari() {
        return suMiktari;
    }

    /**
     * Test icin ornek kullanim
     */
    public static void main(String[] args) throws InterruptedException {
        // 10 kapasiteli ve saniyede 2 istek isleyen bir kova olustur
        LeakyBucket kova = new LeakyBucket(10, 2);

        System.out.println("LeakyBucket Algoritmasi Test");
        System.out.println("Kapasite: 10 istek, Sizdirma Hizi: 2 istek/saniye");
        System.out.println("--------------------------------------------");

        // Ornek istekler gonder
        for (int i = 0; i < 15; i++) {
            boolean kabulEdildi = kova.istekKabulEt();
            System.out.println("Istek " + (i + 1) + ": " + 
                             (kabulEdildi ? "Kabul edildi ✓" : "Reddedildi ✗") + 
                             " (Kovadaki istek: " + kova.getSuMiktari() + ")");
            
            Thread.sleep(200); // 200ms bekle
        }
        
        System.out.println("--------------------------------------------");
        System.out.println("Bekleyip kovadaki isteklerin sizmasini izleyelim...");
        
        // Kovadaki isteklerin sizmasini izle
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000); // 1 saniye bekle
            kova.sizdir(); // Sizdirma islemini manuel olarak cagir
            System.out.println((i+1) + ". saniye sonra kovadaki istek sayisi: " + kova.getSuMiktari());
        }
    }
} 