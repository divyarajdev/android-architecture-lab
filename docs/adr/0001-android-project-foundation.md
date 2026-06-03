# 0001 - Android project foundation

Date: 2026-05-31

## Status

Accepted

## Context

The repository needs a reproducible Android foundation before feature modules, domain contracts,
data sources, offline behavior, and release workflow are implemented.

## Decision

Use a Gradle-based Android project with:

- Root namespace: `io.github.divyarajdev`
- App namespace: `io.github.divyarajdev.architecturelab`
- Android Gradle Plugin 8.13.2
- Gradle 8.13 wrapper distribution
- Java 17 compile target
- Kotlin configured through Gradle
- Gradle Version Catalog for dependency and plugin versions
- Gradle properties for namespace root, project namespace, and version metadata
- Spotless with ktlint for formatting validation
- GitHub Actions for build, formatting, test, and lint validation
- Dependabot for Gradle and GitHub Actions dependency monitoring
- Milestone-based release tags for traceability

## Consequences

- Build configuration is centralized.
- Dependency versions are reviewable in one catalog.
- App identity and release metadata are not duplicated across build files.
- Formatting and validation can run locally and in CI.
- Later architecture decisions can reference a stable foundation.
