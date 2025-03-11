/**
 * LeakyBucket algoritmasini farkli senaryolarda test eden sinif
 */
public class LeakyBucketTest {
    
    /**
     * Ani yuk artisi senaryosu
     * Kisa surede cok sayida istek gonderildiginde algoritmanin davranisini test eder
     */
    public static void aniYukArtisiTesti() throws InterruptedException {
        System.out.println("\n=== ANI YUK ARTISI TESTI ===");
        System.out.println("Kapasite: 5 istek, Sizdirma Hizi: 1 istek/saniye");
        System.out.println("Senaryo: Kisa surede 10 istek gonderiliyor");
        
        LeakyBucket kova = new LeakyBucket(5, 1);
        
        // Kisa surede 10 istek gonder
        for (int i = 0; i < 10; i++) {
            boolean kabulEdildi = kova.istekKabulEt();
            System.out.println("Istek " + (i + 1) + ": " + 
                             (kabulEdildi ? "Kabul edildi ✓" : "Reddedildi ✗") + 
                             " (Kovadaki istek: " + kova.getSuMiktari() + ")");
            
            Thread.sleep(100); // 100ms bekle
        }
    }
    
    /**
     * Duzenli trafik senaryosu
     * Duzenli araliklarla gelen isteklerin davranisini test eder
     */
    public static void duzenliTrafikTesti() throws InterruptedException {
        System.out.println("\n=== DUZENLI TRAFIK TESTI ===");
        System.out.println("Kapasite: 5 istek, Sizdirma Hizi: 2 istek/saniye");
        System.out.println("Senaryo: Her 500ms'de bir istek gonderiliyor");
        
        LeakyBucket kova = new LeakyBucket(5, 2);
        
        // 5 saniye boyunca her 500ms'de bir istek gonder
        for (int i = 0; i < 10; i++) {
            boolean kabulEdildi = kova.istekKabulEt();
            System.out.println("Istek " + (i + 1) + " (" + (i * 500) + "ms): " + 
                             (kabulEdildi ? "Kabul edildi ✓" : "Reddedildi ✗") + 
                             " (Kovadaki istek: " + kova.getSuMiktari() + ")");
            
            Thread.sleep(500); // 500ms bekle
        }
    }
    
    /**
     * Yuksek sizdirma hizi senaryosu
     * Sizdirma hizi yuksek oldugunda algoritmanin davranisini test eder
     */
    public static void yuksekSizdirmaHiziTesti() throws InterruptedException {
        System.out.println("\n=== YUKSEK SIZDIRMA HIZI TESTI ===");
        System.out.println("Kapasite: 10 istek, Sizdirma Hizi: 5 istek/saniye");
        System.out.println("Senaryo: Her 200ms'de bir istek gonderiliyor");
        
        LeakyBucket kova = new LeakyBucket(10, 5);
        
        // Her 200ms'de bir istek gonder
        for (int i = 0; i < 15; i++) {
            boolean kabulEdildi = kova.istekKabulEt();
            System.out.println("Istek " + (i + 1) + " (" + (i * 200) + "ms): " + 
                             (kabulEdildi ? "Kabul edildi ✓" : "Reddedildi ✗") + 
                             " (Kovadaki istek: " + kova.getSuMiktari() + ")");
            
            Thread.sleep(200); // 200ms bekle
        }
    }
    
    /**
     * Ana test metodu
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("LeakyBucket Algoritmasi Test Senaryolari");
        System.out.println("========================================");
        
        // Farkli senaryolari test et
        aniYukArtisiTesti();
        Thread.sleep(2000); // Testler arasinda 2 saniye bekle
        
        duzenliTrafikTesti();
        Thread.sleep(2000); // Testler arasinda 2 saniye bekle
        
        yuksekSizdirmaHiziTesti();
    }
} 