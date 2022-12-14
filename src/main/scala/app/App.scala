package app

import com.monovore.decline.effect.CommandIOApp
import cats.syntax.all._
import cats.effect.ExitCode
import cats.effect.IO
import com.monovore.decline.Opts
import cats.data.Reader.apply
import days._

object App
    extends CommandIOApp(
      "advent-of-code",
      "Run advent of code solutions",
      true,
      "0.0.0"
    ) {

  override def main: Opts[IO[ExitCode]] =
    AppOpts.solve.map(solve)

  def solve(solve: AppOpts.Solve): IO[ExitCode] =
    input.Reader.getInputForDay(solve).flatMap { input =>
      val puzzle = solve.day match {
        case 1 =>
          IO(Day1(input))
        case 2 =>
          IO(Day2(input))
        case 3 =>
          IO(Day3(input))
        case 4 =>
          IO(Day4(input))
        case 5 =>
          IO(Day5(input))
        case 6 =>
          IO(Day6(input))
        case 7 =>
          IO(Day7(input))
        case 8 =>
          IO(Day8(input))
        case 9 =>
          IO(Day9(input))
        case 10 =>
          IO(Day10(input))
        case 11 =>
          IO(Day11(input))
        case 12 =>
          IO(Day12(input))
        case 14 =>
          IO(Day14(input))
        case _ =>
          IO.raiseError[Puzzle](new Exception(s"Unsupported day: ${solve.day}"))
      }

      puzzle
        .flatMap { p =>
          IO(println(s"First:\n${p.solve}")) *>
            IO(println(s"Second:\n${p.solve2}"))
        }
        .as(ExitCode.Success)
    }
}
