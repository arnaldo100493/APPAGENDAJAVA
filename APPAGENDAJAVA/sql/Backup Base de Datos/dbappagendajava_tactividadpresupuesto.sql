CREATE DATABASE  IF NOT EXISTS `dbappagendajava` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `dbappagendajava`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dbappagendajava
-- ------------------------------------------------------
-- Server version	5.6.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tactividadpresupuesto`
--

DROP TABLE IF EXISTS `tactividadpresupuesto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tactividadpresupuesto` (
  `codigoActividadPresupuesto` char(15) NOT NULL,
  `codigoActividad` char(15) NOT NULL,
  `codigoUnidadMedida` char(15) NOT NULL,
  `descripcion` text NOT NULL,
  `precioUnitario` decimal(8,2) NOT NULL,
  `cantidad` float NOT NULL,
  `fechaRegistro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fechaModificacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`codigoActividadPresupuesto`),
  KEY `codigoActividad` (`codigoActividad`),
  KEY `codigoUnidadMedida` (`codigoUnidadMedida`),
  CONSTRAINT `tactividadpresupuesto_ibfk_1` FOREIGN KEY (`codigoActividad`) REFERENCES `tactividad` (`codigoActividad`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tactividadpresupuesto_ibfk_2` FOREIGN KEY (`codigoUnidadMedida`) REFERENCES `tunidadmedida` (`codigoUnidadMedida`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tactividadpresupuesto`
--

LOCK TABLES `tactividadpresupuesto` WRITE;
/*!40000 ALTER TABLE `tactividadpresupuesto` DISABLE KEYS */;
/*!40000 ALTER TABLE `tactividadpresupuesto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-06 16:32:08
