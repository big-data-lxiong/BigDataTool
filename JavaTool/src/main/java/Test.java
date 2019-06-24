import org.apache.log4j.Logger;

public class Test {

  public static void main(String[] args){
    final Logger logger = Logger.getLogger(Test.class);

    try {
      logger.info("hello");
      logger.warn("warning");
      logger.debug("debug");
      logger.error("erro");
    } catch (Exception e){
    }

  }

}
