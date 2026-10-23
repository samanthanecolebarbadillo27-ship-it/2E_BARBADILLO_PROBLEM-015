# Airport Baggage Logging & Claim System

Java console app using SQLite and a layered DAO architecture.

- `dao/DatabaseConnection.java` – JDBC connection (SQLite, `luggage.db`)
- `dao/ScenarioDao.java` – all SQL via PreparedStatement
- `model/Luggage.java` – entity
- `ui/LuggageUI.java` – menu and console table (no SQL)
- `Main.java` – entry point (no SQL)

Requires the `org.xerial:sqlite-jdbc` driver (included in `pom.xml`).
