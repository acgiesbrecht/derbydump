package au.com.ish.derbydump.derbydump.main;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
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
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mgtest?currentSchema=mg", "postgres", "123456");
            FileInputStream fis = new FileInputStream("C:\\Users\\AdrianGiesbrecht\\IdeaProjects\\derbydump\\derbyDumpOutput.sql");
            String ss = IOUtils.toString(fis);
            List<String> sql = Arrays.asList(ss.split(";"));
            Statement stmt = conn.createStatement();
            for (String s : sql) {
                try {
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
