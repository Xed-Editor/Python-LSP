# Python LSP Extension for Xed-Editor

An extension for [Xed-Editor](https://github.com/Xed-Editor/Xed-Editor) that provides full Python language support using the Language Server Protocol (LSP). It is powered by **ty**, Astral's extremely fast, Rust-based Python type checker and language server.

---

## Features

- **Blazing Fast Performance**: Powered by the Rust-written type checker (`ty`) ensuring instant feedback and low latency on Android.
- **Diagnostics**: Real-time syntax and type checking diagnostics.
- **Code Completion**: Context-aware autocompletes as you type.
- **Navigation**:
  - Go to Definition
  - Go to Declaration
  - Go to Type Definition
  - Find References
- **Hover Info & Inlay Hints**: Rich tooltips showing variable types, function signatures, and documentation.
- **Pre-compiled Binaries**: Includes architecture-specific binaries for Android (`arm64-v8a`, `armeabi-v7a`, `x86_64`), removing the need for manual configuration or terminal setup.

---

## Compatibility & Requirements

> [!IMPORTANT]
> - **Xed-Editor Version**: Supported on Xed-Editor versions between code `87` and `95`.
> - **Supported Architectures**: Android devices with `arm64-v8a`, `armeabi-v7a`, or `x86_64` CPU architectures.
> - **Android Version**: Requires Android 8.0 (API 26) or higher.

---

## How to Build the Extension

To build the extension package from source:

### 1. Prerequisites
- [JDK 21](https://adoptium.net/) or newer.
- Android SDK configured (via environment variable `ANDROID_HOME` or Android Studio).

### 2. Build Commands

To build the extension in **debug mode** (recommended for testing):
```bash
./compileDebug
```

To build in **release mode** (for optimization and packaging):
```bash
./compileRelease
```

### 3. Retrieve the Extension Package
Once the build script finishes successfully, the final extension archive will be output to:
```
output/Python LSP.zip
```

---

## Installation

1. Copy the generated `Python LSP.zip` file to your Android device.
2. Open **Xed-Editor** (Karbon).
3. Navigate to **Settings** > **Extensions**.
4. Import/install the `Python LSP.zip` file.
5. Restart the editor if prompted. Open any `.py` file to start writing with autocompletion, type diagnostics, and hover information!

---

## Project Structure

- `app/` - The main Kotlin application wrapper for Android.
  - `src/main/java/com/rk/xededitor/python/` - Contains the extension entrypoint ([Main.kt](file:///home/rohit/Projects/Python-LSP/app/src/main/java/com/rk/xededitor/python/Main.kt)) and server adapter ([PythonServer.kt](file:///home/rohit/Projects/Python-LSP/app/src/main/java/com/rk/xededitor/python/PythonServer.kt)).
- `bin/` - Pre-compiled architecture-specific binaries of the `ty` LSP server (`arm64-v8a`, `armeabi-v7a`, `x86_64`).
- `schema/` - JSON schema used for validating extension metadata ([schema.json](file:///home/rohit/Projects/Python-LSP/schema/schema.json)).
- `manifest.json` - Metadata config file containing versioning, ID, author, and description ([manifest.json](file:///home/rohit/Projects/Python-LSP/manifest.json)).

---

## License & Repository

- **Repository**: [github.com/Xed-Editor/Python-LSP](https://github.com/Xed-Editor/Python-LSP)
- **Author**: [Xed-Editor](https://github.com/Xed-Editor)
