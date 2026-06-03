# Contributing

This repository uses milestone-scoped work with pull requests targeting `master`.

## Branches

Use:

```text
<type>/m<#>-<short-slug>
```

Example:

```text
chore/m1-foundation
```

## Commits

Use:

```text
<type>(<scope>): <short description>
```

Allowed types:

```text
docs, feat, fix, refactor, chore, test
```

Example:

```text
chore(foundation): set up Android project foundation
```

## Pull Requests

- Link the owning issue with `Closes #<issue-number>`.
- Keep pull requests scoped to one milestone task.
- Use squash merge into `master`.
- Do not merge when build, formatting, tests, or lint fail.

## Validation

Run:

```bash
./gradlew clean
./gradlew spotlessCheck
./gradlew assembleDebug
./gradlew testDebugUnitTest
./gradlew lintDebug
```

## Public Work Policy

Do not include company code, client names, private metrics, internal screenshots, secrets,
production API contracts, or proprietary implementation details.
