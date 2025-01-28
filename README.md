# Not Alma Uygulaması

## Teknik Özellikler

### Mimari ve Yapı
- MVVM (Model-View-ViewModel) mimari deseni
- Repository pattern ile veri yönetimi
- Single Activity yaklaşımı ile iki ekran (Ana Liste ve Not Düzenleme)
- Dependency Injection prensipleri

### Veritabanı
- Room Database implementasyonu
- SQLite tabanlı yerel veri depolama
- Asenkron veritabanı işlemleri
- Entity ve DAO yapıları

### Kullanıcı Arayüzü
- Material Design prensipleri
- RecyclerView ile liste yönetimi
- ViewBinding için XML layout yapıları
- Custom CardView tasarımları
- Swipe-to-delete fonksiyonalitesi

### Veri Yönetimi
- LiveData ile reaktif veri akışı
- Coroutines ile asenkron işlemler
- CRUD (Create, Read, Update, Delete) operasyonları
- State yönetimi

### Yaşam Döngüsü Yönetimi
- Activity ve Fragment yaşam döngüsü
- Configuration changes yönetimi
- Instance state koruması
- ViewModel scope yönetimi

## Teknik Gereksinimler

```gradle
dependencies {
    // Core Android
    implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.4.1'
    
    // UI Components
    implementation 'com.google.android.material:material:1.5.0'
    implementation 'androidx.recyclerview:recyclerview:1.2.1'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.3'
    
    // Architecture Components
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1'
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.4.1'
    implementation 'androidx.room:room-runtime:2.4.2'
    kapt 'androidx.room:room-compiler:2.4.2'
    
    // Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2'
}
```

## Veritabanı Şeması

```kotlin
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val content: String,
    val lastEditTime: String
)
```

## Temel Bileşenler

### ViewModel
- NoteViewModel: Not işlemlerinin yönetimi
- LiveData kullanımı ile UI güncellemeleri
- Coroutine scope yönetimi

### Repository
- NoteRepository: Veri erişim katmanı
- DAO işlemlerinin yönetimi
- Asenkron operasyonların yönetimi

### Adapter
- NotesAdapter: RecyclerView veri yönetimi
- ViewHolder pattern implementasyonu
- Liste işlemlerinin yönetimi

## Performans Optimizasyonları

- ViewHolder pattern ile RecyclerView optimizasyonu
- Lazy loading implementasyonu
- Asenkron veritabanı işlemleri
- Efficient layout hierarchies

## Güvenlik Önlemleri

- SQLite injection koruması (Room ile otomatik)
- Input validation
- Exception handling
- State management

## Test Yapısı

```kotlin
@Test
fun insertNote_shouldSaveToDatabase() {
    // Unit test örneği
}
```

## Derleme ve Çalıştırma

```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease
```

## Minimum Gereksinimler
- Android SDK 21+
- Kotlin 1.5+
- Android Studio Arctic Fox+

## Lisans
Bu proje MIT lisansı altında lisanslanmıştır.
