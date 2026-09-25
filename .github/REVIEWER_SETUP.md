# GitHub reviewer setup

This repository includes two GitHub-based review flows:

## 1) PR Reviewer Bot
File: `.github/workflows/pr-reviewer-bot.yml`

What it does:
- runs on pull requests
- checks the files changed in the PR
- posts a basic review summary comment
- warns when no tests are included or build/config files changed

## 2) Team Reviewer Flow
File: `.github/workflows/team-review-request.yml`

What it does:
- requests review from a GitHub organization team
- can be triggered automatically for PRs opened or labeled

## Required setup

1. Update the values in `.github/workflows/team-review-request.yml`:
   - `ORG_NAME: your-org`
   - `TEAM_SLUG: your-team`
   - `REVIEW_LABEL: team-review`
2. Make sure the repository has permission to request reviewers from the team.
3. If the team is private, confirm the GitHub Actions token and repo settings allow reviewer assignment.

## Notes

- These workflows use GitHub Actions and GitHub PR review assignment; they are not local Copilot agent files.
- The custom Copilot agents under `.github/agents` show up in the VS Code agent dropdown, but GitHub PR reviewer lists are actual GitHub users, teams, or apps.
- To assign real reviewer teams, the org must grant the required access and the workflow must use a valid team slug.
