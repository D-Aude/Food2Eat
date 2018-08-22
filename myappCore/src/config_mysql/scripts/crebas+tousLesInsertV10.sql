/*==============================================================*/
/* Nom de SGBD :  MySQL 5.0                                     */
/* Date de création :  18/08/2018 15:59:12                      */
/*==============================================================*/
create database food2eat_db;
use food2eat_db;

drop table if exists ADRESSE;

drop table if exists ANNONCE;

drop table if exists ANNULATION;

drop table if exists CATEGORIE;

drop table if exists EVALUATION;

drop table if exists FOODFRIEND;

drop table if exists GROUPE;

drop table if exists MESURE;

drop table if exists MODESINSCRIPTION;

drop table if exists MODE_CONSERVATION;

drop table if exists MOJETER;

drop table if exists PRODUIT;

drop table if exists REPANNONCE;

drop table if exists STOCK;

drop table if exists USERADRESSE;

drop table if exists UTILISATEUR;

drop table if exists VILLE;

/*==============================================================*/
/* Table : ADRESSE                                              */
/*==============================================================*/
create table ADRESSE
(
   ID_ADRESSE           int not null auto_increment,
   ID_VILLE             int not null,
   NUMERO_VOIE          int,
   NOM_VOIE             varchar(100),
   COMPLEMENT_ADRESSE   varchar(50),
   TYPE_VOIE            varchar(50),
   DATE_DEBUT_VALIDITE  date,
   DATE_FIN_VALIDITE    date,
   X                    double,
   Y                    double,
   primary key (ID_ADRESSE)
);

/*==============================================================*/
/* Table : ANNONCE                                              */
/*==============================================================*/
create table ANNONCE
(
   ID_ANNONCE           int not null auto_increment,
   ID_STOCK             int not null,
   ID_ADRESSE           int not null,
   ID_ANNULATION        int,
   TITRE                varchar(150),
   DATE_PUBLICATION     date,
   DATE_RDV_1           datetime,
   DATE_RDV_2           datetime,
   DATE_RDV_3           datetime,
   DATE_ANNULATION      date,
   DATE_FIN_ANNONCE     date,
   primary key (ID_ANNONCE)
);

/*==============================================================*/
/* Table : ANNULATION                                           */
/*==============================================================*/
create table ANNULATION
(
   ID_ANNULATION        int not null auto_increment,
   NOM_ANNULATION       varchar(100) not null,
   primary key (ID_ANNULATION)
);

/*==============================================================*/
/* Table : CATEGORIE                                            */
/*==============================================================*/
create table CATEGORIE
(
   ID_CATEGORIE         int not null auto_increment,
   ID_GROUPE            int not null,
   NOM_CATEGORIE        varchar(100) not null,
   JOURS_EXTENSION_CATEGORIE int,
   primary key (ID_CATEGORIE)
);

/*==============================================================*/
/* Table : EVALUATION                                           */
/*==============================================================*/
create table EVALUATION
(
   ID_EVALUATION        int not null auto_increment,
   ID_REPONSE           int not null,
   DATE_EVALUATION      date,
   NOTE                 int,
   COMMENTAIRE          varchar(300),
   primary key (ID_EVALUATION)
);

/*==============================================================*/
/* Table : FOODFRIEND                                           */
/*==============================================================*/
create table FOODFRIEND
(
   ID_FOODFRIEND        int not null auto_increment,
   ID_UTILISATEUR       int not null,
   UTI_ID_UTILISATEUR   int not null,
   DATE_DEMANDE         date,
   DATE_REPONSE         date,
   DATE_FIN_RELATION    date,
   primary key (ID_FOODFRIEND)
);

/*==============================================================*/
/* Table : GROUPE                                               */
/*==============================================================*/
create table GROUPE
(
   ID_GROUPE            int not null auto_increment,
   NOM_GROUPE           varchar(100) not null,
   primary key (ID_GROUPE)
);

/*==============================================================*/
/* Table : MESURE                                               */
/*==============================================================*/
create table MESURE
(
   ID_MESURE            int not null auto_increment,
   NOM_MESURE           varchar(100) not null,
   DENOMBRABLE          bool not null,
   primary key (ID_MESURE)
);

/*==============================================================*/
/* Table : MODESINSCRIPTION                                     */
/*==============================================================*/
create table MODESINSCRIPTION
(
   ID_MOTIF_DESINSCRIPTION int not null auto_increment,
   MOTIF_DESISCRIPTION  varchar(200) not null,
   primary key (ID_MOTIF_DESINSCRIPTION)
);

/*==============================================================*/
/* Table : MODE_CONSERVATION                                    */
/*==============================================================*/
create table MODE_CONSERVATION
(
   ID_MODE_CONSERVATION int not null auto_increment,
   NOM_MODE_CONSERVATION varchar(100) not null,
   JOURS_EXTENSION_CONSERVATION int,
   primary key (ID_MODE_CONSERVATION)
);

/*==============================================================*/
/* Table : MOJETER                                              */
/*==============================================================*/
create table MOJETER
(
   ID_MOTIF_JETER       int not null auto_increment,
   NOM_MOTIF_JETER      varchar(150) not null,
   primary key (ID_MOTIF_JETER)
);

/*==============================================================*/
/* Table : POSSEDER                                             */
/*==============================================================*/
create table POSSEDER
(
   ID_UTILISATEUR       int not null,
   ID_ADRESSE           int not null,
   PRINCIPALE           bool,
   primary key (ID_UTILISATEUR, ID_ADRESSE)
);

/*==============================================================*/
/* Table : PRODUIT                                              */
/*==============================================================*/
create table PRODUIT
(
   ID_PRODUIT           int not null auto_increment,
   ID_MODE_CONSERVATION int not null,
   ID_CATEGORIE         int not null,
   ID_MESURE            int not null,
   NOM_PRODUIT          varchar(150),
   NOMBRE_UNITE         int,
   DATE_PROPOSITION     date,
   DATE_ACTIVATION      date,
   DATE_DESACTIVATION   date,
   primary key (ID_PRODUIT)
);

/*==============================================================*/
/* Table : REPANNONCE                                           */
/*==============================================================*/
create table REPANNONCE
(
   ID_REPONSE           int not null auto_increment,
   ID_UTILISATEUR       int not null,
   ID_ANNONCE           int not null,
   DATE_REPONSE         date,
   DATE_ACCEPTATION_REPONSE date,
   DATE_RDV             datetime,
   DATE_ANNULATION_REPONSE date,
   DATE_REFUS           date,
   primary key (ID_REPONSE)
);

/*==============================================================*/
/* Table : STOCK                                                */
/*==============================================================*/
create table STOCK
(
   ID_STOCK             int not null auto_increment,
   ID_PRODUIT           int not null,
   ID_UTILISATEUR       int not null,
   ID_MOTIF_JETER       int,
   QUANTITE             int,
   FRACTION_RESTANTE    int,
   DATE_ACHAT           date,
   DLC                  date,
   DATE_CONSO_PREF      date,
   DATE_MANGER          date,
   DATE_JETER           date,
   ENTAME               bool,
   primary key (ID_STOCK)
);

/*==============================================================*/
/* Table : USERADRESSE                                          */
/*==============================================================*/
create table USERADRESSE
(
   ID_UTILISATEUR       int not null,
   ID_ADRESSE           int not null,
   PRINCIPALE           bool,
   primary key (ID_UTILISATEUR, ID_ADRESSE)
);

