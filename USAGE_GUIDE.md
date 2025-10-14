# On-Demand Services App Template - Usage Guide

## 🎯 Purpose

This template is designed to help developers quickly bootstrap on-demand service applications (rideshare, food delivery, home services, etc.) by providing a clean, modular architecture with pre-built components.

## 🚀 Quick Start

### 1. Using the Template

1. **Clone or Download**: Get the template from the repository
2. **Open in Android Studio**: Open the project in Android Studio Ladybug or later
3. **Sync Project**: Allow Gradle to sync all dependencies
4. **Build & Run**: Compile and run the app to verify everything works

### 2. Customizing for Your Use Case

#### Changing the Package Name
```bash
# Use the provided script to rename the package
./rename_package.sh com.yourcompany.yourservicename
```

#### Modifying the Service Model
Update `/feature/services/src/main/java/com/example/services/Service.kt` to match your service data structure:
```kotlin
data class Service(
    val id: String,
    val name: String,
    val description: String,
    val price: Float,
    val rating: Int,
    val category: String,
    val image: String,
    // Add your custom fields here
)
```

#### Customizing the UI
1. Update theme colors in `/core/ui/src/main/java/com/example/ui/theme/Color.kt`
2. Modify typography in `/core/ui/src/main/java/com/example/ui/theme/Type.kt`
3. Customize components in `/core/ui/src/main/java/com/example/ui/`

#### Integrating Real APIs
1. Update API endpoints in `/core/network/src/main/java/com/example/network/services/api/ServicesService.kt`
2. Modify DTOs in `/core/network/src/main/java/com/example/network/services/model/`
3. Update repository implementations in `/core/network/src/main/java/com/example/network/services/ServicesRepositoryImpl.kt`

## 🏗️ Architecture Overview

### Module Structure

```
app/              # Main application entry point
├── Main Activity and navigation graph

core/             # Shared libraries and utilities
├── common/       # General utilities and extensions
├── data/         # Data layer coordination
├── database/     # Room database implementations
├── datastore/    # Preferences management
├── domain/       # Business logic and use cases
├── model/        # Shared data models
├── network/      # Network layer implementations
└── ui/           # Reusable UI components

feature/          # Feature-specific modules
├── authentication/  # Login/Signup flows
├── main/            # Main dashboard
└── services/        # Service catalog and browsing
```

### Data Flow

```
UI (Compose) → ViewModel → UseCase → Repository → Data Source (API/DB)
     ↑              ↑         ↑          ↑            ↑
   State      Business   Domain    Implementation  Network/Database
              Logic      Models
```

## 🔧 Key Features Implemented

### Authentication System
- Login screen with email/password validation
- Signup screen with form validation
- Token-based session management
- Remember me functionality

### Service Catalog
- Browse services by category
- Search functionality
- Service details display
- Rating and pricing information

### State Management
- Reactive state with Kotlin Flow
- Loading, success, and error states
- Retry mechanisms
- Offline support patterns

### Navigation
- Type-safe navigation with Compose Navigation
- Bottom navigation bar
- Deep linking support

### Dependency Injection
- Hilt for dependency injection
- Module-based organization
- Scoped dependencies

## 🛠️ Adding New Features

### Creating a New Feature Module

1. Create new directory in `/feature/`
2. Add to `settings.gradle.kts`:
   ```kotlin
   include(":feature:yourfeature")
   ```
3. Add dependency in `app/build.gradle.kts`:
   ```kotlin
   implementation(project(":feature:yourfeature"))
   ```
4. Add navigation in `RootNavigationGraph.kt`
5. Create feature structure:
   ```
   feature/yourfeature/
   ├── src/main/java/com/example/yourfeature/
   │   ├── di/           # Feature-specific DI module
   │   ├── navigation/   # Navigation components
   │   ├── presentation/ # Screens and ViewModels
   │   └── usecases/     # Feature-specific use cases
   └── build.gradle.kts
   ```

### Extending the Data Layer

To add new data sources:

