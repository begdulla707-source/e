# Momentlar

Android uchun mahalliy story maketi. Profil nomi `janzakov.b`. Rasmni tanlash,
ko‘rsatiladigan sana va vaqtni kiritish, ko‘ruvchilar nomlarini qo‘shish,
storyni tahrirlash va o‘chirish mumkin. Rasm canvas orqali JPEGga aylantiriladi:
JPEG EXIF metama'lumotlari ilova ichida saqlanmaydi. Sana va ko‘ruvchilar
mahalliy maket ma'lumotidir; Instagram hisobiga ulanmaydi.

Ma'lumotlar telefonning ushbu ilovaga tegishli WebView xotirasida qoladi.
Ilovani o‘chirish ma'lumotlarni ham o‘chiradi. Bir nechta katta rasm joy
chegarasiga yetishi mumkin; shunda eski storylarni o‘chiring.

Android Studio orqali `moment-studio` loyihasini oching yoki Gradle 8.9 va
Android SDK 35 bilan `gradle :app:assembleDebug` bajaring. APK:
`app/build/outputs/apk/debug/app-debug.apk`.
