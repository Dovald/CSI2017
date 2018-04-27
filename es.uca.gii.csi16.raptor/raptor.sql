-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 11-01-2017 a las 17:14:16
-- Versión del servidor: 5.5.24-log
-- Versión de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `raptor`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asignatura`
--

CREATE TABLE IF NOT EXISTS `asignatura` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Id_TipoAsignatura` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Creditos` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Nombre` (`Nombre`),
  KEY `Id_TipoAsignatura` (`Id_TipoAsignatura`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Volcado de datos para la tabla `asignatura`
--

INSERT INTO `asignatura` (`Id`, `Id_TipoAsignatura`, `Nombre`, `Creditos`) VALUES
(35, 2, 'Transformación', 7),
(42, 1, 'Fin de Grado', 18),
(43, 1, 'Vuelo', 6);

--
-- Disparadores `asignatura`
--
DROP TRIGGER IF EXISTS `Asignatura_BI`;
DELIMITER //
CREATE TRIGGER `Asignatura_BI` BEFORE INSERT ON `asignatura`
 FOR EACH ROW BEGIN
if NEW.Nombre='' then 
    signal sqlstate '45000' 
		set message_text = 'El nombre de la asignatura no puede estar vacío.'; 
  end if;
END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `Asignatura_BU`;
DELIMITER //
CREATE TRIGGER `Asignatura_BU` BEFORE UPDATE ON `asignatura`
 FOR EACH ROW BEGIN
if NEW.Nombre='' then 
    signal sqlstate '45000' 
		set message_text = 'El nombre de la asignatura no puede estar vacío.'; 
end if;
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipoasignatura`
--

CREATE TABLE IF NOT EXISTS `tipoasignatura` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(24) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Nombre` (`Nombre`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Volcado de datos para la tabla `tipoasignatura`
--

INSERT INTO `tipoasignatura` (`Id`, `Nombre`) VALUES
(1, 'Obligatoria'),
(2, 'Optativa');

--
-- Disparadores `tipoasignatura`
--
DROP TRIGGER IF EXISTS `TipoAsignatura_BI`;
DELIMITER //
CREATE TRIGGER `TipoAsignatura_BI` BEFORE INSERT ON `tipoasignatura`
 FOR EACH ROW if NEW.Nombre='' then
  	signal sqlstate '45000'
    	set message_text = 'El Nombre del tipo de la asignatura no puede estar vacío.';
  end if
//
DELIMITER ;
DROP TRIGGER IF EXISTS `TipoAsignatura_BU`;
DELIMITER //
CREATE TRIGGER `TipoAsignatura_BU` BEFORE UPDATE ON `tipoasignatura`
 FOR EACH ROW if NEW.Nombre='' then
  	signal sqlstate '45000'
    	set message_text = 'El Nombre del tipo de la asignatura no puede estar vacío.';
  end if
//
DELIMITER ;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asignatura`
--
ALTER TABLE `asignatura`
  ADD CONSTRAINT `asignatura_ibfk_1` FOREIGN KEY (`Id_TipoAsignatura`) REFERENCES `tipoasignatura` (`Id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
