package jar

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.tree.RandomForest
import org.apache.spark.mllib.tree.model.RandomForestModel
import org.apache.spark.mllib.util.MLUtils


/**
  * Created by shang on 16/8/30.
  */
object RandomForestsModel {
  def randomForestModelMain() {
    val conf = new SparkConf()
    val sc = new SparkContext(conf)

    // Load and parse the data file.
    val data = MLUtils.loadLibSVMFile(sc, "shangqilong/data/sigSample.txt")
    // Split the data into training and test sets (30% held out for testing)
    val splits = data.randomSplit(Array(0.8, 0.2))
    val (trainingData, testData) = (splits(0), splits(1))

    // Train a RandomForest model.
    // Empty categoricalFeaturesInfo indicates all features are continuous.
    val numClasses = 2
    val categoricalFeaturesInfo = Map[Int, Int]()
    val numTrees = 5 // Use more in practice.
    val featureSubsetStrategy = "auto" // Let the algorithm choose.
    val impurity = "variance" //"gini"
    val maxDepth = 5
    val maxBins = 32

    // 做分类
//    val model = RandomForest.trainClassifier(trainingData, numClasses, categoricalFeaturesInfo,
//      numTrees, featureSubsetStrategy, impurity, maxDepth, maxBins)

    // 做回归
    val model = RandomForest.trainRegressor(trainingData, categoricalFeaturesInfo,
      numTrees, featureSubsetStrategy, impurity, maxDepth, maxBins)

    // Evaluate model on test instances and compute test error
    val labelsAndPredictions = testData.map { point =>
      val prediction = model.predict(point.features)
      (point.label, prediction)
    }

    for (res <- labelsAndPredictions.collect()) {
//      println("label = " + res._1 + ",predict = " + res._2)
    }

    val testMSE = labelsAndPredictions.map { case (v, p) => math.pow((v - p), 2) }.mean()
    println("Test Mean Squared Error = " + testMSE)
    println("Learned regression forest model:\n" + model.toDebugString)

    // Save and load model
    //  model.save(sc, "target/tmp/myRandomForestRegressionModel")
    //  val sameModel = RandomForestModel.load(sc, "target/tmp/myRandomForestRegressionModel")
  }
}
