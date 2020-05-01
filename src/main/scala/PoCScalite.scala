
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

    //val prog = sql"select * from sqlite_master".query[Int].unique

    val transactor = Transactor.fromDriverManager[IO](
        "org.sqlite.JDBC", "jdbc:sqlite:sample.db", "", ""
    )
    def safeCall: ConnectionIO[Either[Throwable, List[String]]] = {
    //def safeCall: ConnectionIO[Either[Throwable, String]] = {
        sql"select type, name from sqlite_master".query[String].to[List]
            .attempt
    }
    //
    //
    //
    val r = safeCall.transact(transactor).unsafeRunSync
    //val io = prog.transact(transactor)
    //val r = io.unsafeRunSync
    println(r)
}
