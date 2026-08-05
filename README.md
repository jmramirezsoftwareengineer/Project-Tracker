# Project Manager

A modern Android application built with **Kotlin**, **Jetpack Compose**, **MVVM**, and **Clean Architecture** for managing projects. The application supports creating, updating, deleting, searching, and filtering projects while providing offline data persistence through Room. It is designed to be backend-ready by supporting both a local JSON data source and future Retrofit integration.

---

# Features

* Create Project
* Update Project
* Delete Project
* View Project Details
* Search Projects
* Filter Projects by Status
* Project Priority Management
* Start Date & Due Date Picker
* Offline Storage using Room
* Local JSON Data Source
* Settings using DataStore
* Due Project Notifications using WorkManager
* Clean Architecture with MVVM
* Dependency Injection using Koin
* Backend-ready architecture for Retrofit integration

---

# Setup Instructions

## Prerequisites

* Android Studio (Latest Stable Version)
* JDK 17 or later
* Android SDK 24+
* Gradle 8+

## Clone the Repository

```bash
git clone https://github.com/jmramirezsoftwareengineer/Project-Tracker.git
```

Open the project using Android Studio.

---

## Install Dependencies

Allow Android Studio to synchronize the Gradle project automatically.

---

## Local JSON Data

The application uses a local JSON file located at:

```text
app/src/main/assets/test_data.json
```

This file acts as a mock backend during development.

---

## Build the Project

Select an emulator or Android device and click:

```
Run > Run 'app'
```

or execute:

```bash
./gradlew assembleDebug
```

---

# Technology Choices

The application was built using modern Android development practices.

| Technology         | Purpose                            |
| ------------------ | ---------------------------------- |
| Kotlin             | Primary programming language       |
| Jetpack Compose    | Declarative UI Toolkit             |
| MVVM               | UI Architecture Pattern            |
| Clean Architecture | Separation of concerns             |
| Room               | Local database                     |
| Koin               | Dependency Injection               |
| Kotlin Coroutines  | Asynchronous programming           |
| StateFlow          | Reactive UI state management       |
| Navigation Compose | Screen navigation                  |
| DataStore          | Application settings               |
| WorkManager        | Background tasks and notifications |
| Gson               | JSON parsing                       |
| Local JSON Assets  | Mock backend                       |
| Material 3         | UI components and theming          |

---

# Project Structure

```text
ProjectCleanArchitecture/
│
├── data/
├── domain/
├── presentation/
├── datastore/
├── worker/
├── di/
└── app/
```

Each layer has a single responsibility:

* **Presentation** – UI, ViewModels, Navigation
* **Domain** – Business models, Repository interfaces, Use Cases
* **Data** – Room, Local JSON, Repository implementation

---

# How to Run the Application

1. Clone the repository.
2. Open the project in Android Studio.
3. Wait for Gradle Sync to finish.
4. Run the application on an emulator or physical device.
5. On first launch, the application imports data from `assets/test_data.json` into the Room database.
6. All CRUD operations are performed against the Room database.
7. Future backend integration can be enabled without changing the presentation layer.

---

# Assumptions Made

* The application is intended for a single-user environment.
* A local JSON file is used as the initial data source because no backend API is available.
* Room acts as the single source of truth after the initial data import.
* Project IDs contained in the JSON file are unique.
* Internet connectivity is not required for normal application usage.
* Notifications are scheduled locally using WorkManager.
* Authentication and user management are outside the scope of this project.
* The architecture is designed so a Retrofit-based backend can replace the local JSON source with minimal code changes.

---

# Future Improvements

* Integrate a REST API using Retrofit.
* Implement offline-first synchronization.
* Add Paging 3 for large datasets.
* Replace String date fields with `LocalDate` and Room `TypeConverter`s.
* Add unit tests and Jetpack Compose UI tests.
* Add GitHub Actions for CI/CD.
* Modularize the application into feature modules.
* Improve error handling with a unified `Result`/`UiState` pattern.

---

# Architecture

```text
Presentation
      │
ViewModels
      │
Use Cases
      │
Repository
      │
 ┌────┴────┐
 ▼         ▼
Room   Local JSON
           │
      Future Retrofit API
```
