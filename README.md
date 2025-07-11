# 📱 RecordKeeper

**思い出を記録する、あなたの記録管理アプリ**

RecordKeeperは、ライブ・映画・ラーメンの記録を簡単に管理できる Android アプリです。行った場所、観た作品、食べた一杯を記録して、あなたの思い出を整理しましょう。

## 🎯 主な機能

### 📝 記録管理
- **ライブ記録**: アーティスト、会場、日付、評価を記録
- **映画記録**: 作品名、監督、映画館、感想を記録  
- **ラーメン記録**: 店名、メニュー、場所、評価を記録

### ⭐ 評価システム
- 5段階の星評価で満足度を記録
- 直感的なタップ操作で簡単評価

### 📝 メモ機能
- 各記録に自由なメモを追加
- 思い出やコメントを詳しく記録

### 🔍 記録表示
- カテゴリ別のタブで整理された表示
- 日付順での自動ソート
- 削除機能付きの記録管理

## 📁 プロジェクト構成

```
app/
├── src/main/java/com/example/recordkeeper/
│   ├── MainActivity.kt                 # メインアクティビティ
│   ├── data/                          # データレイヤー
│   │   ├── entity/                    # エンティティクラス
│   │   │   ├── LiveRecord.kt
│   │   │   ├── MovieRecord.kt
│   │   │   └── RamenRecord.kt
│   │   ├── dao/                       # データアクセスオブジェクト
│   │   │   ├── LiveRecordDao.kt
│   │   │   ├── MovieRecordDao.kt
│   │   │   └── RamenRecordDao.kt
│   │   └── RecordDatabase.kt          # Room データベース
│   ├── repository/                    # リポジトリ
│   │   └── RecordRepository.kt
│   ├── viewmodel/                     # ViewModel
│   │   └── RecordViewModel.kt
│   └── ui/                           # UI関連
│       ├── compose/                   # Composeコンポーネント
│       │   ├── RecordKeeperApp.kt
│       │   ├── AddRecordDialog.kt
│       │   ├── LiveRecordsScreen.kt
│       │   ├── MovieRecordsScreen.kt
│       │   ├── RamenRecordsScreen.kt
│       │   ├── RatingStars.kt
│       │   └── InteractiveRatingBar.kt
│       └── theme/                     # テーマ設定
│           ├── Theme.kt
│           ├── Color.kt
│           └── Type.kt
├── gradle/
│   └── libs.versions.toml            # Version Catalog
└── build.gradle.kts                  # ビルド設定
```
