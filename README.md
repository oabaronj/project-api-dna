# project-api-dna
Api rest para procesar y descubrir cadenas de DNA mutante y humano

Instrucciones:

1 - Se debe enviar una secuencia de DNA por medio del método POST (/mutant) por medio de un archivo JSON utilizando una herramienta como postman, con la siguiente estructura de ejemplo:

{
    "dna":["ATGCCA","CAGTGC","TTATGT","AGAAGG","CCCTTA","TCACTG"]
}


2 - URL para obtener estadísticas del DNA mutante y humano que se han procesado (Método GET):
/stats/

Instrucciones:
Se debe hacer solicitud por medio del método GET utilizando una herramienta como postman, no es necesario enviar parámetros.


---------------------------------------------------------------------------------------------------------------------------------------------------------------
# Instrucciones Base de datos

1 - La aplicación está configurada para que funcione en una base de datos MySQL, si se desea probar la aplicación de manera local se debe crear una instancia de conexión en la cual el usuario y contraseña son la palabra 'root' y seguido a esto se debe crear un esquema denominado 'apidna'.

2 - Se adjunta DDL de base de datos en MySQL por si es necesario.

CREATE SCHEMA `apidna`;

CREATE DATABASE  IF NOT EXISTS `apidna` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `apidna`;

-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: apidna
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `tbl_dna`
--

DROP TABLE IF EXISTS `tbl_dna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_dna` (
  `num_id_dna` bigint NOT NULL AUTO_INCREMENT,
  `dtm_fecha_creacion` datetime NOT NULL,
  `str_cadena_dna` varchar(250) NOT NULL,
  `str_es_mutante` varchar(1) NOT NULL,
  `str_estado` varchar(1) NOT NULL,
  PRIMARY KEY (`num_id_dna`),
  UNIQUE KEY `UK_DNA_01` (`str_cadena_dna`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
