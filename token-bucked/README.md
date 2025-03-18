# TokenBucket (Jeton Kovası) Algoritması

Bu proje, rate limiting (hız sınırlama) için kullanılan TokenBucket algoritmasının Java ile uygulanmış bir örneğini içerir. Algoritma, açıklamalarla detaylı bir şekilde anlatılmıştır.

## Algoritma Nedir?

TokenBucket (Jeton Kovası) algoritması, bir sisteme gelen isteklerin hızını sınırlamak için kullanılan popüler bir rate limiting yöntemidir. Algoritma, adından da anlaşılacağı gibi, bir kova ve jeton benzetmesi üzerine kurulmuştur:

1. Sistem, sabit kapasiteli bir kova olarak düşünülür.
2. Kovaya belirli aralıklarla jeton eklenir (sabit bir hızda).
3. Her gelen istek, kovadan bir jeton harcar.
4. Eğer kovada yeterli jeton yoksa, yeni gelen istekler reddedilir.

Bu benzetme, sistemin ani yük artışlarına karşı daha esnek bir şekilde yanıt vermesini sağlar ve isteklerin belirli bir ortalama hızda işlenmesini garanti eder.

## Algoritmanın Çalışma Prensibi

TokenBucket algoritması şu şekilde çalışır:

1. **Kapasite**: Kovanın maksimum kapasitesi, sistemin aynı anda işleyebileceği maksimum istek sayısını belirler.
2. **Jeton Ekleme Hızı**: Kovaya birim zamanda eklenen jeton sayısı, sistemin ortalama işleme hızını belirler.
3. **İstek Kabulü**: Yeni bir istek geldiğinde, önce geçen süreye göre kovaya ne kadar jeton eklendiği hesaplanır. Eğer kovada yeterli jeton varsa istek kabul edilir ve bir jeton harcanır, yoksa reddedilir.

## Leaky Bucket ile Farkları

TokenBucket algoritması, Leaky Bucket (Sızdıran Kova) algoritmasına benzer, ancak önemli farklılıkları vardır:

1. **Esneklik**: TokenBucket, ani trafik artışlarına daha esnek yanıt verir. Kovada yeterli jeton varsa, istekler hemen işlenebilir.
2. **Ortalama Hız**: Leaky Bucket sabit bir hızda çalışırken, TokenBucket ortalama bir hızda çalışır.
3. **Jeton Biriktirme**: TokenBucket, düşük trafik dönemlerinde jeton biriktirebilir ve bu jetonları yoğun trafik dönemlerinde kullanabilir.

## Kodun Açıklaması

`TokenBucket.java` dosyasındaki kod, algoritmanın temel bileşenlerini içerir:

- **kapasite**: Kovanın maksimum jeton kapasitesi
- **jetonEklemeHizi**: Saniyede eklenen jeton sayısı
- **mevcutJetonSayisi**: Kovadaki mevcut jeton sayısı
- **sonJetonEklemeZamani**: Son jeton ekleme zamanı

Algoritmanın iki temel fonksiyonu vardır:

1. **istekKabulEt()**: Yeni bir isteğin kabul edilip edilmeyeceğini kontrol eder.
2. **jetonEkle()**: Geçen süreye göre kovaya eklenmesi gereken jeton miktarını hesaplar.

## Test Senaryoları

`TokenBucketTest.java` dosyası, algoritmanın farklı senaryolarda nasıl davrandığını test etmek için kullanılabilir:

1. **Ani Yük Artışı Testi**: Kısa sürede çok sayıda istek gönderildiğinde algoritmanın davranışını test eder.
2. **Düzenli Trafik Testi**: Düzenli aralıklarla gelen isteklerin davranışını test eder.
3. **Yüksek Jeton Ekleme Hızı Testi**: Jeton ekleme hızı yüksek olduğunda algoritmanın davranışını test eder.
4. **Jeton Yenileme Testi**: Jetonların zamanla nasıl yenilendiğini gösterir.

## Nasıl Çalıştırılır?

