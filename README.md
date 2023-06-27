# Facility manager

# Facility Manager

![Facility%20manager%2022d217cf348046b5aeda3d56f9444eef/image1.png](Facility%20manager%2022d217cf348046b5aeda3d56f9444eef/image1.png)

Applicativo per gestione pulizie e manutenzioni di bed and breakfast

La piattaforma di gestione degli interventi di pulizia e manutenzione per strutture Bed&Breakfast è stata ideata per rispondere alle esigenze di una società che gestisce un elevato numero di B&B e che si trova ad affrontare sfide nella coordinazione delle attività di pulizia e manutenzione per ciascuna struttura. L'obiettivo principale del progetto è integrare la piattaforma con l'infrastruttura domotica esistente dei B&B, consentendo l'apertura delle serrature da remoto tramite la tecnologia MQTT.

Bruffa Edoardo

Sommario

# Sommario

[Facility Manager 1](about:blank#facility-manager)

[1. Descrizione del progetto 3](about:blank#descrizione-del-progetto)

[2. Progetto per la generazione di prenotazioni 7](about:blank#progetto-per-la-generazione-di-prenotazioni)

[3. Analisi delle entità contenute nel software: 8](about:blank#analisi-delle-entit%C3%A0-contenute-nel-software)

[4. Package diagram : 13](about:blank#package-diagram)

[5. Dettaglio patter utilizzati 13](about:blank#dettaglio-patter-utilizzati)

[6. Api 14](about:blank#api)

[7. Testing 17](about:blank#testing)

# Descrizione del progetto

La piattaforma ha lo scopo di gestisce interventi di pulizia e manutenzione per strutture Bed&Breakfast, integrando l'infrastruttura domotica esistente per l'apertura remota delle serrature tramite MQTT. Utilizzando Angular come front-end, la piattaforma si suddivide in due flussi principali.

Piattaforma pulizie/manutenzione:

- I gestori pianificano gli interventi, notificandoli ai singoli impiegati dello staff.
- Gli impiegati visualizzano una lista di interventi giornalieri.
- Intervento di pulizia: il personale accede alla struttura e completa una checklist di attività, incluso il cambio di lenzuola, la pulizia dei pavimenti, cucina, bagno e segnalazione di malfunzionamenti (opzionale).
- Intervento di manutenzione: il personale accede alla struttura e completa una checklist specifica, inclusa una relazione dell'intervento, il costo, foto prima e dopo, segnalazioni e documentazione (opzionale).
- Vengono richieste valutazioni da 1 a 5 stelle e feedback per entrambi gli interventi.

![Facility%20manager%2022d217cf348046b5aeda3d56f9444eef/image2.jpeg](Facility%20manager%2022d217cf348046b5aeda3d56f9444eef/image2.jpeg)

Piattaforma pianificazione interventi e gestione prenotazioni:

- Integrazione tramite API con servizi online per ricevere nuove prenotazioni con date di inizio e fine.
- Procedura automatica alle 6:00 per pianificare gli interventi di pulizia in base ai check-out previsti.
- Gli interventi di manutenzione vengono registrati e pianificati per il primo check-out disponibile.
- Gli interventi di pulizia vengono assegnati alle persone considerando la distanza tra le strutture e pianificando gli spostamenti in modo efficiente.
- La piattaforma include tre ruoli principali: Admin, Manutentore, Pulizia.
- I ruoli vengono assegnati agli utenti registrati dall'Admin per assegnare gli interventi appropriati.
- La valutazione finale permette di tracciare il tempo trascorso tra l'accesso in struttura e la fine dell'intervento a fini statistici.

![Facility%20manager%2022d217cf348046b5aeda3d56f9444eef/image3.jpeg](Facility%20manager%2022d217cf348046b5aeda3d56f9444eef/image3.jpeg)

# 

Analizzando nel dettaglio le possibili azioni da parte degli utenti generici

![Facility%20manager%2022d217cf348046b5aeda3d56f9444eef/image4.png](Facility%20manager%2022d217cf348046b5aeda3d56f9444eef/image4.png)

Sono quindi disponibili le api di

- Registrazione
- Recupero password
- Cambio di rulo (ROLE ADMIN , ROLE CLEANER , etc..)
- Cambio password
- Modifica delle informazioni personali , quali nome , cognome, email , posizione, username.

La parte di autenticazione è gestita tramite sessione con JWT Token .

Le azioni di pulizia vengono create automaticamente da un task , il quale viene eseguito ogni mattina alle ore 6 e verifica tutte le prenotazioni in checkout per il giorno corrente.

Dopo averle individuate, crea una nuova Cleaning action e la associa all’utente con minore distanza dalla struttura in questione.

![Facility%20manager%2022d217cf348046b5aeda3d56f9444eef/image5.png](Facility%20manager%2022d217cf348046b5aeda3d56f9444eef/image5.png)

La piattaforma ha invece il compito di controllare tutto il flusso logico del sistema, dalla gestione delle strutture e prenotazioni fino alla gestione di utenti.

- Gestione delle pulizie, aggiungendo se necessario delle pulizie extra a quelle determinate automaticamente dal check-out dei clienti
- Creazione di preventivi , da associare ai manutentori , i quali andranno a compilare con dei dettagli organizzativi come
    - Tempo richiesto
    - Prezzo
    - Data di inizio dei lavori
    - Descrizione
    - Allegare file e documentazione
- Approvazione del preventivo da parte degli amministratori
- Gestione delle strutture
    - Verifica delle prenotazioni ricevute
    - Associazione dei varchi di accesso
    - Aggiunta di informazioni di dettaglio , come indirizzo . cap , posizione geografica
    - Lettura dei log di accesso , viene creato un nuovo log ogni volta che viene aperto un varco.
    - Lettura di files ed immagini allegate a azioni di pulizia oppure interventi di manutenzione.

# Progetto per la generazione di prenotazioni

Questo secondo progetto è un micro-servizio Springboot indipendente.

Contiene un task cron che invia una nuova prenotazione RANDOM al servizio principale.

In caso di sovrapposizione delle prenotazioni, il sistema non le rifiuterà’ automaticamente.

![Facility%20manager%2022d217cf348046b5aeda3d56f9444eef/image6.png](Facility%20manager%2022d217cf348046b5aeda3d56f9444eef/image6.png)

Il servizio viene avviato manualmente.

# Analisi delle entità contenute nel software:

La classe **User** rappresenta un'entità per la gestione degli utenti , ha diverse annotazioni che sfruttano il framework Jakarta Persistence e Jakarta Validation per la persistenza dei dati e la validazione dei campi.

**User** estende la classe "DateAudit" , la quale fornisce informazioni sulla data di creazione e ultima modifica dell'entità e contiene i seguenti attributi:

- id: identificatore univoco generato automaticamente per ogni istanza della classe.
- firstName, lastName: campi obbligatori che rappresentano rispettivamente il nome e il cognome dell'utente.
- email: campo obbligatorio che rappresenta l'indirizzo email dell'utente.
- username, password: campi che rappresentano il nome utente e la password dell'utente.
- latitude, longitude: campi obbligatori che rappresentano le coordinate geografiche dell'utente.
- roles: relazione molti a molti con l'entità "Role" che rappresenta i ruoli dell'utente.
- authorities: un insieme di autorizzazioni assegnate all'utente.
- cleaningActions: relazione uno a molti con l'entità "CleaningAction" che rappresenta le azioni di pulizia associate all'utente.
- feedbacks: relazione uno a molti con l'entità "Feedback" che rappresenta i feedback dell'utente.
- maintenances: relazione uno a molti con l'entità "Maintenance" che rappresenta le attività di manutenzione associate all'utente.
- quote: relazione uno a uno con l'entità "Quote" che rappresenta un preventivo per l'utente.
- accessLogs: relazione uno a molti con l'entità "AccessLog" che rappresenta i log di accesso dell'utente.

Utilizza le annotazioni del framework Lombok per generare automaticamente i metodi getter e setter, il costruttore con argomenti e quello senza argomenti, è mappata tramite una tabella "users" nel database relazionale MariaDB e tutte le relazioni sono gestite tramite annotazioni come @ManyToMany e @OneToMany.

Tutte le classi nel progetto seguono questo pattern di tecnologie.

La classe Structure contiene:

- id: identificatore univoco generato automaticamente per ogni istanza della classe.
- name, address, city, cap, province, country: campi obbligatori che rappresentano rispettivamente il nome, l'indirizzo, la città, il codice postale, la provincia e il paese della struttura.
- latitude, longitude: campi obbligatori che rappresentano le coordinate geografiche della struttura.
- description: campo obbligatorio che contiene una descrizione della struttura.
- isActive: campo obbligatorio che indica se la struttura è attiva o meno.
- cleaningDuration: campo che rappresenta la durata della pulizia della struttura, compreso tra 1 e 24 ore.
- gate: relazione uno a uno con l'entità "Gate" che rappresenta il varco di accesso alla struttura.
- cleaningActions: relazione uno a molti con l'entità "CleaningAction" che rappresenta le azioni di pulizia associate alla struttura nel tempo.
- maintenances: relazione uno a molti con l'entità "Maintenance" che rappresenta le attività di manutenzione associate alla struttura.
- quote: relazione uno a uno con l'entità "Quote" che rappresenta un preventivo per la struttura.

La classe **UserPrincipal** rappresenta l’utente in sessione e viene utilizzato per l'autenticazione e l'autorizzazione all'interno dell'applicazione e implementa l'interfaccia "UserDetails" fornita dal framework Spring Security.

La classe contiene i seguenti attributi:

- id: l'identificatore univoco dell'utente.
- email: l'indirizzo email dell'utente.
- password: la password dell'utente.
- authorities: una collezione di autorizzazioni concesse all'utente.
- attributes: una mappa di attributi aggiuntivi associati all'utente.

dispone di due costruttori, uno che richiede l'inizializzazione dei principali attributi e uno che utilizza un oggetto "User" per creare un'istanza di "UserPrincipal" popolando automaticamente gli attributi, fornisce anche un metodo statico "create" che crea un oggetto "UserPrincipal" a partire da un oggetto "User", mappando i ruoli dell'utente alle autorizzazioni, con lo stesso scopo del costruttore principale

I metodi implementati dall'interfaccia "UserDetails" sono i seguenti:

- getAuthorities: restituisce la collezione di autorizzazioni dell'utente.
- getPassword: restituisce la password dell'utente.
- getUsername: restituisce il nome utente dell'utente.
- isAccountNonExpired: indica se l'account dell'utente è scaduto.
- isAccountNonLocked: indica se l'account dell'utente è bloccato.
- isCredentialsNonExpired: indica se le credenziali dell'utente sono scadute.
- isEnabled: indica se l'utente è abilitato.

La classe **Reservation** rappresenta una prenotazione e ne memorizza le informazioni relative alla struttura.

Contiene i seguenti attributi:

- id: l'identificatore univoco della prenotazione.
- structure: l'istanza della struttura associata alla prenotazione. È una relazione Many-to-One con l'entità "Structure".
- arrival: la data di arrivo della prenotazione.
- departure: la data di partenza della prenotazione.
- checkIn: l'orario di check-in della prenotazione.
- checkOut: l'orario di check-out della prenotazione.
- guests: il numero di ospiti per la prenotazione.
- guestName: il nome dell'ospite associato alla prenotazione.
- guestSurname: il cognome dell'ospite associato alla prenotazione.

La classe "Quote" rappresenta un preventivo e contiene i seguenti attributi:

- id: l'identificatore univoco del preventivo.
- description: una descrizione del preventivo.
- price: il prezzo del preventivo.
- maintenance: l'istanza della manutenzione associata al preventivo. È una relazione One-to-One con l'entità "Maintenance".
- structure: l'istanza della struttura associata al preventivo. È una relazione One-to-One con l'entità "Structure".
- user: l'istanza dell'utente associato al preventivo. È una relazione One-to-One con l'entità "User".
- time: il tempo in giorni indicato nel preventivo.
- file: l'istanza del file associato al preventivo. È una relazione One-to-One con l'entità "File".
- accepted: indica se il preventivo è stato accettato o meno.

la classe utilizza le annotazioni @JsonBackReference e @JsonManagedReference per gestire la serializzazione JSON bidirezionale tra l'entità Quote e le entità **Maintenance**, **Structure**, **User** e **File**.

Infine, l'attributo **time** è annotato con @Min(1) per specificare che 1 ora e’ il valore minimo consentito e alcuni attributi tra cui **time** sono annotato con @Schema per fornire una descrizione specifica del tempo in giorni secondo il modello OpenAPI/Swagger.

L'enumerazione **MaintenanceStatus** rappresenta lo stato di una manutenzione e definisce una serie di costanti che rappresentano i diversi stati, dichiarate come valori in maiuscolo separati da virgole.

- Le costanti sono:
- CREATED: indica che la manutenzione è stata creata.
- AWAITING_QUOTE_APPROVAL: indica che la manutenzione è in attesa di approvazione del preventivo.
- CONFIRMED: indica che la manutenzione è stata confermata.
- REJECTED: indica che la manutenzione è stata respinta.

La classe **Maintenance** rappresenta una manutenzione, descritta da :

- id: l'identificatore univoco della manutenzione.
- structure: la struttura associata alla manutenzione.
- user: l'utente responsabile della manutenzione.
- checkList: la checklist associata alla manutenzione.
- feedback: il feedback associato alla manutenzione.
- status: lo stato della manutenzione, come descritto nel punto precedente.
- description: una descrizione della manutenzione.
- date: la data in cui è stata effettuata la manutenzione.
- duration: la durata della manutenzione in ore.
- pictures: una lista di immagini correlate alla manutenzione.
- documents: una lista di documenti correlati alla manutenzione.
- cost: il costo associato alla manutenzione.
- quote: il preventivo associato alla manutenzione.

La classe "Gate" rappresenta i varchi delle varie strutture:

- id: l'identificatore univoco del cancello.
- name: il nome del cancello.
- description: una descrizione del cancello.
- isActive: uno stato booleano che indica se il cancello è attivo o non attivo.
- mqttTopic: il topic MQTT associato al cancello.
- structure: la struttura associata al cancello.
- accessLogs: una lista di log di accesso orari.

In caso di apertura del varco, verra’ aggiunto un nuovo record in accessLogs.

La classe "CleaningAction" rappresenta un'azione di pulizia e contiene :

- id: L'identificatore univoco dell'azione di pulizia.
- checkList: L'istanza della checklist associata all'azione di pulizia. È una relazione many-to-one con l'entità "CheckList".
- user: L'istanza dell'utente associata all'azione di pulizia. È una relazione many-to-one con l'entità "User".
- structure: L'istanza della struttura associata all'azione di pulizia. È una relazione many-to-one con l'entità "Structure"
- date: La data dell'azione di pulizia.
- note: Una nota relativa all'azione di pulizia, memorizzata come oggetto binario lungo (CLOB) nel database.
- report: Un report relativo all'azione di pulizia, memorizzato come oggetto binario lungo (CLOB) nel database.
- pictures: Una lista di istanze di "File" che rappresentano le immagini associate all'azione di pulizia. È una relazione one-to-many con l'entità "File".
- feedback: L'istanza del feedback associata all'azione di pulizia. È una relazione one-to-one con l'entità "Feedback".
- cleaningDuration: La durata dell'azione di pulizia, espressa in ore. È un valore compreso tra 1 e 24.

![Facility%20manager%2022d217cf348046b5aeda3d56f9444eef/image7.png](Facility%20manager%2022d217cf348046b5aeda3d56f9444eef/image7.png)

Per riassumere il diagramma entità relazione è il seguente:

# Package diagram :

![Facility%20manager%2022d217cf348046b5aeda3d56f9444eef/image8.png](Facility%20manager%2022d217cf348046b5aeda3d56f9444eef/image8.png)

# Dettaglio patter utilizzati

JdbcTemplate:

JdbcTemplate è una classe fornita dal pacchetto JDBC che semplifica l'uso del JDBC (Java Database Connectivity) e aiuta ad evitare errori comuni. Nel progetto, viene utilizzata per eseguire operazioni di base sul database, come l'esecuzione di query e l'estrazione dei risultati. L'uso di JdbcTemplate rappresenta un approccio principale per l'utilizzo dei repository nel progetto.

Singleton:

Il pattern Singleton è un modello di progettazione creazione che garantisce l'esistenza di un'unica istanza di una classe e fornisce un punto di accesso globale ad essa.

Ogni istanza di classe gestita da Springboot, che sia un modello oppure un service è un singleton.

Builder Pattern:

Il pattern Builder è un modello di progettazione creazione che permette di costruire oggetti complessi passo dopo passo. Nel progetto, molte classi model hanno la loro implementazione del Builder.Consente di creare oggetti complessi con un'interfaccia chiara e flessibile, separando il processo di costruzione dalla rappresentazione finale dell'oggetto.

RestFul API Pattern:

Lo stile architetturale REST definisce un insieme di principi per progettare servizi Web scalabili. Nel progetto, le API seguono lo stile RestFul, aderendo ai principi di risorse ben definite, utilizzo dei verbi HTTP corretti e gestione dei parametri di query per filtrare e paginare i risultati delle richieste.

# Api

Un'API (Application Programming Interface) è un'interfaccia che consente a un programma o a un sito web di comunicare con un altro programma o servizio. Le API vengono utilizzate per condividere dati e funzionalità tra diverse applicazioni e sono disponibili in vari formati e tipi.

Nel contesto del progetto, l'API utilizzata segue l'architettura REST (Representational State Transfer), le quali stabiliscono regole generali per la rappresentazione dei dati.

Le richieste API possono essere di diversi tipi, tra cui:

- GET: Utilizzata per recuperare o leggere i dati da una risorsa specifica.
- POST: Utilizzata per creare o inserire nuovi dati in una risorsa.
- PUT: Utilizzata per aggiornare i dati di una risorsa esistente.
- DELETE: Utilizzata per eliminare una risorsa.

Nell'applicativo sono suddivise in servizi e categorie specifiche. Ogni API è stata opportunamente commentata secondo gli standard di OpenAPI v3.0.1. I commenti includono descrizioni dettagliate delle varie parti dell'API, nonché esempi di utilizzo, al fine di fornire una documentazione completa ed efficace per gli sviluppatori che utilizzano l'API.

ACCESSLOG

- POST /api/accessLogs/filter
- GET /api/accessLogs/structureFilter/{structureId}

CHECKLIST

- POST /api/checkLists/filter

FILECONTROLLER

- GET /api/files/{id}

AUTH

- POST /api/auth/tokenResetPassword
- POST /api/auth/signup
- POST /api/auth/registerUser
- POST /api/auth/refreshToken
- POST /api/auth/logout
- POST /api/auth/login
- POST /api/auth/createFirstUser
- GET /api/auth/me

CHECKLIST

- GET /api/checkLists/{checkListId}
- PUT /api/checkLists/{checkListId}
- DELETE /api/checkLists/{checkListId}
- GET /api/checkLists
- POST /api/checkLists
- POST /api/checkLists/filter
- GET /api/checkLists/name/{checkListName}

CLEANINGACTION

- GET /api/cleaningActions/{cleaningActionId}
- PUT /api/cleaningActions/{cleaningActionId}
- DELETE /api/cleaningActions/{cleaningActionId}
- POST /api/cleaningActions
- POST /api/cleaningActions/{cleaningActionId}/picture
- POST /api/cleaningActions/filter
- GET /api/cleaningActions/{cleaningActionId}/pictures
- DELETE /api/cleaningActions/picture/{pictureId}

FEEDBACKS

- POST /api/feedbacks
- DELETE /api/feedbacks/{feedbackId}

GATE

- GET /api/gates/{gateId}
- PUT /api/gates/{gateId}
- DELETE /api/gates/{gateId}
- PUT /api/gates/{gateId}/open
- POST /api/gates
- GET /api/gates/name/{gateName}

MAINTENANCE

- GET /api/maintenance/{maintenanceId}
- PUT /api/maintenance/{maintenanceId}
- DELETE /api/maintenance/{maintenanceId}
- POST /api/maintenance
- GET /api/maintenance/{maintenanceId}/picture
- POST /api/maintenance/{maintenanceId}/picture
- GET /api/maintenance/{maintenanceId}/document
- POST /api/maintenance/{maintenanceId}/document
- POST /api/maintenance/filter
- DELETE /api/maintenance/{pictureId}/picture
- DELETE /api/maintenance/{documentId}/document

MQTT-CONTROLLER

- POST /api/mqtt/publish
- GET /api/mqtt/subscribe

QUOTE

- PUT /api/quotes/{quoteId}
- POST /api/quotes/{quoteId}/file

RESERVATION

- POST /api/reservation
- POST /api/reservation/filter
- GET /api/reservation/{reservationId}
- DELETE /api/reservation/{reservationId}

STRUCTURE

- GET /api/structures/{structureId}
- PUT /api/structures/{structureId}
- DELETE /api/structures/{structureId}
- GET /api/structures
- POST /api/structures
- POST /api/structures/filter
- GET /api/structures/{structureId}/busyDates

USER

- GET /api/user/{userId}
- PUT /api/user/{userId}
- PUT /api/user/enable/{userId}
- PUT /api/user/disable/{userId}
- POST /api/user/recoveryPassword
- POST /api/user/changeRole/{userId}
- POST /api/user/changePassword
- GET /api/user/all

Per maggiori informazioni vedere il documento allegato

# Testing

Per il testing è stato utilizzando JUnit, un framework di test open source per Java che fornisce un test runner per eseguire i casi di test e offre supporto per le asserzioni per controllare i risultati attesi.

Per eseguire i test di unità, sono stati creati casi di test che verificano che la logica del programma funzioni come previsto

Ho utilizzato diverse annotazioni durante la scrittura dei casi di test:

- L'annotazione @Test specifica che il metodo è un metodo di prova.
- L'annotazione @Test(timeout=1000) specifica che il metodo avrà esito negativo se impiega più di 1000 millisecondi (1 secondo).
- L'annotazione @BeforeClass specifica che il metodo verrà invocato solo una volta, prima di iniziare tutti i test.
- L'annotazione @Before specifica che il metodo verrà richiamato prima di ogni test.
- L'annotazione @After specifica che il metodo verrà richiamato dopo ogni test.
- L'annotazione @AfterClass specifica che il metodo verrà invocato solo una volta, dopo aver terminato tutti i test.

La classe org.junit.Assert fornisce metodi per effettuare asserzioni sulla logica del programma. Alcuni dei metodi comuni della classe Assert sono:

1. `void assertEquals(boolean expected, boolean actual)`: verifica che due valori booleani siano uguali.

2. `void assertTrue(boolean condition)`: verifica che una condizione booleana sia vera.

3. `void assertFalse(boolean condition)`: verifica che una condizione booleana sia falsa.

4. `void assertNull(Object obj)`: verifica che un oggetto sia nullo.

5. `void assertNotNull(Object obj)`: verifica che un oggetto non sia nullo.

![Facility%20manager%2022d217cf348046b5aeda3d56f9444eef/image9.png](Facility%20manager%2022d217cf348046b5aeda3d56f9444eef/image9.png)

Il casi di test sono concentrati verso i service, i quali implementano le logiche e sono la parte più delicata del software.

Essendo tantissime le api, mi sono concentrato sui processi critici , quindi la parte di auth e’ stata trascurata (poiché’ implementata secondo i pattern di sicurezza di spingboot) . Questa parte la ritengo più robusta, e per testarla ci vorrebbe troppo tempo .

sono riportati 46 casi di test, tutti completamente diversi fra loro .

![Facility%20manager%2022d217cf348046b5aeda3d56f9444eef/image10.png](Facility%20manager%2022d217cf348046b5aeda3d56f9444eef/image10.png)

![Facility%20manager%2022d217cf348046b5aeda3d56f9444eef/image11.png](Facility%20manager%2022d217cf348046b5aeda3d56f9444eef/image11.png)

![Facility%20manager%2022d217cf348046b5aeda3d56f9444eef/image12.png](Facility%20manager%2022d217cf348046b5aeda3d56f9444eef/image12.png)