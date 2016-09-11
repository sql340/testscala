package jar

import business.TestSparkSQL
import com.spark.testscala.LRWithLBFGS

/**
  * Created by shang on 16/8/30.
  */
object MainApp {
  def main(args: Array[String]): Unit = {
//    LRWithLBFGS.lrWithLBFGSMain()
    GBTsModel.gbtsModel();
//    RandomForestsModel.randomForestModelMain();
//    NaiveBayesModel.naiveBayesModelMain();
//    TestBayes.test()
//    TestSparkSQL.getdata();
  }
}
