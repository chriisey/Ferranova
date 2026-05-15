drop
    database if exists Progetto_tsw;
create database Progetto_tsw;
use Progetto_tsw;
CREATE TABLE Utente
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    nome         VARCHAR(30)  NOT NULL,
    cognome      VARCHAR(30)  NOT NULL,
    email        VARCHAR(255) NOT NULL UNIQUE,
    passwordhash VARCHAR(100) NOT NULL,
    telefono     VARCHAR(13)  NOT NULL,
    is_admin     BOOLEAN      NOT NULL DEFAULT FALSE,
    indirizzo    VARCHAR(255) NOT NULL
);


CREATE TABLE Promozione
(
    id          INT PRIMARY KEY  NOT NULL AUTO_INCREMENT,
    titolo      VARCHAR(255)     NOT NULL,
    descrizione text,
    sconto      tinyint unsigned NOT NULL,
    CHECK (sconto <= 100)
);


CREATE TABLE Prodotto
(
    id             INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    titolo         VARCHAR(255)    NOT NULL,
    descrizione    TEXT,
    quantità       INT             NOT NULL,
    id_promozione  INT,
    macrocategoria VARCHAR(30)     NOT NULL,
    sottocategoria VARCHAR(30)     NOT NULL,
    prezzo         DECIMAL(13, 2)  NOT NULL,
    FOREIGN KEY (id_promozione)
        REFERENCES Promozione (id),
    CHECK (quantità >= -1)

);

CREATE TABLE Carrello
(
    id_utente   INT          NOT NULL,
    id_prodotto INT          NOT NULL,
    quantità    INT unsigned NOT NULL,
    PRIMARY KEY (id_utente, id_prodotto),
    FOREIGN KEY (id_utente) references Utente (id),
    FOREIGN KEY (id_prodotto) references Prodotto (id),
    CHECK (quantità >= 0)
);

CREATE TABLE Ordine
(
    id               INT primary key                                        NOT NULL AUTO_INCREMENT,
    id_utente        INT                                                    NOT NULL,
    stato_ordine     enum ( 'consegnato', 'spedito', 'disperso','ordinato') not null default 'ordinato',
    prezzo_totale    decimal(13, 2)                                         not null,
    spese_spedizione decimal(13, 2)                                         not null default 0.00,
    data_ordine      date                                                   not null,
    data_spedizione  date,
    data_consegna    date,
    tipo_pagamento   varchar(40)                                            not null default 'carta di credito',
    indirizzo        varchar(255)                                           not null,

    FOREIGN KEY (id_utente) references Utente (id)
);

CREATE TABLE Ordine_Prodotto
(
    id_ordine     INT            NOT NULL,
    id_prodotto   INT            NOT NULL,
    quantità      int unsigned   not null,
    prezzo_finale decimal(13, 2) not null,
    PRIMARY KEY (id_ordine, id_prodotto),
    FOREIGN KEY (id_ordine) references Ordine (id),
    FOREIGN KEY (id_prodotto) references Prodotto (id)
);


INSERT INTO Utente (nome, cognome, email, passwordhash, telefono, is_admin, indirizzo)
VALUES ('Domenico', 'Amorelli', 'Domenico.admin@unisa.com', sha1('Bullo1!!!'), '1234567890', TRUE,
        'Policastro, Via Franceschini n.'),
       ('Alessandro', 'Abeille', 'Alessandro.admin@unisa.com', sha1('Bullo1!!!'), '3210987654', TRUE,
        'Avellino, via Tuoro Cappuccini n. 45'),
       ('Christian', 'Fontana', 'Christian.admin@unisa.com', sha1('Bullo1!!!'), '3210987654', TRUE,
        'Nocera Inferiore, via Gerardo Palma n. 11'),
       ('Domenico', 'Amorelli', 'Domenico.user@unisa.com', sha1('Bullo1!!!'), '1234567890', FALSE,
        'Policastro, Via Franceschini n.'),
       ('Alessandro', 'Abeille', 'Alessandro.user@unisa.com', sha1('Bullo1!!!'), '3210987654', FALSE,
        'Avellino, via Tuoro Cappuccini n. 45'),
       ('Christian', 'Fontana', 'Christian.user@unisa.com', sha1('Bullo1!!!'), '3210987654', FALSE,
        'Nocera Inferiore, via Gerardo Palma n. 11');

