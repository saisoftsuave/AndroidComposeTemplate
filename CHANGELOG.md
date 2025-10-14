# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2025-10-14

### Added
- Initial release of On-Demand Services App Template
- Modular architecture with clean separation of concerns
- Jetpack Compose UI implementation
- Hilt dependency injection setup
- Navigation component with type-safe routes
- Authentication feature module (login/signup)
- Services feature module (catalog browsing)
- Core modules for common functionality:
  - Common utilities and extensions
  - Data layer implementations
  - Database setup with Room
  - DataStore for preferences
  - Domain models and use cases
  - Network layer with Retrofit
  - Reusable UI components
- Material 3 design system implementation
- Basic theme customization capabilities
- Sample data and mock implementations
- Comprehensive README documentation
- MIT License file

### Changed
- Transformed existing Android Compose Template into a generic on-demand services template
- Renamed packages and modules to reflect on-demand services terminology
- Updated UI components to be more generic and reusable
- Simplified navigation structure for on-demand service flows
- Made data models generic for various service types
- Removed product-specific business logic
- Updated imports and references throughout the codebase
- Fixed build issues related to Java version compatibility

### Fixed
- Java version compatibility issues (configured to use Java 17)
- Package naming inconsistencies
- Import resolution issues
- Compilation errors in feature modules
- KSP (Kotlin Symbol Processing) configuration issues

### Removed
- Music-specific features and UI components
- Product-specific data models and business logic
- Unused dependencies and modules
- Hardcoded business logic tied to specific domains