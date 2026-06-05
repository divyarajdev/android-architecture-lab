# Changelog

Repository changes are tracked by milestone and release tag.

## Unreleased

### M3 - Data Stack

- In progress: repository implementation, local cache, remote source, and data-layer boundary
  enforcement.

## v0.2.0-core-architecture

### M2 - Core Architecture

- Core modules added for shared primitives, domain models, and use cases.
- Kotlin/JVM convention plugin added for centralized core module configuration.
- Release Readiness Lab domain models added for release candidates, quality gates, module health,
  and readiness decisions.
- Repository contracts added with domain-only type exposure.
- Use cases added for observing, retrieving, and evaluating release candidates.
- Unit tests added for readiness evaluation and repository-backed use cases.
- Architecture boundary test added for repository contract exposure.
- ADR added for core architecture ownership and dependency direction.

## v0.1.0-foundation

### M1 - Foundation

- Android project foundation initialized.
- Java 17, Kotlin, Compose, and Gradle Version Catalog configured through Gradle.
- Spotless with ktlint configured for formatting enforcement.
- GitHub Actions workflow added for build, formatting, test, and lint validation.
- Dependabot configured for Gradle and GitHub Actions.
- Repository governance files added for contribution, security, conduct, review, and release
  tracking.
- ADR baseline added for foundation-level architecture decisions.
