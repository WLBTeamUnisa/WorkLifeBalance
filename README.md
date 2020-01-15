[![Build Status](https://travis-ci.com/WLBTeamUnisa/WorkLifeBalance.svg?branch=master)](https://travis-ci.com/WLBTeamUnisa/WorkLifeBalance)

<h1>Work Life Balance </h1> 
<h2>Installation</h2>
<h3>Requirements</h3>
- git
- Eclipse IDE for Java EE Developers
- WildFly 18.0.1.Final
- Mysql server 5.7.20
- Mysqldb Schema: worklifebalance
- Default user and password for mysqldb: root root

------

<p>Download pre-configured wildfly</p>
https://download.jboss.org/wildfly/18.0.1.Final/wildfly-18.0.1.Final.zip

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
### Code formatting

Set `wlbFormatter.xml` as formatter configuration file

```
Project Properties --> Java Code Style --> Formatter --> Enable project specific settings --> Import
```

<h3>Travis CI</h3>
Go to `https://travis-ci.org/` after your commit and login with github. After login, you will be able to see if the single commit has passed constraints defined in the .travis.yml file.

<h2>Sprint</h2>
<p> 19 requisiti con priorità alta, 9 con priorità media, 4 con priorità bassa </p>
<h3> 1° Sprint - Consegna 22 Dicembre </h3>
Di seguito i requisiti rilasciati per il primo sprint  5/32 

- RF_GA_17 : Login (100%)
- RF_GA_18 : Logout (100%)
- RF_GD_1 : Registra dipendente (100%)
- RF_GP_7 : Inserisci progetto (100%)
- RF_GP_12 : Inserisci dipendenti ad un progetto (80%) - inerente all'inserimento del progetto

- Configurazione dell'ambiente di sviluppo



<h3> 2° Sprint - Consegna 29 Dicembre </h3>
Di seguito sono elencati i requisiti rilasciati per il secondo sprint 12/32

- RF_GP_10 : Modifica progetto (100%)
- RF_GP_14 : Inserisci planimetria (100%)
- RF_GP_11: Visualizza tutti i progetti (100%)
- RF_GP_8 : Ricerca progetto (100%)
- RF_GD_5 : Ricerca dipendente (100%)
- RF_GD_6: Visualizza lista dipendenti (100%)
- RF_GL_23 : Prenota giorni di Smart Working (100%)
- RF_GP_12 : Inserisci dipendenti ad un progetto (100%) - inerente all'inserimento del progetto

<h3> 3° Sprint - Consegna 05 Gennaio </h3>
Di seguito sono elencati i requisiti rilasciati per il terzo sprint 17/32 

- RF_GD_3 : Modifica status dipendente (100%)
- RF_GD_4 : Visualizza profilo dipendente (100%)
- RF_GP_12 : Inserisci dipendenti ad un progetto (100%) - inerente alla modifica del progetto 
- RF_GL_20 : Prenota postazione di lavoro (100%)
- RF_GL_21 : Visualizza pianificazione settimanale (100%)

### 4° Sprint - Consegna 12 Gennaio

Di seguito sono elencati i requisiti rilasciati per il quarto sprint   22/32

- RF_GL_22 : Visualizza storico calendario (100%)

- RF_GP_19 : Visualizza planimetria (100%)

- RF_GM_29 : Ricerca storico giornate lavorative (100%)

- RF_GM_31 : Visualizza tutti i progetti supervisionati (100%)

- RF_GM_32 : Visualizza progetto supervisionato (100%)

  

### Resoconto:

- I requisiti a priorità alta implementati sono 19/19	
- I requisiti a priorità media implementati sono 3/9
- I requisiti a priorità bassa implementati sono 0/4

