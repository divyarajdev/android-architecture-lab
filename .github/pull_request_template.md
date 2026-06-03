# 🔀 Pull Request

## 📝 Title

Use the GitHub PR title field with:

```txt
<type>(<scope>): <short description>
```

Allowed types: `docs`, `feat`, `fix`, `refactor`, `chore`, `test`

## 📌 Summary

Describe the implemented change, affected milestone, and primary technical boundary.

## 🎯 Milestone

Use one milestone:

| Milestone                         | PR title                                               | Branch                        | Commit                                                 | Target date  |
|-----------------------------------|--------------------------------------------------------|-------------------------------|--------------------------------------------------------|--------------|
| M1 - Foundation                   | `chore(foundation): set up Android project foundation` | `chore/m1-foundation`         | `chore(foundation): set up Android project foundation` | `2026-06-07` |
| M2 - Core Architecture            | `feat(core): add domain contracts and use cases`       | `feat/m2-core-architecture`   | `feat(core): add domain contracts and use cases`       | `2026-06-14` |
| M3 - Data Stack                   | `feat(data): implement cache-first data stack`         | `feat/m3-data-stack`          | `feat(data): implement cache-first data stack`         | `2026-06-21` |
| M4 - Compose Feature Flow         | `feat(ui): build compose feature flow`                 | `feat/m4-compose-flow`        | `feat(ui): build compose feature flow`                 | `2026-06-28` |
| M5 - Reliability And Offline      | `feat(reliability): add offline and failure handling`  | `feat/m5-reliability-offline` | `feat(reliability): add offline and failure handling`  | `2026-07-05` |
| M6 - Testing And Portfolio Polish | `test(quality): add coverage and portfolio polish`     | `test/m6-testing-polish`      | `test(quality): add coverage and portfolio polish`     | `2026-07-12` |

Selected milestone:

```txt
Milestone <#> — <Milestone Name>
```

## 🏷️ Label

Use one allowed label:

```txt
documentation
bug
duplicate
enhancement
good first issue
help wanted
invalid
question
wontfix
```

Selected label:

```txt
<allowed-label>
```

## 🔗 Related Issue

Use issue number links only in this PR/MR section.

```txt
Closes #<Issue Number>
```

## 🌿 Branch

```txt
<type>/m<#>-<short-slug>
```

## 💬 Commit Message

```txt
<type>(<scope>): <short description>
```

## 📂 Changes

- [ ] Implemented the approved milestone scope. Reason: reviewer must verify the PR matches the
  linked issue.
- [ ] Kept changes inside the approved structure. Reason: reviewer must verify no unrelated
  structure changes were introduced.
- [ ] Updated documentation for completed work only. Reason: public documentation must not overstate
  implementation status.

## 🎯 Purpose

Explain why this change is required for the selected milestone.

## 🧪 Validation

- [ ] `./gradlew clean` passes. Reason: build cleanup must be verified before merge.
- [ ] `./gradlew spotlessCheck` passes. Reason: formatting enforcement must be verified before
  merge.
- [ ] `./gradlew assembleDebug` passes. Reason: debug build must be verified before merge.
- [ ] `./gradlew testDebugUnitTest` passes. Reason: unit test execution must be verified before
  merge.
- [ ] `./gradlew lintDebug` passes. Reason: Android lint must be verified before merge.
- [ ] GitHub Actions passes. Reason: remote validation must be verified before merge.

## 🏛️ Architecture Checklist

- [ ] Dependency direction is preserved. Reason: module ownership must be verified.
- [ ] Build configuration remains centralized. Reason: dependency, plugin, SDK, and app identity
  configuration must not drift.
- [ ] Repository-level dependency repositories are preserved. Reason: project modules must not
  define their own dependency repositories.
- [ ] ADRs are added or updated when architecture decisions change. Reason: decision history must
  stay traceable.
- [ ] README describes completed work only. Reason: public documentation must not claim future
  milestones as implemented.

## 🔐 Security Checklist

- [ ] No secrets, API keys, private services, or production endpoints are committed. Reason:
  repository contents must be reviewed before merge.
- [ ] No company code, client names, private metrics, internal screenshots, or proprietary
  implementation details are included. Reason: public portfolio policy must be preserved.
- [ ] `local.properties` is not committed. Reason: machine-specific files must stay out of Git.
- [ ] `.idea/` is not committed. Reason: IDE machine state must stay out of Git.

## 🖼️ Screenshots / Output

Add screenshots or command output only when the change has user-visible behavior or validation
evidence.

Use `Not applicable` when the change has no user-visible output.

## 👀 Reviewer Notes

List specific files, decisions, or tradeoffs that need reviewer attention.

## ✅ Checkbox Rule

- Use `[x]` only when the statement is already true or verified.
- Use `[ ]` when implementation, review, CI, testing, or validation is still required.
- Every unchecked item must include `Reason:`.
- Every checked item must include `Checked because:`.