/*==============================================================*/
/* Table : UTILISATEUR                                          */
/*==============================================================*/
create table UTILISATEUR
(
   ID_UTILISATEUR       int not null auto_increment,
   ID_MOTIF_DESINSCRIPTION int,
   PRENOM               varchar(50),
   NOM                  varchar(50),
   DATE_DE_NAISSANCE    date,
   PSEUDO               varchar(15),
   MDP                  varchar(15),
   EMAIL                varchar(50),
   SEXE                 bool,
   DATE_INSCRIPTION     date,
   DATE_DESINSCRIPTION  date,
   primary key (ID_UTILISATEUR)
);

/*==============================================================*/
/* Table : VILLE                                                */
/*==============================================================*/
create table VILLE
(
   ID_VILLE             int not null auto_increment,
   CODE_POSTAL          varchar(5) not null,
   VILLE                varchar(50) not null,
   primary key (ID_VILLE)
);

alter table ADRESSE add constraint FK_LOCALISER foreign key (ID_VILLE)
      references VILLE (ID_VILLE) on delete restrict on update restrict;

alter table ANNONCE add constraint FK_CONCERNER foreign key (ID_ADRESSE)
      references ADRESSE (ID_ADRESSE) on delete restrict on update restrict;

alter table ANNONCE add constraint FK_DONNER foreign key (ID_STOCK)
      references STOCK (ID_STOCK) on delete restrict on update restrict;

alter table ANNONCE add constraint FK_JUSTIFIER foreign key (ID_ANNULATION)
      references ANNULATION (ID_ANNULATION) on delete restrict on update restrict;

alter table CATEGORIE add constraint FK_GROUPER foreign key (ID_GROUPE)
      references GROUPE (ID_GROUPE) on delete restrict on update restrict;

alter table EVALUATION add constraint FK_NOTER foreign key (ID_REPONSE)
      references REPANNONCE (ID_REPONSE) on delete restrict on update restrict;

alter table FOODFRIEND add constraint FK_FAIRE foreign key (ID_UTILISATEUR)
      references UTILISATEUR (ID_UTILISATEUR) on delete restrict on update restrict;

alter table FOODFRIEND add constraint FK_RECEVOIR foreign key (UTI_ID_UTILISATEUR)
      references UTILISATEUR (ID_UTILISATEUR) on delete restrict on update restrict;

alter table PRODUIT add constraint FK_CONSERVER foreign key (ID_MODE_CONSERVATION)
      references MODE_CONSERVATION (ID_MODE_CONSERVATION) on delete restrict on update restrict;

alter table PRODUIT add constraint FK_IDENTIFIER foreign key (ID_CATEGORIE)
      references CATEGORIE (ID_CATEGORIE) on delete restrict on update restrict;

alter table PRODUIT add constraint FK_MESURER foreign key (ID_MESURE)
      references MESURE (ID_MESURE) on delete restrict on update restrict;

alter table REPANNONCE add constraint FK_AVOIR foreign key (ID_ANNONCE)
      references ANNONCE (ID_ANNONCE) on delete restrict on update restrict;

alter table REPANNONCE add constraint FK_PROPOSER foreign key (ID_UTILISATEUR)
      references UTILISATEUR (ID_UTILISATEUR) on delete restrict on update restrict;

alter table STOCK add constraint FK_CONTIENT foreign key (ID_MOTIF_JETER)
      references MOJETER (ID_MOTIF_JETER) on delete restrict on update restrict;

alter table STOCK add constraint FK_PORTER foreign key (ID_PRODUIT)
      references PRODUIT (ID_PRODUIT) on delete restrict on update restrict;

alter table STOCK add constraint FK_STOCKER foreign key (ID_UTILISATEUR)
      references UTILISATEUR (ID_UTILISATEUR) on delete restrict on update restrict;

alter table USERADRESSE add constraint FK_POSSEDER foreign key (ID_UTILISATEUR)
      references UTILISATEUR (ID_UTILISATEUR) on delete restrict on update restrict;

alter table USERADRESSE add constraint FK_POSSEDER2 foreign key (ID_ADRESSE)
      references ADRESSE (ID_ADRESSE) on delete restrict on update restrict;	  
	  
alter table UTILISATEUR add constraint FK_EXPLIQUER foreign key (ID_MOTIF_DESINSCRIPTION)
      references MODESINSCRIPTION (ID_MOTIF_DESINSCRIPTION) on delete restrict on update restrict;

	  
	  
	  /* VILLE */
insert into VILLE (code_postal, ville) values ('92120', 'MONTROUGE');
insert into VILLE (code_postal, ville) values ('75014', 'PARIS 14');
insert into VILLE (code_postal, ville) values ('75012', 'PARIS 12');
insert into VILLE (code_postal, ville) values ('94400', 'VITRY SUR SEINE');
insert into VILLE (code_postal, ville) values ('92100', 'BOULOGNE BILLANCOURT');
insert into VILLE (code_postal, ville) values ('75013', 'PARIS 13');
insert into VILLE (code_postal, ville) values ('75018', 'PARIS 18');
insert into VILLE (code_postal, ville) values ('75019', 'PARIS 19');
insert into VILLE (code_postal, ville) values ('75020', 'PARIS 20');

/* ADRESSE*/
insert into adresse (id_ville, numero_voie, nom_voie, complement_adresse, type_voie, date_debut_validite, date_fin_validite, x, y) values (1, 9, 'du Général de Gaulle', NULL, 'boulevard', '2018/02/05', NULL, 48.8164171, 2.327170600000045);
insert into adresse (id_ville, numero_voie, nom_voie, complement_adresse, type_voie, date_debut_validite, date_fin_validite, x, y) values (1, 3, 'du Général de Gaulle', NULL, 'rue', '2018/02/05', NULL, 48.8172899, 2.326749800000016);
insert into adresse (id_ville, numero_voie, nom_voie, complement_adresse, type_voie, date_debut_validite, date_fin_validite, x, y) values (1, 102, 'Aristide Briand', NULL, 'avenue', '2018/02/05', NULL, 48.81454410000001, 2.325556399999982);
insert into adresse (id_ville, numero_voie, nom_voie, complement_adresse, type_voie, date_debut_validite, date_fin_validite, x, y) values (1, 15, 'Barbès', NULL, 'rue', '2018/02/05', NULL, 48.817953, 2.327532);
insert into adresse (id_ville, numero_voie, nom_voie, complement_adresse, type_voie, date_debut_validite, date_fin_validite, x, y) values (1, 15, 'du Général de Gaulle', NULL, 'boulevard', '2018/02/05', NULL, 48.8161572, 2.327477300000055);
insert into adresse (id_ville, numero_voie, nom_voie, complement_adresse, type_voie, date_debut_validite, date_fin_validite, x, y) values (1, 11, 'de la Vanne', NULL, 'rue', '2018/02/05', NULL, 48.8164513, 2.328940800000055);
insert into adresse (id_ville, numero_voie, nom_voie, complement_adresse, type_voie, date_debut_validite, date_fin_validite, x, y) values (1, 18, 'Barbès', NULL, 'rue', '2018/02/05', NULL, 48.8168517, 2.3277921000000106);
insert into adresse (id_ville, numero_voie, nom_voie, complement_adresse, type_voie, date_debut_validite, date_fin_validite, x, y) values (1, 14, 'François Ory', NULL, 'rue', '2018/02/05', NULL, 48.8179044, 2.3289230000000316);
insert into adresse (id_ville, numero_voie, nom_voie, complement_adresse, type_voie, date_debut_validite, date_fin_validite, x, y) values (1, 5, 'Amaury Duval', NULL, 'rue', '2018/02/05', NULL, 48.81723969999999, 2.3239906999999675);
insert into adresse (id_ville, numero_voie, nom_voie, complement_adresse, type_voie, date_debut_validite, date_fin_validite, x, y) values (1, 15, 'Louis Lejeune', NULL, 'rue', '2018/02/05', NULL, 48.8182342, 2.327017699999942);
insert into adresse (id_ville, numero_voie, nom_voie, complement_adresse, type_voie, date_debut_validite, date_fin_validite, x, y) values (1, 28, 'd\'Etienne d\'Orves', NULL, 'rue', '2018/02/05', NULL, 48.8151806, 2.322797199999968);
insert into adresse (id_ville, numero_voie, nom_voie, complement_adresse, type_voie, date_debut_validite, date_fin_validite, x, y) values (2, 18, 'Friant', NULL, 'rue', '2018/02/05', NULL, 48.8255391, 2.3240619000000606);


