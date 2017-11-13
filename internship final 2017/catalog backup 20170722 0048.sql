-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.7.18


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema catalog
--

CREATE DATABASE IF NOT EXISTS catalog;
USE catalog;

--
-- Definition of table `cards`
--

DROP TABLE IF EXISTS `cards`;
CREATE TABLE `cards` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `season` varchar(45) NOT NULL,
  `price` float(5,2) NOT NULL,
  `description` varchar(200) NOT NULL,
  `thumbnail_url` varchar(50) NOT NULL,
  `image_url` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cards`
--

/*!40000 ALTER TABLE `cards` DISABLE KEYS */;
INSERT INTO `cards` (`id`,`title`,`season`,`price`,`description`,`thumbnail_url`,`image_url`) VALUES 
 (1,'Happy Winter','Winter',10.00,'Hope you ski on lotta fun this holiday season...','/resources/thumbnails/109152.jpg','/resources/images/109152.gif'),
 (2,'Happy Winter','Winter',10.25,'Just the thought of being with you, makes my winter a little warmer!','/resources/thumbnails/109184.jpg','/resources/images/109184.gif'),
 (3,'Happy Winter','Winter',9.80,'I don\'t want much for Christmas. <br/> I just want the person reading this to be healthy, happy and loved!','/resources/thumbnails/109122.jpg','/resources/images/109122.gif'),
 (4,'Happy Spring','Spring',8.00,'Thanks for your warm greetings that brightened up my day!','/resources/thumbnails/107223.jpg','/resources/images/107223.gif'),
 (5,'Happy Spring','Spring',11.00,'Smile and be happy and let the happiness spread everywhere you go. <br/> Life is beautiful!','/resources/thumbnails/107550.jpg','/resources/images/107550.gif'),
 (6,'Happy Fall','Autumn',12.00,'Hope it\'s a Fall of sweet surprises for you...','/resources/thumbnails/104670.jpg','/resources/images/104670.gif'),
 (7,'Happy Fall','Autumn',9.20,'May this Autumn be a harvest of happy times for you!','/resources/thumbnails/104220.jpg','/resources/images/104220.gif'),
 (8,'Happy Fall','Autumn',12.00,'Let us not become weary in doing good, for at the proper time we wil reap a harvest if we do not give up','/resources/thumbnails/104196.jpg','/resources/images/104196.gif'),
 (9,'Happy Spring','Spring',10.10,'Wishing that the Spring blooms your each day with Happiness!','/resources/thumbnails/107472.jpg','/resources/images/107472.gif'),
 (10,'Happy Summer','Summer',6.50,'A friend like you makes my summer cooler','/resources/thumbnails/110962.jpg','/resources/images/110962.gif'),
 (11,'Happy Summer','Summer',7.70,'Do join us for some fun in the sun and have a great summertime','/resources/thumbnails/111091.jpg','/resources/images/111091.gif'),
 (12,'Happy Summer','Summer',9.20,'Thanks to you, this Summer was not a bummer','/resources/thumbnails/108429.jpg','/resources/images/108429.gif'),
 (13,'Happy Winter','Winter',10.00,'Hope you catch on some cool fun this holiday season!','/resources/thumbnails/109153.jpg','/resources/images/109153.gif'),
 (14,'Happy Summer','Summer',8.25,'Find your happy place','/resources/thumbnails/110996.jpg','/resources/images/110996.gif');
/*!40000 ALTER TABLE `cards` ENABLE KEYS */;


--
-- Definition of table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
CREATE TABLE `order_items` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int(10) unsigned NOT NULL,
  `card_id` int(10) unsigned NOT NULL,
  `qty` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK75109F8F573AF0E3` (`order_id`),
  KEY `FK75109F8F6BC20CB1` (`card_id`),
  CONSTRAINT `FK75109F8F573AF0E3` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FK75109F8F6BC20CB1` FOREIGN KEY (`card_id`) REFERENCES `cards` (`id`),
  CONSTRAINT `FK_order_items_1` FOREIGN KEY (`id`) REFERENCES `cards` (`id`),
  CONSTRAINT `FK_order_items_2` FOREIGN KEY (`id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order_items`
--

/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;


--
-- Definition of table `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `total_sum` float(5,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders`
--

/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;


--
-- Definition of table `stocks`
--

DROP TABLE IF EXISTS `stocks`;
CREATE TABLE `stocks` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `card_id` int(10) unsigned NOT NULL,
  `qty` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_stock` FOREIGN KEY (`id`) REFERENCES `cards` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stocks`
--

/*!40000 ALTER TABLE `stocks` DISABLE KEYS */;
INSERT INTO `stocks` (`id`,`card_id`,`qty`) VALUES 
 (1,1,5),
 (2,2,4),
 (3,3,7),
 (4,4,4),
 (5,5,5),
 (6,6,7),
 (7,7,3),
 (8,8,0),
 (9,9,1),
 (10,10,6),
 (11,11,9),
 (12,12,8),
 (13,13,0),
 (14,14,4);
/*!40000 ALTER TABLE `stocks` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
