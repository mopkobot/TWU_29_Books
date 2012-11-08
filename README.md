twu_29_Books
=====

Development Environment Setup
-----------------------------

For Mac:
1. Clone the project repository using git.
2. Install HomeBrew
3. Run "brew install gradle"
4. Run "gradle loadData"
5. Run "gradle build" to run all tests and build a WAR.
6. Run "gradle jettyRun" to deploy using jetty.

For linux:
1. Clone the project repository using git.
2. Run "yum install gradle"
3. Run "gradle loadData"
4. Run "gradle build" to run all tests and build a WAR.
5. Run "gradle jettyRun" to deploy using jetty.

IntelliJ IDEA Setup
-------------------
1. Run "gradle idea" to create the project files
2. Open the twu_29_Books.ipr file

Extra Notes
-----------------
Please do "gradle build" before COMMITTING to the git repository. If the build failed, please DO NOT push your code
to the repository as it can break the build in the CI server.


Setting Environment Variable
-----------------------------


Jasmine Server
-------------------------------
If you don't install jasmine, use "gem install jasmine"

rake jasmine: start jasmine server

visit the link given by server to run all jasmine test


Run specific functional test
--------------------------------
If you like to run all the functional tests from a single test class:

Assume you want to run all the tests from SearchBookFunctionalTest,
you can use this command: gradle functionaltest -Pfile=SearchBookFunctionalTest