/* MODE INSCRIPTION*/
insert into MODESINSCRIPTION (MOTIF_DESISCRIPTION) values ('N\'utilise pas assez l\'application');
insert into MODESINSCRIPTION (MOTIF_DESISCRIPTION) values ('Plus besoin de l\'application');
insert into MODESINSCRIPTION (MOTIF_DESISCRIPTION) values ('Pas assez de temps pour utiliser l\'application');
insert into MODESINSCRIPTION (MOTIF_DESISCRIPTION) values ('Plus envie d\'utiliser l\'application');
insert into MODESINSCRIPTION (MOTIF_DESISCRIPTION) values ('Ne souhaite pas s\'exprimer');
insert into MODESINSCRIPTION (MOTIF_DESISCRIPTION) values ('Autre');

/* UTILISATEUR*/
insert into Utilisateur (id_motif_desinscription, prenom, nom, date_de_naissance, pseudo, mdp, email, sexe, date_inscription, date_desinscription) values (NULL, 'Jon', 'Dupont', '1988/01/01', 'Jon', 'amin', 'jon@mail.com', 1, '2018/02/05', NULL);
insert into Utilisateur (id_motif_desinscription, prenom, nom, date_de_naissance, pseudo, mdp, email, sexe, date_inscription, date_desinscription) values (NULL, 'Danielle', 'Le blanc', '1988/01/01', 'Dany', 'amin', 'dany@mail.com', 2, '2018/02/05', NULL);
insert into Utilisateur (id_motif_desinscription, prenom, nom, date_de_naissance, pseudo, mdp, email, sexe, date_inscription, date_desinscription) values (NULL, 'Caroline', 'Granier', '1988/01/01', 'Caro', 'amin', 'caro@mail.com', 2, '2018/02/05', NULL);
insert into Utilisateur (id_motif_desinscription, prenom, nom, date_de_naissance, pseudo, mdp, email, sexe, date_inscription, date_desinscription) values (NULL, 'Olivier', 'Tom', '1988/01/01', 'Tomy', 'amin', 'footballer@mail.com', 1, '2018/02/05', NULL);
insert into Utilisateur (id_motif_desinscription, prenom, nom, date_de_naissance, pseudo, mdp, email, sexe, date_inscription, date_desinscription) values (NULL, 'Aria', 'Françoise', '1988/01/01', 'Fanfan', 'amin', 'little_sis@mail.com', 2, '2018/02/05', NULL);
insert into Utilisateur (id_motif_desinscription, prenom, nom, date_de_naissance, pseudo, mdp, email, sexe, date_inscription, date_desinscription) values (NULL, 'Samir', 'Momo', '1988/01/01', 'Sami', 'amin', 'big_bro@mail.com', 1, '2018/02/05', NULL);
insert into Utilisateur (id_motif_desinscription, prenom, nom, date_de_naissance, pseudo, mdp, email, sexe, date_inscription, date_desinscription) values (NULL, 'Pierre', 'Jacarrini', '1988/01/01', 'Pierrot', 'amin', 'pierrot@mail.com', 1, '2018/02/05', NULL);
insert into Utilisateur (id_motif_desinscription, prenom, nom, date_de_naissance, pseudo, mdp, email, sexe, date_inscription, date_desinscription) values (NULL, 'Eric', 'Houltel', '1988/01/01', 'Le_Grand', 'amin', 'eric@mail.com', 1, '2018/02/05', NULL);
insert into Utilisateur (id_motif_desinscription, prenom, nom, date_de_naissance, pseudo, mdp, email, sexe, date_inscription, date_desinscription) values (NULL, 'Cédric', 'Samwell', '1988/01/01', 'Ced', 'amin', 'sam@mail.com', 1, '2018/02/05', NULL);
insert into Utilisateur (id_motif_desinscription, prenom, nom, date_de_naissance, pseudo, mdp, email, sexe, date_inscription, date_desinscription) values (NULL, 'Jean-Paul', 'Robert', '1988/01/01', 'Paul', 'amin', 'nedj@mail.com', 1, '2018/02/05', NULL);

/* USERADRESSE */
insert into USERADRESSE (ID_UTILISATEUR, ID_ADRESSE, PRINCIPALE) values (1, 1, 1);
insert into USERADRESSE (ID_UTILISATEUR, ID_ADRESSE, PRINCIPALE) values (2, 2, 1);
insert into USERADRESSE (ID_UTILISATEUR, ID_ADRESSE, PRINCIPALE) values (3, 3, 1);
insert into USERADRESSE (ID_UTILISATEUR, ID_ADRESSE, PRINCIPALE) values (4, 4, 1);
insert into USERADRESSE (ID_UTILISATEUR, ID_ADRESSE, PRINCIPALE) values (5, 5, 1);
insert into USERADRESSE (ID_UTILISATEUR, ID_ADRESSE, PRINCIPALE) values (6, 6, 1);
insert into USERADRESSE (ID_UTILISATEUR, ID_ADRESSE, PRINCIPALE) values (7, 7, 1);
insert into USERADRESSE (ID_UTILISATEUR, ID_ADRESSE, PRINCIPALE) values (8, 8, 1);
insert into USERADRESSE (ID_UTILISATEUR, ID_ADRESSE, PRINCIPALE) values (9, 9, 1);
insert into USERADRESSE (ID_UTILISATEUR, ID_ADRESSE, PRINCIPALE) values (10, 10, 1);
insert into USERADRESSE (ID_UTILISATEUR, ID_ADRESSE, PRINCIPALE) values (1, 10, 0);

