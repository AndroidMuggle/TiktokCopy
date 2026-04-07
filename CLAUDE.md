# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is an Android TikTok clone app built with:
- **Jetpack Compose** for UI
- **Hilt** for dependency injection
- **Ktor** for networking (with Gson serialization)
- **ExoPlayer** for video playback
- **Coil** for image loading
- **Navigation Compose** for navigation

The app currently implements a home screen with tabbed video feed (similar to TikTok's main interface) and placeholder screens for other bottom navigation destinations (Friends, Create Video, Messages, Profile).

## Development Environment

**SDK Versions**: compileSdk 35, minSdk 24, targetSdk 35  
**Java Version**: 21  
**Kotlin Version**: 2.2.0  
**Gradle Version Catalog**: Dependencies are managed via `gradle/libs.versions.toml`. Add new dependencies there and reference them in `app/build.gradle.kts` using `libs` alias.

## Architecture

The codebase follows an **MVI-like pattern** with the following components:

- **Intents/Actions**: Sealed interfaces/classes in `business/*/intent/` define user actions (e.g., `VideoPlayAct`, `ScrollTabClickAct`).
- **State**: Data classes in `business/*/state/` hold UI state (e.g., `HomeScreenState`, `SingleTabState`).
- **ViewModels**: In `business/*/vm/` handle intents and update state via `MutableStateFlow`. ViewModels are annotated with `@HiltViewModel` and inject repositories.
- **Repositories**: In `business/*/repo/` (currently mostly empty stubs) would contain business logic and data sourcing.
- **UI Composables**: In `ui/screen/` and `ui/component/` observe state via `collectAsStateWithLifecycle` and send intents to ViewModels.

### Key Directories

- `app/src/main/java/com/muggle/tiktokcopy/`
  - `business/` – MVI architecture components grouped by feature (home, login, etc.)
    - `home/` – Home screen feature
      - `intent/` – User actions (sealed interfaces)
      - `state/` – UI state data classes
      - `vm/` – ViewModels
      - `repo/` – Repositories (currently stubs)
      - `bean/` – Click type enums and constants
  - `ui/` – Compose UI layer
    - `screen/` – Top-level screens (`HomeScreen`, `FriendScreen`, etc.)
    - `component/` – Reusable Composable components (`nav/`, `video/`)
    - `theme/` – Material3 theme definition
  - `http/` – Networking layer (`HttpClientProvider`, `CommonHttpInterface`, `CommonResponse`)
  - `utils/` – Extension functions and utilities (e.g., `cdp` density‑independent pixel converter)

## Navigation

Navigation uses **Navigation Compose** with type‑safe destinations defined as serializable objects in `ui/screen/AppScreenType.kt` (annotated with `@Serializable` from `kotlinx.serialization`):
- `HomePage`, `FriendPage`, `CreateVideoPage`, `MessagePage`, `MinePage`

The main navigation graph is set up in `AppScreen.kt`, which includes a bottom navigation bar (`BottomNavigator`). Each destination maps to a Composable screen.

## Networking

- **HTTP Client**: Singleton `HttpClientProvider.client` configured with CIO engine, Gson serialization, and JSON content type.
- **Request Wrapper**: `CommonHttpInterface.startHttpRequest` provides a uniform suspend function for making Ktor requests, handling success/error callbacks, and wrapping responses in `CommonResponse<T>` (standard `{code, msg, data}` format).

## Dependency Injection

**Hilt** is used for DI. The application class `TiktokApplication` is annotated with `@HiltAndroidApp`. ViewModels are injected via `@HiltViewModel` and repositories via `@Inject` constructor. No custom Hilt modules are currently defined.

## Development Commands

Use the Gradle wrapper (`gradlew` on Unix/macOS, `gradlew.bat` on Windows):

- **Build debug variant**: `./gradlew assembleDebug`
- **Build release variant**: `./gradlew assembleRelease`
- **Install on connected device/emulator**: `./gradlew installDebug`
- **Run all unit tests**: `./gradlew test`
- **Run a single unit test class**: `./gradlew test --tests "com.muggle.tiktokcopy.ExampleUnitTest"`
- **Run instrumented tests**: `./gradlew connectedAndroidTest`
- **Run lint for debug variant**: `./gradlew lintDebug` (or `lintRelease`)
- **Clean build**: `./gradlew clean`
- **Check dependency updates**: `./gradlew dependencyUpdates` (requires `com.github.ben-manes.versions` plugin, not currently configured)
- **List all available tasks**: `./gradlew tasks`

## Testing

- **Unit tests**: Located in `app/src/test/` (currently only an example test).
- **Instrumented tests**: Located in `app/src/androidTest/` (currently only an example test).
- **UI tests**: Can be added using `androidTestImplementation` dependencies already present (Compose UI test, Espresso).

## UI Utilities

- **Screen adaptation**: The project uses custom extension properties `Number.cdp` and `Number.csp` (defined in `utils/UiUtils.kt`) for density‑independent dimensions and font scaling. These are based on design dimensions `APP_DESIGN_WIDTH = 390` and `APP_DESIGN_HEIGHT = 844` (from `ui/theme/AppConfig.kt`).
- **Theme**: Material3 theme defined in `ui/theme/`. Includes a composition local `AppColorProvide` for custom app colors (currently defaults to `DefaultAppColor`).

## Notes

- The home screen implements a horizontal pager for tabs and a vertical pager for video feed (only "Recommend" tab is partially implemented).
- Many UI components and business logic are still stubs (e.g., other tabs, video player controls, friend/message/profile screens).
- A login feature exists (`business/login/`) with repository, ViewModel, and UI components, but is not integrated into the main navigation.
- All composables are written in Kotlin; there are no XML layouts.