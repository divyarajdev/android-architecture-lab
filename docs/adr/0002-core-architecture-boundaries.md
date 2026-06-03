# 0002 - Core architecture boundaries

Date: 2026-06-03

## Status

Accepted

## Context

The repository needs enforceable core module boundaries before data sources, feature modules,
dependency injection, offline behavior, and UI feature flows are added.

## Decision

Introduce pure Kotlin core modules with explicit ownership:

- `:core:common` owns domain-safe shared primitives.
- `:core:model` owns immutable Release Readiness Lab domain models.
- `:core:domain` owns repository contracts and use cases.

Dependency direction:

- `:core:model` may depend on `:core:common`.
- `:core:domain` may depend on `:core:model`, `:core:common`, and coroutine APIs required by
  stream contracts.
- Core modules must not depend on Android framework APIs, Compose, Room, Retrofit, Hilt, DataStore,
  OkHttp, app code, feature code, persistence implementation, or network implementation.

Repository contracts expose domain models only. Use cases contain business rules that are reusable
outside UI, data, and dependency injection layers.

## Consequences

- Domain behavior can be unit tested without Android runtime dependencies.
- Data and feature implementation can be added later behind stable contracts.
- UI code cannot become the owner of release-readiness business rules.
- Architecture tests can verify public contracts do not expose framework or implementation types.