1. Create new API service interface in `/core/network/src/main/java/com/example/network/`
2. Add DTO models in `/core/network/src/main/java/com/example/network/model/`
3. Create repository interface in `/core/domain/src/main/java/com/example/domain/`
4. Implement repository in `/core/data/src/main/java/com/example/data/`
5. Add DI module in `/core/data/src/main/java/com/example/data/di/`

## 🔌 API Integration

The template includes a mock API implementation. To connect to real APIs:

### 1. Update API Service Interface
```kotlin
// In /core/network/src/main/java/com/example/network/services/api/ServicesService.kt
@GET("services")
suspend fun getServices(): BaseResponse<List<ServiceDto>>

@POST("services")
suspend fun createService(@Body service: ServiceDto): BaseResponse<ServiceDto>
```

### 2. Update Repository Implementation
```kotlin
// In /core/network/src/main/java/com/example/network/services/ServicesRepositoryImpl.kt
override suspend fun getServices(): Flow<ApiResponse<BaseResponse<List<ServiceDto>>>> {
    return apiCall { 
        servicesService.getServices()
    }
}
```

### 3. Update Base URL
In `/core/network/src/main/java/com/example/network/NetworkConstants.kt`:
```kotlin
const val BASE_URL = "https://your-api-endpoint.com/api/"
```

## 🎨 Customizing the UI

### Theme Customization
1. Colors: `/core/ui/src/main/java/com/example/ui/theme/Color.kt`
2. Typography: `/core/ui/src/main/java/com/example/ui/theme/Type.kt`
3. Shapes: `/core/ui/src/main/java/com/example/ui/theme/Shape.kt`

### Adding New Components
Create reusable components in `/core/ui/src/main/java/com/example/ui/`:
```kotlin
@Composable
fun CustomComponent(
    modifier: Modifier = Modifier,
    // Props
) {
    // Implementation
}
```

## 🧪 Testing Strategy

### Unit Tests
Located in `src/test/` directories of each module:
- ViewModel tests
- Use case tests
- Repository tests
- Utility function tests

### Instrumentation Tests
Located in `src/androidTest/` directories:
- UI component tests
- Screen integration tests
- Navigation tests

### Running Tests
```bash
# Run all unit tests
./gradlew test

# Run instrumentation tests
./gradlew connectedAndroidTest

# Run tests for specific module
./gradlew :feature:authentication:test
```

## 🚀 Deployment

### Building Release Version
```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease

# Bundle for Play Store
./gradlew bundleRelease
```

### Signing Configuration
Update `app/build.gradle.kts` with your signing configuration:
```kotlin
signingConfigs {
    create("release") {
        storeFile = file("path/to/keystore.jks")
        storePassword = "your_store_password"
        keyAlias = "your_key_alias"
        keyPassword = "your_key_password"
    }
}
```

## 📱 Supported Features

### Android Versions
- Minimum SDK: API 24 (Android 7.0)
- Target SDK: API 35 (Android 15)
- Compiled SDK: API 35 (Android 15)

### Device Support
- Phones and tablets
- Portrait and landscape orientations
- Various screen densities

### Accessibility
- TalkBack support
- High contrast mode
- Large text support

## 🔒 Security Considerations

### Data Protection
- Token encryption for sensitive data
- Secure storage for credentials
- Network security configuration

### Best Practices
- HTTPS for all API calls
- Input validation and sanitization
- Secure coding practices

## 🆘 Troubleshooting

### Common Issues

1. **Build Failures**
   - Ensure correct Java version (17 recommended)
   - Clean and rebuild project
   - Invalidate caches in Android Studio

2. **Dependency Issues**
   - Check internet connection
   - Verify repository URLs
   - Update dependency versions

3. **Runtime Crashes**
   - Check Logcat for error messages
   - Verify dependency injection setup
   - Ensure all required permissions are granted

### Getting Help
- Check existing issues in the repository
- Refer to official Android documentation
- Consult Jetpack Compose and Hilt documentation

## 🤝 Community and Support

- Report bugs and issues on GitHub
- Suggest new features and improvements
- Contribute code via pull requests
- Join discussions in the community

## 📄 License Information

This template is provided under the MIT License. See the LICENSE file for details.

You are free to use, modify, and distribute this template for both personal and commercial projects.