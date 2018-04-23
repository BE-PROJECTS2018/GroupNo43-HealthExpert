-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 12, 2018 at 11:33 PM
-- Server version: 5.6.26
-- PHP Version: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hedb`
--

-- --------------------------------------------------------

--
-- Table structure for table `bookmarks`
--

CREATE TABLE IF NOT EXISTS `bookmarks` (
  `b_id` int(11) NOT NULL,
  `b_source` text NOT NULL,
  `b_destination` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bookmarks`
--

INSERT INTO `bookmarks` (`b_id`, `b_source`, `b_destination`) VALUES
(21, '01239254145e44665f0a31b786ff3d5d4af7bfd358769a85acf71435db2813ce', '8877685efad32488214097aa90357084608875d5765d767cad2cf9e29def2ad5'),
(30, '8877685efad32488214097aa90357084608875d5765d767cad2cf9e29def2ad5', '558886aed687064296c280cca89da9746731c5a3d000fcf268e11f8c95678a0f');

-- --------------------------------------------------------

--
-- Table structure for table `disease`
--

CREATE TABLE IF NOT EXISTS `disease` (
  `d_id` int(11) NOT NULL,
  `d_name` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `disease`
--

INSERT INTO `disease` (`d_id`, `d_name`) VALUES
(1, 'accident cerebrovascular'),
(2, 'acquired immuno-deficiency syndrome'),
(3, 'adenocarcinoma'),
(4, 'adhesion'),
(5, 'affect labile'),
(6, 'Alzheimer''s disease'),
(7, 'anemia'),
(8, 'anxiety state'),
(9, 'aphasia'),
(10, 'arthritis'),
(11, 'asthma'),
(12, 'bacteremia'),
(13, 'benign prostatic hypertrophy'),
(14, 'biliary calculus'),
(15, 'bipolar disorder'),
(16, 'bronchitis'),
(17, 'candidiasis'),
(18, 'carcinoma'),
(19, 'carcinoma breast'),
(20, 'carcinoma colon'),
(21, 'carcinoma of lung'),
(22, 'carcinoma prostate'),
(23, 'cardiomyopathy'),
(24, 'cellulitis'),
(25, 'cholecystitis'),
(26, 'cholelithiasis'),
(27, 'chronic alcoholic intoxication'),
(28, 'chronic kidney failure'),
(29, 'chronic obstructive airway disease'),
(30, 'cirrhosis'),
(31, 'colitis'),
(32, 'confusion'),
(33, 'coronary arteriosclerosis'),
(34, 'coronary heart disease'),
(35, 'decubitus ulcer'),
(36, 'deep vein thrombosis'),
(37, 'degenerative polyarthritis'),
(38, 'deglutition disorder'),
(39, 'dehydration'),
(40, 'delirium'),
(41, 'delusion'),
(42, 'dementia'),
(43, 'dependence'),
(44, 'depression mental'),
(45, 'depressive disorder'),
(46, 'diabetes'),
(47, 'diverticulitis'),
(48, 'diverticulosis'),
(49, 'edema pulmonary'),
(50, 'effusion pericardial'),
(51, 'embolism pulmonary'),
(52, 'emphysema pulmonary'),
(53, 'encephalopathy'),
(54, 'endocarditis'),
(55, 'epilepsy'),
(56, 'exanthema'),
(57, 'failure heart'),
(58, 'failure heart congestive'),
(59, 'failure kidney'),
(60, 'fibroid tumor'),
(61, 'gastritis'),
(62, 'gastroenteritis'),
(63, 'gastroesophageal reflux disease'),
(64, 'glaucoma'),
(65, 'gout'),
(66, 'hemiparesis'),
(67, 'hemorrhoids'),
(68, 'hepatitis'),
(69, 'hepatitis B'),
(70, 'hepatitis C'),
(71, 'hernia'),
(72, 'hernia hiatal'),
(73, 'HIV'),
(74, 'hiv infections'),
(75, 'hyperbilirubinemia'),
(76, 'hypercholesterolemia'),
(77, 'hyperglycemia'),
(78, 'hyperlipidemia'),
(79, 'hypertension pulmonary'),
(80, 'hypertensive disease'),
(81, 'hypoglycemia'),
(82, 'hypothyroidism'),
(83, 'ileus'),
(84, 'incontinence'),
(85, 'incontinence'),
(86, 'infection'),
(87, 'infection urinary tract'),
(88, 'influenza'),
(89, 'insufficiency renal'),
(90, 'ischemia'),
(91, 'ketoacidosis diabetic'),
(92, 'kidney disease'),
(93, 'kidney failure acute'),
(94, 'lymphatic diseases'),
(95, 'lymphoma'),
(96, 'malignant neoplasm of breast'),
(97, 'malignant neoplasm of lung'),
(98, 'malignant neoplasm of prostate'),
(99, 'malignant neoplasms'),
(100, 'malignant tumor of colonmanic disorder'),
(101, 'manic disorder'),
(102, 'melanoma'),
(103, 'migraine disorders'),
(104, 'mitral valve insufficiency'),
(105, 'myocardial infarction'),
(106, 'neoplasm'),
(107, 'neoplasm metastasis'),
(108, 'neuropathy'),
(109, 'neutropenia'),
(110, 'obesity'),
(111, 'obesity morbid'),
(112, 'oral candidiasis'),
(113, 'osteomyelitis'),
(114, 'osteoporosis'),
(115, 'overload fluid'),
(116, 'pancreatitis'),
(117, 'pancytopenia'),
(118, 'paranoia'),
(119, 'parkinson disease'),
(120, 'paroxysmal dyspnea'),
(121, 'pericardial effusion body substance'),
(122, 'peripheral vascular disease'),
(123, 'personality disorder'),
(124, 'Pneumocystis carinii pneumonia'),
(125, 'pneumonia'),
(126, 'pneumonia aspiration'),
(127, 'pneumothorax'),
(128, 'primary carcinoma of the liver cells'),
(129, 'primary malignant neoplasm'),
(130, 'psychotic disorder'),
(131, 'pyelonephritis'),
(132, 'respiratory failure'),
(133, 'schizophrenia'),
(134, 'sepsis (invertebrate)'),
(135, 'septicemia'),
(136, 'sickle cell anemia'),
(137, 'spasm bronchial'),
(138, 'stenosis aortic valve'),
(139, 'suicide attempt'),
(140, 'systemic infection'),
(141, 'tachycardia sinus'),
(142, 'thrombocytopaenia'),
(143, 'thrombus'),
(144, 'tonic-clonic epilepsy'),
(145, 'tonic-clonic seizures'),
(146, 'transient ischemic attack'),
(147, 'tricuspid valve insufficiency'),
(148, 'ulcer peptic'),
(149, 'upper respiratory infection');

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE IF NOT EXISTS `doctor` (
  `d_id` int(11) NOT NULL,
  `d_name` text NOT NULL,
  `d_regid` text NOT NULL,
  `d_speciality` int(11) NOT NULL,
  `d_photo` text NOT NULL,
  `d_city` text NOT NULL,
  `d_pincode` text NOT NULL,
  `d_phoneno` text NOT NULL,
  `d_gender` text NOT NULL,
  `d_experience` text NOT NULL,
  `d_lat` text NOT NULL,
  `d_long` text NOT NULL,
  `d_accesstoken` text NOT NULL,
  `d_fuid` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`d_id`, `d_name`, `d_regid`, `d_speciality`, `d_photo`, `d_city`, `d_pincode`, `d_phoneno`, `d_gender`, `d_experience`, `d_lat`, `d_long`, `d_accesstoken`, `d_fuid`) VALUES
(5, 'Admin', 'REG', 3, '', 'Mumbai', '400080', '7709900983', 'Male', '6', '', '', '8d896aa3482a55e0a36776814b6d0d2d2cc689893ff9f8492d57342255eddeea', ''),
(14, 'Ramesh Punjani', 'REG1234', 3, 'static/uploads/8877685efad32488214097aa90357084608875d5765d767cad2cf9e29def2ad5image.jpg', 'Mumbai', '400080', '9004337436', 'Male', '4', '', '', '8877685efad32488214097aa90357084608875d5765d767cad2cf9e29def2ad5', 'JqVEJybtaiVqv3p1L5Dmqmpn1bX2'),
(15, 'Shivani', 'REG123', 5, '', 'Mumbai', '400080', '8806146401', 'Female', '5', '', '', '502cda6bf013ca8bd28b060106ba2089d59f2a775338a1476168fbb273ae8d4d', 'Cz8jHNYGUAUXCGHRhKoqAaEuE5H2'),
(16, 'Gayatri ', 'REG21', 4, '', 'mumbai', '421001', '9730778844', 'Female', '9', '', '', 'c2ce32f2b7a5392f830a96ae4909cbef1e73021645072f9ee40a8b7bcc419c6a', '66aTUitHb0WzAmdzK1OSs0J8bef1'),
(17, 'Viral Parmar', 'REG123', 3, 'static/uploads/9af907ce318a91843be8d1107c7ca8289ef21aaf452fc9b93c2c24467054bfa2image.jpg', 'Delhi', '377080', '9022218185', 'Male', '6', '', '', '9af907ce318a91843be8d1107c7ca8289ef21aaf452fc9b93c2c24467054bfa2', 'k60FisT1ujUszzVB1TcFL8BQClC3');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE IF NOT EXISTS `patient` (
  `p_id` int(11) NOT NULL,
  `p_name` text NOT NULL,
  `p_dob` text NOT NULL,
  `p_gender` text NOT NULL,
  `p_height` text NOT NULL,
  `p_weight` text NOT NULL,
  `p_phoneno` text NOT NULL,
  `p_occupation` text NOT NULL,
  `p_symptoms` text NOT NULL,
  `p_bloodgroup` text NOT NULL,
  `p_history` text NOT NULL,
  `p_investigations` text NOT NULL,
  `p_city` text NOT NULL,
  `p_pincode` text NOT NULL,
  `p_mothername` text NOT NULL,
  `p_mothersymptoms` text NOT NULL,
  `p_fathername` text NOT NULL,
  `p_fathersymptoms` text NOT NULL,
  `p_photo` text NOT NULL,
  `p_lat` text NOT NULL,
  `p_long` text NOT NULL,
  `p_accesstoken` text NOT NULL,
  `p_devicetoken` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`p_id`, `p_name`, `p_dob`, `p_gender`, `p_height`, `p_weight`, `p_phoneno`, `p_occupation`, `p_symptoms`, `p_bloodgroup`, `p_history`, `p_investigations`, `p_city`, `p_pincode`, `p_mothername`, `p_mothersymptoms`, `p_fathername`, `p_fathersymptoms`, `p_photo`, `p_lat`, `p_long`, `p_accesstoken`, `p_devicetoken`) VALUES
(1, 'Archish Thakkar', '27/10/1996', 'Male', '5.2', '53', '7709900983', 'Student', '1,2,3', 'O+', 'Migrain Problem', 'Overtime worked', 'Mumbai', '400080', 'Bhavana Thakkar', '251,256,263,813', 'Arvind Thakkar', '256,264,288,289,813', 'static/uploads/8877685efad32488214097aa90357084608875d5765d767cad2cf9e29def2ad5image.jpg', '', '', '01239254145e44665f0a31b786ff3d5d4af7bfd358769a85acf71435db2813ce', 'dxFmU1BDE9Y:APA91bGpmR3LwU5uR-uRGDgphCNkTGjTRIoYCZucWb4Q8ChQZSVnhWrWQxsNLZdKwbbg68-5OIglZZ0zUN0O2olb7gzSoBPei_GRNoi9ytD4qKOmJcqgFlywGNQ1lNW1Qclk4GkWmBaI'),
(2, 'Rohan Tendulkar', '10/02/1973', 'Male', '6.1', '73', '7788997799', 'Home Worker', '194,241', 'B+', 'Kidney Stone', 'Due to outside food', 'Mumbai', '400080', 'Rakhi Tendulkar', '', 'Sachin Tendulkar', '', '', '', '', '558886aed687064296c280cca89da9746731c5a3d000fcf268e11f8c95678a0f', '');

-- --------------------------------------------------------

--
-- Table structure for table `patient_details`
--

CREATE TABLE IF NOT EXISTS `patient_details` (
  `pd_id` int(11) NOT NULL,
  `pd_pid` int(11) NOT NULL,
  `pd_accesstoken` text NOT NULL,
  `pd_symptom` text NOT NULL,
  `pd_disease` text NOT NULL,
  `p_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `prescription`
--

CREATE TABLE IF NOT EXISTS `prescription` (
  `p_id` int(11) NOT NULL,
  `p_paccesstoken` text NOT NULL,
  `p_daccesstoken` text NOT NULL,
  `p_title` text NOT NULL,
  `p_prescription` text NOT NULL,
  `p_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prescription`
--

INSERT INTO `prescription` (`p_id`, `p_paccesstoken`, `p_daccesstoken`, `p_title`, `p_prescription`, `p_timestamp`) VALUES
(9, '01239254145e44665f0a31b786ff3d5d4af7bfd358769a85acf71435db2813ce', '8877685efad32488214097aa90357084608875d5765d767cad2cf9e29def2ad5', 'Headache', '3Times a day CROCIN\nMorning\nEvening\nAfternoon\nWith Water\n', '2018-03-12 17:57:25');

-- --------------------------------------------------------

--
-- Table structure for table `rating`
--

CREATE TABLE IF NOT EXISTS `rating` (
  `r_id` int(11) NOT NULL,
  `r_daccesstoken` text NOT NULL,
  `r_paccesstoken` text NOT NULL,
  `r_score` int(11) NOT NULL,
  `r_like` tinyint(4) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rating`
--

INSERT INTO `rating` (`r_id`, `r_daccesstoken`, `r_paccesstoken`, `r_score`, `r_like`) VALUES
(3, '502cda6bf013ca8bd28b060106ba2089d59f2a775338a1476168fbb273ae8d4d', '01239254145e44665f0a31b786ff3d5d4af7bfd358769a85acf71435db2813ce', 4, 1),
(5, '8877685efad32488214097aa90357084608875d5765d767cad2cf9e29def2ad5 ', '01239254145e44665f0a31b786ff3d5d4af7bfd358769a85acf71435db2813ce', 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE IF NOT EXISTS `roles` (
  `r_id` int(11) NOT NULL,
  `r_name` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`r_id`, `r_name`) VALUES
(1, 'ADMIN'),
(2, 'DOCTOR'),
(3, 'PATIENT');

-- --------------------------------------------------------

--
-- Table structure for table `speciality`
--

CREATE TABLE IF NOT EXISTS `speciality` (
  `s_id` int(11) NOT NULL,
  `s_name` text NOT NULL,
  `s_description` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `speciality`
--

INSERT INTO `speciality` (`s_id`, `s_name`, `s_description`) VALUES
(1, 'Allergist', 'Conducts the diagnosis and treatment of allergic conditions'),
(2, 'Anesthesiologist', 'Treats chronic pain syndromes; administers anesthesia and monitors the patient during surgery'),
(3, 'Gastroenterologist', 'Treats stomach disorders'),
(4, 'Hematologist/Oncologist', 'Treats diseases of the blood and blood-forming tissues (oncology including cancer and other tumors)'),
(5, 'Internal Medicine Physician', 'Treats diseases and disorders of internal structures of the body'),
(6, 'Nephrologist', 'Treats kidney diseases'),
(7, 'Neurologist', 'Treats diseases and disorders of the nervous system'),
(8, 'Neurosurgeon', 'Conducts surgery of the nervous system'),
(9, 'Obstetrician', 'Treats women during pregnancy and childbirth'),
(10, 'Gynecologist', 'Treats diseases of the female reproductive system and genital tract'),
(11, 'Nurse-Midwifery', 'Manages a woman''s health care, especially during pregnancy, delivery, and the postpartum period'),
(12, 'Occupational Medicine Physician', 'Diagnoses and treats work-related disease or injury'),
(13, 'Ophthalmologist', 'Treats eye defects, injuries, and diseases'),
(14, 'Oral and Maxillofacial Surgeon', 'Surgically treats diseases, injuries, and defects of the hard and soft tissues of the face, mouth, and jaws'),
(15, 'Orthopaedic Surgeon', 'preserves and restores the function of the musculoskeletal system'),
(16, 'Otolaryngologist', 'Treats diseases of the ear, nose, and throat,and some diseases of the head and neck, including facial plastic surgery'),
(17, 'Pathologist', 'Diagnoses and treats the study of the changes in body tissues and organs which cause or are caused by disease'),
(18, 'Podiatrist', 'Provides medical and surgical treatment of the foot'),
(19, 'Psychiatrist', 'Treats patients with mental and emotional disorders'),
(20, 'Pulmonary Medicine Physician', 'Diagnoses and treats lung disorders'),
(21, 'Radiation Onconlogist', 'Diagnoses and treats disorders with the use of diagnostic imaging, including X-rays, sound waves, radioactive substances, and magnetic fields'),
(22, 'Diagnostic Radiologist', 'Diagnoses and medically treats diseases and disorders of internal structures of the body'),
(23, 'Rheumatologist', 'Treats rheumatic diseases, or conditions characterized by inflammation, soreness and stiffness of muscles, and pain in joints and associated structures'),
(24, 'Urologist', 'Diagnoses and treats the male and female urinary tract and the male reproductive system');

-- --------------------------------------------------------

--
-- Table structure for table `symptom`
--

CREATE TABLE IF NOT EXISTS `symptom` (
  `s_id` int(11) NOT NULL,
  `s_name` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=842 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `symptom`
--

INSERT INTO `symptom` (`s_id`, `s_name`) VALUES
(1, 'Sporotrichotic chancre'),
(2, 'Other specified pruritic conditions (disorder)'),
(3, 'Todd''s paresis'),
(4, 'Failure to gain weight (finding)'),
(5, 'Involuntary movement'),
(6, 'Leukorrhoea unspecified'),
(7, 'Liver pain (finding)'),
(8, 'Spasmodic dysmenorrhea'),
(9, 'Pleurothotonus'),
(10, 'O/E - left eye preproliferative diabetic retinopathy'),
(11, 'Mucous membrane eruption'),
(12, 'Intermittent tremor'),
(13, 'Lump on finger'),
(14, '[D]Sleep rhythm irregular'),
(15, 'Generalized tenderness of breast'),
(16, 'Purulent nipple discharge'),
(17, 'Syncope symptom'),
(18, 'Fetal distress - in labor'),
(19, 'Pruritus of genital organs'),
(20, 'Vomiting after gastrointestinal tract surgery'),
(21, 'Discharge of eye'),
(22, 'Immune complex urticaria'),
(23, 'Intercostal neuralgia'),
(24, 'Pain in thyroid'),
(25, 'Paraparesis (disorder)'),
(26, 'Lid lag'),
(27, 'Persistent light reaction'),
(28, 'Ear pressure sensation'),
(29, 'Extrinsic staining of teeth associated with oral habits'),
(30, 'Abdominal angina'),
(31, 'Impairment level: low vision of both eyes'),
(32, 'Menstrual urticaria'),
(33, 'Swelling of vagina'),
(34, 'Abnormal gait'),
(35, 'Mass of lymphoreticular structure'),
(36, 'Clavicle pain'),
(37, 'Bowels: occasional accident'),
(38, 'Diurnal dystonia'),
(39, 'Chronic constipation'),
(40, 'Prostatorrhea'),
(41, 'O/E - referable retinopathy'),
(42, '(Spastic hemiplegia) or (spastic foot)'),
(43, 'Amaurosis fugax'),
(44, 'Paroxysmal hematoma of the finger'),
(45, 'Postoperative nausea'),
(46, 'Post dural puncture headache'),
(47, 'Watering or tearing eyes (& [C/O])'),
(48, 'Contact lens related red eye'),
(49, '[D]Coma &/or [D]stupor'),
(50, 'Scintillating scotoma'),
(51, '[D]Local superficial swelling, mass or lump NOS'),
(52, 'Paroxysmal dystonia'),
(53, 'Double incontinence (finding)'),
(54, 'Pain in penis (finding)'),
(55, 'Has watering eyes'),
(56, 'Tinnitus of vascular origin'),
(57, 'Urticaria geographica'),
(58, 'O/E - right eye preproliferative diabetic retinopathy'),
(59, 'Subepidermal edema'),
(60, 'Mass of urinary system structure'),
(61, 'Spasm (finding)'),
(62, 'Cry of Down''s syndrome'),
(63, 'Neurogenic pain'),
(64, 'Physiological development failure'),
(65, '[D]Enuresis NOS'),
(66, 'Enuresis (& [bedwetting])'),
(67, 'Lesion of penis'),
(68, 'Cullen''s sign'),
(69, 'Traumatic neonatal facial cyanosis'),
(70, 'Swallowing symptoms'),
(71, 'Scrotal pruritus'),
(72, 'Involuntary truncal rocking'),
(73, 'Food intolerance'),
(74, 'Peripheral neurogenic pain'),
(75, 'Subtalar joint stiff'),
(76, 'Flaccidity - muscle'),
(77, 'Metatarsophalangeal joint laxity'),
(78, 'Clonus'),
(79, 'Decreased muscle tone'),
(80, 'Gummatous inflammation (morphologic abnormality)'),
(81, 'Internal ophthalmoplegia'),
(82, 'Calf muscle weakness'),
(83, '[D]Other speech disturbance NOS'),
(84, '[D]Jaundice (not of newborn)'),
(85, '(Sore lip) or (angular stomatitis &/or cheilitis)'),
(86, 'Pseudoophthalmoplegia (disorder)'),
(87, 'Excessive growth of facial hair'),
(88, 'Drop attack'),
(89, 'Paralysis of palate'),
(90, 'Sore nostril'),
(91, '[D]Respiratory abnormalities'),
(92, 'Paroxysmal vascular dilatation'),
(93, 'Ring scotoma'),
(94, 'Chest wall pain (finding)'),
(95, 'Palatal myoclonus'),
(96, 'Periorbital edema'),
(97, 'Loss of sense of smell'),
(98, 'Excessive crying of newborn'),
(99, 'Spasmodic torticollis (disorder)'),
(100, 'Edema of uvula'),
(101, 'Thickening of tendon sheath'),
(102, 'Trypanosomal chancre'),
(103, 'Sacral back pain'),
(104, 'Generalized tenderness in pinna'),
(105, 'Headache following lumbar puncture'),
(106, 'Deafness symptom'),
(107, 'Mass of endocrine structure'),
(108, 'Adiadochokinesia'),
(109, 'Abnormal reflex'),
(110, 'Painful arms and moving fingers'),
(111, 'Macerated skin'),
(112, 'Idiopathic stabbing headache'),
(113, 'Floppy muscles'),
(114, 'Dryness of vulva'),
(115, 'Primary enuresis'),
(116, '[X]Other and unspecified symbolic dysfunctions'),
(117, 'Perineal irritation'),
(118, 'Hypoesthesia of tongue'),
(119, 'Peau d''orange surface of breast'),
(120, 'Diaphragmatic tic'),
(121, 'Dry eyes'),
(122, 'Bladder distention (finding)'),
(123, 'Alteration in comfort: chronic pain'),
(124, 'Exophthalmos due to orbital edema or congestion'),
(125, 'Thickening of skin'),
(126, 'Back pain complicating pregnancy'),
(127, 'Mass of uterine adnexa'),
(128, 'Pain in female genitalia'),
(129, 'Nystagmus unchanged when fixation removed'),
(130, 'Excessive sweating'),
(131, 'Bilateral central hearing loss'),
(132, 'Alcohol induced hallucinations'),
(133, '(Visual disturbances NOS) or (amblyopia) or (blurred vision)'),
(134, 'Malignant bone pain'),
(135, 'Mass of digestive structure'),
(136, 'Disturbance in sleep behavior'),
(137, 'Joint stiffness'),
(138, 'Flatulence, eructation and gas pain'),
(139, 'Complete spermatogenic arrest'),
(140, 'Auspitz''s sign'),
(141, 'Mass of oral cavity'),
(142, 'Musculoskeletal hypomobility'),
(143, '[D]Sleep disturbances (& [hypersomnia] or [insomnia])'),
(144, 'Popeye sign'),
(145, 'Glossopyrosis (disorder)'),
(146, 'Polyalgia'),
(147, 'Oliguria (finding)'),
(148, 'Oliguria and anuria'),
(149, 'Wry neck/torticollis'),
(150, 'Quadriceps weakness'),
(151, 'Engorgement of breasts'),
(152, 'Rosenbach''s sign'),
(153, 'Scaphoid abdomen'),
(154, 'Penile warts'),
(155, 'Chapping of lips'),
(156, 'Headache (finding)'),
(157, 'Risus sardonicus'),
(158, 'Carpopedal spasm'),
(159, 'Clasp knife like increase in tone'),
(160, 'Crossed extensor reflex'),
(161, 'Tuberculous chancre'),
(162, 'Labile pulse'),
(163, 'Numbness of tongue'),
(164, 'Resting tremor'),
(165, 'Grooving of nail'),
(166, 'O/E - right eye background diabetic retinopathy'),
(167, 'Nasal congestion'),
(168, 'Pill rolling'),
(169, '[D]Extrarenal uraemia'),
(170, 'Occipital headache'),
(171, 'Unqualified visual loss of both eyes'),
(172, 'Drug-induced xerostomia'),
(173, 'Pseudothalamic pain'),
(174, '[X]Other forms of angina pectoris'),
(175, 'Scleral discoloration'),
(176, 'Maxillary sinus pain'),
(177, 'Constipation (disorder)'),
(178, 'Clinical sign related to pregnancy'),
(179, 'Pain during outflow of dialysate'),
(180, 'Koilonychia'),
(181, 'Intermittent torticollis'),
(182, '[D]Jaundice (not of newborn) NOS'),
(183, 'Mass of respiratory structure'),
(184, '[X]Other signs and symptoms in breast'),
(185, 'Obturator neuralgia'),
(186, 'Postoperative pain'),
(187, 'Tenalgia'),
(188, 'Teeth staining due to drugs'),
(189, 'Femoral neuralgia'),
(190, 'Perineal lump (finding)'),
(191, 'Gestational edema without hypertension'),
(192, 'Sympathetically maintained pain'),
(193, 'Edema of oral vestibule'),
(194, 'Sciatica (disorder)'),
(195, 'Acute vomiting'),
(196, 'Vitreous cavitation'),
(197, '[D]Clicking thumb (context-dependent category)'),
(198, '[D]Swelling, mass or lump of chest NOS'),
(199, 'Appetite loss - anorexia'),
(200, 'Abnormal jaw movement'),
(201, 'Unpleasant odor of genitalia'),
(202, 'O/E - left eye proliferative diabetic retinopathy'),
(203, 'O/E - non-referable retinopathy'),
(204, 'Paroxysmal hypertension'),
(205, 'Other back symptoms (disorder)'),
(206, 'Visual symptoms'),
(207, 'Mass of cardiovascular structure'),
(208, 'Mood hypersomnia'),
(209, 'Neonatal cyanosis'),
(210, 'Absence of sensation (finding)'),
(211, 'Melanin pigmentation of cornea'),
(212, 'Retinal arteries silverwire'),
(213, 'Bowel spasm'),
(214, 'Symptom occurs premenstrually'),
(215, 'Insomnia (disorder)'),
(216, 'Absent deglutition (finding)'),
(217, 'Chromatic aberration of vision'),
(218, 'Puffiness of skin'),
(219, 'Virilization (finding)'),
(220, '[D]Urinary system symptoms NOS'),
(221, 'Starvation (disorder)'),
(222, 'Syncope and collapse'),
(223, 'Grimaces'),
(224, 'Pitting edema'),
(225, 'Body positional nystagmus'),
(226, 'Menstruation absent'),
(227, 'Pattern of fever - finding'),
(228, 'Pain in thoracic spine'),
(229, 'Sclerotomal pain'),
(230, 'Tonic-clonic seizure'),
(231, 'Unilateral facial paresis'),
(232, 'Urticarial vasculitis (disorder)'),
(233, 'Neuromyopathic fecal incontinence'),
(234, 'Knee locking'),
(235, 'Visual hallucinations'),
(236, 'Sensation of pressure in ear'),
(237, 'Intestinal angina'),
(238, 'Muscle twitch (finding)'),
(239, 'Cholinergic pruritus'),
(240, 'Unformed visual hallucinations'),
(241, 'Myoclonic disorder'),
(242, 'Finding of vomiting'),
(243, 'Chronic progressive paraparesis'),
(244, 'Difficulty breathing'),
(245, 'Schistosomal pigment deposition'),
(246, '[D]Heart murmur, undiagnosed'),
(247, 'Leudet''s tinnitus'),
(248, 'Muscle tone atonic'),
(249, 'Sudden visual loss'),
(250, 'Mass of eye structure'),
(251, 'Hypersomnia (disorder)'),
(252, 'Mass of ear structure'),
(253, 'Exhaustion'),
(254, 'Oral paresthesia'),
(255, 'Localized tenderness of breast'),
(256, 'Spontaneous nystagmus'),
(257, 'Nystagmus produced by neck rotation'),
(258, 'Has a sore throat'),
(259, 'Painful mouth'),
(260, 'Musculoskeletal immobility'),
(261, 'Blanching of skin (finding)'),
(262, 'Leukoplakia of female genital organs'),
(263, 'Appendiceal colic'),
(264, '[D]Local superficial swelling, mass or lump'),
(265, 'Purulent nasal discharge'),
(266, 'Urticaria persistans'),
(267, 'Micropsia (disorder)'),
(268, 'Vulval varices in pregnancy'),
(269, 'Pain finding at anatomical site'),
(270, 'Watery eye'),
(271, 'Large liver'),
(272, 'Inflammation of vulva'),
(273, 'Aphonia paralytica'),
(274, 'Distended umbilical veins'),
(275, 'Beau''s lines'),
(276, 'Spots on skin (disorder)'),
(277, 'Splits in nails'),
(278, '[D]Enuresis'),
(279, 'Enuresis [D]'),
(280, 'Multisensory dizziness'),
(281, 'Muscle tension pain'),
(282, 'Achromia of skin (disorder)'),
(283, 'Vascularization of cornea'),
(284, 'Flaccidity of muscle of lower limb'),
(285, 'Flaccid paralysis'),
(286, 'Visual loss, both eyes unqualified'),
(287, 'Red eye'),
(288, 'Micropapular weal'),
(289, 'Scaly scalp'),
(290, 'Intra-abdominal collection'),
(291, 'Pain at rest due to peripheral vascular disease'),
(292, 'Cruralgia'),
(293, 'Hemiplegia (disorder)'),
(294, 'Diarrhea after gastrointestinal tract surgery'),
(295, '(Other joint symptoms of the pelvic region and thigh) or (hip snapping) or (irritable hip)'),
(296, 'Joint swelling'),
(297, 'Diplacusis'),
(298, 'Swelling of musculoskeletal structure'),
(299, 'O/E - retinopathy'),
(300, 'Posterior rhinorrhea (disorder)'),
(301, 'Abdominal migraine - symptom'),
(302, 'Pancreatic pain'),
(303, 'Mass of musculoskeletal structure'),
(304, 'Subjective vertigo'),
(305, 'Umbilical hemorrhage'),
(306, 'Chronic vomiting'),
(307, 'Diarrhea due to ingestion of unabsorbable substances'),
(308, 'Irritation of penis'),
(309, 'Edema of lower leg'),
(310, '(Leukorrhea unspecified) or (vaginal discharge NOS)'),
(311, 'Lamellar nail splitting'),
(312, 'Leukoplakia of male genital organs'),
(313, 'Perianal lump'),
(314, 'Projectile vomiting (disorder)'),
(315, 'Dysphonia of organic tremor'),
(316, 'Thyrotoxic tremor'),
(317, 'Trembles'),
(318, 'Static tremor'),
(319, 'Groin mass'),
(320, 'Narrowing of palpebral fissure'),
(321, 'Problem with balance'),
(322, 'Mixed incontinence'),
(323, 'Weal'),
(324, 'Pain during inflow of dialysate'),
(325, 'Mass of lower limb'),
(326, 'Discomfort in mouth'),
(327, 'Sympathetically independent pain'),
(328, 'Acute abdomen'),
(329, 'Painful ejaculation (finding)'),
(330, 'Ureteric neuromuscular incoordination'),
(331, 'Pain of digestive structure'),
(332, 'O/E - left eye background diabetic retinopathy'),
(333, 'Pain due to any device, implant AND/OR graft'),
(334, 'Harlequin change'),
(335, 'Rectal pain (finding)'),
(336, 'Dry eye'),
(337, 'Rubella arthralgia'),
(338, 'Formed visual hallucinations'),
(339, 'Congestion of nasal sinus'),
(340, '(Priapism) or (erection - painful)'),
(341, 'Snuffles'),
(342, 'Vomiting symptom'),
(343, 'Monocular diplopia'),
(344, 'Extrinsic staining of teeth associated with inadequate oral hygiene'),
(345, 'Nausea (finding)'),
(346, 'Burning sensation of vulva'),
(347, 'Reaction to sudden wakening'),
(348, 'Restrained respiration'),
(349, 'Neck positional nystagmus'),
(350, 'Pruritus of skin'),
(351, 'Tachypnea (finding)'),
(352, 'Weakness of face muscles'),
(353, 'Dorsalgia'),
(354, 'Smokers'' cough'),
(355, 'Arm claudication'),
(356, 'Chronic tremor'),
(357, 'Uncontrollable vomiting'),
(358, 'Bleeding from ear'),
(359, 'Malarial pigment deposition'),
(360, 'Pale complexion'),
(361, 'Total urinary incontinence (finding)'),
(362, 'Aniseikonia (disorder)'),
(363, 'Bruns nystagmus'),
(364, 'Ataxia (finding)'),
(365, 'Mass of body region'),
(366, 'Tooth sensitivity to cold'),
(367, 'Drug-induced vertigo'),
(368, 'Formed hallucinations of people'),
(369, 'Fetal distress - prelabor'),
(370, 'O/E - left eye diabetic maculopathy'),
(371, 'Cervical spine stiff'),
(372, 'Sacrocoxalgia'),
(373, 'Macule (morphologic abnormality)'),
(374, 'Positional nystagmus, variable eye direction'),
(375, 'Premenstrual breast tenderness'),
(376, 'Mass of male genital structure'),
(377, 'Hard edema'),
(378, '[D]Drug-induced vomiting'),
(379, 'Incomplete spermatogenic arrest'),
(380, 'Vaginal varices in the puerperium'),
(381, 'Pain in esophagus'),
(382, 'Nervous system symptoms'),
(383, 'Neurological symptom'),
(384, 'Generalized aches and pains (finding)'),
(385, 'Malaise associated with AIDS'),
(386, 'Diarrhea symptom'),
(387, 'Hypoproteinemia (disorder)'),
(388, 'Meningeal irritation'),
(389, 'Abnormal large bowel motility'),
(390, 'Pain from breast implant'),
(391, 'Chronic vaginal pain'),
(392, 'Indigestion (finding)'),
(393, 'Swallowing painful'),
(394, 'Menopausal problem'),
(395, 'Bitot''s spots'),
(396, 'Chest pain'),
(397, 'Disturbance of consciousness'),
(398, 'Pain in testicle (finding)'),
(399, 'Vas deferens tender'),
(400, 'Nausea and vomiting (disorder)'),
(401, 'Local spasm'),
(402, 'Familial annular erythema'),
(403, 'Abdominal pain'),
(404, 'Genital varices in the puerperium'),
(405, 'Myokymia (finding)'),
(406, 'Secondary endolymphatic hydrops'),
(407, 'Loss of memory'),
(408, 'Postoperative vomiting'),
(409, 'Near syncope'),
(410, 'Abnormal breathing'),
(411, 'Pain (finding)'),
(412, 'Pruritus of vagina'),
(413, 'Anomaly of divergence'),
(414, 'Bolus impaction'),
(415, 'Thunderclap headache'),
(416, '[D]Clicking thumb (situation)'),
(417, 'Reaction to spinal or lumbar puncture (disorder)'),
(418, 'Morning sickness'),
(419, '(Leukorrhoea unspecified) or (vaginal discharge NOS)'),
(420, 'Cachexia (finding)'),
(421, '[X]Other chest pain'),
(422, 'Delayed cold sensitivity'),
(423, 'Segmental peripheral neuralgia'),
(424, 'Chalcosis of cornea'),
(425, 'Acquired stammering'),
(426, '[X]Other chronic pain'),
(427, 'Finger joint laxity'),
(428, 'Unpleasant odor of axilla'),
(429, 'Angina'),
(430, 'Neuropathic pain'),
(431, 'Infrapatellar tenderness'),
(432, 'O/E - right eye proliferative diabetic retinopathy'),
(433, '(Backache NOS) or (back pain [& low])'),
(434, 'Mucopurulent conjunctival discharge'),
(435, 'Bone pain (finding)'),
(436, 'Acute edema'),
(437, 'Mouth symptoms'),
(438, 'Vulvovaginal pain'),
(439, 'Pseudoathetosis'),
(440, 'Contact lens related disorder'),
(441, 'Blood pigmentation of cornea'),
(442, 'Disorder of umbilicus'),
(443, 'Syncopal vertigo'),
(444, 'Lumbar ache - renal'),
(445, 'Junctional premature beats'),
(446, 'Subinvolution of breast'),
(447, 'Cramp in foot'),
(448, 'Nasal discharge'),
(449, 'Vulvovaginal dryness'),
(450, 'Nausea, vomiting and diarrhea'),
(451, 'Anorexia symptom (finding)'),
(452, 'Thin nails'),
(453, 'Circumoral rhytides'),
(454, 'Irregular nystagmus'),
(455, 'Gastric spasm'),
(456, 'Tremor (finding)'),
(457, 'Hyperactive rectosigmoid junction'),
(458, 'Spillage of feces'),
(459, 'Intermittent vomiting'),
(460, 'Reflex urticaria'),
(461, 'Swelling of eyelid (finding)'),
(462, 'Lateral femoral cutaneous neuralgia'),
(463, 'Transverse split nail'),
(464, 'Shoulder joint laxity'),
(465, 'Unqualified visual loss of one eye'),
(466, 'Paracusis (disorder)'),
(467, 'Breast subinvolution, postpartum'),
(468, 'Flaccid dysphonia'),
(469, 'Mass of urogenital structure'),
(470, 'Inflammatory testicular mass'),
(471, 'Loudness recruitment'),
(472, 'Dry skin (finding)'),
(473, 'Pruritus of pregnancy'),
(474, 'Discharge from nipple'),
(475, 'Menopausal sleeplessness'),
(476, 'Delayed dermographism'),
(477, 'Pain of head and neck region'),
(478, 'Fluid disturbance'),
(479, 'Range of joint movement increased'),
(480, 'Painful legs and moving toes'),
(481, 'Mixed nystagmus'),
(482, '[D]Other general symptoms NOS'),
(483, '[D]Other general symptoms'),
(484, 'Muscle pain'),
(485, '(Swallowing symptoms) or (dysphagia)'),
(486, 'Benign paroxysmal positional vertigo nystagmus'),
(487, 'Smooth pursuit movement deficiency'),
(488, 'Spermatemphraxis'),
(489, 'Loose stool'),
(490, '(Esophoria) or (strabismus NOS) or (ophthalmoplegia NOS)'),
(491, 'Sleep-related head banging'),
(492, 'Abnormal vaginal odor'),
(493, 'Parkinsonian tremor'),
(494, 'Flaccid tetraplegia'),
(495, 'Elliptical nystagmus'),
(496, '(Functional constipation) or (impacted faeces)'),
(497, 'Drug-induced apnea'),
(498, 'Tic-tac rhythm'),
(499, '[D]Widespread diabetic foot gangrene'),
(500, 'Metatarsalgia (finding)'),
(501, 'Poor muscle tone'),
(502, 'Postural nystagmus'),
(503, 'Dizziness and giddiness'),
(504, 'Diaphragmatic paresis'),
(505, 'Hyperemesis gravidarum'),
(506, 'Bullous weal'),
(507, 'Menopausal symptom'),
(508, 'Fetal bradycardia'),
(509, 'Persisting cholinergic urticaria'),
(510, 'Hypercapnia (disorder)'),
(511, 'Bigeminal pulse (finding)'),
(512, 'Psoriasiform rash'),
(513, 'Spastic paraparesis'),
(514, 'Slowness and poor responsiveness'),
(515, 'Low back pain'),
(516, 'Tender lymph node'),
(517, 'Testicular neuralgia'),
(518, 'Vertigo preceded by head injury'),
(519, 'Bad taste in mouth'),
(520, 'Subungual hyperkeratosis'),
(521, 'Pain in throat'),
(522, 'Muscle fasciculation'),
(523, 'Premenstrual symptom'),
(524, 'Synkinesis (finding)'),
(525, 'Post-hemiplegic chorea'),
(526, 'Catarrhal nasal discharge'),
(527, 'Cryoglobulinemic purpura'),
(528, 'Injection site pain'),
(529, '(Chronic rhinitis) or (catarrh unspecified)'),
(530, 'Drug pigmentation'),
(531, 'Benign congenital hypotonia (disorder)'),
(532, 'Erythematous mucosa'),
(533, 'Recurrent abdominal pain'),
(534, 'Spasticity'),
(535, 'Nystagmus signs'),
(536, 'Prostatic pain (finding)'),
(537, 'Emaciated'),
(538, 'Onychomadesis'),
(539, 'Frontal sinus pain'),
(540, 'Vulval irritation'),
(541, 'Vomiting in newborn'),
(542, 'Low vision, both eyes'),
(543, 'Staggering gait (finding)'),
(544, 'Drummers'' palsy'),
(545, 'Pulsus trigeminus (finding)'),
(546, 'Sore mouth (finding)'),
(547, 'Pain in the coccyx'),
(548, 'Cervicobrachialgia'),
(549, 'Vasovagal symptom'),
(550, '[D]Weight, failure to gain, infant'),
(551, 'Weight,fail to gain,infant [D]'),
(552, 'Stridor (finding)'),
(553, 'Stellate pseudoscar'),
(554, 'Loosening of tooth (disorder)'),
(555, 'Total scleral ectasia'),
(556, 'Peripheral neuropathic pain'),
(557, 'Pruritus of penis'),
(558, 'Sleep automatism'),
(559, 'Uterine incoordination, first degree'),
(560, 'Cheilodynia (disorder)'),
(561, 'Seizure'),
(562, 'Fetal souffle'),
(563, 'Sleep dysfunction with arousal disturbance'),
(564, 'Newborn physiological jaundice (disorder)'),
(565, 'Spasm of conjugate gaze'),
(566, 'Cerebellar ataxia'),
(567, 'Subsultus tendinum'),
(568, 'Effort syncope'),
(569, 'Hyperoxia (disorder)'),
(570, 'Skin irritation'),
(571, 'Genitofemoral nerve neuralgia'),
(572, 'Gaze paretic nystagmus'),
(573, 'Quadrantic scotoma'),
(574, '[D]Coma and stupor NOS'),
(575, 'Cold reflex urticaria'),
(576, '(Other back symptoms) or (facet joint syndrome)'),
(577, 'Clubbing of nail (disorder)'),
(578, 'Pain due to shoulder joint prosthesis'),
(579, 'Brain stem vertigo'),
(580, 'Unilateral shortlasting neuralgiform pain with conjunctival injection and tearing syndrome'),
(581, 'Fine tremor'),
(582, 'Purulent rhinitis'),
(583, 'Tendon sheath effusion'),
(584, 'Glossodynia (disorder)'),
(585, 'Mass of trunk'),
(586, 'Spurious diarrhea - overflow'),
(587, 'Diffuse inflammation'),
(588, 'Coordination problem'),
(589, 'Nystagmus inhibited when fixation removed'),
(590, 'Pain of oral cavity structure'),
(591, 'Sleep dysfunction with sleep stage disturbance'),
(592, 'Mass of female genital structure'),
(593, 'Tenderness of male genitalia'),
(594, 'Clinical finding'),
(595, 'Erythema fugax'),
(596, 'Other newborn abnormal cerebral signs'),
(597, 'Spastic dysphonia'),
(598, 'Sensation of blocked ears'),
(599, 'Edema of lower extremity'),
(600, 'Menouria'),
(601, 'Edema of newborn'),
(602, 'Breastfeeding painful'),
(603, 'Chronic abdominal pain'),
(604, 'Edema of spinal cord'),
(605, 'Joint effusion of other specified site'),
(606, 'Grunting respiration (finding)'),
(607, 'Inanition (disorder)'),
(608, 'Persistent testicular pain'),
(609, 'Paroxysmal nocturnal dyspnea (finding)'),
(610, 'Motor dysfunction'),
(611, 'Massive tremor'),
(612, 'Anal pain (finding)'),
(613, 'Abnormal granulation tissue'),
(614, 'Snuffles (& neonatal snuffles)'),
(615, 'Increased muscle tone'),
(616, 'Mucus conjunctival discharge'),
(617, '[D]Other speech disturbance'),
(618, 'Erythema of skin'),
(619, 'Eyes tire easily'),
(620, 'Abnormal complexion'),
(621, 'Hypertensive spasm of cardiac sphincter'),
(622, 'Visual alteration'),
(623, 'Partial seizure'),
(624, 'Joint pain'),
(625, 'Erythema scarlatiniforme'),
(626, 'Cerebral paraparesis'),
(627, 'Menopausal headache'),
(628, 'Other sleep stage or arousal dysfunction'),
(629, 'Edema of penis'),
(630, 'Cri du chat'),
(631, 'Incomitant esophoria (disorder)'),
(632, 'Rhinitis due to alpha-adrenergic blocking agent'),
(633, 'Wrist joint laxity'),
(634, '[D]Swelling, mass and lump of chest'),
(635, 'Painful blind eye'),
(636, 'Orthostatic tremor'),
(637, 'Loss of voice'),
(638, 'Meningococcal rash'),
(639, 'Ilioinguinal nerve neuralgia'),
(640, 'Genitourinary symptoms'),
(641, 'Congenital fecal incontinence'),
(642, 'Sore throat symptom'),
(643, 'Involuntary movement symptom'),
(644, 'Pruritus of vulva'),
(645, 'Saber shin'),
(646, 'Nodule on tendon'),
(647, 'Ischemic chest pain'),
(648, 'Transient monoplegia'),
(649, 'Vascular engorgement of breast'),
(650, '[D]Urinary system symptoms'),
(651, 'Daytime enuresis'),
(652, 'Subjective visual disturbance'),
(653, 'Intermittent fever'),
(654, 'Radicular pain'),
(655, 'Respiratory distress (finding)'),
(656, 'Oral hypoesthesia'),
(657, 'Generalized chronic body pains'),
(658, 'Painful sexual act of male'),
(659, 'Sacroiliac joint pain'),
(660, 'Respiration intermittent'),
(661, 'Hypersomnia with sleep apnea'),
(662, 'Spastic pseudobulbar dysphonia'),
(663, 'Grand mal seizure'),
(664, 'Neonatal facial petechiae'),
(665, 'Upper facial weakness'),
(666, 'Spasm of bladder'),
(667, 'Nystagmus enhanced when fixation removed'),
(668, 'Pruritus ani'),
(669, 'Sinus catarrh'),
(670, 'Thumb joint laxity'),
(671, 'Cyanosis of skin'),
(672, 'Opisthotonus (finding)'),
(673, 'Pruritus of male genital organs'),
(674, 'Nasal discharge present'),
(675, 'Flaccidity of muscle of upper limb'),
(676, 'Visual distortions of shape AND/OR size'),
(677, 'Hemiballism (disorder)'),
(678, 'Spherical aberration of vision'),
(679, 'Pain in male perineum'),
(680, 'Hepatocellular jaundice'),
(681, 'Premenstrual swelling'),
(682, 'Subtalar joint laxity'),
(683, 'Extensive congenital erosions, vesicles and reticulate scarring'),
(684, 'Itching of skin'),
(685, 'Itch of skin'),
(686, 'Epileptic aura'),
(687, 'Polyuric state'),
(688, 'Pancreatic symptom'),
(689, 'Tooth sensitivity to heat'),
(690, 'Paresthesia (finding)'),
(691, 'Postpartum subinvolution of breast'),
(692, 'Central cyanosis (disorder)'),
(693, 'Absent peristalsis'),
(694, 'Bronchorrhea'),
(695, 'Appendicular colic'),
(696, 'Sore lip (finding)'),
(697, 'Other specified visual disturbance NOS'),
(698, 'Other specified visual disturbance'),
(699, 'Voluntary nystagmus'),
(700, 'Skin sensation disturbance'),
(701, 'Dacryops'),
(702, 'Swelling of urological structure'),
(703, 'Athetoid movement'),
(704, 'Nipple discharge symptom'),
(705, 'Prominent leg veins - symptom'),
(706, 'Angioscotoma'),
(707, 'Indigestion symptoms'),
(708, 'Joint laxity'),
(709, '(Bathing cramps - immersion) or (immersion)'),
(710, 'Hyperesthesia (finding)'),
(711, 'Flaccid paraplegia'),
(712, 'Edema of male genital organs'),
(713, 'Swallowing problem'),
(714, 'Sucrose intolerance'),
(715, 'Sore gums'),
(716, 'Prostatism (disorder)'),
(717, 'Chronic intractable pain'),
(718, 'Monoparesis - arm'),
(719, 'Deficiency of smooth pursuit movements'),
(720, 'Skeletal muscle tender (finding)'),
(721, 'Colicky pain'),
(722, 'Swelling of ear structure'),
(723, 'Pustular rash (morphologic abnormality)'),
(724, 'Swelling of limb (finding)'),
(725, 'Polymyalgia'),
(726, 'Pain of endocrine structure'),
(727, '(Functional constipation) or (impacted feces)'),
(728, 'Flaccidity'),
(729, 'Hypocapnia (disorder)'),
(730, 'Cramp (finding)'),
(731, 'Muscular hypertonicity'),
(732, 'Dysdiadochokinesis'),
(733, 'Localized retinal edema'),
(734, 'Painful hematuria'),
(735, 'Refractive diplopia'),
(736, 'Memory loss symptom'),
(737, 'Ophthalmoplegia (disorder)'),
(738, '[D]Respiratory abnormalities NOS'),
(739, 'Paresthesia of tongue'),
(740, '[D] Post polio exhaustion'),
(741, 'Edema of oral soft tissues'),
(742, 'Application site anesthesia'),
(743, 'Painful swelling of joint'),
(744, 'Fecal fluid leakage'),
(745, 'Lump on thigh'),
(746, 'Sacroiliac joint stiff'),
(747, 'Unpleasant taste in mouth'),
(748, 'Leukorrhea unspecified'),
(749, 'Hepatomegaly associated with AIDS'),
(750, 'Impairment of balance'),
(751, 'Clubbing (morphologic abnormality)'),
(752, 'Coarse tremor'),
(753, 'Symptomatic disorders in pregnancy'),
(754, 'Sublingual sialectasia'),
(755, 'Anal twitching'),
(756, 'Temporomandibular joint stiff'),
(757, 'Hepatosplenomegaly'),
(758, 'Menopausal concentration lack'),
(759, 'Burning scrotum'),
(760, 'Wrist clonus'),
(761, 'Tenderness of thyroid'),
(762, 'C/O - cough'),
(763, 'Withdrawal sign or symptom'),
(764, '[D]Other symbolic dysfunction'),
(765, 'Reminiscent neuralgia'),
(766, 'Toothache (finding)'),
(767, 'Foot joint laxity'),
(768, 'Neonatal dyskinesia'),
(769, 'Uniocular nystagmus'),
(770, 'Tenderness of oral cavity structure'),
(771, 'Urge incontinence of urine'),
(772, 'Vertigo associated with seizures'),
(773, 'Oculopalatal myoclonus'),
(774, 'Blepharospasm (disorder)'),
(775, 'Melasma gravidarum'),
(776, 'Pain on penetration'),
(777, 'Excessive growth of body hair'),
(778, 'Dystonia (finding)'),
(779, 'Nail shedding'),
(780, 'Change in nail appearance'),
(781, 'Postviral excessive daytime sleepiness'),
(782, 'Visual discomfort'),
(783, 'Pain in scrotum (finding)'),
(784, 'Peripheral retinal edema'),
(785, 'Actinic reaction'),
(786, 'Swelling of structure of eye'),
(787, 'Persistent tremor'),
(788, 'Painful tooth socket'),
(789, 'Pain from tissue expander'),
(790, 'Chronic pelvic pain of female'),
(791, 'Juvenile spring eruption'),
(792, 'Erythematous ear canal'),
(793, 'Spinal paraparesis'),
(794, 'Visual loss, one eye, unqualified'),
(795, '[D]Coma and stupor'),
(796, 'Changes in skin texture'),
(797, 'Courvoisier''s sign'),
(798, 'Psychic dysuria'),
(799, 'Spermatoschesis'),
(800, 'Excessive growth of leg hair'),
(801, 'Pain in lower limb (finding)'),
(802, 'Cracked lips'),
(803, 'Indigestion symptom NOS'),
(804, 'Monoparesis (disorder)'),
(805, 'Face goes red'),
(806, 'Corneal pigmentations and deposits'),
(807, 'Sacroiliac joint laxity'),
(808, 'Oral choreiform movement'),
(809, 'Gingival edema'),
(810, 'Painful bladder spasm'),
(811, 'Splitting toenail'),
(812, 'Sore skin (finding)'),
(813, 'Scalp itchy'),
(814, 'Retina finding'),
(815, '[D]Extrarenal uremia'),
(816, 'Postbasic stare'),
(817, 'Pain due to knee joint prosthesis'),
(818, 'Self-neglect'),
(819, 'Tenderness of lymphoreticular structure'),
(820, 'Lid retraction &/or lag'),
(821, 'Painful penile erection'),
(822, 'Passive tremor'),
(823, 'Blood streaked sputum'),
(824, 'Osler''s node'),
(825, 'Head movements abnormal'),
(826, 'Hematocolpometra'),
(827, 'Tonic seizure'),
(828, 'Injection site hypersensitivity'),
(829, 'O/E - right eye diabetic maculopathy'),
(830, 'Erythematous condition'),
(831, 'Sialectasia'),
(832, 'Fatigue associated with AIDS'),
(833, 'Paradoxical diaphragmatic movement'),
(834, 'Persistent pain following procedure'),
(835, 'Stinging of skin'),
(836, 'Macular eruption'),
(837, 'Continuous tremor'),
(838, 'Stahli''s line'),
(839, 'Elbow joint laxity'),
(840, 'Umbilical bleeding'),
(841, 'Gastrointestinal symptom');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `u_id` int(11) NOT NULL,
  `u_accesstoken` text NOT NULL,
  `u_emailid` text NOT NULL,
  `u_password` text NOT NULL,
  `u_status` tinyint(4) NOT NULL,
  `r_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`u_id`, `u_accesstoken`, `u_emailid`, `u_password`, `u_status`, `r_id`) VALUES
(2, '558886aed687064296c280cca89da9746731c5a3d000fcf268e11f8c95678a0f', 'rohan.t@gmail.com', 'a4232054c2fc1ff21e8d911d0669df5e636562b9da8c14ecaa8fddc0f92e070c', 1, 3),
(5, '8d896aa3482a55e0a36776814b6d0d2d2cc689893ff9f8492d57342255eddeea', 'admin@healthexpert.tk', '7676aaafb027c825bd9abab78b234070e702752f625b752e55e55b48e607e358', 1, 1),
(7, '01239254145e44665f0a31b786ff3d5d4af7bfd358769a85acf71435db2813ce', 'archishthakkar@gmail.com', '7676aaafb027c825bd9abab78b234070e702752f625b752e55e55b48e607e358', 1, 3),
(14, '8877685efad32488214097aa90357084608875d5765d767cad2cf9e29def2ad5', 'ramesh.p@gmail.com', '4c75befceb74064dd44eaf096a6610c55d006b1017dca7a95db690d24b53d78d', 1, 2),
(15, '502cda6bf013ca8bd28b060106ba2089d59f2a775338a1476168fbb273ae8d4d', 'shivani.s@gmail.com', 'f16cc737a0712980930a04ba79a7657b7c2d59a34febaa915b4d3b606e729898', 1, 2),
(16, 'c2ce32f2b7a5392f830a96ae4909cbef1e73021645072f9ee40a8b7bcc419c6a', 'gayatri.mul@gmail.com', 'cd069c55a0ae4a1fe22d26707393b596d0403a165b85e303f2e4b0038f0c6de0', 1, 2),
(17, '9af907ce318a91843be8d1107c7ca8289ef21aaf452fc9b93c2c24467054bfa2', 'viral.p@gmail.com', '826d78bec82493b5ec93c504cdd58917fec7e827d1aec535d649aef0034fbc69', 1, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bookmarks`
--
ALTER TABLE `bookmarks`
  ADD PRIMARY KEY (`b_id`);

--
-- Indexes for table `disease`
--
ALTER TABLE `disease`
  ADD PRIMARY KEY (`d_id`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`d_id`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`p_id`);

--
-- Indexes for table `patient_details`
--
ALTER TABLE `patient_details`
  ADD PRIMARY KEY (`pd_id`);

--
-- Indexes for table `prescription`
--
ALTER TABLE `prescription`
  ADD PRIMARY KEY (`p_id`);

--
-- Indexes for table `rating`
--
ALTER TABLE `rating`
  ADD PRIMARY KEY (`r_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`r_id`);

--
-- Indexes for table `speciality`
--
ALTER TABLE `speciality`
  ADD PRIMARY KEY (`s_id`);

--
-- Indexes for table `symptom`
--
ALTER TABLE `symptom`
  ADD PRIMARY KEY (`s_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`u_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bookmarks`
--
ALTER TABLE `bookmarks`
  MODIFY `b_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=32;
--
-- AUTO_INCREMENT for table `disease`
--
ALTER TABLE `disease`
  MODIFY `d_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=150;
--
-- AUTO_INCREMENT for table `doctor`
--
ALTER TABLE `doctor`
  MODIFY `d_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `p_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `patient_details`
--
ALTER TABLE `patient_details`
  MODIFY `pd_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `prescription`
--
ALTER TABLE `prescription`
  MODIFY `p_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `rating`
--
ALTER TABLE `rating`
  MODIFY `r_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `r_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `speciality`
--
ALTER TABLE `speciality`
  MODIFY `s_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=25;
--
-- AUTO_INCREMENT for table `symptom`
--
ALTER TABLE `symptom`
  MODIFY `s_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=842;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `u_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=18;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
