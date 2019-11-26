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
-- Table structure for table `tusuarioamigo`
--

DROP TABLE IF EXISTS `tusuarioamigo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tusuarioamigo` (
  `codigoUsuarioAmigo` char(15) NOT NULL,
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
  PRIMARY KEY (`codigoUsuarioAmigo`),
  KEY `codigoUsuario` (`codigoUsuario`),
  CONSTRAINT `tusuarioamigo_ibfk_1` FOREIGN KEY (`codigoUsuario`) REFERENCES `tusuario` (`codigoUsuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tusuarioamigo`
--

LOCK TABLES `tusuarioamigo` WRITE;
/*!40000 ALTER TABLE `tusuarioamigo` DISABLE KEYS */;
/*!40000 ALTER TABLE `tusuarioamigo` ENABLE KEYS */;
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger triggerBeforeInsertTUsuarioAmigo before insert on tusuarioamigo for each row
begin
set @ultimoCodigo=(select max(codigoUsuarioAmigo) from TUsuarioAmigo);
if @ultimoCodigo is null then
	set @ultimoCodigo="USUARIOA0000000";
end if;
set @parteTexto=mid(@ultimoCodigo, 1, 8);
set @parteNumerica=mid(@ultimoCodigo, 9, 7)+1;
set @longitudNumero=(select length(@parteNumerica));
set @codigoNumerico=concat(repeat('0', 7-@longitudNumero), @parteNumerica);
set @codigo=concat(@parteTexto, @codigoNumerico);
set new.codigoUsuarioAmigo=(select @codigo);
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