/* FOODFRIEND*/
insert into foodfriend (ID_UTILISATEUR, UTI_ID_UTILISATEUR, DATE_DEMANDE, DATE_REPONSE, DATE_FIN_RELATION) values (1, 2, '2018/03/10', '2018/03/15', NULL);
insert into foodfriend (ID_UTILISATEUR, UTI_ID_UTILISATEUR, DATE_DEMANDE, DATE_REPONSE, DATE_FIN_RELATION) values (1, 4, '2018/03/10', '2018/03/11', NULL);
insert into foodfriend (ID_UTILISATEUR, UTI_ID_UTILISATEUR, DATE_DEMANDE, DATE_REPONSE, DATE_FIN_RELATION) values (6, 1, '2018/03/10', NULL, NULL);
insert into foodfriend (ID_UTILISATEUR, UTI_ID_UTILISATEUR, DATE_DEMANDE, DATE_REPONSE, DATE_FIN_RELATION) values (9, 1, '2018/03/10', NULL, NULL);
insert into foodfriend (ID_UTILISATEUR, UTI_ID_UTILISATEUR, DATE_DEMANDE, DATE_REPONSE, DATE_FIN_RELATION) values (2, 4, '2018/03/10', '2018/04/01', NULL);
insert into foodfriend (ID_UTILISATEUR, UTI_ID_UTILISATEUR, DATE_DEMANDE, DATE_REPONSE, DATE_FIN_RELATION) values (3, 2, '2018/03/10', NULL, NULL);
insert into foodfriend (ID_UTILISATEUR, UTI_ID_UTILISATEUR, DATE_DEMANDE, DATE_REPONSE, DATE_FIN_RELATION) values (3, 1, '2018/03/10', '2018/03/15', NULL);
insert into foodfriend (ID_UTILISATEUR, UTI_ID_UTILISATEUR, DATE_DEMANDE, DATE_REPONSE, DATE_FIN_RELATION) values (1, 5, '2018/03/10', '2018/04/01', NULL);
insert into foodfriend (ID_UTILISATEUR, UTI_ID_UTILISATEUR, DATE_DEMANDE, DATE_REPONSE, DATE_FIN_RELATION) values (1, 7, '2018/03/10', '2018/04/01', NULL);
insert into foodfriend (ID_UTILISATEUR, UTI_ID_UTILISATEUR, DATE_DEMANDE, DATE_REPONSE, DATE_FIN_RELATION) values (5, 6, '2018/03/10', '2018/03/11', NULL);
insert into foodfriend (ID_UTILISATEUR, UTI_ID_UTILISATEUR, DATE_DEMANDE, DATE_REPONSE, DATE_FIN_RELATION) values (6, 10, '2018/03/10', '2018/03/11', NULL);
insert into foodfriend (ID_UTILISATEUR, UTI_ID_UTILISATEUR, DATE_DEMANDE, DATE_REPONSE, DATE_FIN_RELATION) values (5, 9, '2018/03/10', '2018/03/11', NULL);



/*------------------ Mojeter ------------------*/
INSERT INTO Mojeter (nom_motif_jeter) VALUES ('Produit abimé non consommable');
INSERT INTO Mojeter (nom_motif_jeter) VALUES ('DLC dépassée, pas le temps de poster une annonce');
INSERT INTO Mojeter (nom_motif_jeter) VALUES ('Aucune reponse a l''annonce publiee et DLC depassee');

/*------------------ Groupe ------------------*/
INSERT INTO Groupe (nom_groupe) VALUES ('Légumes');
INSERT INTO Groupe (nom_groupe) VALUES ('Fruits');
INSERT INTO Groupe (nom_groupe) VALUES ('Produits sucrés');
INSERT INTO Groupe (nom_groupe) VALUES ('Viandes Poissons Œufs');
INSERT INTO Groupe (nom_groupe) VALUES ('Produits laitiers');
INSERT INTO Groupe (nom_groupe) VALUES ('Boissons');
INSERT INTO Groupe (nom_groupe) VALUES ('Féculents');
INSERT INTO Groupe (nom_groupe) VALUES ('Matières grasses');
INSERT INTO Groupe (nom_groupe) VALUES ('Autres');

/*------------------ Categorie ------------------*/
INSERT INTO Categorie (id_groupe, nom_categorie, jours_extension_categorie) VALUES (1, 'légumes', 0);
INSERT INTO Categorie (id_groupe, nom_categorie, jours_extension_categorie) VALUES (2, 'fruits rouges frais', 7);
INSERT INTO Categorie (id_groupe, nom_categorie, jours_extension_categorie) VALUES (2, 'fruits jaunes frais', 14);
INSERT INTO Categorie (id_groupe, nom_categorie, jours_extension_categorie) VALUES (2, 'fruits à peau épaisse', 20);
INSERT INTO Categorie (id_groupe, nom_categorie, jours_extension_categorie) VALUES (2, 'fruits secs et oléagineux', 150);
INSERT INTO Categorie (id_groupe, nom_categorie, jours_extension_categorie) VALUES (2, 'fruits séchés', 180);
INSERT INTO Categorie (id_groupe, nom_categorie, jours_extension_categorie) VALUES (3, 'sucre', 1825);
INSERT INTO Categorie (id_groupe, nom_categorie, jours_extension_categorie) VALUES (3, 'chocolat', 180);
INSERT INTO Categorie (id_groupe, nom_categorie, jours_extension_categorie) VALUES (3, 'miel', 200);
INSERT INTO Categorie (id_groupe, nom_categorie, jours_extension_categorie) VALUES (4, 'viandes de grande surface', 0);
INSERT INTO Categorie (id_groupe, nom_categorie, jours_extension_categorie) VALUES (4, 'viandes du marché', 5);
INSERT INTO Categorie (id_groupe, nom_categorie, jours_extension_categorie) VALUES (4, 'poissons de grande surface', 0);
INSERT INTO Categorie (id_groupe, nom_categorie, jours_extension_categorie) VALUES (4, 'poissons du marché', 5);
INSERT INTO Categorie (id_groupe, nom_categorie, jours_extension_categorie) VALUES (4, 'œufs', 27);
INSERT INTO Categorie (id_groupe, nom_categorie, jours_extension_categorie) VALUES (5, 'yaourt', 90);
INSERT INTO Categorie (id_groupe, nom_categorie, jours_extension_categorie) VALUES (5, 'fromage', 14);
INSERT INTO Categorie (id_groupe, nom_categorie, jours_extension_categorie) VALUES (5, 'lait', 60);
INSERT INTO Categorie (id_groupe, nom_categorie, jours_extension_categorie) VALUES (6, 'boisson', 60);
INSERT INTO Categorie (id_groupe, nom_categorie, jours_extension_categorie) VALUES (7, 'produits de la panification', 0);
INSERT INTO Categorie (id_groupe, nom_categorie, jours_extension_categorie) VALUES (7, 'céréales', 0);
INSERT INTO Categorie (id_groupe, nom_categorie, jours_extension_categorie) VALUES (7, 'légumineuses', 0);
INSERT INTO Categorie (id_groupe, nom_categorie, jours_extension_categorie) VALUES (7, 'pomme de terre', 0);
INSERT INTO Categorie (id_groupe, nom_categorie, jours_extension_categorie) VALUES (8, 'huile', 0);
INSERT INTO Categorie (id_groupe, nom_categorie, jours_extension_categorie) VALUES (8, 'beurre', 0);
INSERT INTO Categorie (id_groupe, nom_categorie, jours_extension_categorie) VALUES (8, 'crème fraîche', 0);
INSERT INTO Categorie (id_groupe, nom_categorie, jours_extension_categorie) VALUES (9, 'plat préparé', 0);

/*------------------ Mode_Conservation ------------------*/
INSERT INTO Mode_Conservation (nom_mode_conservation, jours_extension_conservation) VALUES ('frais', 0);
INSERT INTO Mode_Conservation (nom_mode_conservation, jours_extension_conservation) VALUES ('épicerie', 1095);
INSERT INTO Mode_Conservation (nom_mode_conservation, jours_extension_conservation) VALUES ('conserve', 365);
INSERT INTO Mode_Conservation (nom_mode_conservation, jours_extension_conservation) VALUES ('surgelé', 730);
INSERT INTO Mode_Conservation (nom_mode_conservation, jours_extension_conservation) VALUES ('épicerie frais', 1095);
INSERT INTO Mode_Conservation (nom_mode_conservation, jours_extension_conservation) VALUES ('épicerie fine', 0);

/*------------------ Mesure ------------------*/
INSERT INTO Mesure (nom_mesure, denombrable) VALUES ('%', 0);
INSERT INTO Mesure (nom_mesure, denombrable) VALUES ('pot(s)', 1);
INSERT INTO Mesure (nom_mesure, denombrable) VALUES ('brique(s)', 1);
INSERT INTO Mesure (nom_mesure, denombrable) VALUES ('bouteille(s)', 1);
INSERT INTO Mesure (nom_mesure, denombrable) VALUES ('piece(s)', 1);

