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

## 🏗️ 技術スタック

### **フロントエンド**
- **Jetpack Compose**: モダンな宣言的UI
- **Material Design 3**: 最新のデザインシステム
- **Kotlin 2.0.21**: 最新の安定版Kotlin

### **アーキテクチャ**
- **MVVM パターン**: 保守性の高いアーキテクチャ
- **Repository パターン**: データアクセスの抽象化
- **Flow + StateFlow**: リアクティブなデータストリーム

### **データベース**
- **Room 2.6.1**: 型安全なSQLiteラッパー
- **Flow**: リアルタイムデータ更新

### **ビルドシステム**
- **Version Catalog**: 依存関係の一元管理
- **Kotlin DSL**: 型安全なビルドスクリプト

## 📋 システム要件

- **Android 7.0** (API level 24) 以上
- **Target SDK**: 36 (Android 16 対応)
- **Kotlin**: 2.0.21

## 🚀 セットアップ

### 1. リポジトリをクローン
```bash
git clone https://github.com/winschneid/RecordKeeper.git
cd RecordKeeper
```

### 2. Android Studio で開く
- Android Studio Ladybug | 2024.2.1 以降を推奨
- プロジェクトを開いて自動的に依存関係をダウンロード

### 3. ビルドと実行
```bash
./gradlew assembleDebug
```

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

## 🎨 スクリーンショット

### メイン画面
3つのタブでカテゴリ別に記録を表示

### 記録追加
直感的なフォームで簡単に記録を追加

### 評価機能
タップで簡単に5段階評価

## 🔧 開発について

### 使用した最新技術
- **Kotlin 2.0.21**: 最新の安定版言語機能
- **Compose 2024.12.01**: 最新のUI toolkit
- **Room 2.6.1**: 最新のデータベースライブラリ
- **Navigation 2.8.4**: 最新のナビゲーションライブラリ

### アーキテクチャの特徴
- **Single Activity**: Jetpack Composeによる単一アクティビティ構成
- **Reactive Programming**: Flow/StateFlowによるリアクティブプログラミング
- **Type Safety**: Kotlin DSLとVersion Catalogによる型安全性

## 🤝 コントリビューション

プルリクエストやイシューの作成を歓迎しています。

## 📄 ライセンス

このプロジェクトは MIT ライセンスの下で公開されています。

## 🙏 謝辞

このアプリは Claude Code によって作成されました。

---

**📱 あなたの記録を、もっと簡単に、もっと楽しく。**