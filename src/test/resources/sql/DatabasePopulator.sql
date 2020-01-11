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
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('e.tesauro1@wlbadmin.it','Emmanuel','d5c28294c73615f473e7498cabd6ce4259382742','Admin');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('s.nocera1@wlb.it','Sabato','d5c28294c73615f473e7498cabd6ce4259382742',1,'Nocera'),('l.cerrone1@wlb.it','Luigi','d5c28294c73615f473e7498cabd6ce4259382742',0,'Cerrone'),('m.rossi1@wlb.it','Marco','d5c28294c73615f473e7498cabd6ce4259382742',0,'Rossi'),('v.fabiano1@wlb.it','Vincenzo','d5c28294c73615f473e7498cabd6ce4259382742',0,'Fabiano'),('s.singh1@wlb.it','Simranjit','d5c28294c73615f473e7498cabd6ce4259382742',1,'Singh');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `floor`
--

LOCK TABLES `floor` WRITE;
/*!40000 ALTER TABLE `floor` DISABLE KEYS */;
INSERT INTO `floor` VALUES (2,'e.tesauro1@wlbadmin.it'),(1,'e.tesauro1@wlbadmin.it');
/*!40000 ALTER TABLE `floor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `prenotation_date`
--

LOCK TABLES `prenotation_date` WRITE;
/*!40000 ALTER TABLE `prenotation_date` DISABLE KEYS */;
INSERT INTO `prenotation_date` VALUES ('2019-12-31','v.fabiano1@wlb.it',15),('2020-01-01','v.fabiano1@wlb.it',15),('2020-01-02','v.fabiano1@wlb.it',15);
/*!40000 ALTER TABLE `prenotation_date` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (19,'Progetto per il Dipartimento di Informatica','2020-01-15','Progetto1','Informatica','2019-10-01','e.tesauro1@wlbadmin.it','s.nocera1@wlb.it'),(20,'Progetto per il Dipartimento di Lingue e Letterature Straniere','2021-10-10','Progetto2','Lingue e Letteratura','2018-10-10','e.tesauro1@wlbadmin.it','s.nocera1@wlb.it');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,1),(1,2),(2,1);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `smart_working_prenotation`
--

LOCK TABLES `smart_working_prenotation` WRITE;
/*!40000 ALTER TABLE `smart_working_prenotation` DISABLE KEYS */;
INSERT INTO `smart_working_prenotation` VALUES ('v.fabiano1@wlb.it',15,1,2020);
/*!40000 ALTER TABLE `smart_working_prenotation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `works`
--

LOCK TABLES `works` WRITE;
/*!40000 ALTER TABLE `works` DISABLE KEYS */;
INSERT INTO `works` VALUES (20,'v.fabiano1@wlb.it'),(20,'l.cerrone1@wlb.it'),(19,'l.cerrone1@wlb.it'),(19,'v.fabiano1@wlb.it'),(18,'l.cerrone1@wlb.it'),(18,'v.fabiano1@wlb.it'),(17,'m.rossi1@wlb.it'),(17,'v.fabiano1@wlb.it');
/*!40000 ALTER TABLE `works` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `workstation`
--

LOCK TABLES `workstation` WRITE;
/*!40000 ALTER TABLE `workstation` DISABLE KEYS */;
INSERT INTO `workstation` VALUES (1,1,1),(1,1,2),(1,1,3),(1,1,4),(1,1,5),(1,1,6),(1,1,7),(1,1,8),(1,1,9),(1,1,10),(1,2,1),(1,2,2),(1,2,3),(1,2,4),(1,2,5),(1,2,6),(1,2,7),(1,2,8),(1,2,9),(1,2,10),(1,2,11),(1,2,12),(1,2,13),(1,2,14),(1,2,15),(1,2,16),(1,2,17),(1,2,18),(1,2,19),(1,2,20),(1,2,21),(1,2,22),(1,2,23),(1,2,24),(1,2,25),(1,2,26),(1,2,27),(1,2,28),(1,2,29),(1,2,30),(2,1,1),(2,1,2),(2,1,3),(2,1,4),(2,1,5),(2,1,6),(2,1,7),(2,1,8),(2,1,9),(2,1,10),(2,1,11),(2,1,12),(2,1,13),(2,1,14),(2,1,15),(2,1,16),(2,1,17),(2,1,18),(2,1,19),(2,1,20);
/*!40000 ALTER TABLE `workstation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `workstation_prenotation`
--

LOCK TABLES `workstation_prenotation` WRITE;
/*!40000 ALTER TABLE `workstation_prenotation` DISABLE KEYS */;
/*!40000 ALTER TABLE `workstation_prenotation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-29 21:59:40
