
//import doobie._
import doobie.ConnectionIO
import doobie.implicits._
import doobie.util.transactor.Transactor
import cats._
//import cats.data._
import cats.effect.IO
import cats.implicits._
import scala.concurrent.ExecutionContext

object PoCScalite extends App {

  implicit val cs = IO.contextShift(ExecutionContext.global)

  // requests
  val list_tables = sql"SELECT type, name FROM sqlite_master".query[(String, String)].to[List]
  val create_tt1 = sql"CREATE TABLE tt1 (idx INTEGER)".update.run
  val drop_tt1 = sql"DROP TABLE IF EXISTS tt1".update.run

  val transactor = Transactor.fromDriverManager[IO](
    "org.sqlite.JDBC", "jdbc:sqlite:sample.db", "", ""
  )

  def safeCall: ConnectionIO[Either[Throwable, List[(String, String)]]] = {
    sql"select type, name from sqlite_master".query[(String, String)].to[List]
      .attempt
  }
  def callprint(tup: (String, String)) = println(s"type: ${tup._1}, name: ${tup._2}")
  /*
  safeCall.transact(transactor).unsafeRunSync match {
    case Right(v) =>
      v map callprint
      val r = v contains ("table", "tt1")
    case Left(e) => ()
  }
  */
  //
  //def safeCall2 = (drop_tt1.attempt, create_tt1.attempt, list_tables.attempt)
  //def safeCall2 = (drop_tt1, create_tt1).flatMap
  //
  def t1 = create_tt1
  def t2 = drop_tt1
  def t3 = t1.flatMap(_ => t2).attempt
  //
  def t4 =
    for {
      a <- t1
      b <- t2
    } yield b
  //
  val r = t3.transact(transactor).unsafeRunSync /*match {
    case Left(e) => ()
    case Right(v) =>
      //
      println(v)
      //
  }*/
  //
  println(r)
  //
}
