/**
 * TokenBucket algoritmasini farkli senaryolarda test eden sinif
 */
public class TokenBucketTest {
    
    /**
     * Ani yuk artisi senaryosu
     * Kisa surede cok sayida istek gonderildiginde algoritmanin davranisini test eder
     */
    public static void aniYukArtisiTesti() throws InterruptedException {
        System.out.println("\n=== ANI YUK ARTISI TESTI ===");
        System.out.println("Kapasite: 5 jeton, Jeton Ekleme Hizi: 1 jeton/saniye");
        System.out.println("Senaryo: Kisa surede 10 istek gonderiliyor");
        
        TokenBucket kova = new TokenBucket(5, 1);
        
        // Kisa surede 10 istek gonder
        for (int i = 0; i < 10; i++) {
            boolean kabulEdildi = kova.istekKabulEt();
            System.out.println("Istek " + (i + 1) + ": " + 
                             (kabulEdildi ? "Kabul edildi (OK)" : "Reddedildi (X)") + 
                             " (Kovadaki jeton: " + String.format("%.2f", kova.getMevcutJetonSayisi()) + ")");
            
            Thread.sleep(100); // 100ms bekle
        }
    }
    
    /**
     * Duzenli trafik senaryosu
     * Duzenli araliklarla gelen isteklerin davranisini test eder
     */
    public static void duzenliTrafikTesti() throws InterruptedException {
        System.out.println("\n=== DUZENLI TRAFIK TESTI ===");
        System.out.println("Kapasite: 5 jeton, Jeton Ekleme Hizi: 2 jeton/saniye");
        System.out.println("Senaryo: Her 500ms'de bir istek gonderiliyor");
        
        TokenBucket kova = new TokenBucket(5, 2);
        
        // 5 saniye boyunca her 500ms'de bir istek gonder
        for (int i = 0; i < 10; i++) {
            boolean kabulEdildi = kova.istekKabulEt();
            System.out.println("Istek " + (i + 1) + " (" + (i * 500) + "ms): " + 
                             (kabulEdildi ? "Kabul edildi (OK)" : "Reddedildi (X)") + 
                             " (Kovadaki jeton: " + String.format("%.2f", kova.getMevcutJetonSayisi()) + ")");
            
            Thread.sleep(500); // 500ms bekle
        }
    }
    
    /**
     * Yuksek jeton ekleme hizi senaryosu
     * Jeton ekleme hizi yuksek oldugunda algoritmanin davranisini test eder
     */
    public static void yuksekJetonEklemeHiziTesti() throws InterruptedException {
        System.out.println("\n=== YUKSEK JETON EKLEME HIZI TESTI ===");
        System.out.println("Kapasite: 10 jeton, Jeton Ekleme Hizi: 5 jeton/saniye");
        System.out.println("Senaryo: Her 200ms'de bir istek gonderiliyor");
        
        TokenBucket kova = new TokenBucket(10, 5);
        
        // Her 200ms'de bir istek gonder
        for (int i = 0; i < 15; i++) {
            boolean kabulEdildi = kova.istekKabulEt();
            System.out.println("Istek " + (i + 1) + " (" + (i * 200) + "ms): " + 
                             (kabulEdildi ? "Kabul edildi (OK)" : "Reddedildi (X)") + 
                             " (Kovadaki jeton: " + String.format("%.2f", kova.getMevcutJetonSayisi()) + ")");
            
            Thread.sleep(200); // 200ms bekle
        }
    }
    
    /**
     * Jeton yenileme senaryosu
     * Jetonlarin zamanla nasil yenilendigini gosterir
     */
    public static void jetonYenilemeTesti() throws InterruptedException {
        System.out.println("\n=== JETON YENILEME TESTI ===");
        System.out.println("Kapasite: 5 jeton, Jeton Ekleme Hizi: 1 jeton/saniye");
        System.out.println("Senaryo: Tum jetonlar harcanir ve yenilenmesi beklenir");
        
        TokenBucket kova = new TokenBucket(5, 1);
        
        // Tum jetonlari harca
        System.out.println("Baslangic jeton sayisi: " + String.format("%.2f", kova.getMevcutJetonSayisi()));
        for (int i = 0; i < 5; i++) {
            kova.istekKabulEt();
        }
        System.out.println("Tum jetonlar harcandi: " + String.format("%.2f", kova.getMevcutJetonSayisi()));
        
        // Jetonlarin yenilenmesini izle
        for (int i = 0; i < 6; i++) {
            Thread.sleep(1000); // 1 saniye bekle
            System.out.println((i+1) + ". saniye sonra jeton sayisi: " + 
                             String.format("%.2f", kova.getMevcutJetonSayisi()));
        }
    }
    
    /**
     * Ana test metodu
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("TokenBucket Algoritmasi Test Senaryolari");
        System.out.println("========================================");
        
        // Farkli senaryolari test et
        aniYukArtisiTesti();
        Thread.sleep(2000); // Testler arasinda 2 saniye bekle
        
        duzenliTrafikTesti();
        Thread.sleep(2000); // Testler arasinda 2 saniye bekle
        
        yuksekJetonEklemeHiziTesti();
        Thread.sleep(2000); // Testler arasinda 2 saniye bekle
        
        jetonYenilemeTesti();
    }
} 