Projeyi çalıştırmak için Java JDK'nın yüklü olması gerekmektedir. Aşağıdaki komutları kullanarak kodu derleyip çalıştırabilirsiniz:

```bash
# Kodları derle
javac TokenBucket.java TokenBucketTest.java

# Temel testi çalıştır
java TokenBucket

# Detaylı test senaryolarını çalıştır
java TokenBucketTest
```

## Örnek Çıktı

Temel test çalıştırıldığında, aşağıdakine benzer bir çıktı alınır:

```
TokenBucket Algoritması Test
Kapasite: 10 jeton, Jeton Ekleme Hızı: 2 jeton/saniye
--------------------------------------------
İstek 1: Kabul edildi ✓ (Kovadaki jeton: 9.00)
İstek 2: Kabul edildi ✓ (Kovadaki jeton: 8.00)
...
```

## Kullanım Alanları

TokenBucket algoritması aşağıdaki durumlarda kullanılabilir:

1. **API Rate Limiting**: API'lere gelen istek sayısını sınırlamak için
2. **DDoS Koruması**: Sistemleri aşırı yük saldırılarından korumak için
3. **Trafik Şekillendirme**: Ağ trafiğini düzenlemek için
4. **Kaynak Yönetimi**: Sistem kaynaklarının adil kullanımını sağlamak için

## Algoritmanın Avantajları ve Dezavantajları

### Avantajlar
- Ani trafik artışlarına daha esnek yanıt verir
- Düşük trafik dönemlerinde jeton biriktirebilir
- Ortalama bir işleme hızı sağlar

### Dezavantajlar
- Leaky Bucket'a göre daha karmaşık bir yapıya sahiptir
- Jeton sayısının sürekli güncellenmesi gerekir

## Diğer Rate Limiting Algoritmaları

TokenBucket dışında kullanılan diğer popüler rate limiting algoritmaları:

1. **Leaky Bucket**: Sabit bir hızda çalışır, ani trafik artışlarına karşı daha katıdır
2. **Fixed Window Counter**: Belirli bir zaman aralığında sabit sayıda istek kabul eder
3. **Sliding Window Log**: İsteklerin zaman damgalarını kaydeder ve belirli bir pencere içindeki istek sayısını sınırlar
4. **Sliding Window Counter**: Fixed Window ve Sliding Window Log algoritmalarının bir karışımıdır 

## Token Bucket Algoritması Özeti
Token Bucket (Jeton Kovası) algoritması, rate limiting (hız sınırlama) için kullanılan popüler bir yöntemdir. Bu algoritma, Leaky Bucket'tan farklı olarak daha esnek bir yapıya sahiptir ve ani trafik artışlarına daha iyi yanıt verebilir.
## Temel Çalışma Prensibi:
Sistem, sabit kapasiteli bir kova olarak düşünülür.
Kovaya belirli aralıklarla jeton eklenir (sabit bir hızda).
Her gelen istek, kovadan bir jeton harcar.
Eğer kovada yeterli jeton yoksa, yeni gelen istekler reddedilir.
## Leaky Bucket ile Farkları:
Esneklik: Token Bucket, ani trafik artışlarına daha esnek yanıt verir. Kovada yeterli jeton varsa, istekler hemen işlenebilir.
Ortalama Hız: Leaky Bucket sabit bir hızda çalışırken, Token Bucket ortalama bir hızda çalışır.
Jeton Biriktirme: Token Bucket, düşük trafik dönemlerinde jeton biriktirebilir ve bu jetonları yoğun trafik dönemlerinde kullanabilir.
## Uygulamada Yapılan Değişiklikler:
Leaky Bucket'ta "sızdırma hızı" kavramı yerine "jeton ekleme hızı" kavramı kullanıldı.
Leaky Bucket'ta kova dolduğunda istekler reddedilirken, Token Bucket'ta jeton olmadığında istekler reddedilir.
Token Bucket'ta başlangıçta kova dolu olarak başlar, bu da ani trafik artışlarına daha iyi yanıt verebilmesini sağlar.
Jeton sayısı ondalıklı olarak tutularak daha hassas bir ölçüm sağlanır.