/*------------------ Produit ------------------*/
INSERT INTO Produit (id_mode_conservation, id_categorie, id_mesure, nom_produit, nombre_unite, date_proposition, date_activation, date_desactivation) VALUES (4, 1, 1, 'Brocolis en fleurettes surgelés 1kg Carrefour', '100', '2018-07-01', '2018-07-02', NULL);
INSERT INTO Produit (id_mode_conservation, id_categorie, id_mesure, nom_produit, nombre_unite, date_proposition, date_activation, date_desactivation) VALUES (1, 3, 1, 'Tomate à farcir 1kg', '100', '2018-07-01', '2018-07-02', NULL);
INSERT INTO Produit (id_mode_conservation, id_categorie, id_mesure, nom_produit, nombre_unite, date_proposition, date_activation, date_desactivation) VALUES (1, 3, 1, 'Raisin en grappe 1kg', '100', '2018-07-01', '2018-07-02', NULL);
INSERT INTO Produit (id_mode_conservation, id_categorie, id_mesure, nom_produit, nombre_unite, date_proposition, date_activation, date_desactivation) VALUES (1, 4, 1, 'Pastèque', '100', '2018-07-01', '2018-07-02', NULL);
INSERT INTO Produit (id_mode_conservation, id_categorie, id_mesure, nom_produit, nombre_unite, date_proposition, date_activation, date_desactivation) VALUES (2, 8, 1, 'Chocolat au lait praliné noisettes 100g', '100', '2018-07-01', '2018-07-02', NULL);
INSERT INTO Produit (id_mode_conservation, id_categorie, id_mesure, nom_produit, nombre_unite, date_proposition, date_activation, date_desactivation) VALUES (1, 11, 1, 'Gigot d''agneau', '100', '2018-07-01', '2018-07-02', NULL);
INSERT INTO Produit (id_mode_conservation, id_categorie, id_mesure, nom_produit, nombre_unite, date_proposition, date_activation, date_desactivation) VALUES (1, 10, 1, 'Blanc de dinde 160g Fleury Michon', '100', '2018-07-01', '2018-07-02', NULL);
INSERT INTO Produit (id_mode_conservation, id_categorie, id_mesure, nom_produit, nombre_unite, date_proposition, date_activation, date_desactivation) VALUES (4, 12, 1, 'Cabillaud surgelé Findus', '100', '2018-07-01', '2018-07-02', NULL);
INSERT INTO Produit (id_mode_conservation, id_categorie, id_mesure, nom_produit, nombre_unite, date_proposition, date_activation, date_desactivation) VALUES (1, 14, 5, 'Oeufs plein air boite 24 Loué', '24', '2018-07-01', '2018-07-02', NULL);
INSERT INTO Produit (id_mode_conservation, id_categorie, id_mesure, nom_produit, nombre_unite, date_proposition, date_activation, date_desactivation) VALUES (1, 15, 2, 'Yaourt brassé nature Pack de 12 Danone', '12', '2018-07-01', '2018-07-02', NULL);
INSERT INTO Produit (id_mode_conservation, id_categorie, id_mesure, nom_produit, nombre_unite, date_proposition, date_activation, date_desactivation) VALUES (1, 16, 1, 'Camembert 250g Coeur de Lion', '100', '2018-07-01', '2018-07-02', NULL);
INSERT INTO Produit (id_mode_conservation, id_categorie, id_mesure, nom_produit, nombre_unite, date_proposition, date_activation, date_desactivation) VALUES (5, 18, 1, 'Jus d''orange -30% de sucres  1L  Joker', '100', '2018-07-01', '2018-07-02', NULL);
INSERT INTO Produit (id_mode_conservation, id_categorie, id_mesure, nom_produit, nombre_unite, date_proposition, date_activation, date_desactivation) VALUES (2, 19, 1, 'Pain de mie blanc 280g Harry''s', '100', '2018-07-01', '2018-07-02', NULL);
INSERT INTO Produit (id_mode_conservation, id_categorie, id_mesure, nom_produit, nombre_unite, date_proposition, date_activation, date_desactivation) VALUES (2, 19, 1, 'Pain de mie complet 280g Harry''s', '100', '2018-07-01', '2018-07-02', NULL);
INSERT INTO Produit (id_mode_conservation, id_categorie, id_mesure, nom_produit, nombre_unite, date_proposition, date_activation, date_desactivation) VALUES (2, 20, 1, 'Semoule moyenne 500g Panzani', '100', '2018-07-01', '2018-07-02', NULL);
INSERT INTO Produit (id_mode_conservation, id_categorie, id_mesure, nom_produit, nombre_unite, date_proposition, date_activation, date_desactivation) VALUES (2, 23, 1, 'Huile d''olive extra vierge 1L Puget', '100', '2018-07-01', '2018-07-02', NULL);
INSERT INTO Produit (id_mode_conservation, id_categorie, id_mesure, nom_produit, nombre_unite, date_proposition, date_activation, date_desactivation) VALUES (3, 26, 1, 'Sauce Tomacouli nature 750g Panzani', '100', '2018-07-01', '2018-07-02', NULL);
INSERT INTO Produit (id_mode_conservation, id_categorie, id_mesure, nom_produit, nombre_unite, date_proposition, date_activation, date_desactivation) VALUES (3, 21, 1, 'Lentilles cuisinées aux oignons 200g Cassegrain', '100', '2018-07-01', '2018-07-02', NULL);
INSERT INTO Produit (id_mode_conservation, id_categorie, id_mesure, nom_produit, nombre_unite, date_proposition, date_activation, date_desactivation) VALUES (5, 17, 1, 'Lait demi-écrémé 1L Lactel', '100', '2018-07-01', '2018-07-02', NULL);
INSERT INTO Produit (id_mode_conservation, id_categorie, id_mesure, nom_produit, nombre_unite, date_proposition, date_activation, date_desactivation) VALUES (2, 17, 4, 'Lait demi-écrémé Pack 6x1L Lactel', '6', '2018-07-01', '2018-07-02', NULL);

/*-----------------------Stock---------------------------*/
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (1, 7, NULL, 1, 50, '2018-07-02', '2018-09-01', '2018-09-30', NULL, NULL,1);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (2, 7, NULL, 1, 75, '2018-07-02', '2018-09-01', '2018-09-30', NULL, NULL,1);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (3, 1, 1, 1, 100, '2018-07-02', '2018-09-01', '2018-09-30', NULL, '2018-07-31', 0);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (4, 1, NULL, 1, 50, '2018-07-02', '2018-09-01', '2018-09-30', NULL, NULL,1);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (5, 8, NULL, 1, 100, '2018-07-02', '2018-09-01', '2018-09-30', NULL, NULL,0);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (6, 8, NULL, 1, 50, '2018-07-02', '2018-09-01', '2018-09-30', NULL, NULL,1);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (7, 9, NULL, 1, 100, '2018-07-02', '2018-09-01', '2018-09-30', NULL, NULL,0);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (8, 2, NULL, 1, 100, '2018-07-02', '2018-09-01', '2018-09-30', NULL, NULL,0);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (9, 9, NULL, 1, 20, '2018-07-02', '2018-09-01', '2018-09-30', NULL, NULL,1);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (10, 10, NULL, 1, 8, '2018-07-02', '2018-09-01', '2018-09-30', NULL, NULL,1);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (11, 10, NULL, 1, 100, '2018-07-02', '2018-09-01', '2018-09-30', NULL, NULL,0);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (12, 2, NULL, 1, 100, '2018-07-02', '2018-09-01', '2018-09-30', NULL, NULL,0);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (13, 3, 2, 1, 50, '2018-07-02', '2018-09-01', '2018-09-30', NULL, '2018-07-31', 1);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (14, 3, NULL, 1, 100, '2018-07-02', '2018-09-01', '2018-09-30', NULL, NULL,0);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (15, 4, NULL, 1, 50, '2018-07-02', '2018-09-01', '2018-09-30', NULL, NULL,1);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (16, 4, NULL, 1, 100, '2018-07-02', '2018-09-01', '2018-09-30', NULL, NULL,0);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (17, 5, NULL, 1, 50, '2018-07-02', '2018-09-01', '2018-09-30', NULL, NULL,1);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (18, 5, NULL, 1, 100, '2018-07-02', '2018-09-01', '2018-09-30', NULL, NULL,0);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (19, 6, 1, 1, 50, '2018-07-02', '2018-09-01', '2018-09-30', NULL, '2018-07-31', 1);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (20, 6, NULL, 1, 5, '2018-07-02', '2018-09-01', '2018-09-30', NULL, NULL,1);

