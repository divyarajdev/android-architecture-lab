# android-architecture-lab

Android reference project for Kotlin, Jetpack Compose, reproducible Gradle builds, centralized
dependency governance, CI validation, ADR tracking, and milestone-based architecture decisions.

## Current Status

M1 - Foundation is complete: build tooling, repository governance, CI validation, Dependabot,
and ADR tracking are in place.

M2 - Core Architecture is complete: framework-independent core modules, domain contracts,
repository interfaces, use cases, architecture boundary tests, and ADR documentation are in place.

M3 - Data Stack is planned: repository implementation, local cache, remote source, and data-layer
boundary enforcement.

## Repository Scope

This repository uses a fictional Release Readiness Lab domain.

It contains no company code, client names, private metrics, internal screenshots, secrets,
production API contracts, or proprietary implementation details.

Repository content is limited to public portfolio work, fictional data, and reproducible Android
implementation examples.

## Requirements

- Android Studio stable release with Android Gradle Plugin 9.x support
- JDK 17 toolchain
- Android SDK 36 installed
- Gradle wrapper from this repository

## Setup

```bash
git clone git@github-divyarajdev:divyarajdev/android-architecture-lab.git
cd android-architecture-lab
./gradlew assembleDebug
```

## Engineering Focus

- Reproducible Android build foundation
- Java 17 toolchain
- Android Gradle Plugin-compatible Kotlin
- Centralized dependency management with Gradle Version Catalog
- Centralized app identity and version metadata with Gradle properties
- Formatting enforcement with Spotless and ktlint
- GitHub Actions validation for build, formatting, tests, and lint
- Dependabot for Gradle and GitHub Actions
- Architecture decision tracking with ADRs
- Security, contribution, review, and release governance
- Milestone-based release traceability

## Repository Structure

| Path              | Responsibility                                                                        |
|-------------------|---------------------------------------------------------------------------------------|
| `.github/`        | GitHub Actions, Dependabot, CODEOWNERS, issue form, and pull request template         |
| `app/`            | Minimal Android application module for M1 build validation                            |
| `docs/adr/`       | Architecture Decision Records for build, module, data, testing, and release decisions |
| `gradle/`         | Version Catalog and Gradle wrapper configuration                                      |
| Root Gradle files | Repository-level build configuration, formatting, and Android project settings        |

## Planned Architecture Direction

- Explicit UI, domain, and data layer ownership
- Feature modules isolated behind stable core contracts
- ViewModel-owned screen state exposed as immutable `StateFlow`
- Lifecycle-aware state collection in Jetpack Compose
- UI events handled through unidirectional data flow
- Business rules kept outside composables
- Domain use cases added where reuse or business complexity justifies them
- Data access exposed through repository contracts
- Room, Retrofit, OkHttp, and DataStore kept behind the data layer
- Cache-first data flow for offline and stale-data handling
- Unit, UI, lint, and CI checks used as quality gates

## Quality Gates

| Check             | Command                   |
|-------------------|---------------------------|
| Clean build state | `./gradlew clean`         |
| Formatting        | `./gradlew spotlessCheck` |
| Debug build       | `./gradlew assembleDebug` |
| Unit tests        | `./gradlew test`          |
| Android lint      | `./gradlew lintDebug`     |

## Project Governance

- [Contributing](CONTRIBUTING.md)
- [Security policy](SECURITY.md)
- [Code of conduct](CODE_OF_CONDUCT.md)
- [Changelog](CHANGELOG.md)
- [Architecture decisions](docs/adr/README.md)

## Milestone Roadmap

| Phase                             | Focus                                                   | Due        |
|-----------------------------------|---------------------------------------------------------|------------|
| M1 - Foundation                   | Build tooling, governance, CI, Dependabot, ADR baseline | 2026-06-07 |
| M2 - Core Architecture            | Core modules, domain contracts, use cases               | 2026-06-14 |
| M3 - Data Stack                   | Repository implementation, local cache, remote source   | 2026-06-21 |
| M4 - Compose Feature Flow         | Dashboard, detail, settings, lifecycle-aware UI state   | 2026-06-28 |
| M5 - Reliability And Offline      | Offline state, stale data, retry, failure handling      | 2026-07-05 |
| M6 - Testing And Portfolio Polish | Tests, documentation, screenshots, release readiness    | 2026-07-12 |

## License

[MIT](LICENSE)
