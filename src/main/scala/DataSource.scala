package io.innerproduct
package nba

import kantan.csv._
import kantan.csv.ops._

/**
 * This object is responsible for reading the raw play-by-play data and
 * generating events from it. The raw data is typical of the format used by
 * working with CSV or Dataframes (or, more abstractly, data languages that
 * cannot express sum types). We want to parse into a more useful format of
 * events.
 */
object DataSource {
  /**
   * The type of events that we emit. You will have to change this.
   */
  type Event = Nothing

  /**
   * Convenient definitions to help you extract data from the raw output.
   */
  object Index {
    val gameId = 0
    val dataSet = 1
    val date = 2
    val a1 = 3
    val a2 = 4
    val a3 = 5
    val a4 = 6
    val a5 = 7
    val h1 = 8
    val h2 = 9
    val h3 = 10
    val h4 = 11
    val h5 = 12
    val period = 13
    val awayScore = 14
    val homeScore = 15
    val remainingTime = 16
    val elapsed = 17
    val playLength = 18
    val playId = 19
    val team = 20
    val eventType = 21
    val assist = 22
    val away = 23
    val home = 24
    val block = 25
    val entered = 26
    val left = 27
    val num = 28
    val opponent = 29
    val outOf = 30
    val player = 31
    val points = 32
    val possession = 33
    val reason = 34
    val result = 35
    val steal = 36
    val eventType2 = 37
    val shotDistance = 38
    val originalX = 39
    val originalY = 40
    val convertedX = 41
    val convertedY = 42
    val description = 43
  }

  val rawData: java.net.URL =
    getClass.getResource("/NBA-PbP-Sample-Dataset.csv")
  /**
   * Reads each row of the raw data as a List[String]
   */
  val reader: CsvReader[ReadResult[List[String]]] = rawData.asCsvReader[List[String]](rfc.withHeader)

  /**
   * This is the primary method you have to implement in this object. You need
   * to return [[Event]]s as a minimum, for which you will have to implement the
   * `Event` type and parse the raw data into this type. You might want to add
   * more structure than just [[Event]]. For example, what do you return when
   * there are no more events?
   */
  def event(reader: CsvReader[ReadResult[List[String]]]): Event =
    ???
}
