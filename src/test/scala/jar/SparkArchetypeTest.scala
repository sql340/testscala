package com.spark.testscala

import org.junit.Test
import org.junit.Assert._
import org.scalatest.junit.AssertionsForJUnit

class SparkArchetypeTest extends AssertionsForJUnit{

  @Test
  def testSayHello() {
    println("Hello Spark")
    assertTrue(true)
  }
}