/*Tomate => Manger 21*/
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (2, 1, NULL, 1, 100, '2018-08-21', '2018-08-29', '2018-09-30', NULL, NULL,0);
/*Oeufs => Jeter*/
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (9, 1, NULL, 1, 4, '2018-07-02', '2018-08-10', '2018-08-15', NULL, NULL,1);
/*Yaourt*/
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref  , date_Manger, date_Jeter, Entame) VALUES (10, 1, NULL, 1, 11, '2018-07-02', '2018-09-01', '2018-09-30', NULL, NULL,1);
/*camembert*/
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (11, 1, NULL, 1, 100, '2018-07-02', '2018-09-01', '2018-09-30', NULL, NULL,0);
/*blanc de dinde => Donner*/
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (7, 1, NULL, 1, 70, '2018-08-15', '2018-08-28', '2018-08-28', NULL, NULL,1);
/*annonce terminee idstock = 26*/
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (1, 2, NULL, 1, 5, '2018-07-02', '2018-09-01', '2018-09-30', NULL, NULL,0);
/*stock aude 27*/
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (11, 9, NULL, 1, 100, '2018-07-02', '2018-06-01', '2018-09-30', NULL, NULL,0);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (12, 9, NULL, 1, 100, '2018-07-02', '2018-06-01', '2018-09-30', NULL, NULL,0);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (13, 9, NULL, 1, 50, '2018-07-02', '2018-06-01', '2018-09-30', NULL, NULL, 1);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (14, 9, NULL, 1, 100, '2018-07-02', '2018-06-01', '2018-09-30', NULL, NULL,0);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (15, 9, NULL, 1, 50, '2018-07-02', '2018-06-01', '2018-09-30', NULL, NULL,1);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (16, 9, NULL, 1, 100, '2018-07-02', '2018-06-01', '2018-09-30', NULL, NULL,0);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (17, 9, NULL, 1, 50, '2018-07-02', '2018-06-01', '2018-09-30', NULL, NULL,1);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (18, 9, NULL, 1, 100, '2018-07-02', '2018-06-01', '2018-09-30', NULL, NULL,0);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (19, 9, NULL, 1, 50, '2018-07-02', '2018-06-01', '2018-09-30', NULL, NULL, 1);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (20, 9, NULL, 1, 5, '2018-07-02', '2018-06-01', '2018-09-30', NULL, NULL,1);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (20, 9, NULL, 1, 5, '2018-07-02', '2018-06-01', '2018-09-30', NULL, NULL,1);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (18, 7, NULL, 1, 100, '2018-07-02', '2018-09-15', '2018-09-15', NULL, NULL,1);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (5, 5, NULL, 1, 100, '2018-01-20', '2018-09-30', '2018-09-30', NULL, NULL,1);
/*stock dany pour annonce pain de mie 40*/
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (14, 2, NULL, 1, 100, '2018-08-10', '2018-08-30', '2018-09-30', NULL, NULL,0);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (16, 2, NULL, 1, 100, '2018-08-15', '2018-10-30', '2018-12-30', NULL, NULL,0);
/*stock pour autre annonce pain de mie 42*/
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (13, 10, NULL, 1, 100, '2018-08-15', '2018-08-31', '2018-10-30', NULL, NULL,0);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (13, 8, NULL, 1, 100, '2018-08-01', '2018-08-26', '2018-09-26', NULL, NULL,0);
/*stock jon pour annonce en cours 44*/
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (12, 1, NULL, 1, 100, '2018-08-01', '2018-08-31', '2018-10-30', NULL, NULL,0);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (5, 1, NULL, 1, 100, '2018-07-01', '2018-08-31', '2018-10-30', NULL, NULL,0);
/*stock dany pour annonce terminé et annulé 46*/
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (1, 2, NULL, 1, 50, '2018-08-01', '2018-08-31', '2018-10-30', NULL, NULL,0);
INSERT INTO Stock (id_Produit, id_Utilisateur, id_Motif_Jeter, quantite, fraction_restante, date_Achat, DLC, date_Conso_Pref, date_Manger, date_Jeter, Entame) VALUES (2, 2, NULL, 1, 75, '2018-08-01', '2018-08-31', '2018-10-30', NULL, NULL,0);



/*------------------ANNULATION ---------------------*/
insert into Annulation (nom_annulation) values ( 'Aucune reponse a l annonce publiee et DLC depassee');
insert into Annulation (nom_annulation) values ( 'Aliment plus disponible( abimee)');
insert into Annulation (nom_annulation) values ( 'Aliment consommé');

