import org.apache.avro.generic.GenericRecord
import org.apache.hadoop.fs.Path
import org.apache.parquet.hadoop.ParquetWriter
import org.apache.parquet.hadoop.metadata.CompressionCodecName

class Utils {
  ParquetWriter[GenericRecord] = AvroParquetWriter.
    builder[GenericRecord](new Path(baseTempDir + file))
    .withSchema(FilterMessage.getClassSchema())
    .withConf(hadoopConf)
    .withCompressionCodec(CompressionCodecName.SNAPPY)
    .build()
}
