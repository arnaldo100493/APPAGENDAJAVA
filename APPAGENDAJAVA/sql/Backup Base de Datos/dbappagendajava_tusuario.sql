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
-- Table structure for table `tusuario`
--

DROP TABLE IF EXISTS `tusuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tusuario` (
  `codigoUsuario` char(15) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellidoPaterno` varchar(20) NOT NULL,
  `apellidoMaterno` varchar(20) NOT NULL,
  `correoElectronico` varchar(30) NOT NULL,
  `contrasenia` varchar(700) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `sexo` tinyint(1) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `fechaRegistro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fechaModificacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`codigoUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tusuario`
--

LOCK TABLES `tusuario` WRITE;
/*!40000 ALTER TABLE `tusuario` DISABLE KEYS */;
INSERT INTO `tusuario` VALUES ('USUARIOX0000001','Arnaldo Andres','Barrios','Mena','cyberarnaldo04@hotmail.com','12b03226a6d8be9c6e8cd5e55dc6c7920caaa39df14aab92d5e3ea9340d1c8a4d3d0b8e4314f1f6ef131ba4bf1ceb9186ab87c801af0d5c95b1befb8cedae2b9','1993-04-10',1,'6697390','2016-06-23 22:43:07','2016-06-23 22:43:07'),('USUARIOX0000002','Elivia Maria','Mena','Garcia','llivimaria07@hotmail.com','3627909a29c31381a071ec27f7c9ca97726182aed29a7ddd2e54353322cfb30abb9e3a6df2ac2c20fe23436311d678564d0c8d305930575f60e2d3d048184d79','1953-03-07',0,'3173247028','2016-06-23 22:43:57','2016-06-23 22:43:57'),('USUARIOX0000003','Arnaldo','Barrios','Murillo','arbamu@hotmail.com','3627909a29c31381a071ec27f7c9ca97726182aed29a7ddd2e54353322cfb30abb9e3a6df2ac2c20fe23436311d678564d0c8d305930575f60e2d3d048184d79','1952-09-22',1,'3106671330','2016-06-23 22:44:43','2016-06-23 22:44:43'),('USUARIOX0000004','Brainer Arnold','Barrios','Mena','brabame@hotmail.com','3627909a29c31381a071ec27f7c9ca97726182aed29a7ddd2e54353322cfb30abb9e3a6df2ac2c20fe23436311d678564d0c8d305930575f60e2d3d048184d79','1980-07-03',1,'3057038411','2016-06-23 22:45:43','2016-06-23 22:45:43'),('USUARIOX0000005','Deyris Maireth','Barrios','Mena','deymay22@hotmail.com','3627909a29c31381a071ec27f7c9ca97726182aed29a7ddd2e54353322cfb30abb9e3a6df2ac2c20fe23436311d678564d0c8d305930575f60e2d3d048184d79','1982-02-22',0,'3215473771','2016-06-23 22:46:37','2016-06-23 22:46:37'),('USUARIOX0000006','Betsy Yaneth','Barrios','Mena','bexybarrios@hotmail.com','3627909a29c31381a071ec27f7c9ca97726182aed29a7ddd2e54353322cfb30abb9e3a6df2ac2c20fe23436311d678564d0c8d305930575f60e2d3d048184d79','1983-07-27',0,'3135919472','2016-06-23 22:47:25','2016-06-23 22:47:25'),('USUARIOX0000007','Maira Alejandra','Torres','Mena','maira@hotmail.com','3627909a29c31381a071ec27f7c9ca97726182aed29a7ddd2e54353322cfb30abb9e3a6df2ac2c20fe23436311d678564d0c8d305930575f60e2d3d048184d79','1997-04-26',0,'3216565404','2016-06-23 22:49:12','2016-06-23 22:49:12'),('USUARIOX0000008','Anonimo','Anonimo AP','Anonimo AM','anonimo@gmail.com','3627909a29c31381a071ec27f7c9ca97726182aed29a7ddd2e54353322cfb30abb9e3a6df2ac2c20fe23436311d678564d0c8d305930575f60e2d3d048184d79','2016-07-21',1,'','2016-07-06 17:34:40','2016-07-06 17:34:40');
/*!40000 ALTER TABLE `tusuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger triggerBeforeInsertTUsuario before insert on tusuario for each row
begin
set @ultimoCodigo=(select max(codigoUsuario) from tusuario);
if @ultimoCodigo is null then
   set @ultimoCodigo="USUARIOX0000000";
end if;
set @parteTexto=mid(@ultimoCodigo, 1, 8);
set @parteNumerica=mid(@ultimoCodigo, 9, 7)+1;
set @longitudNumero=(select length(@parteNumerica));
set @codigoNumerico=concat(repeat('0', 7-@longitudNumero), @parteNumerica);
set @codigo=concat(@parteTexto, @codigoNumerico);
set new.codigoUsuario=(select @codigo);
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-06 16:32:08
