# README #

### What is this repository for? ###

* This repository is for test projects for the Anderson University team.  It includes a web service, a UI, and an automated test suite that will be used at the beginning of the internship

### How do I get set up? ###

#### Python ####
OSX:

1. Brew install python
2. sudo pip install --upgrade pip
3. sudo pip install -U Flask 
4. sudo pip install -U flask-cors
5. sudo pip install simplejson

Windows:

1. install: https://www.python.org/ftp/python/2.7.10/python-2.7.10.amd64.msi
2. update PATH system environment variable.  append: C:\Python27
3. update PATH system environment variable.  append: C:\Python27\Scripts
4. cmd prompt: pip install --upgrade pip
5. cmd prompt: pip install -U Flask
6. cmd prompt: pip install -U flask-cors
7. cmd prompt: pip install simplejson

* project can be run from pycharm or from command line in correct directory: python app.py
* Documentation on api endpoints: https://confluence.inin.com/display/Testing/Anderson+University+Project+API

#### UI Project ####
* double click on index page and run after python web service is running

#### Java Automation Project ####
* Follow instructions for Java and intellij setup here: https://confluence.inin.com/display/PureCloud/Tools+and+Setup+for+test+automation+Projects
* In your IDE, set a run parameter in testNG to field: environment, value: local    - in TestNG, go to Run -> Edit Configuration -> Defaults -> TestNG -> parameters

### Who do I talk to? ###

* Brian Schultz