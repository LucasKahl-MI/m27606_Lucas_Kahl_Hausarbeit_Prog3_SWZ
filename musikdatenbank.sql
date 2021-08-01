-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 25. Feb 2021 um 16:55
-- Server-Version: 10.4.11-MariaDB
-- PHP-Version: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `musikdatenbank`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `interpret`
--

CREATE TABLE `interpret` (
  `ID` int(11) NOT NULL,
  `Vorname` varchar(50) DEFAULT NULL,
  `Nachname` varchar(50) DEFAULT NULL,
  `infos` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `interpret`
--

INSERT INTO `interpret` (`ID`, `Vorname`, `Nachname`, `infos`) VALUES
(1, 'Nathan', 'Carter', 'Schuhgröße 45'),
(2, 'Brad', 'Paisley', 'Schuhgröße 43'),
(3, 'Ed', 'Sheeran', 'Schuhgröße 39'),
(4, 'Eva', 'Max', 'Schuhgröße 36'),
(5, 'Julia ', 'Scheeser', 'Schuhgröße 37'),
(6, 'Roland', 'Kaiser', 'Schuhgröße 48');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `song`
--

CREATE TABLE `song` (
  `Titel_ID` int(11) NOT NULL,
  `Interpret_ID` int(11) NOT NULL,
  `Song_Titel` varchar(100) NOT NULL,
  `Titel_Laenge` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `song`
--

INSERT INTO `song` (`Titel_ID`, `Interpret_ID`, `Song_Titel`, `Titel_Laenge`) VALUES
(1, 1, 'Wanna Dance', '4:43'),
(1, 2, 'She\'s Everything', '4:26'),
(1, 3, 'Perfect ', '4:23'),
(1, 4, 'Sweet but Psycho', '3:07'),
(1, 5, 'Ein Traum wird wahr', '2:47'),
(1, 6, 'Warum hast Du nicht nein gesagt ', '3:38'),
(2, 1, 'Temple Bar', '2:58'),
(2, 2, 'Mud on the Tires', '3:26'),
(2, 3, 'Photograph', '4:18'),
(2, 4, 'Kings & Queens', '2:42'),
(2, 5, 'Ich werd niemals schweigen', '3:25'),
(2, 6, 'Santa Maria', '4:03'),
(3, 1, 'Liverpool', '4:32'),
(3, 2, 'Celebrity', '3:43'),
(3, 3, 'Shape of You', '3:53'),
(3, 4, 'Salt', '3:00'),
(3, 5, 'Choreografie', '3:21'),
(3, 6, 'Joana', '4:05');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `interpret`
--
ALTER TABLE `interpret`
  ADD PRIMARY KEY (`ID`);

--
-- Indizes für die Tabelle `song`
--
ALTER TABLE `song`
  ADD PRIMARY KEY (`Titel_ID`,`Interpret_ID`),
  ADD KEY `Interpret_ID` (`Interpret_ID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `interpret`
--
ALTER TABLE `interpret`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `song`
--
ALTER TABLE `song`
  ADD CONSTRAINT `song_ibfk_1` FOREIGN KEY (`Interpret_ID`) REFERENCES `interpret` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
