-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 11, 2019 at 06:08 AM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `carsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `car`
--

CREATE TABLE `car` (
  `id` int(11) NOT NULL,
  `mainId` int(11) DEFAULT NULL,
  `shipperId` int(11) DEFAULT NULL,
  `vendorId` int(11) DEFAULT NULL,
  `customerId` int(11) DEFAULT NULL,
  `uuid` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `year` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `container` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `seal` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `inlandStatus` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `cargoRecieved` datetime DEFAULT NULL,
  `titleRecieved` datetime DEFAULT NULL,
  `dvl` datetime DEFAULT NULL,
  `stRecieved` datetime DEFAULT NULL,
  `origin` int(11) DEFAULT NULL,
  `destination` int(11) DEFAULT NULL,
  `etd` datetime DEFAULT NULL,
  `eta` datetime DEFAULT NULL,
  `loadrequest` int(11) DEFAULT NULL,
  `releaseOption` int(11) DEFAULT NULL,
  `consigneeId` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `landcost` int(11) DEFAULT NULL,
  `seacost` int(11) DEFAULT NULL,
  `fees` int(11) DEFAULT NULL,
  `commision` int(11) DEFAULT NULL,
  `lastUpdate` datetime DEFAULT NULL,
  `storageStartDate` datetime DEFAULT NULL,
  `storageEndDate` datetime DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `make` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `model` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `bodyStyle` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `engineType` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `engineLiters` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `color` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `assemlyCountry` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `photoExist` int(11) DEFAULT NULL,
  `docExist` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `car`
--

INSERT INTO `car` (`id`, `mainId`, `shipperId`, `vendorId`, `customerId`, `uuid`, `description`, `year`, `container`, `seal`, `inlandStatus`, `cargoRecieved`, `titleRecieved`, `dvl`, `stRecieved`, `origin`, `destination`, `etd`, `eta`, `loadrequest`, `releaseOption`, `consigneeId`, `state`, `landcost`, `seacost`, `fees`, `commision`, `lastUpdate`, `storageStartDate`, `storageEndDate`, `note`, `make`, `model`, `bodyStyle`, `engineType`, `engineLiters`, `color`, `assemlyCountry`, `photoExist`, `docExist`, `type`) VALUES
(11, 1, 10, NULL, NULL, 'klasdkmlasdlmkasdlm', 'Zewail city', '2020', 'kdsds', 'lksdklsdk', 'giza', '2019-06-26 18:22:41', '2019-06-18 20:34:40', '2019-06-21 18:25:39', '2019-06-05 20:37:26', 371, 1412, '2019-06-14 20:37:19', '2019-06-18 20:37:24', 1, 1, NULL, 0, 2323, 788, 2322, 998, '2019-06-04 18:14:38', '2019-05-29 18:14:32', NULL, 'please read it', 'BMW', 'X5', 'klslklds', 'ksdlksdlk', 'sdsd', 'lkmlmk', 'مصر', 1, 1, NULL),
(12, 1, 15, 1, NULL, 'lkkllksdsd', 'Dakrory', '2017', 'osdfksdf', 'lskldflksdf', 'Giza', '2019-06-15 15:56:52', '2019-06-20 15:56:58', '2019-06-22 15:56:56', '2019-06-26 15:57:05', 1, 4265, '2019-06-17 15:43:21', '2019-09-17 15:57:02', 1, 1, 2, 2, 55, NULL, 25, 122, '2019-06-06 13:39:46', '2019-06-01 16:29:16', NULL, '', 'BMW', '222', '4 doors', 'klmlk', '', '', 'مصر', 1, 0, 0),
(13, 1, 15, 3, NULL, 'sdklsdklsdkldssd232323sd', 'mohamed', '2020', 'osdfksdf', 'lskldflksdf', 'klmkl', '2019-06-01 13:59:27', '2019-06-01 13:59:16', '2019-06-01 13:59:22', '2019-06-01 13:59:30', 39, 1412, '2019-07-01 15:54:52', '2019-06-01 13:59:25', 0, 1, 2, 6, 123, 13, 2322, 122, '2019-06-06 13:41:25', '2019-06-01 13:59:55', '2019-06-08 18:15:27', 'please read it', 'BMW', 'X5', '4 doors', '4cyl', '4liter', 'black', 'مصر', 1, 1, 0),
(14, 1, 10, NULL, NULL, 'lksdklsdlksdsd22k23k23', '', '', '', '', '', NULL, NULL, '2019-06-15 11:25:08', NULL, 371, 1408, NULL, NULL, 0, 0, NULL, 6, NULL, NULL, NULL, NULL, '2019-06-06 14:26:16', NULL, NULL, '', '', '', '', '', '', '', '', 0, 0, 1),
(15, 1, 10, 1, NULL, 'klasdkmlasdlmkasd', '', '2017', '', '', '', NULL, NULL, NULL, NULL, 381, 1531, NULL, NULL, 0, 0, NULL, 4, NULL, NULL, NULL, NULL, '2019-06-06 14:16:12', NULL, NULL, '', 'BMW', '222', 'sdfsdf', 'ksdlksdlk', 'lkmmkl', 'black', 'مصر', 0, 0, 1),
(16, 1, 15, 2, NULL, 'lkkllksdsd23232', NULL, '2020', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 381, 1531, NULL, NULL, 0, 0, 1, 0, NULL, NULL, NULL, NULL, '2019-06-06 13:20:40', NULL, NULL, NULL, 'Toyota xxxx', 'crola', 'sdfsdf', 'ksdlksdlk', '4liter', 'black', 'مصر', 0, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `carimage`
--

CREATE TABLE `carimage` (
  `id` int(11) NOT NULL,
  `url` varchar(45) DEFAULT NULL,
  `carId` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `carimage`
--

INSERT INTO `carimage` (`id`, `url`, `carId`, `type`) VALUES
(1, 'img8272088495755286320.png', 11, 0),
(2, 'img6032234184490386176.png', 11, 0),
(3, 'img8272088495755286320.png', 11, 1),
(4, 'img6032234184490386176.png', 11, 1),
(5, 'img6084960490893956519.png', 13, 0),
(6, 'img5541713043554210700.png', 13, 1),
(7, 'img6305983527893027542.png', 12, 0),
(8, 'img190549176288432223.png', 13, 0),
(9, 'img5327908244231425875.png', 13, 1);

-- --------------------------------------------------------

--
-- Table structure for table `consignee`
--

CREATE TABLE `consignee` (
  `id` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `other` text,
  `notes` text,
  `allowAccess` int(11) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `consignee`
--

INSERT INTO `consignee` (`id`, `userId`, `other`, `notes`, `allowAccess`, `parentId`) VALUES
(1, 18, '', 'sdklsdklsdklsdk', 1, 15),
(2, 19, '', 'm,sdowekl', 0, 15),
(3, 20, '', 'klsdlkmlmk2323lmk23', 0, 15);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `idType` int(11) DEFAULT NULL,
  `other` mediumtext,
  `notes` mediumtext,
  `allowAccess` int(11) NOT NULL DEFAULT '0',
  `parentId` int(11) DEFAULT NULL,
  `salesRep` mediumtext,
  `idNumber` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `userId`, `idType`, `other`, `notes`, `allowAccess`, `parentId`, `salesRep`, `idNumber`) VALUES
(1, 14, 3, '', '', 0, 1, 'okokok', '9012912n'),
(2, 15, 1, '', '', 0, 2, 'okopok', '');

-- --------------------------------------------------------

--
-- Table structure for table `shipper`
--

CREATE TABLE `shipper` (
  `id` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `idType` int(11) DEFAULT NULL,
  `other` mediumtext,
  `notes` mediumtext,
  `allowAccess` int(11) NOT NULL DEFAULT '0',
  `parentId` int(11) DEFAULT NULL,
  `salesRep` varchar(45) DEFAULT NULL,
  `idNumber` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `shipper`
--

INSERT INTO `shipper` (`id`, `userId`, `idType`, `other`, `notes`, `allowAccess`, `parentId`, `salesRep`, `idNumber`) VALUES
(10, 6, 1, '', '', 1, 1, '', ''),
(11, 7, 2, '', '', 0, 1, '', '15666546'),
(12, 8, 1, '', '', 1, 1, '', ''),
(13, 9, 3, '', '', 0, 1, '', '123456'),
(14, 10, 1, '', '', 0, 1, '', NULL),
(15, 11, 1, 'lkslkdlk', 'sdfdwoeidmomew', 0, 1, 'asdlkasdlk', '3132135161'),
(16, 16, 1, '', '', 0, 1, 'ok', 'ok');

-- --------------------------------------------------------

--
-- Table structure for table `transportfee`
--

CREATE TABLE `transportfee` (
  `id` int(11) NOT NULL,
  `location` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `njPortCost` varchar(45) DEFAULT NULL,
  `gaPortCost` varchar(45) DEFAULT NULL,
  `txPortCost` varchar(45) DEFAULT NULL,
  `caPortCost` varchar(45) DEFAULT NULL,
  `lowCost` varchar(45) DEFAULT NULL,
  `highCost` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transportfee`
--

