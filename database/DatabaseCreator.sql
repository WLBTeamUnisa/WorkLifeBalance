-- MySQL dump 10.13  Distrib 8.0.18, for macos10.14 (x86_64)
--
-- Host: localhost    Database: worklifebalance
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `EMAIL` varchar(37) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(40) NOT NULL,
  `SURNAME` varchar(20) NOT NULL,
  PRIMARY KEY (`EMAIL`)
) ENGINE=MyISAM ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `EMAIL` varchar(37) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(40) NOT NULL,
  `STATUS` int(1) NOT NULL,
  `SURNAME` varchar(20) NOT NULL,
  PRIMARY KEY (`EMAIL`)
) ENGINE=MyISAM ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `floor`
--

DROP TABLE IF EXISTS `floor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `floor` (
  `NUM_FLOOR` int(3) NOT NULL,
  `EMAIL_ADMIN` varchar(37) NOT NULL,
  PRIMARY KEY (`NUM_FLOOR`),
  KEY `FKls8l9itrc1m8ruifm4bu5qa1j` (`EMAIL_ADMIN`)
) ENGINE=MyISAM ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `ID` int(20) NOT NULL AUTO_INCREMENT,
  `DATE` date NOT NULL,
  `TEXT` varchar(250) NOT NULL,
  `EMPLOYEE_EMAIL` varchar(37) NOT NULL,
  `ID_PROJECT` int(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK7m5bnpns31qoynvt02ktsoixt` (`EMPLOYEE_EMAIL`),
  KEY `FKsv51b55gibk6icr1yb1kdqm` (`ID_PROJECT`)
) ENGINE=MyISAM ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `prenotation_date`
--

DROP TABLE IF EXISTS `prenotation_date`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prenotation_date` (
  `DATE` date NOT NULL,
  `EMPLOYEE_EMAIL` varchar(37) NOT NULL,
  `ID_PRENOTATION_SW` int(20) NOT NULL,
  PRIMARY KEY (`DATE`,`EMPLOYEE_EMAIL`,`ID_PRENOTATION_SW`),
  KEY `FKmm3hcdligupegsi2quwsh46ns` (`EMPLOYEE_EMAIL`,`ID_PRENOTATION_SW`)
) ENGINE=MyISAM ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `ID` int(20) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(250) NOT NULL,
  `END_DATE` date NOT NULL,
  `NAME` varchar(15) NOT NULL,
  `SCOPE` varchar(50) NOT NULL,
  `START_DATE` date NOT NULL,
  `EMAIL_ADMIN` varchar(37) NOT NULL,
  `EMAIL_MANAGER` varchar(37) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKjhn4q6kabu60je4gskcwjsm9` (`EMAIL_ADMIN`),
  KEY `FKsqtaqsgapeoasfjm7n017rxf` (`EMAIL_MANAGER`)
) ENGINE=MyISAM AUTO_INCREMENT=2 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `NUM_FLOOR` int(3) NOT NULL,
  `NUM_ROOM` int(2) NOT NULL,
  PRIMARY KEY (`NUM_FLOOR`,`NUM_ROOM`)
) ENGINE=MyISAM ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `smart_working_prenotation`
--

DROP TABLE IF EXISTS `smart_working_prenotation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `smart_working_prenotation` (
  `EMPLOYEE_EMAIL` varchar(37) NOT NULL,
  `ID` int(20) NOT NULL AUTO_INCREMENT,
  `CALENDAR_WEEK` int(2) NOT NULL,
  `YEAR` int(4) NOT NULL,
  PRIMARY KEY (`ID`,`EMPLOYEE_EMAIL`),
  KEY `FKf000vk7j11pepxpspmihah4xs` (`EMPLOYEE_EMAIL`)
) ENGINE=MyISAM AUTO_INCREMENT=4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `works`
--

DROP TABLE IF EXISTS `works`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `works` (
  `ID_PROJECT` int(20) NOT NULL,
  `EMAIL_EMPLOYEE` varchar(37) NOT NULL,
  KEY `FK85aubsk8o6ai0ccabdiwi30yy` (`EMAIL_EMPLOYEE`),
  KEY `FK8k9rfpstgug5j5nxlixfguvee` (`ID_PROJECT`)
) ENGINE=MyISAM ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `workstation`
--

DROP TABLE IF EXISTS `workstation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workstation` (
  `NUM_FLOOR` int(3) NOT NULL,
  `NUM_ROOM` int(2) NOT NULL,
  `NUM_WORKSTATION` int(3) NOT NULL,
  PRIMARY KEY (`NUM_FLOOR`,`NUM_ROOM`,`NUM_WORKSTATION`)
) ENGINE=MyISAM ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `workstation_prenotation`
--

DROP TABLE IF EXISTS `workstation_prenotation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workstation_prenotation` (
  `EMPLOYEE_EMAIL` varchar(37) NOT NULL,
  `ID` int(20) NOT NULL AUTO_INCREMENT,
  `PRENOTATION_DATE` date NOT NULL,
  `CALENDAR_WEEK` int(2) NOT NULL,
  `YEAR` int(4) NOT NULL,
  `NUM_FLOOR` int(3) NOT NULL,
  `NUM_ROOM` int(2) NOT NULL,
  `NUM_WORKSTATION` int(3) NOT NULL,
  PRIMARY KEY (`ID`,`EMPLOYEE_EMAIL`,`PRENOTATION_DATE`),
  KEY `FKmi6h2vjcaslo1e7ogx6u6q24a` (`EMPLOYEE_EMAIL`),
  KEY `FKoo3dj4i4vois6bwmh0eeamy99` (`NUM_FLOOR`,`NUM_ROOM`,`NUM_WORKSTATION`)
) ENGINE=MyISAM AUTO_INCREMENT=2 ;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-22 22:37:40
