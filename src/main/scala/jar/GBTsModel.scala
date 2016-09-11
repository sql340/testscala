package jar

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.tree.GradientBoostedTrees
import org.apache.spark.mllib.tree.configuration.BoostingStrategy
import org.apache.spark.mllib.tree.model.GradientBoostedTreesModel
import org.apache.spark.mllib.util.MLUtils


/**
  * Created by shang on 16/8/30.
  */
object GBTsModel {
  def gbtsModel() {
    val conf = new SparkConf()
    val sc = new SparkContext(conf)

    // Load and parse the data file.
    val data = MLUtils.loadLibSVMFile(sc, "shangqilong/data/sigSample.txt")
    // Split the data into training and test sets (30% held out for testing)
    val splits = data.randomSplit(Array(0.7, 0.3))
    val (trainingData, testData) = (splits(0), splits(1))

    // Train a GradientBoostedTrees model.
    // The defaultParams for Classification use LogLoss by default.
    val boostingStrategy = BoostingStrategy.defaultParams("Regression")
//    val boostingStrategy = BoostingStrategy.defaultParams("Classification");
    boostingStrategy.numIterations = 3 // Note: Use more iterations in practice.
    boostingStrategy.treeStrategy.numClasses = 2
    boostingStrategy.treeStrategy.maxDepth = 5
    // Empty categoricalFeaturesInfo indicates all features are continuous.
    boostingStrategy.treeStrategy.categoricalFeaturesInfo = Map[Int, Int]()

    val model = GradientBoostedTrees.train(trainingData, boostingStrategy)

    // Evaluate model on test instances and compute test error
    val labelAndPreds = testData.map { point =>
      val prediction = model.predict(point.features)
      (point.label, prediction)
    }

    for ( res <- labelAndPreds.collect()) {
      println("label:" + res._1 + "  predict:" + res._2)
    }

    val testErr = labelAndPreds.filter(r => r._1 != r._2).count.toDouble / testData.count()
    println("Test Error = " + testErr)
    // 以下会打印出所有的树结构
//    println("Learned classification GBT model:\n" + model.toDebugString)

    // Save and load model
//    model.save(sc, "target/tmp/myGradientBoostingClassificationModel")
//    val sameModel = GradientBoostedTreesModel.load(sc,
//      "target/tmp/myGradientBoostingClassificationModel")
  }
}
