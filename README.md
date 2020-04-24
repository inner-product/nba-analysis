# NBA Data Analysis

A simple project for relative beginners at Scala working on data engineering
tasks.

The task is to create a service that analyses play-by-play data from a given CSV
file. The data was obtained from the sample log at
https://www.bigdataball.com/nba-historical-playbyplay-dataset/.

## Components

* [`EventService`](src/main/scala/EventService.scala) - The `main` application you are to build up. Its job is to:
    1. Read events until there are no more.
    2. Collate a `BoxScore` from the events.
    3. Post the `BoxScore` to the `BoxScoreService`, ensuring there are no errors.
* [`DataSource`](src/main/scala/DataSource.scala) - Translates raw CSV data to a structured event format.
* [`BoxScoreService`](src/main/scala/BoxScoreService.scala) - Contains the definition of a `BoxScore` and a (fake) method to `post` it to a remote endpoint.

## Tasks

1. Data Modeling
    1. Examine the data under [src/main/resources](src/main/resources) to get a sense of the structure and fields.
    2. Decide how to encode the CSV rows as a data structure (algebraic data type). Create this type as `DataSource.Event`.
    3. What methods are available to use from the `CsvReader`? How do they work? You can use the console, via `sbt console`, etc. The console (REPL) has **tab-completion**, which is very handy:

        ```
        > sbt console
        ...

        scala> import io.innerproduct.nba._
        import io.innerproduct.nba._

        scala> val reader = DataSource.reader
        reader: kantan.csv.CsvReader[kantan.csv.ReadResult[List[String]]] = kantan.codecs.resource.ResourceIterator$$anon$3@75331de9

        scala> reader.<TAB>
        ... lots of methods here ...        

        scala> reader.next
        res2: kantan.csv.ReadResult[List[String]] = Right(List(GAME-ID, Season that this data sets belongs to., Game
        ...
        ```
    4. Implement `DataSource.event()` to translate an event from a row of the CSV.
2. Reading events.
    1. In `EventService`, read all the available events. Decide how to handle errors during reading, if any.
    2. Design the transformation of `DataSource.Event` values into the datatypes of `BoxScoreService`. How do you accumulate the necessary state? How do you validate the necessary inputs and outputs?
    3. Sidebar: [`Validated`](src/main/scala/Validated.scala).
3. Post valid events.
    1. Invoke `BoxScoreService.post` and handle successes and errors.