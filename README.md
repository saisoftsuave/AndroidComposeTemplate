# On-Demand Services App Template

A modern, scalable Android template for building on-demand service applications using Jetpack Compose and clean architecture principles.

## 📱 Overview

This template provides a solid foundation for building on-demand service applications with a modular architecture that promotes code reuse, testability, and maintainability.

## ✅ Features

- **Ready-to-use authentication flows** (Login/Signup)
- **Service catalog browsing** with search functionality
- **Clean Architecture** with modular design
- **Jetpack Compose** for modern UI
- **Hilt Dependency Injection** for clean code
- **Navigation Component** with type-safe routing
- **Repository Pattern** for data management
- **Responsive UI components** with Material 3 design
- **State management** with ViewModel and StateFlow
- **Error handling** patterns
- **Sample data and mock implementations**
- **Well-documented codebase**

## 🏗️ Architecture

The template follows Clean Architecture principles with a modular structure:

```
app/                      # Main application module
├── Main application entry point

core/                     # Shared core modules
├── common/              # Utilities and extensions
├── data/                # Data layer implementations
├── database/            # Room database setup
├── datastore/           # Preferences management
├── domain/              # Business logic and use cases
├── model/               # Shared data models
├── network/             # Network layer and API clients
└── ui/                  # Reusable UI components

feature/                 # Feature modules
├── authentication/      # Authentication flows
├── main/               # Main dashboard
└── services/           # Service catalog
```

## 🚀 Getting Started

1. Clone or download this template
2. Open in Android Studio (Ladybug or later recommended)
3. Sync the project with Gradle files
4. Build and run the application
5. Customize for your specific on-demand service

## 🛠️ Customization Points

- Update package name using `rename_package.sh`
- Modify data models in `feature/services/src/main/java/com/example/services/`
- Connect to your real APIs in `core/network/`
- Customize UI theme in `core/ui/src/main/java/com/example/ui/theme/`
- Add new features by creating new modules in `feature/`

## 🔧 Technology Stack

- **Kotlin**: 2.0+
- **Jetpack Compose**: 1.7+
- **Android SDK**: API 35 (Android 15)
- **Dependency Injection**: Hilt 2.56+
- **Networking**: Retrofit 2.9+
- **Database**: Room 2.7+
- **Preferences**: DataStore 1.1+
- **Navigation**: Compose Navigation 2.8+

## 📄 Documentation

- [README.md](README.md) - Main project documentation
- [USAGE_GUIDE.md](USAGE_GUIDE.md) - Detailed usage instructions
- [PROJECT_STRUCTURE.md](PROJECT_STRUCTURE.md) - Technical architecture documentation
- [SUMMARY.md](SUMMARY.md) - Quick overview
- [CHANGELOG.md](CHANGELOG.md) - Version history

## 📦 Build Status

✅ **Build Successful** - Ready for development

## 🤝 Contributing

Feel free to fork this template and adapt it for your projects. Contributions are welcome!

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.