INSERT INTO `transportfee` (`id`, `location`, `city`, `state`, `njPortCost`, `gaPortCost`, `txPortCost`, `caPortCost`, `lowCost`, `highCost`) VALUES
(1, 'BIRMINGHAM', 'HUETOWN', 'AL', '0', '400', '0', '0', '875', '1100'),
(2, 'HUNTSVILLE', 'HUNTSVILLE', 'AL', '0', '425', '0', '0', '875', '1100'),
(3, 'MOBILE', 'EIGHT MILE', 'AL', '0', '400', '0', '0', '875', '1100'),
(4, 'MONTGOMERY', 'MONTGOMERY', 'AL', '0', '400', '0', '0', '875', '1100'),
(5, 'BIRMINGHAM', 'BESSEMER', 'AL', '0', '400', '0', '0', '875', '1100'),
(6, 'DOTHAN', 'HEADLAND', 'AL', '0', '400', '0', '0', '875', '1100'),
(7, 'HUNTSVILLE', 'ATHENS', 'AL', '0', '400', '0', '0', '875', '1100'),
(8, 'BIRMINGHAM', 'MOODY', 'AL', '0', '425', '0', '0', '875', '1100'),
(9, 'TANNER', 'TANNER', 'AL', '0', '450', '0', '0', '875', '1100'),
(10, 'Fayetteville', 'PRAIRIE GROVE', 'AR', '0', '0', '425', '0', '975', '1200'),
(11, 'Little Rock', 'CONWAY', 'AR', '0', '0', '425', '0', '975', '1200'),
(12, 'Fayetteville', 'Lincoln', 'AR', '0', '0', '425', '0', '975', '1200'),
(13, 'Little Rock', 'Scott', 'AR', '0', '0', '425', '0', '975', '1200'),
(14, 'PHOENIX', 'PHOENIX', 'AZ', '0', '0', '0', '300', '1050', '1400'),
(15, 'TUCSON', 'TUCSON', 'AZ', '0', '0', '0', '425', '1050', '1400'),
(16, 'PHOENIX', 'PHOENIX', 'AZ', '0', '0', '0', '300', '1050', '1400'),
(17, 'TUCSON', 'TUCSON', 'AZ', '0', '0', '0', '425', '1050', '1400'),
(18, 'ARIZONA', 'PHOENIX', 'AZ', '0', '0', '0', '300', '1050', '1400'),
(19, 'PNOENIX', 'TOLLESON', 'AZ', '0', '0', '0', '400', '1050', '1400'),
(20, 'TUCSON', 'TUCSON', 'AZ', '0', '0', '0', '475', '1050', '1400'),
(21, 'PHOENIX', 'CHANDLER', 'AZ', '0', '0', '0', '350', '1050', '1400'),
(22, 'BAKERSFIELD', 'BAKERSFIELD', 'CA', '0', '0', '0', '275', '1050', '1400'),
(23, 'FRESNO', 'FRESNO', 'CA', '0', '0', '0', '350', '1050', '1400'),
(24, 'HAYWARD', 'HAYWARD', 'CA', '0', '0', '0', '400', '1050', '1400'),
(25, 'LOS ANGELES', 'LOS ANGELES', 'CA', '0', '0', '0', '150', '1050', '1400'),
(26, 'MARTINEZ', 'MARTINEZ', 'CA', '0', '0', '0', '375', '1050', '1400'),
(27, 'RANCHO CUCAMONGA', 'RANCHO CUCAMONGA', 'CA', '0', '0', '0', '225', '1050', '1400'),
(28, 'SACRAMENTO', 'SACRAMENTO', 'CA', '0', '0', '0', '375', '1050', '1400'),
(29, 'SAN BERNARDINO', 'COLTON', 'CA', '0', '0', '0', '200', '1050', '1400'),
(30, 'SAN DIEGO', 'SAN DIEGO', 'CA', '0', '0', '0', '225', '1050', '1400'),
(31, 'SAN JOSE', 'SAN MARTIN', 'CA', '0', '0', '0', '350', '1050', '1400'),
(32, 'SOUTH SACRAMENTO', 'SACRAMENTO', 'CA', '0', '0', '0', '375', '1050', '1400'),
(33, 'VALLEJO', 'VALLEJO', 'CA', '0', '0', '0', '375', '1050', '1400'),
(34, 'VAN NUYS', 'VAN NUYS', 'CA', '0', '0', '0', '175', '1050', '1400'),
(35, 'ANAHEIM', 'ANAHEIM', 'CA', '0', '0', '0', '200', '1050', '1400'),
(36, 'COLTON', 'COLTON', 'CA', '0', '0', '0', '200', '1050', '1400'),
(37, 'EAST BAY', 'BAY POINT', 'CA', '0', '0', '0', '325', '1050', '1400'),
(38, 'FRESNO', 'FRESNO', 'CA', '0', '0', '0', '375', '1050', '1400'),
(39, 'FONTANA', 'FONTANA', 'CA', '0', '0', '0', '225', '1050', '1400'),
(40, 'FREMONT', 'FREMONT', 'CA', '0', '0', '0', '375', '1050', '1400'),
(41, 'HIGH DESERT', 'HESPERIA', 'CA', '0', '0', '0', '375', '1050', '1400'),
(42, 'LOS ANGELES', 'GARDENA', 'CA', '0', '0', '0', '150', '1050', '1400'),
(43, 'NORTH HOLLYWOOD', 'NORTH HOLLYWOOD', 'CA', '0', '0', '0', '150', '1050', '1400'),
(44, 'SACRAMENTO', 'RANCHO CORDOVA', 'CA', '0', '0', '0', '375', '1050', '1400'),
(45, 'SAN DIEGO', 'SAN DIEGO', 'CA', '0', '0', '0', '225', '1050', '1400'),
(46, 'CALIFORNIA', 'ANAHEIM', 'CA', '0', '0', '0', '175', '1050', '1400'),
(47, 'CENTRAL CALIFORNIA', 'FRESNO', 'CA', '0', '0', '0', '375', '1050', '1400'),
(48, 'RIVERSIDE', 'RIVERSIDE', 'CA', '0', '0', '0', '375', '1050', '1400'),
(49, 'SAN DIEGO', 'OCEANSIDE', 'CA', '0', '0', '0', '250', '1050', '1400'),
(50, 'SAN FRANCISCO BAY', 'HAYWARD', 'CA', '0', '0', '0', '375', '1050', '1400'),
(51, 'SOUTHERN CALIFORNIA', 'FONTANA', 'CA', '0', '0', '0', '225', '1050', '1400'),
(52, 'SAN DIEGO', 'SAN DIEGO', 'CA', '0', '0', '0', '225', '1050', '1400'),
(53, 'SACRAMENTO', 'SACRAMENTO', 'CA', '0', '0', '0', '375', '1050', '1400'),
(54, 'LOS ANGELES', 'MIRA LOMA', 'CA', '0', '0', '0', '375', '1050', '1400'),
(55, 'SUN VALLEY', 'SUN VALLEY', 'CA', '0', '0', '0', '200', '1050', '1400'),
(56, 'GOLDEN GATE', 'TRACY', 'CA', '0', '0', '0', '550', '1050', '1400'),
(57, 'DENVER', 'BRIGHTON', 'CO', '0', '0', '525', '550', '1050', '1400'),
(58, 'DENVER', 'DENVER', 'CO', '0', '0', '525', '550', '1050', '1400'),
(59, 'WESTERN COLORADO', 'DELTA', 'CO', '0', '0', '525', '525', '1050', '1400'),
(60, 'COLORADO', 'COMMERCE CITY', 'CO', '0', '0', '525', '550', '1050', '1400'),
(61, 'DENVER', 'AURORA', 'CO', '0', '0', '525', '550', '1050', '1400'),
(62, 'COLORADO SPRINGS', 'FOUNTAIN', 'CO', '0', '0', '525', '550', '1050', '1400'),
(63, 'Hartford', 'East Windsor', 'CT', '250', '0', '0', '0', '875', '1100'),
(64, 'Hartford-South', 'Middletown', 'CT', '250', '0', '0', '0', '875', '1100'),
(65, 'HARTFORD', 'NEW BRITAIN', 'CT', '250', '0', '0', '0', '875', '1100'),
(66, 'New Castle', 'New Castle', 'DE', '275', '0', '0', '0', '875', '1100'),
(67, 'SEAFORD', 'SEAFORD', 'DE', '275', '0', '0', '0', '875', '1100'),
(68, 'FORT PIERCE', 'FORT PIERCE', 'FL', '0', '350', '0', '0', '875', '1100'),
(69, 'JACKSONVILLE EAST', 'JACKSONVILLE', 'FL', '0', '225', '0', '0', '875', '1100'),
(70, 'JACKSONVILLE WEST', 'JACKSONVILLE', 'FL', '0', '225', '0', '0', '875', '1100'),
(71, 'MIAMI CENTRAL', 'MIAMI', 'FL', '0', '350', '0', '0', '875', '1100'),
(72, 'MIAMI NORTH', 'MIAMI', 'FL', '0', '350', '0', '0', '875', '1100'),
(73, 'OCALA', 'OCALA', 'FL', '0', '350', '0', '0', '875', '1100'),
(74, 'ORLANDO', 'ORLANDO', 'FL', '0', '300', '0', '0', '875', '1100'),
(75, 'PUNTA GORDA', 'PUNTA GORDA', 'FL', '0', '350', '0', '0', '875', '1100'),
(76, 'TALLAHASSEE', 'MIDWAY', 'FL', '0', '350', '0', '0', '875', '1100'),
(77, 'TAMPA EAST', 'DOVER', 'FL', '0', '300', '0', '0', '875', '1100'),
(78, 'TAMPA SOUTH', 'RIVERVIEW', 'FL', '0', '300', '0', '0', '875', '1100'),
(79, 'WEST PALM BEACH', 'WEST PALM BEACH', 'FL', '0', '375', '0', '0', '875', '1100'),
(80, 'CLEARWATER', 'CLEARWATER', 'FL', '0', '350', '0', '0', '875', '1100'),
(81, 'JACKSONVILLE', 'JACKSONVILLE', 'FL', '0', '225', '0', '0', '875', '1100'),
(82, 'MIAMI', 'OPA LOCKA', 'FL', '0', '350', '0', '0', '875', '1100'),
(83, 'MIAMI NORTH', 'PEMBROKE PINES', 'FL', '0', '350', '0', '0', '875', '1100'),
(84, 'ORLANDO NORTH', 'SANFORD', 'FL', '0', '275', '0', '0', '875', '1100'),
(85, 'ORLANDO', 'ORLANDO', 'FL', '0', '300', '0', '0', '875', '1100'),
(86, 'PENSACOLA', 'MILTON', 'FL', '0', '350', '0', '0', '875', '1100'),
(87, 'TAMPA', 'PALMETTO', 'FL', '0', '350', '0', '0', '875', '1100'),
(88, 'CENTRAL FLORIDA', 'ORLANDO', 'FL', '0', '350', '0', '0', '875', '1100'),
(89, 'DAYTONA BEACH', 'DAYTONA BEACH', 'FL', '0', '350', '0', '0', '875', '1100'),
(90, 'FORT LAUDERDALE', 'DAVIE', 'FL', '0', '350', '0', '0', '875', '1100'),
(91, 'FORT MYERS', 'FORT MYERS', 'FL', '0', '350', '0', '0', '875', '1100'),
(92, 'IMPERIAL FLORIDA', 'LAKELAND', 'FL', '0', '350', '0', '0', '875', '1100'),
(93, 'JACKSONVILLE', 'JACKSONVILLE', 'FL', '0', '225', '0', '0', '875', '1100'),
(94, 'LAKELAND', 'LAKELAND', 'FL', '0', '350', '0', '0', '875', '1100'),
(95, 'ORLANDO', 'OCOEE', 'FL', '0', '350', '0', '0', '875', '1100'),
(96, 'PALM BEACH', 'WEST PALM BEACH', 'FL', '0', '375', '0', '0', '875', '1100'),
(97, 'PENSACOLA', 'PENSACOLA', 'FL', '0', '375', '0', '0', '875', '1100'),
(98, 'SAINT PETE', 'CLEARWATER', 'FL', '0', '325', '0', '0', '875', '1100'),
(99, 'TAMPA', 'TAMPA', 'FL', '0', '300', '0', '0', '875', '1100'),
(100, 'MIAMI', 'OPA LOCKA', 'FL', '0', '350', '0', '0', '875', '1100'),
(101, 'SARASOTA', 'BRADENTON', 'FL', '0', '350', '0', '0', '875', '1100'),
(102, 'TAMPA', 'TAMPA', 'FL', '0', '300', '0', '0', '875', '1100'),
(103, 'OCALA', 'OCALA', 'FL', '0', '350', '0', '0', '875', '1100'),
(104, 'JACKSONVILLE', 'JACKSONVILLE', 'FL', '0', '225', '0', '0', '875', '1100'),
(105, 'ATLANTA EAST', 'LOGANVILLE', 'GA', '0', '275', '0', '0', '875', '1100'),
(106, 'ATLANTA WEST', 'AUSTELL', 'GA', '0', '275', '0', '0', '875', '1100'),
(107, 'SAVANNAH', 'SAVANNAH', 'GA', '0', '125', '0', '0', '875', '1100'),
(108, 'TIFTON', 'TIFTON', 'GA', '0', '275', '0', '0', '875', '1100'),
(109, 'ATLANTA EAST', 'WINDER', 'GA', '0', '275', '0', '0', '875', '1100'),
(110, 'ATLANTA NORTH', 'ACWORTH', 'GA', '0', '275', '0', '0', '875', '1100'),
(111, 'ATLANTA', 'LOGANVILLE', 'GA', '0', '275', '0', '0', '875', '1100'),
(112, 'ATLANTA SOUTH', 'LAKE CITY', 'GA', '0', '275', '0', '0', '875', '1100'),
(113, 'SAVANNAH', 'RINCON', 'GA', '0', '125', '0', '0', '875', '1100'),
(114, 'TIFTON', 'TIFTON', 'GA', '0', '275', '0', '0', '875', '1100'),
(115, 'GEORGIA', 'ATLANTA', 'GA', '0', '300', '0', '0', '875', '1100'),
(116, 'DRIVE CENTER', 'STOCKBRIDGE', 'GA', '0', '325', '0', '0', '875', '1100'),
(117, 'GEORGIA', 'ATLANTA', 'GA', '0', '325', '0', '0', '875', '1100'),
(118, 'METRO ATLANTA', 'ATLANTA', 'GA', '0', '325', '0', '0', '875', '1100'),
(119, 'ATLANTA', 'FAIRBURN', 'GA', '0', '325', '0', '0', '875', '1100'),
(120, 'CARTERSVILLE', 'CARTERSVILLE', 'GA', '0', '300', '0', '0', '875', '1100'),
(121, 'BOISE', 'NAMPA', 'ID', '0', '0', '0', '600', '875', '1100'),
(122, 'BOISE', 'CALDWELL', 'ID', '0', '0', '0', '600', '875', '1100'),
(123, 'CHICAGO NORTH', 'ELGIN', 'IL', '475', '0', '0', '0', '875', '1100'),
(124, 'CHICAGO SOUTH', 'CHICAGO HEIGHTS', 'IL', '475', '0', '0', '0', '875', '1100'),
(125, 'PEORIA', 'PEKIN', 'IL', '500', '0', '0', '0', '875', '1100'),
(126, 'CHICAGO WEST', 'AURORA', 'IL', '475', '0', '0', '0', '875', '1100'),
(127, 'CHICAGO NORTH', 'WHEELING', 'IL', '475', '0', '0', '0', '875', '1100'),
(128, 'CHICAGO SOUTH', 'MARKHAM', 'IL', '475', '0', '0', '0', '875', '1100'),
(129, 'LINCOLN', 'LINCOLN', 'IL', '525', '0', '0', '0', '875', '1100'),
(130, 'SPECIALTY DIVISION', 'WHEELING', 'IL', '575', '0', '0', '0', '875', '1100'),
(131, 'SAINT LOUIS', 'GRANITE CITY', 'IL', '550', '0', '0', '0', '875', '1100'),
(132, 'ARENA ILLINOIS', 'BOLINGBROOK', 'IL', '475', '0', '0', '0', '875', '1100'),
(133, 'CHICAGO', 'MATTESON', 'IL', '475', '0', '0', '0', '875', '1100'),
(134, 'METRO CHICAGO', 'AISIP', 'IL', '500', '0', '0', '0', '875', '1100'),
(135, 'Chicago North', 'East Dundee', 'IL', '475', '0', '0', '0', '875', '1100'),
(136, 'SOUTHERN ILLINOIS', 'Saint Louis', 'IL', '500', '0', '0', '0', '875', '1100'),
(137, 'HAMMOND', 'HAMMOND', 'IN', '475', '0', '0', '0', '875', '1100'),
(138, 'INDIANAPOLIS', 'INDIANAPOLIS', 'IN', '475', '0', '0', '0', '875', '1100'),
(139, 'INDIANAPOLIS', 'INDIANAPOLIS', 'IN', '475', '0', '0', '0', '875', '1100'),
(140, 'SOUTH BEND', 'SOUTH BEND', 'IN', '475', '0', '0', '0', '875', '1100'),
(141, 'FORT WAYNE', 'FORT WAYNE', 'IN', '475', '0', '0', '0', '875', '1100'),
(142, 'INDIANAPOLIS', 'INDIANAPOLIS', 'IN', '475', '0', '0', '0', '875', '1100'),
(143, 'LOUISVILLE', 'CLARKSVILLE', 'IN', '475', '0', '0', '0', '875', '1100'),
(144, 'INDIANAPOLIS', 'PLAINFIELD', 'IN', '475', '0', '0', '0', '875', '1100'),
(145, 'DES MOINES', 'DES MOINES', 'IA', '650', '0', '0', '0', '875', '1100'),
(146, 'DES MOINES', 'DES MOINES', 'IA', '650', '0', '0', '0', '875', '1100'),
(147, 'DES MOINES', 'GRIMES', 'IA', '725', '0', '0', '0', '875', '1100'),
(148, 'Davenport', 'Davenport', 'IA', '650', '0', '0', '0', '875', '1100'),
(149, 'KANSAS CITY', 'KANSAS CITY', 'KS', '0', '0', '475', '0', '975', '1200'),
(150, 'WICHITA', 'WICHITA', 'KS', '0', '0', '475', '0', '975', '1200'),
(151, 'KANSAS CITY', 'KANSAS CITY', 'KS', '0', '0', '475', '0', '975', '1200'),
(152, 'WICHITA', 'WICHITA', 'KS', '0', '0', '475', '0', '975', '1200'),
(153, 'LEXINGTON EAST', 'LEXINGTON', 'KY', '525', '0', '0', '0', '875', '1100'),
(154, 'LEXINGTON WEST', 'LAWRENCEBURG', 'KY', '525', '0', '0', '0', '875', '1100'),
(155, 'LOUISVILLE', 'LOUISVILLE', 'KY', '525', '0', '0', '0', '875', '1100'),
(156, 'WALTON', 'WALTON', 'KY', '525', '0', '0', '0', '875', '1100'),
(157, 'ASHLAND', 'ASHLAND', 'KY', '500', '0', '0', '0', '875', '1100'),
(158, 'BOWLING GREEN', 'BOWLING GREEN', 'KY', '525', '0', '0', '0', '875', '1100'),
(159, 'PADUCAH', 'PADUCAH', 'KY', '550', '0', '0', '0', '875', '1100'),
(160, 'BIRMINGHAM', 'HUETOWN', 'AL', '0', '400', '0', '0', '875', '1100'),
(161, 'HUNTSVILLE', 'HUNTSVILLE', 'AL', '0', '425', '0', '0', '875', '1100'),
(162, 'MOBILE', 'EIGHT MILE', 'AL', '0', '400', '0', '0', '875', '1100'),
(163, 'MONTGOMERY', 'MONTGOMERY', 'AL', '0', '400', '0', '0', '875', '1100'),
(164, 'BIRMINGHAM', 'BESSEMER', 'AL', '0', '400', '0', '0', '875', '1100'),
(165, 'DOTHAN', 'HEADLAND', 'AL', '0', '400', '0', '0', '875', '1100'),
(166, 'HUNTSVILLE', 'ATHENS', 'AL', '0', '400', '0', '0', '875', '1100'),
(167, 'BIRMINGHAM', 'MOODY', 'AL', '0', '425', '0', '0', '875', '1100'),
(168, 'TANNER', 'TANNER', 'AL', '0', '450', '0', '0', '875', '1100'),
(169, 'Fayetteville', 'PRAIRIE GROVE', 'AR', '0', '0', '425', '0', '975', '1200'),
(170, 'Little Rock', 'CONWAY', 'AR', '0', '0', '425', '0', '975', '1200'),
(171, 'Fayetteville', 'Lincoln', 'AR', '0', '0', '425', '0', '975', '1200'),
(172, 'Little Rock', 'Scott', 'AR', '0', '0', '425', '0', '975', '1200'),
(173, 'PHOENIX', 'PHOENIX', 'AZ', '0', '0', '0', '300', '1050', '1400'),
(174, 'TUCSON', 'TUCSON', 'AZ', '0', '0', '0', '425', '1050', '1400'),
(175, 'PHOENIX', 'PHOENIX', 'AZ', '0', '0', '0', '300', '1050', '1400'),
(176, 'TUCSON', 'TUCSON', 'AZ', '0', '0', '0', '425', '1050', '1400'),
(177, 'ARIZONA', 'PHOENIX', 'AZ', '0', '0', '0', '300', '1050', '1400'),
(178, 'PNOENIX', 'TOLLESON', 'AZ', '0', '0', '0', '400', '1050', '1400'),
(179, 'TUCSON', 'TUCSON', 'AZ', '0', '0', '0', '475', '1050', '1400'),
(180, 'PHOENIX', 'CHANDLER', 'AZ', '0', '0', '0', '350', '1050', '1400'),
(181, 'BAKERSFIELD', 'BAKERSFIELD', 'CA', '0', '0', '0', '275', '1050', '1400'),
(182, 'FRESNO', 'FRESNO', 'CA', '0', '0', '0', '350', '1050', '1400'),
(183, 'HAYWARD', 'HAYWARD', 'CA', '0', '0', '0', '400', '1050', '1400'),
(184, 'LOS ANGELES', 'LOS ANGELES', 'CA', '0', '0', '0', '150', '1050', '1400'),
(185, 'MARTINEZ', 'MARTINEZ', 'CA', '0', '0', '0', '375', '1050', '1400'),
(186, 'RANCHO CUCAMONGA', 'RANCHO CUCAMONGA', 'CA', '0', '0', '0', '225', '1050', '1400'),
(187, 'SACRAMENTO', 'SACRAMENTO', 'CA', '0', '0', '0', '375', '1050', '1400'),
(188, 'SAN BERNARDINO', 'COLTON', 'CA', '0', '0', '0', '200', '1050', '1400'),
(189, 'SAN DIEGO', 'SAN DIEGO', 'CA', '0', '0', '0', '225', '1050', '1400'),
(190, 'SAN JOSE', 'SAN MARTIN', 'CA', '0', '0', '0', '350', '1050', '1400'),
(191, 'SOUTH SACRAMENTO', 'SACRAMENTO', 'CA', '0', '0', '0', '375', '1050', '1400'),
(192, 'VALLEJO', 'VALLEJO', 'CA', '0', '0', '0', '375', '1050', '1400'),
(193, 'VAN NUYS', 'VAN NUYS', 'CA', '0', '0', '0', '175', '1050', '1400'),
(194, 'ANAHEIM', 'ANAHEIM', 'CA', '0', '0', '0', '200', '1050', '1400'),
(195, 'COLTON', 'COLTON', 'CA', '0', '0', '0', '200', '1050', '1400'),
(196, 'EAST BAY', 'BAY POINT', 'CA', '0', '0', '0', '325', '1050', '1400'),
(197, 'FRESNO', 'FRESNO', 'CA', '0', '0', '0', '375', '1050', '1400'),
(198, 'FONTANA', 'FONTANA', 'CA', '0', '0', '0', '225', '1050', '1400'),
(199, 'FREMONT', 'FREMONT', 'CA', '0', '0', '0', '375', '1050', '1400'),
(200, 'HIGH DESERT', 'HESPERIA', 'CA', '0', '0', '0', '375', '1050', '1400'),
(201, 'LOS ANGELES', 'GARDENA', 'CA', '0', '0', '0', '150', '1050', '1400'),
(202, 'NORTH HOLLYWOOD', 'NORTH HOLLYWOOD', 'CA', '0', '0', '0', '150', '1050', '1400'),
(203, 'SACRAMENTO', 'RANCHO CORDOVA', 'CA', '0', '0', '0', '375', '1050', '1400'),
(204, 'SAN DIEGO', 'SAN DIEGO', 'CA', '0', '0', '0', '225', '1050', '1400'),
(205, 'CALIFORNIA', 'ANAHEIM', 'CA', '0', '0', '0', '175', '1050', '1400'),
(206, 'CENTRAL CALIFORNIA', 'FRESNO', 'CA', '0', '0', '0', '375', '1050', '1400'),
(207, 'RIVERSIDE', 'RIVERSIDE', 'CA', '0', '0', '0', '1050', '1050', '1400'),
(208, 'SAN DIEGO', 'OCEANSIDE', 'CA', '0', '0', '0', '250', '1050', '1400'),
(209, 'SAN FRANCISCO BAY', 'HAYWARD', 'CA', '0', '0', '0', '375', '1050', '1400'),
(210, 'SOUTHERN CALIFORNIA', 'FONTANA', 'CA', '0', '0', '0', '225', '1050', '1400'),
(211, 'SAN DIEGO', 'SAN DIEGO', 'CA', '0', '0', '0', '225', '1050', '1400'),
(212, 'SACRAMENTO', 'SACRAMENTO', 'CA', '0', '0', '0', '375', '1050', '1400'),
(213, 'LOS ANGELES', 'MIRA LOMA', 'CA', '0', '0', '0', '1050', '1050', '1400'),
(214, 'SUN VALLEY', 'SUN VALLEY', 'CA', '0', '0', '0', '200', '1050', '1400'),
(215, 'GOLDEN GATE', 'TRACY', 'CA', '0', '0', '0', '1050', '1050', '1400'),
(216, 'DENVER', 'BRIGHTON', 'CO', '0', '0', '525', '550', '1050', '1400'),
(217, 'DENVER', 'DENVER', 'CO', '0', '0', '525', '550', '1050', '1400'),
(218, 'WESTERN COLORADO', 'DELTA', 'CO', '0', '0', '1050', '1050', '1050', '1400'),
(219, 'COLORADO', 'COMMERCE CITY', 'CO', '0', '0', '525', '550', '1050', '1400'),
(220, 'DENVER', 'AURORA', 'CO', '0', '0', '525', '550', '1050', '1400'),
(221, 'COLORADO SPRINGS', 'FOUNTAIN', 'CO', '0', '0', '525', '550', '1050', '1400'),
(222, 'Hartford', 'East Windsor', 'CT', '250', '0', '0', '0', '875', '1100'),
(223, 'Hartford-South', 'Middletown', 'CT', '250', '0', '0', '0', '875', '1100'),
(224, 'HARTFORD', 'NEW BRITAIN', 'CT', '250', '0', '0', '0', '875', '1100'),
(225, 'New Castle', 'New Castle', 'DE', '275', '0', '0', '0', '875', '1100'),
(226, 'SEAFORD', 'SEAFORD', 'DE', '275', '0', '0', '0', '875', '1100'),
(227, 'FORT PIERCE', 'FORT PIERCE', 'FL', '0', '350', '0', '0', '875', '1100'),
(228, 'JACKSONVILLE EAST', 'JACKSONVILLE', 'FL', '0', '225', '0', '0', '875', '1100'),
(229, 'JACKSONVILLE WEST', 'JACKSONVILLE', 'FL', '0', '225', '0', '0', '875', '1100'),
(230, 'MIAMI CENTRAL', 'MIAMI', 'FL', '0', '350', '0', '0', '875', '1100'),
(231, 'MIAMI NORTH', 'MIAMI', 'FL', '0', '350', '0', '0', '875', '1100'),
(232, 'OCALA', 'OCALA', 'FL', '0', '350', '0', '0', '875', '1100'),
(233, 'ORLANDO', 'ORLANDO', 'FL', '0', '300', '0', '0', '875', '1100'),
(234, 'PUNTA GORDA', 'PUNTA GORDA', 'FL', '0', '350', '0', '0', '875', '1100'),
(235, 'TALLAHASSEE', 'MIDWAY', 'FL', '0', '350', '0', '0', '875', '1100'),
(236, 'TAMPA EAST', 'DOVER', 'FL', '0', '300', '0', '0', '875', '1100'),
(237, 'TAMPA SOUTH', 'RIVERVIEW', 'FL', '0', '300', '0', '0', '875', '1100'),
(238, 'WEST PALM BEACH', 'WEST PALM BEACH', 'FL', '0', '375', '0', '0', '875', '1100'),
(239, 'CLEARWATER', 'CLEARWATER', 'FL', '0', '350', '0', '0', '875', '1100'),
(240, 'JACKSONVILLE', 'JACKSONVILLE', 'FL', '0', '225', '0', '0', '875', '1100'),
(241, 'MIAMI', 'OPA LOCKA', 'FL', '0', '350', '0', '0', '875', '1100'),
(242, 'MIAMI NORTH', 'PEMBROKE PINES', 'FL', '0', '350', '0', '0', '875', '1100'),
(243, 'ORLANDO NORTH', 'SANFORD', 'FL', '0', '275', '0', '0', '875', '1100'),
(244, 'ORLANDO', 'ORLANDO', 'FL', '0', '300', '0', '0', '875', '1100'),
(245, 'PENSACOLA', 'MILTON', 'FL', '0', '350', '0', '0', '875', '1100'),
(246, 'TAMPA', 'PALMETTO', 'FL', '0', '350', '0', '0', '875', '1100'),
(247, 'CENTRAL FLORIDA', 'ORLANDO', 'FL', '0', '350', '0', '0', '875', '1100'),
(248, 'DAYTONA BEACH', 'DAYTONA BEACH', 'FL', '0', '350', '0', '0', '875', '1100'),
(249, 'FORT LAUDERDALE', 'DAVIE', 'FL', '0', '350', '0', '0', '875', '1100'),
(250, 'FORT MYERS', 'FORT MYERS', 'FL', '0', '350', '0', '0', '875', '1100'),
(251, 'IMPERIAL FLORIDA', 'LAKELAND', 'FL', '0', '350', '0', '0', '875', '1100'),
(252, 'JACKSONVILLE', 'JACKSONVILLE', 'FL', '0', '225', '0', '0', '875', '1100'),
(253, 'LAKELAND', 'LAKELAND', 'FL', '0', '350', '0', '0', '875', '1100'),
(254, 'ORLANDO', 'OCOEE', 'FL', '0', '350', '0', '0', '875', '1100'),
(255, 'PALM BEACH', 'WEST PALM BEACH', 'FL', '0', '375', '0', '0', '875', '1100'),
(256, 'PENSACOLA', 'PENSACOLA', 'FL', '0', '375', '0', '0', '875', '1100'),
(257, 'SAINT PETE', 'CLEARWATER', 'FL', '0', '325', '0', '0', '875', '1100'),
(258, 'TAMPA', 'TAMPA', 'FL', '0', '300', '0', '0', '875', '1100'),
(259, 'MIAMI', 'OPA LOCKA', 'FL', '0', '350', '0', '0', '875', '1100'),
(260, 'SARASOTA', 'BRADENTON', 'FL', '0', '350', '0', '0', '875', '1100'),
(261, 'TAMPA', 'TAMPA', 'FL', '0', '300', '0', '0', '875', '1100'),
(262, 'OCALA', 'OCALA', 'FL', '0', '350', '0', '0', '875', '1100'),
(263, 'JACKSONVILLE', 'JACKSONVILLE', 'FL', '0', '225', '0', '0', '875', '1100'),
(264, 'ATLANTA EAST', 'LOGANVILLE', 'GA', '0', '275', '0', '0', '875', '1100'),
(265, 'ATLANTA WEST', 'AUSTELL', 'GA', '0', '275', '0', '0', '875', '1100'),
(266, 'SAVANNAH', 'SAVANNAH', 'GA', '0', '125', '0', '0', '875', '1100'),
(267, 'TIFTON', 'TIFTON', 'GA', '0', '275', '0', '0', '875', '1100'),
(268, 'ATLANTA EAST', 'WINDER', 'GA', '0', '275', '0', '0', '875', '1100'),
(269, 'ATLANTA NORTH', 'ACWORTH', 'GA', '0', '275', '0', '0', '875', '1100'),
(270, 'ATLANTA', 'LOGANVILLE', 'GA', '0', '275', '0', '0', '875', '1100'),
(271, 'ATLANTA SOUTH', 'LAKE CITY', 'GA', '0', '275', '0', '0', '875', '1100'),
(272, 'SAVANNAH', 'RINCON', 'GA', '0', '125', '0', '0', '875', '1100'),
(273, 'TIFTON', 'TIFTON', 'GA', '0', '275', '0', '0', '875', '1100'),
(274, 'GEORGIA', 'ATLANTA', 'GA', '0', '300', '0', '0', '875', '1100'),
(275, 'DRIVE CENTER', 'STOCKBRIDGE', 'GA', '0', '325', '0', '0', '875', '1100'),
(276, 'GEORGIA', 'ATLANTA', 'GA', '0', '325', '0', '0', '875', '1100'),
(277, 'METRO ATLANTA', 'ATLANTA', 'GA', '0', '325', '0', '0', '875', '1100'),
(278, 'ATLANTA', 'FAIRBURN', 'GA', '0', '325', '0', '0', '875', '1100'),
(279, 'CARTERSVILLE', 'CARTERSVILLE', 'GA', '0', '300', '0', '0', '875', '1100'),
(280, 'BOISE', 'NAMPA', 'ID', '0', '0', '0', '600', '1050', '1400'),
(281, 'BOISE', 'CALDWELL', 'ID', '0', '0', '', '600', '1050', '1400'),
(282, 'CHICAGO NORTH', 'ELGIN', 'IL', '475', '0', '0', '0', '875', '1100'),
(283, 'CHICAGO SOUTH', 'CHICAGO HEIGHTS', 'IL', '475', '0', '0', '0', '875', '1100'),
(284, 'PEORIA', 'PEKIN', 'IL', '500', '0', '0', '0', '875', '1100'),
(285, 'CHICAGO WEST', 'AURORA', 'IL', '475', '0', '0', '0', '875', '1100'),
(286, 'CHICAGO NORTH', 'WHEELING', 'IL', '475', '0', '0', '0', '875', '1100'),
(287, 'CHICAGO SOUTH', 'MARKHAM', 'IL', '475', '0', '0', '0', '875', '1100'),
(288, 'LINCOLN', 'LINCOLN', 'IL', '525', '0', '0', '0', '875', '1100'),
(289, 'SPECIALTY DIVISION', 'WHEELING', 'IL', '575', '0', '0', '0', '875', '1100'),
(290, 'SAINT LOUIS', 'GRANITE CITY', 'IL', '550', '0', '0', '0', '875', '1100'),
(291, 'ARENA ILLINOIS', 'BOLINGBROOK', 'IL', '475', '0', '0', '0', '875', '1100'),
(292, 'CHICAGO', 'MATTESON', 'IL', '475', '0', '0', '0', '875', '1100'),
(293, 'METRO CHICAGO', 'AISIP', 'IL', '500', '0', '0', '0', '875', '1100'),
(294, 'Chicago North', 'East Dundee', 'IL', '475', '0', '0', '0', '875', '1100'),
(295, 'SOUTHERN ILLINOIS', 'Saint Louis', 'IL', '500', '0', '0', '0', '875', '1100'),
(296, 'HAMMOND', 'HAMMOND', 'IN', '475', '0', '0', '0', '875', '1100'),
(297, 'INDIANAPOLIS', 'INDIANAPOLIS', 'IN', '475', '0', '0', '0', '875', '1100'),
(298, 'INDIANAPOLIS', 'INDIANAPOLIS', 'IN', '475', '0', '0', '0', '875', '1100'),
(299, 'SOUTH BEND', 'SOUTH BEND', 'IN', '475', '0', '0', '0', '875', '1100'),
(300, 'FORT WAYNE', 'FORT WAYNE', 'IN', '475', '0', '0', '0', '875', '1100'),
(301, 'INDIANAPOLIS', 'INDIANAPOLIS', 'IN', '475', '0', '0', '0', '875', '1100'),
(302, 'LOUISVILLE', 'CLARKSVILLE', 'IN', '475', '0', '0', '0', '875', '1100'),
(303, 'INDIANAPOLIS', 'PLAINFIELD', 'IN', '475', '0', '0', '0', '875', '1100'),
(304, 'DES MOINES', 'DES MOINES', 'IA', '650', '0', '0', '0', '875', '1100'),
(305, 'DES MOINES', 'DES MOINES', 'IA', '650', '0', '0', '0', '875', '1100'),
(306, 'DES MOINES', 'GRIMES', 'IA', '725', '0', '0', '0', '875', '1100'),
(307, 'Davenport', 'Davenport', 'IA', '650', '0', '0', '0', '875', '1100'),
(308, 'KANSAS CITY', 'KANSAS CITY', 'KS', '0', '0', '475', '0', '975', '1200'),
(309, 'WICHITA', 'WICHITA', 'KS', '0', '0', '475', '0', '975', '1200'),
(310, 'KANSAS CITY', 'KANSAS CITY', 'KS', '0', '0', '475', '0', '975', '1200'),
(311, 'WICHITA', 'WICHITA', 'KS', '0', '0', '475', '0', '975', '1200'),
(312, 'LEXINGTON EAST', 'LEXINGTON', 'KY', '525', '0', '0', '0', '875', '1100'),
(313, 'LEXINGTON WEST', 'LAWRENCEBURG', 'KY', '525', '0', '0', '0', '875', '1100'),
(314, 'LOUISVILLE', 'LOUISVILLE', 'KY', '525', '0', '0', '0', '875', '1100'),
(315, 'WALTON', 'WALTON', 'KY', '525', '0', '0', '0', '875', '1100'),
(316, 'ASHLAND', 'ASHLAND', 'KY', '500', '0', '0', '0', '875', '1100'),
(317, 'BOWLING GREEN', 'BOWLING GREEN', 'KY', '525', '0', '0', '0', '875', '1100'),
(318, 'PADUCAH', 'PADUCAH', 'KY', '550', '0', '0', '0', '875', '1100'),
(319, 'LEXINGTON', 'LEXINGTON', 'KY', '525', '0', '0', '0', '875', '1100'),
(320, 'Louisville North', 'Eminence', 'KY', '575', '0', '575', '575', '875', '1100'),
(321, 'BATON ROUGE', 'GREENWELL SPRINGS', 'LA', '0', '0', '325', '0', '975', '1200'),
(322, 'LIVINGSTON', 'LIVINGSTON', 'LA', '0', '0', '325', '0', '975', '1200'),
(323, 'NEW ORLEANS', 'NEW ORLEANS', 'LA', '0', '0', '325', '0', '975', '1200'),
(324, 'SHREVEPORT', 'SHREVEPORT', 'LA', '0', '0', '325', '0', '975', '1200'),
(325, 'BATON ROUGE', 'LIVINGSTON', 'LA', '0', '0', '300', '0', '975', '1200'),
(326, 'LAFAYETTE', 'SCOTT', 'LA', '0', '0', '325', '0', '975', '1200'),
(327, 'NEW ORLEANS', 'SLIDELL', 'LA', '0', '0', '325', '0', '975', '1200'),
(328, 'SHREVEPORT', 'SHREVEPORT', 'LA', '0', '0', '325', '0', '975', '1200'),
(329, 'PORTLAND - SACO', 'SACO', 'ME', '300', '0', '0', '0', '875', '1100'),
(330, 'LYMAN', 'LYMAN', 'ME', '300', '0', '0', '0', '875', '1100'),
(331, 'Portland - Gorham', 'Gorham', 'ME', '300', '0', '0', '0', '875', '1100'),
(332, 'WASHINGTON DC', 'WALDORF', 'MD', '300', '0', '0', '0', '875', '1100'),
(333, 'BALTIMORE', 'FINKSBURG', 'MD', '300', '0', '0', '0', '875', '1100'),
(334, 'SALISBURY', 'FRUITLAND', 'MD', '325', '0', '0', '0', '875', '1100'),
(335, 'BALTIMORE', 'BALTIMORE', 'MD', '300', '0', '0', '0', '875', '1100'),
(336, 'LAUREL', 'LAUREL', 'MD', '300', '0', '0', '0', '875', '1100'),
(337, 'METRO DC', 'BRANDYWINE', 'MD', '300', '0', '0', '0', '875', '1100'),
(338, 'BALTIMORE - WASHINGTON', 'ELKRIDGE', 'MD', '300', '0', '0', '0', '875', '1100'),
(339, 'Dundalk', 'Dundalk', 'MD', '300', '0', '0', '0', '875', '1100'),
(340, 'NORTH BOSTON', 'NORTH BILLERICA', 'MA', '325', '0', '0', '0', '875', '1100'),
(341, 'SOUTH BOSTON', 'BELLINGHAM', 'MA', '350', '0', '0', '0', '875', '1100'),
(342, 'WEST WARREN', 'WEST WARREN', 'MA', '350', '0', '0', '0', '875', '1100'),
(343, 'BOSTON', 'LANCASTER', 'MA', '350', '0', '0', '0', '875', '1100'),
(344, 'TAUNTON', 'EAST TAUNTON', 'MA', '350', '0', '0', '0', '875', '1100'),
(345, 'NEW ENGLAND', 'NORTH DIGHTON', 'MA', '350', '0', '0', '0', '875', '1100'),
(346, 'BOSTON', 'FRAMINGHAM', 'MA', '350', '0', '0', '0', '875', '1100'),
(347, 'CONCORD', 'ACTON', 'MA', '350', '0', '0', '0', '875', '1100'),
(348, 'Boston - Shirley', 'Shirley', 'MA', '350', '0', '0', '0', '875', '1100'),
(349, 'DETROIT', 'WOODHAVEN', 'MI', '525', '0', '0', '0', '875', '1100'),
(350, 'LANSING', 'LANSING', 'MI', '525', '0', '0', '0', '875', '1100'),
(351, 'DETROIT', 'BELLEVILLE', 'MI', '525', '0', '0', '0', '875', '1100'),
(352, 'GRAND RAPIDS', 'BYRON CENTER', 'MI', '525', '0', '0', '0', '875', '1100'),
(353, 'DETROIT', 'CARLETON', 'MI', '525', '0', '0', '0', '875', '1100'),
(354, 'METRO DETROIT', 'FLAT ROCK', 'MI', '575', '0', '0', '0', '875', '1100'),
(355, 'LANSING', 'DIMONDALE', 'MI', '525', '0', '0', '0', '875', '1100'),
(356, 'KINCHELOE', 'KINCHELOE', 'MI', '850', '0', '0', '0', '875', '1100'),
(357, 'MINNEAPOLIS', 'FRIDLEY', 'MN', '525', '0', '0', '0', '875', '1100'),
(358, 'MINNEAPOLIS NORTH', 'HAM LAKE', 'MN', '525', '0', '0', '0', '875', '1100'),
(359, 'SAINT CLOUD', 'AVON', 'MN', '525', '0', '0', '0', '875', '1100'),
(360, 'MINNEAPOLIS', 'SAINT PAUL', 'MN', '525', '0', '0', '0', '875', '1100'),
(361, 'MINNEAPOLLIS', 'MAPLE GROVE', 'MN', '525', '0', '0', '0', '875', '1100'),
(362, 'NORTHSTAR MINNESOTA', 'SHAKOPEE', 'MN', '525', '0', '0', '0', '875', '1100'),
(363, 'MINNEAPOLIS', 'DAYTON', 'MN', '525', '0', '0', '0', '875', '1100'),
(364, 'JACKSON', 'FLORENCE', 'MS', '0', '450', '0', '0', '875', '1100'),
(365, 'JACKSON', 'JACKSON', 'MS', '0', '450', '0', '0', '875', '1100'),
(366, 'Gulf Coast', 'Moss Point', 'MS', '0', '450', '0', '0', '875', '1100'),
(367, 'Grenada', 'Grenada', 'MS', '0', '450', '0', '0', '875', '1100'),
(368, 'COLUMBIA', 'COLUMBIA', 'MO', '550', '0', '0', '0', '875', '1100'),
(369, 'SIKESTON', 'SIKESTON', 'MO', '550', '0', '0', '0', '875', '1100'),
(370, 'SPRINGFIELD', 'ROGERSVILLE', 'MO', '550', '0', '0', '0', '875', '1100'),
(371, 'SAINT LOUIS', 'BRIDGETON', 'MO', '550', '0', '0', '0', '875', '1100'),
(372, 'SPRINGFIELD', 'SPRINGFIELD', 'MOY', '550', '0', '0', '0', '875', '1100'),
(373, 'KANSAS CITY', 'KANSAS CITY', 'MO', '550', '0', '0', '0', '875', '1100'),
(374, 'MISSOURI', 'SPRINGFIELD', 'MO', '550', '0', '0', '0', '875', '1100'),
(375, 'SAINT LOUIS', 'BRIDGETON', 'MO', '550', '0', '0', '0', '875', '1100'),
(376, 'KANSAS CITY', 'BELTON', 'MO', '550', '0', '0', '0', '875', '1100'),
(377, 'SAINT LOUIS', 'BARNHART', 'MO', '550', '0', '0', '0', '875', '1100'),
(378, 'BILLINGS', 'BILLINGS', 'MT', '1050', '0', '0', '0', '875', '1100'),
(379, 'HELENA', 'HELENA', 'MT', '1050', '0', '0', '0', '875', '1100'),
(380, 'BILLINGS', 'BILLINGS', 'MT', '1050', '0', '0', '0', '875', '1100'),
(381, 'MISSOULA', 'MISSOULA', 'MT', '1050', '0', '0', '0', '875', '1100'),
(382, 'LINCOLN', 'GREENWOOD', 'NE', '775', '0', '0', '0', '875', '1100'),
(383, 'OMANA', 'OMAHA', 'NE', '775', '0', '0', '0', '875', '1100'),
(384, 'OMAHA', 'OMAHA', 'NE', '775', '0', '0', '0', '875', '1100'),
(385, 'Fargo', 'Fargo', 'ND', '0', '0', '0', '1100', '1050', '1400'),
(386, 'LAS VEGAS', 'LAS VEGAS', 'NV', '0', '0', '0', '300', '1050', '1400'),
(387, 'RENO', 'RENO', 'NV', '0', '0', '0', '400', '1050', '1400'),
(388, 'LAS VEGAS', 'HENDERSON', 'NV', '0', '0', '0', '300', '1050', '1400'),
(389, 'RENO', 'MCCARRAN', 'NV', '0', '0', '0', '400', '1050', '1400'),
(390, 'LAS VEGAS', 'LAS VEGAS', 'NV', '0', '0', '0', '300', '1050', '1400'),
(391, 'NEVADA', 'LAS VEGAS', 'NV', '0', '0', '0', '300', '1050', '1400'),
(392, 'MANCHESTER', 'SALEM', 'NH', '400', '0', '0', '0', '875', '1100'),
(393, 'CANDIA', 'CANDIA', 'NH', '400', '0', '0', '0', '875', '1100'),
(394, 'GLASSBORO EAST', 'GLASSBORO', 'NJ', '200', '0', '0', '0', '875', '1100'),
(395, 'SOMERVILLE', 'HILLSBOROUGH', 'NJ', '150', '0', '0', '0', '875', '1100'),
(396, 'TRENTON', 'WINDSOR', 'NJ', '150', '0', '0', '0', '875', '1100'),
(397, 'CENTRAL NEW JERSEY', 'MORGANVILLE', 'NJ', '150', '0', '0', '0', '875', '1100'),
(398, 'NORTHERN NEW JERSEY', 'CARTERET', 'NJ', '125', '0', '0', '0', '875', '1100'),
(399, 'SOUTHERN NEW JERSEY', 'TURNERSVILLE', 'NJ', '200', '0', '0', '0', '875', '1100'),
(400, 'NY METRO SKYLINE', 'FAIRFIELD', 'NJ', '175', '0', '0', '0', '875', '1100'),
(401, 'NEW JERSEY', 'BORDENTOWN', 'NJ', '200', '0', '0', '0', '875', '1100'),
(402, 'NEW JERSEY', 'MANVILLE', 'NJ', '200', '0', '0', '0', '875', '1100'),
(403, 'Avenel New Jersey', 'Avenel', 'NJ', '125', '0', '0', '0', '875', '1100'),
(404, 'ALBUQUERQUE', 'ALBUQUERQUE', 'NM', '0', '0', '0', '525', '1050', '1400'),
(405, 'ALBUQUERQUE', 'ALBUQUERQUE', 'NM', '0', '0', '0', '525', '1050', '1400'),
(406, 'NEW MEXICO', 'ALBUQUERQUE', 'NM', '0', '0', '0', '525', '1050', '1400'),
(407, 'ALBANY', 'ALBANY', 'NY', '300', '0', '0', '0', '875', '1100'),
(408, 'LONG ISLAND', 'BROOKHAVEN', 'NY', '225', '0', '0', '0', '875', '1100'),
(409, 'NEWBURGH', 'MARLBORO', 'NY', '225', '0', '0', '0', '875', '1100'),
(410, 'ROCHESTER', 'LE ROY', 'NY', '350', '0', '0', '0', '875', '1100'),
(411, 'SYRACUSE', 'CENTRAL SQUARE', 'NY', '325', '0', '0', '0', '875', '1100'),
(412, 'ALBANY', 'SCHENECTADY', 'NY', '300', '0', '0', '0', '875', '1100'),
(413, 'BUFFALO', 'BUFFALO', 'NY', '400', '0', '0', '0', '875', '1100'),
(414, 'LONG ISLAND', 'MEDFORD', 'NY', '225', '0', '0', '0', '875', '1100'),
(415, 'NEWBURGH', 'ROCK TAVERN', 'NY', '225', '0', '0', '0', '875', '1100'),
(416, 'ROCHESTER', 'ROCHESTER', 'NY', '350', '0', '0', '0', '875', '1100'),
(417, 'SYRACUSE', 'CICERO', 'NY', '325', '0', '0', '0', '875', '1100'),
(418, 'ALBANY', 'CLIFTON PARK', 'NY', '350', '0', '0', '0', '875', '1100'),
(419, 'NEW YORK', 'NEWBURGH', 'NY', '250', '0', '0', '0', '875', '1100'),
(420, 'BUFFALO', 'AKRON', 'NY', '425', '0', '0', '0', '875', '1100'),
(421, 'SYRACUSE', 'CICERO', 'NY', '325', '0', '0', '0', '875', '1100'),
(422, 'LONG ISLAND', 'YAPHANK', 'NY', '300', '0', '0', '0', '875', '1100'),
(423, 'CHINA GROVE', 'CHINA GROVE', 'NC', '0', '300', '0', '0', '875', '1100'),
(424, 'RALEIGH', 'DUNN', 'NC', '0', '300', '0', '0', '875', '1100'),
(425, 'ASHEVILLE', 'FLETCHER', 'NC', '0', '300', '0', '0', '875', '1100'),
(426, 'CONCORD', 'CONCORD', 'NC', '0', '300', '0', '0', '875', '1100'),
(427, 'CHARLOTTE', 'CHARLOTTE', 'NC', '0', '300', '0', '0', '875', '1100'),
(428, 'GREENSBORO', 'GRAHAM', 'NC', '0', '325', '0', '0', '875', '1100'),
(429, 'RALEIGH', 'CLAYTON', 'NC', '0', '300', '0', '0', '875', '1100'),
(430, 'WILMINGTON', 'CASTLE HAYNE', 'NC', '0', '350', '0', '0', '875', '1100'),
(431, 'NORTH CAROLINA', 'KENLY', 'NC', '0', '400', '0', '0', '875', '1100'),
(432, 'STATESVILLE', 'STATESVILLE', 'NC', '0', '400', '0', '0', '875', '1100'),
(433, 'RALEIGH', 'CLAYTON', 'NC', '0', '300', '0', '0', '875', '1100'),
(434, 'CHARLOTTE', 'CHARLOTTE', 'NC', '0', '300', '0', '0', '875', '1100'),
(435, 'MEBANE', 'MEBANE', 'NC', '0', '350', '0', '0', '875', '1100'),
(436, 'CLEVELAND EAST', 'NORTHFIELD', 'OH', '450', '0', '0', '0', '875', '1100'),
(437, 'CLEVELAND WEST', 'COLUMBIA STATION', 'OH', '450', '0', '0', '0', '875', '1100'),
(438, 'COLUMBUS', 'COLUMBUS', 'OH', '450', '0', '0', '0', '875', '1100'),
(439, 'AKRON - CANTON', 'NEW PHILADELPHIA', 'OH', '450', '0', '0', '0', '875', '1100'),
(440, 'COLUMBUS', 'GROVE CITY', 'OH', '450', '0', '0', '0', '875', '1100'),
(441, 'CINCINNATI', 'WEST CHESTER', 'OH', '450', '0', '0', '0', '875', '1100'),
(442, 'CLEVELAND', 'LORAIN', 'OH', '450', '0', '0', '0', '875', '1100'),
(443, 'CINCINNATI SOUTH', 'AMELIA', 'OH', '450', '0', '0', '0', '875', '1100'),
(444, 'DAYTON', 'DAYTON', 'OH', '450', '0', '0', '0', '875', '1100'),
(445, 'LIMA', 'LIMA', 'OH', '450', '0', '0', '0', '875', '1100'),
(446, 'CINCINNATI', 'HAMILTON', 'OH', '450', '0', '0', '0', '875', '1100'),
(447, 'OHIO', 'GROVE CITY', 'OH', '450', '0', '0', '0', '875', '1100'),
(448, 'DAYTON', 'DAYTON', 'OH', '450', '0', '0', '0', '875', '1100'),
(449, 'CLEVELAND', 'NORTHFIELD', 'OH', '450', '0', '0', '0', '875', '1100'),
(450, 'CINCINNATI', 'FRANKLIN', 'OH', '450', '0', '0', '0', '875', '1100'),
(451, 'OKLAHOMA CITY', 'OKLAHOMA CITY', 'OK', '0', '0', '400', '0', '975', '1200'),
(452, 'TULSA', 'TULSA', 'OK', '0', '0', '400', '0', '975', '1200'),
(453, 'OKLAHOMA CITY', 'OKLAHOMA CITY', 'OK', '0', '0', '400', '0', '975', '1200'),
(454, 'TULSA', 'TULSA', 'OK', '0', '0', '400', '0', '975', '1200'),
(455, 'EUGENE', 'EUGENE', 'OR', '0', '0', '0', '525', '1050', '1400'),
(456, 'PORTLAND', 'PORTLAND', 'OR', '0', '0', '0', '525', '1050', '1400'),
(457, 'WOODBURN', 'WOODBURN', 'OR', '0', '0', '0', '525', '1050', '1400'),
(458, 'EUGENE', 'EUGENE', 'OR', '0', '0', '0', '525', '1050', '1400'),
(459, 'PORTLAND', 'PORTLAND', 'OR', '0', '0', '0', '525', '1050', '1400'),
(460, 'PORTLAND', 'PORTLAND', 'OR', '0', '0', '0', '525', '1050', '1400'),
(461, 'ALTOONA', 'EBENSBURG', 'PA', '375', '0', '0', '0', '875', '1100'),
(462, 'CHAMBERSBURG', 'CHAMBERSBURG', 'PA', '375', '0', '0', '0', '875', '1100'),
(463, 'HARRISBURG', 'GRANTVILLE', 'PA', '300', '0', '0', '0', '875', '1100'),
(464, 'PHILADELPHIA', 'PENNSBURG', 'PA', '200', '0', '0', '0', '875', '1100'),
(465, 'Bridgeport', 'Bridgeport', 'PA', '200', '0', '0', '0', '875', '1100'),
(466, 'PITTSBURGH NORTH', 'ELLWOOD CITY', 'PA', '400', '0', '0', '0', '875', '1100'),
(467, 'PITTSBURGH SOUTH', 'WEST MIFFLIN', 'PA', '400', '0', '0', '0', '875', '1100'),
(468, 'YORK HAVEN', 'YORK HAVEN', 'PA', '300', '0', '0', '0', '875', '1100'),
(469, 'ALTOONA', 'EAST FREEDOM', 'PA', '375', '0', '0', '0', '875', '1100'),
(470, 'ERIE', 'GARLAND', 'PA', '475', '0', '0', '0', '875', '1100'),
(471, 'HARRISBURG', 'MANCHESTER', 'PA', '325', '0', '0', '0', '875', '1100'),
(472, 'PHILADELPHIA', 'CONSHOHOCKEN', 'PA', '200', '0', '0', '0', '875', '1100'),
(473, 'PITTSBURGH SOUTH', 'MOUNTAIN MORRIS', 'PA', '400', '0', '0', '0', '875', '1100'),
(474, 'Pittsburgh-North', 'GIBSONIA', 'PA', '400', '0', '0', '0', '875', '1100'),
(475, 'SCRANTON', 'PITTSTON', 'PA', '275', '0', '0', '0', '875', '1100'),
(476, 'CENTRAL PENN', 'GRANTVILLE', 'PA', '275', '0', '0', '0', '875', '1100'),
(477, 'PENNSYLVANIA', 'MANHEIM', 'PA', '275', '0', '0', '0', '875', '1100'),
(478, 'PHILADELPHIA', 'HATFIELD', 'PA', '275', '0', '0', '0', '875', '1100'),
(479, 'PITTSBURGH', 'CRANBERRY', 'PA', '400', '0', '0', '0', '875', '1100'),
(480, 'PA', 'YORK', 'PA', '350', '0', '0', '0', '875', '1100'),
(481, 'York Springs', 'York Springs', 'PA', '275', '0', '0', '0', '875', '1100'),
(482, 'PITTSBURGH', 'MERCER', 'PA', '300', '0', '0', '0', '875', '1100'),
(483, 'PHILADELPHIA EAST', 'CHALFONT', 'PA', '250', '0', '0', '0', '875', '1100'),
(484, 'COLUMBIA', 'GASTON', 'SC', '0', '275', '0', '0', '875', '1100'),
(485, 'GREER', 'GREER', 'SC', '0', '275', '0', '0', '875', '1100'),
(486, 'CHARLESTON', 'RAVENEL', 'SC', '0', '275', '0', '0', '875', '1100'),
(487, 'GREENVILLE', 'GREENVILLE', 'SC', '0', '275', '0', '0', '875', '1100'),
(488, 'DARLINGTON', 'DARLINGTON', 'SC', '0', '275', '0', '0', '875', '1100'),
(489, 'SIOUX FALLS', 'TEA', 'SD', '900', '0', '0', '0', '875', '1100'),
(490, 'SIOUX FALLS', 'TEA', 'SD', '900', '0', '0', '0', '875', '1100'),
(491, 'Sioux Falls', 'Sioux Falls', 'SD', '900', '0', '0', '0', '875', '1100'),
(492, 'KNOXVILLE', 'MADISONVILLE', 'TN', '0', '400', '0', '0', '875', '1100'),
(493, 'MEMPHIS', 'MEMPHIS', 'TN', '0', '400', '0', '0', '875', '1100'),
(494, 'NASHVILLE', 'LEBANON', 'TN', '0', '400', '0', '0', '875', '1100'),
(495, 'CHATTANOOGA', 'CHATTANOOGA', 'TN', '0', '400', '0', '0', '875', '1100'),
(496, 'KNOXVILLE', 'KNOXVILLE', 'TN', '0', '400', '0', '0', '875', '1100'),
(497, 'MEMPHIS', 'MEMPHIS', 'TN', '0', '425', '0', '0', '875', '1100'),
(498, 'NASHVILLE', 'NASHVILLE', 'TN', '0', '400', '0', '0', '875', '1100'),
(499, 'NASHVILLE', 'MOUNTAINE JULIET', 'TN', '0', '400', '0', '0', '875', '1100'),
(500, 'TENNESSEE', 'NASHVILLE', 'TN', '0', '400', '0', '0', '875', '1100'),
(501, 'KNOXVILLE', 'LENOIR CITY', 'TN', '0', '400', '0', '0', '875', '1100'),
(502, 'MEMPHIS', 'MEMPHIS', 'TN', '0', '400', '0', '0', '875', '1100'),
(503, 'NASHVILLE', 'OLD HICKORY', 'TN', '0', '400', '0', '0', '875', '1100'),
(504, 'ABILENE', 'ABILENE', 'TX', '0', '0', '375', '0', '975', '1200'),
(505, 'AMARILLO', 'AMARILLO', 'TX', '0', '0', '450', '0', '975', '1200'),
(506, 'AUSTIN', 'NEW BRAUNFELS', 'TX', '0', '0', '250', '0', '975', '1200'),
(507, 'CORPUS CHRISTI', 'CORPUS CHRISTI', 'TX', '0', '0', '300', '0', '975', '1200'),
(508, 'DALLAS', 'GRAND PRAIRIE', 'TX', '0', '0', '275', '0', '975', '1200'),
(509, 'EL PASO', 'EL PASO', 'TX', '0', '0', '450', '0', '975', '1200'),
(510, 'FORT WORTH', 'HASLET', 'TX', '0', '0', '275', '0', '975', '1200'),
(511, 'HOUSTON', 'HOUSTON', 'TX', '0', '0', '125', '0', '975', '1200'),
(512, 'KEMAH', 'KEMAH', 'TX', '0', '0', '275', '0', '975', '1200'),
(513, 'LONGVIEW', 'LONGVIEW', 'TX', '0', '0', '275', '0', '975', '1200'),
(514, 'LUFKIN', 'LUFKIN', 'TX', '0', '0', '275', '0', '975', '1200'),
(515, 'MCALLEN', 'MERCEDES', 'TX', '0', '0', '325', '0', '975', '1200'),
(516, 'AUSTIN', 'AUSTIN', 'TX', '0', '0', '250', '0', '975', '1200'),
(517, 'CORPUS CHRISTI', 'CORPUS CHRISTI', 'TX', '0', '0', '325', '0', '975', '1200'),
(518, 'FORT WORTH', 'GRAND PRAIRIE', 'TX', '0', '0', '275', '0', '975', '1200'),
(519, 'DALLAS SOUTH', 'WILMER', 'TX', '0', '0', '275', '0', '975', '1200'),
(520, 'EL PASO', 'EL PASO', 'TX', '0', '0', '400', '0', '975', '1200'),
(521, 'HOUSTON NORTH', 'HOUSTON', 'TX', '0', '0', '125', '0', '975', '1200'),
(522, 'HOUSTON', 'HOUSTON', 'TX', '0', '0', '125', '0', '975', '1200'),
(523, 'LONGVIEW', 'LONGVIEW', 'TX', '0', '0', '275', '0', '975', '1200'),
(524, 'PERMIAN BASIN', 'ODESSA', 'TX', '0', '0', '400', '0', '975', '1200'),
(525, 'SAN ANTONIO SOUTH', 'SAN ANTONIO', 'TX', '0', '0', '275', '0', '975', '1200'),
(526, 'SAN ANTONIO', 'SAN ANTONIO', 'TX', '0', '0', '275', '0', '975', '1200'),
(527, 'DALLAS', 'DALLAS', 'TX', '0', '0', '275', '0', '975', '1200'),
(528, 'DALLAS - FORT WORTH', 'EULESS', 'TX', '0', '0', '375', '0', '975', '1200'),
(529, 'EL PASO', 'EL PASO', 'TX', '0', '0', '500', '0', '975', '1200'),
(530, 'HOUSTON', 'HOUSTON', 'TX', '0', '0', '125', '0', '975', '1200'),
(531, 'METRO DALLAS', 'GRAND PRAIRIE', 'TX', '0', '0', '275', '0', '975', '1200'),
(532, 'SAN ANTONIO', 'SAN ANTONIO', 'TX', '0', '0', '275', '0', '975', '1200'),
(533, 'TEXAS HOBBY', 'HOUSTON', 'TX', '0', '0', '125', '0', '975', '1200'),
(534, 'HOUSTON', 'HOUSTON', 'TX', '0', '0', '125', '0', '975', '1200'),
(535, 'SAN ANTONIO', 'SAN ANTONIO', 'TX', '0', '0', '325', '0', '975', '1200'),
(536, 'DALLAS', 'HUTCHINS', 'TX', '0', '0', '375', '0', '975', '1200'),
(537, 'AUSTIN', 'AUSTIN', 'TX', '0', '0', '250', '0', '975', '1200'),
(538, 'SAN ANTONIO', 'SAN ANTONIO', 'TX', '0', '0', '325', '0', '975', '1200'),
(539, 'ANDREWS', 'ANDREWS', 'TX', '0', '0', '450', '0', '975', '1200'),
(540, 'LUBBOCK', 'LUBBOCK', 'TX', '0', '0', '450', '0', '975', '1200'),
(541, 'Fort Worth North', 'Justin', 'TX', '0', '0', '325', '0', '975', '1200'),
(542, 'Austin', 'Dale', 'TX', '0', '0', '250', '0', '975', '1200'),
(543, 'SALT LAKE CITY', 'NORTH SALT LAKE', 'UT', '1050', '1050', '1050', '450', '1050', '1400'),
(544, 'SALT LAKE CITY', 'OGDEN', 'UT', '1050', '1050', '1050', '450', '1050', '1400'),
(545, 'UTAH', 'WOODS CROSS', 'UT', '1050', '1050', '1050', '450', '1050', '1400'),
(546, 'Culpeper', 'Culpeper', 'VA', '375', '375', '375', '375', '875', '1100'),
(547, 'Northern Virginia', 'Fredericksburg', 'VA', '350', '350', '350', '350', '875', '1100'),
(548, 'Pulaski', 'Pulaski', 'VA', '375', '500', '500', '500', '875', '1100'),
(549, 'Richmond', 'Ashland', 'VA', '375', '375', '375', '375', '875', '1100'),
(550, 'Suffolk', 'Suffolk', 'VA', '375', '500', '500', '500', '875', '1100'),
(551, 'Tidewater', 'Yorktown', 'VA', '375', '500', '500', '500', '875', '1100'),
(552, 'DANVILLE', 'CHATHAM', 'VA', '375', '375', '375', '375', '875', '1100'),
(553, 'HAMPTON', 'HAMPTON', 'VA', '375', '375', '375', '375', '875', '1100'),
(554, 'RICHMOND', 'SANDSTON', 'VA', '375', '375', '375', '375', '875', '1100'),
(555, 'BURLINGTON', 'ESSEX', 'VT', '500', '500', '500', '500', '875', '1100'),
(556, 'GRAHAM', 'GRAHAM', 'WA', '0', '0', '0', '800', '1050', '1400'),
(557, 'NORTH SEATTLE', 'ARLINGTON', 'WA', '0', '0', '0', '675', '1050', '1400'),
(558, 'PASCO', 'PASCO', 'WA', '0', '0', '0', '800', '1050', '1400'),
(559, 'SPOKANE', 'AIRWAY HEIGHTS', 'WA', '0', '0', '0', '800', '1050', '1400'),
(560, 'SPOKANE', 'SPOKANE', 'WA', '0', '0', '0', '800', '1050', '1400'),
(561, 'SEATTLE', 'TUKWILA', 'WA', '0', '0', '0', '675', '1050', '1400'),
(562, 'SEATTLE', 'KENT', 'WA', '0', '0', '0', '675', '1050', '1400'),
(563, 'SEATTLE', 'AUBURN', 'WA', '0', '0', '0', '675', '1050', '1400'),
(564, 'CHARLESTON', 'HURRICANE', 'WV', '550', '0', '0', '0', '875', '1100'),
(565, 'BUCKHANNON', 'BUCKHANNON', 'WV', '550', '0', '0', '0', '875', '1100'),
(566, 'SHADY SPRING', 'Shady Spring', 'WV', '550', '0', '0', '0', '875', '1100'),
(567, 'MADISON', 'MADISON', 'WI', '525', '0', '0', '0', '875', '1100'),
(568, 'MILWAUKEE', 'CUDAHY', 'WI', '525', '0', '0', '0', '875', '1100'),
(569, 'APPLETON', 'APPLETON', 'WI', '525', '0', '0', '0', '875', '1100'),
(570, 'MILWAUKEE', 'SUSSEX', 'WI', '525', '0', '0', '0', '875', '1100'),
(571, 'MILWAUKEE', 'CALEDONIA', 'WI', '525', '0', '0', '0', '875', '1100'),
(572, 'WISCONSIN', 'PORTAGE', 'WI', '525', '525', '525', '525', '875', '1100');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(255) NOT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  `lastUpdate` datetime NOT NULL,
  `image` longblob,
  `role` int(11) NOT NULL,
  `active` int(11) NOT NULL,
  `country` int(255) DEFAULT NULL,
  `address1` varchar(255) DEFAULT NULL,
  `address2` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  `phoneCode` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `mobilePhone` varchar(255) DEFAULT NULL,
  `phone2` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `userName`, `company`, `firstName`, `lastName`, `email`, `password`, `date`, `lastUpdate`, `image`, `role`, `active`, `country`, `address1`, `address2`, `city`, `state`, `zip`, `phoneCode`, `phone`, `mobilePhone`, `phone2`, `fax`) VALUES
