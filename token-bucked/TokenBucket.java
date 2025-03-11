/**
 * TokenBucket (Jeton Kovasi) algoritmasi
 * 
 * Bu algoritma, bir sisteme gelen istekleri sinirlamak icin kullanilir.
 * Kova benzetmesi ile calisir: Kovaya belirli araliklarla jeton eklenir,
 * her istek bir jeton harcar. Yeterli jeton yoksa istek reddedilir.
 */
public class TokenBucket {
    private final int kapasite;       // Kovanin maksimum jeton kapasitesi
    private final double jetonEklemeHizi;   // Saniyede eklenen jeton sayisi
    private double mevcutJetonSayisi;       // Kovadaki mevcut jeton sayisi
    private long sonJetonEklemeZamani;      // Son jeton ekleme zamani

    /**
     * TokenBucket sinifinin yapici metodu
     * 
     * @param kapasite Kovanin maksimum alabilecegi jeton sayisi
     * @param jetonEklemeHizi Saniyede eklenen jeton sayisi
     */
    public TokenBucket(int kapasite, double jetonEklemeHizi) {
        this.kapasite = kapasite;
        this.jetonEklemeHizi = jetonEklemeHizi;
        this.mevcutJetonSayisi = kapasite; // Baslangicta kova dolu
        this.sonJetonEklemeZamani = System.currentTimeMillis();
    }

    /**
     * Yeni bir istegin kabul edilip edilmeyecegini kontrol eder
     * 
     * @return Istek kabul edildiyse true, reddedildiyse false
     */
    public synchronized boolean istekKabulEt() {
        jetonEkle(); // Once yeni jetonlari ekle

        // Yeterli jeton yoksa istegi reddet
        if (mevcutJetonSayisi < 1.0) {
            return false;
        }

        // Bir jeton harca
        mevcutJetonSayisi--;
        return true;
    }

    /**
     * Gecen sureye gore kovaya eklenmesi gereken jeton sayisini hesaplar ve ekler
     */
    private void jetonEkle() {
        long simdi = System.currentTimeMillis();
        long gecenSure = simdi - sonJetonEklemeZamani;

        System.out.println("gecenSure: " + TimestampConverter.convertMillisToSeconds(gecenSure) + " saniye");
        
        // Gecen surede eklenmesi gereken jeton sayisini hesapla
        double eklenecekJetonSayisi = (gecenSure / 1000.0) * jetonEklemeHizi;
        System.out.println("eklenecekJetonSayisi: " + eklenecekJetonSayisi);
        
        if (eklenecekJetonSayisi > 0) {
            // Kovaya jeton ekle, ama kapasiteyi asma
            mevcutJetonSayisi = Math.min(kapasite, mevcutJetonSayisi + eklenecekJetonSayisi);
            System.out.println("mevcutJetonSayisi: " + mevcutJetonSayisi);
            sonJetonEklemeZamani = simdi;
            System.out.println("sonJetonEklemeZamani: " + TimestampConverter.convertTimestamp(sonJetonEklemeZamani));
        }
    }

    /**
     * Kovadaki mevcut jeton sayisini dondurur
     */
    public double getMevcutJetonSayisi() {
        jetonEkle(); // Guncel jeton sayisini hesapla
        return mevcutJetonSayisi;
    }

    /**
     * Test icin ornek kullanim
     */
    public static void main(String[] args) throws InterruptedException {
        // 10 kapasiteli ve saniyede 2 jeton eklenen bir kova olustur
        TokenBucket kova = new TokenBucket(10, 2);

        System.out.println("TokenBucket Algoritmasi Test");
        System.out.println("Kapasite: 10 jeton, Jeton Ekleme Hizi: 2 jeton/saniye");
        System.out.println("--------------------------------------------");

        // Ornek istekler gonder
        for (int i = 0; i < 15; i++) {
            boolean kabulEdildi = kova.istekKabulEt();
            System.out.println("Istek " + (i + 1) + ": " + 
                             (kabulEdildi ? "Kabul edildi (OK)" : "Reddedildi (X)") + 
                             " (Kovadaki jeton: " + String.format("%.2f", kova.getMevcutJetonSayisi()) + ")");
            
            Thread.sleep(200); // 200ms bekle
        }
        
        System.out.println("--------------------------------------------");
        System.out.println("Bekleyip kovaya jeton eklenmesini izleyelim...");
        
        // Kovaya jeton eklenmesini izle
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000); // 1 saniye bekle
            System.out.println((i+1) + ". saniye sonra kovadaki jeton sayisi: " + 
                             String.format("%.2f", kova.getMevcutJetonSayisi()));
        }
    }
} 