/*------------------ANNONCE ---------------------*/
insert into annonce (id_stock, id_adresse, titre, date_publication, date_rdv_1, date_rdv_2, date_rdv_3, date_fin_annonce)  values (1, 7, 'Je pars en vacances', '2018-08-19', '2018-08-24 19:00', '2018-08-25 12:00', '2018-08-26 12:00', null);
insert into annonce (id_stock, id_adresse, titre, date_publication, date_rdv_1, date_rdv_2, date_rdv_3, date_fin_annonce)  values (2, 7, 'Je propose des tomates de mon jardin', '2018-08-19', '2018-08-24 20:00', '2018-08-25 11:00', '2018-08-26 11:00', null);
insert into annonce (id_stock, id_adresse, titre, date_publication, date_rdv_1, date_rdv_2, date_rdv_3, date_fin_annonce)  values (5, 8, 'Pour les amoureux du chocolat...', '2018-08-19', '2018-08-25 11:00', '2018-08-25 12:00', '2018-08-25 13:00', null);
insert into annonce (id_stock, id_adresse, titre, date_publication, date_rdv_1, date_rdv_2, date_rdv_3, date_fin_annonce)  values (7, 9, 'Miam miam miam', '2018-08-19', '2018-08-26 11:00', '2018-08-26 12:00', '2018-08-26 13:00', null);
insert into annonce (id_stock, id_adresse, titre, date_publication, date_rdv_1, date_rdv_2, date_rdv_3, date_fin_annonce)  values (9, 9, 'Vive food2eat ! ', '2018-08-20', '2018-08-24 19:00', '2018-08-25 12:00', '2018-08-26 12:00', null);
insert into annonce (id_stock, id_adresse, titre, date_publication, date_rdv_1, date_rdv_2, date_rdv_3, date_fin_annonce)  values (10, 10, 'Je déménage', '2018-08-20', '2018-08-24 20:00', '2018-08-25 11:00', '2018-08-26 11:00', null);
insert into annonce (id_stock, id_adresse, titre, date_publication, date_rdv_1, date_rdv_2, date_rdv_3, date_fin_annonce)  values (11, 10, 'Ne m\'obligez pas à nourrir ma poubelle...', '2018-08-20', '2018-08-25 11:00', '2018-08-25 12:00', '2018-08-25 13:00', null);
insert into annonce (id_stock, id_adresse, titre, date_publication, date_rdv_1, date_rdv_2, date_rdv_3, date_fin_annonce)  values (13, 3, 'Qui aime le pain de mie ? ', '2018-08-20', '2018-08-26 11:00', '2018-08-26 12:00', '2018-08-26 13:00', null);
insert into annonce (id_stock, id_adresse, titre, date_publication, date_rdv_1, date_rdv_2, date_rdv_3, date_annulation)  values (14, 3, 'Qui aime le pain de mie complet ?', '2018-08-20', '2018-08-24 19:00', '2018-08-25 12:00', '2018-08-26 12:00', '2018-08-20');
insert into annonce (id_stock, id_adresse, titre, date_publication, date_rdv_1, date_rdv_2, date_rdv_3, date_fin_annonce)  values (15, 4, 'Produit indispensable !', '2018-08-21', '2018-08-24 20:00', '2018-08-25 11:00', '2018-08-26 11:00', null);
insert into annonce (id_stock, id_adresse, titre, date_publication, date_rdv_1, date_rdv_2, date_rdv_3, date_fin_annonce)  values (16, 4, 'Dépêchez-vous...', '2018-08-21', '2018-08-25 11:00', '2018-08-25 12:00', '2018-08-25 13:00', null);
insert into annonce (id_stock, id_adresse, titre, date_publication, date_rdv_1, date_rdv_2, date_rdv_3, date_fin_annonce)  values (17, 5, 'Parfait pour la cuisine', '2018-08-22', '2018-08-26 11:00', '2018-08-26 12:00', '2018-08-26 13:00', null);
insert into annonce (id_stock, id_adresse, titre, date_publication, date_rdv_1, date_rdv_2, date_rdv_3, date_fin_annonce)  values (18, 5, 'Je pars en vacances', '2018-08-22', '2018-08-24 19:00', '2018-08-25 12:00', '2018-08-26 12:00', null);
insert into annonce (id_stock, id_adresse, titre, date_publication, date_rdv_1, date_rdv_2, date_rdv_3, date_fin_annonce)  values (19, 6, 'Pour faire le plein de calcium', '2018-08-22', '2018-08-24 20:00', '2018-08-25 11:00', '2018-08-26 11:00', null);
insert into annonce (id_stock, id_adresse, titre, date_publication, date_rdv_1, date_rdv_2, date_rdv_3, date_fin_annonce)  values (20, 6, 'Pour faire le plein de calcium', '2018-08-22', '2018-08-25 11:00', '2018-08-25 12:00', '2018-08-25 13:00', null);
/* annonce terminé 16*/
insert into annonce (id_stock, id_adresse, titre, date_publication, date_rdv_1, date_rdv_2, date_rdv_3, date_fin_annonce)  values (26, 2, 'Besoin de votre aide pour sauver mon aliment !', '2018-08-20', '2018-08-25 11:00', '2018-08-26 12:00', '2018-08-27 13:00', null);
/* annonce aude 17*/
insert into Annonce (Id_stock,Id_adresse,Date_publication,Date_rdv_1,Date_rdv_2,Date_rdv_3,Date_fin_annonce) values ('27','9','2018/08/03','2018/08/05 20:00','1018/08/09 20:00','2018/08/10 20:00','2018/08/10');
insert into Annonce (Id_stock,Id_adresse,Date_publication,Date_rdv_1,Date_rdv_2,Date_rdv_3,Date_fin_annonce) values ('28','9','2018/08/03','2018/08/05 20:00','1018/08/09 20:00','2018/08/10 20:00','2018/08/10');
insert into Annonce (Id_stock,Id_adresse,Date_publication,Date_rdv_1,Date_rdv_2,Date_rdv_3,Date_fin_annonce) values ('29','9','2018/08/03','2018/08/05 20:00','1018/08/09 20:00','2018/08/10 20:00','2018/08/10');
insert into Annonce (Id_stock,Id_adresse,Date_publication,Date_rdv_1,Date_rdv_2,Date_rdv_3,Date_fin_annonce) values ('30','9','2018/08/03','2018/08/05 20:00','1018/08/09 20:00','2018/08/10 20:00','2018/08/10');
insert into Annonce (Id_stock,Id_adresse,Date_publication,Date_rdv_1,Date_rdv_2,Date_rdv_3,Date_fin_annonce) values ('31','9','2018/08/03','2018/08/05 20:00','1018/08/09 20:00','2018/08/10 20:00','2018/08/10');
insert into Annonce (Id_stock,Id_adresse,Date_publication,Date_rdv_1,Date_rdv_2,Date_rdv_3,Date_fin_annonce) values ('32','9','2018/08/03','2018/08/05 20:00','1018/08/09 20:00','2018/08/10 20:00','2018/08/10');
insert into Annonce (Id_stock,Id_adresse,Date_publication,Date_rdv_1,Date_rdv_2,Date_rdv_3,Date_fin_annonce) values ('33','9','2018/08/03','2018/08/05 20:00','1018/08/09 20:00','2018/08/10 20:00','2018/08/10');
insert into Annonce (Id_stock,Id_adresse,Date_publication,Date_rdv_1,Date_rdv_2,Date_rdv_3,Date_fin_annonce) values ('34','9','2018/08/03','2018/08/05 20:00','1018/08/09 20:00','2018/08/10 20:00','2018/08/10');
insert into Annonce (Id_stock,Id_adresse,Date_publication,Date_rdv_1,Date_rdv_2,Date_rdv_3,Date_fin_annonce) values ('35','9','2018/08/03','2018/08/05 20:00','1018/08/09 20:00','2018/08/10 20:00','2018/08/10');
insert into Annonce (Id_stock,Id_adresse,Date_publication,Date_rdv_1,Date_rdv_2,Date_rdv_3,Date_fin_annonce) values ('36','9','2018/08/03','2018/08/05 20:00','1018/08/09 20:00','2018/08/10 20:00','2018/08/10');
insert into Annonce (Id_stock,Id_adresse,Date_publication,Date_rdv_1,Date_rdv_2,Date_rdv_3,Date_fin_annonce) values ('37','9','2018/07/22','2018/07/28 20:00','2018/08/03 20:00','2018/08/30 23:00');
insert into Annonce (Id_stock,Id_adresse,Date_publication,Date_rdv_1,Date_rdv_2,Date_rdv_3, Date_fin_annonce) values ('38','7','2018/08/20','2018/08/30 20:00','2018/09/01 20:00','2018/09/04 23:00', '2018/08/22');
insert into Annonce (Id_stock,Id_adresse,Date_publication,Date_rdv_1,Date_rdv_2,Date_rdv_3, Date_fin_annonce) values ('39','5','2018/08/22','2018/08/25 20:00','2018/08/27 20:00','2018/08/30 20:30', '2018/08/22');
/* annonce dany pain de mie 30*/
insert into Annonce (Id_stock,Id_adresse, titre, Date_publication,Date_rdv_1,Date_rdv_2,Date_rdv_3) values ('40','2', 'C''est gratuit !','2018/08/22','2018/08/23 20:00','2018/08/27 20:00','2018/08/30 20:30');
insert into Annonce (Id_stock,Id_adresse, titre, Date_publication,Date_rdv_1,Date_rdv_2,Date_rdv_3) values ('41','2', 'Je pars en vacances','2018/08/20','2018/08/23 20:00','2018/08/27 20:00','2018/08/30 20:30');
/* annonce autre pain de mie 33*/
insert into Annonce (Id_stock,Id_adresse,titre, Date_publication,Date_rdv_1,Date_rdv_2,Date_rdv_3) values ('42','10','Je déménage !','2018/08/19','2018/08/23 7:00','2018/08/27 7:00','2018/08/30 8:30');
insert into Annonce (Id_stock,Id_adresse,titre, Date_publication,Date_rdv_1,Date_rdv_2,Date_rdv_3) values ('43','8','Mon frigo est plein à craquer...','2018/08/18','2018/08/23 12:00','2018/08/27 15:00','2018/08/30 15:30');
/* annonce en cours pour Jon*/
insert into Annonce (Id_stock,Id_adresse,titre, Date_publication,Date_rdv_1,Date_rdv_2,Date_rdv_3) values ('44','1','Qui veut faire le plein de vitamine !','2018/08/18','2018/08/24 7:00','2018/08/27 7:00','2018/08/30 8:30');
insert into Annonce (Id_stock,Id_adresse,titre, Date_publication,Date_rdv_1,Date_rdv_2,Date_rdv_3) values ('45','1','La gourmandise n''est pas un défaut','2018/08/20','2018/08/25 7:00','2018/08/27 7:00','2018/08/30 8:30');
/* annonce terminé et annonce annulé dany*/
insert into Annonce (Id_stock,Id_adresse,Date_publication,Date_rdv_1,Date_rdv_2,Date_rdv_3,Date_fin_annonce) values ('46','2','2018/08/03','2018/08/05 20:00','2018/08/09 20:00','2018/08/10 20:00','2018/08/10');
insert into Annonce (Id_stock,Id_adresse,Date_publication,Date_rdv_1,Date_rdv_2,Date_rdv_3, Date_fin_annonce, date_annulation) values ('47','2','2018/08/03','2018/08/05 20:00','2018/08/09 20:00','2018/08/10 20:00',null,'2018/08/10');



