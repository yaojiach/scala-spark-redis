import org.apache.spark.SparkContext
import org.apache.spark.sql.{DataFrame, SQLContext, SparkSession}
import com.redislabs.provider.redis._ 

object App {

  case class Person(name: String, age: Int)

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("redis-df")
      .config("spark.redis.host", "localhost")
      .config("spark.redis.port", "6379")
      .getOrCreate()

    val personSeq = Seq(Person("John", 30), Person("Peter", 45))
    val df = spark.createDataFrame(personSeq)

    df.write
      .format("org.apache.spark.sql.redis")
      .option("table", "person")
      .save()
    
    spark.stop()
  }

}
