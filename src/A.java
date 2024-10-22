name: Continuous Integration
on:
    pull_request:
        types: [opened, reopened]
        branches: "feature/**"
jobs:
    tests:
        runs-on: ubuntu-latest
        steps:             
        -  
            name: Git Checkout 
            uses: actions/checkout@v4 
 
        -   name: Setup Java SDK 
            uses: actions/setup-java@v4 
            with: 
              distribution: 'temurin'  
              java-version: '21' 
        -   name: Unit tests
            run: mvn test