/*------------------REPONSE ---------------------*/
/*reponse annonce brocoli annonce 16*/
insert into RepAnnonce (Id_utilisateur,Id_annonce,Date_reponse,Date_acceptation_reponse,Date_RDV) values ( '6','16','2018/08/21',null,'2018-08-25 11:00');
insert into RepAnnonce (Id_utilisateur,Id_annonce,Date_reponse,Date_acceptation_reponse,Date_RDV) values ( '5','16','2018/02/21',null,'2018-08-25 11:00');
insert into RepAnnonce (Id_utilisateur,Id_annonce,Date_reponse,Date_acceptation_reponse,Date_RDV) values ( '4','16','2018/07/22',null,'2018-08-26 12:00');
/*reponse annonce brocoli annonce 32*/
insert into RepAnnonce (Id_utilisateur,Id_annonce,Date_reponse,Date_acceptation_reponse,Date_RDV) values ( '3','32','2018/08/22',null,'2018/08/23 20:00');
insert into RepAnnonce (Id_utilisateur,Id_annonce,Date_reponse,Date_acceptation_reponse,Date_RDV) values ( '9','32','2018/08/22',null,'2018/08/27 20:00');
/*reponse annonce pain de mie de Dany annonce 30*/
insert into RepAnnonce (Id_utilisateur,Id_annonce,Date_reponse,Date_acceptation_reponse,Date_RDV) values ( '6','30','2018/08/21',null,'2018/08/23 20:00');
insert into RepAnnonce (Id_utilisateur,Id_annonce,Date_reponse,Date_acceptation_reponse,Date_RDV) values ( '5','30','2018/02/21',null,'2018/08/23 20:00');
insert into RepAnnonce (Id_utilisateur,Id_annonce,Date_reponse,Date_acceptation_reponse,Date_RDV) values ( '4','30','2018/07/22',null,'2018/08/27 20:00');
insert into RepAnnonce (Id_utilisateur,Id_annonce,Date_reponse,Date_acceptation_reponse,Date_RDV) values ( '3','30','2018/08/22',null,'2018/08/27 20:00');
insert into RepAnnonce (Id_utilisateur,Id_annonce,Date_reponse,Date_acceptation_reponse,Date_RDV) values ( '9','30','2018/08/22',null,'2018/08/30 20:30');

/*reponse aude*/
insert into RepAnnonce (Id_utilisateur,Id_annonce,Date_reponse,Date_acceptation_reponse,Date_RDV) values ( '10','17','2018/07/24','2018/07/25','2018/08/10 20:00');
insert into RepAnnonce (Id_utilisateur,Id_annonce,Date_reponse,Date_acceptation_reponse,Date_RDV) values ( '10','18','2018/07/24','2018/07/25','2018/08/10 20:00');
insert into RepAnnonce (Id_utilisateur,Id_annonce,Date_reponse,Date_acceptation_reponse,Date_RDV) values ( '10','19','2018/07/24','2018/07/25','2018/08/10 20:00');
insert into RepAnnonce (Id_utilisateur,Id_annonce,Date_reponse,Date_acceptation_reponse,Date_RDV) values ( '10','20','2018/07/24','2018/07/25','2018/08/10 20:00');
insert into RepAnnonce (Id_utilisateur,Id_annonce,Date_reponse,Date_acceptation_reponse,Date_RDV) values ( '10','21','2018/07/24','2018/07/25','2018/08/10 20:00');
insert into RepAnnonce (Id_utilisateur,Id_annonce,Date_reponse,Date_acceptation_reponse,Date_RDV) values ( '10','22','2018/07/24','2018/07/25','2018/08/10 20:00');
insert into RepAnnonce (Id_utilisateur,Id_annonce,Date_reponse,Date_acceptation_reponse,Date_RDV) values ( '10','23','2018/07/24','2018/07/25','2018/08/10 20:00');
insert into RepAnnonce (Id_utilisateur,Id_annonce,Date_reponse,Date_acceptation_reponse,Date_RDV) values ( '10','24','2018/07/24','2018/07/25','2018/08/10 20:00');
insert into RepAnnonce (Id_utilisateur,Id_annonce,Date_reponse,Date_acceptation_reponse,Date_RDV) values ( '10','25','2018/07/24','2018/07/25','2018/08/10 20:00');
insert into RepAnnonce (Id_utilisateur,Id_annonce,Date_reponse,Date_acceptation_reponse,Date_RDV) values ( '10','26','2018/07/24','2018/07/25','2018/08/10 20:00');
insert into RepAnnonce (Id_utilisateur,Id_annonce,Date_reponse,Date_acceptation_reponse,Date_RDV) values ( '10','27','2018/08/08','2018/08/30','2018/08/30 21:00');


/*------------------EVALUATION ---------------------*/
insert into Evaluation (Id_reponse,Date_evaluation,Note,Commentaire) values ( '1','2018/02/25','5','Transaction parfaite');
insert into Evaluation (Id_reponse,Date_evaluation,Note,Commentaire) values ( '4','2018/08/10','2','en retard, j \‘ai du attendre 15 minutes');
insert into Evaluation (Id_reponse,Date_evaluation,Note,Commentaire) values ( '5','2018/08/11','5','super');
insert into Evaluation (Id_reponse,Date_evaluation,Note) values ( '6','2018/03/09','3');
insert into Evaluation (Id_reponse,Date_evaluation,Note,Commentaire) values ( '8','2018/08/15','4','RAS');
insert into Evaluation (Id_reponse,Date_evaluation,Note,Commentaire) values ( '12','2018/08/5','4','Perfect');

