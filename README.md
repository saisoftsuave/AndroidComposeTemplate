# On-Demand Services App Template

A modern, scalable Android template for building on-demand service applications using Jetpack Compose and clean architecture principles.

## 📱 Overview

This template provides a solid foundation for building on-demand service applications (like Uber, DoorDash, TaskRabbit, etc.) with a modular architecture that promotes code reuse, testability, and maintainability.

## 🏗️ Architecture

The template follows Clean Architecture principles with a modular structure:

```
app/                      # Main application module
├── src/main/
│   ├── java/com/example/ondemand/
│   │   ├── MainActivity.kt
│   │   ├── Application.kt
│   │   └── RootNavigationGraph.kt
│   └── res/
│
core/                     # Shared core modules
├── common/              # Common utilities and extensions
├── data/                # Data layer implementations
├── database/            # Room database setup
├── datastore/           # DataStore for preferences
├── domain/              # Business logic and use cases
├── model/               # Domain models
├── network/             # Network layer and API clients
├── ui/                  # Reusable UI components
│
feature/                 # Feature modules
├── authentication/      # Authentication flows (Login/Signup)
├── main/                # Main dashboard and navigation
├── services/            # Services catalog and management
```

## 🔧 Key Technologies

- **Jetpack Compose**: Modern UI toolkit
- **Hilt**: Dependency injection
- **Navigation**: Type-safe navigation with Compose Navigation
- **Coroutines & Flow**: Asynchronous programming
- **Room**: Local database
- **DataStore**: Preferences storage
- **Retrofit**: Network client
- **Kotlin Serialization**: JSON serialization
- **Material 3**: Modern design system

## 🚀 Getting Started

### Prerequisites

- Android Studio Ladybug (2024.2.1) or later
- JDK 17
- Kotlin 2.0+
- Gradle 8.9+

### Setup

1. Clone or download this template
2. Open the project in Android Studio
3. Sync the project with Gradle files
4. Build and run the application

### Customization Guide

#### 1. Rename Package (Optional)
If you want to change the package name from `com.example.ondemand`:
1. Update `namespace` in `app/build.gradle.kts`
2. Update package declarations in all Kotlin files
3. Update `applicationId` in `app/build.gradle.kts`

#### 2. Customize Authentication
The authentication module (`feature/authentication`) provides:
- Login screen
- Signup screen
- Token-based session management

To customize:
1. Update validation rules in `ValidateEmail` and `ValidatePassword`
2. Modify API endpoints in `AuthRepositoryImpl`
3. Update UI components as needed

#### 3. Customize Services
The services module (`feature/services`) provides:
- Service catalog browsing
- Service details
- Search functionality

To customize for your specific on-demand service:
1. Update `Service` data model in `Service.kt`
2. Modify API endpoints in `ServicesRepositoryImpl`
3. Customize UI components in `ServiceCatalogScreen.kt` and related files
4. Update categories and service listings

#### 4. Add New Features
To add new features:
1. Create a new module under `feature/`
2. Follow the same structure as existing feature modules
3. Add the module to `settings.gradle.kts`
4. Include the module dependency in `app/build.gradle.kts`

#### 5. API Integration
The template uses a mock API approach by default. To integrate with real APIs:
1. Update endpoints in `ServicesService` and `AuthService`
2. Modify data models in respective DTO files
3. Update repository implementations to call actual APIs
4. Configure Retrofit client with your API base URL

#### 6. Theming
Customize the app's appearance:
1. Update colors in `core/ui/src/main/java/com/example/ui/theme/Color.kt`
2. Modify typography in `core/ui/src/main/java/com/example/ui/theme/Type.kt`
3. Adjust shapes in `core/ui/src/main/java/com/example/ui/theme/Shape.kt`

## 📁 Module Structure

### App Module
- Entry point of the application
- Main navigation graph
- Dependency injection setup

### Core Modules
- **common**: Utilities, extensions, and shared code
- **data**: Data layer implementations connecting repositories to data sources
- **database**: Room database setup and DAOs
- **datastore**: Preferences management using DataStore
- **domain**: Business logic, use cases, and domain models
- **model**: Shared data models
- **network**: Retrofit setup, interceptors, and API clients
- **ui**: Reusable UI components and theming

### Feature Modules
- **authentication**: Login and signup flows
- **main**: Main dashboard and bottom navigation
- **services**: Service catalog and browsing

## 🎨 UI Components

The template includes reusable UI components:
- Input fields with validation
- Loading indicators
- Error handling components
- Custom buttons
- Search bars
- Cards and lists
- Bottom navigation

## 🔐 Security

- Token-based authentication
- Secure storage for sensitive data
- Network security configuration

## 🧪 Testing

The template includes basic test structure:
- Unit tests for view models
- Instrumentation tests for UI components
- Repository tests for data layer

To run tests:
```bash
# Run all unit tests
./gradlew test

# Run instrumentation tests
./gradlew connectedAndroidTest
```

## 📦 Build Variants

- **Debug**: Development build with logging
- **Release**: Production build with optimizations

## 🚨 Known Issues & Limitations

1. Currently uses mock data instead of real API integration
2. Basic authentication flow - may need enhancement for production use
3. Limited error handling scenarios

## ✅ Best Practices Included

- Modular architecture for scalability
- Clean separation of concerns
- Dependency injection for testability
- Reactive programming with Coroutines and Flow
- Type-safe navigation
- Proper state management
- Resource management and cleanup
- Consistent code style and naming conventions

## 🔄 Migration Guide

To migrate from a traditional View-based architecture:
1. Replace Activities/Fragments with Compose screens
2. Convert XML layouts to Composable functions
3. Migrate state management to ViewModel + StateFlow
4. Update navigation to use Compose Navigation
5. Replace findViewById with Compose state

## 📚 Documentation References

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Hilt Dependency Injection](https://developer.android.com/training/dependency-injection/hilt-android)
- [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Android Architecture Components](https://developer.android.com/topic/architecture)

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Inspired by modern Android development practices
- Built with official Android Jetpack libraries
- Follows Google's recommended architecture guidelines

---

## 🚀 Quick Start Checklist

- [ ] Update package name if needed
- [ ] Configure API endpoints
- [ ] Customize authentication flow
- [ ] Modify service models for your use case
- [ ] Update theme colors and typography
- [ ] Add your own features and modules
- [ ] Implement real API integration
- [ ] Add proper error handling
- [ ] Write unit and instrumented tests
- [ ] Configure signing for release builds