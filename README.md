# android-architecture-lab

Android reference project for Kotlin, Jetpack Compose, modular architecture, repository-backed data
access, offline-capable data flow, testing, CI validation, and release engineering.

## Current Status

M1 - Foundation is in progress.

## Repository Scope

This repository uses a fictional Release Readiness Lab domain.

It contains no company code, client names, private metrics, internal screenshots, secrets,
production API contracts, or proprietary implementation details.

## Engineering Focus

- Reproducible Android build foundation
- Java 17 toolchain
- Latest stable Kotlin
- Centralized dependency management with Gradle Version Catalog
- Formatting enforcement with Spotless and ktlint
- GitHub Actions validation
- Dependabot for Gradle and GitHub Actions
- Architecture decision tracking with ADRs
- Security, contribution, and release governance
- Milestone-based release traceability

## Planned Architecture Direction

- Clear UI, domain, and data layer ownership
- Feature modules isolated behind stable core contracts
- ViewModel-owned screen state exposed as immutable `StateFlow`
- Lifecycle-aware state collection in Jetpack Compose
- UI events handled through unidirectional data flow
- Business rules kept outside composables
- Domain use cases added where reuse or business complexity justifies them
- Data access exposed through repositories
- Room, Retrofit, OkHttp, and DataStore kept behind the data layer
- Cache-first data flow for offline and stale-data handling
- Unit, UI, lint, and CI checks used as quality gates

## Validation

```bash
./gradlew clean
./gradlew spotlessCheck
./gradlew assembleDebug
./gradlew testDebugUnitTest
./gradlew lintDebug
```

## Milestone Roadmap

| Phase                  | Focus                                                   | Due        |
|------------------------|---------------------------------------------------------|------------|
| M1 - Foundation        | Build tooling, governance, CI, Dependabot, ADR baseline | 2026-06-07 |
| M2 - Core Architecture | Module boundaries, domain contracts, use cases          | 2026-06-14 |
| M3 - Data Stack        | Repository implementation, local cache, remote source   | 2026-06-21 |
| M4 - Compose Flow      | Dashboard, detail, settings, lifecycle-aware UI state   | 2026-06-28 |
| M5 - Reliability       | Offline state, stale data, retry, failure handling      | 2026-07-05 |
| M6 - Portfolio Ready   | Tests, documentation, screenshots, release readiness    | 2026-07-12 |

## License

MIT
