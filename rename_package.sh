#!/bin/bash

# Script to rename the template package name
# Usage: ./rename_package.sh com.yourcompany.yourapp

NEW_PACKAGE=$1

if [ -z "$NEW_PACKAGE" ]; then
    echo "Usage: $0 <new_package_name>"
    echo "Example: $0 com.mycompany.myondemandservice"
    exit 1
fi

echo "Renaming package from com.example.ondemand to $NEW_PACKAGE"

# Replace in build.gradle.kts files
find . -name "build.gradle.kts" -exec sed -i '' "s/com\.example\.ondemand/$NEW_PACKAGE/g" {} \;

# Replace in AndroidManifest.xml files
find . -name "AndroidManifest.xml" -exec sed -i '' "s/com\.example\.ondemand/$NEW_PACKAGE/g" {} \;

# Replace package declarations in Kotlin files
find . -name "*.kt" -exec sed -i '' "s/package com\.example\.ondemand/package $NEW_PACKAGE/g" {} \;
find . -name "*.kt" -exec sed -i '' "s/import com\.example\.ondemand/import $NEW_PACKAGE/g" {} \;

# Rename directory structure
OLD_DIR="com/example/ondemand"
NEW_DIR_PATH=$(echo "$NEW_PACKAGE" | tr '.' '/')

echo "Creating new package directory structure: $NEW_DIR_PATH"
mkdir -p "$(dirname "$NEW_DIR_PATH")"

# Move the existing package structure to the new one
# This would need to be done for each module's src directory
echo "Package renaming completed!"
echo "Please manually update the directory structure in each module's src/main/java/ folder"
echo "From: src/main/java/com/example/ondemand/"
echo "To: src/main/java/$NEW_DIR_PATH/"

echo ""
echo "Next steps:"
echo "1. Manually rename directories in each module"
echo "2. Update any hardcoded references to com.example.ondemand"
echo "3. Sync project with Gradle files"
echo "4. Build and test the application"