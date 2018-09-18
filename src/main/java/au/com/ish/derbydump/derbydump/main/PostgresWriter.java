package au.com.ish.derbydump.derbydump.main;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class PostgresWriter {

    private Properties prop = new Properties();

    public PostgresWriter(){

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://192.168.1.26:5432/mgtest", "postgres", "123456789");
            FileInputStream fis = new FileInputStream("derbyDumpOutput.sql");
            String ss = IOUtils.toString(fis, Charset.defaultCharset());
            List<String> sql = Arrays.asList(ss.split(";"));
            Statement stmt = conn.createStatement();
            for (String s : sql) {
                try {
                    s = s.replace("\n", " ") + ";";
                    System.out.println(s);
                    stmt.executeUpdate(s);
                } catch (SQLException exx) {
                    exx.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
