# Online Food Store - Console Application

## Açıklama
Bu konsol uygulaması, online yemek mağazası projesindeki mevcut DAO ve model sınıflarını kullanarak kullanıcıların:
- Sisteme login olmasını
- Yemekleri listelemesini
- Sepete yemek eklemesini
- Ödeme yapmasını (Kredi kartı veya Kapıda ödeme)

sağlar.

## Özellikler

### 1. **Kullanıcı Girişi**
- Kullanıcı adı ve şifre ile giriş
- 3 deneme hakkı
- Veritabanındaki kullanıcılar ile doğrulama

### 2. **Yemek Listeleme**
- Veritabanındaki tüm kitapları görüntüleme
- Yemek detayları: ID, Adı, Restoran, Kategori, Fiyat, Stok

### 3. **Sepet Yönetimi**
- Yemekleri sepete ekleme
- Sepetteki yemekleri görüntüleme
- Toplam tutarı hesaplama

### 4. **Ödeme İşlemi**
- **Kredi Kartı ile Ödeme**: Kredi kartı bilgileri ile ödeme
- **Kapıda Ödeme**: Sipariş kapıda ödenecek

## Kullanılan Teknolojiler

- Java
- JDBC (MySQL bağlantısı)
- Mevcut DAO pattern implementation
- Strategy Pattern (Ödeme yöntemleri için)

## Kullanılan Sınıflar

### Model Sınıfları
- `User` - Kullanıcı bilgileri
- `Food` - Yemek bilgileri

### DAO Sınıfları
- `IUserDAO` / `UserDAOImpl` - Kullanıcı veritabanı işlemleri
- `IFoodDAO` / `FoodDAOImpl` - Yemek veritabanı işlemleri
- `DBConnection` - Veritabanı bağlantısı

### Ödeme Sınıfları
- `PaymentMethod` - Ödeme interface
- `CreditCardPayment` - Kredi kartı ödemesi
- `CashOnDeliveryPayment` - Kapıda ödeme

## Çalıştırma

### 1. Veritabanı Hazırlığı
Öncelikle MySQL veritabanınızda `onlinefoodstore` veritabanını ve gerekli tabloları oluşturmuş olmalısınız:

## Veritabanı Yapısı

```sql
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    username VARCHAR(50),
    password INT,
    role VARCHAR(20)
);

CREATE TABLE foods (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    restaurant VARCHAR(100),
    category VARCHAR(50),
    price DOUBLE,
    stock INT
);

CREATE TABLE coupons (
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(50),
    discount_amount DOUBLE
);
```

### 2. Uygulamayı Çalıştırma

#### IDE'den Çalıştırma (IntelliJ IDEA, Eclipse vb.)
1. Projeyi IDE'nizde açın
2. `FoodStoreConsoleApp.java` dosyasını bulun
3. `main` metoduna sağ tıklayıp "Run" seçeneğini seçin

#### Komut Satırından Çalıştırma
```bash
# Projeyi derleyin
mvn clean compile

# Uygulamayı çalıştırın
mvn exec:java -Dexec.mainClass="com.onlinefoodstore.console.FoodStoreConsoleApp"
```

## Kullanım Akışı

### 1. Giriş Yapma
```
═══════════════════════════════════════
         KULLANICI GİRİŞİ
═══════════════════════════════════════
Kullanıcı Adı: testuser
Şifre: 1234

✅ Giriş başarılı! Hoş geldiniz, Test User
```

### 2. Ana Menü
```
╔════════════════════════════════════════╗
║           ANA MENÜ                     ║
╠════════════════════════════════════════╣
║ 1. Yemekleri Listele                   ║
║ 2. Sepeti Görüntüle                    ║
║ 3. Ödeme Yap                           ║
║ 4. Çıkış                               ║
╚════════════════════════════════════════╝
Seçiminiz: 
```

### 3. Yemek Seçme
```
ID   Yemek Adı     Restoran     Kategori     Fiyat     Stok
-----------------------------------------------------------
1    Lahmacun      Öz Urfa      Ana Yemek    45.00     10
2    Döner         Dönerci Ali  Fast Food    360.00     15  

Sepete eklemek için yemek ID'si girin (0 = Ana menüye dön): 1

```

### 4. Sepeti Görüntüleme
```
╔════════════════════════════════════════════╗
║                 SEPETİNİZ                  ║
╚════════════════════════════════════════════╝
Yemek Adı     Restoran     Fiyat
---------------------------------
Lahmacun      Öz Urfa      45.00 TL
---------------------------------
TOPLAM: 45.00 TL
```

### 5. Ödeme Yapma
```
╔════════════════════════════════════════╗
║         ÖDEME İŞLEMİ                   ║
╚════════════════════════════════════════╝
Toplam Tutar: 45.00 TL

───────────────────────────────────────
Ödeme Yöntemi Seçin:
1. Kredi Kartı
2. Kapıda Ödeme
───────────────────────────────────────
Seçiminiz: 1

💳 Ödeme işleniyor...
Kredi kartı ile ödeme seçildi. Tutar: 45.00 TL
✅ Sipariş başarıyla tamamlandı!
📦 Kitaplarınız en kısa sürede kargoya verilecektir.
```