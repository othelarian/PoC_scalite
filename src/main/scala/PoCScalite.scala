
//import doobie._
import doobie.implicits._
import doobie.util.transactor.Transactor
//import cats._
//import cats.data._
import cats.effect.IO
import scala.concurrent.ExecutionContext
//import cats.implicits._

object PoCScalite extends App {

    implicit val cs = IO.contextShift(ExecutionContext.global)

    val prog = sql"select 42".query[Int].unique

    val transactor = Transactor.fromDriverManager[IO](
        "org.sqlite.JDBC", "jdbc:sqlite:sample.db", "", ""
    )
    //
    //
    //
    val io = prog.transact(transactor)
    val r = io.unsafeRunSync
    println(r)
}
