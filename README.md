[![Build Status](https://travis-ci.com/WLBTeamUnisa/WorkLifeBalance.svg?branch=master)](https://travis-ci.com/WLBTeamUnisa/WorkLifeBalance)
 [![codecov](https://codecov.io/gh/WLBTeamUnisa/WorkLifeBalance/branch/master/graphs/badge.svg)](https://codecov.io/gh/WLBTeamUnisa/WorkLifeBalance)

<H1>Work Life Balance </H1 
------

<h2>Installation</h2>
------

<h3>Requirements</h3>
- git
- Eclipse IDE for Java EE Developers
- WildFly 18.0.1.Final
- Mysql server 5.7.20
- Mysqldb Schema: worklifebalance
- Default user and password for mysqldb: root root

------

<p>Download pre-configured wildfly</p>
```
https://download.jboss.org/wildfly/18.0.1.Final/wildfly-18.0.1.Final.zip
```

------

Download JBoss Tools 4.13.0.Final, essential for creating the Wildfly server

```
Eclipse--> Help--> Eclipse Marketplace-->JBoss Tools 4.13.0.Final
```

Create server Wildfly

```
File--> New Server--> JBoss Community--> WildFly18
```

Download current repository

```
git clone https://github.com/WLBTeamUnisa/WorkLifeBalance.git
cd WorkLifeBalance
```

------

Import downloaded folder into eclipse

```
Import --> Maven/Existing Maven Project
```

------

Build "work life balance" project using run as Maven build...

```
Goals: clean install.
Finish and if the build is successful, you will find the .war file in the target folder.
```

------

Copy builded file into wildfly's home folder

```
copy target/WorkLifeBalance.war on {WILDFLY_HOME}/standalone/deployments (wait for the creation of the .deployed file)
```

------

<h4>Execution</h4>
Just run wildfly server on eclipse  and check on http://localhost:8080/WorkLifeBalance/  on any browser.



<h2>Implementation</h2>
------

### Code formatting

Set `wlbFormatter.xml` as formatter configuration file

```
Project Properties --> Java Code Style --> Formatter --> Enable project specific settings --> Import
```

<h3>Travis CI</h3>
Go to `https://travis-ci.org/` after your commit and login with github. After login, you will be able to see if the single commit has passed the formatting constraints described in wlbFormatter.xml.

<h2>Sprint</h2>
------

<h3> 1° Sprint - Consegna 22 Dicembre </h3>
Di seguito i requisiti rilasciati per il primo sprint  5/32 (19 con priorità alta, 9 con priorità media, 4 con priorità bassa)

- Login (100%)
- Logout (100%)
- Insert Employee (100%)
- Insert Project (100%)
- Insert Employee at Project (85%)

- git
- Eclipse IDE for Java EE Developers
- WildFly 1.0.0.Final



<h3> 2° Sprint - Consegna 29 Dicembre </h3>

Di seguito i requisiti rilasciati per il secondo sprint  10/32 (19 con priorità alta, 9 con priorità media, 4 con priorità bassa)

- Modify Project (88,5%)
- Insert Planimetry (86,5%)
- Search Project 
- Search Employee
- Smart Working Prenotation (94,1%)

