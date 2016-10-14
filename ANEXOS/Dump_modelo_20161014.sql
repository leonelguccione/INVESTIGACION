-- MySQL dump 10.13  Distrib 5.5.39, for Win32 (x86)
--
-- Host: localhost    Database: modelo_del_estudiante
-- ------------------------------------------------------
-- Server version	5.5.39

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
-- Current Database: `modelo_del_estudiante`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `modelo_del_estudiante` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `modelo_del_estudiante`;

--
-- Table structure for table `alumnos`
--

DROP TABLE IF EXISTS `alumnos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alumnos` (
  `dni` bigint(20) NOT NULL,
  `legajo` bigint(20) NOT NULL,
  `apellido` varchar(40) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumnos`
--

LOCK TABLES `alumnos` WRITE;
/*!40000 ALTER TABLE `alumnos` DISABLE KEYS */;
INSERT INTO `alumnos` VALUES (2342342,432,'Basualdo','Jackeline'),(12342234,1,'Lopez','Juan'),(12631232,726312,'Bores','Tato'),(16254212,42342,'Puchetto','Marisa'),(20123456,1234,'Lanfranconi','Carolina'),(23198293,2,'Alvarez','Pedro'),(28732123,81238123,'Echenique','Elba'),(34987671,42342,'Marinoni','Roberto'),(45192891,35,'Lazzurri','Guillermo'),(82748278,4215125,'Fox','Megan'),(91827361,42342,'Travellini','Mariana'),(99999999,123123,'Abalos','Pedro'),(293472923,23452343,'Johansen','Scarlet'),(345353435,1231231,'Gomez','Luis');
/*!40000 ALTER TABLE `alumnos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asignaturas`
--

DROP TABLE IF EXISTS `asignaturas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asignaturas` (
  `codigo` varchar(10) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `arbol_dominio` blob,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asignaturas`
--

LOCK TABLES `asignaturas` WRITE;
/*!40000 ALTER TABLE `asignaturas` DISABLE KEYS */;
INSERT INTO `asignaturas` VALUES ('Fis001','Fisica 1','¨Ì\0sr\0modelo.Arbol_PerturbacionY,⁄Œø°	Ç\0L\0descripciont\0Ljava/lang/String;L\0nombreq\0~\0L\0raizt\0\ZLmodelo/Nodo_Perturbacion;L\0	treeModelt\0#Ljavax/swing/tree/DefaultTreeModel;xpt\0< sin descripci√≥n >t\0\0sr\0modelo.Nodo_PerturbacionÚ‚RÖåùø\0\0xr\0\'javax.swing.tree.DefaultMutableTreeNodeƒXø¸Ú®q‡\0Z\0allowsChildrenL\0childrent\0Ljava/util/Vector;L\0parentt\0\"Ljavax/swing/tree/MutableTreeNode;xpsr\0java.util.VectorŸó}[Ä;Ø\0I\0capacityIncrementI\0elementCount[\0elementDatat\0[Ljava/lang/Object;xp\0\0\0\0\0\0\0ur\0[Ljava.lang.Object;êŒXüs)l\0\0xp\0\0\0\nsq\0~\0pq\0~\0uq\0~\0\0\0\0t\0\nuserObjectsr\0modelo.DatoBeanÆyQ†ï±_\0L\0etiquetaBeant\0Lmodelo/EtiquetaBean;L\0idDatoq\0~\0L\0propertySupportt\0\"Ljava/beans/PropertyChangeSupport;xpsr\0modelo.EtiquetaBean∞ºù,bÿªÓ\0D\0	aprendidoD\0conocidoD\0desconocidoD\0parcialmente_conocidoxp\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0t\0MRUpxsq\0~\0pq\0~\0uq\0~\0\0\0\0q\0~\0sq\0~\0sq\0~\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0t\0MUVpxppppppppxpuq\0~\0\0\0\0q\0~\0sq\0~\0sq\0~\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0t\0fisicapxsr\0!javax.swing.tree.DefaultTreeModelßæë&\Z≈ÂŸ\0Z\0asksAllowsChildrenL\0listenerListt\0%Ljavax/swing/event/EventListenerList;L\0roott\0Ljavax/swing/tree/TreeNode;xp\0sr\0#javax.swing.event.EventListenerList±6∆}ÑÍ÷D\0\0xppxq\0~\0sq\0~\0\0\0\0\0\0\0\0uq\0~\0\0\0\0\nt\0rootq\0~\0ppppppppxx'),('g002','Geografia','¨Ì\0sr\0modelo.Arbol_PerturbacionY,⁄Œø°	Ç\0L\0descripciont\0Ljava/lang/String;L\0nombreq\0~\0L\0raizt\0\ZLmodelo/Nodo_Perturbacion;L\0	treeModelt\0#Ljavax/swing/tree/DefaultTreeModel;xpt\0< sin descripci√≥n >t\0\0sr\0modelo.Nodo_PerturbacionÚ‚RÖåùø\0\0xr\0\'javax.swing.tree.DefaultMutableTreeNodeƒXø¸Ú®q‡\0Z\0allowsChildrenL\0childrent\0Ljava/util/Vector;L\0parentt\0\"Ljavax/swing/tree/MutableTreeNode;xpsr\0java.util.VectorŸó}[Ä;Ø\0I\0capacityIncrementI\0elementCount[\0elementDatat\0[Ljava/lang/Object;xp\0\0\0\0\0\0\0ur\0[Ljava.lang.Object;êŒXüs)l\0\0xp\0\0\0\nsq\0~\0pq\0~\0uq\0~\0\0\0\0t\0\nuserObjectsr\0modelo.DatoBeanÆyQ†ï±_\0L\0etiquetaBeant\0Lmodelo/EtiquetaBean;L\0idDatoq\0~\0L\0propertySupportt\0\"Ljava/beans/PropertyChangeSupport;xpsr\0modelo.EtiquetaBean∞ºù,bÿªÓ\0D\0	aprendidoD\0conocidoD\0desconocidoD\0parcialmente_conocidoxp\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0t\0Limitespxsq\0~\0pq\0~\0uq\0~\0\0\0\0q\0~\0sq\0~\0sq\0~\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0t\0Climapxppppppppxpuq\0~\0\0\0\0q\0~\0sq\0~\0sq\0~\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0t\0	Geografiapxsr\0!javax.swing.tree.DefaultTreeModelßæë&\Z≈ÂŸ\0Z\0asksAllowsChildrenL\0listenerListt\0%Ljavax/swing/event/EventListenerList;L\0roott\0Ljavax/swing/tree/TreeNode;xp\0sr\0#javax.swing.event.EventListenerList±6∆}ÑÍ÷D\0\0xppxq\0~\0sq\0~\0\0\0\0\0\0\0\0uq\0~\0\0\0\0\nt\0rootq\0~\0ppppppppxx'),('h2','Historia 3','¨Ì\0sr\0modelo.Arbol_PerturbacionY,⁄Œø°	Ç\0L\0descripciont\0Ljava/lang/String;L\0nombreq\0~\0L\0raizt\0\ZLmodelo/Nodo_Perturbacion;L\0	treeModelt\0#Ljavax/swing/tree/DefaultTreeModel;xpt\0< sin descripci√≥n >t\0\0sr\0modelo.Nodo_PerturbacionÚ‚RÖåùø\0\0xr\0\'javax.swing.tree.DefaultMutableTreeNodeƒXø¸Ú®q‡\0Z\0allowsChildrenL\0childrent\0Ljava/util/Vector;L\0parentt\0\"Ljavax/swing/tree/MutableTreeNode;xpsr\0java.util.VectorŸó}[Ä;Ø\0I\0capacityIncrementI\0elementCount[\0elementDatat\0[Ljava/lang/Object;xp\0\0\0\0\0\0\0ur\0[Ljava.lang.Object;êŒXüs)l\0\0xp\0\0\0\nsq\0~\0pq\0~\0uq\0~\0\0\0\0t\0\nuserObjectsr\0modelo.DatoBeanÆyQ†ï±_\0L\0etiquetaBeant\0Lmodelo/EtiquetaBean;L\0idDatoq\0~\0L\0propertySupportt\0\"Ljava/beans/PropertyChangeSupport;xpsr\0modelo.EtiquetaBean∞ºù,bÿªÓ\0D\0	aprendidoD\0conocidoD\0desconocidoD\0parcialmente_conocidoxp\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0t\0Sumeriospxsq\0~\0pq\0~\0uq\0~\0\0\0\0q\0~\0sq\0~\0sq\0~\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0t\0Acadiospxsq\0~\0sq\0~\0\0\0\0\0\0\0\0uq\0~\0\0\0\0\nsq\0~\0pq\0~\0 uq\0~\0\0\0\0q\0~\0sq\0~\0sq\0~\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0t\0Construccion de piramidespxsq\0~\0pq\0~\0 uq\0~\0\0\0\0q\0~\0sq\0~\0sq\0~\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0t\0Momificacion de suegraspxppppppppxq\0~\0uq\0~\0\0\0\0q\0~\0sq\0~\0sq\0~\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0t\0Egipciospxpppppppxpuq\0~\0\0\0\0q\0~\0sq\0~\0sq\0~\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0t\0\nHistoria 3pxsr\0!javax.swing.tree.DefaultTreeModelßæë&\Z≈ÂŸ\0Z\0asksAllowsChildrenL\0listenerListt\0%Ljavax/swing/event/EventListenerList;L\0roott\0Ljavax/swing/tree/TreeNode;xp\0sr\0#javax.swing.event.EventListenerList±6∆}ÑÍ÷D\0\0xppxq\0~\0sq\0~\0\0\0\0\0\0\0\0uq\0~\0\0\0\0\nt\0rootq\0~\0ppppppppxx'),('m1','Matematica 1','¨Ì\0sr\0modelo.Arbol_PerturbacionY,⁄Œø°	Ç\0L\0descripciont\0Ljava/lang/String;L\0nombreq\0~\0L\0raizt\0\ZLmodelo/Nodo_Perturbacion;L\0	treeModelt\0#Ljavax/swing/tree/DefaultTreeModel;xpt\0< sin descripci√≥n >t\0\0sr\0modelo.Nodo_PerturbacionÚ‚RÖåùø\0\0xr\0\'javax.swing.tree.DefaultMutableTreeNodeƒXø¸Ú®q‡\0Z\0allowsChildrenL\0childrent\0Ljava/util/Vector;L\0parentt\0\"Ljavax/swing/tree/MutableTreeNode;xpsr\0java.util.VectorŸó}[Ä;Ø\0I\0capacityIncrementI\0elementCount[\0elementDatat\0[Ljava/lang/Object;xp\0\0\0\0\0\0\0ur\0[Ljava.lang.Object;êŒXüs)l\0\0xp\0\0\0\nsq\0~\0pq\0~\0uq\0~\0\0\0\0t\0\nuserObjectsr\0modelo.DatoBeanÆyQ†ï±_\0L\0etiquetaBeant\0Lmodelo/EtiquetaBean;L\0idDatoq\0~\0L\0propertySupportt\0\"Ljava/beans/PropertyChangeSupport;xpsr\0modelo.EtiquetaBean∞ºù,bÿªÓ\0D\0	aprendidoD\0conocidoD\0desconocidoD\0parcialmente_conocidoxp\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0t\0\rconjuntos numpxsq\0~\0sq\0~\0\0\0\0\0\0\0\0uq\0~\0\0\0\0\nsq\0~\0pq\0~\0uq\0~\0\0\0\0q\0~\0sq\0~\0sq\0~\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0t\0linealpxsq\0~\0pq\0~\0uq\0~\0\0\0\0q\0~\0sq\0~\0sq\0~\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0t\0\ncuadraticapxppppppppxq\0~\0uq\0~\0\0\0\0q\0~\0sq\0~\0sq\0~\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0t\0	funcionespxppppppppxpuq\0~\0\0\0\0q\0~\0sq\0~\0sq\0~\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0t\0\nmatematicapxsr\0!javax.swing.tree.DefaultTreeModelßæë&\Z≈ÂŸ\0Z\0asksAllowsChildrenL\0listenerListt\0%Ljavax/swing/event/EventListenerList;L\0roott\0Ljavax/swing/tree/TreeNode;xp\0sr\0#javax.swing.event.EventListenerList±6∆}ÑÍ÷D\0\0xppxq\0~\0sq\0~\0\0\0\0\0\0\0\0uq\0~\0\0\0\0\nt\0rootq\0~\0ppppppppxx');
/*!40000 ALTER TABLE `asignaturas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aux_cursada_alumno`
--

DROP TABLE IF EXISTS `aux_cursada_alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aux_cursada_alumno` (
  `dni_alumno` bigint(20) NOT NULL,
  `id_cursada` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aux_cursada_alumno`
--

LOCK TABLES `aux_cursada_alumno` WRITE;
/*!40000 ALTER TABLE `aux_cursada_alumno` DISABLE KEYS */;
INSERT INTO `aux_cursada_alumno` VALUES (2342342,1),(12631232,1),(28732123,1),(82748278,1),(345353435,1),(20123456,1),(45192891,1),(99999999,3),(23198293,3),(2342342,3),(12631232,3),(28732123,3),(82748278,3),(345353435,3),(293472923,4),(82748278,4),(2342342,5),(12342234,5),(99999999,5),(45192891,5),(12631232,6),(34987671,6),(2342342,6),(16254212,6),(28732123,7),(345353435,7),(20123456,7),(23198293,7),(82748278,7),(91827361,7),(16254212,7),(45192891,8),(28732123,8),(345353435,8),(20123456,8),(23198293,8),(82748278,8),(91827361,8),(16254212,8);
/*!40000 ALTER TABLE `aux_cursada_alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cursadas`
--

DROP TABLE IF EXISTS `cursadas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cursadas` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `codigo_asignatura` varchar(10) DEFAULT NULL,
  `anio` int(5) NOT NULL,
  `cuatrimestre` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursadas`
--

LOCK TABLES `cursadas` WRITE;
/*!40000 ALTER TABLE `cursadas` DISABLE KEYS */;
INSERT INTO `cursadas` VALUES (1,'m1',2016,1),(3,'h2',2015,1),(4,'m1',2016,2),(5,'m1',2015,1),(6,'m1',2015,2),(7,'Fis001',2013,1),(8,'g002',2014,2);
/*!40000 ALTER TABLE `cursadas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examenes`
--

DROP TABLE IF EXISTS `examenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `examenes` (
  `dni_alumno` bigint(20) NOT NULL,
  `id_instancia_ev` int(4) NOT NULL,
  `arbol_particular` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examenes`
--

LOCK TABLES `examenes` WRITE;
/*!40000 ALTER TABLE `examenes` DISABLE KEYS */;
/*!40000 ALTER TABLE `examenes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instancias_evaluaciones`
--

DROP TABLE IF EXISTS `instancias_evaluaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instancias_evaluaciones` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `id_parcial` int(4) NOT NULL,
  `descripcion` varchar(40) NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instancias_evaluaciones`
--

LOCK TABLES `instancias_evaluaciones` WRITE;
/*!40000 ALTER TABLE `instancias_evaluaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `instancias_evaluaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parciales`
--

DROP TABLE IF EXISTS `parciales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parciales` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `id_cursada` int(4) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `arbol_podado` blob NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parciales`
--

LOCK TABLES `parciales` WRITE;
/*!40000 ALTER TABLE `parciales` DISABLE KEYS */;
/*!40000 ALTER TABLE `parciales` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-14 11:10:48
