package com.b3.scala101
package core.session.one

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

//region Case Classes

case class DocumentContent(header: String, body: String, footer: String)
case class DocumentTitle(title: String)
case class Document(title: DocumentTitle, content: DocumentContent)

//endregion

class Baked {

  //region Scala is Lazy

  lazy val test = "Why am Lazy?"
  val answer = "Because u are executed when called not like me at runtime!"

  //endregion

  //region Matchers

  test match {
    case "A" =>
    case "B" =>
    case other =>
    case _ =>
  }

  //endregion

  //region string interpolation

  val thisStringIsInterpolated = s"Hello there Lazy did you have a Q? - $test Answer: $answer"

  //endregion

  //region Object creation

  var documents: Seq[Document] = {
    for (a <- 1 to 10) {
      documents :+ Document(DocumentTitle(title = s"title-$a"), DocumentContent(header = "h", body = "b", footer = "f"))
    }
    documents
  }

  //endregion

  //region defining a function getFirstDocumentHeader

  def getFirstDocumentHeader(): Future[Try[Option[String]]] = {

    documents.head.title match {
      case title: DocumentTitle => Future(Success(Some(title.title)))
      case _ => throw new Exception("Not found :(")
    }

  }

  //endregion

  //region function maybe

  def maybeDocuments: Option[Seq[Document]] = {
    Some(documents)
  }

  def getMaybeFirstDocumentHeader(): Future[Try[Option[Document]]] = {

    maybeDocuments match {
      case None => Future { Failure(new Exception("No documents :-(")) }
      case Some(value) => Future.successful(Success(Some(value.head)))
    }

  }

  //endregion get value

  //region calling a complex function

  def documentCaller(): Future[String] = {
    getFirstDocumentHeader().map {
      case Success(Some(str)) =>
        str match {
          case "title-1" => "Found title 1! :-)"
          case _ => "Did not find title :-("
        }
      case Success(None) =>
        println("HEY we got nothing back! :-(")
        throw new Exception("HEY we got nothing back! :-(")
      case Failure(exception) => throw exception
    }
  }

  //endregion

}

object Inheritance {
  //region Traits and inheritance

  trait BakedHelper {
    def getDocuments(): Future[Seq[Document]]

    def getSingleDocument(title: DocumentTitle)
  }

  class BakedHelperImpl extends BakedHelper {
    override def getDocuments(): Future[Seq[Document]] = ???

    override def getSingleDocument(title: DocumentTitle): Unit = ???
  }

  //endregion
}
