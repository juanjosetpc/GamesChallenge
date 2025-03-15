## Model Design Decisions

In this project, I have designed a complex relational model that consists of multiple tables, specifically:

- Player entity
- Location entity
- Level entity
- Game entity
- PlayerGame entity (an intermediate table that manages relations between players, games, and levels)

Although this model may seem more complex than necessary for a small example, it is much more scalable and maintainable in the long run.
### Scalability and Maintainability
1. **Levels Management**:
    - If more levels are required in the future, the system can easily accommodate them.
    - Simply insert a new entry into the `levels` table and associate it with `PlayerGame` when needed.

2. **Location Handling**:
    - Instead of storing location as a simple `String` inside `Player`, we use a dedicated `Location` table.
    - This prevents redundant or inconsistent location data.
    - It also ensures data normalization and avoids additional validation logic for user-inputted locations.

## How to Run the API

### Requirements
- Java **17** or later installed.

### Steps to Run
1. **Download the JAR file**: The compiled `bestseller-1.0.0.jar` file is located in the root directory of the repository.
2. **Open a terminal** and navigate to the directory where the JAR file is saved.
3. **Run the following command**:
   ```sh 
   java -jar bestseller-1.0.0.jar
4. API will be running at: `http://localhost:8080`
5. You can now test the API using Postman (see below for details).

## ️ Initial Data (Preloaded in H2 Database)
The application initializes an in-memory H2 database when it starts. This is done via the `InitializeDatabase.java` class located in the `config` directory.

**Data loaded on startup:**
### Locations
- Spain
- United States
- France
- Germany
- Brazil

### Games
- BlackJack
- Poker

### Levels
- PRO
- INVENCIBLE
- NOOB

### Players and their locations
| Player Name       | Location      |  
|------------------|---------------|  
| GamerX          | Spain         |  
| PlayerY        | United States |  
| ProPlayer       | France        |  
| NoobPlayer      | Germany       |  
| BrazilianGamer  | Brazil        |  
| GamerSpain2     | Spain         |  

### Player-Game Relations
| Player          | Game     | Level      |  
|----------------|---------|------------|  
| GamerX        | BlackJack | PRO        |  
| PlayerY       | Poker     | NOOB       |  
| ProPlayer     | Poker     | INVENCIBLE |  
| NoobPlayer    | BlackJack | NOOB       |  
| BrazilianGamer| BlackJack | PRO        |  
| GamerSpain2   | BlackJack | PRO        |  


## Testing the API via Postman

To facilitate testing, a Postman collection has been provided. You can use it to send requests to the API without manually configuring each request.

1. Import the Postman Collection `Game API.postman_collection.json` from the root directory of the repository.
2. Set the Base URL to `http://localhost:8080`.
3. Available API Endpoints  
   The Postman collection includes three configured requests:
    - GET Request: This request searches for players with same location and level for a specific game.
    - POST Request: This request creates a new player-game relationship.
    - PUT Request: This request updates the level of a player for a specific game.

   **Test Workflow**:

    - **Step 1**: Execute the **GET** request to search for players. This will return two players: `GamerSpain2` and `GamerX`, both marked as **PRO** in the game **BlackJack**.
    - **Step 2**: Execute the **POST** request to add `GamerX` as an **INVENCIBLE** player for the game **Poker**.
    - **Step 3**: Execute the **PUT** request to change `GamerX`'s status from **PRO** to **INVENCIBLE** in **BlackJack**.
    - **Step 4**: Finally, execute the **GET** request again. It will now return only `GamerSpain2` as a **PRO** player in **BlackJack**, while `GamerX` will no longer be listed as **PRO**.