(1, 'tsallabi', 'Sallabi Car Dealers', 'Tarek ', 'Sallabi', 'tsallabi@yahoo.ca', 'd4a26e0ab3558548116e52ce89b68411', '2016-01-02 01:11:11', '2019-05-25 16:34:32', 0x89504e470d0a1a0a0000000d4948445200000227000001b808060000009e8847d600000006624b474400ff00ff00ffa0bda793000000097048597300000ec400000ec401952b0e1b0000000774494d4507e3051f14230baecaae70000020004944415478daecbd7b7c54d5b9ffff597b66128888808d541135555b8a1808f7fb25805ab4a7b6e7d78badda737ea74539f6f4e639fd9eef8f6f4f4f0f87d6de4f6b6db4d67e0ba248cf29a556b192845c483299dc2f186335a244d0180908212499cc7e7e7f241326c94c3297bd67f69ef9bc5faf794192993d6bafb5f6f37cd6b3d67a1640082184104208218410420821841042082184104208218410420821841042082184104208218410420821842431baaecb783f1353ebde67767b1242483468ac02926861124ca024d2c91517178bc7e3b1a5932d2c2c94b6b63609a7eef7ecd9a3c5da7e816d959f9f2f1428f1233f3f5f6a6a6aa2aaebaaaa2ad1755d44a49f3549082143fcf297bf9440e7f6cc33cf8cf859d775292e2e8ebb93f37f77380ede8a624fd775696e6e967004457b7bbbc4fa5dfe577e7efe98f6632f37bfadfbfafa2496b6634d12abc2c8094908fff88fff38e2e773e7ce8df8d9ed7663fdfaf52a9e657af1c517878d75565696ed9c95ffff0e8763c2f704abf370292f2f1fe3d4ce9e3d3be2e75dbb76b193c7a1ad6b6b6ba3fe2c003072420821a370bbdda2ebbad4d5d5c9e8515d3ccbd1d8d83866d46fa751e5e872078b9cfcfef7bf1f737f8f3ffeb818f1bdfe9f3b3a3a382237118fc713533f0df659b61521848431924ba4480a7c757575895dea2f1c8713ec3d3ffef18f0dbf478ec4cda3bcbc3c6a71a1ebba8fe28410426c4459595950a36d0781d2d4d424e1389df6f6763aa62410f246474e1a1a1ad8070821c48a044e4704bebefad5afc6d5703ffef8e352525212d302c7504eabafaf8fe22449c54904d193319febeeee661f208490488d713cbea7b2b2326a835f5d5d2df5f5f5a2ebba5457571bb2fba5aaaa4aa2fd6c28a713ec3d6eb7dbb4faada8a890c3870f27c4f1edd9b36778079115fb75454585141414445cb6e6e666ae392184102b8c121339229de873454545633e73e8d02143221f467f3e9e8ec9ccfb88c735acd6cf8cba3f8a136227b89598588ad2d2d26183d9d4d4149f8740d3a2dab2bc6eddba31bfdbb4699321658a267f85551c70a49f29282808e9242375be469529d835fceb76a2f97ce08e3433ca4708c5092126d2dbdb3bfc7f11ebdaecf11c4a245b74435dc7e572855d966ddbb68df8b9bebe7ecc7b8a8a8a2c5b8fb9b9b9310b9e89de13ad00f04fd901c0ac59b3a2bed682050b6046f962a5b5b5954687104226a2a1a16138dc5c515111b5c1aeacac9460c9c2c6730e81aff122178165346a0745b0edccb12c741cfd1e7f44cacc907e5b5bdb886bdf77df7d1269b9a3ad4f23ae11e935c3bd8e3f3264c67d46b2ce89d33a84101221478e1c1963348b8a8aa2329cd118ded18e75bccf06bea7a2a2429e7beeb9a88d7ee0193ed15e23d856e8783ba668ae1f893819efaca39a9a9a31ef1fdd26b5b5b5868b9358c463a8d744bb67822dde0ef77e6211c084109252983dda8da51cc1de17982fe46b5ffb5accc262a23284b3a326d82e8ed1e7e698254e6269bf509f696f6f97aeae2e192d18c3bc8ed7fffbeeeeeea805cac18307c56871e2df45d4d5d515f57583bd37dce809232784101266d4c02ae2a4a4a424acef1e2f49dbe8a913a39c7d349f099640ce68c7f4f0c30f8b19e224d8fbcacacac272d846f487c2c242c3a661fc65a8afaf0f3b9a35de9466a88481142784106200c11cc08b2fbe28757575237e575a5a1ad3564ba345c144ef8b255f0900fcec673f33c4f184eb0ccd8a98c43aad6354bb195197feb533fa20863bf7175e7821681d04cbd3124b7deddab52be8f417ad1121840489988c0eb7476a7c45a47f3c47d7d2d21295530ad731f8b3af02835b461391a7239c91b759d1a9c085af555555711528a1a63902939cc55296c6c6c6b84c8f4552178c9c104288094c94b42c92058466be622d57e0e2c6681c4e2caf6011a760d368c14e309e88c0c5a65bb76e9578b65d22fac9e8ba4c649facadade5b40e490914ab8024429c8cfeddfdf7df8f5ffffad70a000e1d3a244625338b95c0046dd11872ffe713e1044627970b5686d6d656cc9d3b57c5da7efeefebe8e890cccc4ccbb68711df6d55873e5132c1f1da8d568958ae3fb30a48bc0996f9f5d1471f1d1ed95945980492ac07a47576761a2a3acd14265614d5762a9f916d4d08c509493ab2b3b36d51cee6e6e6e1ff676464d8bedebff9cd6f8ef99d52910d9a9b9a9a12e6a0ade05ced205042adb1bae4924b687c08c509217634ee81cc983123a9eafea73ffd69b0f688e81af3e6cd4b58f967ce9cc9e987309833674ed0e76cca942963ea2fd8510784509c9094a3b0b0d03665f59fa5028c3dbf261c3c1ecfc507cda2f3fa9146840e1e3cc84e9c4403819c9c1c5614a1382164f3e6cd133ae98a8a0a9c3973c652e5cecbcb8bf83381532622d29fe87b78e18517c6fc6ef1e2c5115de38e3bee98b0fdcc384dbaa2a2820f8f490285102bc23029b194e12c2f2fc79a356b94c7e391254b96245ebdc7b843c30abb4bc6fbfe68233aa1eee5c8912358b76e9d32fa5e8395b3bbbb5b92612d905dfb0021a6f65d560149b4e1acadad0530380db266cd1abf18b05c59a399dab11281d34c46d487a669aab4b474c4efd7ad5b173747176c0d05894c745098108a13424218c7254b96284dd3d48a152b860d65e0ff13455555d5889f1f7becb171cbe417595665f2e4c9863ba6f5ebd72bbf5009bc5e43438369ed102e81bbad528de2e2e209df535959490344284e088907dbb66d83a6692a3f3f3fe66bb95caeb0469a7e23bf68d1224bd74d5f5f5fdcbe6be1c2858689cbf1a66e468ba240e6cf9f9fb25181dcdcdc09ef7de5ca958c9a10cbe2641510cb2ae721a7b37fff7eb9f6da6be17038a09482a66970381c484f4f475a5adaf0fb67cf9e3d6c6c6fbdf556050cae4be8efef1ff1be40f1114c80f809b5932198332c292991356bd68cf85d636363d0cf85bb1e2396f52e6eb77bccef0217e8c6239caf699a216b4fb2b3b3a39a9e88e6bbbd5e2fd2d3d313b64ec8c8e76602745a1842084962ec7266c9a1438752e62c1523dbc40e6d1b6eb9429d6745082124493872e448c803dabefbddefd201584894182128745d1f71da712cb8dd6e1eba470821c98ec7e31963e81b1a1a6232fe7ea7969f9f1ff6e9b0fe9715f29aa492f088e694e944d1d6d6c65381092124559c564d4d8d047364f17686cdcdcd743816142856287f555515c52c2161c0d5dac4f6ce2a9cf7c5b2003452c7c6dc11e6525353230b172e841ddba4a3a323e8c9cdec33841092448c0e91efdfbfdf945173494989eda60f5241984ef42a2c2c14ab979d2d49082149eea4cc36fea19c606565259d4c02f0783c2185497e7ebe58bddfb20509212489397cf8f018435f5d5d6d9af1e7c8d77af8d773343636b24d082184103296f6f6f611918ceeee6ed9b16387c4baeb26d4abbbbb7bc4cfc176701142ec0117611142a2a6b6b6564265d2b5328d8d8dc8c9c9a1fd23c4a2f06c1d42c884f8171eefdebd7b4474c28ec20400e6cf9f0f2e6626c4ba70e4400819031df52067ce9cc18c193368270989338c9c104286a308f5f5f58c2004306dda3404eec862dd10427142083189c2c242d9ba75eb98e98cf9f3e7b37242b074e9d26121b777ef5e292b2ba35021c42418ae242405d075dd575858a86ddcb8919561d6488f595e09a13821844c284838b24f00b5b5b558b264096d2b21142784100a12ebc1680a21513e3bac0242284c88f1545656b21208a1382124b5a9aeae66255808a518342124eae78755404872c0c889c5467e9cd22124fae78755404872e076bb590984108a1342887558b162052bc122708a8d90d860d891902482533b1619f5714a8790d89e21560121c9c39e3d7b58098410db43754f4812c1c849e2292d2dc5faf5eb695b09a1382184509c58034ee91062c073c42a20247944444949092b971042714208894c949819dd983973262b398198bd9dfbddef3d24851fbb43e4c4db8c9091a486e14742e2244a828e0e4c9802b0e2d48ec7e3c1b265cb00003b77ee04006cdfbe3df9467b264fe9542c5929f2f6db7827e3126cf9ea3f61f257eea30d2784101219454545e28f96047b992584e2f91a7d8fcdcdcd71134722d25f595939a64c6eb75be25d0f668b42f9fd1fa4f083d7c8c96b3f2caf5ef75129fae07552b62a57a4f96546510821841823102a2a2a6c254eeebbef3ed1755d2a2b2b6de50cebeaea92429c1cd9f20969bee646e99a758374cdba413a667f588e5e7d833c77ed8d72fa670f53a010420831461c98f1fd7e1111cbeb1bdff88624ebce1f5b464d0e1d963f5f315bdec8faa89c9a75839c9a7583bc3bfb063979ed87a5edba8f48d915d74ad586db445e7f93228524059caf24c440a717f103a89457299596e87200838b3957ad5aa5d846d161e67a93b6cfdc2d17cacb31c3910e97ae03d0477eb700ef281dad1919f8d477fe0fd4173e47db4e6c0d77eb109240a727222ea3cb72f8f0e1b0de575a5a0a4dd394ff95ecc2c44c8a8b8b4dbbb63434cb3177053ea09c70ca00447cfebe73b1ff296086e6c4cd5e2f0e3df82db47efe5e9177df631485d8161a23421238126f6d6dc5dcb9730d7d0efbfafac4e50aae793a3b3b3173e64cc5f63278946762d4e4d8bffe1fe9dcf5146ed014041a44532384c9b031570a4a7ce8d51c68f77a717ef6d5c87dec575039d9b4f3c47630724248944ece08473767ce1cc3cb969e9e3eec8c6a6a6a869da7a6698ac2c45ec8c977a4febfff07d326a541a0415718234c4464f8a543834b0457a74dc2cc8e77f1c21d9f80fc7637232884e284103ab9c8686b6b33dc79d4d5d541d334b574e952c574eaf6a5ff37bf45e6f91e4c16057d542b2aa546fc3bfc7b01260f0c2053076e72b890ffafdbf1da37bf4581426c058d1621091e7d9b31b543e2d37680b9533a6539cb6456672732340d9a1ed9674501020dbd9a427b6f0f26cf9f8f854fed86ca9cc1be462c0f23278484414b4b8b69db45cd98da2123292e2eb65de4c0fbf43ee93b71125381888509301841d1444786cf878fb8d2a1b5b4e0cf5b6e8734b7308a42284e08498611370584bd993d7bb6edca5cb6eb77b822633220139b694d065fe3f462cc743871c3bb9d38f8f13b217f7e810285509c1062d7d176bc1651266bc233ab909595658e0135694a475ec89773f58db85439c6fe4d8277157d8292a4fb0670a54ff01101fef88f0f409ef81dfb1cb12c9c7b24c42262810b57cdc1e3f1c892254b6cd566afdef959f15557e33287134e7dbc391d7dcc3853d4e0944ed0f20a30a069e87628bc72e13c566efd12a6edf877f63b427142088509c549b2b4a7196d26f54d5274fb27304f692376e288c8989d39a3c5890cfdd92f4e941a9b1345134041c77997132ff7f5e2a6cf7d06b37ef663f63d622938ad43c810f19cc6212414af3db9075394826378eca80f0b8db16b4bb411665cc9c8a8c945617231e5bdae06454c9aaee323e9e97875dfefd172d73d22ef9d66df27142784586d74bd76edda848b23b684b1343636da2b6a72f21d69fed3b3983e94e157836e78b99552f08940f97c9832e0c30dae4978aff808fefaf507d96108c50921561226562847a2c5513272f3cd37dbaabcfd8f3e860f767763b23fe2211ab480dd3aba9a78e16b68533f34f52302a51c50ca018886345dc787d226a1b3b0082f7df60b229d5d14c924e1709e91a42c22d26fc6c17b561b8d53789a73694dd31c465fb46cfe22997dea0c26690a9a7e710a4737a957044e11f5381c383ed087f4c50bb1f8d9fdec8724b1b69055405291828202b19a3021c662e6349919c2a4ffffee91fe773a9101359c742dfa4849980a4b5d7ca58b0fd7bad2d0575387e6cf7c9e6b5008c50921f1a4a5a5457273732d5936b7db4d876010478e1cb14d59e5f4d9e7dd7bf7e283e993e0107d82846ac6e2df01e4d081c9be015ceb4ac3e9231578f51bffcc4e44284e08890715151596cef67aead4293692416cdfbedd464aaa7ccbf9fa064cd1b4e1d387e3268c440617de2a7d780d4a96cb85b7f30bf0ea97ee17f17ad7b3379178c379459232d8659b30d79dc44e4747876466661a7e5d8fc783152b5618de3eafdef959f1565763fa8449d7ccc10105810ffad07855838e1e671afeda7f01f33ef759ccfce90fd927497ced20ab80509818475e5e1e344d53c5c5c5492fa2ac8c19c20440902468b123553572bca61a97c3117761a286d2e38bc8f0193e4a29e8d090a60fe046571a9af73e83f7bfff23f64912dfbec92a201425c6e0f57a919e9eae86be770040d48b26193d898d868606c9cece3667446770dbbcf2f57f96b3bfdf8feb1586a774fc2228d4393a468f4f35b998a06df82f439964df77b9d0d4df8bbff9c14350f7dcc57e49e202232724a91d54dc1e244d537e6132f4b333da6bedd9b3878d1703757575b61126f2e67179f94f7fc2f4f4b4116b4d745d8f833001fc996383ed0ad287c45286eec3cdae741cfcdfff1fe45021232884e2849068292d2d35cd418d7656a11c565e5e5e54d78c47b99399050b16d8a6ac177efd1b5cd1db8f49a36673cc983e8a4aba28c0e903a60d0ce07a8713fbffe91b90d7dfa44021a6c3101d493ae2359533d1283a9645999cd6b15efb77767662e6cc9986b58b9c3efd7cd99adc2dd7be7f0e93e03f13c79fc5d537ae40313e399b7f9c3a5225f90f1bd404e8776838a5eb3833fb6aac2acddfa05cae62f636629a7d6515103aa6c8854338e2219645995c146bbd7a3352980040df8167b7c8a953c8d07d230eebb3d4e8754820e90a70ea3a3ea80b1cedc751f0775f2a626f2314278458489898f15e925ac8e9f79fafd8bd0797a74f860a58372d4a87287d4cd444864e121eeeeb4a87ae8cdcd973f1d4e291dfa70defe8d104d094607afa24bcee76438e945340138a1342c6233f3fdf52c2842486a6a62653ae7be2c409632f587264cb85975a70a90d66d6fd0b737d1ad0e770e2dd9e1e7cfcb39f835ab38acf03310d762e627bcc8c98c49a74aba2a242962f5f4e411407babbbb252323c316c2b4f5f64f8ad6d088cb34e7f0365e60701a253ebb74468f4f278ec2781d1a3a7d3ef4cff90896141c64bf2414278424429818e594622923c58935fa8291ed209e2a29baf33398a7392cb32b2764598716c48a02ce381d6885e0e385f9501fba96fd92988a935540e88c280c48682a2a2a0cbdde2b4f3d834b9506071474ab3c4b181421a317e6fa85890ec1f19e1e7cfcbf7e4c6142e2023b19a13031599430721297117ebf88b8acde1fe4d871f963ee666443617aff405c0ff80b254b06058836182519d5531d50e87528bcd5df87abefd882ab1ecf637f2471810b62098589c962c0ed765bf25e9389a79f7eda1461b26ddb3643af77ee578fe1eaa1a46bba45dcbc8800faa03009dc15a47401c487d3bae0dd6baec1953f7ce8207b1a891754c184c2044071713172737395d5cabd77ef5e7ce10b5fe0739aa07ed1d0d080850b171a52fff2ee29295fbf19d79d3b0b173428e816c86fa28f18a78ac2709974a5a10f400b7cb8f50fff0db57421fb21891b8c9c10db505e6e5e5e053385492cdc75d75d6cf80409130086091300b870e0007c5da7902e8307ed5923f19a36c20df8cba409d0e7d0f0ea403f6ef9fa57294c48dce18258620b6249053fae69e69a0e1282caca4ac3ae25a7df7fbef0ce4f6156fa243806bcd045412cdaf54401034ac3bbbe014c5fbd12da835fe3334212229b09b13c76172667ce9c89fab36eb79beb4e12407777b771173b5cbac5fbf22bb844290834cb0a1300f0290de7203877d954e4fcf217ec082421501113cb6346d83e111113eedab14fff30bace9b6efd1b497fe9254cd51c70eaba65eb522985730e075a7a2f60cb138f43dd7e0bfb1d49088c9c100a13c2fe612252ee968ee6264c13cdd2c20418dca1f3564f37166cfd070a13427142483c1c4f4949098509098bf2f272c3aef5d2537b315d39e0820f9a45a59426c080a6e16d08a664cfc7acfff8373e2784e28410b385c9993367b061c386841adc580e8fabafafe7ba9338b266cd1a63b60fbf7e4c5e7be1054c4b7301ca6199dc26a38589828e730a786dca2558f0781e3b00a13821c46c6102003366cc48b85b983d7b76d46578f7dd77d931e2d04f8ce6ecaf1ec3077bbd98a4ebf0c19ac5f569c00587036ff4f7e113dfd90e95750da32684e2841093230403c93095b379f366760e9b216fbf234d075fc0d54e27d2741da34ffe8defe9c3211c8000fd9a136ffa0630efcebf81fafc67294c08c50921a3993f7fbe719d5bd394a6692ed62a89b4df18719d737f7a1672aa0b937d3e38441f234aac7022b14f69e8820f3d57cfc295fff15d363ea1382164344685e99b9a9a9272e12bcfd9b948434383a5eb42de3b2d9ea7f662c6a4c9836b4d0232b15a41940083e9e97b15d0a114363ef23054e60c464d08c509216639de050b1658d6c8c6229af6eddbc78e32447676b629d72d2b2b33e64205051878e5554cd1344b2e8205807e8786d7fa7bb1e96b5f835a9ca3e2f18c575757536093b0a0522649234c9a9a9a2c2d4cfcb8dd6e59b66c59dcc50dfb4cfceab7e5d68f8bf3e84bb8d4e13439b7897f7c19de7738940c4f2bbdaeeb501bd661f153bb543cdb8c7d9844d2b3094908462e80b583300180688589994e99c2c438a4a44c3a9a9b310dca7249d74404039a86d34ac385cc4c2cfad9cfe226c8fdfff7783c8c9e9009e1c17f24a118b100d62e111323686a6a62a7b13847f7ecc154cd0987048b67e8068f0b23173fe71d0ebcecf3e18e8776425d31232ed339813f2f59b2849d844c08c36bc4f623603b868979ce4ee2fb8d19f52acd2df2c72d776081330d97f50f04596f62b43809b35c2280a630a06978a5ff023efae52fe38371c8023b5e5b717a878cfb3cb20a88cd1dcc801def7fe7ce9dec0451505a5a6ae92981338f3f816b067c98a4ebd0953e4a94e84326d748b33b367fca45d37ef17b1c1814261dbe7e4ccb599070614208c509b11c7575758618ad13274ec0ae794cd6ae5d4ba31f05ab57afb66cd9e4ed0e7929bf00573a5c965b6ba22be02c04273332b020ef9796187c50bc108a1362298cc8f350565616533af844b36edd3a86b4ad64080d986238f73ffb21a7df471a0025a3cdebc84886a8c19731263c98190f88a8680a3e28bcd5db872d3b77405d7bada97d8fa283509c10dba1ebba18b10876eddab574ee2946414181659d9ebc775aca9f7e1a1f484f874306a7512c61e005e8530ebca90f60fea73f0df5e9bf35b560870e1d8aa88dac9e4c8f509c901411265619e512fb919b9b6bddc2bdf017385e3b864b940601263ce44f893fba6290381a8ec4e823848902705a7c387d7d163eb0e33b07cdac82c6c646d9b46953449f312b991ea1382124be1d36898449717171c2851e3186e6dfedc67569e970e93ee82afe663550ecf893ac0d681ace39349cd4805b7ffe33a8e9536f3773e071f3cd3747f5d923478eb02f138a1392188c70a6c91631c9cdcd6504284c2a2b2b4d7360b1f62b292a95f75e6ec5650a430b61c72e86d564f065262202a51c504ac101853e8703af78fb70cb37bf0eb570be65fbdaaa55abd8c109c509b1a763e1544e6ab374e952cb96ade1c927315d53708940e9929083fdb4a1752e2202518057299cf4f6e2aa4db9c0b6ad1bac3ef0602490509c90b8d2dede2eb13a96dada5a5624b12452df206fe61fc665ae34083488a6a007d946ac2b0c25640b95972446e73e7c7d0d3ea5e1ac12f45f91899b7ef6132897abd8cac204003c1e0f3b13a13821f163d6ac59315f63c99225491b35696d6d8dfab346e58bb13a252525969dd2e97a6217ae1a18409a0e4802827bfef5257e9452b8e0d0f0aa6f006b7ef010d407cc4b4f6f64b42396f3a608c509217137fec93e9d3377eedca8ef6fc1820529d1870e1c3860ca75f7eedd1b9b3078eba4bc945f80590e17d27c3a94aec2585b329897c4a835284aa9110245870f6ff55cc082ad5f82ba25d716c2c4cffefdfb39b5432ef66d5601319b680d59aaac33e1393bf1778446d45fd7cf1e96977ff0237cd49136b47158837fca469fe0aa7e61a21bd07a7e81e27568787760008e9c05b8f9b9fdca6eede1f178b062c50afa2432fc3411626e278bd201b4b5b5712495e27477775b5298c87ba7a5e6f7ff8de96993a08b0cae35517ac0da8f091cbc3246985c34e43ace29e0f5a95330ef97ff654ba1b86cd9b29499aa241427c4c602252b2bcbd42da456e1a5975e8afab32d2d2d495d3fbb76edb266c19e7d1ebeb6d7718952106d68978c24a6297c4ad0a3b9f0467f1f3eb9f33fa0aebbc694e8433c76d4a4ca5425991886d0485ce1148ff1863f99ebc6aa533a8db9b749c65f5fc5659a134af7c54fe40f4f0769433febe8713971bcbf1f377efa53b8e2e73f36a52fd4d5d549bc8403d3061080911312679a9a9a2c3b6a23d6a2b6b6d69a533a870aa5abb5159709a0745fc2222600a06b0e74e90318c8ba0e99dbb79b2610e319d1e0b34e284e48dc59b060816a6c6ca4d1221392939363c97235ef7d0697395cc3518cd892ae4596f72470ad8aae801ed1d1e97060ed2f7e0e75c574461c08c5092131381d1a518e3427a4b3b3d394ebc6925b466a1ba52dbf10d39c8e846482f5a394a0cfa1a1cde7c5c67ff967a845372bf62d42714248ac1d4fd3d4fdf7df4f6319501fec1523db393333d3946bc7925ba6f3d7bfc1755e1f26e9ba41bb6db409cd70a008f2e74751bae0edde1e4cbbed16a87ffc72d209935458084f2610e0ac0292485e7cf145d9bc79339d3906b7cd66646450dcc09a0b61a5fd84946eb8051fbed0078776f114e0b88a58193c6df87de8e8c8bc1cab3d651bcc484f6f854100057b8a0f6059052491dc7aebad5119a08a8a8aa41b59452b4c9211b3a6744e9c3811f5674feddb07c7f96ea461489818e83b450dbec68c1e87a3261a1c50d094428f43c3cb0a58fdc31f2059858995ca41284e48aa76c2284648cb972f475f5f1f8dd710c5c5c54953177d7d7da64de9cc9e3d3bbaa8c97b5d52fdcc7f23333d1d50830b58e3b14b27f03b06a0d0ab291cbbd08375dbee87dab4def0c8020501a13821244681e272b992ae0ea2dd6abd76eddaa4a9074bb6eb1f9f45daf1b790010dfa90d934725a47c9f8d7734030a06978dbe7c515cb9762dafffe67c385c9ae5dbb284c8865e09c1eb10c4cd0c6646c668edeebeaeab078f1e2a8eaa876ed2699fafa1b98a26970ea7a42eae594c38163974cc66d7ff933d435b3951dea3c118316922483355601b1bb214aa65074696969d23918abdc43b4c2445e7851cebed686a990b80b137f2e151f14deeaebc56d3bbe6bb830b1f29420a799521727ab80584da0446390745d97641865ad5dbb7680cfa5f1783c9ea83f5bf7d45398eed0e0996ce06f0000200049444154d2050265e8817de1d0e770e278ff052cfafc5d507ffb8994889810c2c809b19c518b5664141414d8ded06a9ae662ef326114e68c4eef89a75ada8b4a30d5e982403341988cce7372f1674d00051da77c5e74cf9d83693fd8b1c1c86f16917ef60c427142924e98584da0e4e6e6722448c11b94254b9644252bdefecd6f315b14d20c4bba163e039a86f73507de9d9486cd0fffdcf06dc322620b217ce8d0213ed314278458c7a1c470068f6ee73a8d25c7879dc5d98103072c551e39fe96fcb5b8141fd49c4833ac4b8d8e948c3e5b67f0674d805e4de155dd8b4d0f7e036aee9c949dce89654a8e509c90141623f5f5f5a618ba9c9c9c680f09b4f5da93993367a6e40e853befbcd394eb96949444f5b9779e7e06ae73e730597438e22877450d464ddef17971f52db7426ddb9ad2eb4cb69b74da32b136dca6450c356c662c4a6d686890ecececc895b78d17c8a6e29662b39c6634f521ef9e92bf6cf938b2deebc207fabd90a14bf877cf8c9ee2f16777bd98abc4bf6e440ffafef118d0349cd17de89c7905d614fce5a09a7ed9eda92a4c92e1592651b639ab80844b38499a3a3a3a0c377e0b162c50d18476b9fe84c2246af6ff11cee3edc800868589d9f8b3c15e7068784303d6fce807305298bcf8e28bc2fe41284e48d271cf3df74cf89ecccc4c1c3e7cd87043b262c58aa8044a7979b92d8d5a7d7d7dd49f6d6c6ca4211fa2b2b232aacf353cbd171f72a521dd37723e4757832f1119715ab0121d4ac6ae1df1bf3f88bbc5c8b52683e7e88802deea398f250f6c83ca5d6b982ad2753daa0336ad02d79da41e0c9511d3462e668462a32987d7eb457a7aba4a853a0780279f7c125ffce217552adcab197d500ebe28457fff65643b5c11444df408c77b23dfaf09d0e7d4d0e1f5e29215cbf1e13fec5556afdbb88fa439b593523072424ca3a5a5c570a3c8337826e6f8f1e396121d6eb75bf2f3f3a5a3a343745d975dbb7689aeebb275eb56d1755dace63c6b9f7c0a335ce9232323a3fe1ff8f34553aa8d6368756823222563f3999c85e0f88c69b8f1673f32ec5ecc886212120fa84489e9232fab4450ec36f2b2c3a2d8b6b636c9caca0200e4e5e5e1b6db6ec3bbefbe8b65cb96d972b42dee1af9d3fff369cc77a563aa7760c494cce8a99cc84681430b638308189f127895132ffbfab0f9578f407de27643daaebebe5ee6cf9f9f5ca369464f284e08314a9c1c3c781077dc718725b643dac9b8555555c9e2c58ba3fa6c6767a7e15b920b0b0b45d7756cdcb831691dd9c9bfdf2a9d7fc9c7152ed7b8e7e85cdcb5a3e362c444c3e8752423844f1071a309d0e372e28dfe3eccfdfce770f98fbfafacf0cc529c108a1392f4e2c42cc3920a022591d193868606e9e8e8801d1752eedcb913dffef6b7238b9abc715c4a7337e3c3fd5e389416b02dd8187132e6fb1400d1f096f2e1c2473e8c95cf3c0df581e914271427045c7342c23408461805a30d66b4652a2d2de53cfc386de47f656767c3ae3b3c6eb9e596883fd3b9f719a4f5f6c189f185c9a028197c69a20d0b954061a209027e1fdcf40a34f428c1e9f434acfcc5cf294c0809802a9444447777b7646464586af493cc119478454e3a3a3a243333336547d8d2d129076fdd821bdf3f8bcbbddea06b43827e4f88a46ca17e1f284e7a1c1a5eebef45ee7f7c07eacb7f4f619244cf2d31a09d59052412a64c99a28e1c3962c8e83cd1c6ca0e86dcebf546fdd9c2c2c2b0ef2f3333534f963eba73e7cec83fb4ef0f9872b20393454187369c102d506c048b8484ca6312f8fbd1d71abc9e8eb7fb7b71c5c76fa73021240854a0242a8a8a8a64ddba75315dc3e8459bc9ba83275ed19364726e91b66bcd8a757279fb0964286d38e5fc6871e2171d111958a54688134d807e8786b3ba0fa7665d85959e2386f4bfad5bb7caa38f3e9a1a236a464e52a39d5905241a366cd810f33a94cccc4cb8dd6ec31c62555515479b24f2299d679f97f36f1ec725224185895f94e851f4f6d1c24441c7058786575c2eac78e87b8689d754112684e284a438baae4b3849d4621528cb962d334c202c5fbe5c55545450a044c9c1830753f2beab9e7c12d31d0e3821132c628d0ebf40f169409fc38963177ab0ee81fba136acb1dc227342284e88a5850900cc9933272ce367a59d3cab57af8eea0c9e8a8a8aa434f295959561dfd7962d5b52aeaf4b6985745654e2528713020dbaf2676e0d348d63cfcc09f92c04599ba29482261a7c9a0b277c3e5cb96a252efbe7af5398104271426235821319422b0994152b56a848a300cb972f477777b7258dbda669aaa9a929aacf2e5dba34b50c5a84fdf0f5471fc3d5d0902e7a54d3364105cf50a42430e19aae80f7a1e3bd1997e1a3fff5d398bfa3baba3a6585095301a4065c5844c21609adadad983367ce840ec02ae9ee9369816cb45bb89b9a9ab060c182b0ee2919b61347d27ed2f68694dcb20537f6f5c3a53022b7890c5d65a27c2717b3be6a43ff97317f1305e8a2f092cf8bdc5fff0aeae35b54bcfb75aa8b5062c336661590700ddf9c39730000478e1c31358a6294f1554a7913f5dd46525f5f1f756e99ecececb0efc9e874f756e7c49e3d98d4d383c9a2c3296a4ce423cc3e36de7330b83b47d3f0860c60d9dfdd1db33021c0830f3ec84a4801f8a090a89cf39e3d7b70efbdf732826281b630f27eec3e2a0ff73ea5e33d79f1b6dbf1a133677179bf37e6299d50e7e628e868570aefcdfd08361e3ac875260670ecd8315c7ffdf5f45d490e2327649843870e856df8eebefb6e98bd0ea5a1a1c110437cf4e8515b3901a393d4a58c318ba4bf3dbd0fae13273149c490b526c12228030ee07dcd81cec9e9c87df4114b89553be33f059b509c901461d3a64da6388ca8327622b2298909ae638b0cb2561025d1ec74b22335fbf6e14369e948f7f977de8cdc8513eed4cec5dd39233faf4147af72e015dd878ddffa16d487b2b8ce849048043fab80446b00e39d7d34516b59cc9ee2a9acac9478ecaa09e73e6cec04754dd31ce1bc51fef86729ddf615cc73b802a226fa98f15ab0a99a60e204f09f4e3cf87951804f6938e6f322f3e377e0c6471fa63049405f26366f63560189870134c298d4d4d458620d8b9175aeebba5869bb6f7d7dbd5d9d9523dcf77af63c8d69aef451d1116d8c399c48980c8a92a1d389033e3da069e8d207806baea1303189a6a626d60bc50921c638fa58c5c1c2850b63ce71104d1976efde6da8216c6a6ab2ec9a929c9c9ca4eeb752522e1d651598ea7284253e26bc9ec8b0c8f1c74e7a340dc79d0eacf8d143961e30d89979f3e6b112284e0819495e5e5e4ce22096750dab57af8efb2ea07016ff46e270ac6c5823ad1bbba5bc7febf127f02187c2a4015f580b61455dcc79120ca514941a3cc9d83fa5d37efe1c967fed2b50ab57a858fa092d0da13821294d3867e804b26ddbb698be6fc58a15aaa7a727a66becdfbf3f26e3ed76bb23fe4c595959d4df6985c5aeededed617d7f6d6dedb05099e875c71d77a870de17f802064fa4068003070ea0a6a60681bf334b50c9ebc7a4adbc0c9970c2a9eb86d6ad030a5e878676df00aec9cdc5f46f7c2d6a61525c5c4c6142521e2e2a22a62e84357b84184b59a259885a595989952b57869f8554a45f445c768d8c24aa3f16141484bd7b2cdc7b3af16fdf95f6277e871be1800f12d3b44e6006597f3e93779d0ebc36633a6efff301a8abaf8aeae26d6d6dc2adb2c9d39709c509b1a9380106f39964676727c448b9dd6e59b66c9929df67b5d07c7d7d3d162d5aa492ad8f86d31e72f21d79e1b62db8e1dc795cdeef33ec1c1dbf50e9530eb4eafdd8f4f863501fbb25daadeb3e309a4d714206db9755406192688310eed92fa1282a2a8a5a04ac5811f9ba8070eacc8a6b06de7cf34d5b3ba2c097d7eb8dac3feedd87291d9d982cca3061a2c9603e933e87136dde3e2cfbe2df452d4c688f0919099527c549c2c589510e3d96b21995e2beacac4c56ae5cc9d1a6c5a85cb24a66bedd810ca54193d8d79b6832b48d5803ded27578b3e761d9ee5d501f986e8b847f4931b266e424b9db975540c2c5ececa19aa6a95816dbbadd6e89e5bb637528baae5b5a98a42ab2ff4fd2fbd6095c221252984cb42b47c40711dfc5b6561a74a5a147144e4f9e84650fff82c2841003a1f24c61ac143531ca58d7d7d723272727eab2d6d6d64aa4b93e344d53767230a936e2acf8c4dfca94ba465cadeb106841a7750217b88e31924a0508938be3b96ea713aff5f762d3433ba1eefd028509fb3131b27d5905241ca23d1f275aa353555515d567fdc2a2adad4d62f9bc951d8ca669ead8b163ec9461208525d2555583a94e575061a249a0090c11551181528e61732922d004e8e8ebc59577fe0d8549a2da56a49fb5407142529ceddbb7c7f5fb962f5f1ed3a8282b2b0b85858586ac23b102e5e5e508cc13c223e3c3e3d8af7f836b3527d2f4913b74466f235632a1231c123380cfe1400774a81baec3bc5ffd22aa76d8b56b3785498c1c3d7ad4c55aa0382149466d6dad25a774467f67494949d49fdfb06183610b5d13fa906a9a5ab366cd9832b5b6b6b2238f27289a5be48d6a0f3e003526e9da70ca79a5070894f1cda13f9f498fa6f06a7a1a96fee88751954bd775b9e79ebbd94031e2f3f95809142724d9b0cb192a1b366c88399bacdd048aff7e032325c1983b77aedab76f5f5ceac38ebcb9ef19645ce8473a74388634482c89d77c1ad0eb74e28dde0bd8fc8daf432d5f1af1c50a0a0a183131882bafbc92954071425299975e7a29a1df3f65ca949885821d048a5f944c99324585fbdd77dd7557c465dcbb776fd2f75939f1b6bcf4a73f637a7ada88b5264a5723d6996843195e27ec0b02781d2ebc353080d91bd623e39fee8fb8dedbdbdb2537379706c5205e79e515564212c379eb142512676da5698e5846fd7bf6ecc1bdf7deabe2f99df11043c930756534beeffd50ca7ef1303e34e9124c1a1808101983c2c43f9de3172613256613059cd21c383e7d1a6e79fe00d4ac2b9515fb50ca8daeb9632779db965540613291434f166314ede9c2fe83f0ccbca7781a59b3f3d558819afd0770adc385347d6074ef47e0ae1c5d0dbe1c50c34265f4d48f28c0ab148e7b7b71cbf777442c4c08211427c460eeb8e38ea41b2d452a50962c59a22a2a2a0cbd878a8a0ac34489a6692a92adde919e25643706f6fe5efa4e9cc4a50ad0c24c06eb5f203bfaff9a007d0e275eeff762dd97bf1c557a7a464d08891c8e005210bb4ee91869f49b9a9a223ed3c7282763569d5a35a95ebca9f8c427654ac3515ce99bb83a464febf8d3d22b251011380468570aa7e76763fdf37fa43049b2810ab170dbb20a482a1aa6ecececb83af39d3b779a3e7d136de2ba64420e15ca7b55d5b8d4e188ed3a2218d0349c511a4e5f7a09d6fdf2e77115cf84509c909422d90ca611533c6647926a6b6bf1ed6f7fdbf4115eac89eb9281379ef82d3ea439316960a2f99cc1b527fe3527c3bf55170d63afe6c05f950febfed7b7a0b2ae89a86ebf7cdf560a1342284e48b81c3c78306e8edf2e0205183c4dd88cefd3344d2d59b2c492f5986c4255ea1ae558a50799ca3126e95a44d75140bfe6c4c9fe3edcf8894f40fddddd114fff3d96f7288d0d211427245cb66cd9929c1d59d354676767d49f5fb972a5a1cebaa8a82861597553b56fb7ff613f32067c70229c66d4429abf014d4397aec371fd0db8fe97ffc52dc384509c10123d3367ce543535357189264cb47e64e3c68d5ca81747e4f85bd2f0c73f62465a5a449f0b9631f6bc52786b521a96fdf807a6f41d4208c50909203f3f3f6ce3196bcaf844b174e9d28464931d2d5c12fa50a762f4e4a9677079d71964e80a7a0466cdbf6dd82f52041ada2f5cc0aaaf7f156ad9a2b0ebb1bebe9ec284108a13120d1b376e0cfbbd46a48c4fa4738e572e144dd3d4934f3e99dac2c002b87fffdfb8dae9822bcab5264a17781d1adaf57e646dde84295f892c3d7d4e4e4ecc513b4208c5094985ce1d2781f2c52f7e51c53bcbab51f79e0c5311fa53fb64e09d0e644041938be24444c23a3bc77fdaf0fb4af0ce5557e2ba1f7e3faa721811b523e1535d5dcd4aa038218402c56e1c3b762c25dab86ccf53b8dce98243f4114224dc138807340ddd4e178e0fe8d8f283ef435d3553c5d2df4a4b4bf9e0114271428ce6c489131428492050aebffefaa41fc9cb8b8572aea11e97381d234e1f1e6e3b35fea17e83e9e935b479fbb1fa4bff00b5717dcc75b67efdfa98768e91f0e8eeee6625509c10bbe3f178c276b0b367cf4e3aa796ca119464beb7571f7d1cd76a4ea4e93a7c88ec36440d464d3a07063065f122647ced2b078d2ad7cc993315d71f99cb75d75dc74aa0382176e7b5d75e63678f7336d964b8672b23350df2767d1d3ea09c70eafaa8699cd1794c469e462c0af0290de745c7998c742cfef94fa1a64fbd9df56f1fa64f9fce4aa0382176e7aebbee622518e02cece86c5a5b5b9353703fbd1793fbbc48131f1c116ed251fee91c7d00b9fffe6f11a7a78fb4cf783c1e3e7c063363c60c0a3f8a13429283eaeaeaa8231f761d05cf9d3b77dc72373535d9ee9ee4f809f9ebc11770f9e44941d79a68a243137ddc45b1277b2fe0bacf7c06eaf39f35bd5d57ac5841474a4824030856416a1049de0ed643f2d5cb44f76db7fbf3fefb4ea9ca7b1cd74c9e8449030363ef67e86e2f8a167de8f71afa1d1a4efb0670e1c61bb0a8f890b243ff23a967ab52be7d5905c94f6363230d620a0b9370eec16e4eb3e6d93f6396d389345ff0f99cb1bb743468a2414147b7a6f0eae44958f8c31f58ae1d0821142729c3cd37df4c6192c2c224d9eea5efc9a7c5fbf63bb8141891746d227c1ad0eb74e2cdbe0bf8d8b7be09b5342721f5a194f2d22a11427142c22459733330947e91aaaa2a5b975fbcdef5554f3d856969aea1adc383bb70440deec009d2fa0074285dd0af39717cc08beb366f42dad62f254ca829a5d28e1e3dcace4808c5090987cccc4c9db530484f4f4f5286e0972f5f1ef29e44a4dff237f0427ed1fb0d8d98aa39c3cc003bb8a5d8a70167a0a3e7eaab71fd433b137e1bd9d9d9aaa0a0800f1a21a1443cab20f949e5c5b09cce89ac4eac7edfafdef959f15557e332c7606e9390420b1aa07428198ca8f48b8656e8d8b8fbb750b96b95ddfb27e1fa9da46f5f560121173970e040d21b3dbbde9f54d5497b6d0da62b35ae30191e790db9fd3e8713affbfab1febead961226feb6d8b973271f3c42284e48aa10cda8f4539ffa544a8ec6cacbcb2d2f5ada9e790653748153262ea61ade3a0c747afba02d5d02e7b7bf65c9fbfbf6b7bfcd084084949595b112284e889de9eaea0acb4197979727d57d47b37d3a95c2c481f7ba73e74eac59b3c6d2f72eaf1f97979f3b88e92e17a0268e9a880874a5e1b4069c99360dab1efeb96dda834ccc473ef211564292e3641524375e6f78bb16adee9c2225d2edd3a9e81cec74cffdbffd1d2e7fff2c26a5a7430f185329a520226392ae39a070d6a1e155dd87dbbebd1dea9aab2c7faf25252558b76e1d8d5618cc9c3993622ec961032739a9b818369ae91c8e5cad8b747649c5c65b7175571726290714f4e1f524c3ed17204efc87fabd36e0c5759fbf0bb37ffc3d95cc7d3715e1f39a026dcc2a20c9462467c5e4e5e5d1d0591cef7307e1ebec4406d4e0793932386d33c2a90764841dd03474e90348bff17a5b09133a5d422ec2691d827dfbf625cdbd443af27ce08107e80c2c8c9c3efb7cc9673e874ca70b0edd17f490bf11ef57c079a57062f2646cfee98f804539b68c0a308242521d464e083efbd9cfa6e47d1f3b768c8d6f758a4bb7f43635e3528703026d282b2c822660133598dfe4784f0fd6fdcb83508b72283c93906ddbb6b11252003ebc494e3823b0dada5a2c59b244a5c2bd8e1ea1b287589b96db3f256868c0f480a46bfe45b023da52800b4e0d6f7b0770d5965b31eb377929d79f536a54cd6737f9db985540924198d0b8251f525925ef3435628672c01170c05f3061a2009c15a0f39aab70d5ce1deca3494c7171312b2105e09a1392144432cae4748e3d7879cfd398aaeb48834024f45a93014d43af02daa1e3633f7c086ae607e8d49398dcdc5cb66f2a0c2059052495d8b76f1faebffe7a1a378b23afbf216fe4e7e33257da048b6075f43934bce6edc7daad5f865abb3aa9da96d113427142928e70b3c3da9d48a22677dd75178dbd0d38fff86f30edec594c828c1126fec5b0a206a326eff8fa3063d50a4c7a60db41d61c211427c4e2bcfefaebac04623ba4f33d697cfe05cc76a421dd373655bd7fcdc90004e705e8be6c2a16fcf44750d3a7de9e94469ad19361f2f2f258091427c4ee389dc9bfa4e8f0e1c361474d68e4edc1b9fd7f84afe35d5c2202c738c7e8f4399c68830f1bbef36f50d75ccd93a453006e23a6382149407676f684ef89249baa1559bf7e7d58efabafaf6787b00172fafde7eb7eff3f989e3e69c2846b277b2fe0c35ff83cd4a7ff968e3b45686c6c6425a4087ca8939870d662d8794416c95a138e3c6d224efee780fce52bff848f4e9e824bfbbd63c4892640bf43c3299f0fde8f7e1839052f283ed329349ae6739c3a6dcd2a20c94e4f4f0f2bc126b4fef677c872b890eef34146f9a1c17c263ace3b34bc3ee5122cf8c1f753ae7e98e383509c1092244c993285a32d1b20a515f24e63136640c1a9eb6392adf934a0d7e9c41b172ee08e7ffd17a8450b52ae5d99e383509c1062612259084becc12bfb7e8fa94a8363d46cb3520a9a007dca8137bdfdb87ecbadd0fefe9e9475d26565652979dfadadad7c48284e08b136e12e84e51cb53d90e65679e585bf60aad301a8915b7444043ea5e10c745cb8f61a5cf7fdefa5745dad5dbb3625fbf4b973e7f8a0509c9054a0a4a424a9ef8f6b4d6cd456bb76e1caf3e7718908f4516649a0a11fc0db22d8c0f4f4005273edc9b265cb38d0a03821c9406d6d2d3c1e4fc8bfcf9c39d396f715ee8e05ae35b107f24ea734bef017cc72a50f9f3c1c489f53c36bbe7e6cfaca03506b56b24dc1b52724f9e1c17f49cce8d386ebebeb65fefcf9c33fcf99332769efbda6a6861dc0269c3ff02c7ca74e23cde90464302dbd1a929f0e2578a7bf1793d7ad84f6bfbe41874c488ac0c8490a919393a3344d1bf14ad67b5dba74291d990d90d3679fafdcfb0c3e909e0e870c464dfcc2441c0a5d50e8c99c81a53ff9312b6bb4f14ea1f5544545456c708a1342ac4baa27a14a3a0e156ef1bdf20a2e510a82c19d3a9a000ed1d10d8556e858fb9d7f839a7515c5660ab371e346b63fc509211c5192f8d0f4c46f708dd30597ae4357833b737405f46b0a27bc7d5874cf17a03e7527db937d9d509c104288f94869b974b5be82e90a43533a3a4453e8d714def5092e997713aefcde0e3adf14e7e0c183ac048a1342ac0ba774928b979eda8b4b7d3ea4e932bcde4487e09c02deb9f412e43cf4102b290c0a0b0b93fafe962c59c246a63821c4de232886b9ed813434cb1b05059896963e78fa303488d220d0f0566f2f72fff7b7a01666b32dc360f3e6cd495d4f3367ce643fa03821c4ba6cd9b285959024743eb907d37a2e207d2816a609d0af39f186cf8b9b3e75279c7f770f1d52046cdbb62d29ef6bbc3c4d24b9a10120b620dc291d464eac8f74bc2b7f5abf191f397f01d39542ba6f704ae78443e1e475d760f381fd07d5f4a9b7b3a68c7f3e6c3982e6339d9230724268c4485c39fffa1b58f6c93be1ba791efe3a290daf4dbd14af5e3a056d974dc5e6ffdc010a93e8fa7e535353d2ddd7891327d8b88410eb525a5a2abaae8ffb622dc56784ee7fdd77df7da2ebba1415154dd836e1be5e697959fee19e7b0cbbde78af868686a0bf6f6b6b1bfe7f5555d588bf555555891dda26595e7ce252178e34892d1de4912347b066cd1a464de2c0a14387e4faebaf475656162b2380aaaa2a2c5fbedc32fdaeb9b9596ebae9a6a48b08b1a7519c1042c8b000642dd8cf8936343448767636eb95509c1042284a88351c6932b51f85498a3f4fac0242887f4dcf78efa9acac1cf1f3ce9d3bc7bd66676767c8bf6d58b4087f6d6d0500d4d7d753d819444545053b33490aa84c094961bababa64dab469237e57565686f4f4742c5bb6cc70fb305072441cd75d83cad252acb827742e135dd7bd4383a71103a89e9e1e747474e0fcf9f3e8eded85d7eb0d6ed8d4e0a545044a29f87c3eac5ab50a0090979717765e10ff0e98505325555555d8b76f1feebefb6ee4e4e45862c49f2cd113464e084962fcabde77ecd8214f3df5d4885d0aac1df68d78ed8e90d7df90aa8f7d5c6a96ad92b25b3e26f2fa9b62d53af0d7c3ae5dbbc2da51525b5b2b00b07fff7e4bec34e12e1dc2c809b185030ae77d252525282b2bc3f6eddbd1d4d484bebe3e2c59b284a39714e91766b6b39c7c5bf6afdf88ac0b7db8c4e1c04b7d17b0f807dfc335f7de93e808831ecc06f6f4f420232323e2eb959494e0e4c993b8ebaebbd0d4d484050b16282b3ff38c9a104bf7015601018075ebd661fbf6ed000643d8fec3b6468f68dc6ef7f0ff6b6a6a38cab119c1a266663b82337ff8032e3f7f01d35d2e747807b0e8939f4cb830f1eba620bf1b884698f89fa19a9a1a00c0850b17a0ebba949595c5fdf9282e2e66472784587f849cc857656525058c85fac273cf3d17d7d0b99cea7affc5556be5a5eb6e94a3b33f2465eb3689747659764aa7b8b8d8f067201189db38a543ec0e436729e090ac5ec6d2d252ac5dbb1600744dd31c6c35636969699177de7907e5e5e5c3d1b17885cd65f7d372e85bff8aacc993f1a63e804d7b9f865a69dc425b5dd7252f2f0f3366cc4057571766cc9831e2ff00f0873ffc01ab56adc217bff8c511df5b5555258b172f36edde8f1e3d8a79f3e6c52d4265b7e73e149cd22114271427b6e1c89123c8ccccc4dcb973d9676368ff781a7ee9b9b0ad61cb277ea58ebd89f706fab1f19bdf847af09f94c1f7e71bb26312708f0e2b3e1b6eb71b53a74e853f8babd7ebc5f9f3e7316dda34c3db85e284d81dae3949720e1f3e9c14f7b166cd1acc99336744b83a11f3f976a2adad2d61c20400505ef9abb3afbd863e6f3fa6ad5f0f6cfd7f779ae0c81cda200effaba5a525ace982d6a13c2b7eeaeaea622a8bc7e319f7ef2b56ac40607a7997cb05ff366effae9f549fdea030217ed811929cbebe3e71b95c34662986aeebd2dada8a3973e6e0d8b163b8fefaebe35a4f72eefc7fd67cf5ebdbcfe6176272e6e55871f079a82baf884b19dadbdbe5d5575f85d3e984884044e0743ab17af56a352a9221cb962d4bcafe6d5771c3e79990147354e1bcf2f3f3c79cc2ca8573d68c88e8ba2e15151541ebc20af524d575f2cc8d7365ef0d1f1579f6a0d8e4b997ea52390000200049444154f0863aa93851afd1d12f339e7babbcf2f3f31909252495181d2e8ed400eaba2ee5e5e552505020baaecbd6ad5b294e2c283401c09f682fd1f5f3d6035f97a7675d27c7ffe37b22e7ceffa71dead20887ee3f06c00afd9cbb740821b68e9c1c3a74488cf88e78475d52ad1d271ad59796960ee7a1496439e5ed0ed937e766f1dcf67191f7cfff5f8b3e13de68c54960c4aabbbb3be4fb8c8ac2b4b7b747d59efe081bc50921c496e2245e8641d775a9aeae96c3870fd39819244e6a6b6b871da43f8749a2cbd9fa5f0fcb23736e1269382a367b2606a2e96fcdcdcdc3ef09cc23d3d5d59550c7cdc8092184e2c4c0b2363737cb912347c62d6faaeed4193d32f7ff2e3f3fdf12ed289d5df2a325cbe5f41ffe68e9f6f1783c41a737a3793682453a8c5ebb92ece284569a108a9331af1d3b7688ddca9daa6d59575717722ac20ae57bebb12724ff2b5fb37cfbecddbb774287e976bbc72c341e1df5ebe8e8b0ac03f7af11a33821843072427112f77a292828b04c7decfbcebf8b9c793f3f999e097f54ca6e0e3c70ba89c284d80dee294f218736dedfad985f60bc32a7723e84c07aa9ababc3e2c58bf91c1bd8b7f6ecd9837befbd574df4ccb8dd6ef4f7f763f2e4c9c8c9c981cbe582dbed86d3e91c3e38d36822edf77670fecc6d4282e1641510bbd1d4d4c44a885298e8baee1d1a94387a7a7ad0d6d6069fcf87818101e8ba0eafd70b91417fe67038a0691a9c4e2766cc9881e9d3a7fb339afa0088a669b6cceed7dddd2dc11ca4df91df7df7ddb8f1c61b833af5c6c646e4e4e44c58e7353535b270e14276520a1342c878ecd8b1c376e1d5f1f2b3a4f2883f70316cb89fd9b163874c94efc6a857e056db80ff7b87ca3260953a0cb57627541e9f58bf231153209cd22184d8c2a9857add77df7d62a732a772bb3536368a5d9d91aeeb525f5f2f56abd309ea2e22416566be9f6412271e8f87e284848eaab10a528313274e8cfbf7bcbc3c569205c548b0f7043b2bc9eaa2cdebf542d334a5699a0a675ac4524652d39cc0c885b18191c8fdfbf78f68b3c58b179b5696969696a471e8cb962de3940e09093b478a39be098cb0b24b799361ae3a5631d1d4d484ecec6c4bddd3e83285d34ebaaeeb06db221d836b629cc1ea3c927af3973f11c2efc1071fc44f7ef213783c1e041e501849dfb7aa602d2b2bc3dab56be97f08c50999d85079bd5ea4a7a72b3b94d78ee244d775292c2cc4c68d1b93b58b89a669da38f7efadadad752e5ab4c8163713d8c7e2ede4cbcacab07af5ea31bfefe9e9c19429536c2f4eb810964cd8475805a9c3b66ddbc6fd7bb0e9021283a716e90fcc140a20e984494d4dcdf0744d30611298340e806d84c9810307e2eae003ea50699aa682091300c8c8c8b07d9f79f2c927691cc88450bda610e118592b8d68ec1639a9aaaa3275bd8155282f2fc7aa55ab82b641b22c568e753aa7a7a7078d8d8db870e10272737347d455454585ac5cb952457b6dbb4feb306a42c281794ec8301e8f272984573c8d5f4b4b8bcc99332755ba882fd83a8e641225c0e0f4a699cef7d24b2f05b7d112323e54b029869d16c586133909f69ea6a626381c0edc7cf3cd2a51f54851921a3cf9e493b8e79e7bc27a6e8c4acc66e7c809a32684e284446dacac624026122713dd4b7575b5a1db15755d97cece4e646666daa2ad8f1d3b86acac2c1c39720473e6cc19b7dc274e9cc0c99327f1fefbef63d3a64d387cf830366dda14b4ee522c5a3406afd70b97cb35ee73a2ebba0f26ade9a33821142784e2c4c6e2c4a87b29292991356bd6d8aa9dcd6ac3548d949c387102b367cf5656a81fbb8a130a1312517f6115a45883278981086674354d53454545861a675dd7e3294c7ca3776d047b8d7781caca4a53da3855d38dfbeb7c3c61d2d7d7674ae662ff771f3e7c78f8773c578a509c90a4a5b1b171dcbff7f5f559da091d3b760c6565654145d7c68d1b0d71ccf174c601c2c3196d99aaaaaaa0699a5ab972a5a1c224d544c9d1a347118e100ccc086bc616fcc045b99b366d523b77ee0400cb25dd4bb54111a1382126f2e8a38f8efb77abe73bc9caca427f7f7fc8bff7f4f4c4ec90ad66b4c72bd3d1a347b17cf9728a1203da223b3b7bdc7a6c6e6e96ddbb779b5e2fa393216eddba95868b509c90e4e60b5ff882edef21373737e4dfa20d7debba2eb5b5b5a63b9ed6d65660680a279cf7efd8b1436271a8142563d9b66d1bcacbcbc38e92b8dd6ed1755d6ebae926dc7df7dd712faf5d1661c722c00909849d2645b1c396e2f1ca18ec7c144dd394dbed96c07348c2bd977839e48a8a0aac5ebdda90058d46b751aa249133225a6526050505b8e5965bd4a8b20c9f3f64a705b15555558647f5486ac0c80909cae1c3872d3d7a5eb06041d0eca4d10893f6f6f6b8dcabdbedb6a43089c769ba7613ee8974ead3a64d0bd6de51d9ea442fa0a530211427c450d6af5f6f0b3f32de1fcbcbcbc37244b366cd8a4b6157ac58e13f6bc61b4eb9cc1426baaefb527507ced0794763da60c78e1d96a80fa58cf3e7765d404b08c549aa36bc0de6814b4b4bc773ae5e4dd31ca1eec3e3f160cd9a3596bbc7050b16008053d775292a2a125dd7a5b4b4748453345398545555f9bf2b659ffd2187ed0cac6f5dd765fbf6ed9628df8c19336863087d14ab8084a2b2b232a1a3c8f1467d9d9d9dce504650d334b562c50acbac3309c5ba75eb0000fe1368c78b64ecdcb9d39f78cea70f22baae4b535393783c1e3972e488046e6fd5755d76edda35e6779cba19d9fe568c1c65656585fc5b41414144f797a87b603e16122b54b6296e9cad3efa89248ad0d0d020975e7a294e9d3a85a54b978e5b6e11e91791b8ec99d6344d793c1e79e2892726dcc64d08005dd33447b067c1ed7663d5aa55613d93b5b5b59293939398512fa32624d63ec42a48e1c6b78101a9afaf9ff03d0d0d0d525b5b2bd9d9d9c8cacac2e2c58ba1ebba747575851436bffad5afe2224cf2f2f20000cb962d53bffef5afc76c59edecec64473491c2c24214161662cf9e3d767a2e1dc17ebf6ddb36f4f5f5857d9d440913a594973d8fc4dc8f5805293e44b378f4248cf375f4f1fa71b0b2075ed37f464f5959d9f0f48a59f80fe20b56aec2c242f1f97c08dc94a194c2a449933069d2244c9b362d30dcaf87726000505a5a2a66df8b0d18d034cd35baddf3f2f2b06ddb365b0e1afcfdd6ea5ba11935211427c47471b26ddb363cf6d863561627118babd19ff1ff3ddec6dc2f56429d9e1c78b2edd1a34771fefc79f4f6f662eedcb9b64eca9568e768f51d4aa1caffe28b2fcae6cd9bc376febb77ef9678278ca330211427246e02c5aa9193aaaa2a2c5dba744c592b2a2a64f9f2e59617272431ced1aee2e41bdff886fce4273fb174e484e28418d6975805642212b96b67bc3519a3cfd7f11bc66049ac8c30ba43d7170078e9a597fc75c30e6221a7aeebbadedcdc2cf1ccac6b8220f705fbfd4f7ef213949494d85a18124271420c637474229ecc9c3933a4c1ebeded0dfafb73e7ce45f41d4d4d4d0200353535e118604dd33475f3cd372bff29c07ea35c5656869e9e9e945ce41a6ce1f2fdf7df3fe2e7c2c2421c3b762cac7a8e8686860601a06ebae9a6e1c84177777750a172f4e8515bda650be740d169290921668cd664bc9795cb36ba8c1e8f2764d9837d6ec8a985fa1e5f94651e082c476d6dadd4d6d64ab8f762c6cb5f8648cab177efde31bf2b2e2e165dd7e591471e1953c7c1ea7df4ef1275ffa3dbc87f989fd55ea1769945f22cfa4559a2ea961042e222000a0b0b136680b66edd1a91911ccf7086635c034f010e72dd8158efa7abab4b0e1f3e3cfcddbb77ef1e5196c0fb2d282890828202292a2a92b2b232a9acac949a9a1a69686890969696d1f7e14d449f09a75e755d97fcfcfc848a938e8e0e89a4cf27ea55555515529c182de8294c8855e11c2109dbf0256a4e3956031858ee50d76a6a6a1a7398a0ffbdfebf8dde82cc3e137e9f09dc069b688766f505d075757558bc78f1e8bee8f5783cce70321fc7f3deeaebebb168d122fa1162fc73ca2a207676b613953bdcfbcacece1e33fde05f04eb172d870f1f1e16322472cacaca6c23aa1249883553ced1276e279abcbc3c0a1342487c8cb65543b8e14c1b04be02a76622bdc7c0b369467fbebdbd5dfc0b2dd95fc6afbff2f2720180fcfc7cffef74ff7a9544bf4a4a4a123ac514e973d6d5d5258f3cf248587d4e44fa399d43ec0e552f8968546985a99dcece4e646666e2c48913983d7bb632f2fe42d1d9d939eece21f695913436362227274705cbc66b46598e1e3d8a79f3e6c1ebf5c2e50aef64022b4c3185fb9c793c1e59b2648965f2b854565662e5ca957c1e08c509b18638499440094cac16cbf7c762b8fda2289122cdaafd65e7ce9dd8b46913962d5b166a4d87ae699ac348c719aa0d9e7bee39d9b2658badebd52fee02ebd94ac9d7d8ff0921097138c918ce353aaceddf4a9beafd2560da66ccd6eb96961631abfee3d5ce899edaf1efca4a543f1fbd1d9d1692c4032e8825244afc07c8a5fa1cfce6cd9bd55052b381a1e8c8f096e6b973e71a3ec2eee9e91977e4eef178922a2a3567ce1c2ec226142784846b34ed86d7eb35bd5e42e5d4b0631bebba2e8d8d8dc3f774df7df7851c4dcf9b370f009c437dc36966c42d232363dc728fded5a2699a0a96c1d62c4a4a4a028f3b30e459bbe1861bc27abfd9fd8ebb7348bc6047235109103bce39a7fa11f2baae7bbd5eafb3bebe3ea147121884689aa685d3c6f1ce6b32d1419366f621b3ee31581e20424c7d8e58052418fe83ed42c1b9e7c8a310478e1c312c82a0ebba4fd775e9ebeb93bebebe31518ec0f4e56eb7dbbf56c0e972b9924198843db03a73e68ced85a815ce6acacecee6d939c47a0f38495da76a878840240edd4a82bcaeae0e0b172ed4354d738c2aa7ded9d9a95a5b5bb166cd1a76c430fbe0e8feeaf178303aa3aa599185899e859a9a1a59b870a1a9822770479b1d4417211427242adc6eb74c9495d28602c5b2119fa6a626646767b3e319244e82e5a631bafddd6e3756ad5aa562ed8bc78e1dc3d9b367e1f57a71e1c2054c9a3409e1e63549b4f02284e28458ce99db519c8473d60eb19740196fad89590edc6a7ddf8cbe4c614212f65cb30ac878343737dba29c55555561afe7f06f750d75fa6bacc6dc6fd08b8b8b01000f3ef8203b9201747676862d384c76aabad59c368f5320c9065531896a4466a11d281249b9cc3c1d37dcd4e2adadade8e8e8c0ba75ebd8b9a2ace3f1da6ebc76686969913973e6245d248151139274cf3aab80843b520c7c25ba40bb76ed8a7ae74b63632380c15372f7eddb67487926dadd1468f0e7ce9dab366cd8a046d7e9782f0003c0e0ee93868606545454a0b0b070ccf50f1e3c98121d325452b263c78e8dfbb9688449555515acd2efe32d020921844c4038a7ad8633c21cfd3e23d27abbdd6ecb84d5dbdada245952b8874a6cb77bf7eeb8a4b63f7cf8b0e5a74b78da3049469cac0262759a9b9be5c081031099d86ee6e7e7cbe6cd9be33eeaebededb54c7d65656525653f08dc7973f7dd774fe8b047ff2ed2b4f6a9183d60c4845805764462e911a119063670dd49b8df15b8d6e1f0e1c3c8cdcdb5ac614fe4e8b7aaaa0acb972f1f51aff9f9f9d8bc79b3a18ed3cc7b4cd5edf135353558ba74297d0221848432b6e3bd1a1a1a86c3cfc1fe5e5f5f3faeb1aeadadf5bf77209cef6c6a6a9a709ac04ae1ff449fa23baa2c3ea3ca64f63d26e3b3c2e91c6257b8209658c6c886b31db8a4a4040b162c185e9c186c01e4fcf9f3c7fdaecaca4a0040535393c37fa0dd78ef1f6f3ac9bf50323737d73223ce8a8a8af81b9220d1865dbb7609002da04df5781ec017aa7f848af8a42a0f3ef820a7730821647414a3a8a868dc11dd73cf3d1752b4545757473c0a8c7444d9d2d262ab516553539355a22663de535a5a1ad5f5cbcacac28a9cb4b7b78f17e5f2316ac28809b10754cb24618635da1179b8d7322a4368737333e6cf9faf92ad6ecd8a9c949595c9ca952bc7bce7e8d1a3c8cece5646ac258a36cfc950023e6734fdcc6a8473bc8411cf17218980d33a2421a3bd7146fdc346d32a86f3d9679fb5d7431d41fe943073ac8c4b6b6beb889f83091300e8e8e888d989fa4f600ef5be3076e424c50ec58282020a1342088955907477778f1b5adeb163879496964a2cdf31fab57fff7e09e77dc992ef221ea232dca981175f7c51fc8b61636d8386860619123741bfb7bdbd7df4ef7d11de872fd9da63a2575b5b1ba7730821a949a8f52081af212716b3a10cd769729e3e3ee264288a11f43d46b541494949c86b46721f766b8bd1f51ae92b96410021f182613d628a230be77d468795c35d7b62a443eae9e9c194295314db35e41a1f9fa6690e33ea7ebc3528c1cab27fff7eb9f3ce3b47fcceed7663d5aa552995d784d339c40e70cd09317c741d8e714ca481cccbcb33ec5a191919c3f79dea27c3eeddbb37d80191a60b93701df8686102c076c2e4d0a1431426841012892819ef55555525892acb238f3c32e6bbbbbaba4cdf5e1bab23b16b5bfbd7fb049e3b148f6461d1246d4bd6fa0fe71920849094152813656b359a969696889d9aff330199638519488353565696d0c3ffc62bdb8e1d3b4266f64d8636e11a29420831d8785aa11ce17e763c8163d42b3f3f5feaebeb6528ef866d282c2c4ca838a9acac9c50648e6e73ff710781afdadada94899ad0321142284e42bcbababa6c23508c1cb19af50ab5357bd7ae5d315ffbbefbee938a8a8ae12da7664dcf18ed6cfd651efdde60d7282f2f4f89a809ad12b12b5c1c450c37a2a1fe16cfc57891648d8d94bebe3e71b95c6cec0412ac1dbbbbbb25232323eacf5b9548eecbaef74808c509a14089d31666125f8132944c6dc21d889d9d9d98397366521f474061426cff7cb30a483c46b58970e4a1ca31deba8568bf27d4abaeae6e845324c63aedbaba3a09680747389fa33021c4fab01393441855d1342d2ec2b8a1a141b2b3b32d65c0ababab65d1a245ec241613cec9204e284c08c509211350505020b9b9b90937a2c10c7c5e5e1e1e78e001cbf4ffdada5ac9c9c9414d4d0d5a5b5b71e38d37e2d5575f0500dc78e38d3875ea14b66cd9c24e152595959558b972a5adec5d5b5b9b6465655198108a1342e239f24bb440292c2cc4e6cd9b55b2d6f9b163c770f6ec599c3f7f1efdfdfd58bf7e3d82d5c1c68d1b63fece60d7292a2ac294295390919181acac2c646464987a944032396e464c0821240e8636d15b1db9d5d27e7d235513df45baed9bbd8830724288c12341afd78bf4f47495c83270d4997842ad0d8a06bbb76724a28a7d971042a218f1592d7ab177efdea0e7c0b0f588559f2146fd0821c404a31af09effbfbd7bf76ddb8803384e3171501469870241d776cb1014288aa681d120435114e81f502043573babff06a17f42966e361a085d32b943a3976dc97acb7aa1da880e4107a14526a3811d98d789ea99e69b478a94be1f80802cf3793cf17e3c1eefaeb2dabdbd61185cecb152dd6e97c00400d2bcdbb3e69d4c26991d7f873389acff8e9acd26f9140092b8db731a886d050d64af085090157ee316e571a042005899e170e85ac0dbdb77542a15df21ecd30c109cf6bddd6e530820553cc60180042fac8bc542f80500412ecad56a95578c4160425e04003517d7b8ffb7a65eaf4780828d0e4c788b0c0056149c785da42f2e2e52bb38bf7cf9f2c6f68510979c6124a1d168d0f01500d20a4e4e4f4f5ddb9578b5e75875b576b55abdb1ddc964420101e50cc370cdebe3f1983c0768f4100b05c149d079fd7ab3745b57a15078572814eea47d0cf4be09d58410974288ada8bf1180e0045018a004bde8a61d28109820c5dfc995a6693a810910e066962440ec4ca4eb85bffefa2b766062cddbe9741c8308d56d40084c9095eb2d790eb88e1f0494aad7eb626b6b4b334d537bf0e081f6d1471f45ca635eb5312a2ee44eebfff7df7fb5bb77eff29b80525e352604250090cf0bbbf2de32796d18abcec3e43900c8d1053b685f2847474742d576391b4882db5b39a40c00e42c30314d53b45aad1b17f09d9d9dd8177a950585699aa2d3e950d0c051abd52230018075094caca95eaf07ee4d7615818935351a0d0a1cf8e635fa2f01800c9bcfe78eddd4070d1eeccbfb051a9d4e47e91decf9f93977c44825080600ace0e23d9fcf3d07020c5b08d897393939511e4830401b82e60dc330c8130090c70bb8fcbf5eaf17aab0f70a12badd6ea2810977c9881a5803007274118f72813f3e3e1641dbb1c4d9ef76bbedb8aebdbd3d6a513698c3984c57a40a00e43c38910b72d56fe258d3683452566352a9549436d4c57a06da00809c07274ed360301071d75d2c1613ab312140d96ce3f198730c009b16a0a858bfea7d0d32bf53ff16bc6ebc9e7957f5584f008015ebf7fb8e4189d363932c144461962b97cb813a96433e039366b3c9b90400ac3630895a0b93f5e00bc906a90000245218f5fbfd44fa452195b3efecec8cf305ac884e1200ffabd56ad70aa22fbffc32d690f6baae17745d2fb8052da47836954a25f1db6fbfb99e3f00005221d76cb4db6de581c36c36bb5183321c0e095232980748090040e68293b4b6234fb3d98c427145e4a10e480d0040e6029352a924d2de2663b2acf6dc930a00804c072769375c3d38386030410000102e484963cc94f3f373c76dd3470a0000701dd5388dda8ca47ace0500006be0cd9b37990b524aa512810a0000585dbb14ebd5666a53000080a36ab52a0cc3483d5070abc5d9f4f331180cc470382448030040d3dc6b537abd5e6a7da5b8fdffd5ab5762ddd33d89cef2000058abc2723c1e5f0b520e0e0ec4d1d19158d5fec8535e47d3a5b6080080040ad3340bd6d168e4b97d7b0095b5f49bcfe7c2344d51afd7afed33390b00800483152b4048a20f13793bfbfbfbc22f58729a1a8d46a2818b699aa252a908d334855bff2ed49000009052ad40b95c762d8cabd56aec02d9ab60b78222b95622ceb4bbbb2b767676c4eeeeaee77c8bc522d0fac8210000acd8743af52db0ad41e9c20427618319a7e0c1fef847f5c4d9070020270683c1f2914fd8c2bd56aba55af0fbd58894cbe5b57e6b0800808d66b503f11a35d9e9b18bf5b9d3e904aad178f9f2e5f2d1d3dede5ea065e4b62d9c290000b0747a7a1afb718bdc40d50a3a6ab59a383f3f27f00000000000000000000000000000009b69369b2d1b52763a1de58d2ae5d759f39226ed765b4ca7531a98e6d83abf99c45b5700d6fe029e44075c4eeb7cfefcb9c8539aecefef0bb7635ac5fe847d1ba7d96c66262074db77eb15ea2482cb3c17e06fdebc71dc776b5c238213006bcd6ddc9428ebeaf7fbb9e97154eeddd52ba872fa5e0871b9aae031ec728bc54264219dbdd2d3300c91c436f3fa6ab53c7c828abc002441270990a4bb77ef16344dd3745d2fe8babefc1ca540f8e28b2f6e7cdfed76b5a8eb4cc3d75f7fed5b456e7fbc2384d85a456dc39f7ffe197a1df7eeddcb4c5ad76ab5655e91bf5f2c164a0313ebf3fbefbf9f68d0d5ebf5841c34a85af73ffffcb3fc5cafd797ebcdea6f080032ebf5ebd7b9b9a33b3b3b73add991ff7efdfab548eaae35e83aa2d63064e50e3b48cd49bbdd16aad22f8dda05b7610b46a3914822cd9cf22635270036ced1d151a4c2224f174fb7c2c56dffe541f6a23694b596ef76bb81d348de9f6eb7bb96c149d8b17de4c11857119c58dbb182aa24b6e5943ff374030000891426aa2ea8e57239700dc18b172f945d741b8d866f611e66545ef9fb56ab157a3fe576047edb90bf93df74aad56a81b72b8fb9b3cafc542c1697fb311c0e95d44479d5bad8d3791d7e8b8c180d80e024c60530eac554f54537ca85dc6f99b08f212a95ca8df5c88f65e482da2b0de4efc304276917642727278e419bbc1f72fb89a885edf1f1b167deeaf57aa917e22ab6532a9544d04755042700363228314d53c87d9fb8cdef572b12e4823a180c127f8539e86303afb61d610b07d33485dbeba061d61df76d1dbf65bc0ac5b0db1a8fc7c22f9870dabfbdbdbdc0dbb7d2545e5e7ecc263f7e8b735c61ce73dc57e4add79ebdde2eb2e5832bae5800362e30314d53f8b53b89d266224e23c628c150d860226890e0d72f47dc47156e85b85ffac9dfbd78f12254ad95dffe9d9d9d097b90e0d250f34ad57179710b42e4efecb553f6e5fdd2e5f0f03050dad95f4777627ffd3c4c2357fbb152730260ed3d7ffedcb130dfd9d9097417a7ea2edea9ea3fecf6eceb08b29cdca6c35e6044adc188da36c55aaed168042ec46d8d78afa2a4b9df7cd6232aa72040d3346d32992cffdfeff7039d3ff9bb62b118ab8d935cd31536188e93bfc3cee7b65cd0c789f2dfcd66930005c0fad79a04a97d4863b25f7487c3e1b5ff57abd540ebf12b40bc96b51a71ca8f658216e42ad240aeb50ad316268df3b8bbbb1b6b79a7fdf30a848304375ee73689f3e337158b45213704f69aac473a51ce1f572f006bc9eade5c9e0e0f0f33159cd86b36c21684f2dff3f93c74e1bd8ae0e4f8f878b9fefdfd7d1126c0ca4a90e934599d96d9f7cfaf86296c41bdeae04475f04670822ca2875824667b7bfbc677df7fff7da6f6f1e38f3f8edc2ba6fd027efffe7d3930f35dbe5aadaefcf87ffcf1c7e5e79f7efae9fac541ead5d7ceea8d354b4aa592e3f78f1e3d52ba9de9742ae7812b7b9afdfaebaffcf801821320e68f40d70b83c140c97aaccf9797fec3e37cf0c107b1b6a1da77df7de7f8fde9e9e9f2b3d5de4688ecdd58fff0c30fcbcfad562bb1edbc7dfb563e1fb7ecff7ffaf429ddc0030427c872a1dfe97472b1af0f1f3e8c55a0c805b8a669daad5bb77277be4cd374fcfef1e3c7056b0ca3274f9e689aa6691f7ef861e6f6ffabafbe5a7ebe7dfb762adb59451009109c00316d6f6f2f1f0d743a9d4c5fb487c361e4651f3f7e7cedb8ac42dc8b6118cbcf474747810bb6b86928d77ac835460f1f3e0c5c207ff2c92799ce770f1e3c487c1bcf9e3df33ab7b4d900084e90f98ca6eb85edededc8a312abf2eedd3bb75a03c7518f83f8e38f3f222df7f4e9d3e5e7f7de7b2fece266d434901f39ddb973c737e8716a1c19a7ad4e1ae4118393ca6f3ffffcb3eb7a3ffdf4d395a781bd0d11002048e91af2cd081593bdb32a4dd3b47ebfeffb3691dc0bacfd75578fe3bb72db0f7bc75ab3d92c767f1c51d2e0e2e2c2b1ef178b3d3d2e2e2eaecdfbead52b11f73cca6314cd66b3c879c0e96d1d9579519ed7ad5760392fc54d97a8f3552a95d04317f0a60e00e4283872638de0baae69238f6c4ca1158c531f2df69e6f01003964f5286b050f71030f793d6e052e85af772d4f986ed4e5f43f3d3d15d639f51ad765dd825ba7fc64e53deae3d07500000315494441547bde0400ac714d46d0f5cce7738213175e8f68927a44b2ee018adf3cfcea012047177a6be0b7c964a23438f19a8733f07fdac8dd9e8719d5d79ebe9b50531026bf51730200f0656fe089eb052aa910ce3a3ebe0200000000000000009976787828aad56aea8f287676766ef4d31187d5c897330a00408ed5ebf565a3cec160905ac12e37c255b1be72b94c83670000d6815befad696d535520c1db580000ac59609256c13e1a8d946f8f7e450000c898939313d16c364315c66e05faefbfff9e68a19e44104170020040c28216ae71bb9d1f8fc7c2344d21f7a49bd6b159d3c1c181d2c73a8661109c0000a0d270380c7cf76f9fd79a168b45e482596e589a467092641041900200400285b7a6695aabd5ba51c82e160be5e30625153458dde2bb8d3f93446021afdf300c0214000054ddf1db072fb4cfd3ebf54207284eeb721a2431a960cb6f3b41f7c12d4da6d329ed4f0000501990b815ecf2b4bfbf2ffc46c95531a90c4cacf5c97d9cf80527c56271e5c70100c0c6b10ae0e17078ad10edf7fba904206ed3d9d999eb2397d96c260cc310866108ab61ad3c8fdcb99b5b0d8753e0203768cd4a900500c0c6d69af8d522a43d4d261311b446a4dd6e2fe7b58215799acfe7818293e3e363e5c711a7813080e4e92401902df2abbf9d4ee7e68f56d70b79388eadadade5e7cf3efbecc6ffefdfbfaf05a9c178f2e449a15eaf2bddb77bf7ee91d1800cbb4d1200d9f2f6eddbe5e7478f1e39d65264ee2ec72760d275bd1074bf47a3d18defbef9e69b82cae3fefbefbfc968408651730264ccb7df7e7b23185987b612baae179c020fbbcf3fff9c4c00109c00c89267cf9e6576df2e2f2f232d670556f6c023e8232ab97d8a0a3cd601002042419ef664dfb6d3be44399ef3f3f36b8d649d463bf6ea6a3fca71c86f0669daf546b59d4e87b775808c2b900440360394a0f3e6a5816c8063bed275fd5690635f976306e0f21b2709807cfae5975fd6aa90d675fd5690f9a6d329271f00805570ea727d533a11a3f3340000325c483b8d34bc49c7efd7011c00000000000000000000000000000000000000000000000000004058ff010816db931350d3e20000000049454e44ae426082, 0, 1, 438, '7 Lebonan str Sallam District', '7 Lebonan str Sallam District', 'Benghazi ', '', '0', '0', '0', '0', '2147483647', '0');
INSERT INTO `user` (`id`, `userName`, `company`, `firstName`, `lastName`, `email`, `password`, `date`, `lastUpdate`, `image`, `role`, `active`, `country`, `address1`, `address2`, `city`, `state`, `zip`, `phoneCode`, `phone`, `mobilePhone`, `phone2`, `fax`) VALUES
(6, NULL, 'Zewail city', 'AHMED MOHAMED SAYED ABDELAZIZ', 'ALDAKRORY', 'ahmedsonic1993@gmail.com', '318b81e2676bfa31fef002d452c509bc', '2019-05-25 19:43:07', '2019-05-31 10:51:42', NULL, 1, 1, 520, '1 omda st from fasel talbya', '1 omda st from fasel talbya', 'giza', NULL, '12111', NULL, '01061690939', '', '', ''),
(7, NULL, 'Abdo Company', 'Abdo', 'Dakrory', 'ahmedsonic1993@gmail.co', '210d5e7efab6f14f67e71665c4efe8dc', '2019-05-25 19:51:50', '2019-05-28 17:09:11', NULL, 1, 1, 359, '1 omda st from fasel talbya', '6 of Octobar', 'giza', '', '12111', '', '+201000349430', '01061690939', '+201000349430', ''),
(8, NULL, 'Zewail city', 'AHMED MOHAMED SAYED ABDELAZIZ', 'ALDAKRORY', 'ahmedsonic1993@gmail.', '5fb33ef69078d63f31eb1353460b1c4c', '2019-05-25 19:52:13', '2019-05-28 17:14:17', NULL, 1, 1, 369, '1 omda st from fasel talbya', '1 omda st from fasel talbya', 'giza', NULL, '12111', NULL, '+201000349430', '01061690939', '', ''),
(9, NULL, 'Smart opal', 'Gamel', 'Al shaaer', 'asdas@gamel.com', '405ce06a4f6616a3d156b6b52a7b459c', '2019-05-25 21:28:27', '2019-05-26 18:11:48', NULL, 1, 1, 369, '1 omda st from fasel talbya', '6 of Octobar', 'giza', NULL, '12111', NULL, '+201000349430', '+201000349430', '+201000349430', '123'),
(10, NULL, 'Zewail city', 'AHMED MOHAMED SAYED ABDELAZIZ', 'ALDAKRORY', 'ahmedsonic1993@gmail.comw', '743967eda75ea61dbb5d1d6474006681', '2019-05-25 21:36:33', '2019-05-26 18:11:27', NULL, 1, 1, 520, '1 omda st from fasel talbya', '6 of Octobar', 'giza', NULL, '12111', NULL, '+201000349430', '01061690939', '+201000349430', ''),
(11, 'sayed Men3m', 'sayed', 'sayed', 'men3em', 'sayed@gmail.com', '4ffd625ea534e4f33b3e099f9f8126e0', '2019-05-26 18:21:34', '2019-05-26 18:23:56', NULL, 1, 1, 369, '1 omda st from fasel talbya', '1 omda st from fasel talbya', 'giza', NULL, '12111', NULL, '01061690939', '+201000349430', '01061690939', '123'),
(12, NULL, 'Zewail city', 'AHMED MOHAMED SAYED ABDELAZIZ', 'ALDAKRORY', 'Play@gmail.com', '4e9d528c8a60933fd084c0831945e8b4', '2019-05-26 19:12:34', '2019-05-28 21:31:14', NULL, 2, 1, 369, '1 omda st from fasel talbya', '6 of Octobar', 'Giza', NULL, '12588', NULL, '01061690939', '01061690939', '01061690939', '123'),
(13, NULL, 'Dakrory Company', 'Ahmed', 'Dakrory', 'ok@gmail.com', '65885e3d26a6d069fd6cea25754f3ec9', '2019-05-26 19:19:47', '2019-05-28 21:32:33', NULL, 2, 1, 369, '1 omda st from fasel talbya', '6 of Octobar', 'giza', NULL, '12111', NULL, '+201000349430', '+201000349430', '01061690939010', ''),
(14, NULL, 'hCompay', 'ahmed', 'ok', 'pok@pok.com', '903cea2f132533edf82ac92fe543043a', '2019-05-26 20:59:31', '2019-05-26 20:59:31', NULL, 3, 1, 336, '12klkmlkmlkm', '1lkmlkmklm', 'giza', NULL, '123', NULL, '91290129012', '132135165', '93188238', '32'),
(15, NULL, 'man ', 'man', 'ok', 'mailCustomer', '7b5212d938fb1823eaf902b638cd242f', '2019-05-26 21:19:25', '2019-05-26 21:19:25', NULL, 3, 1, 318, 'lklkaskmlas', 'lkm', 'mkl', NULL, 'lkm', NULL, 'mkllk', 'lkk', 'kml', 'lmk'),
(16, NULL, 'ok', 'ok', 'ok', 'ok', '51018d9e9093222e9b2df2163f991e1d', '2019-05-28 17:16:13', '2019-05-28 17:16:25', NULL, 1, 1, 472, 'ok', 'kok', 'k', NULL, 'ok', NULL, 'o', 'ok', 'ok', 'ok'),
(17, NULL, 'Zewail city', 'AHMED MOHAMED SAYED ABDELAZIZ', 'ALDAKRORY', 'ahmedsonic1993@gmaijjl.com', '47837e404bd36a18a7a2e34d6ee588e2', '2019-05-28 21:08:08', '2019-05-28 21:08:08', NULL, 4, 1, 369, '1 omda st from fasel talbya', '1', 'giza', NULL, '12111', NULL, '01061690939', '', '', ''),
(18, NULL, 'Zewail city', 'AHMED MOHAMED SAYED ABDELAZIZ', 'ALDAKRORY', 'ahmedsonic1993@gmadil.com', '7a6bc4968b7b3edbfaf5968bf87b3b29', '2019-05-28 21:10:24', '2019-05-28 21:17:40', NULL, 4, 1, 369, '1 omda st from fasel talbya', '1', 'giza', NULL, '12111', NULL, '01061690939', '', '01061690939', 'ok'),
(19, NULL, 'Dakrory', 'Ahmed', 'Dakrory', 'ahmedsonicd1993@gmail.com', '127ff2bc5cf7b33582ee6e58771794b0', '2019-05-28 21:10:55', '2019-05-28 21:10:55', NULL, 4, 1, 369, '1 omda st from fasel talbya', '1', 'giza', NULL, '12111', NULL, '01061690939', '', '', ''),
(20, NULL, 'Dakrory', 'Ahmed', 'Dakrory', 'ahmedsondic1993@gmail.com', '9bc6b626d46e610412c5f8b5b0472f56', '2019-05-28 21:11:53', '2019-05-28 21:11:53', NULL, 4, 1, 369, '1 omda st from fasel talbya', '1', 'giza', NULL, '12111', NULL, '01061690939', '', '', ''),
(21, NULL, 'Zewail city', 'AHMED', 'ALDAKRORY', 'ajkkajkjkadskjjkjkdfd@gmail.com', '2421ffbe45d738ebe3f43eab15c97c19', '2019-05-28 21:26:49', '2019-05-28 21:28:00', NULL, 2, 1, 369, '1 omda st from fasel talbya', '1 omda st from fasel talbya', 'giza', NULL, '12111', NULL, '', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `vendor`
--

CREATE TABLE `vendor` (
  `id` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `idType` int(11) DEFAULT NULL,
  `other` mediumtext,
  `notes` mediumtext,
  `allowAccess` int(11) NOT NULL DEFAULT '0',
  `parentId` int(11) DEFAULT NULL,
  `salesRep` mediumtext,
  `idNumber` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vendor`
--

INSERT INTO `vendor` (`id`, `userId`, `idType`, `other`, `notes`, `allowAccess`, `parentId`, `salesRep`, `idNumber`) VALUES
(1, 12, 1, '', '', 0, 15, 'dfdfdf', 'd232323'),
(2, 13, 1, '', '', 0, 15, '', 'dsdsd'),
(3, 21, 1, '', '', 0, 15, '', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`id`),
  ADD KEY `mainIdkey_idx` (`mainId`),
  ADD KEY `shipperIdKey_idx` (`shipperId`),
  ADD KEY `vendorIdKey_idx` (`vendorId`),
  ADD KEY `customerIdKey_idx` (`customerId`),
  ADD KEY `consigneeIdKey_idx` (`consigneeId`);

--
-- Indexes for table `carimage`
--
ALTER TABLE `carimage`
  ADD PRIMARY KEY (`id`),
  ADD KEY `carId_idx` (`carId`);

--
-- Indexes for table `consignee`
--
ALTER TABLE `consignee`
  ADD PRIMARY KEY (`id`),
  ADD KEY `useridForCongnie_idx` (`userId`),
  ADD KEY `parentIdShipper_idx` (`parentId`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `userIdx_idx` (`userId`),
  ADD KEY `dataCustomervendor_idx` (`parentId`);

--
-- Indexes for table `shipper`
--
ALTER TABLE `shipper`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_UNIQUE` (`id`),
  ADD KEY `idUser_idx` (`userId`),
  ADD KEY `idMainUnder_idx` (`parentId`),
  ADD KEY `underMainId` (`parentId`);

--
-- Indexes for table `transportfee`
--
ALTER TABLE `transportfee`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vendor`
--
ALTER TABLE `vendor`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idUser_idx` (`parentId`),
  ADD KEY `userIdx_idx` (`userId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `car`
--
ALTER TABLE `car`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `carimage`
--
ALTER TABLE `carimage`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `consignee`
--
ALTER TABLE `consignee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `shipper`
--
ALTER TABLE `shipper`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `transportfee`
--
ALTER TABLE `transportfee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=573;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `vendor`
--
ALTER TABLE `vendor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `car`
--
ALTER TABLE `car`
  ADD CONSTRAINT `consigneeIdKey` FOREIGN KEY (`consigneeId`) REFERENCES `consignee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `customerIdKey` FOREIGN KEY (`customerId`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `mainIdkey` FOREIGN KEY (`mainId`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `shipperIdKey` FOREIGN KEY (`shipperId`) REFERENCES `shipper` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `vendorIdKey` FOREIGN KEY (`vendorId`) REFERENCES `vendor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `carimage`
--
ALTER TABLE `carimage`
  ADD CONSTRAINT `carId` FOREIGN KEY (`carId`) REFERENCES `car` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `consignee`
--
ALTER TABLE `consignee`
  ADD CONSTRAINT `parentIdShipper` FOREIGN KEY (`parentId`) REFERENCES `shipper` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `useridForCongnie` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `dataCustomervendor` FOREIGN KEY (`parentId`) REFERENCES `vendor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `newUserData` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `shipper`
--
ALTER TABLE `shipper`
  ADD CONSTRAINT `idUser` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `mainParentKey` FOREIGN KEY (`parentId`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `vendor`
--
ALTER TABLE `vendor`
  ADD CONSTRAINT `datashipping` FOREIGN KEY (`parentId`) REFERENCES `shipper` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `userIdx` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
