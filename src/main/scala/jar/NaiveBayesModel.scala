package jar

import org.apache.spark.mllib.classification.NaiveBayes
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by shang on 16/8/30.
  */
object NaiveBayesModel {

  def naiveBayesModelMain() {
    val conf = new SparkConf()
    val sc = new SparkContext(conf)

    val data = sc.textFile("shangqilong/data/sigSample.txt")

    // Split data into training (60%) and test (40%).
//    val splits
//    val Array(training, test) = data.randomSplit(Array(0.8, 0.2))
//    val training = splits(0)
//    val test = splits(1)

//    val model = NaiveBayes.train(training, lambda = 1.0, modelType = "multinomial")

//    val predictionAndLabel = test.map(p => (model.predict(p.features), p.label))
//    val accuracy = 1.0 * predictionAndLabel.filter(x => x._1 == x._2).count() / test.count()

    // Save and load model
//    model.save(sc, "target/tmp/myNaiveBayesModel")
//    val sameModel = NaiveBayesModel.load(sc, "target/tmp/myNaiveBayesModel")
  }

}
