# Git workflow
## Intro
Branches main and develop are protected (cannot be pushed on). Working on every new feature begins with creating new branch from develop one.
### Working on new feature
1. git checkout develop
2. git pull
3. create new branch with meaningful name (e.g. add_map_pins) // it should be YouTrack task name and id, tbd...
4. develop and test code
5. git commit (commit messages are not that important)
6. git push
7. github - create pull request
8. wait for review
9. fix all issues
10. mark issues resolved

### Git conflict
If changes from your feature branch cannot be merged automatically:
1. merge develop into your feature branch
2. resolve all conflicts
3. test your code again
4. git push
5. create pull request/mark as resolved