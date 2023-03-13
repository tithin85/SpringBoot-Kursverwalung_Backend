CREATE DATABASE  IF NOT EXISTS `kvgruppeb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `kvgruppeb`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: kvgruppeb
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `kurs`
--

DROP TABLE IF EXISTS `kurs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kurs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `aktuelle_tn_zahl` int DEFAULT NULL,
  `anzahl_tage` varchar(255) NOT NULL,
  `ende_datum` date NOT NULL,
  `freie_plaetze` int DEFAULT NULL,
  `gebuehr_brutto` double NOT NULL,
  `gebuehr_netto` double DEFAULT NULL,
  `kurs_beschreibung` varchar(255) DEFAULT NULL,
  `max_tn_zahl` int NOT NULL,
  `min_tn_zahl` int NOT NULL,
  `mwst_euro` double NOT NULL,
  `mwst_prozent` double NOT NULL,
  `name` varchar(255) NOT NULL,
  `start_datum` date NOT NULL,
  `status` varchar(255) NOT NULL,
  `wie_oftin_woche` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kurs`
--

LOCK TABLES `kurs` WRITE;
/*!40000 ALTER TABLE `kurs` DISABLE KEYS */;
INSERT INTO `kurs` VALUES (5,2,'5','2023-03-18',1,55.49,44.55,'Kleiner Informationskurs zum Thema Python.',3,2,10.45,19,'Python Info','2023-03-11','geplant','1'),(6,2,'5','2023-04-06',0,199,161.19,'Zaubern in einer Woche!',2,2,37.81,19,'Hogwards Einsteiger','2023-03-30','aktiv','5'),(7,6,'1','2023-03-25',6,149,120.69,'Arduino für Schüler.',12,10,28.31,19,'Arduino','2023-03-10','beendet','1'),(8,3,'1','2023-04-03',1,79.5,73.935,NULL,4,2,5.565,7,'TypeScript for Dummies','2023-03-13','geplant','1');
/*!40000 ALTER TABLE `kurs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `ort` varchar(100) NOT NULL,
  `plz` varchar(100) NOT NULL,
  `strasse` varchar(100) NOT NULL,
  `telefon` varchar(100) DEFAULT NULL,
  `titel` varchar(100) DEFAULT NULL,
  `vorname` varchar(100) NOT NULL,
  `anrede` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'ab@mail.de','Burnau','Bremen','28195','Steinweg 17','0421 965 88 745','Prof. Dr.','Alessia','Frau'),(2,'abc@mail.de','Buschwinkel','Bremen','28203','Öddelweg 13','0421 99 478 25','Dr.','Bärbel','Frau'),(3,'a.jaeger@hotmail.de','Jäger','Hamburg','21208','Buschweg 3','040 7369458','Dr. med.','Alexander','Herr'),(4,'ojones@hotmail.com','Jones','Hamburg','21098','Reeperbahn 69','040 96 35 45 77',NULL,'Olivia','Divers'),(5,'schmidt.harald@gmx.de','Schmidt','Dortmund','44244','Lessingstr. 23','0535 66 99 875',NULL,'Harald','Herr'),(6,'f.v.fisch@fisch.de','Fisch','Münschen','98756','Burgstr. 45','090 88 77 254','Freifrau von','Helena','Frau'),(7,'rip@sky.org','Phönix','Skytown','57842','Wolke 7',NULL,NULL,'River',NULL),(8,'mm@mail.de','Müller','Braunschweig','35600','Hauptstr. 17',NULL,NULL,'Manfred','Herr'),(9,'monika.hammer@googlemail.com','Hammer','Berlin','10852','Hubertusallee 1','030 958 978 15',NULL,'Monika',NULL),(10,'a.richter@gmail.com','Richter','Breitbachtal','47965','Schulweg 78','0172 9548 365',NULL,'Annegret','Frau'),(11,'otti@fant.de','Walker','Lüneburg','22015','Dorfstr. 45a',NULL,NULL,'Otto',NULL);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_USER'),(2,'ROLE_MODERATOR'),(3,'ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` bigint NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (6,1),(7,1);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (6,'admin@mail.de','$2a$10$bxFtNgKAhYwjtK6.e7F89OnDzADBWE2m6aZnkgUEK.wq.zHNoq1gC','admin');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zuordnung`
--

DROP TABLE IF EXISTS `zuordnung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zuordnung` (
  `id` int NOT NULL AUTO_INCREMENT,
  `kurs_id` bigint NOT NULL,
  `person_id` bigint NOT NULL,
  `teilnehmer` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zuordnung`
--

LOCK TABLES `zuordnung` WRITE;
/*!40000 ALTER TABLE `zuordnung` DISABLE KEYS */;
INSERT INTO `zuordnung` VALUES (27,7,3,_binary ''),(28,7,2,_binary ''),(30,7,5,_binary ''),(32,7,6,_binary ''),(53,6,4,_binary ''),(54,6,2,_binary '\0'),(55,6,6,_binary ''),(60,8,2,_binary ''),(61,8,3,_binary ''),(63,6,1,_binary '\0'),(64,6,7,_binary '\0'),(65,7,1,_binary ''),(72,5,4,_binary '\0'),(73,5,2,_binary ''),(74,8,1,_binary ''),(75,8,5,_binary '\0'),(76,8,7,_binary '\0');
/*!40000 ALTER TABLE `zuordnung` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-12 20:55:03
