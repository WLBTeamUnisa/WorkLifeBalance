-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: worklifebalance
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
INSERT INTO `admin` VALUES ('l.cerrone3@wlbadmin.it','Luigi','809188a77a05f59eed95081d48007ebb723725db','Cerrone');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('v.fabiano7@wlb.it','Vincenzo','d34ef584d33f026f4bb4b3be40b7e6a1866cc0c4',1,'Fabiano'),('s.singh3@wlb.it','Simranjit','7056b7ab582ceba488f03a2f877d99722653cfbb',0,'Singh'),('s.nocera1@wlb.it','Sabato','26133ad2b46b97ebb845d0a5dd485e3aad7158e1',0,'Nocera');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `floor`
--

LOCK TABLES `floor` WRITE;
/*!40000 ALTER TABLE `floor` DISABLE KEYS */;
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
/*!40000 ALTER TABLE `prenotation_date` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (14,'Progetto che si occupa della realizzazione di un applicazione android ','2022-06-09','TSXgame','applicazione android','2017-12-10','l.cerrone3@wlbadmin.it','v.fabiano7@wlb.it'),(13,'Progetto che si occupa della realizzazione di un e-commerce ','2020-12-12','Eco-Sport','e-commerce di articoli sportivi','2019-12-10','l.cerrone3@wlbadmin.it','v.fabiano7@wlb.it');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `smart_working_prenotation`
--

LOCK TABLES `smart_working_prenotation` WRITE;
/*!40000 ALTER TABLE `smart_working_prenotation` DISABLE KEYS */;
/*!40000 ALTER TABLE `smart_working_prenotation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `works`
--

LOCK TABLES `works` WRITE;
/*!40000 ALTER TABLE `works` DISABLE KEYS */;
INSERT INTO `works` VALUES (13,'s.singh3@wlb.it'),(14,'s.nocera1@wlb.it');
/*!40000 ALTER TABLE `works` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `workstation`
--

LOCK TABLES `workstation` WRITE;
/*!40000 ALTER TABLE `workstation` DISABLE KEYS */;
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

-- Dump completed on 2019-12-22 18:18:40
