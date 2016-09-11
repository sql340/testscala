package com.spark.testscala

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.mllib.classification._
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.evaluation.MulticlassMetrics
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.feature.HashingTF

object LRWithLBFGS {
	def lrWithLBFGSMain() {
		val conf = new SparkConf()
		val sc = new SparkContext(conf)
		//    val sqlContext = new HiveContext(sc)

		//    val negData = MLUtils.loadLibSVMFile(sc,"shangqilong/data/negTrain.txt");
		//    val posData = MLUtils.loadLibSVMFile(sc,"shangqilong/data/posTrain.txt");

		val rawData = MLUtils.loadLibSVMFile(sc, "shangqilong/data/sigSample.txt")

		val splits = rawData.randomSplit(Array(0.9, 0.1), seed = 11L)
		val trainingData = splits(0).cache()
		val testData = splits(1)

		val model = new LogisticRegressionWithLBFGS()
//		.setNumClasses(2)
		.run(trainingData)

//		val predictionAndLabels = testData.map { case LabeledPoint(label, features) =>
//		  val prediction = model.predict(features)
//		  (prediction, label)
//		}
		
		model.clearThreshold()
		val testFeature = testData.map{ point =>
				val score = model.predict(point.features)
				(score, point.label)
		}
		
		val testF = testFeature.collect()
		for(it <- testF) {
		  println(it)
		}
		
		// Get evaluation metrics.
//		val metrics = new MulticlassMetrics(predictionAndLabels)
//		val precision = metrics.precision
//		println("----------Precision = " + precision + "----------")

		// Save and load model
		//    model.save(sc, "shangqilong/data")
		//    val sameModel = LogisticRegressionModel.load(sc, "shangqilong/data")

		sc.stop()
	}
}