INSERT INTO Promozione (titolo, descrizione, sconto)
VALUES ('Natale 2023', 'Sconto del 10% su tutti i prodotti', 10),
       ('Sconto Invernale', 'Sconto del 15% su abbigliamento', 15),
       ('Agratis', 'Sconto del 100% su qualsiasi prodotto', 15);


INSERT INTO Prodotto (titolo, descrizione, quantità, id_promozione, macrocategoria, sottocategoria, prezzo)
/* */
VALUES ('Cacciavite a stella',
        'Il cacciavite a stella Pro 210 di Vales è un cacciavite professionale a croce con testa philipps e realizzato in acciaio cromo vanadio.</br>La punta è magnetica e brunita.</br>Grazie all''impugnatura morbida bimateriale con punti di appoggio per il pollice la presa sarà sempre salda, sicura e forte.</br>Lunghezza totale: 210 mm</br>Punta: PH2',
        2, NULL, 'Ferramenta', 'Utensili', 9.00),
       ('Trapano a percussione',
        'La linea Easy Bosch – un comodo aiuto in tutti i tuoi progetti.</br>Compatto, leggero ed ergonomico per lavorare in comodità e forare senza fatica nella muratura fino a 13 mm e nel legno fino a 25 mm.</br>Levetta di regolazione elettronica per gestire il numero di giri durante la foratura.</br>Mandrino autoserrante a doppia bussola, per sostituire la punta in modo semplice e veloce.</br>Dotazione: EasyImpact 630, valigetta',
        50, NULL, 'Ferramenta', 'Utensili', 59.00),
       ('Martello',
        'Martello da carpentiere con testa in acciaio forgiato e manico in legno resistente.</br>Peso: 500 g</br>Manico: 300 mm',
        30, NULL, 'Ferramenta', 'Utensili', 15.00),
       ('Cesoie per lamiera',
        'Cesoie per lamiera con lama in acciaio temprato e manici ergonomici.</br>Taglio fino a 1.2 mm di spessore', 20,
        NULL, 'Ferramenta', 'Utensili', 25.00),
       ('Sega circolare',
        'Sega circolare elettrica con lama da 185 mm.</br>Potenza: 1200 W</br>Velocità: 5000 giri/min</br>Profondità di taglio: 65 mm',
        15, NULL, 'Ferramenta', 'Utensili', 79.00),
       ('Livella laser',
        'Livella laser autolivellante con portata fino a 15 metri.</br>Precisione: ±0.3 mm/m</br>Durata batteria: 20 ore</br>Dotazione: custodia',
        25, NULL, 'Edilizia', 'Utensili', 99.00),
       ('Trapano avvitatore',
        'Trapano avvitatore a batteria 18V con due batterie al litio e caricabatterie.</br>Coppia di serraggio: 40 Nm</br>Velocità: 0-1500 giri/min',
        40, NULL, 'Ferramenta', 'Utensili', 89.00),
       ('Pala da neve',
        'Pala da neve in plastica resistente con manico in alluminio.</br>Larghezza: 50 cm</br>Lunghezza manico: 130 cm',
        50, NULL, 'Giardinaggio', 'Utensili', 19.00),
       ('Decespugliatore',
        'Decespugliatore a benzina con motore da 25cc e lama a doppio filo.</br>Lunghezza asta: 180 cm</br>Impugnatura regolabile',
        10, NULL, 'Giardinaggio', 'Utensili', 149.00),
       ('Tosaerba elettrico',
        'Tosaerba elettrico con motore da 1600 W e larghezza di taglio di 38 cm.</br>Regolazione altezza: 20-70 mm</br>Capacità sacco raccolta: 45 L',
        12, NULL, 'Giardinaggio', 'Utensili', 129.00),
       ('Forbici per potatura',
        'Forbici per potatura con lama in acciaio inox e manico ergonomico.</br>Lunghezza lama: 60 mm', 100, NULL,
        'Giardinaggio', 'Utensili', 12.00),
       ('Carriola',
        'Carriola da giardino con struttura in acciaio e vasca in plastica resistente.</br>Capacità: 80 L</br>Ruota pneumatica',
        20, NULL, 'Giardinaggio', 'Utensili', 39.00),
       ('Punte per trapano',
        'Set di punte per trapano in acciaio HSS per metallo, legno e muratura.</br>Include 10 punte di varie dimensioni',
        50, NULL, 'Ferramenta', 'Utensili', 15.00),
       ('Tagliapiastrelle',
        'Tagliapiastrelle manuale con lama in tungsteno.</br>Capacità di taglio: fino a 600 mm</br>Base antiscivolo', 5,
        NULL, 'Edilizia', 'Utensili', 45.00),
       ('Cemento rapido',
        'Cemento rapido per piccole riparazioni e fissaggi.</br>Sacchetto da 5 kg</br>Tempo di presa: 15 minuti', 80,
        NULL, 'Edilizia', 'Prodotti', 7.00),
       ('Scala telescopica',
        'Scala telescopica in alluminio con 12 gradini.</br>Altezza massima: 3.8 m</br>Portata: 150 kg', 8, NULL,
        'Edilizia', 'Utensili', 99.00),
       ('Saldatrice a filo',
        'Saldatrice a filo continuo per lavori di saldatura su acciaio e acciaio inox.</br>Potenza: 140 A</br>Include maschera e guanti',
        4, NULL, 'Ferramenta', 'Utensili', 199.00),
       ('Guanti da lavoro', 'Guanti da lavoro in pelle con rinforzi su palmo e dita.</br>Taglia: universale', 150, NULL,
        'Edilizia', 'Abbigliamento', 5.00),
       ('Rastrello',
        'Rastrello da giardino con denti in acciaio e manico in legno.</br>Larghezza: 40 cm</br>Lunghezza manico: 150 cm',
        60, NULL, 'Giardinaggio', 'Utensili', 14.00),
       ('Vanga',
        'Vanga da giardino in acciaio con manico in legno ergonomico.</br>Lunghezza lama: 25 cm</br>Lunghezza manico: 120 cm',
        40, NULL, 'Giardinaggio', 'Utensili', 18.00),
       ('Secchiello per malta', 'Secchiello in plastica resistente per miscelare malta e cemento.</br>Capacità: 20 L',
        70, NULL, 'Edilizia', 'Utensili', 8.00),
       ('Tagliasiepi',
        'Tagliasiepi elettrico con lama da 55 cm e motore da 600 W.</br>Impugnatura ergonomica e sistema anti-vibrazione',
        15, NULL, 'Giardinaggio', 'Utensili', 89.00),
       ('Pittura murale bianca',
        'Pittura murale bianca opaca per interni.</br>Capacità: 10 L</br>Copertura: 100 m²</br>Tempo di asciugatura: 2 ore',
        30, NULL, 'Edilizia', 'Prodotti', 25.00),
       ('Cacciavite a taglio',
        'Cacciavite a taglio Pro 150 di Vales con testa piatta e realizzato in acciaio cromo vanadio.</br>Punta magnetica e brunita.</br>Impugnatura morbida bimateriale con punti di appoggio per il pollice.</br>Lunghezza totale: 150 mm</br>Punta: SL5',
        5, NULL, 'Ferramenta', 'Utensili', 7.00),
       ('Maschera di protezione',
        'Maschera di protezione per occhi con lente antiappannamento e antigraffio.</br>Regolabile e confortevole.', 50,
        NULL, 'Ferramenta', 'Abbigliamento', 12.00),
       ('Compressore d\'aria',
        'Compressore d\'aria portatile con serbatoio da 24 L e motore da 2 HP.</br>Pressione massima: 8 bar', 8, NULL,
        'Ferramenta', 'Utensili', 129.00),
       ('Chiave inglese',
        'Chiave inglese regolabile con apertura massima di 30 mm.</br>Manico ergonomico per una presa confortevole.</br>Lunghezza: 250 mm',
        25, NULL, 'Ferramenta', 'Utensili', 15.00),
       ('Corda da giardino',
        'Corda da giardino in polipropilene resistente agli agenti atmosferici.</br>Lunghezza: 50 m</br>Diametro: 10 mm',
        40, NULL, 'Giardinaggio', 'Prodotti', 10.00),
       ('Batteria al litio',
        'Batteria al litio ricaricabile da 18V per utensili elettrici.</br>Capacità: 4.0 Ah</br>Tempo di ricarica: 60 minuti',
        30, NULL, 'Ferramenta', 'Elettronica', 49.00),
       ('Avvitatore a impulsi',
        'Avvitatore a impulsi con motore brushless e batteria da 18V.</br>Coppia di serraggio: 180 Nm</br>Include 2 batterie e caricabatterie',
        10, NULL, 'Ferramenta', 'Utensili', 179.00),
       ('Tagliasiepi a batteria',
        'Tagliasiepi a batteria con lama da 50 cm e motore da 18V.</br>Include batteria e caricabatterie', 15, NULL,
        'Giardinaggio', 'Utensili', 99.00),
       ('Tubo da giardino', 'Tubo da giardino estensibile fino a 30 m con pistola a spruzzo regolabile.', 50, NULL,
        'Giardinaggio', 'Prodotti', 29.00),
       ('Martello demolitore', 'Martello demolitore elettrico con potenza di 1500 W.</br>Include scalpello e punta', 7,
        NULL, 'Edilizia', 'Utensili', 299.00),
       ('Carrello portautensili',
        'Carrello portautensili con 5 cassetti e ruote girevoli.</br>Struttura in acciaio resistente', 3, NULL,
        'Ferramenta', 'Prodotti', 199.00),
       ('Rullo per pittura',
        'Rullo per pittura con manico telescopico e rullo intercambiabile.</br>Larghezza rullo: 25 cm</br>Include 2 rulli di ricambio',
        60, NULL, 'Edilizia', 'Utensili', 14.00),
       ('Trapano a colonna',
        'Trapano a colonna da banco con motore da 500 W.</br>Velocità regolabile</br>Include morsa', 4, NULL,
        'Ferramenta', 'Utensili', 249.00),
       ('Olio lubrificante', 'Olio lubrificante multiuso per ingranaggi e macchinari.', 50, 1, 'Ferramenta', 'Prodotti', 7.00),
       ('Set di viti', 'Set di viti in acciaio inox di varie dimensioni, confezione da 100 pezzi.', 100, NULL, 'Ferramenta', 'Viteria', 12.00),
       ('Multimetro digitale', 'Multimetro digitale per misurazioni di corrente, tensione e resistenza.', 15, NULL, 'Ferramenta', 'Elettronica', 35.00),
       ('Tuta da lavoro', 'Tuta da lavoro in cotone resistente con rinforzi alle ginocchia e numerose tasche.', 25, 2, 'Ferramenta', 'Abbigliamento', 45.00),
       ('Pialla elettrica', 'Pialla elettrica con lama in carburo di tungsteno e regolazione di profondità.', 10, NULL, 'Edilizia', 'Utensili', 99.00),
       ('Blocchi di calcestruzzo', 'Blocchi di calcestruzzo per costruzioni, dimensioni 20x20x40 cm.', 200, NULL, 'Edilizia', 'Materiali', 3.00),
       ('Stucco murale', 'Stucco murale in polvere per riparazioni e rifiniture interne.', 80, 1, 'Edilizia', 'Prodotti', 15.00),
       ('Tasselli', 'Tasselli in nylon per fissaggio, confezione da 50 pezzi.', 100, NULL, 'Edilizia', 'Viteria', 10.00),
       ('Casco protettivo', 'Casco protettivo regolabile con visiera e protezioni auricolari.', 30, 2, 'Edilizia', 'Abbigliamento', 30.00),
       ('Zappa', 'Zappa da giardino in acciaio con manico in legno ergonomico.', 50, NULL, 'Giardinaggio', 'Utensili', 18.00),
       ('Terriccio universale', 'Terriccio universale per piante da giardino, sacco da 50 L.', 100, 1, 'Giardinaggio', 'Prodotti', 8.00),
       ('Semi di pomodoro', 'Confezione di semi di pomodoro varietà San Marzano, confezione da 50 semi.', 200, NULL, 'Giardinaggio', 'Semi/Piante', 3.00),
       ('Concime granulare', 'Concime granulare a lenta cessione per piante da fiore e da frutto.', 50, NULL, 'Giardinaggio', 'Fertilizzanti', 12.00),
       ('Stivali di gomma', 'Stivali di gomma impermeabili per lavori di giardinaggio, taglia universale.', 30, 2, 'Giardinaggio', 'Abbigliamento', 25.00),
       ('Smerigliatrice angolare', 'Smerigliatrice angolare con disco da 125 mm e motore da 850 W.', 10, 1, 'Ferramenta', 'Utensili', 49.00),
       ('Chiave dinamometrica', 'Chiave dinamometrica con regolazione da 20 a 100 Nm.', 15, NULL, 'Ferramenta', 'Utensili', 65.00),
       ('Silicone sigillante', 'Silicone sigillante bianco per uso interno ed esterno, tubo da 300 ml.', 100, NULL, 'Ferramenta', 'Prodotti', 5.00),
       ('Carta abrasiva', 'Set di carte abrasive di varie grane per levigatura manuale e con utensili.', 200, NULL, 'Ferramenta', 'Prodotti', 8.00),
       ('Chiave a cricchetto', 'Chiave a cricchetto reversibile con 72 denti e impugnatura ergonomica.', 20, 1, 'Ferramenta', 'Utensili', 25.00),
       ('Dadi e bulloni', 'Set di dadi e bulloni in acciaio zincato, confezione da 100 pezzi.', 150, NULL, 'Ferramenta', 'Viteria', 15.00),
       ('Rondelle', 'Set di rondelle piane e molle in acciaio inox, confezione da 200 pezzi.', 180, NULL, 'Ferramenta', 'Viteria', 10.00),
       ('Caricabatterie universale', 'Caricabatterie universale per batterie al litio e nichel-cadmio.', 25, 1, 'Ferramenta', 'Elettronica', 35.00),
       ('Tester di continuità', 'Tester di continuità digitale per verifiche elettriche e di rete.', 20, NULL, 'Ferramenta', 'Elettronica', 20.00),
       ('Gilet ad alta visibilità', 'Gilet ad alta visibilità con bande riflettenti, taglia unica.', 40, 2, 'Ferramenta', 'Abbigliamento', 12.00),
       ('Scarpe antinfortunistiche', 'Scarpe antinfortunistiche con punta in acciaio, taglia 42.', 30, 2, 'Ferramenta', 'Abbigliamento', 50.00),
       ('Livella a bolla', 'Livella a bolla da 60 cm con 3 bolle per misurazioni orizzontali e verticali.', 25, 1, 'Edilizia', 'Utensili', 18.00),
       ('Sega per cartongesso', 'Sega per cartongesso con lama seghettata e impugnatura ergonomica.', 30, NULL, 'Edilizia', 'Utensili', 15.00),
       ('Ghiaia', 'Ghiaia per costruzioni e pavimentazioni, sacco da 25 kg.', 500, NULL, 'Edilizia', 'Materiali', 4.00),
       ('Mattoni forati', 'Mattoni forati in laterizio per costruzioni, dimensioni 25x12x20 cm.', 1000, NULL, 'Edilizia', 'Materiali', 1.50),
       ('Vernice antimuffa', 'Vernice antimuffa bianca per interni, latta da 5 L.', 60, 1, 'Edilizia', 'Prodotti', 40.00),
       ('Additivo per cemento', 'Additivo per migliorare l\'adesione e la lavorabilità del cemento, flacone da 1 L.', 80, NULL, 'Edilizia', 'Prodotti', 10.00),
       ('Chiodi da carpenteria', 'Chiodi da carpenteria in acciaio temperato, confezione da 1 kg.', 150, NULL, 'Edilizia', 'Viteria', 5.00),
       ('Ancoranti chimici', 'Ancoranti chimici per fissaggi pesanti, confezione da 300 ml.', 70, NULL, 'Edilizia', 'Viteria', 25.00),
       ('Giacca da lavoro impermeabile', 'Giacca da lavoro impermeabile con cappuccio, taglia L.', 20, 2, 'Edilizia', 'Abbigliamento', 60.00),
       ('Occhiali di protezione', 'Occhiali di protezione trasparenti con lenti antiappannamento.', 50, 2, 'Edilizia', 'Abbigliamento', 15.00),
       ('Spruzzatore a pressione', 'Spruzzatore a pressione con serbatoio da 5 L per trattamenti fitosanitari.', 40, 1, 'Giardinaggio', 'Utensili', 30.00),
       ('Forbici da siepe', 'Forbici da siepe con lame in acciaio e manici telescopici.', 35, NULL, 'Giardinaggio', 'Utensili', 25.00),
       ('Pacciamatura in corteccia', 'Pacciamatura in corteccia di pino, sacco da 50 L.', 100, NULL, 'Giardinaggio', 'Prodotti', 10.00),
       ('Rete ombreggiante', 'Rete ombreggiante per serre e giardini, lunghezza 10 m.', 70, 1, 'Giardinaggio', 'Prodotti', 20.00),
       ('Bulbi di tulipano', 'Confezione di bulbi di tulipano, 10 pezzi.', 200, NULL, 'Giardinaggio', 'Semi/Piante', 6.00),
       ('Pianta di basilico', 'Pianta di basilico in vaso da 10 cm.', 150, NULL, 'Giardinaggio', 'Semi/Piante', 2.50),
       ('Concime liquido', 'Concime liquido universale per piante da interno ed esterno, flacone da 1 L.', 80, NULL, 'Giardinaggio', 'Fertilizzanti', 8.00),
       ('Fertilizzante organico', 'Fertilizzante organico pellettato, sacco da 25 kg.', 60, NULL, 'Giardinaggio', 'Fertilizzanti', 15.00),
       ('Guanti da giardinaggio', 'Guanti da giardinaggio in tessuto antiscivolo, taglia M.', 100, 2, 'Giardinaggio', 'Abbigliamento', 5.00),
       ('Cappello da giardiniere', 'Cappello da giardiniere in cotone con protezione UV.', 40, 2, 'Giardinaggio', 'Abbigliamento', 12.00);


INSERT INTO Carrello (id_utente, id_prodotto, quantità)
VALUES (4, 1, 3),
       (4, 2, 1);

INSERT INTO Ordine (id_utente, stato_ordine, prezzo_totale, data_ordine, indirizzo)
VALUES (4, 'ordinato', 558.7, '2024-05-14', 'Nocera inferiore, via roma 44');

INSERT INTO Ordine_Prodotto (id_ordine, id_prodotto, quantità, prezzo_finale)
VALUES (1, 1, 3, 39.80),
       (1, 2, 1, 499.00);