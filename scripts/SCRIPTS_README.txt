This documents lists and shortly describes the scripts in this
directory. All of them must be executed from this directory.
The list in alphabetical order:

 build
 genDDG
 runRegressionTests

- build: compiles all source code and updates directory /bin

- genDDG: invokes depend.Main to create dependency graph

- runRegressionTests: run the regression test suite for this project
<<<<<<< HEAD

Example of use 1 (Scenario "dependency graph"):

$> ./build
############  $> ./genAppJar
$> ./genDDG
$> acroread results/results.pdf

Example of use 2 (Scenario "program instrumentation"):

 $> ./build
 $> ./genAgent
 $> ./run
=======
>>>>>>> 76ad96127783b32d6c65c46de5424437b7983232
