package business
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by shang on 16/9/1.
  */
object TestSparkSQL {
  def getdata() {
    val sc = new SparkContext(new SparkConf());
    val hive_sc = new HiveContext(sc);
    val sql_context = new SQLContext(sc);  // 使用这个会抛异常,要么找不到数据表,要么说含有.的数据表需要``
    val sql = "select req_id, bu from mart_ads.fact_ads_impression where slot_id = 50020 AND partition_date = '2016-08-20'" +
      " limit 100"
    val dataFrame = hive_sc.sql(sql);
    for (df <- dataFrame.collect()) {
      println(df.get(0) + "\t" + df.get(1));
    }

    val df = sql_context.read.json("shangqilong/data/city.json");
    df.show()

    df.printSchema()
    df.select("pinyin").show()
  }
}
