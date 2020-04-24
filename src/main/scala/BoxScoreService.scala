package io.innerproduct
package nba

import scala.concurrent.Future

object BoxScoreService {
  /**
   * The type of errors from validating a box score. You must replace with something more sensible
   */
  type Error = Nothing

  /**
   * A [[BoxScoreLine]] summarizes a player's production in a game. This
   * implementation uses just primitive types (String and Int). In a more
   * sophisticated implementation we might want to define distinct types for
   * these values, so that we don't, for example, confuse fieldGoalsMade and
   * freeThrowsMade.
   */
  final case class BoxScoreLine(
    player: String,
    fieldGoalsMade: Int,
    fieldGoalsAttemped: Int,
    threePointersMade: Int,
    threePointersAttempted: Int,
    freeThrowsMade: Int,
    freeThrowsAttempted: Int,
    offensiveRebounds: Int,
    defensiveRebounds: Int,
    assists: Int,
    turnovers: Int,
    steals: Int,
    blocks: Int,
    fouls: Int
  ) {
    /**
     * Total points scored by this player in this game
     */
    val points =
      (fieldGoalsMade * 2) + (threePointersMade * 3) + freeThrowsMade

    /**
     * Total rebounds by this player in this game
     */
    val rebounds =
      offensiveRebounds + defensiveRebounds

    /**
     * We could easily change [[BoxScoreLine]] so that incorrect box score line
     * cannot be constructed, but we're assuming that we are receiving the data
     * from an external source and we need to validate the input it has sent us.
     * This method checks that the box score line is sensible. E.g. that
     * fieldGoalsMade <= fieldGoalsAttempted and so on.
     */
    def validate: Validated[Error, BoxScoreLine] =
      ???
  }

  final case class BoxScore(game: String, away: List[BoxScoreLine], home: List[BoxScoreLine]) {
    /**
     * Validate this [[BoxScore]]. In addition to validating the individual line
     * each team much field at least 8 players, and hence at least 8 box score
     * lines.
     */
    def validate: Validated[Error, BoxScore] =
      ???
  }

  implicit val ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global

  def boxScore(boxScore: BoxScore): Future[Either[List[Error], Unit]] = {
    def printStatisticalLeaders(lines: List[BoxScoreLine]): Unit = {
      val points = lines.sortBy(line => line.points).last.player
      val rebounds = lines.sortBy(line => line.rebounds).last.player
      val assists = lines.sortBy(line => line.assists).last.player
      println(s"Points:   ${points}")
      println(s"Rebounds: ${rebounds}")
      println(s"Assists:  ${assists}")
    }

    Future {
      if(math.random() < 0.2) throw new Exception("Out to lunch. Try again later.")
      else boxScore.validate match {
        case Validated.Success(b) =>
          println("-----------------------------")
          println(s"Box Score: ${b.game}")
          println("-----------------------------")
          println("Home Team Statistical Leaders")
          printStatisticalLeaders(b.home)
          println("-----------------------------")
          println("Away Team Statistical Leaders")
          printStatisticalLeaders(b.away)
          println("-----------------------------")

          Right(())
        case Validated.Failure(e) => Left(e)
      }
    }
  }
}
