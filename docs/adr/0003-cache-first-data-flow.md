# ADR 0003: Cache-First Data Flow

## Status

Accepted

## Context

The repository now has domain contracts and use cases, but data ownership must be defined before
feature UI and offline states are added. Android architecture guidance recommends exposing
application data through repositories, using coroutines and Flow between layers, and using a local
source of truth for offline-first support.

## Decision

Use a cache-first data layer for release candidate data.

- `:core:database` owns Room entities, DAO access, and the local data source.
- `:core:network` owns remote DTOs and the fake remote data source.
- `:core:data` owns repository implementation and mapping between DTO, entity, and domain models.
- `OfflineFirstReleaseCandidateRepository` observes local data as the source of truth.
- Refresh reads from the remote source and replaces local cached data in one transaction.
- Refresh failure preserves existing cached data and returns a typed domain refresh result.

## Consequences

- Domain contracts remain free of Room entities, DTOs, transport types, and database APIs.
- Later UI state can observe repository flows without directly accessing data sources.
- Offline and stale-data states can build on a stable local source of truth in later milestones.
- Production networking and dependency injection remain separate future milestones.
