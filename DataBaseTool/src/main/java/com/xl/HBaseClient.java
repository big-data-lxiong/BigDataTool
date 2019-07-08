package com.xl;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.TableDescriptor;

public class HBaseClient {

	private HBaseClient INSTANCE = new HBaseClient();

	private HBaseClient() {
	}

	private void createTable() {
		// Instantiating configuration class
		Configuration con = HBaseConfiguration.create();

		try {
			// Instantiating HbaseAdmin class
			HBaseAdmin admin = new HBaseAdmin(con);

			// Instantiating table descriptor class
			HTableDescriptor tableDescriptor = new
				TableDescriptor(TableName.valueOf("emp"));

			// Adding column families to table descriptor
			tableDescriptor.addFamily(new HColumnDescriptor("personal"));
			tableDescriptor.addFamily(new HColumnDescriptor("professional"));


			// Execute the table through admin
			admin.createTable(tableDescriptor);
			System.out.println(" Table created ");

		} catch (Exception e) {

		}
	}


}
