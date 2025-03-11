# LeakyBucket (Sizdiran Kova) Algoritmasi

Bu proje, rate limiting (hiz sinirlama) icin kullanilan LeakyBucket algoritmasinin Java ile uygulanmis bir ornegini icerir. Algoritma, Turkce aciklamalarla detayli bir sekilde anlatilmistir.

## Algoritma Nedir?

LeakyBucket (Sizdiran Kova) algoritmasi, bir sisteme gelen isteklerin hizini sinirlamak icin kullanilan populer bir rate limiting yontemidir. Algoritma, adindan da anlasilacagi gibi, bir kova benzetmesi uzerine kurulmustur:

1. Sistem, sabit kapasiteli bir kova olarak dusunulur.
2. Gelen her istek, kovaya bir damla su olarak eklenir.
3. Kova, sabit bir hizda altindan sizdirir (istekleri isler).
4. Eger kova doluysa, yeni gelen istekler reddedilir.

Bu benzetme, sistemin ani yuk artislarina karsi korunmasini saglar ve isteklerin sabit bir hizda islenmesini garanti eder.

## Algoritmanin Calisma Prensibi

LeakyBucket algoritmasi su sekilde calisir:

1. **Kapasite**: Kovanin maksimum kapasitesi, sistemin ayni anda isleyebilecegi maksimum istek sayisini belirler.
2. **Sizdirma Hizi**: Kovanin altindan sizan su miktari, sistemin birim zamanda isleyebilecegi istek sayisini belirler.
3. **Istek Kabulu**: Yeni bir istek geldiginde, once gecen sureye gore kovadan ne kadar su sizdigi hesaplanir. Eger kovada yer varsa istek kabul edilir, yoksa reddedilir.

## Kodun Aciklamasi

`LeakyBucket.java` dosyasindaki kod, algoritmanin temel bilesenlerini icerir:

- **kapasite**: Kovanin maksimum kapasitesi
- **sizdirmaHizi**: Saniyede islenecek istek sayisi
- **suMiktari**: Kovadaki mevcut istek sayisi
- **sonSizdirmaZamani**: Son sizinti zamani

Algoritmanin iki temel fonksiyonu vardir:

1. **istekKabulEt()**: Yeni bir istegin kabul edilip edilmeyecegini kontrol eder.
2. **sizdir()**: Gecen sureye gore kovadan sizan (islenen) istek miktarini hesaplar.

## Test Senaryolari

`LeakyBucketTest.java` dosyasi, algoritmanin farkli senaryolarda nasil davrandigini test etmek icin kullanilabilir:

1. **Ani Yuk Artisi Testi**: Kisa surede cok sayida istek gonderildiginde algoritmanin davranisini test eder.
2. **Duzenli Trafik Testi**: Duzenli araliklarla gelen isteklerin davranisini test eder.
3. **Yuksek Sizdirma Hizi Testi**: Sizdirma hizi yuksek oldugunda algoritmanin davranisini test eder.

## Nasil Calistirilir?

Projeyi calistirmak icin Java JDK'nin yuklu olmasi gerekmektedir. Asagidaki komutlari kullanarak kodu derleyip calistirabilirsiniz:

```bash
# Kodlari derle
javac LeakyBucket.java LeakyBucketTest.java

# Temel testi calistir
java LeakyBucket

# Detayli test senaryolarini calistir
java LeakyBucketTest
```

## Ornek Cikti

Temel test calistirildiginda, asagidakine benzer bir cikti alinir:

```
LeakyBucket Algoritmasi Test
Kapasite: 10 istek, Sizdirma Hizi: 2 istek/saniye
--------------------------------------------
Istek 1: Kabul edildi ✓ (Kovadaki istek: 1)
Istek 2: Kabul edildi ✓ (Kovadaki istek: 2)
...
Istek 10: Kabul edildi ✓ (Kovadaki istek: 10)
Istek 11: Reddedildi ✗ (Kovadaki istek: 10)
...
```

## Kullanim Alanlari

LeakyBucket algoritmasi asagidaki durumlarda kullanilabilir:

1. **API Rate Limiting**: API'lere gelen istek sayisini sinirlamak icin
2. **DDoS Korumasi**: Sistemleri asiri yuk saldirilarindan korumak icin
3. **Trafik Sekillendirme**: Ag trafigini duzenlemek icin
4. **Kaynak Yonetimi**: Sistem kaynaklarinin adil kullanimini saglamak icin

## Algoritmanin Avantajlari ve Dezavantajlari

### Avantajlar
- Isteklerin sabit bir hizda islenmesini saglar
- Ani yuk artislarina karsi koruma saglar
- Uygulamasi basittir

### Dezavantajlar
- Kisa sureli trafik artislarinda bile istekleri reddedebilir
- Bosta kapasite olsa bile sabit hizda calisir

## Diger Rate Limiting Algoritmalari

LeakyBucket disinda kullanilan diger populer rate limiting algoritmalari:

1. **Token Bucket**: LeakyBucket'a benzer, ancak belirli araliklarla kovaya token eklenir
2. **Fixed Window Counter**: Belirli bir zaman araliginda sabit sayida istek kabul eder
3. **Sliding Window Log**: Isteklerin zaman damgalarini kaydeder ve belirli bir pencere icindeki istek sayisini sinirlar
4. **Sliding Window Counter**: Fixed Window ve Sliding Window Log algoritmalarinin bir karisimidir 