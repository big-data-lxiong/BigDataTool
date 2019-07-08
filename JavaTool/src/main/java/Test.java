import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Test {

  final static Logger logger = Logger.getLogger(Test.class);

  public static void config() {
    String logDir = "../conf/log4j.properties";

    PropertyConfigurator.configure(logDir);
  }

  public static void main(String[] args){
    config();

    try {
      logger.info("hello");
      logger.warn("warning");
      logger.debug("debug");
      logger.error("erro");
    } catch (Exception e){
    }

  }

}
