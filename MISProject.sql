-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : ven. 21 mai 2021 à 12:23
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 7.4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `MISProject`
--

-- --------------------------------------------------------

--
-- Structure de la table `Appointment`
--

CREATE TABLE `Appointment` (
  `id` int(11) NOT NULL,
  `appointmentTime` datetime NOT NULL DEFAULT current_timestamp(),
  `patient` int(11) DEFAULT NULL,
  `vaccinationCenter` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `Appointment`
--

INSERT INTO `Appointment` (`id`, `appointmentTime`, `patient`, `vaccinationCenter`) VALUES
(1, '2021-05-23 18:00:00', 1, 1),
(2, '2021-05-23 18:00:00', 5, 2),
(3, '2021-05-24 18:00:00', 2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `Patient`
--

CREATE TABLE `Patient` (
  `id` int(11) NOT NULL,
  `firstName` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lastName` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `municipality` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `closestCenter` int(11) DEFAULT NULL,
  `NISS` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `comorbidity` tinyint(1) DEFAULT 0,
  `Vaccinated` int(11) DEFAULT 0,
  `score` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `Patient`
--

INSERT INTO `Patient` (`id`, `firstName`, `lastName`, `municipality`, `email`, `closestCenter`, `NISS`, `comorbidity`, `Vaccinated`, `score`) VALUES
(1, 'Rania', 'Charkaoui', 'Jette', 'rania.charkaoui@ulb.be', 1, '99090936057', 0, 0, 0),
(2, 'Gilles', 'Feron', 'Etterbeek', 'gilles.feron@ulb.be', 2, '00011404358', 1, 0, 0),
(3, 'Arthur', 'Elskens', 'Uccle', 'arthur.elskens@ulb.be', NULL, '98874505814', 0, 0, NULL),
(4, 'Christine', 'Decaestecker', 'Ganshoren', 'christine.decaestecker@ulb.be', NULL, '34567886543', 0, 0, NULL),
(5, 'Olivier', 'Debeir', 'Evere', 'olivier.debeir@ulb.be', 2, '70985411067', 0, 1, 51),
(6, 'Gauthier', 'Lafruit', 'Woluwe-Saint-Pierre', 'gauthier.lafruit@ulb.be', NULL, '74898034166', 1, 0, NULL),
(7, 'Adrien', 'Foucart', 'Etterbeek', 'adrien.foucart@ulb.be', 4, '88050542312', 1, 0, 0),
(8, 'David', 'Wikler', 'Saint-Gilles', 'david.wikler@ulb.be', 3, '73022345678', 0, 0, 48),
(9, 'Elise', 'Gutermann', 'Ganshoren', 'elise.gutermann@ulb.be', NULL, '99121163351', 0, 0, NULL),
(10, 'Manon', 'Wyns', 'Bruxelles', 'manon.wyns@ulb.be', NULL, '99121114375', 1, 0, NULL),
(11, 'Benjamin', 'Hainaut', 'Molenbeek-Saint-Jean', 'benjamin.hainaut@ulb.be', NULL, '74031595834', 0, 0, NULL),
(12, 'Daniel', 'Farkas', 'Berchem-Sainte-Agathe', 'daniel.farkas@ulb.be', NULL, '74031522561', 1, 0, NULL),
(13, 'Zineb', 'Smine', 'Anderlecht', 'zineb.smine@ulb.be', NULL, '75063050410', 0, 0, NULL),
(14, 'Alissa', 'Komino', 'Forest', 'alissa.komino@ulb.be', NULL, '99072174902', 0, 0, NULL),
(15, 'Anastasia', 'Meerbergen', 'Saint-Gilles', 'anastasia.meerbergen@ulb.be', NULL, '83012685421', 0, 0, NULL),
(16, 'Phrasie', 'Hamon', 'Koekelberg', 'phrasie.hamon@ulb.be', NULL, '6509545077', 1, 0, NULL),
(17, 'Andrea', 'Ledent', 'Ixelles', 'andrea.ledent@ulb.be', NULL, '88090575440', 1, 0, NULL),
(18, 'Cécile', 'Castiaux', 'Saint-Josse-ten-Noode', 'cecile.castiaux@ulb.be', NULL, '89071756602', 0, 0, NULL),
(19, 'Leila', 'Naji', 'Schaerbeek', 'leila.naji@ulb.be', NULL, '95010455207', 0, 0, NULL),
(20, 'Lisa', 'Woby Mfongou', 'Woluwe-Saint-Lambert', 'lisa.woby.mfongou@ulb.be', NULL, '56053176556', 0, 0, NULL),
(21, 'Madeleine', 'Hossey', 'Etterbeek', 'madeleine.hossey@ulb.be', NULL, '80091866631', 1, 0, NULL),
(22, 'Ahmed', 'Bader', 'Molenbeek-Saint-Jean', 'ahmed.bader@ulb.be', NULL, '79102367110', 1, 0, NULL),
(23, 'Clara', 'Deslypere', 'Bruxelles', 'clara.deslypere@ulb.be', NULL, '86032815440', 0, 0, NULL),
(24, 'Céline', 'Tran', 'Molenbeek-Saint-Jean', 'celine.tran@ulb.be', NULL, '00112066913', 0, 0, NULL),
(25, 'Ilham', 'El Fakiri', 'Schaerbeek', 'ilham.el.fakiri@ulb.be', NULL, '65100956221', 1, 0, NULL),
(26, 'Natalia', 'Leboutte', 'Etterbeek', 'natalia.leboutte@ulb.be', NULL, '78050789973', 0, 0, NULL),
(27, 'Rachel', 'Willaumez', 'Forest', 'rachel.willaumez@ulb.be', NULL, '69070777456', 0, 0, NULL),
(28, 'Rym', 'Benrafalia', 'Ixelles', 'rym.benrafalia@ulb.be', NULL, '98060309077', 1, 0, NULL),
(29, 'Arthur', 'Bouffandeau', 'Watermael-Boitsfort', 'arthur.bouufandeau@ulb.be', NULL, '980110770566', 1, 0, NULL),
(30, 'Charlotte', 'Deroubaix', 'Jette', 'charlotte.deroubaix@ulb.be', NULL, '81050875356', 0, 0, NULL),
(31, 'Samuel', 'Courboin', 'Evere', 'samuel.courboin@ulb.be', NULL, '94092277948', 1, 0, NULL),
(32, 'Sylvie', 'Agnetti', 'Saint-Josse-ten-Noode', 'sylvie.agnetti@ulb.be', NULL, '88080888858', 0, 0, NULL),
(33, 'Sébastien', 'Berlémont', 'Auderghem', 'sebastien.berlemont@ulb.be', NULL, '76041899491', 0, 0, NULL),
(34, 'Tristan', 'Smeesters', 'Watermael-Boitsfort', 'tristan.smeesters@ulb.be', NULL, '90090377199', 1, 0, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `VaccinationCenter`
--

CREATE TABLE `VaccinationCenter` (
  `id` int(11) NOT NULL,
  `availableDoses` int(11) NOT NULL,
  `zone` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `VaccinationCenter`
--

INSERT INTO `VaccinationCenter` (`id`, `availableDoses`, `zone`) VALUES
(1, 10, 'Zone 1'),
(2, 5, 'Zone 2'),
(3, 7, 'Zone 3'),
(4, 8, 'Zone 4');

-- --------------------------------------------------------

--
-- Structure de la table `WaitingList`
--

CREATE TABLE `WaitingList` (
  `id` int(11) NOT NULL,
  `patient` int(11) NOT NULL,
  `vaccinationCenter` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `WaitingList`
--

INSERT INTO `WaitingList` (`id`, `patient`, `vaccinationCenter`) VALUES
(3, 7, 4),
(4, 8, 3);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Appointment`
--
ALTER TABLE `Appointment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Appointment_fk0` (`patient`),
  ADD KEY `Appointment_fk1` (`vaccinationCenter`);

--
-- Index pour la table `Patient`
--
ALTER TABLE `Patient`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Patient_fk0` (`closestCenter`);

--
-- Index pour la table `VaccinationCenter`
--
ALTER TABLE `VaccinationCenter`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `WaitingList`
--
ALTER TABLE `WaitingList`
  ADD PRIMARY KEY (`id`),
  ADD KEY `WaitingList_fk0` (`patient`),
  ADD KEY `WaitingList_fk1` (`vaccinationCenter`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Appointment`
--
ALTER TABLE `Appointment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `Patient`
--
ALTER TABLE `Patient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT pour la table `VaccinationCenter`
--
ALTER TABLE `VaccinationCenter`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `WaitingList`
--
ALTER TABLE `WaitingList`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Appointment`
--
ALTER TABLE `Appointment`
  ADD CONSTRAINT `Appointment_fk0` FOREIGN KEY (`patient`) REFERENCES `Patient` (`id`),
  ADD CONSTRAINT `Appointment_fk1` FOREIGN KEY (`vaccinationCenter`) REFERENCES `VaccinationCenter` (`id`);

--
-- Contraintes pour la table `Patient`
--
ALTER TABLE `Patient`
  ADD CONSTRAINT `Patient_fk0` FOREIGN KEY (`closestCenter`) REFERENCES `VaccinationCenter` (`id`);

--
-- Contraintes pour la table `WaitingList`
--
ALTER TABLE `WaitingList`
  ADD CONSTRAINT `WaitingList_fk0` FOREIGN KEY (`patient`) REFERENCES `Patient` (`id`),
  ADD CONSTRAINT `WaitingList_fk1` FOREIGN KEY (`vaccinationCenter`) REFERENCES `VaccinationCenter` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
